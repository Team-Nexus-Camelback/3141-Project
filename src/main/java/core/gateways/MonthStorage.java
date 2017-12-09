package core.gateways;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import core.entities.BudgetMonth;
import datastorage.MonthReader;
import datastorage.MonthWriter;
import core.entities.Purchase;
import org.jdom2.Element;
import org.jdom2.JDOMException;

public class MonthStorage implements BudgetMonthRepository {
	private MonthReader reader;
	private MonthWriter writer;

	public MonthStorage() {
		writer = new MonthWriter();
		try {
			reader = new MonthReader();
		} catch (IOException | JDOMException e) {
			e.printStackTrace();
		}
	}

	@Override
	public BudgetMonth getMonthFromDate(String date) {
		return turnElementIntoMonth(reader.getMonthFromDate(date));
	}

	@Override
	public boolean saveBudgetMonth(BudgetMonth month) {
		return writer.saveMonthData(month);
	}

	@Override
	public boolean deletePurchase(String monthDate, int id) {
		BudgetMonth month = turnElementIntoMonth(reader.getMonthFromDate(monthDate));
		month.getPurchasesList().removeIf(purchase -> purchase.getId() == id);
		return true;
	}

	@Override
	public List<BudgetMonth> monthsFromYear(String year) {
		// TODO Auto-generated method stub
		return null;
	}

	private BudgetMonth turnElementIntoMonth(Element monthElement){
		String date = monthElement.getAttribute("date").getValue();
		double budget = Double.parseDouble(monthElement.getChild("budget").getText());
		BudgetMonth month = new BudgetMonth(date, budget);
		monthElement.getChildren("Category").forEach(element -> {
            String catName = element.getAttribute("name").getValue();
			Double catAmount = Double.parseDouble(element.getText());
            month.addNewCategoryBudgetIfNew(catName, catAmount);
		});
		monthElement.getChildren("Purchase").forEach(element -> {
            int id = Integer.parseInt(element.getAttributeValue("id"));
            double amount = Double.parseDouble(element.getChildText("amount"));
			try {
				Date purchaseDate = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH)
						.parse(element.getChildText("date"));
				String name = element.getChildText("name");
				String category = element.getChildText("category");
				Purchase purchase = new Purchase(amount, purchaseDate, name, category, id);
				month.addPurchase(purchase);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		});
		return month;
	}


}
