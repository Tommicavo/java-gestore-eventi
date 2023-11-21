package org.java;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.java.exceptions.DateException;
import org.java.exceptions.TitleException;
import org.java.pojo.abs.Event;

public class ProgramEvents {
	
	private String title;
	private List<Event> events;
	
	public ProgramEvents(String title, List<Event> events) throws TitleException {
		checkTitle(title);
		setEvents(events);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	private void checkTitle(String title) throws TitleException {
		if (title == null) {
			throw new TitleException("You must enter a Program Title.");
		}
		setTitle(title);
	}
	
	public void addEvent(Event event) {
		events.add(event);
	}
	
	public String getEventOnDate(String strDate) throws DateException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		StringBuilder output = new StringBuilder();
        try {
        	LocalDate checkDate = LocalDate.parse(strDate, formatter);
        	for (Event event : getEvents()) {
        		if (event.getDate().isEqual(checkDate)) {
        			output.append(event);
        		}
        	}
        	return output.toString();
        } catch (DateTimeParseException e) {
            throw new DateException("Invalid Date Format.\nTry again using this format: dd-mm-yyyy");
        }
	}
	
	public String getProgramSize() {
		return "Number of events: " + getEvents().size() + '\n';
	}
	
	public void initProgram() {
		setEvents(new ArrayList<>());
	}
	
	public String getProgramTitles() {
		StringBuilder output = new StringBuilder();
		for (Event event : getEvents()) {
			String programTitle = event.getDate() + " - " + event.getTitle();
			output.append(programTitle);
			output.append('\n');
		}
		return output.toString();
	}
	
	public void getAllEvents() {
		for (Event event : getEvents()) {
			System.out.println(event);
		}
	}
}
