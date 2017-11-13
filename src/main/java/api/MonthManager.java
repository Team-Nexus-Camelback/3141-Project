package api;

import core.Dto.Month.MonthKeys;
import core.Dto.Month.MonthRequestMessage;
import core.entities.BudgetMonth;
import core.entities.Purchase;
import core.gateways.BudgetMonthRepository;
import core.usecases.GetBudgetMonth;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Month;
import models.Payment;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by ryan on 10/27/17.
 */
public class MonthManager {
    private static MonthManager ourInstance = new MonthManager();
    private GetBudgetMonth getBudgetMonth = new GetBudgetMonth(new BudgetMonthRepository() {
        @Override
        public BudgetMonth getMonthFromDate(String date) {
            BudgetMonth test = new BudgetMonth("10-2017", 1000);
            test.addPurchase(new Purchase(0, 100, "Happy"));
            try {
                test.addPayment(new core.entities.Payment(0, "Taxes", 300, "11/4/17"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return test;
        }

        @Override
        public boolean saveBudgetMonth(BudgetMonth month) {
            return false;
        }

        @Override
        public List<BudgetMonth> monthsFromYear(String year) {
            return null;
        }
    });
    private HashMap<String, String> lastRequestData;

    public static MonthManager getInstance() {
        return ourInstance;
    }

    private MonthManager() {
    }

    public HashMap<String, String> getMonthData(String monthDate, int numberOfPurchase) {
        MonthRequestMessage request = new MonthRequestMessage(true, numberOfPurchase, monthDate);
        return getBudgetMonth.handleRequest(request).getMessage();
    }

    public Month getMonthData(String monthDate){
        MonthRequestMessage request = new MonthRequestMessage(true, monthDate);
        lastRequestData = getBudgetMonth.handleRequest(request).getMessage();
        return translateResponseToModel(lastRequestData);
    }

    public void compareMonthsData(String ... monthsToCompare){
        MonthRequestMessage requestMessage = new MonthRequestMessage(true, monthsToCompare);
    }

    private Month translateResponseToModel(HashMap<String, String> response){
        List<models.Purchase> purchases = ResponseTranslator.purchaseFromResponse(response.get(MonthKeys.PURCHASES.getName()));
        String monthDate = response.get(MonthKeys.DATE.getName());
        Double bugetAmount = Double.parseDouble(response.get(MonthKeys.MONTHLY_BUDGET.getName()));
        List<Payment> payments = ResponseTranslator.paymentsFromResponse(response.get(MonthKeys.PAYMENTS.getName()));
        HashMap<String, Double> categoryGraph = ResponseTranslator.getCategoryGraphFromResponse(response.get(MonthKeys.CATEGORY_SPENT.getName()));
        HashMap<String, Double> overviewGraph = ResponseTranslator.getOverviewGraphFromResponse(response.get(MonthKeys.OVERVIEW.getName()));
        return new Month(monthDate, bugetAmount, categoryGraph, purchases, payments, overviewGraph);
    }
}
