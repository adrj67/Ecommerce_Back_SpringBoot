/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Springecommerce.controller;

//import ch.qos.logback.core.model.Model;
import org.springframework.ui.Model;
import com.Springecommerce.model.Producto;
import com.Springecommerce.model.Usuario;
import com.Springecommerce.service.IUsuarioService;
import com.Springecommerce.service.ProductoService;
import com.Springecommerce.service.UploadFileService;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author adrj
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private UploadFileService upload;
    
    @Autowired
    private IUsuarioService usuarioService;
    
    @GetMapping("")
    public String show(Model model){
        model.addAttribute("productos", productoService.findAll());
        return "productos/show";
    }
    
    @GetMapping("/create")
    public String create(){
        return "productos/create";
    }
    
    @PostMapping("/save")
    public String save(Producto producto, @RequestParam ("img") MultipartFile file, HttpSession session) throws IOException{
        // Logger muestra por la consola de Netbeans los detalles que va a guardar, sin guardar en la base de datos
        LOGGER.info("Este es el objeto producto {}", producto);
        Usuario u = usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
        producto.setUsuario(u);
        
        //imagen
        if (producto.getId()== null){//cuando se crea un producto
            String nombreImagen= upload.saveImage(file);
            producto.setImagen(nombreImagen);
        }else {
            // ver que paso con este else vacio
        }
        productoService.save(producto);
        return "redirect:/productos";
    }
        
    @GetMapping("/edit/{id}")
    public String edit (@PathVariable Integer id, Model model){
        Producto producto = new Producto();
        Optional<Producto> optionalProducto=productoService.get(id);
        producto = optionalProducto.get();
        
        LOGGER.info("Producto buscado: {}", producto);
        model.addAttribute("producto", producto);
        return "productos/edit";
    }
    
    @PostMapping("/update")
    public String update(Producto producto, @RequestParam ("img") MultipartFile file) throws IOException {
        Producto p = new Producto();
        p=productoService.get(producto.getId()).get();
        
        if (file.isEmpty()){ // editamos el producto pero no cambiamos la imagen                
            producto.setImagen(p.getImagen());
            }else{ // cuando se edita tambien la imagen

                // elimina la imagen siempre que no sea por defecto (default.jpg)
                if(!p.getImagen().equals("default.jpg")){
                    upload.deleteImage(p.getImagen());
                }
            
                String nombreImagen= upload.saveImage(file);
                producto.setImagen(nombreImagen);
            }
            producto.setUsuario(p.getUsuario());
            productoService.update(producto);
            return "redirect:/productos";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        
        Producto p = new Producto();
        p=productoService.get(id).get();
        
        // elimina la imagen siempre que no sea por defecto (default.jpg)
        if(!p.getImagen().equals("default.jpg")){
            upload.deleteImage(p.getImagen());
        }
        productoService.delete(id);
        return "redirect:/productos";
    }
}
