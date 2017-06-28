package com.watcher.service;

import java.util.List;

import com.watcher.model.LogFile;

/**
 * Interface for log service which reads log files and converts it to timeline.
 * 
 * @author Aendy
 *
 */
public interface LogTimelineService {

	/**
	 * Method for loading list of log files in Tomcat logs folder.
	 * 
	 * @return list of log files in logs folder
	 */
	public List<String> getListOfLogFiles();
	
	/**
	 * Method for reading file specified in parameters from {@link LogFile} object.
	 * 
	 * @param logFile object with parameters for reading file.
	 * 
	 * @return logFile object filled with data from file
	 */
	public LogFile timelineLogFile(LogFile logFile); 
	
	/**
	 * Method for initializing log file.
	 * 
	 * @return new instance of {@link LogFile}
	 */
	public LogFile initializeLogFile();
	
}
