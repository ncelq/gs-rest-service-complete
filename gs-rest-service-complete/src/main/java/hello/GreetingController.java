package hello;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/quote/{ticket}")
    public void quote(@PathVariable("ticket") String ticket) throws IOException {
    	/*
    	Calendar from = Calendar.getInstance();
    	Calendar to = Calendar.getInstance();
    	from.add(Calendar.YEAR, -5); // from 5 years ago

    	Stock google = YahooFinance.get(ticket, from, to, Interval.DAILY);
    	google.print();
    	*/
    	/*
    	Stock stock = YahooFinance.get(ticket);
    	stock.print();
    	*/
    	
    	Stock stock = YahooFinance.get(ticket);
    	BigDecimal price = stock.getQuote(true).getPrice();
    	System.out.println("---->"+price);
    }
}


//http://ichart.finance.yahoo.com/table.csv?s=SPY&a=0&b=1&c=2010&d=6&e=15&f=2016&g=d&q=q&y=0&z=SPY&x=.csv
/* 
qurl = "http://ichart.finance.yahoo.com/table.csv?s=" & Symbol
qurl = qurl & "&a=" & Month(StartDate) - 1 & "&b=" & Day(StartDate) & _
    "&c=" & Year(StartDate) & "&d=" & Month(EndDate) - 1 & "&e=" & _
    Day(EndDate) & "&f=" & Year(EndDate) & "&g=" & Sheets(stock).Range("a1") & "&q=q&y=0&z=" & _
    Symbol & "&x=.csv"
           */