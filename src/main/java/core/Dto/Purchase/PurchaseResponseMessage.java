package core.Dto.Purchase;

import core.gateways.ResponseMessage;

import java.util.Hashtable;

/**
 * Created by ryan on 10/17/17.
 */
public class PurchaseResponseMessage extends ResponseMessage<Hashtable<String, String>> {

    private Hashtable<String, String> purchaseData = new Hashtable<>();

    public PurchaseResponseMessage(Hashtable<String, String> purchaseData) {
        this.purchaseData = purchaseData;
    }

    @Override
    public Hashtable<String, String> getMessage() {
        return purchaseData;
    }
}
