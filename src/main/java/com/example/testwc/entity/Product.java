package com.example.testwc.entity;

import com.example.testwc.constant.ColorCategory;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {
//對應 watercolor 資料表
    private Long productId; // 資料庫中的自增欄位，not null
    private String zhName;  // 中文名稱，not null
    private String enName;  // 英文名稱，not null
    private String image1Url; // 第一張圖片 URL，not null
    private String image2Url; // 第二張圖片 URL，可以是null
    private BigDecimal price; // 價格，not null
    private LocalDateTime listingDate; // 上架日期，可以是null
    private ColorCategory colorCategory;  // 顏色分類，對應 ENUM 類型
    
    // 建構子 (Java原本就有，但我先寫出來)
    public Product() {}


    // Getter 和 Setter 方法
    
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getImage1Url() {
        return image1Url;
    }

    public void setImage1Url(String image1Url) {
        this.image1Url = image1Url;
    }

    public String getImage2Url() {
        return image2Url;
    }

    public void setImage2Url(String image2Url) {
        this.image2Url = image2Url;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getListingDate() {
        return listingDate;
    }

    public void setListingDate(LocalDateTime listingDate) {
        this.listingDate = listingDate;
    }

    public ColorCategory getColorCategory() {
        return colorCategory;
    }

    public void setColorCategory(ColorCategory colorCategory) {
        this.colorCategory = colorCategory;
    }

    
   

}   
