package datastorage;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


/**
 * Created by ryan on 12/8/17.
 */
public class MonthReader {
    private File xmlFile;
    private SAXBuilder builder = new SAXBuilder();
    private Document xmlDoc;
    private Element root;

    public MonthReader() throws IOException, JDOMException {
        xmlFile = new File("budget_data.xml");
        if (!xmlFile.exists()){
            xmlDoc = new Document();
            root = new Element("BUDGET_DATA");
            xmlDoc.setRootElement(root);
        }
        else{
            xmlDoc = builder.build(new FileInputStream(xmlFile));
            root = xmlDoc.getRootElement();
        }
    }

    public Element getMonthFromDate(String date){
        List<Element> months =  root.getChildren("month");
        for (Element element : months){
            if (element.getAttribute("date").getValue().equals(date))
                return element;
        }
        return null;
    }
}
