package com.watcher.log.timeline;

import java.util.List;

import org.primefaces.model.timeline.TimelineEvent;

import com.watcher.highlighter.Log4jSyntaxHighlighter;
import com.watcher.model.LogFile;
import com.watcher.model.LogFileEvent;
import com.watcher.model.LogFileModel;
import com.watcher.util.WatcherDateUtil;

/**
 * Service used for processing lines for timeline.
 * 
 * It gets line after line and creates events in logFileModel.
 * 
 * One LOG event of severity (ERROR, WARN, DEBUG etc) and it's
 * stacktrace should be one {@link LogFileEvent} and after reading whole file 
 * we will get one {@link LogFileModel} with {@link List} of {@link LogFileEvent}.
 * 
 * @author Aendy
 *
 */
public class Log4jModelServiceImpl {

	/**
	 * Method that parses line of a log file into timeline events.
	 * 
	 * @param logFileModel model that contains all events from single log file
	 * @param logFile log file with all parametars of file
	 * @param line current line of file
	 */
	public void processEventsFromLogLine(LogFileModel logFileModel,
				   						 LogFile logFile,	
				   						 String line) {
	
		if (logFileModel == null
				||
				logFileModel.getTimelineModel() == null) {
			
			return;
			
		}
		
		if (logFileModel.isReversed()) {
			
			procesLineReversed(logFileModel, logFile, line);
			
		} else {
			
			procesLineNormal(logFileModel, logFile, line);
			
		}
		
	}
	
	/**
	 * Processing of file line if we are reading file from top-down.
	 * 
	 * @param logFileModel model that contains all events from single log file
	 * @param logFile log file with all parametars of file
	 * @param line current line of file
	 */
	private void procesLineNormal(LogFileModel logFileModel, LogFile logFile, String line) {
		
		if (line.startsWith("\t")
				||
				!containsTag(line)){
			
			logFileModel.appendToBlock(line);
			
			return;
			
		}
		
		logFileModel.addCurrentEvent();

		createEventFromLine(logFileModel, logFile, line);
	}
	
	/**
	 * Processing of file line if we are reading file reversed, bottom-up.
	 * 
	 * @param logFileModel model that contains all events from single log file
	 * @param logFile log file with all parametars of file
	 * @param line current line of file
	 */
	private void procesLineReversed(LogFileModel logFileModel, LogFile logFile, String line) {
		
		if (line.startsWith("\t")
				||
				!containsTag(line)){
			
			logFileModel.appendToBlockReversed(line);
			
			return;
			
		}

		createEventFromLine(logFileModel, logFile, line);
		
		logFileModel.addCurrentEvent();
		
	}
	
	/**
	 * Method that checks if line contains one of the tags.
	 * 
	 * @param line line to check
	 * 
	 * @return return true if line contains one of the log4j tags
	 */
	private boolean containsTag(String line) {
		
		return line.contains(Log4jSyntaxHighlighter.LOG4J_INFO_TAG)
				||
				line.contains(Log4jSyntaxHighlighter.LOG4J_DEBUG_TAG)
				||
				line.contains(Log4jSyntaxHighlighter.LOG4J_WARNING_TAG)
				||
				line.contains(Log4jSyntaxHighlighter.LOG4J_ERROR_TAG)
				||
				line.contains(Log4jSyntaxHighlighter.LOG4J_SEVERE_TAG);
		
	}
	
	/**
	 * Method that parses line and finds events if there are any.
	 * 
	 * @param logFileModel model that contains all events from single log file
	 * @param logFile log file with all parametars of file
	 * @param line current line of file
	 */
	private void createEventFromLine(LogFileModel logFileModel, LogFile logFile, String line) {
		
		if (line.contains(Log4jSyntaxHighlighter.LOG4J_DEBUG_TAG)) {
			
			logFileModel.setCurrentEvent(
				new TimelineEvent(new LogFileEvent(Log4jSyntaxHighlighter.LOG4J_DEBUG_TAG,
						   						   line), 
								  WatcherDateUtil.string2Date(line.substring(logFile.getStartIndexOfDateInLog(), 
										  									 logFile.getEndIndexOfDateInLog()),
		 								  				     logFile.getLogDateFormat()),
		 						  false, null, "timeline-event-debug"));
			
		} else if (line.contains(Log4jSyntaxHighlighter.LOG4J_WARNING_TAG)) {
		
			logFileModel.setCurrentEvent(
				new TimelineEvent(new LogFileEvent(Log4jSyntaxHighlighter.LOG4J_WARNING_TAG,
												   line),
								  WatcherDateUtil.string2Date(line.substring(logFile.getStartIndexOfDateInLog(), 
										  									 logFile.getEndIndexOfDateInLog()),
										  					  logFile.getLogDateFormat()),
								  false, null, "timeline-event-warning"));
		
		} else if (line.contains(Log4jSyntaxHighlighter.LOG4J_ERROR_TAG)) {
		
			logFileModel.setCurrentEvent(
				new TimelineEvent(new LogFileEvent(Log4jSyntaxHighlighter.LOG4J_ERROR_TAG,
												   line),
								  WatcherDateUtil.string2Date(line.substring(logFile.getStartIndexOfDateInLog(), 
										  									 logFile.getEndIndexOfDateInLog()),
										  					  logFile.getLogDateFormat()),
								  false, null, "timeline-event-error"));
		
		} else if (line.contains(Log4jSyntaxHighlighter.LOG4J_SEVERE_TAG)) {
		
			logFileModel.setCurrentEvent(
				new TimelineEvent(new LogFileEvent(Log4jSyntaxHighlighter.LOG4J_SEVERE_TAG,
												   line),
								  WatcherDateUtil.string2Date(line.substring(logFile.getStartIndexOfDateInLog(), 
										  									 logFile.getEndIndexOfDateInLog()),
										  					  logFile.getLogDateFormat()),
								  false, null, "timeline-event-severe"));
		
		} else if (line.contains(Log4jSyntaxHighlighter.LOG4J_INFO_TAG)) {

			logFileModel.setCurrentEvent(null);
			
		} else {
			
			return;
			
		}

	}
	
}
