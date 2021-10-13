package com.company;

import java.util.Scanner;

public class Controller {
    Scanner input = new Scanner(System.in);

    //Game starts here
    public void start(){
        Chapters chapter = new Chapters();
        System.out.println("To start the game, hit any button");
        input.nextLine();
        //chapter.chapter_1();
        //chapter.chapter_2();
        chapter.chapter_3();

    }
    //Players decides here if they want to restart or quit the game
    public void restart() {
        boolean decide = true;
        while (decide) {
            String playerInput = input.nextLine();
            switch (playerInput) {

                case "y" -> {
                    start();
                    decide = false;
                }
                case "q" -> {
                    System.out.println("Quitting the game! Have a nice day... noob :-)");
                    System.exit(0);
                }
            }
        }
    }
}
