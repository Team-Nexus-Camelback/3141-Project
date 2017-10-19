package api;
// TODO create a better name for this class

import core.Dto.PurchaseCreationRequest;
import core.Dto.PurchaseResponseMessage;
import core.entities.BudgetMonth;
import core.usecases.CreatePurchaseInteractor;
import datastorage.SimpleBudgetRepo;

import java.util.Hashtable;

/**
 * Created by ryan on 10/18/17.
 * the interface between the ui and the core system
 */
public class PurchaseManager {
    private static PurchaseManager ourInstance = new PurchaseManager();

    private CreatePurchaseInteractor purchaseInteractor;


    public static PurchaseManager getInstance() {
        return ourInstance;
    }

    private PurchaseManager() {
        // this is a temporary setup for this class
        purchaseInteractor = new CreatePurchaseInteractor(new SimpleBudgetRepo(new BudgetMonth()));
    }

    public Hashtable<String, String> savePurchaseData(int id, String category, float amount){
        PurchaseCreationRequest createRequest = new PurchaseCreationRequest(id, String.valueOf(amount), category, "10/18/2017");
        return purchaseInteractor.handleRequest(createRequest).getMessage();
    }


}
