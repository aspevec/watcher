package com.watcher.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.watcher.highlighter.Log4jSyntaxHighlighter;
import com.watcher.model.LogFile;
import com.watcher.service.LogReaderService;

/**
 * Service implementation for reading all log files in Tomcat.
 * 
 * @author Aendy
 */
public class LogReaderServiceImpl extends LogAbstractServiceImpl implements LogReaderService {

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
        BufferedReader br = null;
        FileReader fr = null;

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
        int counter = 0; 

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

    /** Bean {@link Log4jSyntaxHighlighter}. */
    private Log4jSyntaxHighlighter log4jSyntaxHighlighter;

    /**
     * 
     * @param log4jSyntaxHighlighter IOC
     */
    public void setLog4jSyntaxHighlighter(Log4jSyntaxHighlighter log4jSyntaxHighlighter) {
        this.log4jSyntaxHighlighter = log4jSyntaxHighlighter;
    }

}
