package com.example.selectshop.models;

import lombok.Getter;
import org.json.JSONObject;
// 가져온 데이터를 클라이언트에게 돌려주는 클래스

//title , link , image , lprice(최저가)
@Getter
public class ItemDto {
    private String title;
    private String link;
    private String image;
    private int lprice;

    public ItemDto(JSONObject itemJson) {
        this.title = itemJson.getString("title");
        this.link = itemJson.getString("link");
        this.image = itemJson.getString("image");
        this.lprice = itemJson.getInt("lprice");
    }
}