package org.java;

import org.java.exceptions.DateException;
import org.java.exceptions.PlaceException;
import org.java.pojo.abs.Event;

public class Main {
	
	public static void addPlace(Event e) {
		try {
			e.addPlace();
		} catch (DateException e1) {
			System.out.println("Date Error: " + e1);
		} catch (PlaceException e2) {
			System.out.println("Places Error: " + e2);
		}
	}
	
	public static void subPlace(Event e) {
		try {
			e.subPlace();
		} catch (DateException e1) {
			System.out.println("Date Error: " + e1);
		} catch (PlaceException e2) {
			System.out.println("Places Error: " + e2);
		}
	}
	
	public static void main(String[] args) {
		Event e = new Event("Titolo evento", "30/11/2023", 1);
		System.out.println(e);
		
		addPlace(e);
		System.out.println(e);
		
		subPlace(e);
		System.out.println(e);
		
		subPlace(e);
		System.out.println(e);	
	}
}
