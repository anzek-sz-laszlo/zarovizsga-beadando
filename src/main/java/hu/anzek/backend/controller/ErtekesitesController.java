/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.controller;


import hu.anzek.backend.model.MegrendelesEladasra;
import hu.anzek.backend.service.ErtekesitesService;
import hu.anzek.backend.service.ProjectAdmin;
import java.io.IOException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Az Értékesítési adatok REST-kontrollere
 * @author User
 */
@RestController
@RequestMapping("/eladasok")
public class ErtekesitesController {
    
    /**
     * a konstruktor és egy konstruktor injektálás
     */
    private final ErtekesitesService service;
    private final ProjectAdmin admin;
    public ErtekesitesController(ErtekesitesService service, ProjectAdmin admin) {
        this.service = service;
        this.admin = admin;
    }
    
    /**
     * megrendelés érkezik a "/megrendeles" erőforrás végpontra:
     * . így kezeljük le:
     * - először ellenőrizzük, hogy van-e ilyen termék és ki tudjuk-e szolgálni (van-e elegendő eladható mennyiség belőle)
     * - másodszor, ha rendben van: rögzítsük azt eladásként, de előtte növeljük a "Termek.eladva" mennyiségét!
     * - - és így csak amenyniben ez utóbbi sikeres művelet volt, akkor rögzítjük magát az eladást!
     * - végül visszakpjuk a rögzített etitást, 
     * - - de nekünk itt DTO-val kell vissztérnünk, úgyhogy át kell alakítanunk Dto-vá és azt adjuk vissza...
     * (Nem célszerű a bejövő adatot visszadni, mert akkor becsaphatjuk a klienst hiszen nem biztos, hogy sikerült letárolnia)
     * @param eladasra eladásra beérkező kérés (a termék neve és a megrendelt mennyiség).
     * @return ha sikerült, akkor az Eladást adjuk vissza, ha nem akkor a "null" értéket!
     * @throws java.io.IOException
     */
    @PostMapping("/megrendeles")
    public ResponseEntity<MegrendelesEladasra> addErtekesites(@RequestBody MegrendelesEladasra eladasra) throws IOException {
        this.admin.projektAdmin(14);
        // a teljes kódot írd meg!
        // ehez nézd át az előzőleg már kijavított Service osztályt, 
        // mert három olyan metódusa is van, amelyre neked itt szükséged lesz!    
        // a vissztérő értékeknél üógyelj, hogy vagy sikeres volt, vagy nem ennek megfelelően jét fajta kimenet kell!
        return null;
    }
    
    /**
     * Az erőforrás végpontra beküldött GET (http-kérésre) az eddig rögzített összes eladást listázzuk ki
     * @return ha vannak már eladások akkor annak a listája, máskülönben "nem található adat" 404 megy vissza
     * @throws java.io.IOException
     */
    @GetMapping("/lista")
    public ResponseEntity<List<MegrendelesEladasra>> getAllErtekesites() throws IOException {
        this.admin.projektAdmin(15);
        List<MegrendelesEladasra> lista = this.service.mapperToList(this.service.getAllErtekesites());
        if( ! lista.isEmpty()) {
            return ResponseEntity.ok(lista);
        }else{
            return ResponseEntity.notFound().build();
        }
    }    
    
    /**
     * A még eladhato mennyiseget mutatja az adott termék megnevezésére
     * @param termekNev a termeék megnevezése
     * @return a még eladhato mennyiség (m >= 0) vagy hibás kérés: nem található ilyen adat!
     * @throws java.io.IOException
     */
    @GetMapping("/eladhato/{termekNev}")
    public ResponseEntity<Integer> getEladhatoMennyisegVanMegBelole(@PathVariable("termekNev") 
                                                                    String termekNev) throws IOException {
        this.admin.projektAdmin(16);
        Integer eladhato = this.service.mennyiEladhatoVanBelole(termekNev);
        if( eladhato != null) {
            return ResponseEntity.ok(eladhato);
        }else{
            return ResponseEntity.notFound().build();
        }        
    }
}
