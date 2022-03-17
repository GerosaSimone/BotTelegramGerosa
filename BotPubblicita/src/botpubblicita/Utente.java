/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

/**
 *
 * @author gero
 */
public class Utente {

    String username;
    String Citta;
    Long id;
    Double latitudine;
    Double longitudine;

    public Utente(String user, String Citta, Long id, Double latitudine, Double longitudine) {
        this.username = user;
        this.Citta = Citta;
        this.id = id;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public Utente(String s) {
        String[] tmp=s.split(";");
        this.username = tmp[0];
        this.Citta = tmp[1];
        this.id = Long.parseLong(tmp[2]);
        this.latitudine = Double.parseDouble(tmp[3]);
        this.longitudine = Double.parseDouble(tmp[4]);
    }

    public String toCSV() {
        return username+";"+Citta+";"+id+";"+latitudine+";"+longitudine+";\n";
    }

    
}
