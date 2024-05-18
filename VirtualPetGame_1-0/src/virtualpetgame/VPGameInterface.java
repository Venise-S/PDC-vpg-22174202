/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package virtualpetgame;

/**
 *
 * @author stamv
 */
public interface VPGameInterface {
    public void menu();
    public void petSelectorMenu();
    public void petActionMenu(Pet selectedPet);
    public void sleep();
    public void findItems();
    public void pauseGame();
}
