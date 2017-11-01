package core.entities;

import core.util.MonthGrapher;

import java.util.*;

/**
 * Created by ryan on 10/17/17.
 */
public class BudgetMonth {
    private ArrayList<Purchase> purchasesList = new ArrayList<>();
    private String monthDate;
    private HashMap<String, Double> categories = new HashMap<>();
    private double amountSpendingFormonth;

    public BudgetMonth(String date, double allocateMoney) {
        this.monthDate = date;
        this.amountSpendingFormonth = allocateMoney;
    }

    public void addPurchase(Purchase purchase){
        purchasesList.add(purchase.getId(), purchase);
    }

    public void addNewCategoryBudgetIfNew(String category, double budget) {
        if (categories.keySet().contains(category)){
            return;
        }
        categories.put(category, budget);
    }

    public Purchase purchaseFromID(int id){
        return purchasesList.get(id);
    }

    public List<Purchase> purchasesOfCategory(String category){
        if (categories.keySet().contains(category)) {
            ArrayList<Purchase> purchaseFromCategory = new ArrayList<>();
            for (Purchase purchase : purchasesList) {
                if (purchase.getCategory().equals(category))
                    purchaseFromCategory.add(purchase);
            }
            return purchaseFromCategory;
        }
        return new ArrayList<>();
    }

    public double categoryBudget(String category){
        return categories.get(category);
    }

    public String getMonthDate() {
        return monthDate;
    }

    public List<String> allPurchaseCategories(){
        ArrayList<String> listOfCategories =  new ArrayList<>();
        listOfCategories.addAll(categories.keySet());
        return listOfCategories;
    }

    public double getAmountSpendingFormonth() {
        return amountSpendingFormonth;
    }
}
