package api;
// TODO create a better name for this class

import core.Dto.Purchase.PurchaseCreationRequest;


import core.Dto.Purchase.PurchaseDeletionRequest;
import core.Dto.Purchase.PurchaseUpdateRequest;
import core.gateways.BudgetMonthRepository;
import core.usecases.CreatePurchaseInteractor;

import core.usecases.DeletePurchase;
import core.usecases.UpdatePurchase;
import models.Purchase;

import java.util.Map;

/**
 * Created by ryan on 10/18/17.
 * the interface between the ui and the core system
 */
public class PurchaseManager {
    private static PurchaseManager ourInstance = new PurchaseManager();
    private CreatePurchaseInteractor createPurchaseInteractor;
    private DeletePurchase deletePurchase;
    private UpdatePurchase updatePurchase;


    public static PurchaseManager getInstance() {
        return ourInstance;
    }

    private PurchaseManager() {
        // this is a temporary setup for this class

    }

    public void savePurchaseData(Purchase purchase){
        PurchaseCreationRequest request = new PurchaseCreationRequest(purchase.getId(), purchase.getName(), purchase.getAmount(), purchase.getCategory(), purchase.getDate());
        createPurchaseInteractor.handleRequest(request);
    }

    public void deletePurchase(Purchase purchase){
        PurchaseDeletionRequest request = new PurchaseDeletionRequest(purchase.getDate(), purchase.getId());
        deletePurchase.handleRequest(request);
    }

    public void updatePurchase(Purchase purchase){
        PurchaseUpdateRequest updateRequest = new PurchaseUpdateRequest(purchase.getId(), purchase.getName(), purchase.getAmount(), purchase.getCategory(), purchase.getDate());
        updatePurchase.handleRequest(updateRequest);
    }

    public void setRepo(BudgetMonthRepository repo){
        createPurchaseInteractor = new CreatePurchaseInteractor(repo);
        deletePurchase = new DeletePurchase(repo);
        updatePurchase = new UpdatePurchase(repo);
    }
}
