/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

import java.io.Serializable;

/**
 *
 * @author stamv
 */
public class Event implements Serializable {
    private String name;
    private String description;
    
    public Event(String name, String description) {
        this.name = name;
        this.description = description;
    }
    
    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.description;
    }
    
}
