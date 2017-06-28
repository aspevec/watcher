package com.watcher.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.net.telnet.TelnetClient;
import org.apache.log4j.Logger;

import com.watcher.service.ReachableService;

/**
 * Service implementation for checking accessibility for URL from server where Tomcat is located.
 * 
 * Can check pink and telnet access.
 * 
 * @author Aendy
 */
public class ReachableServiceImpl implements ReachableService {

	/** Logger. */
	private final Logger logger = Logger.getLogger(ReachableServiceImpl.class);
	
	@Override
	public String checkPing(String host) {
		
		return runSystemCommand("ping " 
							  + host);
		
	}

	/**
	 * Method for execute command in command line and return result.
	 * 
	 * @param command to execute
	 * 
	 * @return result of command
	 */
	private String runSystemCommand(String command) {

		String result = "";
		
	    try {
	    	
	        Process p = Runtime.getRuntime().exec(command);
	        
	        BufferedReader inputStream = new BufferedReader(
	                new InputStreamReader(p.getInputStream()));

	        String sCurrentLine = inputStream.readLine();
	        
	        // reading output stream of the command
	        while (sCurrentLine != null) {

	        	result = result 
	        			+ sCurrentLine 
	        			+ "<br>";;
	        	
	        	sCurrentLine = inputStream.readLine();
	        	
	        }
	        
	    } catch (Exception e) {
	    	
	    	logger.error(ExceptionUtils.getStackTrace(e));
	        
	    } 
	    
	    return result;
	    
	}
	
	@Override
	public String checkTelnet(String host, 
							  Integer port) {

		String result = null;
		
		if (telnetConnect(host, port)) {
			
			result = "Telnet connection available!";
			
		} else {
			
			result = "Telnet connection not available!";
			
		}
		
		return result;
		
	}
	
	/**
	 * Method for checking if telnet connection is availabe.
	 * 
	 * @param host host to check
	 * @param port port of host to check
	 * 
	 * @return true if telnet connection can be set
	 */
	private boolean telnetConnect(String host, 
								 int port) {
		
		boolean result = false;
		
		TelnetClient telnet = new TelnetClient();
				
		try {
            
			telnet.connect(host, port);
			
			result = true;
			
        } catch (IOException e) {
        	
        	result = false;
        	
        	logger.error(ExceptionUtils.getStackTrace(e));
        	
        } finally {
        	
        	try {
        		
				telnet.disconnect();
				
			} catch (IOException e) {

				logger.error(ExceptionUtils.getStackTrace(e));
				
			}
        	
        }
		
		return result;

	}
	
}
