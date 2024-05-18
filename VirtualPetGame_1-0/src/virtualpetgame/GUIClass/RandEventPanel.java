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
public class RandEventPanel extends JPanel {
    public RandEventPanel(GUIManager guiManager) {
        // Set the layout manager
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Add components to the panel
        JLabel label = new JLabel("Random Event Panel");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(label);
    }
}
