package com.sbs.jdbc.board.cotainer;

import com.sbs.jdbc.board.controller.ArticleController;
import com.sbs.jdbc.board.controller.MemberController;

import java.util.Scanner;

public class Container {
  public static Scanner scanner;

  public static MemberController memberController;
  public static ArticleController articleController;

  static {
    scanner = new Scanner(System.in);

    memberController = new MemberController();
    articleController = new ArticleController();
  }
}
