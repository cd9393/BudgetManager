package com.company;

import com.company.Category;
import com.company.Purchase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Budget {
    private ArrayList<Purchase> purchases;
    private BigDecimal balance;

    public Budget() {
        this.purchases = new ArrayList<Purchase>();
        balance = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN);
    }


    public void addPurchase(Category category, String description, String price) {
        BigDecimal purchaseAmount = getPrice(price);
        setBalance(this.balance.subtract(purchaseAmount));
        this.purchases.add(new Purchase(description, category, purchaseAmount));
        System.out.println("Purchase was added!");

    }

    public ArrayList<Purchase> getPurchases() {
        return purchases;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void showPurchasesByCategory(String category) {
        Category purchaseCategory = Category.valueOf(category);
        ArrayList<Purchase> purchases = filterPurchases(purchaseCategory);
        if (purchases.size() == 0) {
            System.out.println("The purchase list is empty!");
            System.out.println();
        } else {
            for (int i = 0; i < purchases.size(); i++) {
                System.out.println(purchases.get(i).getDescription() + " " + "$" + purchases.get(i).getPrice());
            }
            System.out.println("Total sum: $" + returnTotal(purchases));
            System.out.println();
        }
    }

    private ArrayList<Purchase> filterPurchases(Category category) {
        ArrayList<Purchase> filteredPurchases = new ArrayList<>();
        for (Purchase purchase : this.purchases) {
            if (purchase.getCategory() == category) {
                filteredPurchases.add(purchase);
            }
        }
        return filteredPurchases;
    }

    public void showAllPurchases() {
        if (this.purchases.size() == 0) {
            System.out.println("The purchase list is empty");
            System.out.println();
        } else {
            for (int i = 0; i < this.purchases.size(); i++) {
                System.out.println(this.purchases.get(i).getDescription() + " " + "$"+this.purchases.get(i).getPrice());
            }
            System.out.println("Total sum: $" + returnTotal(this.purchases));
            System.out.println();
        }
    }

    public BigDecimal returnTotal(ArrayList<Purchase> purchases) {
        BigDecimal total = BigDecimal.ZERO;

        for (Purchase purchase : purchases) {
            total = total.add(purchase.getPrice());
        }
        return total;
    }

    private BigDecimal getPrice(String input) {
        int indexOfDollar = input.indexOf("$");
        String substring = input.substring(indexOfDollar + 1);
        return new BigDecimal(substring);
    }

    public void  sortPurchases(ArrayList<Purchase> purchases) {

        Purchase tempVar;
        for (int i = 0; i < purchases.size() -1; i++)
        {
            for(int j = 0; j < purchases.size() -i-1; j++)
            {
                if(purchases.get(j).getPrice().compareTo(purchases.get(j + 1).getPrice()) < 0)
                {
                    tempVar = purchases.get(j + 1);
                    purchases.set(j + 1,purchases.get(j));
                    purchases.set(j, tempVar);
                }
            }
        }
    }

    public void sortByType() {
        List<String> categoryTotals = getCategoryTotals();
        String tempVar;
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < categoryTotals.size() -1; i++)
        {
            total = total.add(getPriceFromString(categoryTotals.get(i)));
            for(int j = 0; j < categoryTotals.size() -i-1; j++)
            {

                if(getPriceFromString(categoryTotals.get(j)).compareTo(getPriceFromString(categoryTotals.get(j + 1))) < 0)
                {
                    tempVar = categoryTotals.get(j + 1);
                    categoryTotals.set(j + 1,categoryTotals.get(j));
                    categoryTotals.set(j, tempVar);
                }
            }
        }
        System.out.println("Types:");
        for (String categoryTotal : categoryTotals) {
            System.out.println(categoryTotal);
        }
        System.out.println("Total sum: $" + total);
        System.out.println();
    }

    private BigDecimal getPriceFromString(String string) {
        int indexOfDollar = string.indexOf("$") + 1;
        String price = string.substring(indexOfDollar);
        return new BigDecimal(price);
    }

    private List<String> getCategoryTotals() {
        List<String> categoryTotals = new ArrayList<>();
        for (Category category : Category.values()) {
            ArrayList<Purchase> purchases = filterPurchases(category);
            if (purchases.size() == 0) {
                String categoryFormatted = category.toString().substring(0,1) + category.toString().substring(1).toLowerCase();
                String string = categoryFormatted.concat(" - $0");
                categoryTotals.add(string);
            } else {
                BigDecimal total = BigDecimal.ZERO;
                for (Purchase purchase : purchases) {
                    total = total.add(purchase.getPrice());
                }
                String categoryFormatted = category.toString().substring(0,1) + category.toString().substring(1).toLowerCase();
                String string = categoryFormatted.concat(" - $").concat(total.toString());
                categoryTotals.add(string);
            }
        }
        return categoryTotals;
    }

    public void sortCertainType(Category category) {
        ArrayList<Purchase> purchases = filterPurchases(category);
        sortPurchases(purchases);
        displayPurchases(purchases);
    }

    private void displayPurchases(ArrayList<Purchase> purchases) {
        if (purchases.size() == 0) {
            System.out.println("The purchase list is empty!");
            System.out.println();
        } else {
            for (int i = 0; i < purchases.size(); i++) {
                System.out.println(purchases.get(i).getDescription() + " " + "$" + purchases.get(i).getPrice());
            }
            System.out.println("Total sum: $" + returnTotal(purchases));
            System.out.println();
        }
    }
}

