package com.example.homework.controller;

import com.example.homework.dto.CreateModifyDto;
import com.example.homework.dto.CreateRequestDto;
import com.example.homework.dto.DateRequestDto;
import com.example.homework.mapper.ArticleMapper;
import com.example.homework.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.*;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class ArticleControllerTest {

    @Autowired
    ArticleController articleController;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    MockMvc mockMvc;


    @Order(3)
    @DisplayName("게시글 생성")
    @Test
    void createArticle() throws Exception {
        //given
        String createRequestDto = new ObjectMapper().writeValueAsString(
                CreateRequestDto.builder()
                        .title("articleTitle")
                        .content("articleContent")
                        .build());

        //when
        ResultActions resultActions = mockMvc.perform(post("/article")
                .content(createRequestDto)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        //then
        assertThat(resultActions.andReturn().getResponse().getStatus()).isEqualTo(201);
        resultActions
                .andDo(print());

    }

    @Order(1)
    @DisplayName("게시글 상세 조회")
    @Test
    void getArticle() throws Exception {
        //given
        CreateRequestDto createRequestDto1 = CreateRequestDto.builder()
                        .title("articleTitle")
                        .content("articleContent")
                        .build();

        articleController.createArticle(createRequestDto1);

        //when
        ResultActions resultActions = mockMvc.perform(get("/article/1")
                .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(createRequestDto1.getTitle()))
                .andExpect(jsonPath("$.contentHtml").value(createRequestDto1.getContent()))
                .andExpect(jsonPath("$.isPinned").value(false))
                .andExpect(jsonPath("$.viewCount").value(1))
                .andDo(MockMvcResultHandlers.print());
    }
    @Order(2)
    @DisplayName("게시글 목록 조회")
    @Test
    void getArticleList() throws Exception {
        //given
        CreateRequestDto createRequestDto1 = CreateRequestDto.builder()
                .title("articleTitle")
                .content("articleContent")
                .build();
        CreateRequestDto createRequestDto2 = CreateRequestDto.builder()
                .title("articleTitle2")
                .content("articleContent2")
                .build();
        CreateRequestDto createRequestDto3 = CreateRequestDto.builder()
                .title("articleTitle3")
                .content("articleContent3")
                .build();

        articleController.createArticle(createRequestDto1);
        articleController.createArticle(createRequestDto2);
        articleController.createArticle(createRequestDto3);

        //when
        ResultActions resultActions = mockMvc.perform(get("/article")
                .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[1].title").value(createRequestDto2.getTitle()))
                .andExpect(jsonPath("$.[1].isPinned").value(false))
                .andExpect(jsonPath("$.[1].viewCount").value(0))
                .andExpect(jsonPath("$", hasSize(3)))
                .andDo(MockMvcResultHandlers.print());
    }
    @Order(4)
    @DisplayName("게시글 삭제")
    @Test
    void deleteArticle() throws Exception {
        //given
        CreateRequestDto createRequestDto1 = CreateRequestDto.builder()
                .title("articleTitle")
                .content("articleContent")
                .build();

        articleController.createArticle(createRequestDto1);

        //when
        ResultActions resultActions = mockMvc.perform(delete("/article/6")
                .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }


    @Order(5)
    @DisplayName("게시글 기간 조회")
    @Test
    void getArticleDateRangeList() throws Exception {
        //given
        CreateModifyDto createModifyDto1 = CreateModifyDto.builder()
                .title("articleTitle")
                .content_html("articleContent")
                .content_string("articleContent")
                .create_datetime(Timestamp.valueOf("2022-09-14 00:00:00"))
                .build();
        CreateModifyDto createModifyDto2 = CreateModifyDto.builder()
                .title("articleTitle")
                .content_html("articleContent")
                .content_string("articleContent")
                .create_datetime(Timestamp.valueOf("2022-09-15 00:00:00"))
                .build();
        CreateModifyDto createModifyDto3 = CreateModifyDto.builder()
                .title("articleTitle")
                .content_html("articleContent")
                .content_string("articleContent")
                .create_datetime(Timestamp.valueOf("2022-09-20 00:00:00"))
                .build();

        articleMapper.createArticle(createModifyDto1);
        articleMapper.createArticle(createModifyDto2);
        articleMapper.createArticle(createModifyDto3);

        String dateRequestDto = new ObjectMapper().writeValueAsString(
                DateRequestDto.builder()
                        .startDate(Timestamp.valueOf("2022-09-14 00:00:00"))
                        .endDate(Timestamp.valueOf("2022-09-16 00:00:01"))
                        .build());

        //when
        ResultActions resultActions = mockMvc.perform(get("/article/date")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dateRequestDto)
                        .accept(MediaType.APPLICATION_JSON));


        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andDo(MockMvcResultHandlers.print());
    }


}