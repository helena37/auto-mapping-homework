package com.automappinghomework.domain.models;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class GameAddDto {
    private String title;
    private BigDecimal price;
    private double size;
    private String trailer;
    private String image;
    private String description;
    private LocalDate releaseDate;

    public GameAddDto() {
    }

    public GameAddDto(String title, BigDecimal price, double size, String trailer,
                      String image, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.image = image;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    @Size(min = 3, max = 100)
   // @Pattern()
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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
}
