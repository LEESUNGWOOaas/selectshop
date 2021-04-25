package com.example.selectshop.service;

import com.example.selectshop.models.ItemDto;
import com.example.selectshop.models.Product;
import com.example.selectshop.models.ProductMyPriceRequestDto;

import com.example.selectshop.models.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성
@Service // 서비스 선언
public class ProductService {
    //업데이트 메소드 필요 관심가격 정보가 전달됬다면 id 를 받아 해당상품을 찾고  myprice를 업데이트 해줘야한다
    private final ProductRepository productRepository;

    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언
    public Long update(Long id, ProductMyPriceRequestDto requestDto) {
        //id를 product를 찾고 결과로 product가 나온다
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        //그 정보를 update한다
        product.update(requestDto);
        return id;
    }

    @Transactional // 메소드 동작이 SQL 쿼리문 선언
    public Long updateBySearch(Long id, ItemDto itemDto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.") //에러의 종류는 상관이없다.
        );
        product.updateByItemDto(itemDto);
        return id;
    }
}