package core.Dto.Payment;

import core.gateways.ResponseMessage;

import java.util.HashMap;
import java.util.List;

/**
 * Created by ryan on 10/16/17.
 * how a payment request will be responded too
 */
public class PaymentResponseMessage extends ResponseMessage<List<HashMap<String, String>>> {

    public PaymentResponseMessage(List<HashMap<String, String>> message) {
        this.message = message;
    }

    @Override
    public List<HashMap<String, String>> getMessage() {
        return message;
    }




}
