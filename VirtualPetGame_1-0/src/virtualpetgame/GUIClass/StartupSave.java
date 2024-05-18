/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame.GUIClass;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import virtualpetgame.GameSave;
import virtualpetgame.VPGame;
import virtualpetgame.VPGameCUI;

/**
 *
 * @author stamv
 */
public class StartupSave {
    private JFrame frame;
    private JPanel panel;
    private JLabel title;
    private JLabel question;
    private JButton yes;
    private JButton no;

    public StartupSave(VPGame vpGame) {
        // Create and set up the frame
        frame = new JFrame("Virtual Pet Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        
        // Create panel and add components
        panel = new JPanel();
        title = new JLabel("Welcome to Virtual Pet Game.");
        question = new JLabel("A saved game exists. Do you want to continue from the last session?");
        yes = new JButton("Yes");
        no = new JButton("No");

        // Add action listeners to buttons
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                continueGame();
            }
        });

        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame();
            }
        });

        // Add components to panel
        panel.add(title);
        panel.add(question);
        panel.add(yes);
        panel.add(no);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);
    }

    private void continueGame() {
        // Load the saved game
        try {
            VPGameCUI vpGameCUI = GameSave.loadGame("savegame.dat");
            if (vpGameCUI != null) {
                vpGameCUI.getEventSelector().updateVirtualPetGame(vpGameCUI);
                vpGameCUI.getPetManager().startStatDecrease();
                
                // Start the GUI
                //GUIManager newGUI = new GUIManager();
            } else {
                // Handle error if the game could not be loaded
                JOptionPane.showMessageDialog(frame, "Error loading saved game. Starting a new game.", "Error", JOptionPane.ERROR_MESSAGE);
                startNewGame();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            startNewGame();
        } finally {
            frame.dispose();
        }
    }

    private void startNewGame() {
        // Start a new game
        try {
            File saveFile = new File("savegame.dat");
            if (saveFile.exists()) {
                saveFile.delete();
            }
            
            VPGame vpGame = new VPGameCUI();
            vpGame.getEventSelector().updateVirtualPetGame(vpGame);
            vpGame.getPetManager().startStatDecrease();
            
            // Start the GUI
            GUIManager newGUI = new GUIManager(vpGame);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            frame.dispose();
        }
    }
}
