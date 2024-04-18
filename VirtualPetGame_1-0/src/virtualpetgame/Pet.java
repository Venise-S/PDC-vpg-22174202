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
public abstract class Pet implements Serializable {

    private final int MAX_STAT_NUM = 100;
    
    private final int INCRNUM = 50; // increase number
    
    private String type;
    private String name;
    private int level;
    private int hunger;
    private int thirst;
    private int age; 
    
    public Pet(String name, int hunger, int thirst) {
        this.name = name;
        this.hunger = hunger;
        this.thirst = thirst;
        this.age = 1;
        level = 1;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
        if (this.hunger > MAX_STAT_NUM) {
            this.hunger = MAX_STAT_NUM;
        } else if (this.hunger < 0) {
            this.hunger = 0;
        }
    }
    
    public int getMAXSTAT() {
        return MAX_STAT_NUM;
    }

    public int getThirst() {
        return thirst;
    }

    public void setThirst(int thirst) {
        this.thirst = thirst;
        if (this.thirst > MAX_STAT_NUM) {
            this.thirst = MAX_STAT_NUM;
        } else if (this.thirst < 0) {
            this.thirst = 0;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
        public void feed() {
        setHunger(this.hunger + INCRNUM);
    }
    
    public void giveWater() {
        setThirst(this.thirst + INCRNUM);
    }
    
    public void setSpecialStat(int num) {
        // defined in subclass
    }

    // runs on end of day 
    public boolean levelUp() { // run in for loop for all pets , sleep function
        // ONLY if SUM of all stats add up to certain number, level++
        // max: 300
        // 60% of 300: 180

        if (this.getHunger() + this.getThirst() >= 120) {
            this.setLevel(this.getLevel() + 1);
            return true;
         
        }
        return false;
    }

    public void printStats() {
        // defined in subclass
    }
    
    public void printIcon() {
        //defined in subclass
    }
    
    public int getSpecialStat() {
        // defined in subclass
        return 0;
    }
    
}
