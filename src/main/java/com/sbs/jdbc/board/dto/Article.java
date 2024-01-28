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
  private int memberId;
  private String title;
  private String body;
  private int hit;

  private String extra__writerName;

  public Article(Map<String, Object> selectRow) {
    this.id = (int) selectRow.get("id");
    this.regDate = (String) selectRow.get("regDate");
    this.updateDate = (String) selectRow.get("updateDate");
    this.memberId = (int) selectRow.get("memberId");
    this.title = (String) selectRow.get("title");
    this.body = (String) selectRow.get("body");
    this.hit = (int) selectRow.get("hit");

    if(selectRow.get("extra__writerName") != null) {
      this.extra__writerName = (String) selectRow.get("extra__writerName");
    }
  }
}
