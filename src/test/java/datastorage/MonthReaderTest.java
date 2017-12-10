package datastorage;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by ryan on 12/8/17.
 */
class MonthReaderTest {

    @Test
    public void getMonthData(){
        try {
            MonthReader reader = new MonthReader();
            Element requestMonth = reader.getMonthFromDate("11-2017");
            assertTrue(requestMonth.getChild("budget").getText().equals("1000.0"));
        } catch (IOException | JDOMException e) {
            e.printStackTrace();
            fail("you fucked up Ryan");
        }
    }

}