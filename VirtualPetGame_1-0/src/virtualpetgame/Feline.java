/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

/**
 *
 * @author stamv
 */
public class Feline extends Pet {

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
}
