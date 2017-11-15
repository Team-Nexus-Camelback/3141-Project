package core.usecases;

import core.Dto.Purchase.PurchaseDeletionRequest;
import core.Dto.Purchase.PurchaseResponseMessage;
import core.gateways.BudgetMonthRepository;
import core.gateways.IRequestHandler;

/**
 * Created by ryan on 11/14/17.
 */
public class DeletePurchase implements IRequestHandler<PurchaseDeletionRequest, PurchaseResponseMessage> {
    private BudgetMonthRepository repository;

    @Override
    public PurchaseResponseMessage handleRequest(PurchaseDeletionRequest request) {
        repository.deletePurchase(request.getId());
        return null;
    }
}
