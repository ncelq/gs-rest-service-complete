package hello;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;

import yahoofinance.Stock;
import yahoofinance.StockPerf;
import yahoofinance.histquotes.HistoricalQuote;

public class QuoteTest {

	@Test
	public void test() throws IOException {
		String ticket = "SPY";

    	Calendar from = new GregorianCalendar(1995,11,1);	
    	Calendar to = Calendar.getInstance();
    	

    	Stock stock = StockPerf.getPerf(ticket, from, to, 50);
    	//stock.print();
    	List<HistoricalQuote> historyList = stock.getHistory();
        System.out.println("dateStr,history.getVolume(),history.getLow(),history.getHigh(),history.getOpen(),history.getClose(),history.getAdjClose(),history.getAvg(),history.getStd(),history.getStdNo(),history.getAvgStdNo()");    	
    	for (HistoricalQuote history : historyList) {
    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = dateFormat.format(history.getDate().getTime());
            System.out.println(dateStr+","+history.getVolume()+","+history.getLow()+","+history.getHigh()+","+history.getOpen()+","+history.getClose()+","+history.getAdjClose()+","+history.getAvg()+","+history.getStd()+","+history.getStdNo()+","+history.getAvgStdNo());
    	}
    	
	}

}
