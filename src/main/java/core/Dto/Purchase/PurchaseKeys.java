package core.Dto.Purchase;

/**
 * Created by ryan on 11/29/17.
 */
public enum PurchaseKeys {
    ID("id"),
    AMOUNT("amount"),
    CATEGORY("category"),
    ERROR("error");

    private String keyName;
    PurchaseKeys(String s) {
        keyName = s;
    }

    public String key(){
        return keyName;
    }
}
