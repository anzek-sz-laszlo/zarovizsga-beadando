/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hu.anzek.backend.service;


import hu.anzek.backend.model.Beszerzes;
import hu.anzek.backend.model.Termek;
import hu.anzek.backend.repository.TermekRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


/**
 *
 * @author User
 */
@SpringBootTest
public class TermekServiceSpringBootTest {
   
    @Autowired
    private TermekService termekService;
  
    @MockBean  
    private TermekRepository termekRepository;

    public TermekServiceSpringBootTest() {
    }
    
    @MockBean
    private BeszerzesFileReader fileReader;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test of beszerzestTxtbolKiolvas method, of class TermekService.
     * @throws java.io.IOException
     */
    @Test
    public void testBeszerzestTxtbolKiolvas() throws IOException {
        System.out.println("beszerzestTxtbolKiolvas : egy konkret beszerzes adatainak kiolvasasat teszteljuk!");
        // előkészítés:
            // írd meg kérlek
        List<Beszerzes> beszerzesek = Collections.singletonList(beszerzes);
        // mockolás:
        when(this.fileReader.readBeszerzesekFromFile("beszerzes.txt")).thenReturn(beszerzesek);
        // futtatás:
            // íd meg kérlek
        // ellenőrzés:
            // írd meg kérlek
        // hibára futtatás:
        // fail("A tesztben egy konkret beszerzes adatainak kiolvasasat teszteltuk, de hibára fut!");
        
        // admin:
        ProjectAdmin admin = new ProjectAdmin();
        admin.projektAdmin(20);        
    }

    /**
     * Test of beszerzestSqlbe method, of class TermekService.
     * @throws java.io.IOException
     */
    @Test
    public void testBeszerzestSqlbe() throws IOException {
        System.out.println("beszerzestSqlbe : a \"beszrzes.txt\" atemelese az SQL adatbazisba tesztje!");    
        // előkészítés:
        List<Beszerzes> beszerzesek = Collections.singletonList(new Beszerzes("elso termek", 100, 50.55));        
        Termek termek = new Termek(0L, "elso termek", 100,0, 50.55);                
        List<Termek> termekLista = new ArrayList<>();
        // mockolás:
        when(this.fileReader.readBeszerzesekFromFile("beszerzes.txt")).thenReturn(beszerzesek);
            // az alábbi mockolást vedd ki a megjegyzésből és helyettesitsd be a "vajon_mit_hivunk_mockolni( mi_a_paramétere )" -t!
            // az ugye a feladata, hogy csináljon úgy, mintha még nem létezne a termék, hogy rögzíteni tudja (kamu rögzítéssel)!
        // when(Vajon_mit_hivunk(mivel)).thenReturn(Optional.empty());
        // futtatás:
        termekLista = this.termekService.beszerzestSqlbe();
        // ellenőrzések:
        assertEquals(termekLista.get(0).getNev(), beszerzesek.get(0).getNev());
        assertEquals(0, termekLista.size()); // <-- add meg mi lesz az elvárt listaméret az előkészítés alapján?
        assertEquals(termek.getNev(), null); // <-- add meg mi a helyes aktuális érték (mit, hogy olvasunk ittt ki)
        assertEquals(0, termekLista.get(0).getMennyiseg()); // <-- add meg mi az elvárt adatérték ebben, mit, hogy olvasunk ki
        assertEquals(termek.getAr(), termekLista.get(0).getAr());        
        // hibára futtatás:
        // fail("a \"beszrzes.txt\" atemelese az SQL adatbazisba teszteltuk, de hibara futott!");
        
        // admin:
        ProjectAdmin admin = new ProjectAdmin();
        admin.projektAdmin(20);        
    }    
}
