package com.watcher.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.primefaces.model.timeline.TimelineModel;

import com.watcher.log.timeline.Log4jModelServiceImpl;
import com.watcher.model.LogFile;
import com.watcher.model.LogFileModel;
import com.watcher.service.LogTimelineService;

/**
 * Service implementation for reading all log files in Tomcat and showing it as timeline.
 * 
 * @author Aendy
 */
public class LogTimelineServiceImpl extends LogAbstractServiceImpl implements LogTimelineService {

    @Override
    public LogFile timelineLogFile(LogFile logFile) {

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

        LogFileModel logFileModel = new LogFileModel(new TimelineModel(), false);

        BufferedReader br = null;
        FileReader fr = null;

        try {

            fr = new FileReader(logFile.getFilepath());
            br = new BufferedReader(fr);

            String sCurrentLine = br.readLine(); 

            while (sCurrentLine != null) {

                log4jModelServiceImpl.processEventsFromLogLine(logFileModel, 
                                                               logFile, 
                                                               sCurrentLine);

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

        logFileModel.addCurrentEvent();

        logFile.setModel(logFileModel.getTimelineModel());

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

        LogFileModel logFileModel = new LogFileModel(new TimelineModel(), true);

        int counter = 0; 

        File file = new File(logFile.getFilepath());

        ReversedLinesFileReader reversedReader = null;

        try {

            reversedReader = new ReversedLinesFileReader(file);

            String sCurrentLine = reversedReader.readLine(); 

            while(sCurrentLine != null  
                    && 
                    counter < logFile.getNumberOfLastRows()) {

                log4jModelServiceImpl.processEventsFromLogLine(logFileModel, 
                                                               logFile, 
                                                               sCurrentLine);

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

        logFile.setModel(logFileModel.getTimelineModel());

    }

    /** Bean {@link Log4jModelServiceImpl}. */
    private Log4jModelServiceImpl log4jModelServiceImpl;

    /**
     * 
     * @param log4jModelServiceImpl IOC
     */
    public void setLog4jModelServiceImpl(Log4jModelServiceImpl log4jModelServiceImpl) {
        this.log4jModelServiceImpl = log4jModelServiceImpl;
    }

}
