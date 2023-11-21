package org.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.java.exceptions.DateException;
import org.java.exceptions.HourException;
import org.java.exceptions.PlaceException;
import org.java.exceptions.PriceException;
import org.java.exceptions.TitleException;
import org.java.pojo.Concert;
import org.java.pojo.abs.Event;

public class Main {
	
	public static void reserveTickets(Event event, Scanner in) {
		int newTickets = 0;
		while (true) {
			System.out.println("Do you want to reserve some tickets?");
			System.out.println("1 - yes\n2 - no");
			String ticketsToBuy = in.nextLine();
			
			if (!ticketsToBuy.equals("1") && !ticketsToBuy.equals("2")) {
				System.out.println("Invalid input, try again");
				continue;
			}
			else if (ticketsToBuy.equals("1")) {
				System.out.println("How many tickets you want to buy?");
				newTickets = Integer.valueOf(in.nextLine());
			}
			break;
		}
		
		if (newTickets > 0) {
			int boughtTickets = 0;
			for (int i = 0; i < newTickets; i++) {
				try {
					event.addPlace();
					boughtTickets++;
				} catch (DateException e) {
					System.out.println("Date Error: " + e);
				} catch (PlaceException e) {
					System.out.println("Places Error: " + e);
				}
			}
			System.out.println("You bought " + boughtTickets + " tickets!");
		}
		
	}
	
	public static void cancelTickets(Event event, Scanner in) {
		int lessTickets = 0;
		while (true) {
			System.out.println("Do you want to cancel some tickets?");
			System.out.println("1 - yes\n2 - no");
			String ticketsToCancel = in.nextLine();
			
			if (!ticketsToCancel.equals("1") && !ticketsToCancel.equals("2")) {
				System.out.println("Invalid input, try again");
				continue;
			}
			else if (ticketsToCancel.equals("1")) {
				System.out.println("How many tickets you want to cancel?");
				lessTickets = Integer.valueOf(in.nextLine());
			}
			break;
		}
		
		if (lessTickets > 0) {
			int canceledTickets = 0;
			for (int i = 0; i < lessTickets; i++) {
				try {
					event.subPlace();
					canceledTickets++;
				} catch (DateException e) {
					System.out.println("Date Error: " + e);
				} catch (PlaceException e) {
					System.out.println("Places Error: " + e);
				}
			}
			System.out.println("You canceled " + canceledTickets + " tickets!");
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		List<Event> events = new ArrayList<>();

		Event event = null;
		boolean newEvent = true;
		while (true) {
			while (true) {
				System.out.println("Do you want to create an event?");
				System.out.println("1 - yes\n2 - no");
				String createEvent = in.nextLine();
				
				if (!createEvent.equals("1") && !createEvent.equals("2")) {
					System.out.println("Invalid input, try again");
					continue;
				}
				else if (createEvent.equals("2")) {
					newEvent = false;
					break;
				} else if (createEvent.equals("1")) break;
			}
			if (!newEvent) break;
			
			System.out.println("Event title: ");
			String title = in.nextLine();
			
			System.out.println("Event date: ");
			System.out.println("(Write date in this format: dd-mm-yyyy)");
			String date = in.nextLine();
			
			System.out.println("Event hour: ");
			String hour = in.nextLine();
			
			System.out.println("Event total places: ");
			int totalPlaces = Integer.valueOf(in.nextLine());
			
			System.out.println("Ticket price: ");
			String strPrice = in.nextLine();
			
			try {
				event = new Concert(title, date, totalPlaces, hour, strPrice);
				events.add(event);
				System.out.println("Event created successfully!\n");
				reserveTickets(event, in);
				cancelTickets(event, in);
				System.out.println("\nRESUME EVENT");
				System.out.println(event);
			} catch (TitleException e) {
				System.out.println("Title Error: " + e.getMessage() + '\n');
			} catch (DateException e) {
				System.out.println("Date Error: " + e.getMessage() + '\n');
			} catch (HourException e) {
				System.out.println("Hour Error: " + e.getMessage() + '\n');
			} catch (PriceException e) {
				System.out.println("Price Error: " + e.getMessage() + '\n');
			}
			continue;
		}

		System.out.println("TOTAL EVENT LIST:");
		for (Event e : events) {
			System.out.println(e);
			
		}
		
		System.out.println("the end");
	}
}
