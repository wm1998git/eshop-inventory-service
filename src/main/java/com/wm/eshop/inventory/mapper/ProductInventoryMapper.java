package com.wm.eshop.inventory.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.wm.eshop.inventory.model.ProductInventory;

/** 注入【商品-库存表】的MyBatis Mapper配置 */
@Mapper
public interface ProductInventoryMapper {

	@Insert("INSERT INTO product_inventory(value,product_id) VALUES(#{value},#{productId})")
	void add(ProductInventory productInventory);

	@Update("UPDATE product_inventory SET value=#{value},product_id=#{productId} WHERE id=#{id}")
	void update(ProductInventory productInventory);

	@Delete("DELETE FROM product_inventory WHERE id=#{id}")
	void delete(Long id);

	@Select("SELECT * FROM product_inventory WHERE id=#{id}")
	ProductInventory findById(Long id);
	
	@Select("SELECT id,value,product_id as productId FROM product_inventory WHERE product_id=#{productId}")
	ProductInventory findByProductId(Long productId);

}
