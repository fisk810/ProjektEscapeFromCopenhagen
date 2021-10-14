package com.company;

import java.util.Random;

public class Enemy {

    //Instance variables
    private String enemyName;
    private int enemyHP;
    private int enemyRage;

    private int enemyATKMin;
    private int enemyATKMax;
    private String[] shouts;
    private String[] deathShouts;

    private int mpMags;
    private int coltMags;
    private int grenadeAmount;
    private int aidAmount;
    private int syringeAmount;


    //Constructor
    public Enemy(String enemyName, int enemyHP, int enemyRage, int enemyATKMin, int enemyATKMax, String[] shouts, String[] deathShouts, int mpMags, int coltMags, int grenadeAmount, int aidAmount, int syringeAmount) {
        this.enemyName = enemyName;
        this.enemyHP = enemyHP;
        this.enemyRage = enemyRage;
        this.enemyATKMin = enemyATKMin;
        this.enemyATKMax = enemyATKMax;
        this.shouts = shouts;
        this.deathShouts = deathShouts;
        this.mpMags = mpMags;
        this.coltMags = coltMags;
        this.grenadeAmount = grenadeAmount;
        this.aidAmount = aidAmount;
        this.syringeAmount = syringeAmount;

    }
    //These methods get a random shout for the enemy to use. The shout arrays are created in Chapters.java
    public void getRandomShouts(){
        if(enemyHP > 0) {
            int randomShout = new Random().nextInt(shouts.length);
            System.out.println(getEnemyName() +": " + shouts[randomShout]);
        }
    }
    public String getRandomDeathShouts(){
        int randomDeathShout = new Random().nextInt(deathShouts.length);
        return deathShouts[randomDeathShout];
    }

    //Getters and Setters
    //Uses enemyAttackMax and EnemyAttackMin, to calculate and return enemy attack
    public int getEnemyATK(){
        Random random = new Random();
        if (Math.random() > 0.70 && enemyRage < 30) {
            //30% chance for Crit attack
            int crit = random.nextInt(enemyATKMax - enemyATKMin) + enemyATKMax;
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>  " + enemyName + "'s attack: Critical hit: " + crit);
            return crit;
        }
        else if (Math.random() > 0.45 && enemyRage >= 30) {
            //55% chance for RAGE attack if rage is 30 or higher
            int rageCrit = random.nextInt(enemyATKMax - enemyATKMin) + 18;
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>  " + enemyName + "'s attack: RAGE hit: " + rageCrit);
            enemyRage = enemyRage - 30;
            return rageCrit;
        }
        else if(enemyRage >= 30){
            //RAGE attack missed
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>  " + enemyName + "'s attack: RAGE hit missed!");
            enemyRage = enemyRage  - 30;
            return enemyRage;
        }
        else if(Math.random() > 0.15) {
            //Base attack
            int nonCrit = random.nextInt(enemyATKMax - enemyATKMin) + enemyATKMin;
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>  " + enemyName + "'s attack: Normal hit: " + nonCrit);
            return nonCrit;
        }
        else{
            //Attack missed
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>  " + enemyName + "'s attack: missed!");
            return 0;
        }

    }

    public String getEnemyName() {
        return enemyName;
    }

    public int getEnemyHP() {
        return enemyHP;
    }
    public void setEnemyHP(int enemyHP) {
        this.enemyHP = enemyHP;
    }

    public int getEnemyRage() {
        return enemyRage;
    }
    public void setEnemyRage(int enemyRage) {
        this.enemyRage = enemyRage;
    }

    public int getMpMags() {
        return mpMags;
    }

    public int getColtMags() {
        return coltMags;
    }

    public int getGrenadeAmount() {
        return grenadeAmount;
    }

    public int getAidAmount() {
        return aidAmount;
    }

    public int getSyringeAmount() {
        return syringeAmount;
    }
}