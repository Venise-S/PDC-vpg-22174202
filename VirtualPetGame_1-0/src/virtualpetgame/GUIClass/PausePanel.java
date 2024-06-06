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
    private final GUIManager guiManager;
    
    private final JLabel pauseTitle;
    private final JButton continueButton;
    private final JButton resetButton;
    private final JButton saveButton;

    public PausePanel(GUIManager guiManager) {
        this.guiManager = guiManager;
        
        pauseTitle = new JLabel("Paused Game.");
        continueButton = new JButton("Continue");
        resetButton = new JButton("Reset game");
        saveButton = new JButton("Save and Exit");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        pauseTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(Box.createVerticalStrut(20));
        add(pauseTitle);
        add(Box.createVerticalStrut(10));
        add(continueButton);
        add(Box.createVerticalStrut(10));
        add(resetButton);
        add(Box.createVerticalStrut(10));
        add(saveButton);

        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiManager.showMainMenu();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // reset db
                guiManager.getVPGame().getPetManager().createTable();
                JOptionPane.showMessageDialog(null, "Save has been deleted.");
                System.exit(0);
            }
        });

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
