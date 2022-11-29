package com.example._16_auto_mapping.DTOS;

import java.math.BigDecimal;

public class GameDTO {
    private String title;

    private BigDecimal price;

    public GameDTO() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return
                "Title: " + title  + " Price: " + price + '\n';
    }
}
