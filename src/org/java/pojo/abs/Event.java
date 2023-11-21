package org.java.pojo.abs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.java.exceptions.DateException;
import org.java.exceptions.PlaceException;

public class Event {
	
	private String title;
	private LocalDate date;
	private int totalPlaces;
	private int places;
	
	public Event(String title, String strDate, int totalPlaces) {
		setTitle(title);
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
	
	private void formatDate(String strDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(strDate, formatter);
		setDate(date);
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
				"Places: " + getPlaces() + "\n\n"; 
	}
}