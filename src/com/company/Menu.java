package com.company;

import java.util.Scanner;

public class Menu {
    Budget budget;
    boolean isRunning;
    public Menu() {
        this.budget = new Budget();
        this.isRunning = true;
    }

    private  void addIncome(int income) {
        this.budget.setBalance(this.budget.getBalance() + income);
    }

    private void addPurchase(String purchase) {
        this.budget.addPurchase(purchase);
    }

    private void showPurchases() {
        this.budget.showPurchases();
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public void runApp() {
        try (Scanner scanner = new Scanner(System.in)) {
        while (isRunning()) {
            displayMenu();
            int input = scanner.nextInt();
            switch (input) {
                case 1 :
                    System.out.println("Enter income:");
                    int income = scanner.nextInt();
                    this.budget.setBalance(this.budget.getBalance() + income);
                    System.out.println();
                    break;
                case 2 :
                    System.out.println("Enter Purchase name:");
                    String purchaseName = scanner.nextLine();
                    System.out.println("Enter it's price:");
                    String purchasePrice = scanner.nextLine();
                    this.budget.addPurchase(purchaseName.concat(" ").concat(purchasePrice));
                    System.out.println();
                    break;
                case 3 :
                    this.budget.showPurchases();
                    System.out.println();
                    break;
                case 4 :
                    System.out.println("Balance: $" + this.budget.getBalance());
                    System.out.println();
                    break;
                case 0 :
                    setRunning(false);
                    break;
                default :
                    System.out.println("Please choose a valid selection");
                    break;

            }

            }
        }
    }

    private static void displayMenu() {
        System.out.println("Choose your action:");
        System.out.println("1) Add Income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("0) Exit");
    }
}
