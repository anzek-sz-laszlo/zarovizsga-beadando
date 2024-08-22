/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hu.anzek.backend.gyartasirendeleskezeles;

import hu.anzek.backend.model.Beszerzes;
import hu.anzek.backend.service.ProjectAdmin;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author User
 */
public class TermekTest {
    
    @Autowired
    private ProjectAdmin admin;
    
    @Test
    public void testConstructor() throws IOException {
        Beszerzes beszerzes = new Beszerzes("Alma", 10, 2.5);
        assertEquals("Alma", beszerzes.getNev());
        assertEquals(0, beszerzes.getMennyiseg()); // <- add meg az "expected" helyes értékét a nulla helyett!
        assertEquals(0, beszerzes.getAr()); // <- add meg az "expected" helyes értékét a nulla helyett!
        this.admin.projektAdmin(18);
    }

    @Test
    public void testToString() throws IOException {
        Beszerzes termek = new Beszerzes("Alma", 10, 2.5);
        String expected = "{\n" +
                          "       beszerzett cikkelem : \"Alma\",\n" +
                          "       beszerzett mennyiseg : 10 db,\n" +
                          "       beszerzesi ar : 2.5 HUF\n" +
                          "      }";
        assertEquals(expected, termek); // <- add meg, hogy helyesn mivel kell összehasonlítanunk az "excepted" referenciát?
        this.admin.projektAdmin(18);
    }
}
