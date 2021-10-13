package com.company;

import java.util.Scanner;
import java.util.Random;

public class Battle {

    //instansvariable
    Scanner input = new Scanner(System.in);
    boolean playerActive = true;
    Enemy enemy;
    Player player;
    Utilities utilities;
    int rewardCounterMPMag = -1;
    int rewardCounterColtMag = 0;
    int rewardCounterGrenade = 1;
    int rewardCounterAid = 2;
    int rewardCounterSyringe = 3;

    //Constructor
    public Battle(Enemy enemy, Player player, Utilities utilities) {
        this.enemy = enemy;
        this.player = player;
        this.utilities = utilities;
    }

    //Metoder
    public void playerTurn(Enemy enemy, Player player, Utilities utilities) {
        Random randomRage = new Random();

        int fA = player.getHealingKit().getFirstAidAmount();
        int aS = player.getHealingKit().getAdrenalineSyringeAmount();
        int gA = player.getHandgrenade().getHandgrenadeAmount();
        int cA = player.getColtM10().getMag();
        int mA = player.getMp7A2().getMag();

        System.out.println("\nIt's " + player.getPlayerName() + "'s turn!");
        System.out.println("Press a number and hit 'Enter' to play: ");
        System.out.println(
                "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::  \n" +
                        "     1     ::      2       ::      3      ::       4       ::       5       ::         6            \n" +
                        "    Axe    ::   Colt M10   ::    MP7A2    ::  Handgrenade  ::   First Aid   :: Adrenaline Syringe   \n" +
                        " DMG: 1-15 ::   Ammo: " + cA + "    ::   Ammo: " + mA + "   ::  Left: " + gA + "      ::   Left: " + fA + "     :: Left: " + aS + " \n" +
                        "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::  \n");

        while (playerActive) {
            String playerInput = input.nextLine();
            switch (playerInput) {

                //First attack
                case "1" -> {
                    int enemyHPPlaceholder = enemy.getEnemyHP();
                    enemy.setEnemyHP(enemy.getEnemyHP() - player.getPlayerAttack());

                    if ((enemyHPPlaceholder - enemy.getEnemyHP()) > player.getplayerAttackMax()) {
                        enemy.setEnemyRage(enemy.getEnemyRage() + randomRage.nextInt(11 + 1));
                    } else {
                        enemy.setEnemyRage(enemy.getEnemyRage() + randomRage.nextInt(5 + 1));
                    }
                    playerActive = false;
                }
                case "2" -> {
                    int enemyHPPlaceholder = enemy.getEnemyHP();
                    enemy.setEnemyHP(enemy.getEnemyHP() - player.getPlayerAttackWeapon(player.getColtM10()));

                    if ((enemyHPPlaceholder - enemy.getEnemyHP()) > player.getColtM10().getWeaponDamageMax()) {
                        enemy.setEnemyRage(enemy.getEnemyRage() + randomRage.nextInt(16 + 1));
                    } else {
                        enemy.setEnemyRage(enemy.getEnemyRage() + randomRage.nextInt(9 + 1));
                    }
                    playerActive = false;

                }
                case "3" -> {
                    int enemyHPPlaceholder = enemy.getEnemyHP();
                    enemy.setEnemyHP(enemy.getEnemyHP() - player.getPlayerAttackWeapon(player.getMp7A2()));

                    if ((enemyHPPlaceholder - enemy.getEnemyHP()) > player.getMp7A2().getWeaponDamageMax()) {
                        enemy.setEnemyRage(enemy.getEnemyRage() + randomRage.nextInt(21 + 1));
                    } else {
                        enemy.setEnemyRage(enemy.getEnemyRage() + randomRage.nextInt(11 + 1));
                    }
                    playerActive = false;

                }
                case "4" -> {
                    if (player.getHandgrenade().getHandgrenadeAmount() > 0) {
                        enemy.setEnemyHP(enemy.getEnemyHP() - player.getHandgrenade().getHANDGRENADE());
                        System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  " + player.getPlayerName() + " threw a grenade: 100 damage!!\n");
                        enemy.setEnemyRage(enemy.getEnemyRage() + randomRage.nextInt(50 + 1));
                        player.getHandgrenade().setHandgrenadeAmount(player.getHandgrenade().getHandgrenadeAmount() - 1);
                    } else {
                        System.out.println("You're out of handgrenades");
                    }
                    playerActive = false;

                }
                case "5" -> {
                    player.getHealingKit().firstAidKit(player);
                    playerActive = false;
                }
                case "6" -> {
                    player.getHealingKit().adrenalineSyringe(player);
                    playerActive = false;
                }
            }
        }
    }

    //Spiller belønning

    public void victoryReward(int enemyCounterOne, int enemyCounterTwo, int enemyCounterThree, int enemyCounterFour, int enemyCounterFive) {
        int rewardMP = rewardCounterMPMag + enemyCounterOne;                //Arr index: 0,5,10,15
        int rewardColt = rewardCounterColtMag + enemyCounterTwo;            //Arr index: 1,6,11,16
        int rewardGrenade = rewardCounterGrenade + enemyCounterThree;       //Arr index: 2,7,12,17
        int rewardAid = rewardCounterAid + enemyCounterFour;                //Arr index: 3,8,13,18
        int rewardSyringe = rewardCounterSyringe + enemyCounterFive;        //Arr index: 4,9,14,19

        int[] playerArr = new int[8];
        //First enemy defeated
        playerArr[0] = player.getMp7A2().getMag() + 2;
        playerArr[3] = player.getHealingKit().getFirstAidAmount() + 1;
        playerArr[2] = player.getHandgrenade().getHandgrenadeAmount() + 1;
        player.getMp7A2().setMag(playerArr[rewardMP]);
        player.getHealingKit().setFirstAidAmount(playerArr[rewardAid]);
        player.getHandgrenade().setHandgrenadeAmount(playerArr[rewardGrenade]);

        //Second enemy defeated
        playerArr[7] = player.getHandgrenade().getHandgrenadeAmount() + 1;
        player.getHandgrenade().setHandgrenadeAmount(playerArr[rewardGrenade]);

        //Third enemy defeated
        //No reward

        //Fourth enemy defeated


        //Counter for rewards
        rewardCounterMPMag = rewardMP;
        rewardCounterColtMag = rewardColt;
        rewardCounterGrenade = rewardGrenade;
        rewardCounterAid = rewardAid;
        rewardCounterSyringe = rewardSyringe;

    }

    /* Et while loop som styrer, om det er spiller eller fjendens tur. Loopet stopper når spillerens,
    eller fjendens liv rammer 0 eller mindre
    */
    public void turnController(Enemy enemy, Player player) {

        while (enemy.getEnemyHP() > 0 && player.getPlayerHP() > 0) {

            playerTurn(enemy, player, utilities);

            System.out.println(
                    "███████╗███╗   ██╗███████╗███╗   ███╗██╗   ██╗" + "  ::::::::                           \n" +
                            "██╔════╝████╗  ██║██╔════╝████╗ ████║╚██╗ ██╔╝" + "  :: Name: " + enemy.getEnemyName() + "  \n" +
                            "█████╗  ██╔██╗ ██║█████╗  ██╔████╔██║ ╚████╔╝ " + "  ::   HP: " + enemy.getEnemyHP() + "    \n" +
                            "██╔══╝  ██║╚██╗██║██╔══╝  ██║╚██╔╝██║  ╚██╔╝  " + "  :: RAGE: " + enemy.getEnemyRage() + "  \n" +
                            "███████╗██║ ╚████║███████╗██║ ╚═╝ ██║   ██║   " + "  ::::::::                           \n");

            if (enemy.getEnemyHP() > 0) {
                System.out.println(enemy.getEnemyName() + " just attacked you!");
                player.setPlayerHP(player.getPlayerHP() - enemy.getEnemyATK());
                System.out.println(
                        "██╗   ██╗ ██████╗ ██╗   ██╗" + "  ::::::::                                \n" +
                                "╚██╗ ██╔╝██╔═══██╗██║   ██║" + "  :: Name: " + player.getPlayerName() + " \n" +
                                " ╚████╔╝ ██║   ██║██║   ██║" + "  ::   HP: " + player.getPlayerHP() + "   \n" +
                                "  ╚██╔╝  ██║   ██║██║   ██║" + "  ::::::::                                \n" +
                                "   ██║   ╚██████╔╝╚██████╔╝");
            }
            playerActive = true;
        }
        if (player.getPlayerHP() > 0 && enemy.getEnemyHP() <= 0) {

            System.out.println("The enemy has been defeated!");
            System.out.println("Enemy overkilled by: " + enemy.getEnemyHP() + " damage! \n"); //TODO kig lige på den her print
            victoryReward(1, 1, 1, 1, 1);
            playerActive = true;
        } else {
            Controller controller = new Controller();
            System.out.println(
                    "                                    \n" +
                            "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                            "███▀▀▀██┼███▀▀▀███┼███▀█▄█▀███┼██▀▀▀\n" +
                            "██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼█┼┼┼██┼██┼┼┼\n" +
                            "██┼┼┼▄▄▄┼██▄▄▄▄▄██┼██┼┼┼▀┼┼┼██┼██▀▀▀\n" +
                            "██┼┼┼┼██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██┼┼┼\n" +
                            "███▄▄▄██┼██┼┼┼┼┼██┼██┼┼┼┼┼┼┼██┼██▄▄▄\n" +
                            "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n" +
                            "███▀▀▀███┼▀███┼┼██▀┼██▀▀▀┼██▀▀▀▀██▄┼\n" +
                            "██┼┼┼┼┼██┼┼┼██┼┼██┼┼██┼┼┼┼██┼┼┼┼┼██┼\n" +
                            "██┼┼┼┼┼██┼┼┼██┼┼██┼┼██▀▀▀┼██▄▄▄▄▄▀▀┼\n" +
                            "██┼┼┼┼┼██┼┼┼██┼┼█▀┼┼██┼┼┼┼██┼┼┼┼┼██┼\n" +
                            "███▄▄▄███┼┼┼─▀█▀┼┼─┼██▄▄▄┼██┼┼┼┼┼██▄\n" +
                            "┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼┼\n");

            System.out.println("You died");
            System.out.println("Press 'y' and hit enter to play again or press 'q' and hit enter to quit");
            controller.restart();

        }
    }

}



/*
        enemiesDefeated = enemiesDefeated + amountOfEnemiesDefeated;
        if(enemiesDefeated == 1) {
            //Enemy One
            System.out.println(">>>YOUR REWARDS<<< \n1x First aid kit that returns +65 HP \n2x MP7A2 Submachine Gun Ammo");
            System.out.println("These rewards can only be used one time so be careful with how you use them!\n");
            player.getHealingKit().setFirstAidAmount(player.getHealingKit().getFirstAidAmount()+1 );
            player.getMp7A2().setMag(2);
            enemiesDefeated = enemiesDefeated + 1;
        }
        else if (enemiesDefeated == 2){
            //Enemy two
            System.out.println(">>>YOUR REWARDS<<< \n1x Adrenaline Syringe that returns +35 HP \n1x Colt M10 Assault Rifle Ammo");
            System.out.println("These rewards can only be used one time so be careful with how you use them!\n");
            player.getHealingKit().setAdrenalineSyringeAmount(player.getHealingKit().getAdrenalineSyringeAmount() + 1);
            player.getColtM10().setMag(1);
        }
            System.out.println("Press any button to continue.\n");
            System.out.println("Win number increments to: " + enemiesDefeated);
    */