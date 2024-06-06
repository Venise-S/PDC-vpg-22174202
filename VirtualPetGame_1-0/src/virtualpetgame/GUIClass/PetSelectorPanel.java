/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame.GUIClass;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import virtualpetgame.Pet;
import virtualpetgame.VPGame;

/**
 *
 * @author stamv
 */

public class PetSelectorPanel extends JPanel {
    private GUIManager guiManager;
    private VPGame game;

    public PetSelectorPanel(GUIManager guiManager, VPGame game) {
        this.guiManager = guiManager;
        this.game = game;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Select a pet by clicking on the button:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);

        Pet[] pets = game.getPetManager().getPetsArray();
        if (pets != null) {
            for (Pet pet : pets) {
                if (pet == null) {
                    break;
                }
                JButton petButton = new JButton(pet.getName());
                petButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                petButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(null, "Selected pet: " + pet.getName());
                        game.petActionMenu(pet);
                        guiManager.showActionSelect(pet);
                    }
                });
                add(petButton);
            }
        }

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



