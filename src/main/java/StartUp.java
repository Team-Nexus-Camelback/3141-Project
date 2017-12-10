import api.MonthManager;
import api.PaymentManager;
import api.PurchaseManager;
import core.entities.BudgetMonth;
import core.entities.Payment;
import core.entities.Purchase;
import core.gateways.BudgetMonthRepository;
import core.gateways.MonthStorage;
import core.gateways.PaymentRepository;
import core.gateways.PaymentStorage;

import java.util.List;

/**
 * Created by ryan on 11/27/17.
 */
public class StartUp {
    public static void giveManagerRepos(){
        MonthStorage repo = new MonthStorage();
        PurchaseManager.getInstance().setRepo(repo);
        PaymentManager.getInstance().setRepo(new PaymentStorage());
        MonthManager.getInstance().setRepo(repo);
    }
}


