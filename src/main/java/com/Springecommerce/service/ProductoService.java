/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Springecommerce.service;

import com.Springecommerce.model.Producto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author adrj
 */
public interface ProductoService {
    public Producto save (Producto producto);
    public Optional<Producto> get(Integer id);
    public void update (Producto producto);
    public void delete (Integer id);
    public List<Producto> findAll();
}
