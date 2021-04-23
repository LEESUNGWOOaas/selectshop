package com.example.selectshop.controller;

import com.example.selectshop.models.ItemDto;
import com.example.selectshop.utils.NaverShopSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor // final 로 선언된 클래스를 자동으로 생성
@RestController // JSON으로 응답함을 선언
public class SearchRequestController {

    private final NaverShopSearch naverShopSearch;

    @GetMapping("/api/search")
    public List<ItemDto> getItems(@RequestParam String query) {
        String resultString = naverShopSearch.search(query);
        //itemDto로 바꿔준다
        return naverShopSearch.fromJSONtoItems(resultString);
    }
}