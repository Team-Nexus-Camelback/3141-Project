package core.usecases;

import core.Dto.Purchase.PurchaseKeys;
import core.Dto.Purchase.PurchaseResponseMessage;
import core.Dto.Purchase.PurchaseUpdateRequest;
import core.entities.BudgetMonth;
import core.entities.PurchaseFactory;
import core.gateways.BudgetMonthRepository;
import core.gateways.IRequestHandler;

import java.util.HashMap;

/**
 * Created by ryan on 11/13/17.
 */
public class UpdatePurchase extends AbstractHandler<PurchaseUpdateRequest, PurchaseResponseMessage> {
    private BudgetMonthRepository repository;
    private PurchaseFactory purchaseFactory = new PurchaseFactory();

    @Override
    public PurchaseResponseMessage handleRequest(PurchaseUpdateRequest request) {
        BudgetMonth workingMonth = repository.getMonthFromDate(request.getDate());
        workingMonth.updatePurchase(request.getId(), purchaseFactory.makeNewPurchaseFromRequest(request));
        repository.saveBudgetMonth(workingMonth);
        return null;
    }

    @Override
    protected PurchaseResponseMessage errorResponse(String errorMessage) {
        HashMap<String, String> errorData = new HashMap<>();
        errorData.put(PurchaseKeys.ERROR.key(), errorMessage);
        return new PurchaseResponseMessage(errorData);
    }
}
