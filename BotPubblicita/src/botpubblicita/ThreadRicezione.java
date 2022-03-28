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

    public ThreadRicezione() throws IOException {
        c = CittaUtenti.getUtenti();
        a = new Invia("https://api.telegram.org/bot5244355970:AAHGu73Caflh9dcF6S_hOjVjG_YYOSqEsnE");
    }

    @Override
    public void run() {
        while (true) {
            try {
                Messaggi.getMessaggi().aggiorna("https://api.telegram.org/bot5244355970:AAHGu73Caflh9dcF6S_hOjVjG_YYOSqEsnE/getupdates");
            } catch (IOException ex) {
                Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (Messaggi.getMessaggi().getM().size() != 0) {

                Long offset = Messaggi.getMessaggi().getM().get(Messaggi.getMessaggi().getM().size() - 1).getUpdate_id() + 1;
                System.out.println("Offset:"+offset);
                for (int i = 0; i < Messaggi.getMessaggi().getM().size(); i++) {
                        Messaggio m = Messaggi.getMessaggi().getM().get(i);
                    try {
                        Controlla(m);
                    } catch (ParserConfigurationException ex) {
                        Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SAXException ex) {
                        Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }
                try {
                    Messaggi.getMessaggi().aggiorna("https://api.telegram.org/bot5244355970:AAHGu73Caflh9dcF6S_hOjVjG_YYOSqEsnE/getupdates?offset=" + (offset));                 
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(ThreadRicezione.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }

    public void Controlla(Messaggio mess) throws ParserConfigurationException, SAXException, IOException {
        System.out.println("controlla");
        if (mess.getText().length() > 6) {
            if (mess.getText().substring(0, 7).equals("/citta ")) {
                String citta = mess.getText().substring(7);
                PrimaCitta(citta, mess);

            }
        }
    }

    public void PrimaCitta(String s, Messaggio m) throws MalformedURLException, ParserConfigurationException, SAXException, IOException {
        c = CittaUtenti.getUtenti();       
        Element e = getElement("https://nominatim.openstreetmap.org/search?q=" + s + "&format=xml&addressdetails=1");
        Node place = e.getElementsByTagName("place").item(0);
        try {
            a.inviaLocation(Double.parseDouble(place.getAttributes().getNamedItem("lat").getNodeValue()), Double.parseDouble(place.getAttributes().getNamedItem("lon").getNodeValue()));
            c.aggiorna(m.getF().id, s, Double.parseDouble(place.getAttributes().getNamedItem("lat").getNodeValue()), Double.parseDouble(place.getAttributes().getNamedItem("lon").getNodeValue()), m.getC().getUser());
        } catch (Exception b) {
            a.inviaMessaggioUltimoArrivato("Error");
        }
    }

    public Element getElement(String url) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        var document = builder.parse(url);
        return document.getDocumentElement();
    }
}
