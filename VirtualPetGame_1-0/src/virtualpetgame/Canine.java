/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

/**
 *
 * @author stamv
 */
public class Canine extends Pet {
    
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
}
