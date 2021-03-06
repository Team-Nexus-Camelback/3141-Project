package usecases;

import core.Dto.Purchase.PurchaseCreationRequest;
import core.Dto.Purchase.PurchaseResponseMessage;
import core.entities.BudgetMonth;
import core.usecases.CreatePurchaseInteractor;
import dummyrepos.DummyBudgetRepo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ryan on 10/18/17.
 */
class CreatePurchaseInteractorTest {
    private CreatePurchaseInteractor newInteractorForTesting(){
        BudgetMonth month = new BudgetMonth("10-2017", 1000);
        return new CreatePurchaseInteractor(new DummyBudgetRepo(month));
    }

    @Test
    void simpleResponse() {
        CreatePurchaseInteractor interactor = newInteractorForTesting();
        PurchaseResponseMessage responseMessage = interactor.handleRequest(createSimpleRequest());
        float responseAmount = Float.parseFloat(responseMessage.getMessage().get("amount"));
        assertTrue(responseAmount == 19.99f);
    }

    @Test
    void errorResponse() {

    }

    private PurchaseCreationRequest createSimpleRequest() {
        return new PurchaseCreationRequest(0, "Elmo", 19.99, "Toys", "10-2017");

    }
}