package com.example.homework.mapper;

import com.example.homework.dto.ArticleResponseDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {


    List<ArticleResponseDto> selectArticleList(String board);
}
