package core.util;
import core.entities.BudgetMonth;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;



/**
 * Created by Ryan Phillips on 10/27/17.
 * 
 * Date Last Modified: 11/14/17 by Cody Koski
 */
public class MonthComparer {
	
	private BudgetMonth currentMonth;
	
//    public static void getComparisonDataFromMonths(String[] monthsToCompare) {
//
//    }


    public MonthComparer(BudgetMonth currentMonth) {
        this.currentMonth = currentMonth;
    }

    public HashMap<String, Double> changeInCategories (BudgetMonth monthToCompare, String category ) {
    	
    	HashMap<String, Double> categoriesToReturn = new HashMap<>();
    	
    	if ( currentMonth.categoryBudget(category) > monthToCompare.categoryBudget(category)
    			|| currentMonth.categoryBudget(category) < monthToCompare.categoryBudget(category) ) {
    		categoriesToReturn.put(category, currentMonth.categoryBudget(category));
    	}
    	
    	return categoriesToReturn;
    }
    
    public double differenceInSpending ( BudgetMonth monthToCompare, String category ) {
    	
    	double difference = 0;
    	
    	if ( currentMonth.categoryBudget(category) > monthToCompare.categoryBudget(category)
    			|| currentMonth.categoryBudget(category) < monthToCompare.categoryBudget(category) ) {
    		difference = currentMonth.categoryBudget(category) - monthToCompare.categoryBudget(category);
    	}
    	
    	return difference;
    }
    
    public double percentageChangeInSpending ( BudgetMonth monthToCompare, String category ) {
    	
    	return new BigDecimal(((currentMonth.categoryBudget(category) / monthToCompare.categoryBudget(category)) - 1)
    			* 100).setScale(2, RoundingMode.UP).doubleValue();
    }
    
    public ArrayList<String> categoriesThatIncreased ( BudgetMonth monthToCompare, String[] categories ) {
    	
    	ArrayList<String> increased = new ArrayList<>();
    	
    	for ( int i = 0; i < categories.length; i++ ) {
    		if ( differenceInSpending(monthToCompare, categories[i]) > 0 ) {
    			increased.add(categories[i]);
    		}
    	}
    	
    	return increased;
    }
    
    public ArrayList<String> categoriesThatDecreased ( BudgetMonth monthToCompare, String[] categories ) {
    	
    	ArrayList<String> decreased = new ArrayList<String>();
    	
    	for ( int i = 0; i < categories.length; i++ ) {
    		if ( differenceInSpending(monthToCompare, categories[i]) < 0 ) {
    			decreased.add(categories[i]);
    		}
    	}
    	
    	return decreased;
    }


}