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

    public CreatePurchaseInteractor(BudgetMonthRepository monthRepository) {
        this.monthRepository = monthRepository;
    }

    @Override
    public PurchaseResponseMessage handleRequest(PurchaseCreationRequest request) {

        BudgetMonth month = monthRepository.getMonthFromDate(request.getDate());
        Purchase purchaseToAdd = purchaseFactory.makeNewPurchaseFromRequest(request);
        month.addPurchase(purchaseToAdd);
        monthRepository.saveBudgetMonth(month);


        //go about creating the response
        return createResponseFromRequest(request);
    }

    private PurchaseResponseMessage createResponseFromRequest(PurchaseCreationRequest request){
        Hashtable<String, String> responseData = new Hashtable<>();
        responseData.put("id", String.valueOf(request.getId()));
        responseData.put("amount", request.getAmount());
        responseData.put("category", request.getCategory());
        return new PurchaseResponseMessage(responseData);
    }


}
