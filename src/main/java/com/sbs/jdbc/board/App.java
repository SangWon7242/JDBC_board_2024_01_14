package com.sbs.jdbc.board;

import com.sbs.jdbc.board.controller.ArticleController;
import com.sbs.jdbc.board.controller.MemberController;
import com.sbs.jdbc.board.cotainer.Container;
import com.sbs.jdbc.board.dto.Article;
import com.sbs.jdbc.board.util.MysqlUtil;
import com.sbs.jdbc.board.util.SecSql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

  private static boolean isDevMode() {
    // 이 부분을 false로 바꾸면 production 모드 이다.
    return true;
  }

  public void run() {
    System.out.println("== JDBC 게시판 프로그램 ==");
    Scanner sc = new Scanner(System.in);

    try {
      while (true) {
        System.out.printf("명령) ");
        String cmd = sc.nextLine();
        Rq rq = new Rq(cmd);

        // DB 세팅
        MysqlUtil.setDBInfo("localhost", "sbsst", "sbs123414", "text_board");
        MysqlUtil.setDevMode(isDevMode());

        // 명령 로직 실행
        action(rq);
      }
    } finally {
      sc.close();
    }
  }

  private void action(Rq rq) {
    MemberController memberController = Container.memberController;
    ArticleController articleController = Container.articleController;

    if (rq.getUrlPath().equals("/usr/article/write")) {
     articleController.write();
    } else if (rq.getUrlPath().equals("/usr/article/list")) {
      articleController.list();
    } else if (rq.getUrlPath().equals("/usr/article/detail")) {
      articleController.detail(rq);
    } else if (rq.getUrlPath().equals("/usr/article/modify")) {
     articleController.modify(rq);
    } else if (rq.getUrlPath().equals("/usr/article/delete")) {
      articleController.delete(rq);
    } else if (rq.getUrlPath().equals("/usr/member/join")) {
      memberController.join();
    } else if (rq.getUrlPath().equals("/usr/member/login")) {
      memberController.login();
    } else if (rq.getUrlPath().equals("exit")) {
      System.out.println("== 프로그램을 종료합니다 ==");
      System.exit(0);
    } else {
      System.out.println("명령어를 잘못 입력하셨습니다.");
    }
  }
}

