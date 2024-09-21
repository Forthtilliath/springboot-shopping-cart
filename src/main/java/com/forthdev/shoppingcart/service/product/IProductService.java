package com.forthdev.shoppingcart.service.product;

import com.forthdev.shoppingcart.model.Product;
import com.forthdev.shoppingcart.request.AddProductRequest;
import com.forthdev.shoppingcart.request.UpdateProductRequest;

import java.util.List;

public interface IProductService {
	Product addProduct(AddProductRequest request);
	Product getProductById(String id);
	void deleteProduct(String id);
	Product updateProduct(UpdateProductRequest request, String productId);
	List<Product> getAllProducts();
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByBrand(String brand);
	List<Product> getProductsByCategoryAndBrand(String category, String brand);
	List<Product> getProductsByName(String name);
	List<Product> getProductsByBrandAndName(String brand, String name);
	Long countProductsByBrandAndName(String brand, String name);
}
