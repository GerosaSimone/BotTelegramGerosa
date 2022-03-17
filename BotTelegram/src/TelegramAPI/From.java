/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelegramAPI;

import java.util.Date;

/**
 *
 * @author gerosa_simone
 */
public class From {

    public long id;
    boolean is_bot;
    String first_name;
    String last_name;
    String language_code;

    public From() {
        this.id = 0000;
        this.is_bot = true;
        this.first_name = "";
        this.last_name = "";
        this.language_code = "";
    }
    
       public String to_String(){
        String s="";
        
        s+="id: "+id+"\n";
        s+="is_bot: "+is_bot+"\n";
        s+="first_name: "+first_name+"\n";
        s+="last_name: "+last_name+"\n";
        s+="language_code: "+language_code+"\n";
               
        return s;
    }
}
