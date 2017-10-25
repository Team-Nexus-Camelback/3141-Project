package core.util;

import core.entities.BudgetMonth;
import core.entities.Purchase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 10/23/17.
 * handles organizing the data into an easy to graph data structure
 */
public class MonthGrapher {

    private BudgetMonth month;

    public MonthGrapher(BudgetMonth month) {
        this.month = month;
    }

    public String[] graphPercentageOfCategories( ){
        ArrayList<String> graphData = new ArrayList<>();
        for (String category : month.allPurchaseCategories()){
            graphData.add(category);
            String categoryPercent = calculatePercentageOfCategory(month.categoryBudget(category), month.purchasesOfCategory(category));
            graphData.add(categoryPercent);
        }
        return (String[]) graphData.toArray();
    }

    private String calculatePercentageOfCategory(double categoryCap, List<Purchase> purchases){
        double purchaseSum = 0.0;
        for (Purchase purchase : purchases){
            purchaseSum += purchase.getAmount();
        }
        double percent = purchaseSum / categoryCap;
        return String.format("%.2f", percent);
    }

    public void graphBudgetOverview(){}
}
