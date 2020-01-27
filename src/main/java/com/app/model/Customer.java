package com.app.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="custab")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cId;
	@Column
	private String cName;
	@Column
	private String serviceId;
	@ElementCollection(fetch=FetchType.EAGER)
	private List<String> mode;
	public Customer() {
		super();
	}
	public Customer(Integer cId, String cName, String serviceId, List<String> mode) {
		super();
		this.cId = cId;
		this.cName = cName;
		this.serviceId = serviceId;
		this.mode = mode;
	}
	public Customer(Integer cId) {
		super();
		this.cId = cId;
	}
	public Integer getcId() {
		return cId;
	}
	public void setcId(Integer cId) {
		this.cId = cId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public List<String> getMode() {
		return mode;
	}
	public void setMode(List<String> mode) {
		this.mode = mode;
	}
	@Override
	public String toString() {
		return "Customer [cId=" + cId + ", cName=" + cName + ", serviceId=" + serviceId + ", mode=" + mode + "]";
	}
	
	
}
