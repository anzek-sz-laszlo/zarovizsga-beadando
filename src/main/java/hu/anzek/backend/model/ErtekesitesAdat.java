/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


/**
 *
 * @author User
 */
@Entity
public class ErtekesitesAdat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String termekNev;
    private int eladottMennyiseg;
    private double eladasiAr;

    public ErtekesitesAdat(String termekNev, int eladottMennyiseg, double eladasiAr) {
        this.termekNev = termekNev;
        this.eladottMennyiseg = eladottMennyiseg;
        this.eladasiAr = eladasiAr;
    }

    public ErtekesitesAdat() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTermekNev() {
        return termekNev;
    }

    public void setTermekNev(String termekNev) {
        this.termekNev = termekNev;
    }

    public int getEladottMennyiseg() {
        return eladottMennyiseg;
    }

    public void setEladottMennyiseg(int eladottMennyiseg) {
        this.eladottMennyiseg = eladottMennyiseg;
    }

    public double getEladasiAr() {
        return eladasiAr;
    }

    public void setEladasiAr(double eladasiAr) {
        this.eladasiAr = eladasiAr;
    }
}
