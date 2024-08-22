/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hu.anzek.backend.repository;


import hu.anzek.backend.model.Termek;
import java.util.Optional;


/**
 *
 * @author User
 */
public interface TermekRepository{    
    public Optional<Termek> findByNevet(String nev);
}
