/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hu.anzek.backend.service;


import hu.anzek.backend.model.Beszerzes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;


/**
 *
 * @author User
 */
@Service
public class BeszerzesFileReader {

    public List<Beszerzes> readBeszerzesekFromFile(String fileName) {
        List<Beszerzes> rendelesek = new ArrayList<>();    
        fileName = "beszerzesi.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                rendelesek.add(new Beszerzes(parts[0], Integer.parseInt(parts[1]), Double.parseDouble(parts[2])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // projektadminisztráció:
        return rendelesek;
    }    
}
