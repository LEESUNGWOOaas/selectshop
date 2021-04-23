package com.example.selectshop.utils;

import com.example.selectshop.models.ItemDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Component

//ARC에 Naver shopping API코드를 Spring 형태로 뽑아낸 코드를 카피해온다
public class NaverShopSearch {
    public String search(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", "pnIZZAyBaA1bomb_0WS5");
        headers.add("X-Naver-Client-Secret", "DVUVlK8Btk");
        String body = ""; //Body 생성
        //응답을 받아와서 RequestEntity에 body를 같이 넣어준다.
        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=" + query, HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();

        int status = httpStatus.value(); // ARC에 200 OK가 뜨는방식이 Status안에 들어간다.
        String response = responseEntity.getBody();//getoBody= 우리가 직접보낸 결과 화면이 문자열로 response안에 들어간다.
        System.out.println("Response status: " + status); //status 인쇄하고
        System.out.println(response); //response 찍어본다.

        //리턴값은 response안에 들어가있으니 return은 response값을 준다
        return response;
    }

//     문자열로 받고 json Object로 바꿔서 ItemDtoList 만들어서 반환하는것은 항상쓰인다.
//        JsontoItem이란 메소드로 만들어서 더욱 편하게 반복적으로 사용하게 한다.
    public List<ItemDto> fromJSONtoItems(String result) {
        JSONObject rjson = new JSONObject(result);//result값이 {}형식안에 들어간다
        System.out.println(rjson);
        JSONArray items = rjson.getJSONArray("items"); // rjson에서 jsonArray를 꺼낸다
        //배열로 전달되야한다 아이폰을 검색시 검색결과가 1개가 나오는게 아니라 5개 10개나오게 하려면 리스트를 써야한다
        List<ItemDto> itemDtoList = new ArrayList<>();
        //itemDtoList안에  만든 itemDto의 데이터를 넣고 controller로 반환한다.

        //json의 배열안에서 값을 꺼내야 하는데 for문을 돌려서 꺼낸다
        for (int i = 0; i < items.length(); i++) {//시작지점 ,item의 크기만큼 꺼낸다
            JSONObject itemJson = (JSONObject) items.get(i);//object가 모여서 array가 되기때문에
            System.out.println(itemJson);
            ItemDto itemDto = new ItemDto(itemJson);
            itemDtoList.add(itemDto);
        }
        return itemDtoList;
    }

}



