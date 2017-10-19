package core.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 10/17/17.
 */
public class BudgetMonth {
    private ArrayList<Purchase> purchasesList = new ArrayList<>();

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


}
