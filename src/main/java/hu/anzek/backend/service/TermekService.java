/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.service;


import hu.anzek.backend.model.Beszerzes;
import hu.anzek.backend.model.Termek;
import hu.anzek.backend.repository.TermekRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * A Termék problémák kezelésének üzleti logikája
 * @author User
 */
@Service 
public class TermekService {
    private static final String BESZERZES_FILE = "beszerzesek.txt";   
    
    private final TermekRepository repo;
    private final BeszerzesFileReader fileReader;
    

    public TermekService(TermekRepository repo, BeszerzesFileReader fileReader) {
        this.repo = repo;
        this.fileReader = fileReader;
    }
    
    /**
     * Beszerzések átemelése SQL -be!
     * Minden terméket újra és újra kivesz a "beszerzesek.txt" -ből, de a nevüket ellenőrzi!
     * Csak azt tárolja le, amelyik még nem szerepel az adatbázisban!
     * @return visszaadja az épp lementett adatok listáját (tehát csak amit most mentett el az adatbázisba!)
     */
    @Transactional
    public List<Termek> beszerzestSqlbe() {
        List<Termek> termekLista = new ArrayList<>();
        List<Beszerzes> beszerzesek = this.getBeszerzesekTxtbolLista();
        for(Beszerzes b : beszerzesek) {
            Termek termek = new Termek(null, b.getNev(), b.getMennyiseg(), 0, b.getAr());
            // adatátvitel az sql-be (már ha kell most ez a tétlel):
            this.saveIfNotExists(termek, termekLista);            
        }           
        return termekLista;
    }
    
    /**
     * Egy cikklista incializálása
     * @return egy üres cikklista visszaadása
     */
    private List<Termek> findAll() {
        return new ArrayList<>();
    }
    
    /**
     * Sql Termek -repositoryból kiolvassuk az összes (előzőleg már a txt-ből átvett) beszerzett terméket
     * Ez igazából ugyan azt kell, hogy visszaadja, amit a getBeszerzesekTxtbolLista() metódus...
     * @return a beszerzett termékek Terméklistája 
     */
    @Transactional
    public List<Termek> getTermekListaSqlbol() {
        return this.findAll();
    }
    
    /**
     * A beszerzéseket listázzuk a "beszerzes.txt" -ből
     * @return visszaadja a megrendelések listáját.
     */
    public List<Beszerzes> getBeszerzesekTxtbolLista() {
        return this.fileReader.readBeszerzesekFromFile(BESZERZES_FILE);
    }
    
    /**
     * A beszerzésekből (azaz a "beszerzes.txt" -ből) megkeresünk egy tétetelt a neve alapján!
     * @param termekNev ezt a megnevezeésű terméket keressük
     * @return A termék megnevezésére rákeresve visszaadjuk azt, vagy null értéket
     */
    public Beszerzes beszerzestTxtbolKiolvas(String termekNev) {    
        List<Beszerzes> beszerzesek = this.getBeszerzesekTxtbolLista();
        for(Beszerzes b : beszerzesek) {
            if(b.getNev().equals(termekNev)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Egy cikkelem adatbázisba mentese a ermek repository -n keresztül:
     * @param termek
     * @return a mentett és visszaolvasott Termek entitas
     */
    private Termek save(Termek termek) {
        return new Termek();
    }
    /**
     * Adatfelvétel.
     * A lista referencia átadásaal ebben a metódusban végzett módosítások az eredeti listában tükröződnek, 
     * (ezért nincs szükség új listák létrehozására vagy visszatérésére)
     * @param termek a rögzítésre váró termék
     * @param rendelesek az eredeti/mdosult lista refereneciája
     */
    public void saveIfNotExists(Termek termek, List<Termek> rendelesek) {
        if( ! this.repo.findByNevet(termek.getNev()).isPresent()){
            rendelesek.add(termek);
            this.save(termek);
        }
    }
}
