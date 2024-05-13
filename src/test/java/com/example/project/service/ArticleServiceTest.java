package com.example.project.service;

import com.example.project.dto.ArticleForm;
import com.example.project.entity.Article;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;
    @Test
    void index() {
        // 예상 데이터
        Article a = new Article(1L,"1번테스트","11111");
        Article b = new Article(2L,"2번테스트","22222");
        Article c = new Article(3L,"3번테스트","33333");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a,b,c));
        // 실제 데이터
        List<Article> articles = articleService.index();
        // 비교 및 검증
        assertEquals(expected.toString(),articles.toString());
    }

    @Test
    void show_성공() {
        // 예상 데이터
        Long id = 1L;
        Article expected = new Article(id,"1번테스트","11111");
        // 실제 데이터
        Article article = articleService.show(id);
        // 비교 및 검증
        assertEquals(expected.toString(),article.toString());
    }

    @Test
    void show_실패(){
        // 예상 데이터
        Long id = -1L;
        Article expected = null;
        // 실제 데이터
        Article article = articleService.show(id);
        // 비교 및 검증
        assertEquals(expected,article);
    }

    @Test
    @Transactional
    void create_성공() {
        // 예상 데이터
        String title = "4번테스트";
        String content = "44444";
        // dto 생성
        ArticleForm dto = new ArticleForm(null,title,content);
        // 예상 데이터 저장
        Article expected = new Article(4L,title,content);
        // 실제 데이터
        Article article = articleService.create(dto);
        // 비교 및 검증
        assertEquals(expected.toString(),article.toString());
    }
    @Test
    @Transactional
    void create_실패() {
        // 예상 데이터
        Long id = 4L;
        String title = "4번테스트";
        String content = "44444";
        ArticleForm dto = new ArticleForm(id,title,content);
        Article expected = null;
        // 실제 데이터
        Article article =articleService.create(dto);
        // 비교 및 검증
        assertEquals(expected,article);
    }
}