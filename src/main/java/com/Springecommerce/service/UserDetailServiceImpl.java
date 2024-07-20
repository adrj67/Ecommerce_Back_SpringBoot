/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Springecommerce.service;

import com.Springecommerce.model.Usuario;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    HttpSession session;

    private Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Este es el username: {}", username);
        Optional<Usuario> optionalUser = usuarioService.findByEmail(username);
        if (optionalUser.isPresent()) {
            log.info("Esto es el id del usuario: {}", optionalUser.get().getId());
            session.setAttribute("idusuario", optionalUser.get().getId());
            Usuario usuario = optionalUser.get();
            return User.builder()
                .username(usuario.getNombre())
                .password((usuario.getPassword())) // .password(passwordEncoder.encode(usuario.getPassword()))
                .roles(usuario.getTipo())
                .build();
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }
}

/** Primer version
import com.Springecommerce.model.Usuario;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService{
  
  @Autowired
  private IUsuarioService usuarioService;
  
  @Autowired
  private BCryptPasswordEncoder bCrypt;
  
  @Autowired
  HttpSession session;
  
  private Logger log = LoggerFactory.getLogger(UserDetailServiceImpl.class);

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("Este es el username: {}", username);
    Optional<Usuario> optionalUser=usuarioService.findByEmail(username);
    if(optionalUser.isPresent()){
      log.info("Esto es el id del usuario: {}", optionalUser.get().getId());
      session.setAttribute("idusuario", optionalUser.get().getId());
      Usuario usuario =optionalUser.get();
      return User.builder().username(usuario.getNombre()).password(bCrypt.encode(usuario.getPassword())).roles(usuario.getTipo()).build();
    }else{
      throw new UsernameNotFoundException("Usuario no encontrado");
    }
  }
  
}
*/