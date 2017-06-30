package com.watcher.service.impl;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.watcher.service.ProjectReaderService;

/**
 * Class for reading projects from Tomcat server.
 * 
 * @author Aendy
 */
public class ProjectReaderServiceImpl implements ProjectReaderService {

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
	
	/**
	 * Method for returning path to selected project.
	 * 
	 * @param selectedProject name of selected project
	 * 
	 * @return full path to selected project
	 */
	public String getFullProjectPath(String selectedProject) {
		
		String result = rootProjectPath.endsWith("\\") 
						? rootProjectPath 
						: rootProjectPath.concat("\\");
		
		return result.concat(selectedProject)
					 .concat("\\");
		
	}
	
	/** Path to webapps folder on Tomcat. */
	private String rootProjectPath;
	
	/** Extension of project WAR file. */
	private String projectExtension;

	/**
	 * 
	 * @param rootProjectPath IOC
	 */
	public void setRootProjectPath(String rootProjectPath) {
		this.rootProjectPath = rootProjectPath;
	}
	
	/**
	 * 
	 * @param projectExtension IOC
	 */
	public void setProjectExtension(String projectExtension) {
		this.projectExtension = projectExtension;
	}
	
}
