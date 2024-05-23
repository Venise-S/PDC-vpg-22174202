/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

import todelete.VPGameCUI;
import virtualpetgame.GUIClass.GUIManager;


/**
 *
 *
 * @author stamv
 */
public class StartupGame {
    public static void startGUI() {

            VPGame vpGame = new VPGameCUI();
            vpGame.getEventSelector().updateVirtualPetGame(vpGame);
            vpGame.getPetManager().startStatDecrease();
            
            // starting of GUI
            GUIManager newGUI = new GUIManager(vpGame);
        
    }

    public static void main(String[] args) {
        startGUI();
    }
}

