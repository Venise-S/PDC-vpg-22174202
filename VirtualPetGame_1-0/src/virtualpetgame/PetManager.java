/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author stamv
 */
public class PetManager implements Serializable {
    private final Random rand = new Random();
    
    private final transient FormattingCUI fo = new FormattingCUI();
    
    private final int MAX_NAME_LENGTH = 30;
    private final int MAX_PETS = 15;
    private final Pet[] pets;
    
    private boolean running;

    public PetManager() {
        this.pets = new Pet[MAX_PETS];
    }

    public Pet[] getPets() {
        return this.pets;
    }

    public int getNumPets() {
        int count = 0;

        for (Pet pet : pets) {
            if (pet != null) {
                count++;
            }
        }
        return count;
    }

    public void removePet(Pet petToRemove) {
        // remove selected pet from pets[] then move all pets above index down (remove null)
        int indexToRemove = -1;
        for (int i = 0; i < pets.length; i++) {
            if (pets[i] == petToRemove) {
                indexToRemove = i;
                break;
            }
        }
        if (indexToRemove != -1) {
            // Remove the pet at indexToRemove
            for (int i = indexToRemove; i < pets.length - 1; i++) {
                pets[i] = pets[i + 1];
            }
            pets[pets.length - 1] = null; // Set the last element to null
        }
    }

    public void renamePet() {
        System.out.println("Which pet would you like to rename?");
    }

public void startStatDecrease() {
        if (!running) {
            running = true;
            Thread thread = new Thread(() -> {
                while (running) {
                    decrementPetStats();
                    try {
                        Thread.sleep(5000); // sleeping for 5 seconds
                    } catch (InterruptedException e) {
                        System.err.println("Error: Thread sleep failed.");
                    }
                }
            });
            thread.start();
        }
    }

    public void stopStatDecrease() {
        running = false;
    }

    private void decrementPetStats() {
        if (pets.length == 0) {
        } else {
            for (int i = 0; i < pets.length && pets[i] != null; i++) {
                pets[i].setHunger(pets[i].getHunger() - 1);
                pets[i].setThirst(pets[i].getThirst() - 1);
                pets[i].setSpecialStat(pets[i].getSpecialStat() - 1);
            }
        }
    }
    
        public void newPet() {
        // runs during random event and first running game
        // gives two types of pet, canine or feline
        fo.printBreak();
        Scanner scanner = new Scanner(System.in);

        System.out.println("You have found a new pet!");

        // if at maximum amount of pets, exit this method
        if (getNumPets() == getPets().length - 1) {
            System.out.println("You have too many pets and cannot receive a new one. Max: " + MAX_PETS);
            System.out.println("Automatically releasing this pet.");
            return;
        }

        String input;
        boolean validInput = false;

        // define random pet
        int randomNumber = rand.nextInt(2);
        Pet newPet;
        if (randomNumber == 0) {
            newPet = new Feline("", 100, 100, 100); // at full stats
        } else {
            newPet = new Canine("", 100, 100, 100);
        }

        newPet.printIcon();

        // ask user if they want to keep pet
        if (getNumPets() > 0) {
            while (!validInput) {
                System.out.println("Do you want to keep this pet? (y/n)");
                input = scanner.nextLine().trim().toLowerCase();
                switch (input) {
                    case "y":
                    case "yes":
                        System.out.println("You have decided to keep the pet!");
                        validInput = true;
                        break;
                    case "n":
                    case "no":
                        System.out.println("You have decided not to keep the pet.");
                        return;
                    default:
                        System.out.println("Invalid input. Please enter 'y' for yes or 'n' for no.");
                        break;
                }
            }
        }

        // prompt user to enter pet name
        System.out.println("Enter the name of the new " + newPet.getType() + " pet:");
        String name = "";
        Scanner scan = new Scanner(System.in);
        while (name.trim().isEmpty()) {
            name = scan.nextLine().trim();
            if (name.isEmpty() || name.length() == MAX_NAME_LENGTH) {
                System.out.println("Please enter a valid name:");
            }
        }
        newPet.setName(name);

        // update pets
        for (int i = 0; i < getPets().length; i++) {
            if (getPets()[i] == null) {
                getPets()[i] = newPet;
                break;
            }
        }

        System.out.println(newPet.getName() + " has been transported to your home.");
        fo.waitForEnter();
    }
}
