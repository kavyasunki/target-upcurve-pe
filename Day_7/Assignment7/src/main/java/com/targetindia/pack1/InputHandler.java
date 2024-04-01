package com.targetindia.pack1;
import java.util.Scanner;
import java.util.ArrayList;

public class InputHandler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> integerInputs = new ArrayList<>();
        ArrayList<String> nonIntegerInputs = new ArrayList<>();
        int numberOfInputs = 0;
        int numberOfIntegerInputs = 0;
        int numberOfNonIntegerInputs = 0;
        int sumOfIntegerInputs = 0;
        String input = "";

        while (true) {
            try {
                System.out.print("Enter an integer or type 'NO' to stop: ");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("NO")) {
                    break;
                }
                numberOfInputs++;

                int number = Integer.parseInt(input);
                integerInputs.add(number);
                numberOfIntegerInputs++;
                sumOfIntegerInputs += number;
            } catch (NumberFormatException e) {
                numberOfNonIntegerInputs++;
                nonIntegerInputs.add(input);
            }
        }
        System.out.println("Number of inputs = " + numberOfInputs);
        System.out.println("Number of integer inputs = " + numberOfIntegerInputs);
        System.out.println("Number of non-integer inputs = " + numberOfNonIntegerInputs);
        System.out.println("Sum of all integer inputs = " + sumOfIntegerInputs);
        System.out.println("The integer inputs = " + integerInputs);
        System.out.println("The non-integer inputs = " + nonIntegerInputs);
    }
}
