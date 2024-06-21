/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Springecommerce.service;

import com.Springecommerce.model.Usuario;
import com.Springecommerce.repository.IUsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author adrj
 */
@Service
public class UsuarioServiceImpl implements IUsuarioService {
  
  @Autowired
  private IUsuarioRepository usuarioRepository;

  @Override
  public Optional<Usuario> findById(Integer id) {
    return usuarioRepository.findById(id);
  }

  @Override
  public Usuario save(Usuario usuario) {
  
    return usuarioRepository.save(usuario);
  }

  @Override
  public Optional<Usuario> findByEmail(String email) {
    
    return usuarioRepository.findByEmail(email);
  }

  
  
}
