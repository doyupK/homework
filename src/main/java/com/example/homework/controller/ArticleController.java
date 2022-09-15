package com.example.homework.controller;

import com.example.homework.dto.CreateRequestDto;
import com.example.homework.dto.DateRequestDto;
import com.example.homework.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    //게시글 생성
    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@RequestBody CreateRequestDto createRequestDto){
        articleService.createArticle(createRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //게시글 상세 조회 (image는 바이너리인지 url인지..? 메모리에 저장? 클라우드에 저장? 저장하는 api는?)
    @GetMapping("/article/{id}")
    public ResponseEntity<?> getArticle(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(articleService.findById(id));
        } catch (NullPointerException exception){
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }

    // 게시글 List
    // 어떤 게시글에 대한 리스트인지..? image는 바이너리인지 url인지..? 메모리에 저장? 클라우드에 저장? 저장하는 api는?
    @GetMapping("/article")
    public ResponseEntity<?> getArticleList(){
        try {
            return ResponseEntity.ok().body(articleService.findAll());
        } catch (NullPointerException exception){
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }

    // 게시글 삭제
    @DeleteMapping("/article/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id){
        try{
            articleService.deleteArticle(id);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (NullPointerException exception){
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }

    // 특정기간 게시물 조회
    @GetMapping("/article/date")
    public ResponseEntity<?> getArticleDateRangeList(@RequestBody DateRequestDto dateRequestDto){
        try {
            return ResponseEntity.ok().body(articleService.findByDate(dateRequestDto));
        } catch (NullPointerException exception){
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }
}