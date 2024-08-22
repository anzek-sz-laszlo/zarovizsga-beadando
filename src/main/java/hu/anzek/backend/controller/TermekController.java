/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.controller;


import hu.anzek.backend.model.Termek;
import hu.anzek.backend.service.ProjectAdmin;
import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author User
 */
@RestController
@RequestMapping("termek")
public class TermekController {

    // egészítsd ki az injektálást a hiáynzó osztály, vagy osztályok injektálásával
    // hogy a kontroller el tudja látni azt egy dolgot, ami a neki szánt feladat: egy sima Terméklistát adjon vissza
    private final ProjectAdmin admin;
    public TermekController(ProjectAdmin admin) {
        this.admin = admin;
    }
    
    @GetMapping("/termeklista")
    public List<Termek> getTermekListaSqlbol() throws IOException {
        this.admin.projektAdmin(17);
        // írd meg (javítsd, pótold a hiányzó részt)
        return null;
    }    
}
