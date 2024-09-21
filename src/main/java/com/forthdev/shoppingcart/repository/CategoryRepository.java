package com.forthdev.shoppingcart.repository;

import com.forthdev.shoppingcart.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, String> {
	Category findByName(String name);

	boolean existsByName(String name);
}
