package org.java;

import java.util.ArrayList;
import java.util.List;
import org.java.exceptions.DateException;
import org.java.exceptions.HourException;
import org.java.exceptions.PriceException;
import org.java.exceptions.TitleException;
import org.java.pojo.Concert;
import org.java.pojo.abs.Event;

public class MainBonus {
	public static void main(String[] args) {
		
		List<Event> events = new ArrayList<>();
		Concert c1 = null;
		Concert c2 = null;
		Concert c3 = null;
		
		try {
			c1 = new Concert("Concerto_1", "20-12-2023", 5000, "20:30", "22.5");
		} catch (TitleException e) {
			System.out.println("Title Error: " + e.getMessage() + '\n');
		} catch (DateException e) {
			System.out.println("Date Error: " + e.getMessage() + '\n');
		} catch (HourException e) {
			System.out.println("Hour Error: " + e.getMessage() + '\n');
		} catch (PriceException e) {
			System.out.println("Price Error: " + e.getMessage() + '\n');
		}
		
		try {
			c2 = new Concert("Concerto_2", "25-12-2023", 15000, "21:30", "30");
		} catch (TitleException e) {
			System.out.println("Title Error: " + e.getMessage() + '\n');
		} catch (DateException e) {
			System.out.println("Date Error: " + e.getMessage() + '\n');
		} catch (HourException e) {
			System.out.println("Hour Error: " + e.getMessage() + '\n');
		} catch (PriceException e) {
			System.out.println("Price Error: " + e.getMessage() + '\n');
		}
		
		try {
			c3 = new Concert("Concerto_3", "20-12-2023", 3000, "10:00", "15");
		} catch (TitleException e) {
			System.out.println("Title Error: " + e.getMessage() + '\n');
		} catch (DateException e) {
			System.out.println("Date Error: " + e.getMessage() + '\n');
		} catch (HourException e) {
			System.out.println("Hour Error: " + e.getMessage() + '\n');
		} catch (PriceException e) {
			System.out.println("Price Error: " + e.getMessage() + '\n');
		}
		
		events.add(c1);
		events.add(c2);
		
		ProgramEvents programEvents = null;
		try {
			programEvents = new ProgramEvents("Best Events List", events);
		} catch (TitleException e) {
			System.out.println("Title Error: " + e.getMessage());
		}
		
		programEvents.addEvent(c3);
		
		String myDate = "20-12-2023";
		try {
			System.out.println("Programmed events on " + myDate);
			System.out.println(programEvents.getEventOnDate(myDate));
		} catch (DateException e) {
			System.out.println("Date Error: " + e.getMessage());
		}
		
		System.out.println(programEvents.getProgramSize());

		// programEvents.initProgram();
		
		System.out.println("List of event Titles:");
		System.out.println(programEvents.getProgramTitles());
	}
}
