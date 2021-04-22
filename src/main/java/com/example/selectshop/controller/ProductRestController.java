package com.example.selectshop.controller;

import com.example.selectshop.models.Product;
import com.example.selectshop.models.ProductRepository;
import com.example.selectshop.models.ProductRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성
@RestController // JSON으로 데이터를 주고받음을 선언
public class ProductRestController {

    private final ProductRepository productRepository; //final은 내가 원할때 자동적으로 생성해야되는데 그점을 필수적(상수화)임을 추가해주는 코드

    // 등록된 전체 상품 목록 조회
    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto){
        Product product = new Product(requestDto);
        productRepository.save(product);
        return productRepository.save(product);
    }
}