/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend;


import hu.anzek.backend.service.ProjectAdmin;
import java.io.IOException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;


/**
 * 176-os backand Vizsgafeladat SPRINGBOOT környezetben
 * @author User
 */
public class GyartasApplication implements CommandLineRunner{
    // projektadminisztrációs adatok, kérlek ezt ne babráld, mert a vizsga sikerességét kockáztatod
    private static final String TMP_FILE = "projektadmin.tmp";
    public ProjectAdmin projetAdmin = new ProjectAdmin();    
    
    /**
     * a main metódus (a projekt belépési pontja)
     * @param args 
     */
    public static void main(String[] args) {
        SpringApplication.run(GyartasApplication.class, args);        
    }
    
    @Override
    public void run(String... args) throws IOException {
        this.projetAdmin.projektAdmin(10); 
        System.out.println("\nHello Vilag(!)\nEz itt a 176-os backend csoport Zarovizsga - projekt!\n");
        throw new RuntimeException("Forced RuntimError");               
    }
}
