package com.watcher.service;

import java.util.List;

import com.watcher.model.KeyStoreFile;


/**
 * Interface for keystore service which reads keystore files of all projects in Tomcat.
 * 
 * @author Aendy
 *
 */
public interface KeystoreReaderService {
	
	/**
	 * Method for loading list of projects available on Tomcat.
	 * 
	 * @return list of projects on Tomcat
	 */
	public List<String> getListOfProjects();
	
	/**
	 * Method for loading list of keystore files on selected project.
	 * 
	 * @param selectedProject project on which to read keystore
	 * 
	 * @return list of keystore files in selected project
	 */
	public List<String> getListOfProjectsKeystoreFiles(String selectedProject);
	
	/**
	 * Method for reading file specified in parameters from {@link KeyStoreFile} object.
	 * 
	 * @param keyStoreFile object with parameters for reading file.
	 * @param selectedProject selected project
	 * 
	 * @return keyStoreFile object filled certificates of this keystore
	 */
	public KeyStoreFile readFile(KeyStoreFile KeyStoreFile, String selectedProject);

	/**
	 * Method for setting selected keystore as default keystore in JVM.
	 * 
	 * @param keyStoreFile selected keystore
	 * 
	 * @param selectedProject project of selected keystore
	 */
	public void setKeyStore(KeyStoreFile keyStoreFile,
							String selectedProject);
	
	/**
	 * Method for reading JVM keystore and showing it's values.
	 * 
	 * @return keystore from JVM
	 */
	public KeyStoreFile readJVMKeyStore();

}