
package yahoofinance;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import hello.Statistics;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class StockPerf extends YahooFinance {

	public static Stock getPerf(String symbol, Calendar from, Calendar to, int avgDays) throws IOException {
		Calendar orgFrom = (Calendar) from.clone();
		from.add(Calendar.DATE, (0 - avgDays) * 3); // from 5 years ago

		Stock stock = get(symbol, from, to, Interval.DAILY);
		List<HistoricalQuote> history = stock.getHistory();
		List<Double> closePrice = new ArrayList<Double>();
		List<HistoricalQuote> quoteList = new ArrayList<HistoricalQuote>();
		int breakCount = -1;
		for (int i = 0; i < history.size(); i++) {

			
			if (closePrice.size() < avgDays) {
				closePrice.add(history.get(i).getAdjClose().doubleValue());
			} else if (closePrice.size() == avgDays) {

				Statistics stats = new Statistics(closePrice.toArray(new Double[closePrice.size()]));
				// System.out.println(stats.getMean());

				HistoricalQuote quote = history.get(i-avgDays);
				quote.setAvg(stats.getMean());
				quote.setStd(stats.getStdDev());

				closePrice.remove(0);
				closePrice.add(history.get(i).getAdjClose().doubleValue());
			}
			if (history.get(i).getDate().compareTo(orgFrom) == -1 && breakCount == -1) {
				breakCount = i;
			}
		}
		
		
		
		for (int i = 0; i < history.size(); i++) {

			
			if (quoteList.size() < avgDays) {
				quoteList.add(history.get(i));
			} else if (quoteList.size() == avgDays) {

				double total = 0;
				//Statistics stats = new Statistics(closePrice.toArray(new Double[closePrice.size()]));
				for (HistoricalQuote item : quoteList) {
					total += item.getStdNo();
				}
				

				HistoricalQuote quote = history.get(i-avgDays);
				quote.setAvgStdNo(total/quoteList.size());

				quoteList.remove(0);
				quoteList.add(history.get(i));
			}
		}
		
		
		
		List<HistoricalQuote> realHistory = history.subList(0, breakCount);
		stock.setHistory(realHistory);
		
		//stock.setHistory(history);
		return stock;
	}
	
	public static String format(Calendar calendar){
	    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
	    fmt.setCalendar(calendar);
	    String dateFormatted = fmt.format(calendar.getTime());
	    return dateFormatted;
	}
}
