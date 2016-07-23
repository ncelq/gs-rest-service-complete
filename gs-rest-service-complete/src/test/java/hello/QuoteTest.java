package hello;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import yahoofinance.Stock;
import yahoofinance.StockPerf;

public class QuoteTest {

	@Test
	public void test() throws IOException {
		String ticket = "SPY";

    	Calendar from = new GregorianCalendar(2007,0,1);	
    	Calendar to = Calendar.getInstance();
    	

    	Stock stock = StockPerf.getPerf(ticket, from, to, 50);
    	stock.print();
	}

}
