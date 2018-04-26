package com.watcher.service;

import java.util.List;

import com.watcher.model.ConfigFile;


/**
 * Interface for configuration service which reads configuration files of all projects in Tomcat.
 * 
 * @author Aendy
 *
 */
public interface ConfigReaderService {

    /**
     * Method for loading list of configuration files on selected project.
     * 
     * @param selectedProject project on which to read configuration
     * 
     * @return list of configuration files in selected project
     */
    public List<String> getListOfProjectsPropertieFiles(String selectedProject);

    /**
     * Method for reading file specified in parameters from {@link ConfigFile} object.
     * 
     * @param configFile object with parameters for reading file.
     * @param selectedProject selected project
     * 
     * @return configFile object filled with data from file
     */
    public ConfigFile readFile(ConfigFile configFile, String selectedProject);

}
