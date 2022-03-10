/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TelegramAPI;

/**
 *
 * @author gerosa_simone
 */
public class Chat {

    long id;
    String first_name;
    String last_name;
    String type;

    public Chat() {
        id = 0000;
        first_name = "";
        last_name = "";
        type = "";
    }

    public String to_String() {
        String s = "";

        s += "id: " + id + "\n";
        s += "first_name: " + first_name + "\n";
        s += "last_name: " + last_name + "\n";
        s += "type: " + type + "\n";

        return s;
    }
}
