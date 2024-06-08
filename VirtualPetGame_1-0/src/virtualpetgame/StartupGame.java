/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

import virtualpetgame.GUIClass.GUIManager;

/**
 *
 *
 * @author stamv
 */
public class StartupGame {

    public static void main(String[] args) {
        VPGame vpGame = new VPGame();
        vpGame.getEventSelector().updateVirtualPetGame(vpGame);
        vpGame.getPetManager().startStatDecrease();

        // starting of GUI
        GUIManager newGUI = new GUIManager(vpGame);
    }
}
