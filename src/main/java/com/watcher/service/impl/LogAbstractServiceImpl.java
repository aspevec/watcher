package com.watcher.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.primefaces.model.timeline.TimelineModel;

import com.watcher.model.LogFile;
import com.watcher.service.LogAbstractService;

/**
 * Abstract of log reading service - contains shared methods.
 * 
 * @author Aendy
 */
public abstract class LogAbstractServiceImpl implements LogAbstractService {

	/** Logger. */
	protected final Logger logger = Logger.getLogger(LogAbstractServiceImpl.class);

	@Override
	public List<String> getListOfLogFiles() {
	
		File root = new File(rootLogPath);
		
		if (!root.isDirectory()) {
			
			//throw exception ili nesto TODO
		
			return null;
			
		}
		
		List<String> result = new ArrayList<String>();
		
		for (File file : root.listFiles()) {
			
			result.add(file.getName());
			
		}
		
		return result;
		
	}
	
	@Override
	public LogFile initializeLogFile() {
	
		LogFile logFile = new LogFile();
		
		logFile.setModel				(new TimelineModel());
		logFile.setLogDateFormat		(defaultDateFormat);
		logFile.setStartIndexOfDateInLog(0);
		
		return logFile;
		
	}
	
	/**
	 * Method for constructing full file path from file name and root log file path.
	 * 
	 * @param logFile log file in which we are doing mapping
	 */
	protected void fillLogFilePath(LogFile logFile) {
	
		String result = rootLogPath.endsWith("\\")
						? rootLogPath
						: rootLogPath.concat("\\");
	
		logFile.setFilepath(result.concat(logFile.getFilename()));

	}
	
	/** Path to logs folder on Tomcat. */
	private String rootLogPath;
	
	/** Default simple date format to use when reading date from log4j. */
	private String defaultDateFormat;
	
	/**
	 * 
	 * @param rootLogPath IOC
	 */
	public void setRootLogPath(String rootLogPath) {
		this.rootLogPath = rootLogPath;
	}
	
	/**
	 * 
	 * @param defaultDateFormat IOC
	 */
	public void setDefaultDateFormat(String defaultDateFormat) {
		this.defaultDateFormat = defaultDateFormat;
	}
	
}
