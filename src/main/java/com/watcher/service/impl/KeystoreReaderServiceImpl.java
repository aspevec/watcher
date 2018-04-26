package com.watcher.service.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;

import com.watcher.model.CertificateFile;
import com.watcher.model.KeyStoreFile;
import com.watcher.service.KeystoreReaderService;
import com.watcher.service.ProjectReaderService;
import com.watcher.util.WatcherStringUtil;

/**
 * Service implementation for keystore reader of project in Tomcat.
 * 
 * @author Aendy
 */
public class KeystoreReaderServiceImpl implements KeystoreReaderService {

    /** Logger. */
    private final Logger logger = Logger.getLogger(KeystoreReaderServiceImpl.class);

    @Override
    public List<String> getListOfProjectsKeystoreFiles(String selectedProject) {

        File root = new File(getFullProjectPath(selectedProject));

        if (!root.isDirectory()) {

            //throw exception ili nesto

            return null;

        }

        List<String> result = new ArrayList<String>();

        File[] listOfAllFiles = root.listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {

                return pathname.getAbsolutePath().endsWith(keystoreExtension);

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

        String result = projectReaderService.getFullProjectPath(selectedProject);

        return result.concat(projectKeystorePath);

    }

    @Override
    public KeyStoreFile readFile(KeyStoreFile keyStoreFile,
                                 String selectedProject) {

        try {

            if (keyStoreFile == null
                    ||
                    keyStoreFile.getFilename() == null
                    ||
                    selectedProject == null) {

                return keyStoreFile;

            }

            keyStoreFile.setCertificates(null);

            fillKeystoreFilePath(keyStoreFile, selectedProject);

            readKeyStore(keyStoreFile);

            return keyStoreFile;

        } catch (Exception e) {

            logger.error(ExceptionUtils.getStackTrace(e));

        }

        return keyStoreFile;

    }

    /**
     * Method for constructing full file path from file name and root log file path.
     * 
     * @param logFile log file in which we are doing mapping
     * @param selectedProject selected project
     */
    private void fillKeystoreFilePath(KeyStoreFile keyStoreFile, String selectedProject) {

        String result = getFullProjectPath(selectedProject);

        result = result.concat(result.endsWith("\\") ? "" : "\\");

        keyStoreFile.setFilepath(result.concat(keyStoreFile.getFilename()));

    }

    /**
     * Method for reading keystore and its certificates.
     * 
     * @param keyStoreFile 
     * 
     */
    private void readKeyStore(KeyStoreFile keyStoreFile) {

        FileInputStream inputStream = null;

        try {

            File file = new File(keyStoreFile.getFilepath());

            inputStream = new FileInputStream(file);

            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());

            keystore.load(inputStream, keyStoreFile.getPassword().toCharArray());

            Enumeration<String> enumeration = keystore.aliases();

            while(enumeration.hasMoreElements()) {

                String alias = (String) enumeration.nextElement();

                Certificate certificate = keystore.getCertificate(alias);

                if (certificate instanceof X509Certificate) {

                    X509Certificate x509Cer = (X509Certificate) certificate;

                    CertificateFile certificateFile = new CertificateFile();

                    certificateFile.setAlias(alias);
                    certificateFile.setVersion(x509Cer.getVersion());
                    certificateFile.setSignatureAlgorithm(x509Cer.getSigAlgName());
                    certificateFile.setSerialNumber(x509Cer.getSerialNumber().toString(16));
                    certificateFile.setValidFrom(x509Cer.getNotBefore());
                    certificateFile.setValidTo(x509Cer.getNotAfter());
                    certificateFile.setIssuer(x509Cer.getIssuerDN().getName());
                    certificateFile.setSubject(x509Cer.getSubjectDN().getName());

                    keyStoreFile.addCertificateToList(certificateFile);

                }

            }

        } catch (java.security.cert.CertificateException e) {

            logger.error(ExceptionUtils.getStackTrace(e));

            throw new RuntimeException(e.getMessage());

        } catch (NoSuchAlgorithmException e) {

            logger.error(ExceptionUtils.getStackTrace(e));

            throw new RuntimeException(e.getMessage());

        } catch (FileNotFoundException e) {

            logger.error(ExceptionUtils.getStackTrace(e));

            throw new RuntimeException(e.getMessage());

        } catch (KeyStoreException e) {

            logger.error(ExceptionUtils.getStackTrace(e));

            throw new RuntimeException(e.getMessage());

        } catch (IOException e) {

            logger.error(ExceptionUtils.getStackTrace(e));

            throw new RuntimeException(e.getMessage());

        } finally {

            if (null != inputStream) {

                try {

                    inputStream.close();

                } catch (IOException e) {

                    logger.error(ExceptionUtils.getStackTrace(e));

                    throw new RuntimeException(e.getMessage());

                }

            }

        }

    }

    @Override
    public void setKeyStore(KeyStoreFile keyStoreFile,
                            String selectedProject) {

        try {

            if (keyStoreFile == null
                    ||
                    keyStoreFile.getFilename() == null
                    ||
                    selectedProject == null) {

                logger.error("Error while setting keystore.");

                return;

            }


            fillKeystoreFilePath(keyStoreFile, selectedProject);


            System.setProperty("javax.net.ssl.keyStore",
                    keyStoreFile.getFilepath());

            System.setProperty("javax.net.ssl.keyStorePassword", 
                    keyStoreFile.getPassword());

        } catch (Exception e) {

            logger.error(ExceptionUtils.getStackTrace(e));

        }
    }

    @Override
    public KeyStoreFile readJVMKeyStore() {

        String keyStore         = System.getProperty("javax.net.ssl.keyStore");
        String keyStorePassword = System.getProperty("javax.net.ssl.keyStorePassword");

        KeyStoreFile keyStoreFile = new KeyStoreFile();

        if (WatcherStringUtil.isEmptyMultiple(keyStore, 
                keyStorePassword)) {

            return keyStoreFile;

        }

        keyStoreFile.setFilepath(keyStore);
        keyStoreFile.setPassword(keyStorePassword);

        readKeyStore(keyStoreFile);

        return keyStoreFile;

    }

    /** Default path to configuration folder inside project. */
    private String projectKeystorePath;

    /** Extension of configuration files. */
    private String keystoreExtension;

    /** Bean {@link ProjectReaderService}. */
    private ProjectReaderService projectReaderService;

    /**
     * 
     * @param projectKeystorePath IOC
     */
    public void setProjectKeystorePath(String projectKeystorePath) {
        this.projectKeystorePath = projectKeystorePath;
    }

    /**
     * 
     * @param keystoreExtension IOC
     */
    public void setKeystoreExtension(String keystoreExtension) {
        this.keystoreExtension = keystoreExtension;
    }

    /**
     * 
     * @param projectReaderService IOC
     */
    public void setProjectReaderService(ProjectReaderService projectReaderService) {
        this.projectReaderService = projectReaderService;
    }

}
