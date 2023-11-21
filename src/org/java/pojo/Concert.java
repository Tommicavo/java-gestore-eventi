package org.java.pojo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.java.exceptions.DateException;
import org.java.exceptions.HourException;
import org.java.exceptions.PriceException;
import org.java.exceptions.TitleException;
import org.java.pojo.abs.Event;

public class Concert extends Event {

	private LocalTime hour;
	private BigDecimal price;
	
	public Concert(String title, String strDate, int totalPlaces, String strHour, String strPrice) throws DateException, HourException, PriceException, TitleException {
		super(title, strDate, totalPlaces);
		formatHour(strHour);
		formatPrice(strPrice);
	}

	public LocalTime getHour() {
		return hour;
	}
	public void setHour(LocalTime hour) {
		this.hour = hour;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
 
    public void formatHour(String strHour) throws HourException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime hour = LocalTime.parse(strHour, formatter);
            setHour(hour);
        } catch (DateTimeParseException e) {
            throw new HourException("Invalid Hour Format.\nTry again using this format: HH:mm");
        }
    }
    
    private void formatPrice(String strPrice) throws PriceException {
        try {
        	BigDecimal price = new BigDecimal(strPrice);
        	setPrice(price);
        } catch (NumberFormatException | ArithmeticException e) {
            throw new PriceException("Invalid Price Format. Please enter a valid price.");
        }
    }    
    
    public String getFormattedPrice() {
    	BigDecimal formattedPrice = getPrice().setScale(2, RoundingMode.HALF_UP);
    	return formattedPrice + "€";
    }
    
    public String getIntroEvent() {
    	return super.getDate() + " " + getHour() + " - " + super.getTitle() + " - " + getFormattedPrice();
    }
    
    @Override
    public String toString() {
    	return
    			getIntroEvent() + '\n' +
    			"Concert: " + super.getTitle() + '\n' +
    			"Date: " + super.getDate() + '\n' +
    			"Hour: " + getHour() + '\n' +
    			"Price: " + getFormattedPrice() + '\n' +
    			"Places: " + super.getPlaces() + " / " + super.getTotalPlaces() + "\n\n";
    }
}
