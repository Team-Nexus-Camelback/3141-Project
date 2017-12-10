package api;

import core.Dto.Month.MonthKeys;
import core.Dto.Month.MonthRequestMessage;

import core.Dto.Month.MonthUpdateRequest;
import core.gateways.BudgetMonthRepository;
import core.usecases.GetBudgetMonth;

import core.usecases.UpdateBudgetMonth;
import models.Month;
import models.Payment;


import java.util.HashMap;
import java.util.List;

/**
 * Created by ryan on 10/27/17.
 */
public class MonthManager {
    private static MonthManager ourInstance = new MonthManager();
    private GetBudgetMonth getBudgetMonth;
    private UpdateBudgetMonth updateBudgetMonth;


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
        return translateResponseToModel(getBudgetMonth.handleRequest(request).getMessage());

    }

    public boolean updateMonth(Month updateMonth){
        MonthUpdateRequest request = new MonthUpdateRequest(updateMonth.getMonthDate(), updateMonth.getSpendingAmount(), updateMonth.getOverview());
        return updateBudgetMonth.handleRequest(request) == null;
    }

    public Month addCategory(Month month ,String category, double amount){
        month.getCategories().put(category, amount);
        if (updateMonth(month))
            return month;
        return null;
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

    public void setRepo(BudgetMonthRepository repo){
        getBudgetMonth = new GetBudgetMonth(repo);
        updateBudgetMonth = new UpdateBudgetMonth(repo);
    }
}
