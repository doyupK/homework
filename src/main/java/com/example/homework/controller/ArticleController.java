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

    //게시글 상세 조회
    @GetMapping("/article/{id}")
    public ResponseEntity<?> openBoardList(@PathVariable Long id){
        try {
            return ResponseEntity.ok().body(articleService.findById(id));
        } catch (NullPointerException exception){
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }

    //게시글 생성
    @PostMapping("/article")
    public ResponseEntity<?> createArticle(@RequestBody CreateRequestDto createRequestDto){
        articleService.createArticle(createRequestDto);
        return ResponseEntity.ok(HttpStatus.CREATED);
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

    @GetMapping("/article/date")
    public ResponseEntity<?> getArticleList(@RequestBody DateRequestDto dateRequestDto){
        try {
            return ResponseEntity.ok().body(articleService.findByDate(dateRequestDto));
        } catch (NullPointerException exception){
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }
}