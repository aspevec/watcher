package com.watcher.service;

import com.watcher.model.LogFile;

/**
 * Interface for log service which reads log files.
 * 
 * @author Aendy
 *
 */
public interface LogReaderService extends LogAbstractService {

	/**
	 * Method for reading file specified in parameters from {@link LogFile} object.
	 * 
	 * @param logFile object with parameters for reading file.
	 * 
	 * @return logFile object filled with data from file
	 */
	public LogFile readFile(LogFile logFile); 
	
}
