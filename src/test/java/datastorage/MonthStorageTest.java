package datastorage;

import core.entities.BudgetMonth;
import core.entities.Purchase;
import core.gateways.MonthStorage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ryan on 12/9/17.
 */
class MonthStorageTest {
    MonthStorage storage = new MonthStorage();
    @Test
    public void testRetrieval(){
       BudgetMonth month = storage.getMonthFromDate("11-2017");
        assertFalse(month == null);
        assertTrue(month.getMonthDate().equals("11-2017"));
        assertTrue(month.getPurchasesList().size() == 1);
    }

    @Test
    public void saveMonth(){
        BudgetMonth testMonth = new BudgetMonth("12-2017", 2000);
        testMonth.addNewCategoryBudgetIfNew("Food", 350);
        testMonth.addNewCategoryBudgetIfNew("Toys", 400);
        testMonth.addPurchase(new Purchase(0, "Olive Garden", 50, "Food"));
        testMonth.addPurchase(new Purchase(1,"Lego", 60, "Toys"));

        storage.saveBudgetMonth(testMonth);
    }

}