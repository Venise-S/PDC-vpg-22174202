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
    private final JButton forageButton;
    private final JButton pauseButton;

    public MainMenuPanel(GUIManager mainFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        titleLabel = new JLabel("Virtual Pet Game");
        descLabel = new JLabel("Please select your next action:");
        interactButton = new JButton("Select a pet and choose a pet action");
        forageButton = new JButton("Forage for food");
        sleepButton = new JButton("Sleep to next day");
        pauseButton = new JButton("Pause game");

        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        interactButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        forageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sleepButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pauseButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(20));
        add(titleLabel);
        add(Box.createVerticalStrut(10));
        add(descLabel);
        add(Box.createVerticalStrut(20));
        add(interactButton);
        add(Box.createVerticalStrut(10));
        add(sleepButton);
        add(Box.createVerticalStrut(10));
        add(forageButton);
        add(Box.createVerticalStrut(10));
        add(pauseButton);

        interactButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // open interact with pet panel
            }
        });

        sleepButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // open sleep panel
            }
        });

        forageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // open forage panel
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.showPause();
            }
        });
    }
}


