package com.yjy.test03_reflect.other;

public class VersionEntity extends IdEntity {

	private static final long serialVersionUID = -4228616165412181862L;
	/**
	 * °æ±¾ºÅ
	 */
	private int versionNumber;
	
	public int getVersionNumber() {
		return versionNumber;
	}
	
	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}
}
