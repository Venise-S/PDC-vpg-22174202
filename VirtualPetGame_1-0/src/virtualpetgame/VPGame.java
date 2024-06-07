/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

import java.io.Serializable;
import virtualpetgame.GUIClass.GUIManager;

/**
 *
 * @author stamv
 */
public class VPGame implements Serializable {

    // constants
    private final int EVENTCHANCE = 3;
    private final int MAXSTATCHANCE = 40;

    private final GameInfo game;
    private final EventSelector ev;
    private final PetManager petManager; // contains pet db management

    public VPGame() {
        this.petManager = new PetManager();
        this.game = new GameInfo(10);
        this.ev = new EventSelector(this);
    }

    // ---- getters ----
    public GameInfo getGameInfo() {
        return this.game;
    }

    public PetManager getPetManager() {
        return this.petManager;
    }

    public EventSelector getEventSelector() {
        return this.ev;
    }

    public int getEVENTCHANCE() {
        return EVENTCHANCE;
    }

    public int getMAXSTATCHANCE() {
        return MAXSTATCHANCE;
    }

    // method to be called by GUI for pet selection actions
    public void petActionMenu(Pet selectedPet) {
        System.out.println("Selected pet: " + selectedPet.getName());
    }

    // method to start gui
    public void initializeGUI() {
        GUIManager guiManager = new GUIManager(this);
        guiManager.showMainMenu();
    }

}
