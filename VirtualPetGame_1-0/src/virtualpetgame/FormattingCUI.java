/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

import java.util.Scanner;

/**
 *
 * @author stamv
 */
public class FormattingCUI { // for printing breaks between methods in CUI
    private final int BREAKDURATION = 600;
    private final transient Scanner scan = new Scanner(System.in);
    
        // ---- checks and formatting ----
    public void printBreak() {
        // prints a large line break. used between methods
        System.out.println("\n----------------------");
        System.out.println("**********************");
        System.out.println("----------------------\n");

        try {
            Thread.sleep(BREAKDURATION);
        } catch (InterruptedException e) {
            System.err.println("Error: Wait failed");
        }
    }

    public void printHLine() {
        // prints a horizontal line
        System.out.println("\n*--------------------*\n");
    }

    public void waitForEnter() {
        System.out.println("(press any key to continue)");
        scan.nextLine();
    }


}
