package core.usecases;

import core.Dto.Month.MonthKeys;
import core.Dto.Month.MonthResponseMessage;
import core.Dto.Month.MonthUpdateRequest;
import core.entities.BudgetMonth;
import core.entities.MonthFactory;
import core.gateways.BudgetMonthRepository;
import core.gateways.IRequestHandler;

import java.util.HashMap;

/**
 * Created by ryan on 11/18/17.
 */
public class UpdateBudgetMonth extends AbstractHandler<MonthUpdateRequest, MonthResponseMessage> {
    private BudgetMonthRepository repo;

    public UpdateBudgetMonth(BudgetMonthRepository repo) {
        this.repo = repo;
    }

    @Override
    public MonthResponseMessage handleRequest(MonthUpdateRequest request) {
        BudgetMonth currentMonth = repo.getMonthFromDate(request.getMonthDate());
        BudgetMonth newMonth = MonthFactory.generateMonth(currentMonth, request.getSpendingAmount(), request.getCategoryPercents());
        if (repo.saveBudgetMonth(newMonth))
            return null;
        else
            return errorResponse("Month did not update correctly");
    }

    @Override
    protected MonthResponseMessage errorResponse(String errorMessage) {
        HashMap<String, String> data = new HashMap<>();
        data.put(MonthKeys.ERROR.getName(), errorMessage);
        return new MonthResponseMessage(data);
    }
}
