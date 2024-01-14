package com.sbs.jdbc.board;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

  List<Article> articles;
  int articleLastId;

  public App() {
    articles = new ArrayList<>();
    articleLastId = 0;
  }

  public void run() {
    System.out.println("== JDBC 게시판 프로그램 ==");
    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.printf("명령) ");
      String cmd = sc.nextLine();

      if(cmd.equals("/usr/article/write")) {
        System.out.println("== 게시물 작성 ==");
        System.out.printf("제목 : ");
        String title = sc.nextLine();

        System.out.printf("내용 : ");
        String body = sc.nextLine();

        int id = ++articleLastId;

        Article article = new Article(id, title, body);
        articles.add(article);

        System.out.println("입력 된 게시물 : " + article);
        System.out.printf("%d번 게시물이 작성되었습니다.\n", id);
      }
      else if(cmd.equals("/usr/article/list")) {
        System.out.println("== 게시물 리스트 ==");

        if(articles.isEmpty()) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        /*
        for(Article article : articles) {
          System.out.printf("%d / %s\n", article.id, article.title);
        }
         */

        for(int i = articles.size() - 1; i >= 0; i--) {
          Article article = articles.get(i);
          System.out.printf("%d / %s\n", article.id, article.title);
        }

      }
      else if(cmd.equals("exit")) {
        System.out.println("== 프로그램을 종료합니다 ==");
        break;
      }
      else {
        System.out.println("명령어를 잘못 입력하셨습니다.");
      }
    }

    sc.close();
  }
}
