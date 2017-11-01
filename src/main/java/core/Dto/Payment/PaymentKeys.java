package core.Dto.Payment;

/**
 * Created by ryan on 11/1/17.
 */
public enum PaymentKeys {
    ERROR("error"),
    DUE_DATE("date"),
    AMOUNT("amount"),
    NAME("name");

    private String keyName;

    PaymentKeys(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyName() {
        return keyName;
    }
}
