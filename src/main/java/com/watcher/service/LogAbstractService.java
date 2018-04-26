package com.watcher.service;

import java.util.List;

import com.watcher.model.LogFile;

/**
 * Interface for abstract log service - common interface.
 * 
 * @author Aendy
 */
public interface LogAbstractService {

    /**
     * Method for loading list of log files in Tomcat logs folder.
     * 
     * @return list of log files in logs folder
     */
    public List<String> getListOfLogFiles();

    /**
     * Method for initializing log file.
     * 
     * @return new instance of {@link LogFile}
     */
    public LogFile initializeLogFile();

}
