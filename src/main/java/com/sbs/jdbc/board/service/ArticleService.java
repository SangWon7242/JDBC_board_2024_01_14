package com.sbs.jdbc.board.service;

import com.sbs.jdbc.board.cotainer.Container;
import com.sbs.jdbc.board.dto.Article;
import com.sbs.jdbc.board.repository.ArticleRepository;

import java.util.List;

public class ArticleService {
  private ArticleRepository articleRepository;

  public ArticleService() {
    articleRepository = Container.articleRepository;
  }

  public int write(int memberId, String title, String body) {
    return articleRepository.write(memberId, title, body);
  }

  public List<Article> getForPrintArticles() {
    return articleRepository.getForPrintArticles();
  }

  public Article getForPrintArticleById(int id) {
    return articleRepository.getForPrintArticleById(id);
  }

  public int getArticleCount(int id) {
    return articleRepository.getArticleCount(id);
  }

  public void update(int id, String title, String body) {
    articleRepository.update(id, title, body);
  }

  public void delete(int id) {
    articleRepository.delete(id);
  }

  public void increaseHit(int id) {
    articleRepository.increaseHit(id);
  }
}
