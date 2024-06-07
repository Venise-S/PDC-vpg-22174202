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

    private final JLabel titleLabel;
    private final JLabel descLabel;
    private final JButton interactButton;
    private final JButton sleepButton;
    private final JButton pauseButton;

    public MainMenuPanel(GUIManager guiManager) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("Virtual Pet Game");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));
        add(titleLabel);

        descLabel = new JLabel("Please select your next action:");
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));
        add(descLabel);

        interactButton = new JButton("Select a pet and choose a pet action");
        interactButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20));
        add(interactButton);

        sleepButton = new JButton("Sleep to next day");
        sleepButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));
        add(sleepButton);

        pauseButton = new JButton("Pause game");
        pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(10));
        add(pauseButton);

        interactButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // open interact with pet panel
                guiManager.showPetSelector();
            }
        });

        sleepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // open sleep panel
                guiManager.showSleep();
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guiManager.showPause();
            }
        });
    }
}
