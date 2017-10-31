package usecases;

import core.Dto.Month.MonthRequestMessage;
import core.Dto.Month.MonthResponseMessage;
import core.entities.BudgetMonth;
import core.usecases.GetBudgetMonth;
import dummyrepos.DummyBudgetRepo;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ryan on 10/26/17.
 */
class GetBudgetMonthTest {

    @Test
    public void testSimpleRequest(){
        MonthRequestMessage testRequestMessage = createSimpleGetRequest();
        GetBudgetMonth getBudgetMonthInter = createTestInteractor();
        MonthResponseMessage responseMessage = getBudgetMonthInter.handleRequest(testRequestMessage);
        HashMap<String, String> data = responseMessage.getMessage();

        assertTrue(data.get("budget").equals("1000.0"));
        assertTrue(data.get("date").equals("10-2017"));

    }

    private GetBudgetMonth createTestInteractor() {
        BudgetMonth month = new BudgetMonth("10-2017", 1000);
        DummyBudgetRepo repo = new DummyBudgetRepo(month);
        return new GetBudgetMonth(repo);
    }

    private MonthRequestMessage createSimpleGetRequest() {
        return new MonthRequestMessage(true, "10-2017");
    }

}