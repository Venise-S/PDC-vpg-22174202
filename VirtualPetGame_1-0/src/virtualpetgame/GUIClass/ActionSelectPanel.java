/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame.GUIClass;

import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import virtualpetgame.Pet;

/**
 *
 * @author stamv
 */
public class ActionSelectPanel extends JPanel {
    public ActionSelectPanel(GUIManager guiManager, Pet selectedPet) {
        // checking code for if null pet selected. 
        // if yes send error message then return to main menu
        
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Action Select");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);
    }
}
