package api;

import core.Dto.Payment.PaymentKeys;
import models.Payment;
import models.Purchase;
import core.Dto.Payment.PaymentKeys;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ryan on 11/13/17.
 */
public class ResponseTranslator {

    public static HashMap<String,Double> getOverviewGraphFromResponse(String s) {
        s = removeBoundry(s);
        String[] values = s.split(",");
        HashMap<String, Double> returnMap = new HashMap<>();
        for (String pair : values){
            String[] entry = pair.split("=");
            returnMap.put(entry[0].trim(), Double.parseDouble(entry[1].trim()));
        }
        return returnMap;
    }

    public static HashMap<String,Double> getCategoryGraphFromResponse(String s) {
        s = removeBoundry(s);
        String categories[] = s.split(",");
        HashMap<String, Double> returnMap = new HashMap<>();
        String keyname = "";
        for (String cat : categories){
            try{
                double percent = Double.parseDouble(cat);
                returnMap.put(keyname, percent);
            }catch (Exception e){
                keyname = cat;
            }
        }
        return returnMap;
    }

    public static List<Purchase> purchaseFromResponse(String purchasesData) {
        purchasesData = removeBoundry(purchasesData);
        ArrayList<Purchase> purchaseArrayList = new ArrayList<>();
        String[] purchases = purchasesData.split(",");
        for (String purchase : purchases) {
            models.Purchase newPurchase = createPurchaseFromString(purchase);
            purchaseArrayList.add(newPurchase);
        }
        return purchaseArrayList;
    }

    public static models.Purchase createPurchaseFromString(String purchase) {
        String[] dataValues = purchase.split(" ");
        String name = "";
        float amount = 0.0f;
        String date = "";
        String category = "";
        int id = -1;

        for (int i = 0; i < dataValues.length; i++) {
            switch (dataValues[i]) {
                case "name":
                    name = dataValues[i + 1];
                    break;
                case "amount":
                    amount = Float.parseFloat(dataValues[i + 1]);
                    break;
                case "date":
                    date = dataValues[i + 1];
                    break;
                case "category":
                    category = dataValues[i + 1];
                    break;
                case "id":
                    id = Integer.parseInt(dataValues[i+1]);
                    break;
                default:
            }
        }
        return new models.Purchase(id, amount, date, category, name);
    }

    public static List<Payment> paymentsFromResponse(String paymentData){
        paymentData = removeBoundry(paymentData);
        String[] payments = paymentData.split("},");
        ArrayList<Payment> paymentArrayList = new ArrayList<>();
        for (String payment : payments){
            Payment newPayment = createPaymentFromString(payment);
            paymentArrayList.add(newPayment);
        }
        return paymentArrayList;
    }

    public static Payment createPaymentFromString(String payments){
        if (payments.isEmpty())
            return null;
        payments = payments.substring(1, payments.length() -1);
        payments = payments.replace("=", ",");
        String[] dataValues = payments.split(",");
        Date paymentDate = null;
        String paymentName = "";
        Double paymentAmount = -1000.;
        for (int i = 0; i < dataValues.length;i++){
            switch (dataValues[i]){
                case "date":
                    String dateString = dataValues[i+1];
                    try {
                        paymentDate = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH).parse(dateString);
                    } catch (ParseException e) {
                        return  null;
                    }
                    break;
                case " amount":
                    paymentAmount = Double.parseDouble(dataValues[i+1]);
                    break;
                case " name":
                    paymentName = dataValues[i+1];
                    break;
            }
        }
        return new Payment(0, paymentName,paymentAmount, paymentDate);
    }

    private static String removeBoundry(String s){
        return s.substring(1, s.length() -1); // removes { }
    }
}
