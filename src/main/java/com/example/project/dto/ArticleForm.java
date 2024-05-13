package com.example.project.dto;


import com.example.project.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public Article toEntity(){
        return new Article(id,title,content);
    }
}

