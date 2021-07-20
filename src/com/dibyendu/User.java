package com.dibyendu;
import commands.Command;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

//main Starting point of the System

public class User {

    public static void main(String[] args) throws IOException {

    // For File Input
        String input;
        FileInputStream file = new FileInputStream("input.txt");
        Scanner sc = new Scanner(file);
        while(sc.hasNextLine())
        {
            input = sc.nextLine();
            String[] commands = input.split(" ");
            Command c;
            try {
                c = Command.valueOf(commands[0]);
                c.executeCommand(commands);
            }catch ( Exception e)
            {
                System.out.println("Command not valid");
            }
        }

//        For System Input
//        Scanner sc = new Scanner(System.in);
//        String input;
//        do{
//            input = sc.nextLine();
//            String[] commands = input.split(" ");
//            Command c;
//            try {
//                c = Command.valueOf(commands[0]);
//                c.executeCommand(commands);
//            }catch ( Exception e)
//            {
//                System.out.println("Command not valid");
//            }
//        }while(!input.equals("exit"));
    }
}
