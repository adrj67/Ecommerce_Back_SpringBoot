/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Springecommerce.controller;

import com.Springecommerce.model.Orden;
import com.Springecommerce.model.Producto;
import com.Springecommerce.service.IDetalleOrdenService;
import com.Springecommerce.service.IOrdenService;
import com.Springecommerce.service.IUsuarioService;
import com.Springecommerce.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author adrj
 */
@Controller
@RequestMapping("/administrador")
public class AdministradorController {
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private IUsuarioService usuarioService;
    
    @Autowired
    private IOrdenService ordenService;
    
    @Autowired
    private IDetalleOrdenService detalleOrdenService;
    
    @GetMapping("")
    public String home(Model model){
        
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        
        return "administrador/home";
    }
    
    @GetMapping("/usuarios")
    public String usuarios(Model model){
      model.addAttribute("usuarios", usuarioService.findAll());
      return "administrador/usuarios";
    }
    
    
    @GetMapping("/ordenes")
    public String ordenes(Model model){
      model.addAttribute("ordenes", ordenService.findAll());
      return "administrador/ordenes";
    }
    
    @GetMapping("/detalle/{id}")
    private String detalle(Model model, @PathVariable Integer id){
      
      Orden orden = ordenService.findById(id).get();
      
      model.addAttribute("detalles", orden.getDetalle());
      
      return "administrador/detalleOrden";
    }
}
