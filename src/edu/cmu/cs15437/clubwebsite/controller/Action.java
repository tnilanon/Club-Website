package edu.cmu.cs15437.clubwebsite.controller;

import javax.servlet.http.HttpServletRequest;

import java.util.*;

public abstract class Action {
	// Mapping for all supported actions
	private static Map< String, Action > map = new HashMap< String, Action >();

	// Returns the name of the action. It is used to match the incoming request.
	public abstract String getName();
	
	// Returns the filename of the jsp used to render the output.
	public abstract String perform(HttpServletRequest request);
	
	// Add new supported action
	public static void add(Action a) {
		synchronized (map) {
			map.put(a.getName(), a);
		}
	}
	
	public static String perform(String actionName, HttpServletRequest request) {
		Action a;
		synchronized (map) {
			a = map.get(actionName);
		}
		
		if (a == null) return null;
		return a.perform(request);
	}
}

