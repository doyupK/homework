package com.example.homework.mapper;

import com.example.homework.domain.Article;
import com.example.homework.dto.ArticleListResponseDto;
import com.example.homework.dto.CreateModifyDto;
import com.example.homework.dto.DateRequestDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {

    void createArticle(CreateModifyDto createRequestDto);

    Article findById(Long id);

    void countUpById(Long id);

    int deleteById(Long id);


    List<Article> findByDate(DateRequestDto dateRequestDto);

    List<ArticleListResponseDto> findAll();
}