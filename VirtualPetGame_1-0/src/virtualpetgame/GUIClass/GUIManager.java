/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame.GUIClass;

import java.awt.CardLayout;
import javax.swing.*;
import virtualpetgame.*;

/**
 *
 * @author stamv
 */
public class GUIManager {

    private final VPGame game;
    
    private JFrame frame;
    private JPanel panel;
    private CardLayout cardLayout;
    
    // for spacing of elements
    public final int STRUT_MID_PX = 25;
    public final int STRUT_SMALL_PX = 10;

    public VPGame getVPGame() {
        return this.game;
    }

    public GUIManager(VPGame game) {
        this.game = game;

        setupFrame();

        MainMenuPanel mainMenu = new MainMenuPanel(this);
        PausePanel pausePanel = new PausePanel(this);
        PetSelectorPanel petSelectorPanel = new PetSelectorPanel(this);
        SleepPanel sleepPanel = new SleepPanel(this);
        NewPetPanel newPetPanel = new NewPetPanel(this);

        panel.add(mainMenu, "mainMenu");
        panel.add(pausePanel, "pausePanel");
        panel.add(petSelectorPanel, "petSelector");
        panel.add(sleepPanel, "sleep");
        panel.add(newPetPanel, "newPet");

        frame.setVisible(true);
        System.out.println(game.getPetManager().getNumPets());
        // if on new save, display intro and display new pet panel
        if (game.getPetManager().getNumPets() == 0) {
            displayIntro();
        } else {
            showMainMenu();
        }
    }

    private void setupFrame() {
        frame = new JFrame("Virtual Pet Game");
        // use cardlayout to display different panels
        cardLayout = new CardLayout();
        panel = new JPanel(cardLayout);

        frame.setSize(700, 650);
        frame.setTitle("Virtual Pet Game");
        frame.setLocation(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
    }
    
    public void displayIntro() {
        JOptionPane.showMessageDialog(null, "Welcome to Virtual Pet Game. Here you can care for Feline and Canine pets. You'll get started with one now!", "Welcome", JOptionPane.INFORMATION_MESSAGE);
        showNewPet();
    }

    // methods to display different panels
    public void showMainMenu() {
        cardLayout.show(panel, "mainMenu");
    }

    public void showPetSelector() {
        panel.remove(panel.getComponent(panel.getComponentCount() - 1)); // Remove the old PetSelectorPanel
        PetSelectorPanel newPetSelectorPanel = new PetSelectorPanel(this);
        panel.add(newPetSelectorPanel, "petSelector"); // Add the new PetSelectorPanel
        cardLayout.show(panel, "petSelector");
    }

    public void showActionSelect(Pet selectedPet) {
        panel.remove(panel.getComponent(panel.getComponentCount() - 1)); // Remove the old ActionSelectPanel
        ActionSelectPanel actionSelectPanel = new ActionSelectPanel(this, selectedPet);
        panel.add(actionSelectPanel, "actionSelect"); // Add the new ActionSelectPanel
        cardLayout.show(panel, "actionSelect");
    }

    public void showSleep() {
        cardLayout.show(panel, "sleep");
    }

    public void showPause() {
        cardLayout.show(panel, "pausePanel");
    }

    public void showNewPet() {
        panel.remove(panel.getComponent(panel.getComponentCount() - 1)); // Ensure only one instance of NewPetPanel is displayed
        NewPetPanel newPetPanel = new NewPetPanel(this);
        panel.add(newPetPanel, "newPet");
        cardLayout.show(panel, "newPet");
    }
}
