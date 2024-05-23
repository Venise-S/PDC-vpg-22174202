/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

import java.io.Serializable;
import java.sql.*;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author stamv
 */
public class PetManager implements Serializable {

    private Connection connection;
    private final int MAX_PETS = 15;
    private boolean running;

    public PetManager() {
        try {
            // Connect to the embedded Java DB (Derby)
            connection = DriverManager.getConnection("jdbc:derby:petDB;create=true");

            // Create the pets table if it doesn't exist
            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTableIfNotExists() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE pets (id INT PRIMARY KEY, name VARCHAR(255), type VARCHAR(255), hunger INT, thirst INT, specialStat INT)");
        }
    }

    /*public void addPet(String name, String type) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO pets (id, name, type, hunger, thirst, specialStat) VALUES (?, ?, ?, ?, ?, ?)")) {
            // Check if there's space for a new pet
            if (getNumPets() < MAX_PETS) {
                // Generate a unique ID (you might want to improve this)
                int id = getNumPets() + 1;
                statement.setInt(1, id);
                statement.setString(2, name);
                statement.setString(3, type);
                statement.setInt(4, 100); // initial hunger
                statement.setInt(5, 100); // initial thirst
                statement.setInt(6, 100); // initial special stat
                statement.executeUpdate();
            } else {
                JOptionPane.showMessageDialog(null, "You are at the maximum number of pets.", "Pet Released", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println("SQL error");
        }
    }*/
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

            } else {
                JOptionPane.showMessageDialog(null, "You are at the maximum number of pets.", "Pet Released", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println("SQL error");
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

    public Pet[] getPetsArray() { // do not write to pets array
        Pet[] pets = new Pet[MAX_PETS];
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM pets")) {
            int i = 0;
            while (resultSet.next() && i < MAX_PETS) {
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                int hunger = resultSet.getInt("hunger");
                int thirst = resultSet.getInt("thirst");
                int specialStat = resultSet.getInt("specialStat");

                if (type.compareToIgnoreCase("Canine") == 0) {
                    pets[i++] = new Canine(name, hunger, thirst, specialStat);
                } else if (type.compareToIgnoreCase("Feline") == 0) {
                    pets[i++] = new Feline(name, hunger, thirst, specialStat);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pets;
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
        String updateQuery = "UPDATE pets SET hunger = hunger - 1, thirst = thirst - 1, specialStat = specialStat - 1 WHERE hunger > 0 OR thirst > 0 OR specialStat > 0";
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
