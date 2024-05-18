/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame.GUIClass;

import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author stamv
 */
public class NewPetPanel extends JPanel {
    public NewPetPanel(GUIManager guiManager) {
        // Set the layout manager
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // Add components to the panel
        JLabel title = new JLabel("You have found a new pet!");
        // image of pet based on type
        // text input name
        // button keep pet/release - when pressed popup text with update. -> continue to main menu
        // unless invalid input, different box shows up. -> do not continue
        
        
        
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);
    }
}