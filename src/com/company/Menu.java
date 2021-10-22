package com.company;

import java.util.Scanner;

public class Menu {
    Budget budget;
    boolean isRunning;
    public Menu() {
        this.budget = new Budget();
        this.isRunning = true;
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
                        addIncome(scanner);
                        break;
                    case 2 :
                        addPurchase(scanner);
                        break;
                    case 3 :
                        showPurchases();
                        break;
                    case 4 :
                        showBalance();
                        break;
                    case 0 :
                        exitApp();
                        break;
                    default :
                        System.out.println("Please choose a valid selection");
                        break;
                }

            }
        }
    }

    private void exitApp() {
        setRunning(false);
        System.out.println("Bye!");
        System.out.println();
    }

    private void showBalance() {
        System.out.println("Balance: $" + this.budget.getBalance());
        System.out.println();
    }

    private void showPurchases() {
        this.budget.showPurchases();
        System.out.println();
    }

    private void addPurchase(Scanner scanner) {
        System.out.println("Enter Purchase name:");
        scanner.nextLine();
        String purchaseName = scanner.nextLine();
        System.out.println("Enter it's price:");
        String purchasePrice = "$".concat(scanner.nextLine());
        this.budget.addPurchase(purchaseName.concat(" ").concat(purchasePrice));
        System.out.println();
    }

    private void addIncome(Scanner scanner) {
        System.out.println("Enter income:");
        int income = scanner.nextInt();
        this.budget.setBalance(this.budget.getBalance() + income);
        System.out.println("Income was added!");
        System.out.println();
    }

    private static void displayMenu() {
        System.out.println("Choose your action:");
        System.out.println("1) Add Income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("0) Exit");
        System.out.println();
    }
}
