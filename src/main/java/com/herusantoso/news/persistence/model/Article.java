package com.herusantoso.news.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="articles")
@Data
public class Article implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="article_id")
    private long articleId;

    @Column(name="title")
    private String title;

    @Column(name="category")
    private String category;

}
