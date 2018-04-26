package com.watcher.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Domain object representing one keystore file and it's data.
 * 
 * @author Aendy
 */
public class KeyStoreFile implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 5082036218985739421L;
    /** File name. */
    private String filename;
    /** Path to file. */
    private String filepath;
    /** Keystore password. */
    private String password;
    /** List of certificates in keystore. */
    private List<CertificateFile> certificates;

    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getFilepath() {
        return filepath;
    }
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<CertificateFile> getCertificates() {
        return certificates;
    }
    public void setCertificates(List<CertificateFile> certificates) {
        this.certificates = certificates;
    }

    public void addCertificateToList(CertificateFile certificateFile) {

        if (certificates == null) {

            certificates = new ArrayList<CertificateFile>();

        }

        certificates.add(certificateFile);

    }

    @Override
    public String toString() {
        return "KeyStoreFile [filename=" + filename + ", filepath=" + filepath
                + ", password=" + password + ", certificates=" + certificates
                + "]";
    }

}
