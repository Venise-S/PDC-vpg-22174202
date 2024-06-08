/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame.GUIClass;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author stamv
 */
public class PausePanel extends JPanel {

    private final GUIManager guiManager;

    private JLabel pauseTitle;
    private JButton continueButton;
    private JButton resetButton;
    private JButton saveButton;

    public PausePanel(GUIManager guiManager) {
        this.guiManager = guiManager;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        displayPauseTitle();
        displayContinueButton();
        displayResetButton();
        displaySaveButton();
    }

    // methods to show labels and buttons
    private void displayPauseTitle() {
        pauseTitle = new JLabel("Paused Game.");
        pauseTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(guiManager.STRUT_MID_PX));
        add(pauseTitle);
        add(Box.createVerticalStrut(guiManager.STRUT_SMALL_PX));
    }

    private void displayContinueButton() {
        continueButton = new JButton("Continue");
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiManager.showMainMenu();
            }
        });
        add(Box.createVerticalStrut(guiManager.STRUT_SMALL_PX));
        add(continueButton);
        add(Box.createVerticalStrut(guiManager.STRUT_SMALL_PX));
    }

    private void displayResetButton() {
        resetButton = new JButton("Reset game");
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // reset db
                guiManager.getVPGame().getPetManager().resetDatabase();
                JOptionPane.showMessageDialog(null, "Save has been deleted.");
                System.exit(0);
            }
        });
        add(Box.createVerticalStrut(10));
        add(resetButton);
        add(Box.createVerticalStrut(guiManager.STRUT_SMALL_PX));
    }

    private void displaySaveButton() {
        saveButton = new JButton("Save and Exit");
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiManager.getVPGame().getPetManager().close();
                System.exit(0);
            }
        });
        add(Box.createVerticalStrut(guiManager.STRUT_SMALL_PX));
        add(saveButton);
        add(Box.createVerticalStrut(guiManager.STRUT_SMALL_PX));
    }
}
