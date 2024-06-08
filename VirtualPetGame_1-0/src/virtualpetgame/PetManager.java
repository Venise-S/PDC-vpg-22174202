/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

import java.sql.*;
import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 *
 * @author stamv
 */
public class PetManager {

    // for managing pets database
    private Connection connection;
    private final int MAX_PETS = 15;
    private boolean running;

    public PetManager() {
        try {
            // for running in jar file: saves db to fixed location
            /* String dbUrl = "jdbc:derby:" + System.getProperty("user.home") + "/Downloads/22174202-veniseDB/petDB;create=true";*/
            // for running in netbeans:
            String dbUrl = "jdbc:derby:petDB;create=true";
            connection = DriverManager.getConnection(dbUrl);
            initializeDatabase();
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
    }
    
    public Connection getConnection() {
        return this.connection;
    }

    public void initializeDatabase() {
        // prevent db from using table before it exists
        try {
            if (!tableExists("pets")) {
                createTable();
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
    }

    // drops pet table
    public void resetDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:derby:" + System.getProperty("user.home") + "/Downloads/22174202-veniseDB/petDB;create=true"); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE pets");
        } catch (SQLException e) {
            System.err.println("sql error: " + e.getMessage());
        }
    }

    // return true if table exists
    public boolean tableExists(String tableName) throws SQLException {
        DatabaseMetaData dbmd = connection.getMetaData();
        try (ResultSet resultSet = dbmd.getTables(null, null, tableName.toUpperCase(), null)) {
            return resultSet.next();
        }
    }

    // create pets table
    private void createTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE pets (id INT PRIMARY KEY, name VARCHAR(255), type VARCHAR(255), hunger INT, thirst INT, specialStat INT)");
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
    }

    // add pet to db based on given pet
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
            System.out.println("sql error: " + e.getMessage());
        }
    }

    // update given pet to db
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
            System.out.println("sql error: " + e.getMessage());
        }
    }

    // remove pet based on given id
    public void removePet(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM pets WHERE id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
    }

    // returns int of number of pets
    public int getNumPets() {
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM pets")) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
        return 0;
    }

    // adds all pets from db to array
    public Pet[] getPetsArray() {
        Pet[] pets = new Pet[MAX_PETS];
        int i = 0;

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM pets")) {
            // loop through all pets in db
            while (resultSet.next() && i < MAX_PETS) {
                // get values from db
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                int hunger = resultSet.getInt("hunger");
                int thirst = resultSet.getInt("thirst");
                int specialStat = resultSet.getInt("specialStat");

                // set pet to strings got
                Pet pet;
                switch (type.toLowerCase()) {
                    case "canine":
                        pet = new Canine(name, hunger, thirst, specialStat);
                        break;
                    case "feline":
                        pet = new Feline(name, hunger, thirst, specialStat);
                        break;
                    default:
                        System.err.println("pet type error");
                        continue;
                }

                pet.setPetManager(this);
                // add to array
                pets[i++] = pet;
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        return Arrays.copyOf(pets, i);
    }

    public void startStatDecrease() {
        if (!running) {
            running = true;
            Thread thread = new Thread(() -> {
                while (running) {
                    decrementStats();
                    try {
                        Thread.sleep(10000); // sleeping for 10 seconds
                    } catch (InterruptedException e) {
                        System.err.println("error: thread sleep failed.");
                    }
                }
            });
            thread.start();
        }
    }

    public void stopStatDecrease() {
        running = false;
    }

    // run in startStatDecrease thread
    public void decrementStats() {
        // -1 to all stats
        String updateQuery = "UPDATE pets SET hunger = hunger - 1, thirst = thirst - 1, specialStat = specialStat - 1 WHERE hunger > 1 OR thirst > 1 OR specialStat > 1";
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }

        for (Pet pet : getPetsArray()) {
            updatePet(pet);
        }
    }

    // closes connection to db
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
    }

    //all pets lose 50% hunger
    public void loseHunger() {
        decreaseStat("hunger");
    }

    // all pets lose 50% thirst
    public void loseThirst() {
        decreaseStat("thirst");
    }

    // decrease based on stat given
    private void decreaseStat(String stat) {
        String updateQuery = "UPDATE pets SET " + stat + " = " + stat + " / 2 WHERE " + stat + " > 1";
        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sql error: " + e.getMessage());
        }
    }
}
