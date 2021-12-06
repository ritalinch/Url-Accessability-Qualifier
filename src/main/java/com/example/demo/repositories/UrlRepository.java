package com.example.demo.repositories;

import com.example.demo.entities.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long> {

    boolean existsByUrl(String url);

    List<Url> findByAccount_Email(String email);

}
