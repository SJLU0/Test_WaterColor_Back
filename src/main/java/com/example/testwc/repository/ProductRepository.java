package com.example.testwc.repository;

import com.example.testwc.entity.Product;
import com.example.testwc.constant.ColorCategory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 查詢所有產品
    public List<Product> getAllProducts() {
        String sql = "SELECT product_id, zh_name, en_name, image1_url, image2_url, price, listing_date, color_category FROM watercolor";
        return jdbcTemplate.query(sql, new ProductRowMapper());
    }

    // 根據 productId 查詢產品
    public Product getProductById(Long productId) {
        String sql = "SELECT product_id, zh_name, en_name, image1_url, image2_url, price, listing_date, color_category FROM watercolor WHERE product_id = ?";
        return jdbcTemplate.queryForObject(sql, new ProductRowMapper(), productId);
    }

    // 新增一筆產品
    public int addProduct(Product product) {
        String sql = "INSERT INTO watercolor (zh_name, en_name, image1_url, image2_url, price, listing_date, color_category) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                product.getZhName(),
                product.getEnName(),
                product.getImage1Url(),
                product.getImage2Url(),
                product.getPrice(),
                product.getListingDate(),
                product.getColorCategory().name() // 轉換成字符串 (ENUM)

        );
    }

    // ProductRowMapper 用於將查詢結果轉換為 Product 物件
    private static class ProductRowMapper implements RowMapper<Product> {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product product = new Product();
            product.setProductId(rs.getLong("product_id")); // 主鍵 productId
            product.setZhName(rs.getString("zh_name")); // 中文名稱
            product.setEnName(rs.getString("en_name")); // 英文名稱
            product.setImage1Url(rs.getString("image1_url")); // 第一張圖片 
            product.setImage2Url(rs.getString("image2_url")); // 第二張圖片 
            product.setPrice(rs.getBigDecimal("price")); //  價格
            if (rs.getTimestamp("listing_date") != null) {
                product.setListingDate(rs.getTimestamp("listing_date").toLocalDateTime()); // 上架日期
            }
            product.setColorCategory(ColorCategory.valueOf(rs.getString("color_category"))); // 顏色分類
            return product;
        }
    }
}
