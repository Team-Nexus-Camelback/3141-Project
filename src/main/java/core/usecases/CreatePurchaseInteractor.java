package core.usecases;

import core.Dto.Purchase.PurchaseCreationRequest;
import core.Dto.Purchase.PurchaseKeys;
import core.Dto.Purchase.PurchaseResponseMessage;
import core.entities.BudgetMonth;
import core.entities.Purchase;
import core.entities.PurchaseFactory;
import core.gateways.BudgetMonthRepository;
import core.gateways.IRequestHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by ryan on 10/17/17.
 */
public class CreatePurchaseInteractor extends AbstractHandler<PurchaseCreationRequest, PurchaseResponseMessage> {

    private BudgetMonthRepository monthRepository;
    private PurchaseFactory purchaseFactory = new PurchaseFactory();

    private BudgetMonth currentWorkingMonth = new BudgetMonth("00-0000", -1337); // starts out with an impossible value


    public CreatePurchaseInteractor(BudgetMonthRepository monthRepository) {
        this.monthRepository = monthRepository;
    }

    private BudgetMonth getMonthFromRequestDate(String requestDate) throws ParseException {
        if (requestDate.equals(currentWorkingMonth.getMonthDate()))
            return currentWorkingMonth;
        else {
            String date  = getMonthDate(requestDate);
            BudgetMonth revelvantMonth = monthRepository.getMonthFromDate(date);
            switchWorkingMonth(revelvantMonth);
            return revelvantMonth;
        }
    }

    private void switchWorkingMonth(BudgetMonth revelvantMonth) {
        this.currentWorkingMonth = revelvantMonth;
    }

    @Override
    public PurchaseResponseMessage handleRequest(PurchaseCreationRequest request) {
        BudgetMonth month = null;
        try {
            month = getMonthFromRequestDate(request.getDate());
        } catch (ParseException e) {
            return errorResponse("Date is not correct");
        }
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
        responseData.put(PurchaseKeys.ID.key(), String.valueOf(request.getId()));
        responseData.put(PurchaseKeys.AMOUNT.key(), request.getAmount().toString());
        responseData.put(PurchaseKeys.CATEGORY.key(), request.getCategory());
        return new PurchaseResponseMessage(responseData);
    }

    private String getMonthDate(String date) throws ParseException {
        Date purchaseDate = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH).parse(date);
        return new SimpleDateFormat("MM-YYYY").format(purchaseDate);
    }

    @Override
    protected PurchaseResponseMessage errorResponse(String errorMessage) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put(PurchaseKeys.ERROR.key(), errorMessage);
        return new PurchaseResponseMessage(errorResponse);
    }
}
