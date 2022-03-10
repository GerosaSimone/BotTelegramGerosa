/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelegramAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author gerosa_simone
 */
public class Messaggi {
    
    ArrayList<Messaggio> messaggi;
    private static Messaggi istanza = null;

   

    // Metodo della classe impiegato per accedere al singleton
    public static synchronized Messaggi getMessaggi() {
        if (istanza == null) {
            istanza = new Messaggi();
        }
        return istanza;
    }
    //Il costruttore private impedisce l'istanza di oggetti da parte di classi esterne
    private Messaggi() {
        messaggi = new ArrayList<Messaggio>();
    }
public ArrayList<Messaggio> getM(){
    return messaggi;
}
    
    // Metodo della classe impiegato per accedere al singleton
  
    
    public void aggiorna(String url) throws MalformedURLException, IOException {
        
        String s = "";
        URL u;
        u = new URL(url);
        BufferedReader reader = new BufferedReader(new InputStreamReader(u.openStream()));
        String line = null;
        while ((line = reader.readLine()) != null) {
            s += line + "\n";
        }
        reader.close();
        this.GetUpdates(s);
    }
    
    public void GetUpdates(String s) throws MalformedURLException, IOException {
        messaggi = new ArrayList<Messaggio>();
        JSONObject obj = new JSONObject(s);
        JSONArray arr = obj.getJSONArray("result");
        
        for (int i = 0; i < arr.length(); i++) {
            Messaggio m = new Messaggio(arr.getJSONObject(i));
            messaggi.add(m);          
        }
    }
    
    public String to_String(){
        String s="";
        
        for (int i = 0; i < messaggi.size(); i++) {
            s+=messaggi.get(i).to_String();
            s+="-------------------------------------------------------------------------------------------------------------------\n";
        }
        
        return s;
    }
    
}
