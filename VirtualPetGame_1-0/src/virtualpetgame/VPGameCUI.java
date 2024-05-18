/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package virtualpetgame;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author stamv
 */
public class VPGameCUI extends VPGame implements VPGameInterface, Serializable { // handles user interface and interactions with user
    private FormattingCUI fo;
    private final Random rand = new Random();
    
    public VPGameCUI() {
        super(); 
    }

    // check if inputted text is 'x'
    public void checkIfX(String input) {
        // this is run every input. only checks if an x was entered then continues. returns to main menu if true
        if (input.trim().compareToIgnoreCase("x") == 0) {
            menu();
        }
    }

    // ----menus and methods from menu----
    @Override
    public void menu() {
        // main menu


        // displays pet stats first
        if (getPetManager().getNumPets() == 0) {
            System.out.println("Welcome to Virtual Pet Game!\n");
            System.out.println("Across the wilderness you can find certain creatures that closely resemble domesticated animals. ");
            System.out.println("Don't be fooled! These creatures are far from your average cat or dog.");
            System.out.println("Your responsibility is to find and take care of these creatures. \n");
            System.out.println("Here is your first pet...");
            
            getPetManager().newPet();
        }

        System.out.println("Welcome to Virtual Pet game!\n");

        // give information.

        getGameInfo().printInfo();
        System.out.println("Number of pets: " + getPetManager().getNumPets());

                /**System.out.println("What would you like to do?");
                System.out.println("1. Select a pet and choose a pet action");
                System.out.println("2. Sleep to next day");
                System.out.println("3. Forage for food");
                System.out.println("4. Pause (or exit)");*/

                int choice = 0;

                switch (choice) {
                    case 1:
                        System.out.println("Chosen: Interact with a pet");
                        petSelectorMenu();
                        break;
                    case 2:
                        System.out.println("Chosen: Sleep");
                        sleep();
                        break;
                    case 3:
                        System.out.println("Chosen: Find items");
                        findItems();
                        break;
                    case 4:
                        System.out.println("Chosen: Pause Game");
                        pauseGame();
                        break;
                }
            
        
    }

    // from menu -> select a pet
    @Override
    public void petSelectorMenu() {
        // display pets user can select
        fo.printBreak();
        System.out.println("Select a pet by inputting the corresponding number: (x to quit)");
        for (int i = 0; i < getPetManager().getPets().length && getPetManager().getPets()[i] != null; i++) {
            System.out.println((i + 1) + ". " + getPetManager().getPets()[i].getName());
        }

        boolean isValid = false;

        String choice;

        // get input from user base on index (1, 2 etc.)
        while (!isValid) {
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine();
            checkIfX(choice);
            try {
                int petIndex = Integer.parseInt(choice) - 1;

                if (petIndex >= 0 && petIndex < getPetManager().getPets().length && getPetManager().getPets()[petIndex] != null) {
                    isValid = true;
                    Pet selectedPet = getPetManager().getPets()[petIndex];
                    System.out.println("Selected pet: " + selectedPet.getName());
                    petActionMenu(selectedPet);
                } else {
                    System.out.println("Invalid pet selection. Please try again.");
                }
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Please enter a number or 'x' to exit.");
            }
        }

    }

    @Override
    public void petActionMenu(Pet selectedPet) {
        // after selecting a pet, runs.
        // interact with selected pet.
        fo.printBreak();
        selectedPet.printIcon();
        fo.printHLine();

        System.out.println(selectedPet.getName() + " Current Stats: ");
        selectedPet.printStats();
        fo.printHLine();

        System.out.println("What would you like to do with " + selectedPet.getName() + "?");
        System.out.println("1. Feed");
        System.out.println("2. Give water");
        if (selectedPet.getType().compareTo("Feline") == 0) {
            System.out.println("3. Pamper");
        } else {
            System.out.println("3. Play");
        }
        System.out.println("4. Release");
        System.out.println("press b to go back, or x to exit");

        Scanner sc = new Scanner(System.in);
        String action = sc.nextLine();
        checkIfX(action);

        if (action.equalsIgnoreCase("b")) {
            petSelectorMenu();
        }
        switch (action) { // determine user input and updates pet stats
            case "1":
                if (selectedPet.getHunger() == selectedPet.getMAXSTAT()) {
                    System.out.println(selectedPet.getName() + " is full!");
                } else if (getGameInfo().getFood() == 0) {
                    System.out.println("You do not have enough food.");
                } else {
                    getGameInfo().decreaseFood();
                    selectedPet.feed();
                    System.out.println("Food storage is now at " + getGameInfo().getFood());
                }
                break;
            case "2":
                selectedPet.giveWater();
                break;
            case "3":
                selectedPet.setSpecialStat(selectedPet.getMAXSTAT()); // instead of incrementing, sets to maximum
                break;
            case "4":
                if (getPetManager().getNumPets() <= 1) {
                    getPetManager().removePet(selectedPet);
                    System.out.println("You have successfully released this pet out into the wild.");
                    fo.waitForEnter();
                    menu();
                } else {
                    System.out.println("You try to release this pet... \nBut it came back to you!");
                    System.out.println("(You can't release your only pet.)");
                    fo.waitForEnter();
                }
                break;
            default:
                System.out.println("Invalid input. Please try again.");
                break;
        }

        // repeat action menu recursively
        petActionMenu(selectedPet);
    }

    @Override
    public void sleep() {
        // sleep interface 

        getGameInfo().setDay(getGameInfo().getDay() + 1);

        synchronized (getPetManager().getPets()) {
            for (int i = 0; i < getPetManager().getPets().length && getPetManager().getPets()[i] != null; i++) {
                boolean didLevel = getPetManager().getPets()[i].levelUp();
                if (didLevel == true) {
                    System.out.println(getPetManager().getPets()[i].getName() + " leveled up to Level " + getPetManager().getPets()[i].getLevel());
                }

                getPetManager().getPets()[i].setHunger(getPetManager().getPets()[i].getHunger() - rand.nextInt(getMAXSTATCHANCE()));
                getPetManager().getPets()[i].setThirst(getPetManager().getPets()[i].getThirst() - rand.nextInt(getMAXSTATCHANCE()));
                getPetManager().getPets()[i].setSpecialStat(getPetManager().getPets()[i].getSpecialStat() - rand.nextInt(getMAXSTATCHANCE()));
                getPetManager().getPets()[i].setAge(getPetManager().getPets()[i].getAge() + 1);
            }
        }

        int Evchance = rand.nextInt(getEVENTCHANCE());

        // if 0, no event to occur
        if (Evchance == 0) {
            // autosave game
            getPetManager().stopStatDecrease();
            GameSave.saveGame(this, "savegame.dat");
            getPetManager().startStatDecrease();
            menu();
        }
        System.out.println("While you were sleeping, a random event has occurred!");
        getEventSelector().eventSwitch();

        // autosave game
        getPetManager().stopStatDecrease(); // used this instead of synchronized
        GameSave.saveGame(this, "savegame.dat");
        getPetManager().startStatDecrease();

        menu();
    }

    @Override
    public void findItems() {
        // gives random amount of food (once a day)
        Scanner scan = new Scanner(System.in);

        fo.printBreak();
        if (getGameInfo().isForagedToday() == true) {
            System.out.println("You have already foraged for today!");
            menu();
        }

        // ask user if they want to attempt to find a pet
        System.out.println("While go out, you wonder if you can find another pet here.");
        System.out.println("Do you want try search for a pet? (y/n)");
        String ans = scan.nextLine();
        if (ans.compareToIgnoreCase("y") == 0) {
            if (rand.nextInt(2) == 0) {
                System.out.println("Search successful!");
                getPetManager().newPet();
            } else {
                System.out.println("You couldn't find a pet.");
            }
        } else {
            System.out.println("You decide to not look for a pet.");
        }

        int num = (getPetManager().getNumPets() + 1) * getGameInfo().FOOD_MULTIPLIER;
        System.out.println("You go out to forage for food for your pets.");
        fo.printHLine();

        System.out.println("Previous Amount: " + getGameInfo().getFood());
        getGameInfo().setFood(getGameInfo().getFood() + num);
        System.out.println("New Amount: " + getGameInfo().getFood());

        fo.waitForEnter();
        fo.printHLine();
        int Evchance = rand.nextInt(getEVENTCHANCE());

        // chance for no event to occur
        if (Evchance != 0) {
            menu();
        }

        // forage action specific line
        System.out.println("While you were out foraging, a random event has occurred!");
        getEventSelector().eventSwitch();

        menu();
    }

    @Override
    public void pauseGame() {
        //pauses decrement of stats (thread)
        fo.printHLine();
        Scanner scan = new Scanner(System.in);

        getPetManager().stopStatDecrease();
        System.out.println("Game paused!");
        System.out.println("Enter 'exit' to save and exit the game.");
        System.out.println("Enter 'delete' to delete the current save and exit the game. ");
        System.out.println("Enter any key to continue the game.");
        String input = scan.nextLine();

        if (input.trim().compareToIgnoreCase("exit") == 0) {
            GameSave.saveGame(this, "savegame.dat");
            System.exit(0);
        } else if (input.trim().compareToIgnoreCase("delete") == 0) {
            GameSave.deleteGame("savegame.dat");
        } else {
            getPetManager().startStatDecrease();
            menu();
        }
    }
}
