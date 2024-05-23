/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

import java.io.Serializable;

/**
 *
 * @author stamv
 */
public class Feline extends Pet implements Serializable {

    private final int MINLVLUP = 180; // 60% of 300

    private int cleanliness;

    public Feline(String name, int hunger, int thirst, int cleanliness) {
        super(name, hunger, thirst);
        this.cleanliness = cleanliness;
        super.setType("Feline");
    }

    @Override
    public void setSpecialStat(int cleanliness) {
        this.cleanliness = cleanliness;
        if (this.cleanliness <= 0) {
            this.cleanliness = 0;
        } else if (this.cleanliness >= 100) {
            this.cleanliness = 100;
        }
    }

    @Override
    public int getSpecialStat() {
        return cleanliness;
    }

    // game based change stats
    // run on end of day 
    @Override
    public boolean levelUp() { // run in for loop for all pets , sleep function
        // ONLY if SUM of all stats add up to certain number, level++
        // max: 300
        // 60% of 300: 180

        if (this.getHunger() + this.getThirst() + this.cleanliness >= MINLVLUP) {
            this.setLevel(this.getLevel() + 1);
            return true;
        }
        return false;
    }

    @Override
    public void printStats() {
        System.out.println("Level: " + super.getLevel());
        System.out.println("Age: " + super.getAge() + " days");
        System.out.println("Hunger: " + super.getHunger() + "/100");
        System.out.println("Thirst: " + super.getThirst() + "/100");
        System.out.println("Cleanliness: " + getSpecialStat() + "/100");
    }

    @Override
    public void printIcon() {
        System.out.println(" _._     _,-'\"\"`-._\n"
                + "(,-.`._,'(       |\\`-/|\n"
                + "    `-.-' \\ )-`( , o o)\n"
                + "          `-    \\`_`\"'-");
    }

}
