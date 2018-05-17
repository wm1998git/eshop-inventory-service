package com.wm.eshop.inventory.service;

import com.wm.eshop.inventory.model.ProductInventory;

/** 商品库存Service */
public interface ProductInventoryService {

	void add(ProductInventory productInventory);

	void update(ProductInventory productInventory);

	void delete(Long id);

	ProductInventory findById(Long id);

}
