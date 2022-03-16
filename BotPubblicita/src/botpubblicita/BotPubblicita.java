/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botpubblicita;

import TelegramAPI.Invia;
import TelegramAPI.Messaggi;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author gerosa_simone
 */
public class BotPubblicita {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        /*Invia a = new Invia("https://api.telegram.org/bot5244355970:AAHGu73Caflh9dcF6S_hOjVjG_YYOSqEsnE");
        Messaggi m = Messaggi.getMessaggi();
        m.aggiorna("https://api.telegram.org/bot5244355970:AAHGu73Caflh9dcF6S_hOjVjG_YYOSqEsnE/getupdates");

        boolean b=true;
        Scanner s = new Scanner(System.in);
        while (b) {
            System.out.println("Scegliere:\n 1-Visualizza tutti i messaggi\n 2-Invia messaggio all'ultimo utente che ha scritto al bot\n 3-Termina Programma\n");
            int scelta = s.nextInt();
            switch (scelta) {
                case 1:
                    m.aggiorna("https://api.telegram.org/bot5244355970:AAHGu73Caflh9dcF6S_hOjVjG_YYOSqEsnE/getupdates");
                    System.out.println(m.to_String());
                    break;
                case 2:
                    String testo = s.nextLine();
                    System.out.println("inserire messaggio da inviare: ");
                    testo = s.nextLine();
                    a.inviaMessaggioUltimoArrivato(testo);
                    break;
                case 3:
                    b=false;
                    break;        
            }
        }*/
        ThreadRicezione t=new ThreadRicezione();
        t.start();
        

        // TODO code application logic here
        
      
     
        
    }

}
