package com.watcher.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.watcher.model.ConfigFile;
import com.watcher.service.ConfigReaderService;
import com.watcher.service.ReachableService;
import com.watcher.service.ProjectReaderService;;

@ManagedBean(name = "configController")
@ViewScoped
public class ConfigController extends AbstractController implements Serializable {

    /** Serial version UID. */
    private static final long serialVersionUID = 1L;

    /** Configuration file. */
    private ConfigFile configFile = new ConfigFile();

    /** Selected project. */
    private String project;

    /** List of available projects on Tomcat. */
    private List<String> projects = new ArrayList<String>();

    /** List of available configuration files on selected project. */
    private List<String> configFiles = new ArrayList<String>();

    /** Host for which we can check availability. */
    private String host;

    /** Specific port of host. */
    private Integer port;

    /** Result of availability check. */
    private String result;

    @PostConstruct
    public void init() {

        listProjects();

    }

    /**
     * Method for listing project 
     */
    public void listProjects() {

        setProjects(projectReaderService.getListOfProjects());

    }

    /**
     * When project changes refresh list of available config files.
     */
    public void onProjectChange() {

        setConfigFiles(configReaderService.getListOfProjectsPropertieFiles(getProject()));

    }

    /**
     * Method for reading configuration file.
     */
    public void readConfigFile() {

        setConfigFile(configReaderService.readFile(getConfigFile(), 
                                                   getProject()));

    }

    /**
     * Method for checking ping of host.
     */
    public void checkPing() {

        setResult(reachableService.checkPing(getHost()));

    }

    /**
     * Method for checking telnet connectivity of host.
     */
    public void checkTelnet() {

        setResult(reachableService.checkTelnet(getHost(), 
                                               getPort()));

    }

    //getters & setters

    public ConfigFile getConfigFile() {
        return configFile;
    }

    public void setConfigFile(ConfigFile configFile) {
        this.configFile = configFile;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }

    public List<String> getConfigFiles() {
        return configFiles;
    }

    public void setConfigFiles(List<String> configFiles) {
        this.configFiles = configFiles;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    //Services

    @ManagedProperty(value = "#{projectReaderService}")
    private ProjectReaderService projectReaderService;

    @ManagedProperty(value = "#{configReaderService}")
    private ConfigReaderService configReaderService;

    @ManagedProperty(value = "#{reachableService}")
    private ReachableService reachableService;

    /**
     * 
     * @param projectReaderService IOC
     */
    public void setProjectReaderService(ProjectReaderService projectReaderService) {
        this.projectReaderService = projectReaderService;
    }

    /**
     * 
     * @param configReaderService IOC
     */
    public void setConfigReaderService(ConfigReaderService configReaderService) {
        this.configReaderService = configReaderService;
    }

    /**
     * 
     * @param reachableService IOC
     */
    public void setReachableService(ReachableService reachableService) {
        this.reachableService = reachableService;
    }

}
