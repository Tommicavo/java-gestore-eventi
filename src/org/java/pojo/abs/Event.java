package org.java.pojo.abs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.java.exceptions.DateException;
import org.java.exceptions.PlaceException;
import org.java.exceptions.TitleException;

public abstract class Event {
	
	private String title;
	private LocalDate date;
	private int totalPlaces;
	private int places;
	
	public Event(String title, String strDate, int totalPlaces) throws DateException, TitleException {
		checkTitle(title);
		formatDate(strDate);
		setTotalPlaces(totalPlaces);
		setPlaces(0);
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getTotalPlaces() {
		return totalPlaces;
	}
	private void setTotalPlaces(int totalPlaces) {
		this.totalPlaces = totalPlaces;
	}
	public int getPlaces() {
		return places;
	}
	private void setPlaces(int places) {
		this.places = places;
	}
	
	private void checkTitle(String title) throws TitleException{
		if (title == null) {
			throw new TitleException("You must enter a title.");
		}
		setTitle(title);
	}
	
	private void formatDate(String strDate) throws DateException {
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
        try {
            LocalDate date = LocalDate.parse(strDate, formatter);
            setDate(date);
        } catch (DateTimeParseException e) {
            throw new DateException("Invalid Date Format.\nTry again using this format: dd-mm-yyyy");
        }
		if (getDate().isBefore(today)) {
			throw new DateException("You can't create an event in the past.");
		}
	}
	
	private String getDateTitle() {
		return getDate() + " - " + getTitle();
	}
	
	public void addPlace() throws DateException, PlaceException {
		LocalDate today = LocalDate.now();
		if (getDate().isBefore(today)) {
			throw new DateException("This event has already passed.");
		}
		if (getPlaces() >= getTotalPlaces()) {
			throw new PlaceException("This event is already sold out.");
		}
		setPlaces(getPlaces() + 1);
	}
	
	public void subPlace() throws DateException, PlaceException {
		LocalDate today = LocalDate.now();
		if (getDate().isBefore(today)) {
			throw new DateException("This event has already passed.");
		}
		if (getPlaces() < 1) {
			throw new PlaceException("There are no ticket sold for this event.");
		}
		setPlaces(getPlaces() - 1);
	}
	
	@Override
	public String toString() {
		return 
				getDateTitle() + '\n' +
				"Total places: " + getTotalPlaces() + '\n' +
				"Places: " + getPlaces(); 
	}
}
