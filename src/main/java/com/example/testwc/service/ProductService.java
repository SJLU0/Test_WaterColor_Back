package com.example.testwc.service;

import com.example.testwc.entity.Product;
import com.example.testwc.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 查詢所有產品
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    // 根據 productId 查詢單一產品
    public Product getProductById(Long productId) {
        return productRepository.getProductById(productId);
    }

    // 新增一筆產品
    public boolean addProduct(Product product) {
        int result = productRepository.addProduct(product);
        return result > 0; // 如果結果大於0，表示新增成功
    }

    // 更新產品
    public boolean updateProduct(Product product) {
        int result = productRepository.updateProduct(product);
        return result > 0; // 如果結果大於0，表示更新成功
    }

    // 刪除產品
    public boolean deleteProduct(Long productId) {
        int result = productRepository.deleteProductById(productId);
        return result > 0; // 如果結果大於0，表示刪除成功
    }
}
