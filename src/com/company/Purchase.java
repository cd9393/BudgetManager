package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Purchase {
    private String description;
    private Category category;
    private BigDecimal price;

    public Purchase (String description, Category category, BigDecimal price) {
        this.description = description;
        this.category = category;
        this.price = price.setScale(2, RoundingMode.HALF_EVEN);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
