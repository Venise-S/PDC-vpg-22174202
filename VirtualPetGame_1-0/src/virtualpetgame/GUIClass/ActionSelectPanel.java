/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame.GUIClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import virtualpetgame.Pet;

/**
 *
 * @author stamv
 */
public class ActionSelectPanel extends JPanel {

    private final GUIManager guiManager;
    private final Pet selectedPet;

    // stat labels
    private JLabel hungerLabel;
    private JLabel thirstLabel;
    private JLabel specialStatLabel;
    private Timer statTimer;

    public ActionSelectPanel(GUIManager guiManager, Pet selectedPet) {
        this.guiManager = guiManager;
        this.selectedPet = selectedPet;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // show title labels and image
        displayHeaderLabels();

        // show current stats to user
        displayStats();

        // show action options
        displayActionLabel();

        //show buttons to respective stats
        displayFeedButton();
        displayWaterButton();
        displaySpecialActButton();

        // show navigation buttons
        displayBackButton();
        displayMainMenuButton();

        // update stats periodically
        startStatUpdate();
    }

    // methods to update labels on GUI. does not modify stats itself
    private void displayHeaderLabels() {
        // show pet img
        ImageIcon petIcon = new ImageIcon(getClass().getResource("/" + selectedPet.getType() + "img.png"));
        JLabel petImage = new JLabel(petIcon);

        petImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(petImage);

        // show pet name
        JLabel petName = new JLabel(selectedPet.getName());
        petName.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(petName);
    }

    private void displayStats() {
        hungerLabel = new JLabel("Hunger: " + selectedPet.getHunger());
        hungerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        thirstLabel = new JLabel("Thirst: " + selectedPet.getThirst());
        thirstLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (selectedPet.getType().equalsIgnoreCase("Canine")) {
            specialStatLabel = new JLabel("Exercise: " + selectedPet.getSpecialStat());
        } else {
            specialStatLabel = new JLabel("Cleanliness: " + selectedPet.getSpecialStat());
        }
        specialStatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(hungerLabel);
        add(thirstLabel);
        add(specialStatLabel);
    }

    private void updateStatLabels() {
        hungerLabel.setText("Hunger: " + selectedPet.getHunger());
        thirstLabel.setText("Thirst: " + selectedPet.getThirst());
        if (selectedPet.getType().equalsIgnoreCase("Canine")) {
            specialStatLabel.setText("Exercise: " + selectedPet.getSpecialStat());
        } else {
            specialStatLabel.setText("Cleanliness: " + selectedPet.getSpecialStat());
        }

        // Refresh the panel to show updated stats
        revalidate();
        repaint();
    }

    private void startStatUpdate() {
        statTimer = new Timer(1000, new ActionListener() { // update every 2 seconds
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStatLabels();
            }
        });
        statTimer.start();
    }

    private void stopStatUpdate() {
        if (statTimer != null) {
            statTimer.stop();
        }
    }

    private void displayActionLabel() {
        JLabel label = new JLabel("Select an action for " + selectedPet.getName() + ":");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);
    }

    private void displayFeedButton() {
        JButton feedButton = new JButton("Feed");
        feedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        feedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPet.feed();
                JOptionPane.showMessageDialog(null, selectedPet.getName() + " has been fed.");
                updateStatLabels();
            }
        });
        add(feedButton);
    }

    private void displayWaterButton() {
        JButton giveWaterButton = new JButton("Give Water");
        giveWaterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        giveWaterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPet.giveWater();
                JOptionPane.showMessageDialog(null, selectedPet.getName() + " has been given water.");
                updateStatLabels();
            }
        });
        add(giveWaterButton);
    }

    private void displaySpecialActButton() {
        JButton specialActionButton = new JButton();
        if (selectedPet.getType().equalsIgnoreCase("Canine")) {
            specialActionButton.setText("Play");
        } else {
            specialActionButton.setText("Pamper");
        }
        specialActionButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        specialActionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPet.setSpecialStat(selectedPet.getSpecialStat() + 50);
                if (selectedPet.getType().equalsIgnoreCase("Canine")) {
                    JOptionPane.showMessageDialog(null, selectedPet.getName() + " enjoyed the playing.");
                } else {
                    JOptionPane.showMessageDialog(null, selectedPet.getName() + " enjoyed the pampering.");
                }
                updateStatLabels();
            }
        });
        add(specialActionButton);
    }

    private void displayBackButton() {
        JButton backButton = new JButton("Back to Pet Selector");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiManager.showPetSelector();
                stopStatUpdate();
            }
        });
        add(backButton);
    }

    // exit to main menu
    public void displayMainMenuButton() {
        JButton mainMenuButton = new JButton("Back to Main Menu");
        mainMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiManager.showMainMenu();
                stopStatUpdate();
            }
        });
        add(mainMenuButton);
    }
}
