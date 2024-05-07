package com.example.project.service;

import com.example.project.dto.ArticleForm;
import com.example.project.entity.Article;
import com.example.project.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Iterable<Article> index(){
        return articleRepository.findAll();
    }
    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }
    public Article create(ArticleForm dto){
        Article article = dto.toEntity();
        // DB가 생성해주는 id 오입력방지
        if (article.getId()!=null){
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id,ArticleForm dto){
        // 클라이언트로 요청 온 데이터, dto ->엔티티변환
        Article article = dto.toEntity();
        log.info("id:{}, article: {}", id, article.toString());
        // 기존 데이터 id
        Article target = articleRepository.findById(id).orElse(null);

        // DB에 있는 데이터를 초과한 id 값이 들어올 때(잘못된 값)
        if (target == null || !id.equals(article.getId())) {
            log.info("잘못된 요청 id:{}, article: {}", id, article.toString());
            return null;
        }
        // null 이 들어올 경우 기존데이터에 새 데이터로 덮기
        target.patch(article);
        // 정상 반환
        Article updated = articleRepository.save(target);
        return updated;
    }
    public Article delete(Long id){
        Article target = articleRepository.findById(id).orElse(null);
        // 기존 id 이외에 값 or 초과 값이 요청왔을 때
        if (target==null){
            return null;
        }
        articleRepository.delete(target);
        return target; // or build() 같은 결과값
    }
}
