package core.Dto;

import core.gateways.IRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 10/16/17.
 * The class that outside systems will use to communicate with internal system
 */
public class PaymentRequestMessage implements IRequest {
    private boolean verboseData;
    private List<Integer> payments;
    private boolean needsAllPayments;

    public boolean needsAllPayments() {
        return needsAllPayments;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public boolean isVerboseData() {
        return verboseData;
    }

    public PaymentRequestMessage(int paymentID) {
        this.payments = new ArrayList<>(); // defaults to array list
        this.payments.add(paymentID);
        this.verboseData = false;
    }

    public PaymentRequestMessage(int paymentID, boolean verboseData) {
        this.payments = new ArrayList<>();
        this.payments.add(paymentID);
        this.verboseData = verboseData;
    }

    public PaymentRequestMessage(List<Integer> payments) {
        this.payments = payments;
        this.verboseData = false;
    }

    public PaymentRequestMessage(boolean verboseData, List<Integer> payments) {
        this.verboseData = verboseData;
        this.payments = payments;
    }

    public PaymentRequestMessage(boolean needsAllPayment){
        needsAllPayments = needsAllPayment;
    }
}
