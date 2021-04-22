package com.example.selectshop.service;

import com.example.selectshop.models.Product;
import com.example.selectshop.models.ProductMyPriceRequestDto;

import com.example.selectshop.models.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class ProductService {
    //업데이트 메소드 필요 관심가격 정보가 전달됬다면 id 를 받아 해당상품을 찾고  myprice를 업데이트 해줘야한다
    private final ProductRepository productRepository;

    @Transactional // DB정보가 업데이틑 되는데 반드시 필요한 어노테이션
    public Long update(Long id, ProductMyPriceRequestDto requestDto) {
        //id를 product를 찾고 결과로 product가 나온다
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        //그 정보를 update한다
        product.update(requestDto);
        return id;
    }
}

