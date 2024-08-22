/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.service;


import hu.anzek.backend.model.ErtekesitesAdat;
import hu.anzek.backend.model.MegrendelesEladasra;
import hu.anzek.backend.model.Termek;
import hu.anzek.backend.repository.ErtekesitesRepository;
import hu.anzek.backend.repository.TermekRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;


/**
 * Az értékesítési problémák üzleti logikája
 * @author User
 */
public class ErtekesitesService {
    
    private final ErtekesitesRepository ertekesitesRepository;
    private final TermekRepository termekRepository;
    private final TermekService termekService;
    
    /**
     * konstruktor, a reposiory interfész injektálással
     * @param ertekesitesRepository 
     * @param termekService 
     */
    public ErtekesitesService(ErtekesitesRepository ertekesitesRepository,
                              TermekRepository termekRepository,
                              TermekService termekService) {
        this.ertekesitesRepository = ertekesitesRepository;
        this.termekRepository = termekRepository;
        this.termekService = termekService;
    }
    
    /**
     * ErtekesitesAdat -entitást ment a reopository -ján keresztül:
     * @param adat az adat
     * @return visszaadja a kimentett és visszaolvasott entitást
     */
    private ErtekesitesAdat save(ErtekesitesAdat adat) {
        return new ErtekesitesAdat();
    }
    /**
     * Termek -entitást ment a reopository -ján keresztül:
     * @param adat az adat
     * @return visszaadja a kimentett és visszaolvasott entitást
     */
    private Termek save(Termek adat) {
        return new Termek();
    }
    
    /**
     * Értékesítési adat elmentése
     * @param adat az értékesítési adat
     * @return az elmentett adatot visszaolvasva (azt egy ID -vel kiegészítve) adjuk vissza, ha sikeres volt a mentés, ha nem akkor null értéket
     */
    @Transactional
    public ErtekesitesAdat ertekesitesRogzitese(ErtekesitesAdat adat) {
        if(termekEladottMennyisegetNovelj(adat)) {
            this.save(adat);
        }
        return adat;
    }

    /**
     * A Termek entitasban az eladott emnyiség növelése, és ennek sikere, az, hogy magát az eladást is letároljuk!
     * @param adat az értékesítési adat
     * @return sikerült a termék eladási adatot növelni, vagy sem?
     */
    @Transactional
    public boolean termekEladottMennyisegetNovelj(ErtekesitesAdat adat) {
        Termek termek = this.termekRepository.findByNevet(adat.getTermekNev()).orElse(null);
        if(termek != null) {
            termek.setEladva( termek.getEladva() - adat.getEladottMennyiseg() );
            this.save(termek);            
            return true;
        }
        return false;
    }
    
    /**
     * Az összes ErtekesitesAdat (eladások) listája az sql-ből a repon kerezstül:
     * @return a Lista
     */
    private List<ErtekesitesAdat> findAll() {
        return Collections.singletonList(new ErtekesitesAdat());
    }
    
    /**
     * Értékesítési adatok lekérdezése
     * @return az összes értékesítés listája, vagy üres lista!
     */
    @Transactional
    public List<ErtekesitesAdat> getAllErtekesites() {
        return this.findAll();
    }    

    /**
     * Kiszolgálható-e a megrendelni kívánt tétel?
     * Nyilván akkor van fedezet, ha több van még, mint amit ki akarunk adni!
     * @param eladasra a bejövő kérés tartalma
     * @return igen/nem (true/false)
     */
    @Transactional
    public boolean kiszolgalhatoE(MegrendelesEladasra eladasra) {
        Termek termek = this.termekRepository.findByNevet(eladasra.getTermekNev()).orElse(null);
        if ( termek != null) {            
            if( (termek.getMennyiseg() - termek.getEladva()) <= eladasra.getRendeltMennyiseg() ) {
                return true;
            }
        }        
        return false;
    }

    /**
     * Mennyi eladható mennyiség áll rendelkezésre a kívánt tételből?
     * A termek entitas beszrzett mennyiségéből kivonjuk a már eladott mennyiséget.
     * @param termekNev a bejövő kérés a cikk megnevezése
     * @return a még kiadható mennyiség, vagy nulla
     */
    @Transactional
    public Integer mennyiEladhatoVanBelole(String termekNev) {
        Termek termek = this.termekRepository.findByNevet(termekNev).orElse(null);
        if ( termek != null) {            
            return termek.getMennyiseg();
        }        
        return 0;
    }    

    /**
     * Itt egy egyszerű mapper az entitás és a DTO között
     * @param ertAdat az entitás
     * @return visszaadjuk a DTO -t
     */
    public MegrendelesEladasra mapper(ErtekesitesAdat ertAdat) {
        return new MegrendelesEladasra(ertAdat.getTermekNev(), ertAdat.getEladottMennyiseg());
    }

    /**
     * Entitás listakollekcióból DTO listát készít
     * @param allErtekesites az entitás listakollekció
     * @return a DTO lsitakollekció
     */
    public List<MegrendelesEladasra> mapperToList(List<ErtekesitesAdat> allErtekesites) {
        List<MegrendelesEladasra> entityLista = new ArrayList<>();
        for(ErtekesitesAdat ertAdat : allErtekesites) {
            entityLista.add(this.mapper(ertAdat));
        }        
        return entityLista;
    }
}
