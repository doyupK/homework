package com.example.homework.service.impl;

import com.example.homework.domain.Article;
import com.example.homework.dto.ArticleResponseDto;
import com.example.homework.dto.CreateModifyDto;
import com.example.homework.dto.CreateRequestDto;
import com.example.homework.dto.DateRequestDto;
import com.example.homework.mapper.ArticleMapper;
import com.example.homework.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

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
                .content(createRequestDto.getContent())
                .content_html(createRequestDto.getContent())
                .content_string(HtmlUtils.htmlEscape(createRequestDto.getContent()))
                .build();

        articleMapper.createArticle(createModifyDto);
    }

    @Override
    public ArticleResponseDto findById(Long id){
        articleMapper.countUpById(id);
        Article article = articleMapper.findById(id);

        return ArticleResponseDto.builder()
                .articleId(article.getId())
                .title(article.getTitle())
                .createdDatetime(article.getCreatedDatetime())
                .contentHtml(article.getContentHtml())
                .viewCount(article.getViewCount())
                .isPinned(article.isPinned())
                .nameKo(article.getBoard().getNameKo())
                .build();
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