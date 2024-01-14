package com.sbs.jdbc.board;

public class Article {
  public int id;
  public String title;
  public String body;

  // 생성자 메서드 : 객첵가 만들어질 때 딱 한번 실행!!
  public Article(int id, String title, String body) {
    this.id = id;
    this.title = title;
    this.body = body;
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
