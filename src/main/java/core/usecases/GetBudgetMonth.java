package core.usecases;

import core.Dto.MonthRequestMessage;
import core.Dto.MonthResponseMessage;
import core.entities.BudgetMonth;
import core.gateways.BudgetMonthRepository;
import core.gateways.IRequestHandler;
import core.util.MonthGrapher;

import java.util.HashMap;

/**
 * Created by ryan on 10/23/17.
 * handles getting a request for a budget month data
 */
public class GetBudgetMonth implements IRequestHandler<MonthRequestMessage,MonthResponseMessage> {
    private BudgetMonthRepository repository;

    @Override
    public MonthResponseMessage handleRequest(MonthRequestMessage request) {
        if (request.monthsToCompare().length == 1)
            return createResponseForSingleMonth(request);
        return createResponseFormMonths(request.monthsToCompare());
    }

    private MonthResponseMessage createResponseFormMonths(String[] strings) {
        return null;
    }

    private MonthResponseMessage createResponseForSingleMonth(MonthRequestMessage request) {
        String monthDate = request.monthsToCompare()[0];
        BudgetMonth requestedMonth = repository.getMonthFromDate(monthDate);
        HashMap<String, String> responseData = new HashMap<>();
        if (request.needsMonthGraphData()){
            MonthGrapher grapher = new MonthGrapher(requestedMonth);
            grapher.graphPercentageOfCategories();

        }

        return new MonthResponseMessage();
    }
}
