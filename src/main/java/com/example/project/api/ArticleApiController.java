package com.example.project.api;


import com.example.project.dto.ArticleForm;
import com.example.project.entity.Article;
import com.example.project.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
public class ArticleApiController {
    @Autowired
    private ArticleRepository articleRepository;
    // 모든 데이터 검색
    @GetMapping("/api/articles")
    public Iterable<Article> index(){
        return articleRepository.findAll();
    }
    // 특정 데이터 검색
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }
    // 데이터 추가 요청
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    @PatchMapping("api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id, @RequestBody ArticleForm dto) {
        // 클라이언트로 요청 온 데이터
        Article article = dto.toEntity();
        log.info("id:{}, article: {}", id, article.toString());
        // 기존 데이터 id
        Article target = articleRepository.findById(id).orElse(null);

        // DB에 있는 데이터를 초과한 id 값이 들어올 때(잘못된 값)
        if (target == null || !id.equals(article.getId())) {
            log.info("잘못된 요청 id:{}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // null 이 들어올 경우 기존데이터에 새 데이터로 덮기
        target.patch(article);
        // 정상 반환
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    // 삭제 요청
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        Article target = articleRepository.findById(id).orElse(null);
        // 기존 id 이외에 값 or 초과 값이 요청왔을 때
        if (target==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).body(null); // or build() 같은 결과값
    }
}
