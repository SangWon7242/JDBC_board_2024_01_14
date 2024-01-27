package com.sbs.jdbc.board.service;

import com.sbs.jdbc.board.cotainer.Container;
import com.sbs.jdbc.board.repository.ArticleRepository;

import java.util.List;
import java.util.Map;

public class ArticleService {
  private ArticleRepository articleRepository;

  public ArticleService() {
    articleRepository = Container.articleRepository;
  }

  public int write(String title, String body) {
    return articleRepository.write(title, body);
  }

  public List<Map<String, Object>> getArticlesMap() {
    return articleRepository.getArticlesMap();
  }

  public Map<String, Object> getArticleMap(int id) {
    return articleRepository.getArticleMap(id);
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
}
