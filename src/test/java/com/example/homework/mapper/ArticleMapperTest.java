package com.example.homework.mapper;

import com.example.homework.domain.Article;
import com.example.homework.dto.CreateModifyDto;
import com.example.homework.mapper.ArticleMapper;
import com.example.homework.service.impl.ArticleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;


//@ExtendWith(SpringExtension.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;


    @BeforeEach
    public void setup() {
        CreateModifyDto createModifyDto1 = CreateModifyDto.builder()
                .id(1L)
                .title("title")
                .content_string("contentString")
                .content_html("contentHtml")
                .create_datetime(Timestamp.valueOf("2022-09-14 00:00:00"))
                .build();
        CreateModifyDto createModifyDto2 = CreateModifyDto.builder()
                .id(2L)
                .title("title")
                .content_string("contentString")
                .content_html("contentHtml")
                .create_datetime(Timestamp.valueOf("2022-09-15 00:00:00"))
                .build();
        CreateModifyDto createModifyDto3 = CreateModifyDto.builder()
                .id(3L)
                .title("title")
                .content_string("contentString")
                .content_html("contentHtml")
                .create_datetime(Timestamp.valueOf("2022-09-20 00:00:00"))
                .build();

        articleMapper.createArticle(createModifyDto1);
        articleMapper.createArticle(createModifyDto2);
        articleMapper.createArticle(createModifyDto3);
    }

    @Test
    @DisplayName("게시글 등록")
    void createArticle() {
        //given
        CreateModifyDto createModifyDto4 = CreateModifyDto.builder()
                .id(4L)
                .title("title")
                .content_string("contentString")
                .content_html("contentHtml")
                .create_datetime(Timestamp.valueOf("2022-09-20 00:00:00"))
                .build();

        articleMapper.createArticle(createModifyDto4);

        //when
        Article articleResult = articleMapper.findById(4L);

        //then
        assertThat(articleResult).isNotNull();
    }

    @Test
    @DisplayName("게시글 삭제")
    void deleteArticle() {
        //given
        //when
        int deleteResult = articleMapper.deleteById(1L);

        //then
        assertThat(deleteResult).isGreaterThanOrEqualTo(1);
    }
}