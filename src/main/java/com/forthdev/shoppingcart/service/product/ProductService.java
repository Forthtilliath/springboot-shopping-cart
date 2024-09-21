package com.forthdev.shoppingcart.service.product;

import com.forthdev.shoppingcart.exceptions.ResourceNotFoundException;
import com.forthdev.shoppingcart.model.Category;
import com.forthdev.shoppingcart.model.Product;
import com.forthdev.shoppingcart.repository.CategoryRepository;
import com.forthdev.shoppingcart.repository.ProductRepository;
import com.forthdev.shoppingcart.request.AddProductRequest;
import com.forthdev.shoppingcart.request.UpdateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {
	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;

	@Override
	public Product addProduct(AddProductRequest request) {
		Category category = Optional
				.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
				.orElseGet(() -> {
					Category newCategory = new Category(request.getCategory().getName());
					return categoryRepository.save(newCategory);
				});
		request.setCategory(category);
		return productRepository.save(createProduct(request, category));
	}

	private Product createProduct(AddProductRequest request, Category category) {
		return new Product(request.getName(), request.getBrand(), request.getPrice(), request.getInventory(), request.getDescription(), category);
	}

	@Override
	public Product getProductById(String id) {
		return productRepository
				.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found"));
	}

	@Override
	public void deleteProduct(String id) {
		productRepository
				.findById(id)
				.ifPresentOrElse(productRepository::delete, () -> {
					throw new ResourceNotFoundException("Product not found");
				});
	}

	@Override
	public Product updateProduct(UpdateProductRequest request, String productId) {
		return productRepository.findById(productId)
				.map(existingProduct -> updateExistingProduct(existingProduct,request))
				.map(productRepository::save)
				.orElseThrow(()-> new ResourceNotFoundException("Product not found!"));
	}

	private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request) {
		existingProduct.setName(request.getName());
		existingProduct.setBrand(request.getBrand());
		existingProduct.setPrice(request.getPrice());
		existingProduct.setInventory(request.getInventory());
		existingProduct.setDescription(request.getDescription());

		// Category category = categoryRepository.findByName(request.getCategory().getName());
		existingProduct.setCategory(request.getCategory());
		// existingProduct.setCategory(category.getName());

		return existingProduct;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		return productRepository.findByCategory(category);
	}

	@Override
	public List<Product> getProductsByBrand(String brand) {
		return productRepository.findByBrand(brand);
	}

	@Override
	public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
		return productRepository.findByCategoryAndBrand(category, brand);
	}

	@Override
	public List<Product> getProductsByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public List<Product> getProductsByBrandAndName(String brand, String name) {
		return productRepository.findByBrandAndName(brand, name);
	}

	@Override
	public Long countProductsByBrandAndName(String brand, String name) {
		return productRepository.countByBrandAndName(brand, name);
	}
}
