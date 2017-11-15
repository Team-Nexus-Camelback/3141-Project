package core.Dto.Month;

/**
 * Created by ryan on 10/27/17.
 */
public enum MonthKeys {
    ERROR("error"),
    MONTHLY_BUDGET("budget"),
    DATE("date"),
    OVERVIEW("overview"),
    CATEGORY_SPENT("categories"),
    PAYMENTS("payments"),
    PURCHASES("purchases");

    private String keyName;

    MonthKeys(String keyName){
        this.keyName = keyName;
    }

    public String getName(){
        return keyName;
    }
}