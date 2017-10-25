package core.Dto;

import core.gateways.IRequest;

/**
 * Created by ryan on 10/23/17.
 */
public class MonthRequestMessage implements IRequest {
    private boolean needsGraph;
    private String[] monthsToCompare;

    public boolean needsMonthGraphData(){
        return needsGraph;
    }

    public String[] monthsToCompare(){
      return monthsToCompare;
    }

    public MonthRequestMessage(String ... monthsToCompare) {
        this.monthsToCompare = monthsToCompare;
    }

    public MonthRequestMessage(boolean needsGraph, String[] monthsToCompare) {
        this.needsGraph = needsGraph;
        this.monthsToCompare = monthsToCompare;
    }
}
