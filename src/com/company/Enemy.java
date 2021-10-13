package com.company;

import java.util.Random;

public class Enemy {

    //Instance variables
    private String enemyName;
    private int enemyHP;
    private int enemyRage;

    private int enemyATKMin;
    private int enemyATKMax;


    //Constructor
    public Enemy(String enemyName, int enemyHP, int enemyRage, int enemyATKMin, int enemyATKMax) {

        this.enemyName = enemyName;
        this.enemyHP = enemyHP;
        this.enemyRage = enemyRage;
        this.enemyATKMin = enemyATKMin;
        this.enemyATKMax = enemyATKMax;

    }

    //Getters and Setters
    public String getEnemyName() {
        return enemyName;
    }
    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
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

    //bruger enemyattackmax og enemyattackmin, til at udregne og returne enemy attack
    //der er 30% chance for at der kommer "critical hit"
    public int getEnemyATK() {
        Random random = new Random();
        if (Math.random() > 0.70 && enemyRage < 30) {
            //Crit attack
            int crit = random.nextInt(enemyATKMax - enemyATKMin) + enemyATKMax;
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>  " + enemyName + "'s attack: Critical hit: " + crit);
            return crit;
        }
        else if (Math.random() > 0.45 && enemyRage > 30) {
            //RAGE attack
            int rageCrit = random.nextInt(enemyATKMax - enemyATKMin) + 18;
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>  " + enemyName + "'s attack: RAGE hit: " + rageCrit);
            enemyRage = enemyRage - 30;
            return rageCrit;
        }
        else if(enemyRage > 30){
            //RAGE attack missed
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>  " + enemyName + "'s attack: RAGE hit missed!");
            int enemyRage = getEnemyRage() - 30;
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

}