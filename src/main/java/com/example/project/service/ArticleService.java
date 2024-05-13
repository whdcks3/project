package com.example.project.service;


import com.example.project.dto.ArticleForm;
import com.example.project.entity.Article;
import com.example.project.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    // 전체 데이터 조회
    public List<Article> index(){
        return articleRepository.findAll();
    }
    // 단일데이터 조회
    public Article show(Long id){
        return articleRepository.findById(id).orElse(null);
    }
    // 데이터 등록
    public Article create(ArticleForm dto){
        Article article = dto.toEntity();
        // 기존데이터 수정방지
        if (article.getId() !=null){
            return null;
        }
        return articleRepository.save(article);
    }
    // 데이터 수정
    public Article update(Long id,ArticleForm dto){
        // dto-> 엔티티 변환
        Article article = dto.toEntity();
        // 변경시킬 기존 데이터 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 잘못된 요청 처리
        if (target == null || id.equals(article.getId())){
            return null;
        }
        // 일부 내용만 전달되어 왔을 때 기존 내용으로 저장
        target.patch(article);
        // 업데이트하기
        return articleRepository.save(target);
    }

    // 데이터 삭제
    public Article delete(Long id){
        // 제거 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 잘못된 요청 처리
        if (target==null){
            return null;
        }
        // 대상 삭제
        articleRepository.delete(target);
        return target;
    }
    // 트랜잭션
    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos){
        // dto를 엔티티 묶음으로 변환하기
        List<Article> articleList = dtos.stream()
                .map(ArticleForm::toEntity)
                .toList();
        // 엔티티 묶음을 db에 저장하기
        articleList.forEach(article->articleRepository.save(article));
        // 강제 예외 발생시키기
        articleRepository.findById(-1L)
                .orElseThrow(()->new IllegalArgumentException("결제 실패"));
        // 결과반환
        return articleList;
    }
}
