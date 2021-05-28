package io;

import java.util.Scanner;

public class Input {

    public static Scanner sc = new Scanner(System.in);

    public static Integer inputInt(){
        int intInput;
        while(true){
            String input = sc.nextLine();
            try{
                intInput = Integer.parseInt(input);
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Invalid choice: (" + input + ") try again!");
            }
        }
        return intInput;
    }

    public static String inputString(){
        return sc.nextLine();
    }
}