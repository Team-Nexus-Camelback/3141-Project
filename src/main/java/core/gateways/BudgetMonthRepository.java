package core.gateways;

import core.entities.BudgetMonth;

import java.util.List;

/**
 * Created by ryan on 10/17/17.
 */
public interface BudgetMonthRepository {

    /**
     *
     * @param date will be in this format MM-YYYY
     * @return a ready made BudgetMonth Object
     */
    BudgetMonth getMonthFromDate(String date);

    boolean saveBudgetMonth(BudgetMonth month);

    boolean deletePurchase(int id);

    /**
     *
     * @param year a string that will specify the year to select months from
     * @return a list of all available months
     */
    List<BudgetMonth> monthsFromYear(String year);

}
