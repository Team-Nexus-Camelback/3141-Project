package core.entities;

import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 * Created by ryan on 11/19/17.
 */
public class MonthFactory {

    public static BudgetMonth generateMonth(BudgetMonth current, double spendingAmount, HashMap<String, Double> categories){
        BudgetMonth newMonth = new BudgetMonth(current.getMonthDate(), spendingAmount);
        addCatesToMonth(newMonth, categories);
        return newMonth;
    }

    private static void addCatesToMonth(BudgetMonth newMonth, HashMap<String, Double> categories) {
        categories.forEach(new BiConsumer<String, Double>() {
            @Override
            public void accept(String s, Double aDouble) {
                newMonth.addNewCategoryBudgetIfNew(s, aDouble);
            }
        });
    }

}
