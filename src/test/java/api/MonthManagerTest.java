package api;

import core.Dto.Month.MonthKeys;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ryan on 10/30/17.
 */
class MonthManagerTest {

    @Test
    public void simpleReqest(){
        String testMonthDate = "10-2017";
        HashMap<String, String> getData = MonthManager.getInstance().getMonthData(testMonthDate);
        assertTrue(getData.get(MonthKeys.DATE.getName()).equals("10-2017"));
        MonthManager.getInstance().getLastestPurchase();
    }

}