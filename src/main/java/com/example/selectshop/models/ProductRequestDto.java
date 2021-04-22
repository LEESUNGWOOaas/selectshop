package com.example.selectshop.models;

import lombok.Getter;

@Getter
public class ProductRequestDto {
    //title link image lprice
    private String title;
    private String link;
    private String image;
    private int lprice;
}
