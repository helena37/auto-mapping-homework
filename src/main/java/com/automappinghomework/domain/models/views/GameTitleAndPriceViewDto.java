package com.automappinghomework.domain.models.views;

import java.math.BigDecimal;

public class GameTitleAndPriceViewDto {
   private String title;
   private BigDecimal price;

    public GameTitleAndPriceViewDto() {
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
}
