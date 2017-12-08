package datastorage;

import core.entities.BudgetMonth;
import core.entities.Purchase;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ryan on 12/7/17.
 */
class MonthWriterTest {

    @Test
    public void saveAMonth(){
        MonthWriter writer = new MonthWriter();
        BudgetMonth test = createTestMonth();
        writer.saveMonthData(test);
    }

    @Test
    public void updateAMonth(){
        BudgetMonth test = createTestMonth();
        MonthWriter writer = new MonthWriter();
        writer.updateMonth("11-2017", test);
    }
    @Test
    public void deleteAMonth(){
        MonthWriter writer = new MonthWriter();
        writer.deleteMonth("11-2017");
    }

    private BudgetMonth createTestMonth(){
        BudgetMonth test = new BudgetMonth("11-2017", 1000);
        test.addPurchase(new Purchase(0,"Chipotle", 20, "Food"));
        return test;
    }
}