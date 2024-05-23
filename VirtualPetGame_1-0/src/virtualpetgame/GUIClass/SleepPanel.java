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
import javax.swing.JPanel;

/**
 *
 * @author stamv
 */
public class SleepPanel extends JPanel {
    public SleepPanel(GUIManager guiManager) {
        // Set the layout manager
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Add components to the panel
        JLabel label = new JLabel("Sleep");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);
        // add a back button
                JButton backButton = new JButton("Back to Main Menu");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiManager.showMainMenu();  // Return to the main menu
            }
        });
    }
}
