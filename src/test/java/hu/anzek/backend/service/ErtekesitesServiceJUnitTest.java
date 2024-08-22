/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hu.anzek.backend.service;


import hu.anzek.backend.model.ErtekesitesAdat;
import hu.anzek.backend.model.MegrendelesEladasra;
import hu.anzek.backend.model.Termek;
import hu.anzek.backend.repository.ErtekesitesRepository;
import hu.anzek.backend.repository.TermekRepository;
import java.io.IOException;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 *
 * @author User
 */
public class ErtekesitesServiceJUnitTest {
    
    @Mock
    private ErtekesitesRepository ertekesitesRepository;

    @Mock
    private TermekRepository termekRepository;

    @InjectMocks
    private ErtekesitesService ertekesitesService;

    @BeforeEach
    public void setUp() {
         // nyisd meg a mockito -t szabályosan!
    }

    @Test
    public void testErtekesitesRogzitese() throws IOException {
        // Előkészítés
        ErtekesitesAdat ertekesitesAdat = new ErtekesitesAdat("elso termek", 50, 1300.00);
        Termek termek = new Termek(1L, "elso termek", 100, 10, 1250.25);
        when(this.termekRepository.findByNevet("elso termek")).thenReturn(Optional.of(termek));
        when(this.ertekesitesRepository.save(ertekesitesAdat)).thenReturn(ertekesitesAdat);
        // Futtatás
        ErtekesitesAdat result = null; // <- add meg a nul helyett azt amit ebben a tesztben futtatnunk kell!
        // Ellenőrzés
        Assertions.assertNotNull(result);
        Assertions.assertEquals("elso termek", result.getTermekNev());
        Assertions.assertEquals(60, termek.getEladva()); // Az eredeti 10-hez hozzáadunk 50-et
        verify(this.termekRepository, times(1)).save(termek);
        verify(this.ertekesitesRepository, times(1)).save(ertekesitesAdat);
        
        // admin:
        ProjectAdmin admin = new ProjectAdmin();
        admin.projektAdmin(21);          
    }

    @Test
    public void testKiszolgalhatoE() throws IOException {
        // Előkészítés
        MegrendelesEladasra megrendeles = new MegrendelesEladasra("elso termek", 20);
        Termek termek = new Termek(1L, "elso termek", 100, 70, 1250.25);
        when(this.termekRepository.findByNevet("elso termek")).thenReturn(Optional.of(termek));
        // Futtatás
        boolean result = false; // <- add meg a false helyett azt amit ebben a tesztben futtatnunk kell!
        // Ellenőrzés ->  100 - 70 >= 20
        Assertions.assertTrue(result);
        
        // admin:
        ProjectAdmin admin = new ProjectAdmin();
        admin.projektAdmin(21);            
    }

    @Test
    public void testMennyiEladhatoVanBelole() throws IOException {
        // Előkészítés
        Termek termek = new Termek(1L, "elso termek", 100, 30, 1250.25);
        when(termekRepository.findByNevet("elso termek")).thenReturn(Optional.of(termek));
        // Futtatás
        int eladhato = 0; // <- add meg a nulla helyett azt amit ebben a tesztben futtatnunk kell!
        // Ellenőrzés:  100 - 30 = 70
        Assertions.assertEquals(70, eladhato);
        
        // admin:
        ProjectAdmin admin = new ProjectAdmin();
        admin.projektAdmin(21);            
    }

    @Test
    public void testMennyiEladhatoVanBelole_TermekNemLetezik() throws IOException {
        // Előkészítés
        when(this.termekRepository.findByNevet("nemletezo termek")).thenReturn(Optional.empty());
        // Futtatás
        int eladhato = 0; // <- add meg a 0 helyett azt amit ebben a tesztben futtatnunk kell!
        // Ellenőrzés
        Assertions.assertEquals(0, eladhato);
        
        // admin:
        ProjectAdmin admin = new ProjectAdmin();
        admin.projektAdmin(21);            
    }    
}
