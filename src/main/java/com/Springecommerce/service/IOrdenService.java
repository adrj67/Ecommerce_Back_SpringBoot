/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Springecommerce.service;

import com.Springecommerce.model.Orden;
import com.Springecommerce.model.Usuario;
import java.util.List;

/**
 *
 * @author adrj
 */


public interface IOrdenService {
  
  List<Orden> findAll();  
  Orden save (Orden orden);
  String generarNumeroOrden();
  List<Orden> findByUsuario(Usuario usuario);
  
}
