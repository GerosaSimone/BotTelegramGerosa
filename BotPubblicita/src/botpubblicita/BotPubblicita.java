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
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author gerosa_simone
 */
public class BotPubblicita {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

        ThreadRicezione t = new ThreadRicezione();
        t.start();

        Scanner scanner = new Scanner(System.in);
        String pub = "";
        String citta = "";
        Double raggio = 0.0;

        while (true) {
            System.out.println(""
                    + "1-Testo pubblicita\n"
                    + "2-Citta di pubblicazione\n"
                    + "3-raggio d'azione della pubblicita\n"
                    + "4-invia pubblicita\n");
            Integer scelta = scanner.nextInt();

            switch (scelta) {
                case 1:
                    System.out.println("Inserire il testo della pubblicita");
                    scanner.nextLine();
                    pub = scanner.nextLine();

                    break;
                case 2:
                    System.out.println("Inserire la citta di pubblicazione");
                    scanner.nextLine();
                    citta = scanner.nextLine();

                    break;
// eventuali altri case
                case 3:
                    System.out.println("Inserire il raggio d'azione della pubblicita");
                    raggio = scanner.nextDouble();

                    break;
                case 4:
                    System.out.println("Pubblicare la seguente pubblicita:\n"
                            + "Testo:" + pub + "\n"
                            + "Citta:" + citta + "\n"
                            + "Raggio:" + raggio + "\n"
                            + "DIGITARE SI per l'invio\n");
                    scanner.nextLine();
                    String s = scanner.nextLine();

                    if (s.equals("SI")) {
                        Pubblicita p = new Pubblicita(pub, citta, raggio);
                        p.invia();
                        pub = "";
                        citta = "";
                        raggio = 0.0;
                    } else {
                        pub = "";
                        citta = "";
                        raggio = 0.0;
                    }

                    break;
            }
        }
    }
}
