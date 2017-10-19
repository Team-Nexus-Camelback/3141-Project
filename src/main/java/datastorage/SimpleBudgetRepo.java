package datastorage;

import core.entities.BudgetMonth;
import core.gateways.BudgetMonthRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 10/18/17.
 * Just a simple Repo, Only for the first deliverable, ITS TEMPORARY
 */
public class SimpleBudgetRepo implements BudgetMonthRepository {

    private BudgetMonth month = new BudgetMonth("10-2017");



    @Override
    public BudgetMonth getMonthFromDate(String date) {
        return month;
    }

    @Override
    public boolean saveBudgetMonth(BudgetMonth month) {
        this.month = month;
        return true;
    }

    @Override
    public List<BudgetMonth> monthsFromYear(String year) {
        ArrayList<BudgetMonth> monthsOfYear = new ArrayList<>();
        monthsOfYear.add(month);
        return monthsOfYear;
    }
}
