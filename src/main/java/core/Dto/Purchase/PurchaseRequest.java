package core.Dto.Purchase;

/**
 * Created by ryan on 11/13/17.
 */
public interface PurchaseRequest {

    String getAmount();

    String getCategory();

    String getDate();

    int getId();
}
