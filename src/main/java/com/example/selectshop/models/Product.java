package com.example.selectshop.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // get 함수를 일괄적으로 만들어줍니다.
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class Product extends Timestamped{

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myprice;

    public Product (ProductRequestDto requestDto){ //데이터를 가지고있는 관심상품 정보
        this.title = requestDto.getTitle();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.image = requestDto.getImage();
        this.myprice = 0; //myprice는 가격 설정을 안하고 생성시 제일 작은 것 부터 순차적으로 보여줘야한다. 0이면 최저가 표시가 안나온다.

    }
    //item 정보를 받아서 product에 업데이트
    public void updateByItemDto(ItemDto itemdto){
        this.lprice = itemdto.getLprice();
    }

    public void update(ProductMyPriceRequestDto requestDto){
        this.myprice = requestDto.getMyprice();
    }
}