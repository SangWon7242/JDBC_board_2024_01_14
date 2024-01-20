package com.sbs.jdbc.board;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

        // JDBC 드라이버 클래스 이름
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";

        // 데이터베이스 연결 정보
        String url = "jdbc:mysql://127.0.0.1:3306/text_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
        String username = "sbsst";
        String password = "sbs123414";

        Connection conn = null;
        PreparedStatement pstat = null;

        try {
          // JDBC 드라이버 로드
          Class.forName(jdbcDriver);

          // 데이터베이스에 연결
          conn = DriverManager.getConnection(url, username, password);

          String sql = "INSERT INTO article";
          sql += " SET regDate = NOW()";
          sql += ", updateDate = NOW()";
          sql += ", title = \"" + title + "\""; // title = "제목"
          sql += ", `body` = \"" + body + "\"";

          pstat = conn.prepareStatement(sql);
          int affectedRows = pstat.executeUpdate();

          System.out.println("affectedRows : " + affectedRows);

        } catch (ClassNotFoundException e) {
          System.out.println("드라이버 로딩 실패");
        } catch (SQLException e) {
          System.out.println("에러 : " + e);
        } finally {
          try {
            if (conn != null && !conn.isClosed()) {
              // 연결 닫기
              conn.close();
            }
          } catch (SQLException e) {
            e.printStackTrace();
          }
          try {
            if (pstat != null && !pstat.isClosed()) {
              pstat.close();
            }
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }

        Article article = new Article(id, title, body);
        articles.add(article);

        System.out.printf("%d번 게시물이 작성되었습니다.\n", id);
      }
      else if(cmd.equals("/usr/article/list")) {
        System.out.println("== 게시물 리스트 ==");

        // JDBC 드라이버 클래스 이름
        String jdbcDriver = "com.mysql.cj.jdbc.Driver";

        // 데이터베이스 연결 정보
        String url = "jdbc:mysql://127.0.0.1:3306/text_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
        String username = "sbsst";
        String password = "sbs123414";

        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;

        try {
          // JDBC 드라이버 로드
          Class.forName(jdbcDriver);

          // 데이터베이스에 연결
          conn = DriverManager.getConnection(url, username, password);

          String sql = "SELECT *";
          sql += " FROM article";
          sql += " ORDER BY id DESC;";

          pstat = conn.prepareStatement(sql);
          rs = pstat.executeQuery(sql);

          while (rs.next()) {
            int id = rs.getInt("id");
            String regDate = rs.getString("regDate");
            String updateDate = rs.getString("updateDate");
            String title = rs.getString("title");
            String body = rs.getString("body");

            Article article = new Article(id, regDate, updateDate, title, body);
            articles.add(article);
          }

        } catch (ClassNotFoundException e) {
          System.out.println("드라이버 로딩 실패");
        } catch (SQLException e) {
          System.out.println("에러 : " + e);
        } finally {
          try {
            if (rs != null && !rs.isClosed()) {
              rs.close();
            }
          } catch (SQLException e) {
            e.printStackTrace();
          }
          try {
            if (pstat != null && !pstat.isClosed()) {
              pstat.close();
            }
          } catch (SQLException e) {
            e.printStackTrace();
          }
          try {
            if (conn != null && !conn.isClosed()) {
              // 연결 닫기
              conn.close();
            }
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }

        if(articles.isEmpty()) {
          System.out.println("게시물이 존재하지 않습니다.");
          continue;
        }

        System.out.println("번호 / 제목");
        for(Article article : articles) {
          System.out.printf("%d / %s\n", article.id, article.title);
        }
        /*
        for(int i = articles.size() - 1; i >= 0; i--) {
          Article article = articles.get(i);
          System.out.printf("%d / %s\n", article.id, article.title);
        }
        */
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
