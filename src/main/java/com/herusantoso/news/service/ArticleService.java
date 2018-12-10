package com.herusantoso.news.service;

import com.herusantoso.news.exception.NostraException;
import com.herusantoso.news.persistence.model.Article;
import com.herusantoso.news.persistence.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleRepository articleRepository;

    @Cacheable(value= "articleCache", key= "#articleId")
    public Article getArticleById(long articleId) {
        Article articleFound = articleRepository.findById(articleId)
                .orElseThrow(() -> new NostraException("Article Not Found"));
        return articleFound;
    }

    @Cacheable(value= "allArticlesCache", unless= "#result.size() == 0")
    public List<Article> getAllArticles(){
        List<Article> list = new ArrayList<>();
        return (List<Article>) articleRepository.findAll();
    }

    @Caching(
            put= { @CachePut(value= "articleCache", key= "#article.articleId") },
            evict= { @CacheEvict(value= "allArticlesCache", allEntries= true) }
    )
    public Article addArticle(Article article){
        return articleRepository.save(article);
    }


    @Caching(
            put= { @CachePut(value= "articleCache", key= "#article.articleId") },
            evict= { @CacheEvict(value= "allArticlesCache", allEntries= true) }
    )
    public Article updateArticle(Article article) {
        Article articleFound = articleRepository.findById(article.getArticleId())
                .orElseThrow(() -> new NostraException("Article Not Found"));
        articleFound.setCategory(article.getCategory());
        articleFound.setTitle(article.getTitle());
        return articleRepository.save(articleFound);
    }


    @Caching(
            evict= {
                    @CacheEvict(value= "articleCache", key= "#articleId"),
                    @CacheEvict(value= "allArticlesCache", allEntries= true)
            }
    )
    public Boolean deleteArticle(long articleId) {
        Article articleFound = articleRepository.findById(articleId)
                .orElseThrow(() -> new NostraException("Article Not Found"));
        articleRepository.delete(articleFound);
        return Boolean.TRUE;
    }


}
