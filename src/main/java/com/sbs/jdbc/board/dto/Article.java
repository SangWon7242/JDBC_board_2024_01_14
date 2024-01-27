package com.sbs.jdbc.board.dto;

import java.util.Comparator;
import java.util.Map;

public class Article {
  public int id;
  public String regDate;
  public String updateDate;

  public String title;
  public String body;

  // 테스트를 위한 생성자
  public Article(int id, String regDate, String updateDate, String title, String body) {
    this.id = id;
    this.regDate = regDate;
    this.updateDate = updateDate;
    this.title = title;
    this.body = body;
  }

  public Article(Map<String, Object> selectRow) {
    this.id = (int) selectRow.get("id");
    this.regDate = (String) selectRow.get("regDate");
    this.updateDate = (String) selectRow.get("updateDate");
    this.title = (String) selectRow.get("title");
    this.body = (String) selectRow.get("body");
  }

  @Override
  public String toString() {
    return "Article{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", body='" + body + '\'' +
        '}';
  }
}
