/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Springecommerce.controller;

import com.Springecommerce.model.Usuario;
import com.Springecommerce.service.IUsuarioService;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
  
  // /usuario/registro
  @GetMapping("/registro")
  public String create(){
    return "usuario/registro";
  }
  
  @PostMapping("/save")
  public String save(Usuario usuario){
    logger.info("Usuarios registro: {}", usuario);
    usuario.setTipo("USER");
    usuarioService.save(usuario);
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
    //logger.info("Usuario de bd: {}", user.get());
    
    if(user.isPresent()){
      session.setAttribute("idusuario", user.get().getId());
      if(user.get().getTipo().equals("ADMIN")){
        return "redirect:/administrador";
      } else {
        return"redirect:/";
      }
    } else {
      logger.info("El Usuario no existe.");
    }
    
    return "redirect:/";
  }
}
