/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelegramAPI;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author gero
 */
public class Invia {
    Messaggi m;
    String url;
    public Invia(String s){
        m=Messaggi.getMessaggi();
        url=s;
    }
    public void inviaLocation(Double latitudine,Double longitudine) throws MalformedURLException, IOException{
        Long id=m.getM().get(m.getM().size()-1).c.id; 
        URL Url = new URL(url+"/sendLocation?chat_id="+id+"&latitude="+latitudine+"&longitude="+longitudine);
        Url.openStream();
    }
    
    public void inviaChatId(Long id,String messaggio) throws MalformedURLException, IOException{
        URL Url = new URL(url+"/sendMessage?chat_id="+id+"&text="+messaggio);
        Url.openStream();
    }
    
    public void inviaMessaggioUltimoArrivato(String messaggio) throws IOException{
        Long id=m.getM().get(m.getM().size()-1).c.id;      
        URL Url = new URL(url+"/sendMessage?chat_id="+id+"&text="+messaggio);
        Url.openStream();
        
    }
}
