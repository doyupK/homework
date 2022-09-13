package com.example.homework.controller;

import com.example.homework.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping("/board/{board}")
    public ResponseEntity<?> searchBoard(@PathVariable String board){
        return ResponseEntity.ok().body(boardService.findByNameKo(board));
    }
}
