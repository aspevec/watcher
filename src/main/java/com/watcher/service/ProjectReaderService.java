package com.watcher.service;

import java.util.List;



/**
 * Interface for service which reads projects on Tomcat return paths to same projects.
 * 
 * @author Aendy
 *
 */
public interface ProjectReaderService {

    /**
     * Method for loading list of projects available on Tomcat.
     * 
     * @return list of projects on Tomcat
     */
    public List<String> getListOfProjects();

    /**
     * Method for returning path to selected project.
     * 
     * @param selectedProject name of selected project
     * 
     * @return full path to selected project
     */
    public String getFullProjectPath(String selectedProject);

}
