package com.sbs.jdbc.board.cotainer;

import com.sbs.jdbc.board.controller.ArticleController;
import com.sbs.jdbc.board.controller.MemberController;
import com.sbs.jdbc.board.repository.ArticleRepository;
import com.sbs.jdbc.board.repository.MemberRepository;
import com.sbs.jdbc.board.service.ArticleService;
import com.sbs.jdbc.board.service.MemberService;
import com.sbs.jdbc.board.session.Session;

import java.util.Scanner;

public class Container {
  public static Scanner scanner;
  public static Session session;

  public static MemberRepository memberRepository;
  public static ArticleRepository articleRepository;

  public static MemberService memberService;
  public static ArticleService articleService;

  public static MemberController memberController;
  public static ArticleController articleController;

  static {
    scanner = new Scanner(System.in);
    session = new Session();

    memberRepository = new MemberRepository();
    articleRepository = new ArticleRepository();

    memberService = new MemberService();
    articleService = new ArticleService();

    memberController = new MemberController();
    articleController = new ArticleController();
  }
}
