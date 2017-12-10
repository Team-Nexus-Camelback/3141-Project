package core.entities;

import core.Dto.Purchase.PurchaseCreationRequest;
import core.Dto.Purchase.PurchaseRequest;

/**
 * Created by ryan on 10/18/17.
 */
public class PurchaseFactory {

    public Purchase makeNewPurchaseFromRequest(PurchaseRequest request){
        Double purchaseAmount = request.getAmount();
        String purchaseCategory = request.getCategory();
        int purchaseID = request.getId();
        String name = request.getName();
        return new Purchase(purchaseID, name, purchaseAmount, purchaseCategory);
    }
}
