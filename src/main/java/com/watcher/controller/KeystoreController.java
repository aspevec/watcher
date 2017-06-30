package com.watcher.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.watcher.model.KeyStoreFile;
import com.watcher.service.KeystoreReaderService;
import com.watcher.service.ProjectReaderService;

@ManagedBean(name = "keystoreController")
@ViewScoped
public class KeystoreController extends AbstractController implements Serializable {

	/** Serial version UID. */
	private static final long serialVersionUID = 1L;

	/** KeyStore file. */
	private KeyStoreFile keyStoreFile = new KeyStoreFile();

	/** Selected project. */
	private String project;

	/** List of available projects on Tomcat. */
	private List<String> projects = new ArrayList<String>();

	/** List of available keystore files on selected project. */
	private List<String> keyStoreFiles = new ArrayList<String>();

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
	 * When project changes refresh list of available keystore files.
	 */
	public void onProjectChange() {

		setKeyStoreFiles(keystoreReaderService
				.getListOfProjectsKeystoreFiles(getProject()));

	}

	/**
	 * Method for reading keystore file.
	 */
	public void readKeystoreFile() {
		
		setKeyStoreFile(keystoreReaderService.readFile(getKeyStoreFile(), 
													   getProject()));
		
	}
	
	/**
	 * Method for reading JVM keystore file.
	 */
	public void readKeystoreJVM() {
		
		setKeyStoreFile(keystoreReaderService.readJVMKeyStore());
		
	}
	
	/**
	 * Method for set keystore to default JVM.
	 */
	public void setKeystoreJVM() {
		
		keystoreReaderService.setKeyStore(getKeyStoreFile(), 
										  getProject());
		
	}
	
	//getters & setters
	
	public KeyStoreFile getKeyStoreFile() {
		return keyStoreFile;
	}

	public void setKeyStoreFile(KeyStoreFile keyStoreFile) {
		this.keyStoreFile = keyStoreFile;
	}

	public List<String> getKeyStoreFiles() {
		return keyStoreFiles;
	}

	public void setKeyStoreFiles(List<String> keyStoreFiles) {
		this.keyStoreFiles = keyStoreFiles;
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
	
	//Services
	
	@ManagedProperty(value = "#{projectReaderService}")
	private ProjectReaderService projectReaderService;
	
	@ManagedProperty(value = "#{keystoreReaderService}")
	private KeystoreReaderService keystoreReaderService;

	/**
	 * 
	 * @param projectReaderService IOC
	 */
	public void setProjectReaderService(ProjectReaderService projectReaderService) {
		this.projectReaderService = projectReaderService;
	}
	
	/**
	 * 
	 * @param keystoreReaderService IOC
	 */
	public void setKeystoreReaderService(KeystoreReaderService keystoreReaderService) {
		this.keystoreReaderService = keystoreReaderService;
	}

}
