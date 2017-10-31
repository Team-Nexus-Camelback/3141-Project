package core.usecases;

import core.Dto.PurchaseCreationRequest;
import core.Dto.PurchaseResponseMessage;
import core.entities.BudgetMonth;
import core.entities.Purchase;
import core.entities.PurchaseFactory;
import core.gateways.BudgetMonthRepository;
import core.gateways.IRequestHandler;

import java.util.Hashtable;

/**
 * Created by ryan on 10/17/17.
 */
public class CreatePurchaseInteractor implements IRequestHandler<PurchaseCreationRequest, PurchaseResponseMessage> {

    private BudgetMonthRepository monthRepository;
    private PurchaseFactory purchaseFactory = new PurchaseFactory();
    private BudgetMonth currentWorkingMonth = new BudgetMonth("00-0000"); // starts out with an impossible value

    public CreatePurchaseInteractor(BudgetMonthRepository monthRepository) {
        this.monthRepository = monthRepository;
    }

    private BudgetMonth getMonthFromRequestDate(String requestDate){
        if (requestDate.equals(currentWorkingMonth.getMonthDate()))
            return currentWorkingMonth;
        else {
            BudgetMonth revelvantMonth = monthRepository.getMonthFromDate(requestDate);
            switchWorkingMonth(revelvantMonth);
            return revelvantMonth;
        }
    }

    private void switchWorkingMonth(BudgetMonth revelvantMonth) {
        this.currentWorkingMonth = revelvantMonth;
    }

    // TODO add error checking
    @Override
    public PurchaseResponseMessage handleRequest(PurchaseCreationRequest request) {
        BudgetMonth month =  getMonthFromRequestDate(request.getDate());
        Purchase purchaseToAdd = purchaseFactory.makeNewPurchaseFromRequest(request);
        savePurchaseToMonth(purchaseToAdd, month);
        return createResponseFromRequest(request);
    }

    private void savePurchaseToMonth(Purchase purchaseToAdd, BudgetMonth month){
        month.addPurchase(purchaseToAdd);
        monthRepository.saveBudgetMonth(month);
    }

    private PurchaseResponseMessage createResponseFromRequest(PurchaseCreationRequest request){
        Hashtable<String, String> responseData = new Hashtable<>();
        responseData.put("id", String.valueOf(request.getId()));
        responseData.put("amount", request.getAmount());
        responseData.put("category", request.getCategory());
        return new PurchaseResponseMessage(responseData);
    }


}
