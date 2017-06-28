package com.watcher.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.primefaces.model.timeline.TimelineModel;

import com.watcher.highlighter.Log4jSyntaxHighlighter;
import com.watcher.model.LogFile;
import com.watcher.service.LogReaderService;

public class LogReaderServiceImpl implements LogReaderService {

	/** Logger. */
	private final Logger logger = Logger.getLogger(LogReaderServiceImpl.class);

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
	
	@Override
	public LogFile readFile(LogFile logFile) {
	
		if (logFile == null
				||
				logFile.getFilename() == null) {
			
			return logFile;
			
		}
		
		fillLogFilePath(logFile);
		
		if (logFile.getNumberOfLastRows() == null
				||
				logFile.getNumberOfLastRows() == 0) {
			
			readWholeFile(logFile);
			
		} else {
			
			readLastLinesOfTheFile(logFile);
			
		}
		
		return logFile;
	}
	
	/**
	 * Method for constructing full file path from file name and root log file path.
	 * 
	 * @param logFile log file in which we are doing mapping
	 */
	private void fillLogFilePath(LogFile logFile) {
	
		String result = rootLogPath.endsWith("\\")
						? rootLogPath
						: rootLogPath.concat("\\");
	
		logFile.setFilepath(result.concat(logFile.getFilename()));

	}
	
	/**
	 * Method for reading whole file from given path.
	 * 
	 * @param filePath path to file
	 * 
	 * @return content of file
	 */
	private void readWholeFile(LogFile logFile) {
		
		if (logFile == null) {
			
			return;
			
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader br 			= null;
		FileReader fr 				= null;
	
		try {

			fr = new FileReader(logFile.getFilepath());
			br = new BufferedReader(fr);

			String sCurrentLine = br.readLine(); 

			while (sCurrentLine != null) {
				
				stringBuilder.append(log4jSyntaxHighlighter.highlight(sCurrentLine));
				
				sCurrentLine = br.readLine();
				
			}

		} catch (IOException e) {

			logger.error(ExceptionUtils.getStackTrace(e));
			
		} finally {

			try {

				if (br != null)
					
					br.close();

				if (fr != null)
					
					fr.close();

			} catch (IOException e) {

				logger.error(ExceptionUtils.getStackTrace(e));

			}

		}
		
		logFile.setContent	(stringBuilder.toString());
		
	}
	
	/**
	 * Read last X lines of file.
	 * 
	 * @param filePath path of a file
	 * @param numberOfLastLines number of last rows to read
	 * 
	 * @return string of last x rows of file
	 */
	private void readLastLinesOfTheFile(LogFile logFile) {
			
		if (logFile == null) {
			
			return;
			
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		int counter 				= 0; 
		
		File file = new File(logFile.getFilepath());
		
		ReversedLinesFileReader reversedReader = null;
		
		try {
			
			reversedReader = new ReversedLinesFileReader(file);
			
			String sCurrentLine = reversedReader.readLine(); 
			
			while(sCurrentLine != null  
						&& 
						counter < logFile.getNumberOfLastRows()) {

				appendOnStartOfStringBuilder(stringBuilder, 
											 log4jSyntaxHighlighter.formatWithoutColor(sCurrentLine));
				
				sCurrentLine = reversedReader.readLine();
						
			    counter++;
			    
			}
			
		} catch (IOException e) {

			logger.error(ExceptionUtils.getStackTrace(e));
			
		} finally {
			
			if (reversedReader != null) {
				
				try {
					
					reversedReader.close();
					
				} catch (IOException e) {

					logger.error(ExceptionUtils.getStackTrace(e));
					
				}
				
			}
		}
		
		logFile.setContent	(stringBuilder.toString());
		
	}
	
	/**
	 * Method which puts string on a beginning of a stringbuilder.
	 * 
	 * @param sb string builder
	 * @param input string to insert
	 */
	private void appendOnStartOfStringBuilder(StringBuilder sb, 
			  								  String input) {

		sb.insert(0, input, 0, input.length());
	
	}

	/** Path to logs folder on Tomcat. */
	private String rootLogPath;
	
	/** Bean {@link Log4jSyntaxHighlighter}. */
	private Log4jSyntaxHighlighter log4jSyntaxHighlighter;
	
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
	 * @param log4jSyntaxHighlighter IOC
	 */
	public void setLog4jSyntaxHighlighter(Log4jSyntaxHighlighter log4jSyntaxHighlighter) {
		this.log4jSyntaxHighlighter = log4jSyntaxHighlighter;
	}

	/**
	 * 
	 * @param defaultDateFormat IOC
	 */
	public void setDefaultDateFormat(String defaultDateFormat) {
		this.defaultDateFormat = defaultDateFormat;
	}
	
}
