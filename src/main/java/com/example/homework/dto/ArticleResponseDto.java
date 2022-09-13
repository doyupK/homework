package com.example.homework.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Builder
@Getter
@Setter
public class ArticleResponseDto {
    private Long articleId;
    private String title;
    private String contentHtml;
    private int viewCount;
    private boolean isPinned;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp createdDatetime;
    private String nameKo;

}
