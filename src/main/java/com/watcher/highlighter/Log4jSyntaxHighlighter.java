package com.watcher.highlighter;

/**
 * Highlighter of log files.
 * 
 * @author Aendy
 */
public class Log4jSyntaxHighlighter {

	/** Log4j INFO tag. */
	public static final String LOG4J_INFO_TAG = "INFO";
	
	/** Log4j DEBUG tag. */
	public static final String LOG4J_DEBUG_TAG = "DEBUG";
	
	/** Log4j WARN tag. */
	public static final String LOG4J_WARNING_TAG = "WARN";
	
	/** Log4j ERROR tag. */
	public static final String LOG4J_ERROR_TAG = "ERROR";
	
	/** Log4j SEVERE tag. */
	public static final String LOG4J_SEVERE_TAG = "SEVERE";
	
	/**
	 * Color used for current log block highlight.
	 */
	private String currentColor;
	
	/**
	 * Getter for current color.
	 * 
	 * @return current color
	 */
	public String getCurrentColor() {
		return currentColor;
	}

	/**
	 * Setter for current color.
	 * 
	 * @param currentColor curent color.
	 */
	public void setCurrentColor(String currentColor) {
		this.currentColor = currentColor;
	}

	/**
	 * Method which format current log line without coloring.
	 * 
	 * Used in reverse log reading... should be better but for now is just this...
	 * 
	 * TODO do it smarter with coloring - something like for timeline.
	 * 
	 * @param input log line
	 * 
	 * @return modified line
	 */
	public String formatWithoutColor(String input) {
		
		//part of stacktrace - fill with current color
		if (input.startsWith("\t")) {
			
			return "<blockquote style=\"margin: 0 0 0 40px; "
									 + "border: none; "
									 + "padding: 0px;\"> "
				 + input
				 + "</blockquote>";
			
		}
		
		return input 
			 + "<br>";
		
	}
	
	
	/**
	 * Method which highlight current log line depending on its tags.
	 * 
	 * @param input log line
	 * 
	 * @return modified line
	 */
	public String highlight(String input) {
		
		//part of stacktrace - fill with current color
		if (input.startsWith("\t")) {
			
			return "<blockquote style=\"margin: 0 0 0 40px; "
									 + "border: none; "
									 + "padding: 0px;\"> "
				 + surroundRowWithColor(input)
				 + "</blockquote>";
			
		}
		
		defineNextColor(input);
		
		return surroundRowWithColor(input) 
			 + "<br>";
		
	}
	 
	/**
	 * Method for surrounding log line with HTML code so it uses currenty defined color.
	 * 
	 * @param input line from log file which we color if we should.
	 * 
	 * @return modified line
	 */
	private String surroundRowWithColor(String input) {
		
		if (getCurrentColor() == null) {
			
			return input;
			
		}
		
		return "<span style=\"color: " 
		   	 + getCurrentColor() 
		   	 + ";\">"
		   	 + input
		   	 + "</span>";
		
	}
	
	/**
	 * Method that defines color for next block of log lines.
	 * 
	 * Depending on log TAG color is picked and used until next block start.
	 * 
	 * @param input input line
	 */
	private void defineNextColor(String input) {
		
		if (input.contains(LOG4J_DEBUG_TAG)) {
			
			setCurrentColor(debugTagColor);
			
		} else if (input.contains(LOG4J_WARNING_TAG)) {
			
			setCurrentColor(warningTagColor);
			
		} else if (input.contains(LOG4J_ERROR_TAG)) {
			
			setCurrentColor(errorTagColor);
			
		} else if (input.contains(LOG4J_SEVERE_TAG)) {
			
			setCurrentColor(severeTagColor);
			
		} else if (input.contains(LOG4J_INFO_TAG)) {
			
			setCurrentColor(null);
			
		}
		
	}
	
	// parameters from configuration
	
	/** Log4j color used for DEBUG lines. */
	public String debugTagColor;	
	
	/** Log4j color used for WARNING lines. */
	public String warningTagColor;
	
	/** Log4j color used for ERROR lines. */
	public String errorTagColor;
	
	/** Log4j color used for SEVERE lines. */
	public String severeTagColor;
	
	/**
	 * 
	 * @param debugTagColor IOC
	 */
	public void setDebugTagColor(String debugTagColor) {
		this.debugTagColor = debugTagColor;
	}

	/**
	 * 
	 * @param warningTagColor IOC
	 */
	public void setWarningTagColor(String warningTagColor) {
		this.warningTagColor = warningTagColor;
	}

	/**
	 * 
	 * @param errorTagColor IOC
	 */
	public void setErrorTagColor(String errorTagColor) {
		this.errorTagColor = errorTagColor;
	}

	/**
	 * 
	 * @param severeTagColor IOC
	 */
	public void setSevereTagColor(String severeTagColor) {
		this.severeTagColor = severeTagColor;
	}
	
}
 