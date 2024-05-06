package com.example.project.controller;

import com.example.project.dto.ArticleForm;
import com.example.project.entity.Article;
import com.example.project.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Slf4j
@Controller
public class ArticleController {
    //articleRepository 객체 선언
    @Autowired
    private ArticleRepository articleRepository;

    // 정보 추가 페이지
    @GetMapping("/articles/new")
    public String newArticles(){
        return "articles/new";
    }
    // 폼 데이터->dto->entity->repository
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        Article article = form.toEntity();
        Article saved = articleRepository.save(article);
        return "redirect:/articles/"+ saved.getId();
    }
    // 회원 상세 페이지(아이디기준)
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article",articleEntity);
        return "articles/show";
    }

    // 회원 목록 페이지(모든 데이터)
    @GetMapping("/articles")
    public String index(Model model){
        Iterable<Article> articleEntity = articleRepository.findAll();
        model.addAttribute("articleList",articleEntity);
        return "articles/index";
    }
    
    // 회원 수정 페이지
    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id,Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);
        model.addAttribute("article",articleEntity);
        return "/articles/edit";
    }
    // 수정된 데이터 저장
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        Article articleEntity = form.toEntity();
        Optional<Article> target = articleRepository.findById(articleEntity.getId());
        if (target.isPresent()){
            Article article= target.get();
            articleRepository.save(article);
        }
        return "redirect:/articles/"+ articleEntity.getId();

    }
    // 회원 데이터 삭제
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        Optional<Article> target = articleRepository.findById(id);
        if (target.isPresent()){
            Article article=target.get();
            articleRepository.delete(article);
            rttr.addFlashAttribute("msg","삭제됐습니다.");
        }

        return "redirect:/articles";
    }

}
