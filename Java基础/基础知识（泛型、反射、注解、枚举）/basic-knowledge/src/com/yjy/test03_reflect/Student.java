package com.yjy.test03_reflect;

public class Student extends Person<String> {
	private Integer stuNo;

	public Integer getStuNo() {
		return stuNo;
	}

	public void setStuNo(Integer stuNo) {
		this.stuNo = stuNo;
	}
}
