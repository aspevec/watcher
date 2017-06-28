package com.watcher.controller;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 * Abstract for all controllers for adding messages to views.
 * 
 * TODO need to include calls for this methods in this project ...
 * 
 * @author Aendy
 */
public abstract class AbstractController {

	protected void addInfoMessage(String identification, 
								  String message) {
	
		addInfoMessage(identification, 
					   message, 
					   message);
		
	}
	
	protected void addInfoMessage(String identification, 
			  					  String summary, 
			  					  String details) {
		
		addMessage(identification, 
				   FacesMessage.SEVERITY_INFO, 
				   summary, 
				   details);
		
	}
	
	protected void addWarnMessage(String identification, 
			  					  String message) {

		addWarnMessage(identification, 
					   message, 
					   message);
		
	}
	
	protected void addWarnMessage(String identification, 
				  				  String summary,
				  				  String details) {
			
		addMessage(identification, 
				   FacesMessage.SEVERITY_WARN, 
				   summary, 
				   details);

	}
	
	protected void addErrorMessage(String identification, 
			  					   String message) {

		addErrorMessage(identification, 
						message, 
						message);
		
	}
	
	protected void addErrorMessage(String identification, 
				   				   String summary, 
				   				   String details) {
			
		addMessage(identification, 
				   FacesMessage.SEVERITY_ERROR, 
				   summary, 
				   details);

	}
	
	protected void addFatalMessage(String identification, 
			  					   String message) {

		addFatalMessage(identification, 
						message, 
						message);

	}
	
	protected void addFatalMessage(String identification, 
			   					   String summary,
			   					   String details) {

		addMessage(identification, 
				   FacesMessage.SEVERITY_FATAL, 
				   summary, 
				   details);
		
	}
	
	private void addMessage(String identification, 
							Severity severity, 
							String summary, 
							String details) {
				
		FacesContext.getCurrentInstance().addMessage(identification, 
		 									 		 new FacesMessage(severity,
		 											 		  		  summary,
		 											 		  		  details));
		
	}
	
}
