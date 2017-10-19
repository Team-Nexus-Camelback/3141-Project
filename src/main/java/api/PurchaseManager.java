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
        purchaseInteractor = new CreatePurchaseInteractor(new SimpleBudgetRepo(new BudgetMonth()));
    }

    public Hashtable<String, String> savePurchaseData( String category, float amount){
        int idForPurchase = createIDForPurchase();
        PurchaseCreationRequest createRequest = new PurchaseCreationRequest(idForPurchase, String.valueOf(amount), category, "10/18/2017");
        return purchaseInteractor.handleRequest(createRequest).getMessage();
    }

    private int createIDForPurchase() {

        return 0;
    }
}
