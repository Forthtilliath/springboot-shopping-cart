package com.forthdev.shoppingcart.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity(name = "categories")
@NoArgsConstructor
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String name;

	@OneToMany(mappedBy = "category")
	private List<Product> products;

	public Category(String name) {
		this.name = name;
	}
}
