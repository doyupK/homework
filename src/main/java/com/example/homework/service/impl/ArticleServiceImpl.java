package com.example.homework.service.impl;

import com.example.homework.domain.Article;
import com.example.homework.dto.*;
import com.example.homework.mapper.ArticleMapper;
import com.example.homework.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }


    @Override
    public void createArticle(CreateRequestDto createRequestDto){
        CreateModifyDto createModifyDto = CreateModifyDto.builder()
                .title(createRequestDto.getTitle())
                .content_html(createRequestDto.getContent())
                .content_string(HtmlUtils.htmlEscape(createRequestDto.getContent()))
                .create_datetime(Timestamp.from(Instant.now()))
                .build();

        articleMapper.createArticle(createModifyDto);
    }

    @Override
    public ArticleResponseDto findById(Long id){
        articleMapper.countUpById(id);
        Article article = articleMapper.findById(id);

        return ArticleResponseDto.builder()
                .id(article.getId())
                .title(article.getTitle())
                .createdDatetime(article.getCreatedDatetime())
                .contentHtml(article.getContentHtml())
                .viewCount(article.getViewCount())
                .isPinned(article.getIsPinned())
                .nameKo(article.getBoard().getNameKo())
                .build();
    }

    @Override
    public List<ArticleListResponseDto> findAll(){
        return articleMapper.findAll();
    }

    @Override
    public void deleteArticle(Long id){
        int deleteCount = articleMapper.deleteById(id);
        if(deleteCount == 0) {
            throw new NullPointerException();
        }
    }

    @Override
    public List<Article> findByDate(DateRequestDto dateRequestDto){
        return articleMapper.findByDate(dateRequestDto);
    }

}