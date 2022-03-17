/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author gero
 */
public class Gestione {

    String path;

    public Gestione(String path) throws IOException {
        this.path = path;
        File f = new File(path);
        if (!f.exists()) {
            f.createNewFile();
        }
    }

    public ArrayList<Utente> getUtenti() throws FileNotFoundException, IOException {
        ArrayList<Utente> listUtenti = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = "";
        while ((line = br.readLine()) != null) //returns a Boolean value  
        {
            listUtenti.add(new Utente(line));
        }
        return listUtenti;
    }

    public void svuotaFile() throws IOException {
        FileWriter writer = new FileWriter(path);
        writer.write("");
        writer.flush();
        writer.close();
    }

    public void aggiornaFile(ArrayList<Utente> u) throws IOException {
        svuotaFile();
        FileWriter fw = new FileWriter(path, true);
        for (Utente ut : u) {
            fw.append(ut.toCSV());
        }
        fw.flush();
        fw.close();
    }

    public void aggiungi(Utente u) throws IOException {

        FileWriter writer = new FileWriter(path, true);
        writer.append(u.toCSV());
        writer.flush();
        writer.close();
    }

}
