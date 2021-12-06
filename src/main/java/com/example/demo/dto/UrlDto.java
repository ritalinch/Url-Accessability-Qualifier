package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UrlDto {

    private String url;

    @JsonCreator
    public UrlDto(@JsonProperty(required = true) String url) {
        this.url = url;
    }

}
