/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.model;


/**
 * A vásárlóktól érkező megrendelések DTO-ja
 * @author User
 */
public class MegrendelesEladasra {
    // mit rendelt ?
    private String termekNev;
    // és mennyit belőle?
    private int rendeltMennyiseg;    

    public MegrendelesEladasra() {
    }

    public MegrendelesEladasra(String termekNev, int rendeltMennyiseg) {
        this.termekNev = termekNev;
        this.rendeltMennyiseg = rendeltMennyiseg;
    }

    public String getTermekNev() {
        return termekNev;
    }

    public void setTermekNev(String termekNev) {
        this.termekNev = termekNev;
    }

    public int getRendeltMennyiseg() {
        return rendeltMennyiseg;
    }

    public void setRendeltMennyiseg(int rendeltMennyiseg) {
        this.rendeltMennyiseg = rendeltMennyiseg;
    }
    
}
