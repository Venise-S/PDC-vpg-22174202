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
public abstract class VPGame implements Serializable {

    // FUNCTIONALITY SIDE OF PROGRAMME
    // constants
    private final int EVENTCHANCE = 3;
    private final int MAXSTATCHANCE = 40;

    private final GameInfo game;
    private final EventSelector ev;
    private final PetManager getPetManager; // contains pets[] list

    public VPGame() {
        this.getPetManager = new PetManager();
        this.game = new GameInfo(10);
        this.ev = new EventSelector(this);
    }

    // ---- getters ----
    public GameInfo getGameInfo() {
        return this.game;
    }

    public PetManager getPetManager() {
        return this.getPetManager;
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

    // Method to be called by GUI for pet selection actions
    public void petActionMenu(Pet selectedPet) {
        // Implement the actions to be taken when a pet is selected
        System.out.println("Selected pet: " + selectedPet.getName());
        // Further actions can be added here
    }

    // Ensure to pass the game instance to GUI components that need it
    public void initializeGUI() {
        GUIManager guiManager = new GUIManager(this);
        guiManager.showMainMenu();
    }

}
