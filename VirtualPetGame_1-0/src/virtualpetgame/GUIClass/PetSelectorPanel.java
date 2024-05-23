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
    private VPGame game;

    public PetSelectorPanel(GUIManager guiManager, VPGame game) {
        this.guiManager = guiManager;
        this.game = game;  
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Select a pet by clicking on the button:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);

        // displaying of pets user is able to select
        Pet[] pets = game.getPetManager().getPetsArray();
        for (int i = 0; i < pets.length && pets[i] != null; i++) {
            JButton petButton = new JButton(pets[i].getName());
            petButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            int petIndex = i;
            petButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Pet selectedPet = pets[petIndex];
                    JOptionPane.showMessageDialog(null, "Selected pet: " + selectedPet.getName());
                    game.petActionMenu(selectedPet); 
                    guiManager.showActionSelect(); 
                }
            });
            add(petButton);
        }

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiManager.showMainMenu();  // return to the main menu
            }
        });
        add(backButton);
    }
}
