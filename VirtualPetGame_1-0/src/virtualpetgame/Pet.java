/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

/**
 *
 * @author stamv
 */

public abstract class Pet {
    private final int MAX_STAT_NUM = 100;
    private final int INCRNUM = 50;

    private String type;
    private String name;
    private int hunger;
    private int thirst;

    private transient PetManager petManager; // Add a reference to PetManager

    public Pet(String name, int hunger, int thirst) {
        this.name = name;
        this.hunger = hunger;
        this.thirst = thirst;
    }

    public void setPetManager(PetManager petManager) {
        this.petManager = petManager;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        updatePetInDatabase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        updatePetInDatabase();
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
        updatePetInDatabase();
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
        updatePetInDatabase();
    }

    public void feed() {
        setHunger(this.hunger + INCRNUM);
    }

    public void giveWater() {
        setThirst(this.thirst + INCRNUM);
    }

    public abstract void setSpecialStat(int num);

    public abstract int getSpecialStat();

    private void updatePetInDatabase() {
        if (petManager != null) {
            petManager.updatePet(this);
        }
    }
}
