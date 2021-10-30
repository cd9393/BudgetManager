package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Scanner;

public class Menu {
    budget.Budget budget;
    private final File DATABASE;
    boolean isRunning;
    public Menu() {
        this.budget = new budget.Budget();
        DATABASE = new File("purchases.txt");
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
                        showPurchases(scanner);
                        break;
                    case 4 :
                        showBalance();
                        break;
                    case 5 :
                        save();
                        break;
                    case 6 :
                        load();
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

    private void load() {
        try (Scanner scanner = new Scanner(DATABASE)) {
            BigDecimal budget = new BigDecimal(scanner.nextLine());
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String [] input = line.split(";");
                this.budget.addPurchase(Category.valueOf(input[0]) , input[1], input[2]);
            }
            this.budget.setBalance(budget);
            System.out.println("Purchases were loaded!");
            System.out.println();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void save() {
        try (PrintWriter printWriter = new PrintWriter(DATABASE)) {
            printWriter.println(this.budget.getBalance());
            for (Purchase purchase : budget.getPurchases()) {
                printWriter.println(purchase.getCategory() + ";" + purchase.getDescription() + ";" + purchase.getPrice());
            }
            System.out.println("Purchases were saved!");
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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

    private void showPurchases(Scanner scanner) {

        if (this.budget.getPurchases().size() == 0) {
            System.out.println("The purchase list is empty!");
        } else {
            boolean isMenuRunning = true;
            while (isMenuRunning) {
                showPurchaseMenu();
                int category = scanner.nextInt();
                switch (category) {
                    case 1:
                        this.budget.showPurchasesByCategory("FOOD");
                        break;
                    case 2:
                        this.budget.showPurchasesByCategory("CLOTHES");
                        break;
                    case 3:
                        this.budget.showPurchasesByCategory("ENTERTAINMENT");
                        break;
                    case 4:
                        this.budget.showPurchasesByCategory("OTHER");
                        break;
                    case 5:
                        this.budget.showAllPurchases();
                        break;
                    case 6:
                        isMenuRunning = false;
                        break;
                    default:
                        System.out.println("Please make a valid selection");
                        break;
                }
            }
        }
        System.out.println();
    }

    private void showPurchaseMenu() {
        System.out.println("Choose the type of Purchases");
        int menuNumber = 1;
        for (Category category : Category.values()) {
            System.out.println(menuNumber + ") " + category.toString());
            menuNumber++;
        }
        System.out.println("5) All");
        System.out.println("6) Back");
        System.out.println();
    }

    private void addPurchase(Scanner scanner) {
        String category = null;
        do {
            category = choosePurchaseCategory(scanner);
            if ("Exit".equals(category)) {
                break;
            }
            Category purchaseCategory = Category.valueOf(category);
            System.out.println("Enter Purchase name:");
            scanner.nextLine();
            String purchaseName = scanner.nextLine();
            System.out.println("Enter it's price:");
            String purchasePrice = "$".concat(scanner.nextLine());
            this.budget.addPurchase(purchaseCategory, purchaseName, purchasePrice);
            System.out.println();
        } while(!"Exit".equals(category));

    }

    private String choosePurchaseCategory(Scanner scanner) {
        String category = null;
        boolean isWrongChoice = false;
        do {
            showAddPurchaseMenu();
            int chosenOption = scanner.nextInt();
            switch (chosenOption) {
                case 1:
                    category = "FOOD";
                    break;
                case 2:
                    category = "CLOTHES";
                    break;
                case 3:
                    category = "ENTERTAINMENT";
                    break;
                case 4:
                    category =  "OTHER";
                    break;
                case 5:
                    category = "Exit";
                    break;
                default:
                    System.out.println("Please make a valid selection");
                    isWrongChoice = true;
                    break;
            }
        }while(isWrongChoice);
        return category;
    }

    private void showAddPurchaseMenu() {
        System.out.println("Choose the type of Purchases");
        int menuNumber = 1;
        for (Category category : Category.values()) {
            System.out.println(menuNumber + ") " + category.toString());
            menuNumber++;
        }
        System.out.println("5) Back");
        System.out.println();
    }

    private void addIncome(Scanner scanner) {
        System.out.println("Enter income:");
        scanner.nextLine();
        String input = scanner.nextLine();
        BigDecimal income = new BigDecimal(input);
        this.budget.setBalance(this.budget.getBalance().add(income));
        System.out.println("Income was added!");
        System.out.println();
    }

    private static void displayMenu() {
        System.out.println("Choose your action:");
        System.out.println("1) Add Income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("5) Save");
        System.out.println("6) Load");
        System.out.println("0) Exit");
        System.out.println();
    }
}
