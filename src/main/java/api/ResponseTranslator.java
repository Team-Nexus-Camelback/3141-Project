package api;

import models.Payment;
import models.Purchase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        models.Purchase newPurchase = createPurchaseFromString(purchasesData);
        purchaseArrayList.add(newPurchase);
        return purchaseArrayList;
    }

    public static models.Purchase createPurchaseFromString(String purchase) {
        String[] dataValues = purchase.split(" ");
        String name = "";
        float amount = 0.0f;
        String date = "";
        String category = "";

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
                default:
            }
        }
        return new models.Purchase(amount, date, category, name);
    }

    public static List<Payment> paymentsFromResponse(String paymentData){
        paymentData = removeBoundry(paymentData);
        String[] payments = paymentData.split(",");
        ArrayList<Payment> paymentArrayList = new ArrayList<>();
        for (String payment : payments){
            Payment newPayment = createPaymentFromString(payment);
            paymentArrayList.add(newPayment);
        }
        return paymentArrayList;
    }

    public static Payment createPaymentFromString(String payments){
        String[] dataValues = payments.split(" ");
        String name = "";
        double amount = 0.0;
        String date = "";
        boolean isPaid = false;
        for (int i = 0; i < dataValues.length; i++){
            switch (dataValues[i]){
                case "paymentName":
                    name = dataValues[i+1];
                    break;
                case "amount":
                    amount = Double.parseDouble(dataValues[i+1]);
                    break;
                case "dueDate":
                    date = dataValues[i+1];
                    break;
                case "isPaid":
                    isPaid = Boolean.parseBoolean(dataValues[i+1]);
                    break;
            }
        }
        try {
            return new Payment(name, amount, date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String removeBoundry(String s){
        return s.substring(1, s.length() -1); // removes { }
    }
}
