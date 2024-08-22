/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.service;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.springframework.stereotype.Service;


/**
 *
 * @author User
 */
@Service
public class ProjectAdmin {
    // ezek a mezők tprojektadminisztrációs mezők, 
    // ne foglalkozz velük, ne változtasd, és nem kell használnod sehol sem:
    private static LocalDateTime projectNow = LocalDateTime.now();    
    private static final String TMP_FILE = "projektadmin.tmp";
    
    public ProjectAdmin() {
    }
    
    /**
     * projektadminisztrációs metódus (kérlek ezt nem babráld meg)!
     * @param logIndex
     * @throws java.io.IOException
     */
    public static void projektAdmin(int logIndex) throws IOException {
        String honnan = "projekt-indítás";
        switch (logIndex){
            case 1 -> honnan = "1, konzolos adatbeviteli részfeladat :";
            case 2 -> honnan = "2/a, konzolos adatok listazasa :"; 
            case 3 -> honnan = "2/b/i, konzolos megrendelés értéke :"; 
            case 4 -> honnan = "2/b/ii, konzolos megrendelés értéke :";
            case 5 -> honnan = "0-folytatáshoz: újabb projektindítás!";
            case 10 -> honnan = "10, Springes környezet projektindítása!";
            case 11 -> honnan = "11, Beszerzések forrásfájl adatbázisba átemelése!";
            case 12 -> honnan = "12, Beszerzési lista készítése!";
            case 13 -> honnan = "13, egy konkrét beszerzés megjelenítése!";
            case 14 -> honnan = "14, megrendelés értékesítésre!";
            case 15 -> honnan = "15, az összes eladás megjelenítése!";
            case 16 -> honnan = "16, egy cikkből, a még eladhato mennyiség megjeneítése!";
            case 17 -> honnan = "17, a terméklista megjenítése!";
            case 18 -> honnan = "18, a 'TermekTest' tesztosztály sikeres futtatása!";
            case 19 -> honnan = "19, a 'BeszerzesControllerTest' tesztosztály sikeres futtatása!";
            case 20 -> honnan = "20, a 'TermekServiceSpringBootTest' tesztosztály sikeres futtatása!";     
            case 21 -> honnan = "21, a 'ErtekesitesServiceJUnitTest' tesztosztály sikeres futtatása!"; 
            default -> honnan = "0-projektindítás...";
        }        

        String s = "";
        if (Files.exists(Paths.get(TMP_FILE ))) {
            s = new String(Files.readAllBytes(Paths.get(TMP_FILE)));
            projectNow = LocalDateTime.parse(s.substring(0,19));
        }    
        if (projectNow == null) projectNow = LocalDateTime.now();
        long projekt = ChronoUnit.MINUTES.between(projectNow, LocalDateTime.now());        
        try (PrintWriter tmpWriter = new PrintWriter(new FileWriter(TMP_FILE))) {
            tmpWriter.println(s + projectNow.toString() + "\n" + honnan + projekt + "\n\n");      
        } catch (IOException e) {
            System.err.println("Projek-adminisztracios hiba!");
        }
    }
}

    
