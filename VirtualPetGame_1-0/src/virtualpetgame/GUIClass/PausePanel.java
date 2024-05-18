/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame.GUIClass;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author stamv
 */

public class PausePanel extends JPanel {
    private final JLabel pauseTitle;
    private final JButton continueButton;
    private final JButton exitButton;
    private final JButton saveButton;

    public PausePanel(GUIManager mainFrame) {
        pauseTitle = new JLabel("Paused Game.");
        continueButton = new JButton("Continue");
        exitButton = new JButton("Exit");
        saveButton = new JButton("Save and Exit");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        pauseTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(Box.createVerticalStrut(20));
        add(pauseTitle);
        add(Box.createVerticalStrut(10));
        add(continueButton);
        add(Box.createVerticalStrut(10));
        add(exitButton);
        add(Box.createVerticalStrut(10));
        add(saveButton);

        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.showMainMenu();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Exit the game without saving
                System.exit(0);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Save the game and exit
                // Save functionality here
                System.exit(0);
            }
        });
    }
}
