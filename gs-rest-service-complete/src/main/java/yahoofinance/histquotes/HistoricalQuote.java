
package yahoofinance.histquotes;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * All getters can return null in case the data is not available from Yahoo Finance.
 * 
 * @author Stijn Strickx
 */
public class HistoricalQuote {
    
    private String symbol;
    
    private Calendar date;
    
    private BigDecimal open;
    private BigDecimal low;
    private BigDecimal high;
    private BigDecimal close;
    private BigDecimal adjClose;
    
    private double avg;
    private double std;
    private double avgStdNo;
    
    private Long volume;
    
    public HistoricalQuote() {}

    public HistoricalQuote(String symbol, Calendar date, BigDecimal open, BigDecimal low, BigDecimal high, BigDecimal close, BigDecimal adjClose, Long volume) {
        this.symbol = symbol;
        this.date = date;
        this.open = open;
        this.low = low;
        this.high = high;
        this.close = close;
        this.adjClose = adjClose;
        this.volume = volume;
    }
    
    

    public double getAvgStdNo() {
    	return new BigDecimal(avgStdNo).setScale(4,RoundingMode.HALF_UP).doubleValue();
	}

	public void setAvgStdNo(double avgStdNo) {
		this.avgStdNo = avgStdNo;
	}

	public double getAvg() {
		return new BigDecimal(avg).setScale(4,RoundingMode.HALF_UP).doubleValue();
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public double getStd() {
		return new BigDecimal(std).setScale(4,RoundingMode.HALF_UP).doubleValue();
	}

	public void setStd(double std) {
		this.std = std;
	}

	public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    /**
     * 
     * @return      the intra-day low
     */
    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }
    
    /**
     * 
     * @return      the intra-day high
     */
    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    /**
     * The adjusted closing price on a specific date 
     * reflects all of the dividends and splits since that day.
     * The adjusted closing price from a date in history can be used to 
     * calculate a close estimate of the total return, including dividends, 
     * that an investor earned if shares were purchased on that date.
     * @return      the adjusted close price
     */
    public BigDecimal getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(BigDecimal adjClose) {
        this.adjClose = adjClose;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }
    
    public double getStdNo() {
    	try {
    		return new BigDecimal((this.getAdjClose().doubleValue() - this.getAvg())/this.getStd()).setScale(4,RoundingMode.HALF_UP).doubleValue();	
    	} catch (NumberFormatException ex) {
    		return Double.NaN;
    	}
    	
    }
    
	@Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(this.date.getTime());
        return this.symbol + "@" + dateStr + ": " + this.low + "-" + this.high + "," + 
                this.open + "->" + this.close + "," + this.adjClose + "," + this.getAvg() + "," + this.getStd() + "," + this.getStdNo() + ","+this.getAvgStdNo()+"\n";
    }
}
