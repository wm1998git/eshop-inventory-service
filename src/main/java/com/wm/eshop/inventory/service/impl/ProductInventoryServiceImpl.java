package com.wm.eshop.inventory.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.wm.eshop.inventory.mapper.ProductInventoryMapper;
import com.wm.eshop.inventory.model.ProductInventory;
import com.wm.eshop.inventory.service.ProductInventoryService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

	@Autowired
	private ProductInventoryMapper productInventoryMapper;

	@Autowired
	private JedisPool jedisPool;

	public void add(ProductInventory productInventory) {
		productInventoryMapper.add(productInventory);
		Jedis jedis = jedisPool.getResource();
		jedis.set("product_inventory_" + productInventory.getProductId(), JSONObject.toJSONString(productInventory));
	}

	public void update(ProductInventory productInventory) {
		productInventoryMapper.update(productInventory);
		Jedis jedis = jedisPool.getResource();
		jedis.set("product_inventory_" + productInventory.getProductId(), JSONObject.toJSONString(productInventory));
	}

	public void delete(Long id) {
		ProductInventory productInventory = findById(id);
		productInventoryMapper.delete(id);// 删除之前先查询出数据，供写入redis时用
		Jedis jedis = jedisPool.getResource();
		jedis.del("product_inventory_" + productInventory.getProductId());
	}

	public ProductInventory findById(Long id) {
		return productInventoryMapper.findById(id);
	}

}
