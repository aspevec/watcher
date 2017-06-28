package com.watcher.highlighter;

import com.watcher.util.WatcherStringUtil;

/**
 * Highlighter of configuration files.
 * 
 * @author Aendy
 */
public class ConfigSyntaxHighlighter {

	/**
	 * Highlight configuration file row.
	 * 
	 * @param input configuration file row
	 * 
	 * @return highlighted configuration file row
	 */
	public String highlight(String input) {
		
		return highlightBasic(input);
		
	}
	
	/**
	 * Method for highlight configuration file.
	 * 
	 * Highlight comments or highlight parameters.
	 * 
	 * @param input configuration file row
	 * 
	 * @return highlighted configuration file row
	 */
	private String highlightBasic(String input) {
		
		if (WatcherStringUtil.isEmpty(input)) {
			
			return input;
			
		}
		
		if (input.startsWith("#")) {
			
			return "<span style=\"color: " 
				 + configCommentColor 
				 + ";\">"
				 + input
				 + "</span>";
		
		} else {
			
			return  "<span style=\"font-weight: bold;\">"
					 + input.substring(0, input.indexOf("="))
					 + "</span>"
					 + input.substring(input.indexOf("="));
		
		}
		
	}
	
	/** Color for defining color of comments in config files. */
	private String configCommentColor;
	
	/**
	 * 
	 * @param configCommentColor IOC
	 */
	public void setConfigCommentColor(String configCommentColor) {
		this.configCommentColor = configCommentColor;
	}
	
}
 