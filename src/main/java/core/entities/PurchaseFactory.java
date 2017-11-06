package core.entities;

import core.Dto.Purchase.PurchaseCreationRequest;

/**
 * Created by ryan on 10/18/17.
 */
public class PurchaseFactory {

    public Purchase makeNewPurchaseFromRequest(PurchaseCreationRequest request){
        float purchaseAmount = Float.parseFloat(request.getAmount());
        String purchaseCategory = request.getCategory();
        int purchaseID = request.getId();
        return new Purchase(purchaseID, purchaseAmount, purchaseCategory);
    }
}
