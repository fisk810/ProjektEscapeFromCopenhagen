package com.company;

import java.util.Scanner;

public class Controller{
    //Making input scanner
    Scanner input = new Scanner(System.in);

    //Game starts here
    public void start(){
        Chapters chapter = new Chapters();
        System.out.println(
                ">>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<< \n" +
                        "To start the game, hit any button.\n" +
                        ">>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<");
        input.nextLine();

        //All chapters gathered here
        chapter.chapter_1();
        chapter.chapter_2();
        chapter.chapter_3();
    }


    //Player decides here if they want to restart or quit the game
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