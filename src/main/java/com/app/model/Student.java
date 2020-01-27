package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="stdtabs")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer stdId;
	@Column
	private String stdName;
	@Column
	private Double stdFee;
	@Column
	private String stdCourse;
	
	public Student() {
		super();
	}
	public Student(Integer stdId, String stdName, Double stdFee, String stdCourse) {
		super();
		this.stdId = stdId;
		this.stdName = stdName;
		this.stdFee = stdFee;
		this.stdCourse = stdCourse;
	}
	public Student(Integer stdId) {
		super();
		this.stdId = stdId;
	}
	public Integer getStdId() {
		return stdId;
	}
	public void setStdId(Integer stdId) {
		this.stdId = stdId;
	}
	public String getStdName() {
		return stdName;
	}
	public void setStdName(String stdName) {
		this.stdName = stdName;
	}
	public Double getStdFee() {
		return stdFee;
	}
	public void setStdFee(Double stdFee) {
		this.stdFee = stdFee;
	}
	public String getStdCourse() {
		return stdCourse;
	}
	public void setStdCourse(String stdCourse) {
		this.stdCourse = stdCourse;
	}
	@Override
	public String toString() {
		return "Student [stdId=" + stdId + ", stdName=" + stdName + ", stdFee=" + stdFee + ", stdCourse=" + stdCourse
				+ "]";
	}
	
}
