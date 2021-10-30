package budget;

import com.company.Category;
import com.company.Purchase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

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
}
