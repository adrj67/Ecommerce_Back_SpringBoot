/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Springecommerce.service;

import com.Springecommerce.model.Usuario;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author adrj
 */
public interface IUsuarioService {
  List<Usuario> findAll();
  Optional<Usuario> findById (Integer id);
  Usuario save (Usuario usuario);
  Optional<Usuario> findByEmail (String email);
  
}
