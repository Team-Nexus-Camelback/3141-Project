package core.usecases;

import core.Dto.Purchase.PurchaseResponseMessage;
import core.Dto.Purchase.PurchaseUpdateRequest;
import core.entities.BudgetMonth;
import core.entities.PurchaseFactory;
import core.gateways.BudgetMonthRepository;
import core.gateways.IRequestHandler;

/**
 * Created by ryan on 11/13/17.
 */
public class UpdatePurchase implements IRequestHandler<PurchaseUpdateRequest, PurchaseResponseMessage> {
    private BudgetMonthRepository repository;
    private PurchaseFactory purchaseFactory = new PurchaseFactory();

    @Override
    public PurchaseResponseMessage handleRequest(PurchaseUpdateRequest request) {
        BudgetMonth workingMonth = repository.getMonthFromDate(request.getDate());
        workingMonth.updatePurchase(request.getId(), purchaseFactory.makeNewPurchaseFromRequest(request));
        repository.saveBudgetMonth(workingMonth);
        return null;
    }
}
