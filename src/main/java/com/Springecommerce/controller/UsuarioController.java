/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Springecommerce.controller;

import com.Springecommerce.model.Usuario;
import com.Springecommerce.service.IUsuarioService;
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
}
