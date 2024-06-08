/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame.GUIClass;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;
import virtualpetgame.*;

/**
 *
 * @author stamv
 */

public class NewPetPanel extends JPanel {
    private final GUIManager guiManager;
    private final Random rand = new Random();
    private final Pet newPet;
    private JTextField nameField;

    public NewPetPanel(GUIManager guiManager) {
        this.guiManager = guiManager;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // create a blank pet with type determined
        newPet = createPet();

        displayTitleLabel();
        displayPetImage();
        displayNameTextLabel();
        displayNameField();
        displayQuestionLabel();
        displayButtonPanel();
    }

    private Pet createPet() {
        int num = rand.nextInt(2);
        if (num == 0) {
            return new Canine("", 100, 100, 100);
        } else {
            return new Feline("", 100, 100, 100);
        }
    }

    // methods to show labels and buttons
    private void displayTitleLabel() {
        JLabel title = new JLabel("You have found a new pet!");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);
        add(Box.createVerticalStrut(guiManager.STRUT_SMALL_PX));
    }

    private void displayPetImage() {
        ImageIcon petIcon = new ImageIcon(getClass().getResource("/" + newPet.getType() + "img.png"));
        JLabel petImage = new JLabel(petIcon);
        petImage.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(petImage);
        add(Box.createVerticalStrut(guiManager.STRUT_SMALL_PX));
    }

    private void displayNameTextLabel() {
        JLabel nameText = new JLabel("Name: ");
        nameText.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(nameText);
        add(Box.createVerticalStrut(guiManager.STRUT_SMALL_PX));
    }

    private void displayNameField() {
        nameField = new JTextField(20);
        nameField.setMaximumSize(new Dimension(Integer.MAX_VALUE, nameField.getPreferredSize().height));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(nameField);
        add(Box.createVerticalStrut(guiManager.STRUT_SMALL_PX));
    }

    private void displayQuestionLabel() {
        JLabel question = new JLabel("Do you want to keep this pet?");
        question.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(question);
        add(Box.createVerticalStrut(guiManager.STRUT_SMALL_PX));
    }

    private void displayButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton keepPet = new JButton("Keep pet");
        JButton releasePet = new JButton("Release pet");

        buttonPanel.add(keepPet);
        buttonPanel.add(releasePet);

        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(buttonPanel);

        // button action listeners
        keepPet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String petName = nameField.getText();
                if (petName.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a name for the pet.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // update pet name
                    newPet.setName(petName);
                    // update game state to keep the pet with the given name
                    guiManager.getVPGame().getPetManager().addPet(newPet);
                    JOptionPane.showMessageDialog(null, "You have kept the pet named " + petName + "!", "Pet Kept", JOptionPane.INFORMATION_MESSAGE);
                    guiManager.showMainMenu();
                }
            }
        });

        releasePet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (guiManager.getVPGame().getPetManager().getPetsArray().length > 0) {
                    JOptionPane.showMessageDialog(null, "You have released the pet.", "Pet Released", JOptionPane.INFORMATION_MESSAGE);
                    guiManager.showMainMenu();
                } else {
                    JOptionPane.showMessageDialog(null, "Cannot release when you have no pets.");
                }
            }
        });
    }
}
