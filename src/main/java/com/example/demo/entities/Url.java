package com.example.demo.entities;

import com.example.demo.dto.UrlDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Url {

    @Id
    @GeneratedValue
    private Long id;

    private String url;

    private boolean accessible;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private Url(String url) {
        this.url = url;

        // TODO: IMPLEMENT CHECKING SERVICE
        this.accessible = true;
    }

    public static Url fromDto(UrlDto dto) {
        return new Url(dto.getUrl());
    }

}
