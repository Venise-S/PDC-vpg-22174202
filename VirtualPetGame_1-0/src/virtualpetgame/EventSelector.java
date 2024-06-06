/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author stamv
 */

public class EventSelector<E extends VPGame> {
    private final Random rand = new Random();
    private E vpGame;

    public EventSelector(E vpGame) {
        this.vpGame = vpGame;
          
    }
    
        public void updateVirtualPetGame(E vpGame) {
        this.vpGame = vpGame;
    }
    
    public String randEvent() {
        int chosenInt = rand.nextInt(3);
        String eventDesc = "";
        switch (chosenInt) {
            case 0:
                eventDesc = "a new pet has approached you!";
                // will run new pet panel in gui
                break;
            case 1:
                eventDesc = "your pets went extra hungry. -50%";
                vpGame.getPetManager().loseHunger();
                break;
            case 2:
                eventDesc = "your pets went extra thirsty. -50%";
                vpGame.getPetManager().loseThirst();
        }
        return eventDesc;
    }
    
}