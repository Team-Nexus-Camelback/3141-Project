package api;

import core.Dto.Month.MonthKeys;
import core.Dto.Month.MonthRequestMessage;
import core.entities.BudgetMonth;
import core.entities.Purchase;
import core.gateways.BudgetMonthRepository;
import core.usecases.GetBudgetMonth;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.HashMap;
import java.util.List;

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

    public HashMap<String, String> getMonthData(String monthDate){
        MonthRequestMessage request = new MonthRequestMessage(true, monthDate);
        lastRequestData = getBudgetMonth.handleRequest(request).getMessage();
        return lastRequestData;
    }

    public void compareMonthsData(String ... monthsToCompare){
        MonthRequestMessage requestMessage = new MonthRequestMessage(true, monthsToCompare);
    }

    public HashMap<String, ObservableList<String>> getLastestPurchase(){
        HashMap<String, ObservableList<String>> listHashMap = new HashMap<>();
        String purchasesData = lastRequestData.get(MonthKeys.PURCHASES.getName());
        purchasesData = purchasesData.replace("[", "");
        purchasesData = purchasesData.replace("]", "");
        String[] purchases = purchasesData.split(",");
        for (String purchase : purchases){
            String[] dataValues = purchase.split(" ");
            String value;
            for (String data : dataValues){
                switch (data){
                    case "id":
                        listHashMap.put(data, FXCollections.observableArrayList());
                    case "name":
                        listHashMap.put(data, FXCollections.observableArrayList());
                    case "category":
                        listHashMap.put(data, FXCollections.observableArrayList());
                    case "amount":
                        listHashMap.put(data, FXCollections.observableArrayList());
                    case "date":
                        listHashMap.put(data, FXCollections.observableArrayList());
                    default:
                       listHashMap.get(data).add(data);
                }
            }
        }
        return listHashMap;
    }
}
