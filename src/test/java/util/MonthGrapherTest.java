package util;

import core.entities.BudgetMonth;
import core.entities.Purchase;
import core.util.MonthGrapher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ryan on 10/25/17.
 */
class MonthGrapherTest {

    private BudgetMonth createTestableBudgetMonth(){
        BudgetMonth testMonth = new BudgetMonth("02-1998", 1000);
        testMonth.addNewCategoryBudgetIfNew("Hello", 200);
        testMonth.addNewCategoryBudgetIfNew("World", 400);
        testMonth.addPurchase(new Purchase(0, "Test", 100, "Hello"));
        testMonth.addPurchase(new Purchase(1, "Foo", 20, "World"));
        return testMonth;
    }

    @Test
    void graphPercentageOfCategories() {
        BudgetMonth dummyMonth = createTestableBudgetMonth();
        MonthGrapher grapher = new MonthGrapher(dummyMonth);
        ArrayList<String> graphData = grapher.graphPercentageOfCategories();
        assertTrue(graphData.get(0).equals("Hello"));
        assertTrue(graphData.get(1).equals("0.50"));
        assertTrue(graphData.get(2).equals("World"));
        assertTrue(graphData.get(3).equals("0.05"));
    }

    @Test
    void graphBudgetOverview() {
        BudgetMonth dummyMonth = createTestableBudgetMonth();
        MonthGrapher grapher = new MonthGrapher(dummyMonth);
        HashMap<String, Double> overviewData = grapher.graphBudgetOverview();

        assertTrue(overviewData.keySet().contains("Unallocated"));
        assertTrue(overviewData.keySet().contains("Hello"));
        assertTrue(overviewData.get("Unallocated") == .4);
    }

}