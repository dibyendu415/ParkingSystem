package com.dibyendu;
import commands.Command;
import java.util.Scanner;

//main Starting point of the System

public class User {

    public static void main(String[] args) {
	// write your code here
        Scanner sc = new Scanner(System.in);
        String input;
        do{
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
        }while(!input.equals("exit"));
    }
}
