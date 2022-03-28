/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import TelegramAPI.Invia;
import java.io.IOException;
import java.net.MalformedURLException;
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
public class Pubblicita {
    CittaUtenti u;
    String txt;
    String citta;
    Double raggio;

    public Pubblicita(String txt, String citta, Double raggio) throws IOException {
        u=CittaUtenti.getUtenti();
        this.txt = txt;
        this.citta = citta;
        this.raggio = raggio;
    }
    
    public void invia() throws IOException, ParserConfigurationException, SAXException{
        Invia a=new Invia("https://api.telegram.org/bot5244355970:AAHGu73Caflh9dcF6S_hOjVjG_YYOSqEsnE");
        u.utenti=u.g.getUtenti();
        u.vis();
        double lat=latitude(citta);
        double lon=longitude(citta);
        System.out.println("lat "+lat+" lon:"+lon+"\n");
        for (int i = 0; i < u.utenti.size(); i++) {
            Double dist=u.utenti.get(i).GetDistanceKilometers(lat,lon);
            System.out.println("dist "+dist+" raggio "+raggio);
            if(dist<=raggio){
                
                System.out.println("  invia user:"+u.utenti.get(i).username+" distanza "+dist);               
                a.inviaChatId(u.utenti.get(i).id, txt);
            }else{
               
                System.out.println("  non invia user:"+u.utenti.get(i).username+" distanza "+dist);   
                
            }
                 
        }
    }
    
     public double latitude(String s) throws MalformedURLException, ParserConfigurationException, SAXException, IOException {      
        Element e = getElement("https://nominatim.openstreetmap.org/search?q=" + s + "&format=xml&addressdetails=1");
        Node place = e.getElementsByTagName("place").item(0);
        Element p=(Element)place;
        return Double.parseDouble(p.getAttribute("lat"));
    }

    public double longitude(String s) throws MalformedURLException, ParserConfigurationException, SAXException, IOException {       
        Element e = getElement("https://nominatim.openstreetmap.org/search?q=" + s + "&format=xml&addressdetails=1");
        Node place = e.getElementsByTagName("place").item(0);
        Element p=(Element)place;
        return Double.parseDouble(p.getAttribute("lon"));
    }

    public Element getElement(String url) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        var document = builder.parse(url);
        return document.getDocumentElement();
    }
    
    
    
    
}
