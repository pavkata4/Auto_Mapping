package com.example._16_auto_mapping.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game{

    private long Id;
    private String title;
    private String trailer;
    private String imageThumbnail;
    private double size;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;




    public Game() {
    }

    public Game(String title, String trailer, String imageThumbnail, double size, BigDecimal price, String description, LocalDate releaseDate) {
        this.title = title;
        this.trailer = trailer;
        this.imageThumbnail = imageThumbnail;
        this.size = size;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    @Pattern(regexp = "[A-Z]+\\w+", message = "The title must start with uppercase letter.")
    @Length(min = 3, max = 100, message = "the size of title must between 3 and 100 characters.")
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    //@Pattern(regexp = "https:\\/\\/www\\.youtube\\.com\\/watch\\?v=\\w{11}", message = "invalid URL.")
    @Pattern(regexp = "\\w{11}", message = "Invalid URL.")
    @Column(name = "trailer", nullable = false)
    public String getTrailer() {
        return trailer;
    }
    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Pattern(regexp = "(http:\\/\\/ ?|https:\\/\\/)((\\w+)|(\\W+))+", message = "Invalid thumbnail.")
    @Column(name = "image_thumbnail", nullable = false)
    public String getImageThumbnail() {
        return imageThumbnail;
    }
    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    @Column(name = "size", nullable = false)
    @Min(value = 1, message = "Size must be positive number.")
    public double getSize() {
        return size;
    }
    public void setSize(double size) {
        this.size = size;
    }


    @Min(value = 1, message = "Price must be positive number.")
    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Length(min = 20, message = "Description must be at least 20 symbols")
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "release_date", nullable = false)
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return Id;
    }
    public void setId(long id) {
        Id = id;
    }

}
