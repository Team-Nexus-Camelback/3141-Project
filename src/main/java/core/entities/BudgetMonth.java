package core.entities;

import java.util.*;
import java.util.function.Predicate;

/**
 * Created by ryan on 10/17/17.
 */
public class BudgetMonth {
    private ArrayList<Purchase> purchasesList = new ArrayList<>();
    private ArrayList<Payment> paymentList = new ArrayList<>();
    private String monthDate;
    private HashMap<String, Double> categories = new HashMap<>();
    private double amountSpendingForMonth;

    public BudgetMonth(String date, double allocateMoney) {
        this.monthDate = date;
        this.amountSpendingForMonth = allocateMoney;
    }

    public void addPurchase(Purchase purchase){
        addNewCategoryBudgetIfNew(purchase.getCategory(), purchase.getAmount());
        purchasesList.add(purchase.getId(), purchase);
    }

    public void updatePurchase(int id, Purchase updatedPurchase){
        purchasesList.add(id, updatedPurchase);
    }

    public void addPayment(Payment payment){
        paymentList.add(payment.getId(), payment);
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

    public double getAmountSpendingForMonth() {
        return amountSpendingForMonth;
    }

    public List<Purchase> getMostRecentPurchase(int numberOfPurchase){
        if (numberOfPurchase < 0)
            return purchasesList;
        purchasesList.sort(new Comparator<Purchase>() {
            @Override
            public int compare(Purchase o1, Purchase o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        return purchasesList.subList(0, numberOfPurchase - 1);
    }

    public List<Payment> getUnfinishedPayments(){
        List<Payment> returnList  = paymentList;
        returnList.removeIf(new Predicate<Payment>() {
            @Override
            public boolean test(Payment payment) {
                return payment.isPaid();
            }
        });
        return returnList;
    }
}
