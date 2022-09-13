package com.example.homework.service;

import com.example.homework.domain.Article;
import com.example.homework.dto.ArticleResponseDto;
import com.example.homework.dto.CreateRequestDto;
import com.example.homework.dto.DateRequestDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArticleService {


    @Transactional
    void createArticle(CreateRequestDto createRequestDto);

    @Transactional
    ArticleResponseDto findById(Long id);

    @Transactional
    void deleteArticle(Long id);

    @Transactional
    List<Article> findByDate(DateRequestDto dateRequestDto);
}
