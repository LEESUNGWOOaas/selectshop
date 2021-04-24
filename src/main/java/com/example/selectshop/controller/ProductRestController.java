package com.example.selectshop.controller;

import com.example.selectshop.models.Product;
import com.example.selectshop.models.ProductMyPriceRequestDto;
import com.example.selectshop.models.ProductRepository;
import com.example.selectshop.models.ProductRequestDto;
import com.example.selectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동으로 생성
@RestController // JSON으로 데이터를 주고받음을 선언
public class ProductRestController {

    private final ProductRepository productRepository; //final은 내가 원할때 자동적으로 생성해야되는데 그점을 필수적(상수화)임을 추가해주는 코드
    private final ProductService productService;

    // 등록된 전체 상품 목록 조회
    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    // 신규 상품 등록
    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto){
        Product product = new Product(requestDto);
        productRepository.save(product);
        return productRepository.save(product);
    }

    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMyPriceRequestDto requestDto) {
        return productService.update(id, requestDto);
    }
}