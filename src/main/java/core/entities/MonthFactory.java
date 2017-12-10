package core.entities;

import org.jdom2.input.sax.BuilderErrorHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by ryan on 11/19/17.
 */
public class MonthFactory {

    public static BudgetMonth generateMonth(BudgetMonth current, double spendingAmount, Map<String, Double> categories){
        BudgetMonth newMonth = new BudgetMonth(current.getMonthDate(), spendingAmount);
        addCatesToMonth(newMonth, categories);
        return newMonth;
    }

    public static BudgetMonth generateMonth(String date, double spendingAmount, List<Purchase> purchases, Map<String, Double> categories){
        BudgetMonth newMonth = new BudgetMonth(date, spendingAmount);
        purchases.forEach(newMonth::addPurchase);
        addCatesToMonth(newMonth, categories);
        return newMonth;
    }

    private static void addCatesToMonth(BudgetMonth newMonth, Map<String, Double> categories) {
        categories.forEach(new BiConsumer<String, Double>() {
            @Override
            public void accept(String s, Double aDouble) {
                newMonth.addNewCategoryBudgetIfNew(s, aDouble);
            }
        });
    }

}
