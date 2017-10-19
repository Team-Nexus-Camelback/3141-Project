package core.entities;

/**
 * Created by ryan on 10/17/17.
 */
public class Purchase {
    private float amount;
    private String purchaseName;
    private String category;
    private int id;

    public Purchase(int id, float amount, String category) {
        this.id = id;
        this.amount = amount;
        this.category = category;
    }

    public float getAmount() {
        return amount;
    }

    public String getPurchaseName() {
        return purchaseName;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }
}
