/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

import java.util.Random;

/**
 *
 * @author stamv
 */
public class EventSelector<E extends VPGame> {

    private E vpGame;
    
    private final Random rand = new Random();
    private final int numEvents = 3;

    public EventSelector(E vpGame) {
        this.vpGame = vpGame;

    }

    public void updateVirtualPetGame(E vpGame) {
        this.vpGame = vpGame;
    }

    // generate random event, run respective code in pet manager and return string
    public String randEvent() {
        int chosenInt = rand.nextInt(numEvents);
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
