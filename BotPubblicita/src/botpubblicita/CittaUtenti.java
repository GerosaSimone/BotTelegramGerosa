/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import TelegramAPI.Messaggi;
import TelegramAPI.Messaggio;
import java.util.ArrayList;

/**
 *
 * @author gero
 */
public class CittaUtenti {
    ArrayList<Utente> utenti;
    
     private static CittaUtenti istanza = null;

   

    // Metodo della classe impiegato per accedere al singleton
    public static synchronized CittaUtenti getUtenti() {
        if (istanza == null) {
            istanza = new CittaUtenti();
        }
        return istanza;
    }
    //Il costruttore private impedisce l'istanza di oggetti da parte di classi esterne
    private CittaUtenti() {
        utenti = new ArrayList<Utente>();
    }
    
    void aggiorna(){
        Messaggi m=Messaggi.getMessaggi();
        for (int i = 0; i < m.getM().size(); i++) {
            if(m.getM().get(i).getText().substring(0,7)=="/citta ") {
                
            }
            } 
    }
}
