package com.company;

import java.util.Scanner;
import java.util.Random;

public class Battle {

    //Instance variables
    Scanner input = new Scanner(System.in);
    boolean playerActive = true;

    private Enemy enemy;
    private Player player;

    //Constructor
    public Battle(Enemy enemy, Player player) {
        this.enemy = enemy;
        this.player = player;
    }

    //Methods
    //PlayerTurn allows the player to use attacks via a switch
    public void playerTurn(Enemy enemy, Player player) {
        Random randomRage = new Random();

        //These variables are used for making the "UI". It made it easier to "draw" the UI with shorter identifiers.
        int fA = player.getHealingKit().getFirstAidAmount();
        int aS = player.getHealingKit().getAdrenalineSyringeAmount();
        int gA = player.getHandgrenade().getHandgrenadeAmount();
        int cA = player.getColtM10().getMag();
        int mA = player.getMp7A2().getMag();

        //Player UI
        System.out.println("\nIt's " + player.getPlayerName() + "'s turn!");
        System.out.println("Press a number and hit 'Enter' to play: ");
        System.out.println(
                ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: \n" +
                        " 1 Axe        :: 2 Colt M10   :: 3 MP7A2      :: 4 Handgrenade :: 5 First Aid   :: 6 Adrenaline Syringe  \n" +
                        "   DMG: 5-25  ::   DMG: 21-39 ::   DMG: 14-25 ::   DMG: 100    ::   Heal: 65    ::   Heal: 35            \n" +
                        "              ::   Ammo: " + cA + "    ::   Ammo: " + mA + "    ::   Left: " + gA + "     ::   Left: " + fA + "     ::   Left: " + aS + " \n" +
                        ":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: \n");

        //Player commands
        while (playerActive) {
            String playerInput = input.nextLine();
            switch (playerInput) {

                //First attack
                case "1" -> {
                    //Axe 5-25 damage
                    int enemyHPPlaceholder = enemy.getEnemyHP();
                    enemy.setEnemyHP(enemy.getEnemyHP() - player.getPlayerAttack());
                    //If the player manages to crit, the enemy gets more rage.
                    if ((enemyHPPlaceholder - enemy.getEnemyHP()) > player.getplayerAttackMax()) {
                        enemy.setEnemyRage(enemy.getEnemyRage() + randomRage.nextInt(11 + 1));
                    }else{
                        enemy.setEnemyRage(enemy.getEnemyRage() + randomRage.nextInt(5 + 1));
                    }
                    playerActive = false;
                }
                case "2" -> {
                    //Colt M10 21-39 damage
                    int enemyHPPlaceholder = enemy.getEnemyHP();
                    enemy.setEnemyHP(enemy.getEnemyHP() - player.getPlayerAttackWeapon(player.getColtM10()));
                    //If the player manages to crit, the enemy gets more rage.
                    if ((enemyHPPlaceholder - enemy.getEnemyHP()) > player.getColtM10().getWeaponDamageMax()) {
                        enemy.setEnemyRage(enemy.getEnemyRage() + randomRage.nextInt(16 + 1));
                    }else{
                        enemy.setEnemyRage(enemy.getEnemyRage() + randomRage.nextInt(9 + 1));
                    }
                    playerActive = false;
                }
                case "3" -> {
                    //MP7A2 14-25 damage
                    int enemyHPPlaceholder = enemy.getEnemyHP();
                    enemy.setEnemyHP(enemy.getEnemyHP() - player.getPlayerAttackWeapon(player.getMp7A2()));
                    //If the player manages to crit, the enemy gets more rage.
                    if ((enemyHPPlaceholder - enemy.getEnemyHP()) > player.getMp7A2().getWeaponDamageMax()) {
                        enemy.setEnemyRage(enemy.getEnemyRage() + randomRage.nextInt(21 + 1));
                    }else{
                        enemy.setEnemyRage(enemy.getEnemyRage() + randomRage.nextInt(11 + 1));
                    }
                    playerActive = false;

                }
                case "4" -> {
                    //Grenade with 100 damage
                    if (player.getHandgrenade().getHandgrenadeAmount() > 0) {
                        enemy.setEnemyHP(enemy.getEnemyHP() - player.getHandgrenade().getHANDGRENADE());
                        System.out.print(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  " + player.getPlayerName() + " threw a grenade: 100 damage!!\n");
                        enemy.setEnemyRage(enemy.getEnemyRage() + randomRage.nextInt(50 + 1));
                        player.getHandgrenade().setHandgrenadeAmount(player.getHandgrenade().getHandgrenadeAmount() - 1);
                    }else{
                        System.out.println("You're out of handgrenades");
                    }
                    playerActive = false;

                }
                case "5" -> {
                    //First aid 65+ healing
                    player.getHealingKit().firstAidKit(player);
                    playerActive = false;
                }
                case "6" -> {
                    //Adrenaline syringe 35+ healing
                    player.getHealingKit().adrenalineSyringe(player);
                    playerActive = false;
                }
            }
        }
    }

    //This method takes rewards from a defeated enemy and hands it to the player
    public void victoryReward() {
        //Getting rewards from the enemy and giving it to the player
        player.getMp7A2().setMag(player.getMp7A2().getMag() + enemy.getMpMags());
        player.getColtM10().setMag(player.getColtM10().getMag() + enemy.getColtMags());
        player.getHandgrenade().setHandgrenadeAmount((player.getHandgrenade().getHandgrenadeAmount() + enemy.getGrenadeAmount()));
        player.getHealingKit().setFirstAidAmount(player.getHealingKit().getFirstAidAmount() + enemy.getAidAmount());
        player.getHealingKit().setAdrenalineSyringeAmount(player.getHealingKit().getAdrenalineSyringeAmount() + enemy.getSyringeAmount());
    }

    //This methods controls the battles turns
    public void turnController(Enemy enemy, Player player) {
        //A while loop that controls whether it is the player or the enemy's turn. The loop stops once the player or the enemy's health
        //reaches 0
        while (enemy.getEnemyHP() > 0 && player.getPlayerHP() > 0) {

            playerTurn(enemy, player);

            System.out.println(""+
                            "███████╗███╗   ██╗███████╗███╗   ███╗██╗   ██╗" + "  ::::::::                           \n" +
                            "██╔════╝████╗  ██║██╔════╝████╗ ████║╚██╗ ██╔╝" + "  :: Name: " + enemy.getEnemyName() + "  \n" +
                            "█████╗  ██╔██╗ ██║█████╗  ██╔████╔██║ ╚████╔╝ " + "  ::   HP: " + enemy.getEnemyHP() + "    \n" +
                            "██╔══╝  ██║╚██╗██║██╔══╝  ██║╚██╔╝██║  ╚██╔╝  " + "  :: RAGE: " + enemy.getEnemyRage() + "  \n" +
                            "███████╗██║ ╚████║███████╗██║ ╚═╝ ██║   ██║   " + "  ::::::::                           \n");
            enemy.getRandomShouts();
            if (enemy.getEnemyHP() > 0) {
                System.out.println(enemy.getEnemyName() + " just attacked you!");
                player.setPlayerHP(player.getPlayerHP() - enemy.getEnemyATK());
                System.out.println(""+
                                "██╗   ██╗ ██████╗ ██╗   ██╗" + "  ::::::::                                \n" +
                                "╚██╗ ██╔╝██╔═══██╗██║   ██║" + "  :: Name: " + player.getPlayerName() + " \n" +
                                " ╚████╔╝ ██║   ██║██║   ██║" + "  ::   HP: " + player.getPlayerHP() + "   \n" +
                                "  ╚██╔╝  ██║   ██║██║   ██║" + "  ::::::::                                \n" +
                                "   ██║   ╚██████╔╝╚██████╔╝" );
            }
            playerActive = true;
        }
        //if either the enemy or the player reaches 0 health turnController ends
        if (player.getPlayerHP() > 0 && enemy.getEnemyHP() <= 0) {
            System.out.println(enemy.getEnemyName() + ": " + enemy.getRandomDeathShouts());
            System.out.println("\nThe enemy has been defeated!");
            System.out.println("Enemy overkilled by: " + Math.abs(enemy.getEnemyHP()) + " damage! \n");
            victoryReward();
            playerActive = true;
        }
        else{
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

