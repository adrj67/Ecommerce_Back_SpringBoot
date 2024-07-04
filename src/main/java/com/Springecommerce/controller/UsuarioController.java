/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Springecommerce.controller;

import com.Springecommerce.model.Orden;
import com.Springecommerce.model.Usuario;
import com.Springecommerce.service.IOrdenService;
import com.Springecommerce.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author adrj
 */

@RequestMapping("/usuario")
@Controller
public class UsuarioController {
  
  private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
  
  @Autowired
  private IUsuarioService usuarioService;
  
  @Autowired
  private IOrdenService ordenService;
  
  BCryptPasswordEncoder passEncode = new BCryptPasswordEncoder();
  
  // /usuario/registro
  @GetMapping("/registro")
  public String create(){
    return "usuario/registro";
  }
  
  @PostMapping("/save")
  public String save(Usuario usuario){
    try {
      
      logger.info("Usuarios registro: {}", usuario);
      usuario.setTipo("USER");
      usuario.setPassword(passEncode.encode(usuario.getPassword()));
 
      usuarioService.save(usuario);
    } catch (Exception e) {
      logger.error("Error al guardar el usuario: ", e);
    }
    return "redirect:/";
  }
  
  @GetMapping("/login")
  public String login() {
    return "usuario/login";
  }
  
  @PostMapping("/acceder")
  public String acceder(Usuario usuario, HttpSession session){
    logger.info("Accesos : {}", usuario);
    
    Optional<Usuario> user= usuarioService.findByEmail(usuario.getEmail());
    logger.info("Usuario de bd: {}", user.get());
    
    if(user.isPresent()){ //if(user.isPresent()){
      
      session.setAttribute("idusuario", user.get().getId());
      if(user.get().getTipo().equals("ADMIN")){
        logger.info("Tipo de Usuario: {}", user.get().getTipo());
        return "redirect:/administrador";
      } else {
        logger.info("Tipo de Usuario: {}", user.get().getTipo());
        return"redirect:/";
      }
    } else {
      logger.info("El Usuario no existe.");
    }
    
    return "redirect:/";
  }
  
  @GetMapping("/compras")
  public String obtenerCompras(Model model, HttpSession session){
    model.addAttribute("sesion", session.getAttribute("idusuario"));
    
    Usuario usuario =  usuarioService.findById(Integer.parseInt(session.getAttribute("idusuario").toString())).get();
    List<Orden> ordenes=ordenService.findByUsuario(usuario);
    
    model.addAttribute("ordenes", ordenes);
    
    return "usuario/compras";
  }
  
  @GetMapping("/detalle/{id}")
  public String detalleCompra(@PathVariable Integer id, HttpSession session, Model model){
    logger.info("Id de la orden: {}", id);
    Optional<Orden> orden= ordenService.findById(id);
    model.addAttribute("detalles", orden.get().getDetalle());
    //session
    model.addAttribute("sesion", session.getAttribute("idusuario"));
    
    return "usuario/detallecompra";
  }
  
  @GetMapping("/cerrar")
  public String cerrarSesion(HttpSession session){
    session.removeAttribute("idusuario");
    return "redirect:/";
  }
}
