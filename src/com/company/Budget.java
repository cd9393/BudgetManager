package budget;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Budget {
    private ArrayList<String> purchases;
    private Map<String, ArrayList<String>> categorisedPurchases;
    private double balance;

    public Budget() {
        this.purchases = new ArrayList<String>();
        this.categorisedPurchases = new LinkedHashMap<>();
        categorisedPurchases.put("Food", new ArrayList<>());
        categorisedPurchases.put("Clothes", new ArrayList<>());
        categorisedPurchases.put("Entertainment", new ArrayList<>());
        categorisedPurchases.put("Other", new ArrayList<>());
        this.balance = 0;
    }

    public void addPurchaseToCategory(String category, String purchase) {
        ArrayList<String> purchases = this.categorisedPurchases.get(category);
        purchases.add(purchase);
        this.categorisedPurchases.put(category,purchases);
        addPurchase(purchase);
    }

    public void addPurchase(String purchase) {
        double purchaseAmount = getPrice(purchase);
        setBalance(this.balance - purchaseAmount);
        this.purchases.add(purchase);
        System.out.println("Purchase was added!");

    }

    public Map<String, ArrayList<String>> getCategorisedPurchases() {
        return categorisedPurchases;
    }

    public ArrayList<String> getPurchases() {
        return purchases;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Double returnTotal(ArrayList<String> purchases) {
        Double total = 0.00;

        for (int i = 0; i < purchases.size(); i++) {
            total += getPrice(purchases.get(i));
        }
        return total;
    }

    public void showPurchasesByCategory(String category) {
        ArrayList<String> purchases = this.categorisedPurchases.get(category);
        if (purchases.size() == 0) {
            System.out.println("The purchase list is empty!");
            System.out.println();
        } else {
            for (int i = 0; i < purchases.size(); i++) {
                System.out.println(purchases.get(i));
            }
            System.out.println("Total sum: $" + returnTotal(purchases));
            System.out.println();
        }
    }

    public void showAllPurchases() {
        if (this.purchases.size() == 0) {
            System.out.println("The purchase list is empty");
            System.out.println();
        } else {
            for (int i = 0; i < this.purchases.size(); i++) {
                System.out.println(this.purchases.get(i));
            }
            System.out.println("Total sum: $" + returnTotal(this.purchases));
            System.out.println();
        }
    }

    private double getPrice(String input) {
        int indexOfDollar = input.indexOf("$");
        String substring = input.substring(indexOfDollar + 1);
        return Double.parseDouble(substring);
    }
}
