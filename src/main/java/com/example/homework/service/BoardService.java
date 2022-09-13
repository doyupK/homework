package com.example.homework.service;

import com.example.homework.dto.ArticleResponseDto;

import java.util.List;

public interface BoardService {
    List<ArticleResponseDto> findByNameKo(String board);
}
