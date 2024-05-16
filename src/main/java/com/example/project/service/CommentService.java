package com.example.project.service;

import com.example.project.dto.CommentDto;
import com.example.project.entity.Article;
import com.example.project.entity.Comment;
import com.example.project.repository.ArticleRepository;
import com.example.project.repository.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    // 댓글 조회
    public List<CommentDto> comments(Long articleId) {
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 엔티티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        return commentRepository.findByArticleId(articleId)// 댓글 엔티티 목록 조회
                .stream() // 댓글 엔티티 목록을 스트림으로 변환
                .map(CommentDto::createCommentDto)// 엔티티를 DTO로 매핑
                .collect(Collectors.toList()); // 스트림을 리스트로 변환
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        // 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new IllegalArgumentException("댓글 생성 실패"+"게시글이 없습니다"));
        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto,article);
        // 댓글 엔티티를 DB에 저장
        Comment created = commentRepository.save(comment);
        // DTO로 변환해 반환
        return CommentDto.createCommentDto(created);
    }
}
