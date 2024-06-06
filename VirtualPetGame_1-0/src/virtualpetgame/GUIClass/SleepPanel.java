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

    public SleepPanel(GUIManager guiManager) {
        this.guiManager = guiManager;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Sleep");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);

        // Back button
        JButton backButton = new JButton("To next day");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getEvent();
            }
        });
    }

    public void getEvent() {
        Random rand = new Random();
        int eventChance = rand.nextInt(2);
        if (eventChance == 0) {
            return;
        }

        String eventChosen = guiManager.getVPGame().getEventSelector().randEvent();
        // String eventChosen = "a new pet has approached you!";
        JOptionPane.showMessageDialog(null, "While you were sleeping, " + eventChosen);
        if (eventChosen.contains("a new pet")) {
            guiManager.showNewPet();
        } else {
            guiManager.showMainMenu();  // Return to the main menu only if no event occurs
        }
    }
}
