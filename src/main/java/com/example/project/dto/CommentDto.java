package com.example.project.dto;

import com.example.project.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class CommentDto {
    private Long id; // 댓글의 id
    private Long articleId; // 댓글의 부모 id
    private String N_name; // 댓글 작성자
    private String body; // 댓글 본문

    public static CommentDto createCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(), // 댓글 엔티티의 id
                comment.getArticle().getId(), // 댓글 엔티티가 속한 부모 게시글의 id
                comment.getN_name(), // 댓글 엔티티의 N_name
                comment.getBody()); // 댓글 엔티티의 body
    }
}
