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
        List<models.Purchase> purchases = purchaseFromResponse(response.get(MonthKeys.PURCHASES.getName()));
        String monthDate = response.get(MonthKeys.DATE.getName());
        Double bugetAmount = Double.parseDouble(response.get(MonthKeys.MONTHLY_BUDGET.getName()));
        List<Payment> payments = paymentsFromResponse(response.get(MonthKeys.PAYMENTS.getName()));
        HashMap<String, Double> categoryGraph = getCategoryGraphFromResponse(response.get(MonthKeys.CATEGORY_SPENT.getName()));
        HashMap<String, Double> overviewGraph = getOverviewGraphFromResponse(response.get(MonthKeys.OVERVIEW.getName()));
        return new Month(monthDate, bugetAmount, categoryGraph, purchases, payments, overviewGraph);
    }

    //TODO move these method to a new class

    private HashMap<String,Double> getOverviewGraphFromResponse(String s) {
        s = s.substring(1, s.length() -1); // removes { }
        String[] values = s.split(",");
        HashMap<String, Double> returnMap = new HashMap<>();
        for (String pair : values){
            String[] entry = pair.split("=");
            returnMap.put(entry[0].trim(), Double.parseDouble(entry[1].trim()));
        }
        return returnMap;
    }

    private HashMap<String,Double> getCategoryGraphFromResponse(String s) {
        s = s.replace("[", "");
        s = s.replace("]", "");
        String categories[] = s.split(",");
        HashMap<String, Double> returnMap = new HashMap<>();
        String keyname = "";
        for (String cat : categories){
            try{
                double percent = Double.parseDouble(cat);
                returnMap.put(keyname, percent);
            }catch (Exception e){
                keyname = cat;
            }
        }
        return returnMap;
    }

    private List<models.Purchase> purchaseFromResponse(String purchasesData) {
        purchasesData = purchasesData.replace("[", "");
        purchasesData = purchasesData.replace("]", "");
        ArrayList<models.Purchase> purchaseArrayList = new ArrayList<>();
        models.Purchase newPurchase = createPurchaseFromString(purchasesData);
        purchaseArrayList.add(newPurchase);
        return purchaseArrayList;
    }

    private models.Purchase createPurchaseFromString(String purchase) {
        String[] dataValues = purchase.split(" ");
        String name = "";
        float amount = 0.0f;
        String date = "";
        String category = "";

        for (int i = 0; i < dataValues.length; i++) {
            switch (dataValues[i]) {
                case "name":
                    name = dataValues[i + 1];
                    break;
                case "amount":
                    amount = Float.parseFloat(dataValues[i + 1]);
                    break;
                case "date":
                    date = dataValues[i + 1];
                    break;
                case "category":
                    category = dataValues[i + 1];
                    break;
                default:
            }
        }
        return new models.Purchase(amount, date, category, name);
    }

    private List<Payment> paymentsFromResponse(String paymentData){
        paymentData = paymentData.replace("[", "");
        paymentData = paymentData.replace("]", "");
        String[] payments = paymentData.split(",");
        ArrayList<Payment> paymentArrayList = new ArrayList<>();
        for (String payment : payments){
            Payment newPayment = createPaymentFromString(payment);
            paymentArrayList.add(newPayment);
        }
        return paymentArrayList;
    }

    private Payment createPaymentFromString(String payments){
        String[] dataValues = payments.split(" ");
        String name = "";
        double amount = 0.0;
        String date = "";
        boolean isPaid = false;
        for (int i = 0; i < dataValues.length; i++){
            switch (dataValues[i]){
                case "paymentName":
                    name = dataValues[i+1];
                    break;
                case "amount":
                    amount = Double.parseDouble(dataValues[i+1]);
                    break;
                case "dueDate":
                    date = dataValues[i+1];
                    break;
                case "isPaid":
                    isPaid = Boolean.parseBoolean(dataValues[i+1]);
                    break;
            }
        }
        try {
            return new Payment(name, amount, date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
