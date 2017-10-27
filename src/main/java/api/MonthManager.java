package api;

import core.Dto.Month.MonthRequestMessage;
import core.entities.BudgetMonth;
import core.gateways.BudgetMonthRepository;
import core.usecases.GetBudgetMonth;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ryan on 10/27/17.
 */
public class MonthManager {
    private static MonthManager ourInstance = new MonthManager();
    private GetBudgetMonth getBudgetMonth = new GetBudgetMonth(new BudgetMonthRepository() {
        @Override
        public BudgetMonth getMonthFromDate(String date) {
            return null;
        }

        @Override
        public boolean saveBudgetMonth(BudgetMonth month) {
            return false;
        }

        @Override
        public List<BudgetMonth> monthsFromYear(String year) {
            return null;
        }
    });

    public static MonthManager getInstance() {
        return ourInstance;
    }

    private MonthManager() {
    }

    public HashMap<String, String> getMonthData(String monthDate){
        MonthRequestMessage request = new MonthRequestMessage(true, monthDate);
        return getBudgetMonth.handleRequest(request).getMessage();
    }

    public void compareMonthsData(String ... monthsToCompare){
        MonthRequestMessage requestMessage = new MonthRequestMessage(true, monthsToCompare);

    }


}
