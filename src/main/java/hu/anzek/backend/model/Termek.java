/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 *
 * @author User
 */
public class Termek {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nev;
    private int mennyiseg;
    private int eladva;
    private double ar;

    public Termek() {
    }

    public Termek(Long id,
                  String nev,
                  int mennyiseg,
                  int eladva,
                  double ar) {
        this.id = id;
        this.nev = nev;
        this.mennyiseg = mennyiseg;
        this.eladva = eladva;
        this.ar = ar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public int getMennyiseg() {
        return mennyiseg;
    }

    public void setMennyiseg(int mennyiseg) {
        this.mennyiseg = mennyiseg;
    }

    public int getEladva() {
        return eladva;
    }

    public void setEladva(int eladva) {
        this.eladva = eladva;
    }

    public double getAr() {
        return ar;
    }

    public void setAr(double ar) {
        this.ar = ar;
    }

    @Override
    public String toString() {
        return "{\n" + "       termeknev : \"" + this.nev 
                     + "\",\n       eredeti_mennyiseg : "  + this.mennyiseg 
                     + " db,\n       ebbol_eladva : " + this.eladva 
                     + " db,\n       egysegar : " + this.ar 
                     + " HUF\n" + "      }";
    }    
    
    public void toConsole(String s) {
        System.out.println("Termek(" + s + ")\n      " + this.toString());
    }

}
