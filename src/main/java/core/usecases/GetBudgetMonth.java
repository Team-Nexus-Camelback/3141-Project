package core.usecases;

import core.Dto.Month.MonthRequestMessage;
import core.Dto.Month.MonthKeys;
import core.Dto.Month.MonthResponseMessage;
import core.entities.BudgetMonth;
import core.gateways.BudgetMonthRepository;
import core.gateways.IRequestHandler;
import core.util.MonthComparer;
import core.util.MonthGrapher;

import java.util.HashMap;

/**
 * Created by ryan on 10/23/17.
 * handles getting a request for a budget month data
 */
public class GetBudgetMonth implements IRequestHandler<MonthRequestMessage,MonthResponseMessage> {
    private BudgetMonthRepository repository;

    public GetBudgetMonth(BudgetMonthRepository repository) {
        this.repository = repository;
    }

    @Override
    public MonthResponseMessage handleRequest(MonthRequestMessage request) {
        if (request.monthsToCompare().length == 1)
            return createResponseForSingleMonth(request);
        return createResponseFormMonths(request.monthsToCompare());
    }

    private MonthResponseMessage createResponseFormMonths(String[] strings) {
        MonthComparer.getComparisonDataFromMonths(strings);
        return null;
    }

    private MonthResponseMessage createResponseForSingleMonth(MonthRequestMessage request) {
        String monthDate = request.monthsToCompare()[0];
        BudgetMonth requestedMonth = repository.getMonthFromDate(monthDate);
        HashMap<String, String> responseData = new HashMap<>();
        if (request.needsMonthGraphData()){
            MonthGrapher grapher = new MonthGrapher(requestedMonth);
            String overViewData = grapher.graphBudgetOverview().toString();
            responseData.put(MonthKeys.OVERVIEW.getName(), overViewData);
            String categoryData = grapher.graphPercentageOfCategories().toString();
            responseData.put(MonthKeys.CATEGORY_SPENT.getName(), categoryData);
        }
        responseData.put(MonthKeys.MONTHLY_BUDGET.getName(), String.valueOf(requestedMonth.getAmountSpendingForMonth()));
        responseData.put(MonthKeys.DATE.getName(), requestedMonth.getMonthDate());
        responseData.put(MonthKeys.PURCHASES.getName(), requestedMonth.getMostRecentPurchase(request.getPurchaseToGet()).toString());

        return new MonthResponseMessage(responseData);
    }
}
