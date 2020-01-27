package com.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="emptab")
public class Employee {
	@Id
	@GeneratedValue
	private Integer eId;
	@Column
	private String eName;
	@Column
	private Double eSal;
	
	public Employee() {
		super();
	}
	public Employee(Integer eId, String eName, Double eSal) {
		super();
		this.eId = eId;
		this.eName = eName;
		this.eSal = eSal;
	}
	public Employee(Integer eId) {
		super();
		this.eId = eId;
	}
	public Integer geteId() {
		return eId;
	}
	public void seteId(Integer eId) {
		this.eId = eId;
	}
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public Double geteSal() {
		return eSal;
	}
	public void seteSal(Double eSal) {
		this.eSal = eSal;
	}
	@Override
	public String toString() {
		return "Employee [eId=" + eId + ", eName=" + eName + ", eSal=" + eSal + "]";
	}
	
}
