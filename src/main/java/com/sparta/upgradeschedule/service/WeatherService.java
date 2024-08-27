package com.sparta.upgradeschedule.service;


import com.sparta.upgradeschedule.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Slf4j(topic = "WEATHER API")
@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<ItemDto> searchItems() {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
                .fromUriString("https://f-api.github.io")
                .path("/f-api/weather.json")
                .encode()
                .build()
                .toUri();
        log.info("uri = " + uri);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        return fromJSONtoItems(responseEntity.getBody());
    }

    public List<ItemDto> fromJSONtoItems(String responseEntity) {
        //바로 배열로 받아와줘야 원하는데로 작동
        JSONArray item2 = new JSONArray(responseEntity);
        List<ItemDto> itemDtoList = new ArrayList<>();

        for (Object item : item2) {
            ItemDto itemDto = new ItemDto((JSONObject) item);
            itemDtoList.add(itemDto);
        }

        return itemDtoList;
    }
}