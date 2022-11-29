package com.example._16_auto_mapping.DTOS;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DetailGameDTO {

    private String title;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;

    public DetailGameDTO() {
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Title: " + title + '\n' + " Price: " + price + '\n' + "Description: " + description + '\n' + "release date: " + releaseDate;
    }
}
