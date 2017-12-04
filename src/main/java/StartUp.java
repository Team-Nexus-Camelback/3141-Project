import api.MonthManager;
import api.PaymentManager;
import api.PurchaseManager;
import core.entities.BudgetMonth;
import core.entities.Payment;
import core.gateways.BudgetMonthRepository;
import core.gateways.PaymentRepository;

import java.util.List;

/**
 * Created by ryan on 11/27/17.
 */
public class StartUp {
    public static void giveManagerRepos(){
        PurchaseManager.getInstance().setRepo(new BudgetMonthRepository() {
            @Override
            public BudgetMonth getMonthFromDate(String date) {
                BudgetMonth month = new BudgetMonth("11-2017", 1000);
                month.addNewCategoryBudgetIfNew("Food", 200);
                return month;
            }

            @Override
            public boolean saveBudgetMonth(BudgetMonth month) {
                return false;
            }

            @Override
            public boolean deletePurchase(int id) {
                return false;
            }

            @Override
            public List<BudgetMonth> monthsFromYear(String year) {
                return null;
            }
        });
        PaymentManager.getInstance().setRepo(new PaymentRepository() {
            @Override
            public List<Payment> getUnFinishedPayments() {
                return null;
            }

            @Override
            public Payment paymentByID(int id) {
                return null;
            }

            @Override
            public boolean savePayment(Payment payment, int id) {
                return false;
            }

            @Override
            public boolean deletePaymentByID(int id) {
                return false;
            }
        });
        MonthManager.getInstance().setRepo(new BudgetMonthRepository() {
            @Override
            public BudgetMonth getMonthFromDate(String date) {
                BudgetMonth month = new BudgetMonth("11-2017", 1000);
                month.addNewCategoryBudgetIfNew("Food", 200);
                return month;
            }

            @Override
            public boolean saveBudgetMonth(BudgetMonth month) {
                return false;
            }

            @Override
            public boolean deletePurchase(int id) {
                return false;
            }

            @Override
            public List<BudgetMonth> monthsFromYear(String year) {
                return null;
            }
        });

    }
}