/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.Springecommerce.repository;

import com.Springecommerce.model.Orden;
import com.Springecommerce.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author adrj
 */

@Repository
public interface IOrdenRepository extends JpaRepository<Orden, Integer>{
  List<Orden> findByUsuario(Usuario usuario);
}
