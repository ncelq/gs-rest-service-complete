package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}

/*
qurl = "http://ichart.finance.yahoo.com/table.csv?s=" & Symbol
qurl = qurl & "&a=" & Month(StartDate) - 1 & "&b=" & Day(StartDate) & _
    "&c=" & Year(StartDate) & "&d=" & Month(EndDate) - 1 & "&e=" & _
    Day(EndDate) & "&f=" & Year(EndDate) & "&g=" & Sheets(stock).Range("a1") & "&q=q&y=0&z=" & _
    Symbol & "&x=.csv"
           */