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
public class EventSelector implements Serializable {
    private Random rand = new Random();
    private VPGameCUI vpGame;

    public HashSet<Event> eventList;
    private transient final int RANDPETCHANCE = 4; // the lower the likelier

    public EventSelector(VPGameCUI vpGame) {
        this.vpGame = vpGame;
        this.eventList = new HashSet<>();
        insEventSet();
    }
    
        public void updateVirtualPetGame(VPGameCUI vpGame) {
        this.vpGame = vpGame;
    }

    public void insEventSet() {
        
        Event newPetEv = new Event("New pet", "A pet has approached you!");
        Event loseF = new Event("Lose food", "Your backpack was ramsacked and you lost 50% food.");
        //Event loseH = new Event("Hungry pets", "All your pets got hungry (hunger -10)");
        Event addFood = new Event("Gain food","Your pets brought you some food.");

        eventList.add(newPetEv);
        eventList.add(loseF);
        eventList.add(addFood);
    }

    private Event pickRandEvent() {
        
        int randomIndex = rand.nextInt(eventList.size());
        int currentIndex = 0;
        for (Event event : eventList) {
            if (currentIndex == randomIndex) {
                return event;
            }
            currentIndex++;
        }
        return null;
    }

    public void eventSwitch() {
        Event myEvent = pickRandEvent();

        System.out.println(myEvent.getName() + ": ");
        System.out.println(myEvent.getDesc());

        Scanner sc = new Scanner(System.in);
        System.out.println("\n(press enter to continue)");
        sc.nextLine();
        
        switch (myEvent.getName().toLowerCase()) {
            case "new pet":
                newPetEv();
                break;
            case "lose food":
                loseF();
                break;
            case "gain food":
                addFood();
                break;
            default:
                break;
        }
    }

    public void newPetEv() {
        vpGame.getPetManager().newPet();
    }

    public void loseF() {
        GameInfo game = vpGame.getGameInfo();
        game.setFood(game.getFood()/2);
    }
    
    public void addFood() {
        GameInfo game = vpGame.getGameInfo();
        game.setFood(game.getFood() + rand.nextInt(8)+2);
    }
    
    

}
