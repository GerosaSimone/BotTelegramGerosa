/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import TelegramAPI.Messaggi;
import TelegramAPI.Messaggio;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author gero
 */
public class CittaUtenti {
    ArrayList<Utente> utenti;
    Gestione g;
   
     private static CittaUtenti istanza = null;

    // Metodo della classe impiegato per accedere al singleton
    public static synchronized CittaUtenti getUtenti() throws IOException {
        if (istanza == null) {
            istanza = new CittaUtenti();
        }
        return istanza;
    }
    //Il costruttore private impedisce l'istanza di oggetti da parte di classi esterne
    CittaUtenti() throws IOException {
        g=new Gestione("utenti.csv");
        utenti = new ArrayList<Utente>();
        utenti=g.getUtenti();
    }
    public void aggiorna(Long id, String citta, double lat,double lon,String username) throws IOException{
        boolean trovato=false;
        int pos=-1;
        Utente u=new Utente(username,citta,id,lat,lon);
        
        for (int i = 0; i < utenti.size(); i++) {
            
            if(utenti.get(i).id.equals(u.id)){
                trovato=true;
                pos=i;
            }
        }
        if(trovato){
            utenti.remove(pos);
            utenti.add(u);
            g.aggiornaFile(utenti);
        }else{
            utenti.add(u);
            g.aggiungi(u);
        }
    }
    public void vis(){
    for (int i = 0; i < utenti.size(); i++) {
            
            System.out.println(utenti.get(i).toCSV()+"\n");
            
        }
}

   
    
   
}
