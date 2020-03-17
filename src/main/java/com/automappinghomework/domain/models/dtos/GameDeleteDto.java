package com.automappinghomework.domain.models.dtos;

public class GameDeleteDto {
    private long id;

    public GameDeleteDto() {
    }

    public GameDeleteDto(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
