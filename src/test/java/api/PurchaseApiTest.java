package api;

import org.junit.jupiter.api.Test;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ryan on 10/18/17.
 */
public class PurchaseApiTest {

    @Test
    void saveASimplePurchase() {
        PurchaseManager manager = PurchaseManager.getInstance();
//        Hashtable<String, String> newPurchaseData = manager.savePurchaseData(0,"Toys", 19.99f);
//        float newAmountData = getPurchaseAmountFromData(newPurchaseData);
//        assertTrue(newAmountData == 19.99f);
    }

    private float getPurchaseAmountFromData(Hashtable<String, String> newPurchaseData) {
        String rawMessage = newPurchaseData.get("amount");
        return Float.parseFloat(rawMessage);
    }
}
