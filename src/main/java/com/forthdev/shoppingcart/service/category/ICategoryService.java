package com.forthdev.shoppingcart.service.category;

import com.forthdev.shoppingcart.model.Category;

import java.util.List;

public interface ICategoryService {
	Category getCategoryById(String id);

	Category getCategoryByName(String name);

	List<Category> getAllCategories();

	Category addCategory(Category category);

	Category updateCategory(Category category, String id);

	void deleteCategory(String id);
}
