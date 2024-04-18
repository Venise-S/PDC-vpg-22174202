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
public class Canine extends Pet implements Serializable {

    private final int MINLVLUP = 180; // 60% of 300
    
    private int exercise;

    public Canine(String name, int hunger, int thirst, int exercise) {
        super(name, hunger, thirst);
        this.exercise = exercise;
        super.setType("Canine");
    }

    @Override
    public int getSpecialStat() {
        return exercise;
    }

    @Override
    public void setSpecialStat(int exercise) {
        this.exercise = exercise;
                if (this.exercise <= 0) {
            this.exercise = 0;
        }
        else if (this.exercise >= 100) {
            this.exercise = 100;
        }
    }

    // game based change stats
    @Override
    public boolean levelUp() { // run in for loop for all pets , sleep function
        // ONLY if SUM of all stats add up to certain number, level++
        // max: 300
        // 60% of 300: 180

        if (this.getHunger() + this.getThirst() + this.exercise >= MINLVLUP) {
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
        System.out.println("Exercise: " + getSpecialStat() + "/100");
    }

    @Override
    public void printIcon() {
        System.out.println("  __    __\n"
                + "o-''))_____\\\\\n"
                + "\"--__/ * * * )\n"
                + "c_c__/-c____/");
    }

}
