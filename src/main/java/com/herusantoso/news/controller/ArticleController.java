package com.herusantoso.news.controller;

import com.herusantoso.news.persistence.model.Article;
import com.herusantoso.news.service.ArticleService;
import com.herusantoso.news.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @RequestMapping(value = "get-all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultVO> getAll(){
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return articleService.getAllArticles();
            }
        };

        return handler.getResult();
    }

    @RequestMapping(value = "get-detail/{id}",
            method = RequestMethod.GET)
    public ResponseEntity<ResultVO> getDetail(@PathVariable(value = "id") Long id){
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return articleService.getArticleById(id);
            }
        };

        return handler.getResult();
    }

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultVO> create(@RequestBody Article article){
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return articleService.addArticle(article);
            }
        };

        return handler.getResult();
    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultVO> update(@RequestBody Article product){
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return articleService.updateArticle(product);
            }
        };

        return handler.getResult();
    }

    @RequestMapping(value = "delete/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<ResultVO> delete(@PathVariable(value = "id") Long id){
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return articleService.deleteArticle(id);
            }
        };

        return handler.getResult();
    }

}
