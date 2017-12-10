import api.MonthManager;
import api.PaymentManager;
import api.PurchaseManager;
import core.entities.BudgetMonth;
import core.entities.Payment;
import core.entities.Purchase;
import core.gateways.BudgetMonthRepository;
import core.gateways.PaymentRepository;
import core.gateways.PaymentStorage;

import java.util.List;

/**
 * Created by ryan on 11/27/17.
 */
public class StartUp {
    public static void giveManagerRepos(){
        testRepo repo = new testRepo();
        PurchaseManager.getInstance().setRepo(repo);
        PaymentManager.getInstance().setRepo(new PaymentStorage());
        MonthManager.getInstance().setRepo(repo);
    }
}

class testRepo implements BudgetMonthRepository{

    private BudgetMonth month = new BudgetMonth("12-2017", 1000);

    public testRepo() {
        month.addPurchase(new Purchase(0,"Chipotle", 20, "Food"));
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
        return true;
    }

    @Override
    public List<BudgetMonth> monthsFromYear(String year) {
        return null;
    }
}
