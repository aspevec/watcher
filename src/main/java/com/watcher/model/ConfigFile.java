package com.watcher.model;

import java.io.Serializable;

/**
 * Object representing readed file.
 * 
 * It has content of readed file with addition of reading parameters.
 * 
 * @author Aendy
 */
public class ConfigFile implements Serializable {
	
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 5167534092423115861L;
	
	/** Content of log file. */
	private String content;
	/** If we are not reading whole file, we need to define number of last rows to read. */
	private Integer numberOfLastRows;
	/** File name. */
	private String filename;
	/** Path to file. */
	private String filepath;
	
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
	@Override
	public String toString() {
		
		return "ConfigFile [content=" + content 
					   + ", numberOfLastRows=" + numberOfLastRows 
					   + ", filename=" + filename 
					   + ", filepath=" + filepath + "]";
		
	}

}
