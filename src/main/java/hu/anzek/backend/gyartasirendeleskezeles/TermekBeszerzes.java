package hu.anzek.backend.gyartasirendeleskezeles;

import hu.anzek.backend.model.Beszerzes;
import hu.anzek.backend.service.ProjectAdmin;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

/**
 * A Termékbeszerzés/gyártás vagy (egyéb módon való beszerzés, nem releváns honnan jön egy- egy termék) konzolos adatbeviteli 
 * és adatellenőrzési ÖNÁLLÓ alkalmazása.
 * @author User
 */
public class TermekBeszerzes{

    // Ezeket a kódban fel kell használnod:
    private static final int HETEKSZAMA = 52;
    private static final int INFLACIO = 4;    
    private static final String BESZERZES_FILE = "beszerzes.txt";   
    Scanner scanner = new Scanner(System.in);
    
    // projektadminisztrációs adatok, kérlek ezt ne babráld, mert a vizsga sikerességét kockáztatod
    private static final String TMP_FILE = "projektadmin.tmp";
    public ProjectAdmin projetAdmin = new ProjectAdmin();
      
    // konzolos metódus deklarációk:
    
    /**
     * A megrendelések felvitele metódusa
     * @throws IOException lehetséges I/O hiba a fájl írás esetén
     */
    private void rendelesFelvetel() throws IOException {
        System.out.println("+-----------------------------+");
        System.out.println("| 1. feladat adatok felvitele |");
        System.out.println("+-----------------------------+");  
        List<Beszerzes> beszerzesiLista = null;      
        String BESZERZESI_FILE = "beszerzesi.txt";
        while (true) {
            System.out.print("Termek neve: ");
            String nev = this.scanner.next("nev");
            System.out.print("Mennyisege: ");
            int mennyiseg = Integer.parseInt(this.scanner.next("mennyiseg"));
            System.out.print("Beszerzesenek egysegara: ");
            double ar = Double.parseDouble(this.scanner.next("egysegara"));
            
            // hozzáadjuk a megrendelt termékek listához:
            beszerzesiLista.add(new Beszerzes(nev, mennyiseg, ar));
            
            System.out.print("Tovabbi termek felvetele lesz? (i/n): ");
            if (this.scanner.nextLine().equalsIgnoreCase("n")) {
                break;
            }
        }
        
        // A "PrintWriter(object)" osztály hasonlóan működik mint a "System.out.print[ln](string)",
        // de ameddig a System.out... konzolra irányul, a PrintWriter bármely kimenetre irányítható, akár valamely portra is...
        // Illetve ez az osztály egy objektumot kap paraméterként, 
        // - és ha az sztring, akkor a sztringet, 
        // - ha más típusú objektum, akkor annak a "toString()" metódusát használja az adatok kiírásához!        
        // és itt most a "FileWriter(fájlnév)" objektumot kapja meg paraméterként!
        // tehát abba is írja ki az adatokat:
        try (PrintWriter writer = new PrintWriter(new FileWriter(BESZERZESI_FILE))) {
            for (Beszerzes termek : beszerzesiLista) {
                writer.println(termek.toString());                
            }
        } catch (IOException e) {
            System.err.println("Hiba a fajl irasa kozben!");
            System.exit(0);
        }        
        System.out.println("Beszerzesi lista elmentve a '" + BESZERZESI_FILE + "' nevu fajlba (a projekt-gyoker mappaban kell lennie).");                 
        // projekt-adminisztració:               
        this.projetAdmin.projektAdmin(1);
    }

    /**
     * A konzolos alkalmazás második feladatrésze:
     */
    private void tovabbiFeladatok() throws IOException{        
        // a, Konzolra listázás:
        this.listazBeszerzeseket();        
        // projektadmin:       
        this.projetAdmin.projektAdmin(2);
        
        // b/i, A beszerzés értékének kiszámítása és egy évre előre vetítése:
        double beszerzesErteke = this.szamoljBeszerzesiErteket();
        // a System.out.printf(format, number) egy formázó-metajelöléssel íratja ki a numerikus adatokat (itt pl 2 tizedes pontossággal):
        System.out.printf("Az egy heti beszerzes erteke: %.2f HUF\n", beszerzesErteke );
        // projektadmin:
        this.projetAdmin.projektAdmin(3);        
        // b/ii, Az éves bevétel előkalkulációja:
        double egyEvesTervezettErtek = this.szamoljEgyEvesErteket(beszerzesErteke);
        System.out.printf("A kovetkezo ev tervezett beszerzesi allomanyanak erteke (inflacioval szamitva): %.2f HUF\n", egyEvesTervezettErtek );     
        // projektadmin:
        this.projetAdmin.projektAdmin(4);         
    }
    
    /**
     * a 2/a. feladat: metódus a "beszerzes.txt" tartalmának kilistázásához
     * (ki kellolvasni a txt fájl tartalmát és kiíratni)
     * nincs se bemeneti, se kilépési paramétere
     */
    public static void listazBeszerzeseket() {        
        System.out.println("+-------------------------------+");
        System.out.println("| 2/a. feladat a listakeszites  |");
        System.out.println("+-------------------------------+");
        // implementáld a konzolra listázást (fájlból kiolvasás)
    }

    /**
     * a 2/b. részfeladat: a megrendelés értékének kiszámítása és egy évre előre vetítése
     * (célszerű újra kiolvasni a txt fájl tartalmát!)
     * @return visszaadja "beszerzes.txt" -ben tárolt megrendelésállomány értékét
     */
    public static double szamoljBeszerzesiErteket() {
        System.out.println("+-----------------------------------------+");
        System.out.println("| 2/b/i. feladat a heti beszerzes erteke  |");
        System.out.println("+----------------------------------------+");        
        double osszErtek = 0.0;
        // implementáld a megfelelő, hiányzó kódot:
        // a cél, hogy az osszErtek a megrendelések összesenjét tartalmazza! 
        // Úgy tekijük, hogy mindegy mennyi rendelés van bent, az egy heti beszerzett megrendelésümk!
        return osszErtek;
    }
    
    /**
     * Metódus az egy éves tervezett beszerzési állomány kiszámításához
     * @param hetiErtek a heti "átlag" beszerzési bevétel, amit egy évre tervezett 1 heti alapnak tekintünk!
     * @return az egy év várható beszrzésállomány értéke
     */
    public static double szamoljEgyEvesErteket(double hetiErtek) {
        System.out.println("+------------------------------------+");
        System.out.println("| 2/b/i. feladat a beszerzés erteke  |");
        System.out.println("+------------------------------------+");         
        // számítsd ki az egy évre vetített értéket:
        // Kérlek, használd a konstans HETEKSZAMA változót!:
        double evesErtek = hetiErtek * HETEKSZAMA; 
        // Még az inflációval növelni kell - és add át a RETURN -nal a visszatérő értéket: 
        // Kérlek, használd a konstans INFLACIO változót!:
        return evesErtek * (1 - (double) INFLACIO / 100); 
    }    
        
    /**
     * A vizsga-indító eljárás - nincs vele dolgod!
     * @return kezdhetjük? (Visszavonhatatlanul megezdi a vizsgát)
     */
    private void tudnivalok() throws IOException {        
        if (!Files.exists(Paths.get(TMP_FILE))) {
            System.out.println("///////////////////////////////");
            System.out.println("//   Fontos tudnivalok!      //");
            System.out.println("///////////////////////////////");
            System.out.println("//                           //");
            System.out.println("//   Olvasd el, fogadd meg!  //");
            System.out.println("///////////////////////////////");
            System.out.println("1, csak a specifikacioban eloirt dolgokkal foglalkozz!");
            System.out.println("2, kovesd a JAVA -konvenciokat (elnevezesek, konstansok, szokasok, mappaszerkezetek, stb)!");
            System.out.println("3, kovesd a CC elveket!");
            System.out.println("4, a modositast, bovitest, stb, roviden dokumentald: pl a javitott sor folott '//' jelekkel, vagy a JAVADOC '/** * */' konvencioval!");
            System.out.println("5, hasznalhatsz batran magyar elnevezeseket de az ekezeteket keruld el!");
            System.out.println("6, A projekt-adminisztracios reszt ne \"babrald\" - mert ha nem lesz ervenyes adat benne a vizsgad ervenytelen lesz!");
            System.out.println("Fontos: ertekelest kell adnunk OOP-ről, BackEnd-ről, az SQL-ről is onallo ertekeles keszul majd!");
            System.out.println("");            
            System.out.println("A feladataidrol:");
            System.out.println("1,  a projekten belul ket kulonallo kodreszlet lesz: a, egy konzol alkalmazas; b, egy Springes alkalmazas");
            System.out.println("    ezek egy feladatot alkotva valositjak meg a projekedet!");
            System.out.println("    Nem hagyhato egyik sem el!");
            System.out.println("    Ha nem keszultel el az elsovel idore,tolds fel a minta szerint a txt fajlt, es ne add fel, a masodik reszt igy is megoldhato elso resz hianyaban!");
            System.out.println("1/a) OOP ez a konzolalkamazas, ez inditja a vizsgaprojektet.");
            System.out.println("1/b) Backend-fejlesztes a Springes kornyezet. Ez a projekt masik resze lesz, amely kiterjeszti a projektet");
            System.out.println("2, SQL-A masodik feladatod egy MySQL script csatolasa lesz. Ezeket a feladatokat a \"VizsgaSpecifikacio\" tartalmazza!");
            System.out.println("");
            System.out.print("A feladatot elkezdhetjuk? (i/n): ");

            // projekt admin:
            if (scanner.nextLine().equalsIgnoreCase("i")) {
                this.projetAdmin.projektAdmin(0);
            }
        }else{
            this.projetAdmin.projektAdmin(5);
        }
    }    

    /**
     * . A rendszer beléptetési pont (a fő metódus)
     * @param args nincs argumentumlista
     * @throws java.io.IOException lehetséges I/O hiba - fájl írás/olbasás esetén!
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!\nEz a backend programozoi vizsga a 'Gyartas rendeles kezelese' cimu projekt!");        
        TermekBeszerzes gyrk = new TermekBeszerzes();
        gyrk.tudnivalok();              
        gyrk.rendelesFelvetel();
        gyrk.tovabbiFeladatok();
        
    }    
}
