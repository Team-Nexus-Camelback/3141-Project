package api;
// TODO create a better name for this class

import core.Dto.Purchase.PurchaseCreationRequest;

import core.entities.BudgetMonth;
import core.gateways.BudgetMonthRepository;
import core.usecases.CreatePurchaseInteractor;
import models.Purchase;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by ryan on 10/18/17.
 * the interface between the ui and the core system
 */
public class PurchaseManager {
    private static PurchaseManager ourInstance = new PurchaseManager();

    private CreatePurchaseInteractor purchaseInteractor = new CreatePurchaseInteractor(new BudgetMonthRepository() {
        @Override
        public BudgetMonth getMonthFromDate(String date) {
            return new BudgetMonth("2/11/2017", 5000);
        }

        @Override
        public boolean saveBudgetMonth(BudgetMonth month) {
            return false;
        }

        @Override
        public List<BudgetMonth> monthsFromYear(String year) {
            return null;
        }
    });


    public static PurchaseManager getInstance() {
        return ourInstance;
    }

    private PurchaseManager() {
        // this is a temporary setup for this class
        //
    }

    public Purchase savePurchaseData(int id, String category, float amount){
        PurchaseCreationRequest createRequest = new PurchaseCreationRequest(id, String.valueOf(amount), category, "10/18/2017");
        Hashtable<String, String> data = purchaseInteractor.handleRequest(createRequest).getMessage();
        return new Purchase(Float.parseFloat(data.get("amount")),"11/3/2017",
                data.get("category"), "Name");
    }
}
