package core.Dto.Purchase;

import core.gateways.ResponseMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ryan on 10/17/17.
 */
public class PurchaseResponseMessage extends ResponseMessage<Map<String, String>> {

    private Map<String, String> purchaseData = new HashMap<>();

    public PurchaseResponseMessage(Map<String, String> purchaseData) {
        this.purchaseData = purchaseData;
        if (purchaseData.get(PurchaseKeys.ERROR.key()) != null)
            this.successful = false;
    }

    @Override
    public Map<String, String> getMessage() {
        return purchaseData;
    }
}
