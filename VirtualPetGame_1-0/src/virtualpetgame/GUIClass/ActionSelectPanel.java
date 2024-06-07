/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame.GUIClass;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import virtualpetgame.Pet;

/**
 *
 * @author stamv
 */
public class ActionSelectPanel extends JPanel {

    private GUIManager guiManager;
    private Pet selectedPet;

    // stat labels
    private JLabel hungerLabel;
    private JLabel thirstLabel;
    private JLabel specialStatLabel;
    private Timer statUpdateTimer;

    public ActionSelectPanel(GUIManager guiManager, Pet selectedPet) {
        this.guiManager = guiManager;
        this.selectedPet = selectedPet;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // show pet image
        ImageIcon petIcon = new ImageIcon(getClass().getResource("/" + selectedPet.getType() + "img.png"));
        JLabel petImage = new JLabel(petIcon);

        petImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(petImage);

        // show pet name
        JLabel petName = new JLabel(selectedPet.getName());
        petName.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(petName);

        // show current stats to user
        displayStats();

        // show action options
        JLabel label = new JLabel("Select an action for " + selectedPet.getName() + ":");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);

        JButton feedButton = new JButton("Feed");
        feedButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        feedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPet.feed();
                JOptionPane.showMessageDialog(null, selectedPet.getName() + " has been fed.");
                updateStats();
            }
        });
        add(feedButton);

        JButton giveWaterButton = new JButton("Give Water");
        giveWaterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        giveWaterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPet.giveWater();
                JOptionPane.showMessageDialog(null, selectedPet.getName() + " has been given water.");
                updateStats();
            }
        });
        add(giveWaterButton);

        // button functionality
        JButton specialActionButton = new JButton(
                selectedPet.getType().equalsIgnoreCase("Canine") ? "Play" : "Pamper"
        );
        specialActionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        specialActionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPet.setSpecialStat(selectedPet.getSpecialStat() + 50);
                if (selectedPet.getType().equals("Canine")) {
                    JOptionPane.showMessageDialog(null, selectedPet.getName() + " enjoyed the playing.");
                } else {
                    JOptionPane.showMessageDialog(null, selectedPet.getName() + " enjoyed the pampering.");
                }
                updateStats();
            }

        });
        add(specialActionButton);

        JButton backButton = new JButton("Back to Pet Selector");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiManager.showPetSelector();
                stopStatUpdateTimer();
            }
        });
        add(backButton);

        JButton mainMenuButton = new JButton("Back to Main Menu");
        mainMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guiManager.showMainMenu();
                stopStatUpdateTimer();
            }
        });
        add(mainMenuButton);

        // start timer to update stats
        startStatUpdateTimer();
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

    private void updateStats() {
        hungerLabel.setText("Hunger: " + selectedPet.getHunger());
        thirstLabel.setText("Thirst: " + selectedPet.getThirst());
        if (selectedPet.getType().equalsIgnoreCase("Canine")) {
            specialStatLabel.setText("Exercise: " + selectedPet.getSpecialStat());
        } else {
            specialStatLabel.setText("Cleanliness: " + selectedPet.getSpecialStat());
        }

        // repaint panel so stats are updated
        revalidate();
        repaint();
    }

    private void startStatUpdateTimer() {
        statUpdateTimer = new Timer(1000, new ActionListener() { // Update every 2 seconds
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStats();
            }
        });
        statUpdateTimer.start();
    }

    private void stopStatUpdateTimer() {
        if (statUpdateTimer != null) {
            statUpdateTimer.stop();
        }
    }
}
