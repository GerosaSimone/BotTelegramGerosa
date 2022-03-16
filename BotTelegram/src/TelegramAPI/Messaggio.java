/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelegramAPI;

import java.util.Date;
import org.json.JSONObject;

/**
 *
 * @author gerosa_simone
 */
public class Messaggio {
    long message_id;
    long update_id;
    From f;
    Chat c;
    long date;
    String text;

    public long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(long message_id) {
        this.message_id = message_id;
    }

    public long getUpdate_id() {
        return update_id;
    }

    public void setUpdate_id(long update_id) {
        this.update_id = update_id;
    }

    public From getF() {
        return f;
    }

    public void setF(From f) {
        this.f = f;
    }

    public Chat getC() {
        return c;
    }

    public void setC(Chat c) {
        this.c = c;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Messaggio() {
        this.message_id = 0;
        this.update_id = 0;
        this.f = null;
        this.c = null;
        this.date = 0;
        this.text = "";
    }
    
    public Messaggio (JSONObject arr){
        f=new From();
        c=new Chat();
         message_id = arr.getJSONObject("message").getLong("message_id");
         update_id = arr.getLong("update_id");
            JSONObject from = arr.getJSONObject("message").getJSONObject("from");
            f.id = from.getLong("id");
            f.is_bot = from.getBoolean("is_bot");
            f.first_name = from.getString("first_name");
            f.last_name = from.getString("last_name");
            f.language_code = from.getString("language_code");
            
            JSONObject chat = arr.getJSONObject("message").getJSONObject("chat");
            c.id = chat.getLong("id");
            c.first_name = chat.getString("first_name");
            c.last_name = chat.getString("last_name");
            c.type = chat.getString("type");
            
            date=arr.getJSONObject("message").getLong("date");
            text=arr.getJSONObject("message").getString("text");
            
    }
    public String to_String(){
        String s="";
        
        s+="update_id: "+update_id+"\n";
        s+="message_id: "+message_id+"\n";
        s+="From: \n";
        s+=f.to_String();
        s+="Chat: \n";
        s+=c.to_String();
        s+="date: "+new Date(date*1000)+"\n";
        s+="text: "+text+"\n";        
        return s;
    }
}
