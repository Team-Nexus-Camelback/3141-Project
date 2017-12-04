package dummyrepos;

import core.entities.BudgetMonth;
import core.entities.Purchase;
import core.gateways.BudgetMonthRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 10/18/17.
 * A dummy repo meant for testing system classes
 */
public class DummyBudgetRepo implements BudgetMonthRepository {

    private BudgetMonth month = new BudgetMonth("10-2017", 1337);

    public DummyBudgetRepo(BudgetMonth month) {
        this.month = month;
    }

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
    public boolean deletePurchase(int id) {
        return false;
    }

    @Override
    public List<BudgetMonth> monthsFromYear(String year) {
        ArrayList<BudgetMonth> monthsOfYear = new ArrayList<>();
        monthsOfYear.add(month);
        return monthsOfYear;
    }
}
