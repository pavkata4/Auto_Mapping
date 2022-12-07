package com.example._16_auto_mapping.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "trailer", nullable = false)
    private String trailer;
    @Column(name = "image_thumbnail", nullable = false)
    private String imageThumbnail;
    @Column(name = "size", nullable = false)
    private BigDecimal size;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;




    public Game() {
    }

    public Game(String title, String trailer, String imageThumbnail, BigDecimal size, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.trailer = trailer;
        this.imageThumbnail = imageThumbnail;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

//    public long getId() {
//        return Id;
//    }
//    public void setId(long id) {
//        Id = id;
//    }

//    @Pattern(regexp = "[A-Z]+\\w+", message = "The title must start with uppercase letter.")
//    @Length(min = 3, max = 100, message = "the size of title must between 3 and 100 characters.")

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    //@Pattern(regexp = "https:\\/\\/www\\.youtube\\.com\\/watch\\?v=\\w{11}", message = "invalid URL.")
//    @Pattern(regexp = "\\w{11}", message = "Invalid URL.")

    public String getTrailer() {
        return trailer;
    }
    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

//    @Pattern(regexp = "(http:\\/\\/ ?|https:\\/\\/)((\\w+)|(\\W+))+", message = "Invalid thumbnail.")

    public String getImageThumbnail() {
        return imageThumbnail;
    }
    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }


//    @Min(value = 1, message = "Size must be positive number.")
    public BigDecimal getSize() {
        return size;
    }
    public void setSize(double size) {
        this.size = BigDecimal.valueOf(size);
    }


//    @Min(value = 1, message = "Price must be positive number.")

    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

//    @Length(min = 20, message = "Description must be at least 20 symbols")

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


}
