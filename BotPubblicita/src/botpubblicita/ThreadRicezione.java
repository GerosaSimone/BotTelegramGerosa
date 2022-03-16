/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import TelegramAPI.Invia;
import TelegramAPI.Messaggi;
import TelegramAPI.Messaggio;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ThreadRicezione extends Thread {

    Invia a;
    CittaUtenti c;

    public ThreadRicezione() {
        c = CittaUtenti.getUtenti();
        a = new Invia("https://api.telegram.org/bot5244355970:AAHGu73Caflh9dcF6S_hOjVjG_YYOSqEsnE");
    }

    @Override
    public void run() {
        Messaggio vecchio = new Messaggio();
        while (true) {
            try {
                Messaggi.getMessaggi().aggiorna("https://api.telegram.org/bot5244355970:AAHGu73Caflh9dcF6S_hOjVjG_YYOSqEsnE/getupdates");
                Messaggio m = Messaggi.getMessaggi().getM().get(Messaggi.getMessaggi().getM().size() - 1);
                System.out.println("nuova: "+m.getText());
                System.out.println("vecchia: "+vecchio.getText());
                if (!m.getText().equals(vecchio.getText())) {
                    Controlla(m);
                }

                vecchio = m;
                Thread.sleep(500);
            } catch (IOException ex) {
                Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void Controlla(Messaggio mess) throws ParserConfigurationException, SAXException, IOException {
        System.out.println("controlla");
        if (mess.getText().length() > 6) {
            if (mess.getText().substring(0, 7).equals("/citta ")) {
                String citta = mess.getText().substring(7);
                PrimaCitta(citta);

            }
        } else if (mess.getText().length() > 2) {

            if (mess.getText().substring(0, 2).toLowerCase().equals("yes")) {

                

            } else if (mess.getText().substring(0, 1).toLowerCase().equals("no")) {

            }
        }
    }

    public void PrimaCitta(String s) throws MalformedURLException, ParserConfigurationException, SAXException, IOException {
        System.out.println("stampa citta");
        Element e = getElement("https://nominatim.openstreetmap.org/search?q=" + s + "&format=xml&addressdetails=1");
        Node place = e.getElementsByTagName("place").item(0);
        try{
        a.inviaLocation(Double.parseDouble(place.getAttributes().getNamedItem("lat").getNodeValue()), Double.parseDouble(place.getAttributes().getNamedItem("lon").getNodeValue()));
        }catch(Exception b){
            a.inviaMessaggioUltimoArrivato("Citta inesistente");
        }
    }

    public Element getElement(String url) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        var document = builder.parse(url);
        return document.getDocumentElement();
    }
}
