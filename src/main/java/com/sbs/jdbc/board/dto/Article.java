package com.sbs.jdbc.board.dto;

import lombok.*;

import java.util.Map;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Article {
  private int id;
  private String regDate;
  private String updateDate;
  private String title;
  private String body;

  public Article(Map<String, Object> selectRow) {
    this.id = (int) selectRow.get("id");
    this.regDate = (String) selectRow.get("regDate");
    this.updateDate = (String) selectRow.get("updateDate");
    this.title = (String) selectRow.get("title");
    this.body = (String) selectRow.get("body");
  }
}
