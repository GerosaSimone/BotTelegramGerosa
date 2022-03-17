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
   
        ThreadRicezione t=new ThreadRicezione();
        t.start();
        

        // TODO code application logic here
        
      
     
        
    }

}
