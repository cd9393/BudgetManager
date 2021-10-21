package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Budget {
    private ArrayList<String> purchases;
    private float balance;

    public Budget() {
        this.purchases = new ArrayList<String>();
        this.balance = 0;
    }

    public void addPurchase(String purchase) {
        this.purchases.add(purchase);
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public Double returnTotal() {
        Double total = 0.00;

        for (int i = 0; i < this.purchases.size(); i++) {
            total += getPrice(this.purchases.get(i));
        }
        return total;
    }

    public void showPurchases() {
        if (this.purchases.size() == 0) {
            System.out.println("There are no purchases to show");
        } else {
            for (int i = 0; i < this.purchases.size(); i++) {
                System.out.println(this.purchases.get(i));
            }
            System.out.println("Total sum: $" + returnTotal());
        }
    }

    private ArrayList<String> buildPurchasesFromUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<String> userInput = new ArrayList<String>();
            while(scanner.hasNext()) {
                userInput.add(scanner.nextLine());
            }
            return userInput;
        }
    }

    private double getPrice(String input) {
        int indexOfDollar = input.indexOf("$");
        String substring = input.substring(indexOfDollar + 1);
        return Double.parseDouble(substring);
    }
}
