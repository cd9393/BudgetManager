package com.company;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // write your code here
        ArrayList<String> userInput = buildUserInput();
        outPutUserInput(userInput);
        Double total = returnTotal(userInput);
        System.out.println("total: $" +  total);

    }

    static Double returnTotal(ArrayList<String> userInput) {
        Double total = 0.00;

        for (int i = 0; i < userInput.size(); i++) {
            total += getPrice(userInput.get(i));
        }
        return total;
    }

    static void outPutUserInput(ArrayList<String> userInput) {
        for (int i = 0; i < userInput.size(); i++) {
            System.out.println(userInput.get(i));
        }
    }

    static ArrayList<String> buildUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<String> userInput = new ArrayList<String>();
            while(scanner.hasNext()) {
                userInput.add(scanner.nextLine());
            }
            return userInput;
        }
    }

    static double getPrice(String input) {
        int indexOfDollar = input.indexOf("$");
        String substring = input.substring(indexOfDollar + 1);
        return Double.parseDouble(substring);
    }
}
