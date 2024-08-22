/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.controller;

import hu.anzek.backend.model.Beszerzes;
import hu.anzek.backend.service.ProjectAdmin;
import hu.anzek.backend.service.TermekService;
import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * A beszerzett (gyártott, átvett, vagy vásárolt) Termékek lekérdezésének a kontrollere  
 * @author User
 */
@RestController
@RequestMapping("") // <- itt add meg a gyökér erőforrás URL utvonalát (a Specifikáció alapján találhatod ki)
public class BeszerzesController {
    
    private final TermekService termekService;
    private final ProjectAdmin admin;
    public BeszerzesController(TermekService termekService, ProjectAdmin admin) {
        this.termekService = termekService;
        this.admin = admin;
    }
   
    // tedd rá a megfelelő annotációt és útvonalat
    public void getAdatatvetel() throws IOException {
        this.admin.projektAdmin(11);
        // írd meg azt az egy sort ami ide kellhet 
        // Ha már rendben van, akkor alaposan nézd át a service osztályt, mert mindened ami kell ott van!
    }
    
    // tedd rá a megfelelő annotációt és útvonalat
    public List<Beszerzes> getBeszerzesek() throws IOException {        
        this.admin.projektAdmin(12);        
        // az alábbi helyett írd meg azt az egy sort ami ide kellhet
        return null;
    }
    
    // tedd rá a megfelelő annotációt és útvonalat illetve a változót (ez utóbbit ugye /{..} közé)
    public Beszerzes getEgyKonkretBeszerzes(@PathVariable("termekNev") String termekNev) throws IOException {
        this.admin.projektAdmin(13);
        // az alábbi helyett írd meg azt az egy sort ami ide kellhet
        return null;
    }
}

