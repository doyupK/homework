package com.example.homework.service.impl;

import com.example.homework.dto.ArticleResponseDto;
import com.example.homework.mapper.ArticleMapper;
import com.example.homework.mapper.BoardMapper;
import com.example.homework.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final ArticleMapper articleMapper;

    @Autowired
    public BoardServiceImpl(BoardMapper boardMapper, ArticleMapper articleMapper) {
        this.boardMapper = boardMapper;
        this.articleMapper = articleMapper;
    }

    @Override
    public List<ArticleResponseDto> findByNameKo(String board) {
        return boardMapper.selectArticleList(board);
    }
}
