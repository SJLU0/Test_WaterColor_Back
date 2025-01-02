package com.example.testwc.controller;

import com.example.testwc.entity.Product;
import com.example.testwc.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:5174")// 允許前端 http://localhost:5174 請求
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 查詢所有產品
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // 根據 productId 查詢單一產品
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    // 新增一筆產品
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        boolean isAdded = productService.addProduct(product);
        if (isAdded) {
            return ResponseEntity.status(201).body(product); // 返回201表示Created成功
        } else {
            return ResponseEntity.status(500).build(); // 500表示服務器錯誤
        }
    }
}
