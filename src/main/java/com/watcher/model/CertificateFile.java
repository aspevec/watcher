package com.watcher.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Domain object representing one certificate of keystore.
 * 
 * @author Aendy
 */
public class CertificateFile implements Serializable {
	
	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = -5620453592808197827L;
	
	/** Certificate alias in keystore. */
	private String alias;
	/** Certificate version. */
	private Integer version;
	/** Certificate serial number - hex. */
	private String serialNumber;
	/** Certificate signature algorithm. */
	private String signatureAlgorithm;
	/** Certificate is valid from. */
	private Date validFrom;
	/** Certificate is valid to. */
	private Date validTo;
	/** Who issued certificate. */
	private String issuer;
	/** To whom certificate is issued. */
	private String subject;
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getSignatureAlgorithm() {
		return signatureAlgorithm;
	}
	public void setSignatureAlgorithm(String signatureAlgorithm) {
		this.signatureAlgorithm = signatureAlgorithm;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}
	public String getIssuer() {
		return issuer;
	}
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "CertificateFile [alias=" + alias + ", version=" + version
				+ ", serialNumber=" + serialNumber + ", signatureAlgorithm="
				+ signatureAlgorithm + ", validFrom=" + validFrom
				+ ", validTo=" + validTo + ", issuer=" + issuer + ", subject="
				+ subject + "]";
	}
	
}
