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

    public ArrayList<Messaggio> getM() {
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

        if(!arr.isEmpty()){
        for (int i = 0; i < arr.length(); i++) {
            try {
                Messaggio m = new Messaggio(arr.getJSONObject(i));
                messaggi.add(m);
            } catch (Exception e) {

            }

        }
        }
    }

      public String to_String() {
        String s = "";

        for (int i = 0; i < messaggi.size(); i++) {
            s += messaggi.get(i).to_String();
            s += "-------------------------------------------------------------------------------------------------------------------\n";
        }

        return s;
    }
      /*
    
     //lettura di un file JSon
    
        InputStream is = url.openConnection().getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = null;
        String s = "";
        while ((line = reader.readLine()) != null) {
            s += line;
        }
    
        //parsing del file JSon
    
        JSONObject objs = new JSONObject(s);
        JSONArray arr = objs.getJSONArray("eventuale nome dell'array"); // puÃ² dover essere fatto prima
        if (!arr.isEmpty()) {
            for (int i = 0; i < arr.length(); i++) {
                JSONObject result = arr.getJSONObject(i);
                result.getJSONObject(" ").getString(" ");
            }
        }
    
        //scrittura file csv
    
        File file = new File("NomiCitta.csv");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(Utente.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scanner scanner = new Scanner(file);
        StringBuffer stringBuffer = new StringBuffer();
        String rigaDaModificare = null;
        while (scanner.hasNextLine()) {
            String riga = scanner.nextLine();
            if (riga.contains(String.valueOf(id))) {
                rigaDaModificare = riga;
            }
            stringBuffer.append(riga + System.lineSeparator());
        }
        String contents = stringBuffer.toString();
        scanner.close();
        if (rigaDaModificare != null) {
            contents = contents.replaceAll(rigaDaModificare, id + ";" + first_name + ";" + second_name + ";" + latitudine + ";" + longitudine);
        } else {
            contents += id + ";" + first_name + ";" + second_name + ";" + latitudine + ";" + longitudine + System.lineSeparator();
        }
        contents = contents.trim();
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            writer.write(contents);
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Utente.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        //lettura file csv    
    
        Files.readAllLines(Paths.get("NomiCitta.csv"))
        
        //operazioni con file XML
    
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        var document = builder.parse("utl pagina XML");
        NodeList lista = document.getElementsByTagName("tag"); //salva lista di nodi        
        
        Node place = lista.item(0); //salva singolo nodo || lista.item(i);
        place.getAttributes();        
        String s = document.getElementsByTagName("place").item(0).getTextContent();//salvare il valore
        NamedNodeMap attr = document.getElementsByTagName("place").item(0).getAttributes(); //variabile per lavorare con gli attributi
        attr.getNamedItem("currency").getNodeValue(); //prendere valore di un attributo
    
    */
  

}
