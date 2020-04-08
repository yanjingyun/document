package com.yjy.test03_reflect.other;

import java.io.Serializable;

public class IdEntity implements Serializable {

	private static final long serialVersionUID = -47233795909497265L;

	private String id;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
