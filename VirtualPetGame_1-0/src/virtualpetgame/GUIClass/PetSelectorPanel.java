/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame.GUIClass;

import java.awt.Component;
import javax.swing.*;
import java.awt.event.*;
import virtualpetgame.Pet;

/**
 *
 * @author stamv
 */


public class PetSelectorPanel extends JPanel {

    private GUIManager guiManager;

    public PetSelectorPanel(GUIManager guiManager) {
        this.guiManager = guiManager;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        displayLabel();
        displayPetButtons();
        displayBackButton();
    }

    private void displayLabel() {
        JLabel label = new JLabel("Select a pet by clicking on the button:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);
    }

    private void displayPetButtons() {
        Pet[] pets = guiManager.getVPGame().getPetManager().getPetsArray();
        if (pets != null) {
            for (Pet pet : pets) {
                if (pet == null) {
                    break;
                }
                addButtonForPet(pet);
            }
        }
    }

    private void addButtonForPet(Pet pet) {
        JButton petButton = new JButton(pet.getName());
        petButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        petButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Selected pet: " + pet.getName());
                guiManager.showActionSelect(pet);
            }
        });
        add(petButton);
    }

    private void displayBackButton() {
        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiManager.showMainMenu();  // Return to the main menu
            }
        });
        add(backButton);
    }
}
