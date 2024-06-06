/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

import java.io.Serializable;
import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author stamv
 */
public class PetManager implements Serializable {

    private Connection connection;
    private final int MAX_PETS = 15;
    public boolean running;

    public PetManager() {
        try {
            connection = DriverManager.getConnection("jdbc:derby:petDB;create=true");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkExistedTable(String name) {
        try {
            DatabaseMetaData dbmd = connection.getMetaData();
            try (Statement statement = connection.createStatement(); ResultSet resultSet = dbmd.getTables(null, null, null, new String[]{"TABLE"})) {
                while (resultSet.next()) {
                    String tableName = resultSet.getString("TABLE_NAME");
                    if (tableName.equalsIgnoreCase(name)) {
                        try {
                            System.out.println(name);
                            String deleteTable = "DROP TABLE " + name;
                            statement.executeUpdate(deleteTable);
                            System.out.println(name + " deleted");
                        } catch (SQLException e) {
                            System.err.println("Error dropping table: " + e.getMessage());
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error getting table metadata: " + e.getMessage());
        }
    }

    public void createTable() {
        checkExistedTable("pets");
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE pets (id INT PRIMARY KEY, name VARCHAR(255), type VARCHAR(255), hunger INT, thirst INT, specialStat INT)");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    public void addPet(Pet pet) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO pets (id, name, type, hunger, thirst, specialStat) VALUES (?, ?, ?, ?, ?, ?)")) {
            if (getNumPets() < MAX_PETS) {
                int id = getNumPets() + 1;
                statement.setInt(1, id);
                statement.setString(2, pet.getName());
                statement.setString(3, pet.getType());
                statement.setInt(4, 100); // initial hunger
                statement.setInt(5, 100); // initial thirst
                statement.setInt(6, 100); // initial special stat
                statement.executeUpdate();

                pet.setPetManager(this);
            } else {
                JOptionPane.showMessageDialog(null, "You are at the maximum number of pets.", "Pet Released", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
    }

    public void updatePet(Pet pet) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE pets SET name = ?, type = ?, hunger = ?, thirst = ?, specialStat = ? WHERE name = ?")) {
            statement.setString(1, pet.getName());
            statement.setString(2, pet.getType());
            statement.setInt(3, pet.getHunger());
            statement.setInt(4, pet.getThirst());
            statement.setInt(5, pet.getSpecialStat());
            statement.setString(6, pet.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removePet(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM pets WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getNumPets() {
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM pets")) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Pet[] getPetsArray() {
        Pet[] pets = new Pet[MAX_PETS];
        int i = 0;

        if (connection == null) {
            System.err.println("DB connection error");
            return null;
        }

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM pets")) {
            while (resultSet.next() && i < MAX_PETS) {
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                int hunger = resultSet.getInt("hunger");
                int thirst = resultSet.getInt("thirst");
                int specialStat = resultSet.getInt("specialStat");

                System.out.println("Retrieved pet from DB: name=" + name + ", type=" + type + ", hunger=" + hunger + ", thirst=" + thirst + ", specialStat=" + specialStat);

                Pet pet;
                switch (type.toLowerCase()) {
                    case "canine":
                        pet = new Canine(name, hunger, thirst, specialStat);
                        break;
                    case "feline":
                        pet = new Feline(name, hunger, thirst, specialStat);
                        break;
                    default:
                        System.err.println("Unknown pet type: " + type);
                        continue;
                }

                pet.setPetManager(this);
                pets[i++] = pet;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Arrays.copyOf(pets, i);
    }

    public void startStatDecrease() {
        if (!running) {
            running = true;
            Thread thread = new Thread(() -> {
                while (running) {
                    decrementPetStats();
                    try {
                        Thread.sleep(10000); // sleeping for 10 seconds
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

        String updateQuery = "UPDATE pets SET hunger = hunger - 1, thirst = thirst - 1, specialStat = specialStat - 1 WHERE hunger > 1 OR thirst > 1 OR specialStat > 1";
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Pet pet : getPetsArray()) {
            updatePet(pet);
        }
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //all pets lose 50% hunger
    public void loseHunger() {
        String updateQuery = "UPDATE pets SET hunger = hunger / 2 WHERE hunger > 0";
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Pet pet : getPetsArray()) {
            pet.setHunger(pet.getHunger() / 2);
            updatePet(pet);
        }
    }

// all pets lose 50% thirst
    public void loseThirst() {
        String updateQuery = "UPDATE pets SET thirst = thirst / 2 WHERE thirst > 0";
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Pet pet : getPetsArray()) {
            pet.setThirst(pet.getThirst() / 2);
            updatePet(pet);
        }
    }

}
