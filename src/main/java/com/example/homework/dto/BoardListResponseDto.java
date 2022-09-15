package com.example.homework.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class BoardListResponseDto {
    private Long id;
    private String title;
    private String contentHtml;
    private int viewCount;
    private Boolean isPinned;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private Timestamp createdDatetime;
}
