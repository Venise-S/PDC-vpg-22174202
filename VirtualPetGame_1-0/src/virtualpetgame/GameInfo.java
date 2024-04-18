/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

import java.io.Serializable;
import java.util.Random;


public class GameInfo implements Serializable {
    private int day;
    private int food;
    private boolean foragedToday;
    
    public final int FOOD_MULTIPLIER = 5;
    
    private final Random rand = new Random();

    public GameInfo(int food) {
        this.foragedToday = false;
        day = 1;
        this.food = food;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
        foragedToday = false;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
        foragedToday = true;
    }

    public boolean isForagedToday() {
        return foragedToday;
    }
    
    public int getFOOD_MULTIPLIER() {
        return FOOD_MULTIPLIER;
    }
    
    public void decreaseFood() {
        setFood(this.food - rand.nextInt(5)+2);
    }
    
    public void printInfo() {
        System.out.println("Day: " + getDay());
        System.out.println("Food storage: " + getFood());
        if (isForagedToday()) {
            System.out.println("Already foraged for today.");
        }
        else {
            System.out.println("Not yet foraged today.");
        }
    }
    
}
