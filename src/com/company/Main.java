package com.company;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> userInput = new ArrayList<String>();
        while(scanner.hasNext()) {
            userInput.add(scanner.nextLine());
        }
        Double total = 0.00;

        for (int i = 0; i < userInput.size(); i++) {
            total += getPrice(userInput.get(i));
            System.out.println(userInput.get(i));
        }

        System.out.println("total: $" +  total);

    }

    static double getPrice(String input) {
        int indexOfDollar = input.indexOf("$");
        String substring = input.substring(indexOfDollar + 1);
        return Double.parseDouble(substring);
    }
}
