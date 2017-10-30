package core.Dto.Month;

import core.gateways.ResponseMessage;
import core.util.MonthComparer;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by ryan on 10/23/17.
 */
public class MonthResponseMessage extends ResponseMessage<HashMap<String, String>> {


    public MonthResponseMessage(HashMap<String, String> responseData) {
        if ((responseData.get(MonthKeys.ERROR.getName()) != null))
            successful = false;
        message = responseData;
    }

    @Override
    public HashMap<String, String> getMessage() {
        return message;
    }


}
