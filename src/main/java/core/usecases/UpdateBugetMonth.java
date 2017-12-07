package core.usecases;

import core.Dto.Month.MonthResponseMessage;
import core.Dto.Month.MonthUpdateRequest;
import core.entities.BudgetMonth;
import core.entities.MonthFactory;
import core.gateways.BudgetMonthRepository;
import core.gateways.IRequestHandler;

/**
 * Created by ryan on 11/18/17.
 */
public class UpdateBugetMonth implements IRequestHandler<MonthUpdateRequest, MonthResponseMessage> {
    private BudgetMonthRepository repo;

    @Override
    public MonthResponseMessage handleRequest(MonthUpdateRequest request) {
        BudgetMonth currentMonth = repo.getMonthFromDate(request.getMonthDate());
        BudgetMonth newMonth = MonthFactory.generateMonth(currentMonth, request.getSpendingAmount(), request.getCategoryPercents());
        return null;
    }
}
