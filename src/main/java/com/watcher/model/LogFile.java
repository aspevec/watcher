package com.watcher.model;

import java.io.Serializable;

import org.primefaces.model.timeline.TimelineModel;

/**
 * Domain object representing log file and it's content.
 * 
 * @author Aendy
 */
public class LogFile implements Serializable {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -246638117285697632L;
	
	/** Content of log file. */
	private String content;
	/** If we are not reading whole file, we need to define number of last rows to read. */
	private Integer numberOfLastRows;
	/** File name. */
	private String filename;
	/** Path to file. */
	private String filepath;
	
	/** Format of date in selected format. */
	private String logDateFormat;
	
	/** Index where date starts in log file. */
	private Integer startIndexOfDateInLog;
	
	/** Timeline model. */
	private TimelineModel model;
	
	/** Log4j file block. */
	private StringBuilder block;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getNumberOfLastRows() {
		return numberOfLastRows;
	}
	public void setNumberOfLastRows(Integer numberOfLastRows) {
		this.numberOfLastRows = numberOfLastRows;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getLogDateFormat() {
		return logDateFormat;
	}
	public void setLogDateFormat(String logDateFormat) {
		this.logDateFormat = logDateFormat;
	}
	public Integer getStartIndexOfDateInLog() {
		return startIndexOfDateInLog;
	}
	public void setStartIndexOfDateInLog(Integer startIndexOfDateInLog) {
		this.startIndexOfDateInLog = startIndexOfDateInLog;
	}
	public Integer getEndIndexOfDateInLog() {
		
		return (getStartIndexOfDateInLog() != null ? getStartIndexOfDateInLog() : new Integer(0))
			 + (getLogDateFormat() != null ? getLogDateFormat().length() : 0);
		
	}
	public TimelineModel getModel() {
		return model;
	}
	public void setModel(TimelineModel model) {
		this.model = model;
	}
	public StringBuilder getBlock() {
		return block;
	}
	public void setBlock(StringBuilder block) {
		this.block = block;
	}
	@Override
	public String toString() {
		
		return "LogFile [content=" + content 
					+ ", numberOfLastRows=" + numberOfLastRows 
					+ ", filename=" + filename 
					+ ", filepath=" + filepath + "]";
		
	}
	
}
