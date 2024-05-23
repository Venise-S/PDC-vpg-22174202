/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame.GUIClass;

import java.awt.CardLayout;
import java.io.Serializable;
import javax.swing.*;
import virtualpetgame.*;

/**
 *
 * @author stamv
 */
public class GUIManager implements Serializable {

    private final JFrame frame;
    private final JPanel panel;
    private final CardLayout cardLayout;
    private VPGame game;
    private Pet selectedPet = null;
    
    public VPGame getVPGame() {
        return this.game;
    }

    public GUIManager(VPGame game) {
        this.game = game;
        
        testPets();
        
        frame = new JFrame("Virtual Pet Game");
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        frame.setSize(800, 500);
        frame.setTitle("Virtual Pet Game");
        frame.setLocation(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);

        MainMenuPanel mainMenu = new MainMenuPanel(this);
        PausePanel pausePanel = new PausePanel(this);
        PetSelectorPanel petSelectorPanel = new PetSelectorPanel(this, game);
        ActionSelectPanel actionSelectPanel = new ActionSelectPanel(this,selectedPet);
        SleepPanel sleepPanel = new SleepPanel(this);
        NewPetPanel newPetPanel = new NewPetPanel(this);

        panel.add(mainMenu, "mainMenu");
        panel.add(pausePanel, "pausePanel");
        panel.add(petSelectorPanel, "petSelector");
        panel.add(actionSelectPanel, "actionSelect");
        panel.add(sleepPanel, "sleep");
        panel.add(newPetPanel, "newPet");

        frame.setVisible(true);
        showMainMenu();
    }

    public void showMainMenu() {
        cardLayout.show(panel, "mainMenu");
    }

    public void showPetSelector() {
        cardLayout.show(panel, "petSelector");
    }

    public void showActionSelect() {
        cardLayout.show(panel, "actionSelect");
    }

    public void showSleep() {
        cardLayout.show(panel, "sleep");
    }

    public void showPause() {
        cardLayout.show(panel, "pausePanel");
    }

    public void showNewPet() {
        cardLayout.show(panel, "newPet");
    }
    
    public void testPets() {
        Feline pet1 = new Feline("name",100,100,100);
        game.getPetManager().addPet(pet1);
    }

}
