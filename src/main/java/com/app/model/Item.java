package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="itemtab")
public class Item {
	@Id
	@GeneratedValue
	private Integer itemId;
	private String itemCode;
	private Double itemCost;
	public Item() {
		super();
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public Double getItemCost() {
		return itemCost;
	}
	public void setItemCost(Double itemCost) {
		this.itemCost = itemCost;
	}
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemCode=" + itemCode + ", itemCost=" + itemCost + "]";
	}
	
}
