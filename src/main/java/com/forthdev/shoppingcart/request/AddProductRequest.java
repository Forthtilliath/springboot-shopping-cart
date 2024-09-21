package com.forthdev.shoppingcart.request;

import com.forthdev.shoppingcart.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductRequest {
	private String id;
	private String name;
	private String brand;
	private BigDecimal price;
	private int inventory;
	private String description;
	private Category category;
}
