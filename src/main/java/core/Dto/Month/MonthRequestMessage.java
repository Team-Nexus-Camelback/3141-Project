package core.Dto.Month;

import core.gateways.IRequest;

/**
 * Created by ryan on 10/23/17.
 */
public class MonthRequestMessage implements IRequest {
    private boolean needsGraph;
    private String[] monthsToCompare;
    private int purchaseToGet;

    public boolean needsMonthGraphData(){
        return needsGraph;
    }

    public int getPurchaseToGet() {
        return purchaseToGet;
    }

    public String[] monthsToCompare(){
      return monthsToCompare;
    }

    public MonthRequestMessage(String ... monthsToCompare) {
        this.monthsToCompare = monthsToCompare;
    }

    public MonthRequestMessage(boolean needsGraph, String ... monthsToCompare) {
        this.needsGraph = needsGraph;
        this.monthsToCompare = monthsToCompare;
        this.purchaseToGet = -1;
    }

    public MonthRequestMessage(boolean needsGraph, int purchaseToGet,  String ... monthsToCompare) {
        this.needsGraph = needsGraph;
        this.monthsToCompare = monthsToCompare;
        this.purchaseToGet = purchaseToGet;
    }
}
