package com.watcher.model;

import java.io.Serializable;

import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;

/**
 * Object that is main member for {@link TimelineModel}.
 * 
 * From this object {@link TimelineEvent} is created.
 * 
 * @author Aendy
 *
 */
public class LogFileEvent implements Serializable {

    /**
     * Serial version UID
     */
    private static final long serialVersionUID = 2369646112770105083L;

    /**
     * Name of the event - severity
     */
    private String name;

    /**
     * Full stacktrace of the log event.
     */
    private String fullError;

    /**
     * Default constructor.
     */
    public LogFileEvent() {}

    /**
     * Field constructor.
     * 
     * @param name
     * @param fullError
     */
    public LogFileEvent(String name, String fullError) {

        this.name = name;
        this.fullError = fullError;

    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFullError() {
        return fullError;
    }
    public void setFullError(String fullError) {
        this.fullError = fullError;
    }

    /**
     * Append part of the stacktrace to the stacktrace of this event.
     * 
     * @param input row of stacktrace
     */
    public void appendToContent(String input) {

        if (fullError != null) {

            setFullError(getFullError() 
                    + "<br />"
                    + input);

        } else {

            setFullError(input);

        }

    }

    @Override
    public String toString() {
        return name;
    }

}
