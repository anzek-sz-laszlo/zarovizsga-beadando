/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.model;


/**
 *
 * @author User
 */
public class Beszerzes {
    private String nev;
    private int mennyiseg;
    private double ar;

    @Override
    public String toString() {
        return "{\n" 
                + "       beszerzett cikkelem : \"" + this.nev 
                + "\",\n       beszerzett mennyiseg : " + this.mennyiseg 
                + " db,\n       beszerzesi ar : " + this.ar 
                + " HUF\n" + "      }";
    }    
    
    public void toConsole(String s) {
        System.out.println("Beszerzes(" + s + ")\n      " + this.toString());
    }

    public Beszerzes() {
    }

    public Beszerzes(String nev, int mennyiseg, double ar) {
        this.nev = nev;
        this.mennyiseg = mennyiseg;
        this.ar = ar;
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

    public double getAr() {
        return ar;
    }

    public void setAr(double ar) {
        this.ar = ar;
    }
    
}
