/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import TelegramAPI.Messaggio;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.tools.DocumentationTool.Location;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

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
        String[] tmp = s.split(";");
        this.username = tmp[0];
        this.Citta = tmp[1];
        this.id = Long.parseLong(tmp[2]);
        this.latitudine = Double.parseDouble(tmp[3]);
        this.longitudine = Double.parseDouble(tmp[4]);
    }

    public Double GetDistanceKilometers(double lat,double lon) throws ParserConfigurationException, SAXException, IOException {

        double R = 6371.0088;
       
        Double lat1 = lat;
        Double lon1 = lon;  
        
        double latDistance = Math.abs(Math.toRadians((latitudine - lat1)));
        double lonDistance = Math.abs(Math.toRadians((longitudine - lon1)));
        lat1 = Math.toRadians(lat1);
        latitudine = Math.toRadians(latitudine);
        double a = haversin(latDistance) + Math.cos(lat1) * Math.cos(latitudine) * haversin(lonDistance);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return Math.abs(R * c);
    }

   

    private static double haversin(double val) {
        return Math.pow(Math.sin(val / 2), 2);
    }

    public String toCSV() {
        return username + ";" + Citta + ";" + id + ";" + latitudine + ";" + longitudine + ";\n";
    }

}
