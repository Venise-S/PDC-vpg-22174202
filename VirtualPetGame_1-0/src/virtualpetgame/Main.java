/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;
import java.io.File;
import java.util.Scanner;

/**
 * 
 *
 * @author stamv
 */
public class Main {
        public static void startFileOpen() {
        Scanner scan = new Scanner(System.in);
        try {
            // open existing file if exists, otherwise start a new game
            VPGameCUI virtualPetGame;

            File saveFile = new File("savegame.dat");
            if (saveFile.exists()) {
                System.out.println("A saved game exists. Do you want to continue from the last session? (y/n)");

                String choice = scan.nextLine().trim();
                if (choice.compareToIgnoreCase("y") == 0) {
                    
                    virtualPetGame = GameSave.loadGame("savegame.dat");
                    if (virtualPetGame != null) {
                        virtualPetGame.getEventSelector().updateVirtualPetGame(virtualPetGame);
                    } else {
                        // start a new save
                        
                        saveFile.delete();
                        virtualPetGame = new VPGameCUI();
                    }
                } else {
                    System.out.println("Overwriting current save.");
                    
                    virtualPetGame = new VPGameCUI();

                }
            } else {
                virtualPetGame = new VPGameCUI();

            }
            
            virtualPetGame.getEventSelector().updateVirtualPetGame(virtualPetGame);
            virtualPetGame.getPetManager().startStatDecrease();
            virtualPetGame.menu();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        startFileOpen();
    }
}
