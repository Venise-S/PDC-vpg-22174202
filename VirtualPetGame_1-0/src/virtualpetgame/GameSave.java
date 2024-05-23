/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package virtualpetgame;

import todelete.VPGameCUI;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author stamv
 */
public class GameSave {

    public static void saveGame(VPGameCUI game, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(game);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static VPGameCUI loadGame(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            VPGameCUI game = (VPGameCUI) ois.readObject();
            return game;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static void deleteGame(String fileName) {
        File saveFile = new File(fileName);
        if (saveFile.exists()) {
            if (saveFile.delete()) {
                System.out.println("Game deleted");
            } else {
                System.err.println("Failed to delete the game file");
            }
        }
        System.exit(0); // exit to prevent error since object no longer exists.
    }

}
