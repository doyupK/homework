package com.example.homework.dto;

import lombok.*;

@Builder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateModifyDto {
    private int id;
    private String title;
    private String content;
    private String content_html;
    private String content_string;
}
