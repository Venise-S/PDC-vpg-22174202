/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package virtualpetgame;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.sql.SQLException;

/**
 *
 * @author stamv
 */
public class PetManagerTest {

    private PetManager petManager;

    @Before
    public void setUp() {
        try {
            petManager = new PetManager();
            // make sure db is reset before testing
            petManager.resetDatabase();
            petManager.initializeDatabase();
        } catch (Exception e) {
            fail("init failed: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        try {
            if (petManager != null) {
                petManager.resetDatabase();
                petManager.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("Teardown failed: " + e.getMessage());
        }
    }

    @Test
    public void testAddPet() {
        System.out.println("addPet");
        Pet pet = new Canine("test", 100, 100, 100);
        petManager.addPet(pet);
        assertEquals(1, petManager.getNumPets());
    }

    @Test
    public void testUpdatePet() {
        System.out.println("updatePet");
        Pet pet = new Canine("test", 100, 100, 100);
        petManager.addPet(pet);
        pet.setHunger(80);
        petManager.updatePet(pet);
        Pet[] pets = petManager.getPetsArray();
        assertEquals(80, pets[0].getHunger());
    }

    @Test
    public void testRemovePet() {
        System.out.println("removePet");
        Pet pet = new Canine("test", 100, 100, 100);
        petManager.addPet(pet);
        petManager.removePet(1);
        assertEquals(0, petManager.getNumPets());
    }

    @Test
    public void testGetNumPets() {
        System.out.println("getNumPets");
        Pet pet1 = new Canine("test", 100, 100, 100);
        Pet pet2 = new Feline("test2", 100, 100, 100);
        petManager.addPet(pet1);
        petManager.addPet(pet2);
        assertEquals(2, petManager.getNumPets());
    }

    @Test
    public void testGetPetsArray() {
        System.out.println("getPetsArray");
        Pet pet1 = new Canine("test", 100, 100, 100);
        Pet pet2 = new Feline("test2", 100, 100, 100);
        petManager.addPet(pet1);
        petManager.addPet(pet2);
        Pet[] pets = petManager.getPetsArray();
        assertEquals(2, pets.length);
        assertEquals("test", pets[0].getName());
        assertEquals("test2", pets[1].getName());
    }

    @Test
    public void testStartStatDecrease() throws InterruptedException {
        System.out.println("startStatDecrease");
        Pet pet = new Canine("test", 100, 100, 100);
        petManager.addPet(pet);
        petManager.startStatDecrease();
        Thread.sleep(11000); // Sleep a bit more than the interval to ensure the stats decrease
        Pet[] pets = petManager.getPetsArray();
        assertTrue(pets[0].getHunger() < 100);
        petManager.stopStatDecrease();
    }

    @Test
    public void testDecrementPetStats() {
        System.out.println("decrementPetStats");
        Pet pet = new Canine("test", 100, 100, 100);
        petManager.addPet(pet);
        petManager.decrementStats();
        Pet[] pets = petManager.getPetsArray();
        assertEquals(99, pets[0].getHunger());
    }

    @Test
    public void testClose() {
        System.out.println("close");
        petManager.close();
        try {
            assertTrue(petManager.getConnection().isClosed());
        } catch (SQLException e) {
            fail("Database close check failed: " + e.getMessage());
        }
    }
}
