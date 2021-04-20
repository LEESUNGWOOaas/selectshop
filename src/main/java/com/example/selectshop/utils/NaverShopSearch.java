package com.example.selectshop.utils;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


//ARC에 Naver shopping API코드를 Spring 형태로 뽑아낸 코드를 카피해온다
public class NaverShopSearch {
    public String search() {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "pnIZZAyBaA1bomb_0WS5");
        headers.add("X-Naver-Client-Secret", "DVUVlK8Btk");
        String body = ""; //Body 생성
        //응답을 받아와서 RequestEntity에 body를 같이 넣어준다.
        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=아디다스", HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();

        int status = httpStatus.value(); // ARC에 200 OK가 뜨는방식이 Status안에 들어간다.
        String response = responseEntity.getBody();//getoBody= 우리가 직접보낸 결과 화면이 문자열로 response안에 들어간다.
        System.out.println("Response status: " + status); //status 인쇄하고
        System.out.println(response); //response 찍어본다.

        //리턴값은 response안에 들어가있으니 return은 response값을 준다
        return response;
    }

    //메인함수 - Spring 함수와 무관하게 실행함
    public static void main(String[] args) {
        NaverShopSearch naverShopSearch = new NaverShopSearch();
        naverShopSearch.search();
    }
}