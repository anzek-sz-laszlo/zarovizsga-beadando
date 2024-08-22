/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package hu.anzek.backend.gyartasirendeleskezeles;


import hu.anzek.backend.controller.BeszerzesController;
import hu.anzek.backend.model.Beszerzes;
import hu.anzek.backend.service.ProjectAdmin;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 
 * @author User
 */
@WebMvcTest(BeszerzesController.class)
public class BeszerzesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeszerzesController termekController;

    @Test
    public void testGetBeszerzesesek() throws Exception {
        // Web MVC Mockito teszt előkészítése:
        List<Beszerzes> rendelesek = new ArrayList<>();
        rendelesek.add(new Beszerzes("Alma", 10, 2.5));
        rendelesek.add(new Beszerzes("Banán", 20, 3.0));
        // beállítás (hogy ha az adott metódus által kezelt erőforrás és http kérés érkezik),
        // akkor mi legyen az előre megadott reakció:
        given(termekController.getBeszerzesek()).willReturn(rendelesek);
        // a erőforrás végpont megcímzése és a http ige (kérés) elküldése a WebMVC teszthez:
        this.mockMvc.perform(get("/beszerzesek/lista"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].nev").value("Olma"))
                    .andExpect(jsonPath("$[0].mennyiseg").value(10))
                    .andExpect(jsonPath("$[0].ar").value(2.5))
                    .andExpect(jsonPath("$[1].nev").value("Banán"))
                    .andExpect(jsonPath("$[1].mennyiseg").value(20))
                    .andExpect(jsonPath("$[1].ar").value(3.0));
        
        // admin:
        ProjectAdmin admin = new ProjectAdmin();
        admin.projektAdmin(19);
    }
}

