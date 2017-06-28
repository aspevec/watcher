package com.watcher.util;


/**
 * Util class for doing common task with {@link String} objects.
 * 
 * @author Aendy
 */
public class WatcherStringUtil {

	/**
	 * Method for checking if string value is null or empty.
	 * 
	 * @param value value to check
	 * 
	 * @return true if it is empty, false if it contains something
	 */
	public static boolean isEmpty(String value) {
		
		return value == null
					||
					value.isEmpty();
		
	}
	
	/**
	 * Method for checking if multiple string values are null or empty.
	 * 
	 * @param multiple values to check
	 * 
	 * @return true if at least one is empty, false if every arg is not empty
	 */
	public static boolean isEmptyMultiple(String... args) {
		
		boolean result = false;
		
		for (String arg : args) {
			
			result = isEmpty(arg);
			
			if (result) {
				
				return result;
				
			}
			
		}
		
		return result;
		
	}

}
