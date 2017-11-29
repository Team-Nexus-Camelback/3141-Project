package core.usecases;

import core.Dto.Purchase.PurchaseDeletionRequest;
import core.Dto.Purchase.PurchaseResponseMessage;
import core.gateways.BudgetMonthRepository;
import core.gateways.IRequestHandler;
import core.gateways.PaymentRepository;

/**
 * Created by ryan on 11/14/17.
 */
public class DeletePurchase extends AbstractHandler<PurchaseDeletionRequest, PurchaseResponseMessage> {
    private BudgetMonthRepository repository;

    public DeletePurchase(BudgetMonthRepository repository) {
        this.repository = repository;
    }

    @Override
    public PurchaseResponseMessage handleRequest(PurchaseDeletionRequest request) {
        if (repository.deletePurchase(request.getId()))
            return errorResponse("Failed to delete purchase");
        return null;
    }

    @Override
    protected PurchaseResponseMessage errorResponse(String errorMessage) {
        return null;
    }
}
