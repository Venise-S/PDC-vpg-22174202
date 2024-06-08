/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

import virtualpetgame.GUIClass.GUIManager;

/**
 *
 * @author stamv
 */
public class VPGame {

    // constants
    private final int EVENTCHANCE = 3;

    private final EventSelector ev;
    private final PetManager petManager; // contains pets[] list

    public VPGame() {
        this.petManager = new PetManager();
        this.ev = new EventSelector(this);
    }

    // ---- getters ----

    public PetManager getPetManager() {
        return this.petManager;
    }

    public EventSelector getEventSelector() {
        return this.ev;
    }

    public int getEVENTCHANCE() {
        return EVENTCHANCE;
    }

    public void initializeGUI() {
        GUIManager guiManager = new GUIManager(this);
        guiManager.showMainMenu();
    }

}
