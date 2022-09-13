package com.example.homework.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
//@Table(name = "cms_article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
//    @JoinColumn(name = "board_id")
    private Board board;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp createdDatetime;
    private boolean isPinned;
    private int viewCount;
    private String title;
    private String contentHtml;
    private String contentString;



}
