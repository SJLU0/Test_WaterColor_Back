package com.example.testwc.controller;

import com.example.testwc.entity.Product;
import com.example.testwc.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5174") // 允許前端 http://localhost:5174 請求
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Read：查詢所有產品
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Read：根據 productId 查詢單一產品
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    // Create：新增一筆產品
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        boolean isAdded = productService.addProduct(product);
        if (isAdded) {
            return ResponseEntity.status(201).body(product); // 返回201表示Created成功
        } else {
            return ResponseEntity.status(500).build(); // 500表示服務器錯誤
        }
    }

    // Update：更新產品資料 By ID
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        // 檢查是否存在要更新的產品
        Product existingProduct = productService.getProductById(productId);
        if (existingProduct == null) {
            return ResponseEntity.notFound().build(); // 產品不存在，返回 404
        }

        // 更新產品資料
        existingProduct.setZhName(product.getZhName());
        existingProduct.setEnName(product.getEnName());
        existingProduct.setImage1Url(product.getImage1Url());
        existingProduct.setImage2Url(product.getImage2Url());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setListingDate(product.getListingDate());
        existingProduct.setColorCategory(product.getColorCategory());

        boolean isUpdated = productService.updateProduct(existingProduct);
        if (isUpdated) {
            return ResponseEntity.ok(existingProduct); // 返回更新後的產品
        } else {
            return ResponseEntity.status(500).build(); // 更新失敗，返回 500
        }
    }

    // Delete：刪除產品
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        // 檢查是否存在要刪除的產品
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build(); // 產品不存在，返回 404
        }

        boolean isDeleted = productService.deleteProduct(productId);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // 返回 204 無內容表示刪除成功
        } else {
            return ResponseEntity.status(500).build(); // 刪除失敗，返回 500
        }
    }
}
