/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Springecommerce.service;

import com.Springecommerce.model.Orden;
import com.Springecommerce.repository.IOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author adrj
 */

@Service
public class OrdenServiceImpl implements IOrdenService{
  
  @Autowired
  private IOrdenRepository ordenRepository;

  @Override
  public Orden save(Orden orden) {
    return ordenRepository.save(orden);
  }
  
}
