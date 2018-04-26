package com.watcher.model;

import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;

/**
 * Object used when reading one log file.
 * 
 * Contains timelineModel with all events from log file.
 * 
 * It has all methods to fill and modify its events and 
 * 
 * add them to model in appropriate moment.
 * 
 * @author Aendy
 *
 */
public class LogFileModel {

    /** Timeline model. */
    public TimelineModel timelineModel;

    /** If we are reading file in reversed or not. */
    private boolean isReversed;

    /** If error or other tag have stacktrace we need to add multiple lines to single event. */
    private StringBuilder block;

    /** Timeline event. */
    private TimelineEvent currentEvent;

    public LogFileModel(TimelineModel timelineModel,
            boolean isReversed) {

        this.timelineModel 	= timelineModel;
        this.isReversed 	= isReversed;
        this.block 			= new StringBuilder();

    }

    public TimelineModel getTimelineModel() {
        return timelineModel;
    }

    public void setTimelineModel(TimelineModel timelineModel) {
        this.timelineModel = timelineModel;
    }

    public boolean isReversed() {
        return isReversed;
    }

    public void setReversed(boolean isReversed) {
        this.isReversed = isReversed;
    }
    public void setBlock(StringBuilder block) {
        this.block = block;
    }
    public StringBuilder getBlock() {
        return block;
    }
    public void resetBlock() {
        this.block = new StringBuilder();
    }
    public void appendToBlock(String input) {
        getBlock().append(input).append("<br />");
    }
    public void appendToBlockReversed(String input) {

        input = "<br />".concat(input);
        getBlock().insert(0, input, 0, input.length());

    }
    public TimelineEvent getCurrentEvent() {
        return currentEvent;
    }
    public void setCurrentEvent(TimelineEvent currentEvent) {
        this.currentEvent = currentEvent;
    }

    /**
     * Method for adding current event to model.
     */
    public void addCurrentEvent() {

        if (getCurrentEvent() != null) {

            addContentToLogFileEvent(getBlock().toString());

            addTimelineEvent(getCurrentEvent());

            resetBlock();

            setCurrentEvent(null);

        }

    }

    /**
     * Method for appending input string into content of Event object.
     * 
     * @param input input string
     */
    private void addContentToLogFileEvent(String input) {

        LogFileEvent event = (LogFileEvent) currentEvent.getData();

        event.appendToContent(input);

    }

    /**
     * Method for adding timeline event to timeline model.
     * 
     * @param timelineEvent event to add
     */
    public void addTimelineEvent(TimelineEvent timelineEvent) {

        if (timelineModel == null) {

            timelineModel = new TimelineModel();

        }

        timelineModel.add(timelineEvent);

    }

}
