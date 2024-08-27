package com.sparta.upgradeschedule.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ItemDto {
    private String date;
    private String weather;

    public ItemDto(JSONObject itemJson) {
        this.date = itemJson.getString("date");
        this.weather = itemJson.getString("weather");
    }
}