/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame.GUIClass;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author stamv
 */
public class MainMenuPanel extends JPanel {
    GUIManager guiManager;
    
    public MainMenuPanel(GUIManager guiManager) {
        this.guiManager = guiManager;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        displayTitleLabel();
        displayDescLabel();
        displayInteractButton(guiManager);
        displaySleepButton(guiManager);
        displayPauseButton(guiManager);
    }

    private void displayTitleLabel() {
        JLabel titleLabel = new JLabel("Virtual Pet Game");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));
        add(titleLabel);
    }

    private void displayDescLabel() {
        JLabel descLabel = new JLabel("Please select your next action:");
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));
        add(descLabel);
    }

    private void displayInteractButton(GUIManager mainFrame) {
        JButton interactButton = new JButton("Select a pet and choose a pet action");
        interactButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        interactButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // open interact with pet panel
                mainFrame.showPetSelector();
            }
        });
        add(Box.createVerticalStrut(20));
        add(interactButton);
    }

    private void displaySleepButton(GUIManager mainFrame) {
        JButton sleepButton = new JButton("Sleep to next day");
        sleepButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sleepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // open sleep panel
                mainFrame.showSleep();
            }
        });
        add(Box.createVerticalStrut(10));
        add(sleepButton);
    }

    private void displayPauseButton(GUIManager mainFrame) {
        JButton pauseButton = new JButton("Pause game");
        pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPause();
            }
        });
        add(Box.createVerticalStrut(10));
        add(pauseButton);
    }
}