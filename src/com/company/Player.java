package com.company;
import java.util.Random;

public class Player {

    //Instance variables
    private String playerName;
    private int playerHP;
    private int playerAttackMin;
    private int playerAttackMax;

    private Utilities mp7A2 = new Utilities(14, 25, 3);
    private Utilities coltM10 = new Utilities(21, 39, 0);
    private Utilities handgrenade = new Utilities(0);
    private Utilities healingKit = new Utilities(0, 1);

    //Constructor
    public Player(String playerName, int playerHP, int playerAttackMin, int playerAttackMax) {
        this.playerName = playerName;
        this.playerHP = playerHP;
        this.playerAttackMin = playerAttackMin;
        this.playerAttackMax = playerAttackMax;
    }

    //Getters and setters
    public int getPlayerHP() {
        return playerHP;
    }
    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    //Uses playerAttackMax and PlayerAttackMin, to calculate and return playerAttack
    public int getPlayerAttack() {
        Random random = new Random();
        if (Math.random() > 0.60) {
            int crit = random.nextInt(playerAttackMax - playerAttackMin) + playerAttackMax;
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  " + playerName + "'s attacked: Critical hit: " + crit);
            return crit;
        }
        else{
            int normalHit = random.nextInt(playerAttackMax - playerAttackMin) + playerAttackMin;
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  " + playerName + "'s attacked: Normal hit: " + normalHit);
            return normalHit;
        }
    }
    //This method gets the damage from the player's weapon utilities and returns it
    public int getPlayerAttackWeapon(Utilities weapon){
        Random random = new Random();

        if (Math.random() > 0.70 && weapon.getMag() > 0) {
            //Crit attack
            int crit = random.nextInt(weapon.getWeaponDamageMax() - weapon.getWeaponDamageMin()) + weapon.getWeaponDamageMax();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  " + playerName + " shoots: Critical hit: " + crit);
            weapon.setMag(weapon.getMag() - 1);
            return crit;
        }
        else if(Math.random() > 0.15 && weapon.getMag() > 0) {
            //Base attack
            int nonCrit = random.nextInt(weapon.getWeaponDamageMax() - weapon.getWeaponDamageMin()) + weapon.getWeaponDamageMin();
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  " + playerName + " shoots: Normal hit: " + nonCrit);
            weapon.setMag(weapon.getMag() - 1);
            return nonCrit;
        }
        else if(weapon.getMag() == 0){
            //Out of ammo
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  " + "You're out of ammo");
            return 0;
        }
        else{
            //Attack missed
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  " + playerName + " shoots: missed!");
            weapon.setMag(weapon.getMag() - 1);
            return 0;
        }
    }

    public Utilities getHealingKit() {
        return healingKit;
    }

    public int getplayerAttackMax() {
        return playerAttackMax;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Utilities getMp7A2() {
        return mp7A2;
    }

    public Utilities getColtM10() {
        return coltM10;
    }

    public Utilities getHandgrenade() {
        return handgrenade;
    }

}