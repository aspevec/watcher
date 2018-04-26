package com.watcher.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.timeline.TimelineEvent;

import com.watcher.model.LogFile;
import com.watcher.model.LogFileEvent;
import com.watcher.service.LogReaderService;
import com.watcher.service.LogTimelineService;

@ManagedBean(name="logsController")
@ViewScoped
public class LogsController extends AbstractController implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Selected log file from list. */
    private LogFile logFile;

    /** List of log files on Tomcat. */
    private List<String> logFiles = new ArrayList<String>();

    /** Header name for dialog to show log event details. */
    public String timelineHeader;

    /** Header name for dialog to show log event details. */
    public String timelineContent;

    @PostConstruct
    public void init() {

        setLogFile(logReaderService.initializeLogFile());

        listLogFiles();

    }

    /**
     * Read list of log files on server. 
     */
    public void listLogFiles() {

        setLogFiles(logReaderService.getListOfLogFiles());

    }

    /**
     * Read specific log file and show content.
     */
    public void readLogFile() {
        logReaderService.readFile(getLogFile());
    }

    /**
     * Read specific log file and show content of log in timeline.
     */
    public void timelineLogFile() {
        logTimelineService.timelineLogFile(getLogFile());
    }

    /**
     * Action after clicking event from timeline.
     * 
     * @param e event from clicking on event from timeline 
     */
    public void onSelect(TimelineSelectEvent e) {  

        TimelineEvent timelineEvent = e.getTimelineEvent();  

        LogFileEvent data = (LogFileEvent) timelineEvent.getData();

        setTimelineHeader(data.getName());
        setTimelineContent(data.getFullError());

    }

    //getters and setters

    public LogFile getLogFile() {
        return logFile;
    }

    public void setLogFile(LogFile logFile) {
        this.logFile = logFile;
    }

    public List<String> getLogFiles() {
        return logFiles;
    }

    public void setLogFiles(List<String> logFiles) {
        this.logFiles = logFiles;
    }

    /**
     * Set value for dialog after clicking log event from timeline.
     * 
     * @param timelineHeader timeline header
     */
    public void setTimelineHeader(String timelineHeader) {
        this.timelineHeader = timelineHeader;
    }

    /**
     * Set content of dialog element to show full log event stacktrace from event in timeline.
     * 
     * @param timelineContent content of log event (stacktrace)
     */
    public void setTimelineContent(String timelineContent) {
        this.timelineContent = timelineContent;
    }

    public String getTimelineHeader() {
        return timelineHeader;
    }

    public String getTimelineContent() {
        return timelineContent;
    }

    //Services

    @ManagedProperty(value = "#{logReaderService}")
    private LogReaderService logReaderService;

    @ManagedProperty(value = "#{logTimelineService}")
    private LogTimelineService logTimelineService;

    /**
     * @param logReaderService IOC
     */
    public void setLogReaderService(LogReaderService logReaderService) {
        this.logReaderService = logReaderService;
    }

    /**
     * 
     * @param logTimelineService IOC
     */
    public void setLogTimelineService(LogTimelineService logTimelineService) {
        this.logTimelineService = logTimelineService;
    }

}
