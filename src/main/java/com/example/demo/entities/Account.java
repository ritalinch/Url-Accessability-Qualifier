package com.example.demo.entities;

import com.example.demo.dto.AccountDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String name;
    private String pictureUrl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
    private List<Url> urls = new ArrayList<>();

    public static Account fromDto(AccountDto dto) {
        return Account.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .pictureUrl(dto.getPictureUrl())
                .build();
    }

    public AccountDto toDto() {
        return AccountDto.builder()
                .email(this.email)
                .name(this.name)
                .pictureUrl(this.pictureUrl)
                .build();
    }

    public void addUrl(Url url) {
        url.setAccount(this);
        this.urls.add(url);
    }

}
