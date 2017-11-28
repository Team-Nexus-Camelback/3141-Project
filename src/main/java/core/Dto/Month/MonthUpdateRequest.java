package core.Dto.Month;

import core.gateways.IRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ryan on 11/18/17.
 */
public class MonthUpdateRequest implements IRequest {
    private double spendingAmount;
    private Map<String, Double> categoryPercents;
    private String monthDate;

    public MonthUpdateRequest(String monthDate, double spendingAmount, Map<String, Double> categoryPercents) {
        this.spendingAmount = spendingAmount;
        this.categoryPercents = categoryPercents;
        this.monthDate = monthDate;
    }

    public double getSpendingAmount() {
        return spendingAmount;
    }

    public Map<String, Double> getCategoryPercents() {
        return categoryPercents;
    }

    public String getMonthDate() {
        return monthDate;
    }
}
