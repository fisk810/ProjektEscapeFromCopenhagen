package com.company;
import java.util.Random;

public class Player {

    private String playerName;
    private int playerHP;
    private int playerAttackMin;
    private int playerAttackMax;

    private Utilities mp7A2 = new Utilities(14, 25, 3);
    private Utilities coltM10 = new Utilities(21, 39, 0);
    private Utilities handgrenade = new Utilities(0);
    private Utilities healingKit = new Utilities(0, 1);


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

    //Bruger playerAttackMax og playerAttackMin, til at udregne og return playerAttack
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

    //Dette bliver brugt til de forskellige våben spilleren har, på nær basis angreb.
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

    public void setMp7A2(Utilities mp7A2) {
        this.mp7A2 = mp7A2;
    }

    public void setColtM10(Utilities coltM10) {
        this.coltM10 = coltM10;
    }

    public void setHandgrenade(Utilities handgrenade) {
        this.handgrenade = handgrenade;
    }

    public void setHealingKit(Utilities healingKit) {
        this.healingKit = healingKit;
    }

    public int getplayerAttackMin() {
        return playerAttackMin;
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