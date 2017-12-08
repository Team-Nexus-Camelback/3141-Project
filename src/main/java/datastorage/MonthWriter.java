package datastorage;

import core.entities.BudgetMonth;
import core.entities.Purchase;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;


/**
 * Created by ryan on 12/7/17.
 */
public class MonthWriter {
    private File xmlFile;
    private SAXBuilder builder = new SAXBuilder();
    private Document xmlDoc;
    private Element root;
    private XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());

    public MonthWriter() {
        xmlFile = new File("budget_data.xml");
        try {
            if (!xmlFile.exists()){
                xmlDoc = new Document();
                root = new Element("BUDGET_DATA");
                xmlDoc.setRootElement(root);
            }
            else{
                xmlDoc = builder.build(new FileInputStream(xmlFile));
                root = xmlDoc.getRootElement();
            }
        } catch (JDOMException | IOException e) {
                e.printStackTrace();
        }
    }

    public boolean saveMonthData(BudgetMonth month){
        Element monthElement = createMonthElement(month);
        month.getPurchasesList().forEach(purchase -> addPurchaseData(monthElement, purchase));
        root.addContent(monthElement);
        return writeToXML();
    }

    public boolean updateMonth(String date, BudgetMonth newMonthData){
        List<Element> months = root.getChildren("month");
        if (months.isEmpty())
            return false;
        for (Element element : months){
            if (element.getAttribute("date").getValue().equals(date)) {
                addNewDataToMonth(element, newMonthData);
                return writeToXML();
            }
        }
        return false;
    }

    public boolean deleteMonth(String date){
        if (root.getChildren().isEmpty())
            return false;
        root.getChildren("month").removeIf(element -> element.getAttribute("date").getValue().equals(date));
        return writeToXML();
    }

    private void addNewDataToMonth(Element element, BudgetMonth newMonthData) {
        element.getChild("budget").setText(String.valueOf(newMonthData.getAmountSpendingForMonth()));
        element.removeChildren("Purchase");
        newMonthData.getPurchasesList().forEach(purchase -> addPurchaseData(element, purchase));
    }

    private void addAttributeToElement(Element element, String type, Object value){
        Element typeElement = new Element(type);
        typeElement.addContent(value.toString());
        element.addContent(typeElement);
    }

    private void addPurchaseData(Element month, Purchase purchase){
        Element purchaseEle = new Element("Purchase");
        purchaseEle.setAttribute("id", String.valueOf(purchase.getId()));
        addAttributeToElement(purchaseEle, "amount", purchase.getAmount());
        addAttributeToElement(purchaseEle, "date", purchase.getDate());
        addAttributeToElement(purchaseEle, "name", purchase.getPurchaseName());
        addAttributeToElement(purchaseEle, "category", purchase.getCategory());
        month.addContent(purchaseEle);
    }

    private Element createMonthElement(BudgetMonth month){
        Element monthElement =  new Element("month");
        monthElement.setAttribute("date", month.getMonthDate());
        addAttributeToElement(monthElement, "budget", month.getAmountSpendingForMonth());
        return monthElement;
    }

    private boolean writeToXML(){
        try {
            xmlOutputter.output(xmlDoc, new FileWriter(xmlFile));
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
