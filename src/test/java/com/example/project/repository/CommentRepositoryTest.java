package com.example.project.repository;

import com.example.project.entity.Article;
import com.example.project.entity.Comment;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;
    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    @Transactional
    void findByArticleId() {
        // Case 1: 4번 게시글의 모든 댓글 조회
        // 입력 데이터 준비
        Long articleId = 4L;
        // 실제 데이터
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 예상 데이터, dto 생성 및 저장
        Article article = new Article(4L, "인생 영화는?","댓글1");
        Comment a = new Comment(1L,article,"Park","굿 윌 헌팅");
        Comment b = new Comment(2L,article,"Kim","아이 엠 샘");
        Comment c = new Comment(3L,article,"Choi","쇼생크 탈출");
        List<Comment> expected = Arrays.asList(a,b,c);
        // 비교 및 검증
        assertEquals(expected.toString(),comments.toString());
//        // Case 1: 1번 게시글의 모든 댓글 조회
//        // 입력 데이터 준비
//        Long articleId = 1L;
//        // 실제 데이터
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        // 예상 데이터, dto 생성 및 저장
//        Article article = new Article(1L, "1번테스트","11111");
//        List<Comment> expected = Arrays.asList();
//        // 비교 및 검증
//        assertEquals(expected.toString(),comments.toString());
    }


    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    @Transactional
    void findByN_name() {
        // Case 1: "Park"의 모든 댓글 조회
        // 입력 데이터 준비
        String N_name = "Park";
        // 실제 데이터
        List<Comment> comments = commentRepository.findByN_name(N_name);
        // 예상 데이터
        Comment a = new Comment(1L,new Article(4L,"인생 영화는?","댓글1"),N_name,"굿 윌 헌팅");
        Comment b = new Comment(4L, new Article(5L,"소울 푸드는?","댓글2"),N_name,"치킨");
        Comment c = new Comment(7L, new Article(6L,"취미는?","댓글3"),N_name,"조깅");
        List<Comment> expected = Arrays.asList(a,b,c);
        // 비교 및 검증
        assertEquals(expected.toString(),comments.toString(),"Park 의 모든 댓글 출력");
    }
}