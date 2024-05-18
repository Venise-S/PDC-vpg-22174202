/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame.GUIClass;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import virtualpetgame.Pet;
import virtualpetgame.VPGame;

/**
 *
 * @author stamv
 */
public class PetSelectorPanel extends JPanel {
    private GUIManager guiManager;
    private VPGame game;  // Add a reference to the VPGame to access the pet list

    public PetSelectorPanel(GUIManager guiManager, VPGame game) {
        this.guiManager = guiManager;
        this.game = game;  // Initialize the VPGame reference
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Select a pet by clicking on the button:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);

        // Display the list of pets with buttons
        Pet[] pets = game.getPetManager().getPets();
        for (int i = 0; i < pets.length && pets[i] != null; i++) {
            JButton petButton = new JButton(pets[i].getName());
            petButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            int petIndex = i;  // Capture the index for use in the ActionListener
            petButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Pet selectedPet = pets[petIndex];
                    JOptionPane.showMessageDialog(null, "Selected pet: " + selectedPet.getName());
                    game.petActionMenu(selectedPet);  // Call the petActionMenu method with the selected pet
                    guiManager.showActionSelect();  // Switch to the action select panel
                }
            });
            add(petButton);
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
