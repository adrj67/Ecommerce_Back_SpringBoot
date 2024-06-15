/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Springecommerce.service;

import com.Springecommerce.model.DetalleOrden;
import com.Springecommerce.repository.IDetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author adrj
 */

@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService{
  
  @Autowired
  private IDetalleOrdenRepository detalleOrdenRepository;

  @Override
  public DetalleOrden save(DetalleOrden detalleOrden) {
   
    return detalleOrdenRepository.save(detalleOrden);
  }
  
}
