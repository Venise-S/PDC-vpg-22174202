/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame.GUIClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 *
 * @author stamv
 */
public class SleepPanel extends JPanel {

    private GUIManager guiManager;
    
    private final int EVENT_CHANCE = 3;

    public SleepPanel(GUIManager guiManager) {
        this.guiManager = guiManager;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        displayLabel();
        displayBackButton();
    }

    private void displayLabel() {
        JLabel label = new JLabel("Sleep");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);
    }

    private void displayBackButton() {
        JButton backButton = new JButton("To next day");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSleepEvent();
            }
        });
        add(backButton);
    }

    // chance for event to occur
    private void handleSleepEvent() {
        Random rand = new Random();
        int eventChance = rand.nextInt(EVENT_CHANCE);
        if (eventChance == 0) {
            // no event occurs
            guiManager.showMainMenu();
        } else {
            String eventChosen = guiManager.getVPGame().getEventSelector().randEvent();
            JOptionPane.showMessageDialog(null, "While you were sleeping, " + eventChosen);
            if (eventChosen.contains("a new pet")) {
                // new pet event chosen
                guiManager.showNewPet();
            } else {
                // go to main menu
                guiManager.showMainMenu();
            }
        }
    }
}
