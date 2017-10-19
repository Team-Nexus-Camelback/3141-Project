package core.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ryan on 10/17/17.
 */
public class BudgetMonth {
    private ArrayList<Purchase> purchasesList = new ArrayList<>();
    private String monthDate;

    public BudgetMonth(String date) {
        this.monthDate = date;
    }

    public void addPurchase(Purchase purchase){
        purchasesList.add(purchase.getId(), purchase);
    }

    public Purchase purchaseFromID(int id){
        return purchasesList.get(id);
    }

    public List<Purchase> purchasesOfCategory(String category) throws Exception{
        ArrayList<Purchase> purchaseFromCategory = new ArrayList<>();
        for (Purchase purchase : purchasesList){
            if (purchase.getCategory().equals(category))
                purchaseFromCategory.add(purchase);
        }
        return purchaseFromCategory;
    }

    public String getMonthDate() {
        return monthDate;
    }
}
