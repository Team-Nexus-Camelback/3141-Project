package core.util;

import core.entities.BudgetMonth;
import core.entities.Purchase;

import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * @return an array list of data points to create a graph of amount of money spent in each category
     */
    public ArrayList<String> graphPercentageOfCategories( ){
        ArrayList<String> graphData = new ArrayList<>();
        for (String category : month.allPurchaseCategories()){
            graphData.add(category);
            String categoryPercent = calculatePercentageOfCategorySpent(month.categoryBudget(category), month.purchasesOfCategory(category));
            graphData.add(categoryPercent);
        }
        return graphData;
    }

    private String calculatePercentageOfCategorySpent(double categoryCap, List<Purchase> purchases){
        double purchaseSum = 0.0;
        for (Purchase purchase : purchases){
            purchaseSum += purchase.getAmount();
        }
        double percent = purchaseSum / categoryCap;
        return (String.format("%.2f", percent));
    }

    /**
     * @return a hash map containing the data value of make an overview graph
     */
    public HashMap<String, Double> graphBudgetOverview(){
        HashMap<String,Double > graphData = new HashMap<>();
        for (String category : month.allPurchaseCategories()){
            double categoryPercent = calculateCategoryPercentInBudget(category);
            graphData.put(category, categoryPercent);
        }
        addUnallocatedCatIfNeeded(graphData);
        return graphData;
    }

    private void addUnallocatedCatIfNeeded(HashMap<String, Double> graphData) {
        double categorySum = sumOfCategoryInBudget(graphData);
        if (month.getAmountSpendingForMonth() == categorySum ){
            return;
        }
        double unallocatedPercent = Double.parseDouble(String.format("%.2f", (1 - categorySum)));
        graphData.put("Unallocated", unallocatedPercent);

    }

    private double sumOfCategoryInBudget(HashMap<String, Double> graphData) {
        double sumOfCats = 0;
        for (Double aDouble : graphData.values()) sumOfCats += aDouble;
        return sumOfCats;
    }

    private double calculateCategoryPercentInBudget(String category) {
        double categoryPercent = month.categoryBudget(category);
        double overallBugetAmount = month.getAmountSpendingForMonth();
        return Double.parseDouble(String.format("%.2f",categoryPercent / overallBugetAmount));
    }
}
