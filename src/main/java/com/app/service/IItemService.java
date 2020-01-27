package com.app.service;

import java.util.List;

import com.app.model.Item;

public interface IItemService {
	public Item saveItem(Item s);
	public Item getItemById(Integer id);
	public List<Item> getAllItems();
	public void deleteItemById(Integer id);
	public boolean ItemExist(Integer id);
}
