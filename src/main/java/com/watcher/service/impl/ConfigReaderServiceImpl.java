package com.watcher.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.watcher.highlighter.ConfigSyntaxHighlighter;
import com.watcher.model.ConfigFile;
import com.watcher.service.ConfigReaderService;

/**
 * Service implementation for configuration reader of project in Tomcat.
 * 
 * @author Aendy
 */
public class ConfigReaderServiceImpl implements ConfigReaderService {
	
	/** Logger. */
	private final Logger logger = Logger.getLogger(ConfigReaderServiceImpl.class);
	
	@Override
	public List<String> getListOfProjects() {
		
		File root = new File(rootProjectPath);
		
		if (!root.isDirectory()) {
			
			//throw exception ili nesto
		
			return null;
			
		}
		
		final List<String> webappsIgnoreFolders = new ArrayList<String>(
													Arrays.asList("docs",
															  	  "examples",
															  	  "host-manager",
															  	  "manager",
															  	  "ROOT"));
		
		List<String> result = new ArrayList<String>();
		
		File[] listOfAllProjects = root.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				
				if (webappsIgnoreFolders.contains(pathname.getName())) {
					
					return false;
					
				}
				
				if (pathname.getAbsolutePath().endsWith(projectExtension)) {
					
					return false;
					
				}
				
				return true;
				
			}
			
		});

		for (File file : listOfAllProjects) {
			
			result.add(file.getName());
			
		}
		
		return result;
		
	}
	
	@Override
	public List<String> getListOfProjectsPropertieFiles(String selectedProject) {
		
		File root = new File(getFullProjectPath(selectedProject));
		
		if (!root.isDirectory()) {
			
			//throw exception ili nesto
		
			return null;
			
		}
		
		List<String> result = new ArrayList<String>();

		File[] listOfAllFiles = root.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				
				return pathname.getAbsolutePath()
						.endsWith(configurationExtension);
				
			}
			
		});
		
		for (File file : listOfAllFiles) {
			
			result.add(file.getName());
			
		}
		
		return result;
		
	}
	
	/**
	 * Method for returning path to selected project.
	 * 
	 * @param selectedProject name of selected project
	 * 
	 * @return full path to selected project
	 */
	private String getFullProjectPath(String selectedProject) {
		
		String result = rootProjectPath.endsWith("\\") 
						? rootProjectPath 
						: rootProjectPath.concat("\\");
		
		return result
					.concat(selectedProject)
					.concat("\\")
					.concat(projectConfigurationPath);
		
	}
	
	@Override
	public ConfigFile readFile(ConfigFile configFile, String selectedProject) {
	
		if (configFile == null
				||
				configFile.getFilename() == null) {
			
			return configFile;
			
		}
		
		fillConfigFilePath(configFile, selectedProject);
		
		configFile.setContent(readFile(configFile.getFilepath()));
		
		return configFile;
		
	}
	
	/**
	 * Method for constructing full file path from file name and root log file path.
	 * 
	 * @param logFile log file in which we are doing mapping
	 * @param selectedProject selected project
	 */
	private void fillConfigFilePath(ConfigFile configFile, String selectedProject) {
	
		String result = getFullProjectPath(selectedProject);
		
		result = result.concat(result.endsWith("\\") ? "" : "\\");
		
		configFile.setFilepath(result.concat(configFile.getFilename()));

	}
	
	/**
	 * Read file from given path.
	 * 
	 * @param filePath path to file
	 * 
	 * @return highlighted content of file
	 */
	private String readFile(String filePath) {
		
		String result = "";
		BufferedReader br = null;
		FileReader fr = null;
	
		try {

			fr = new FileReader(filePath);
			br = new BufferedReader(fr);

			String sCurrentLine = br.readLine(); 

			while (sCurrentLine != null) {
				
				result = result 
					   + configSyntaxHighlighter.highlight(sCurrentLine)
					   + "<br>";
				
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
		
		return result;
		
	}
	
	/** Path to webapps folder on Tomcat. */
	private String rootProjectPath;
	
	/** Default path to configuration folder inside project. */
	private String projectConfigurationPath;
	
	/** Extension of configuration files. */
	private String configurationExtension;

	/** Extension of project WAR file. */
	private String projectExtension;
	
	/** Bean {@link ConfigSyntaxHighlighter}. */
	private ConfigSyntaxHighlighter configSyntaxHighlighter;
	/**
	 * 
	 * @param rootProjectPath IOC
	 */
	public void setRootProjectPath(String rootProjectPath) {
		this.rootProjectPath = rootProjectPath;
	}

	/**
	 * 
	 * @param projectConfigurationPath IOC
	 */
	public void setProjectConfigurationPath(String projectConfigurationPath) {
		this.projectConfigurationPath = projectConfigurationPath;
	}

	/**
	 * 
	 * @param configurationExtension IOC
	 */
	public void setConfigurationExtension(String configurationExtension) {
		this.configurationExtension = configurationExtension;
	}

	/**
	 * 
	 * @param projectExtension IOC
	 */
	public void setProjectExtension(String projectExtension) {
		this.projectExtension = projectExtension;
	}

	/**
	 * 
	 * @param configSyntaxHighlighter IOC
	 */
	public void setConfigSyntaxHighlighter(ConfigSyntaxHighlighter configSyntaxHighlighter) {
		this.configSyntaxHighlighter = configSyntaxHighlighter;
	}
	
}
