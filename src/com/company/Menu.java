package budget;

import java.util.Scanner;

public class Menu {
    budget.Budget budget;
    boolean isRunning;
    public Menu() {
        this.budget = new budget.Budget();
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
                        this.budget.showPurchasesByCategory("Food");
                        break;
                    case 2:
                        this.budget.showPurchasesByCategory("Clothes");
                        break;
                    case 3:
                        this.budget.showPurchasesByCategory("Entertainment");
                        break;
                    case 4:
                        this.budget.showPurchasesByCategory("Other");
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
        for (String category : this.budget.getCategorisedPurchases().keySet()) {
            System.out.println(menuNumber + ") " + category);
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
            System.out.println("Enter Purchase name:");
            scanner.nextLine();
            String purchaseName = scanner.nextLine();
            System.out.println("Enter it's price:");
            String purchasePrice = "$".concat(scanner.nextLine());
            this.budget.addPurchaseToCategory(category, purchaseName.concat(" ").concat(purchasePrice));
            //this.budget.addPurchase(purchaseName.concat(" ").concat(purchasePrice));
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
                    category = "Food";
                    break;
                case 2:
                    category = "Clothes";
                    break;
                case 3:
                    category = "Entertainment";
                    break;
                case 4:
                    category =  "Other";
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
        for (String category : this.budget.getCategorisedPurchases().keySet()) {
            System.out.println(menuNumber + ") " + category);
            menuNumber++;
        }
        System.out.println("5) Back");
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
