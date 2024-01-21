package com.sbs.jdbc.board.controller;

import com.sbs.jdbc.board.cotainer.Container;
import com.sbs.jdbc.board.util.MysqlUtil;
import com.sbs.jdbc.board.util.SecSql;

public class MemberController {
  public void join() {
    String loginId;
    String loginPw;
    String loginPwConfirm;
    String name;

    System.out.println("== 회원 가입 ==");
    // 로그인 아이디 입력
    while (true) {
      System.out.printf("로그인 아이디 : ");
      loginId = Container.scanner.nextLine().trim();

      if(loginId.length() == 0) {
        System.out.println("로그인 아이디를 입력해주세요.");
        continue;
      }

      SecSql sql = new SecSql();
      sql.append("SELECT COUNT(*) > 0");
      sql.append("FROM `member`");
      sql.append("WHERE loginId = ?", loginId);

      boolean isLoginIdDup = MysqlUtil.selectRowBooleanValue(sql);

      if (isLoginIdDup) {
        System.out.printf("\"%s\"(은)는 이미 사용중인 로그인 아이디입니다.\n", loginId);
        continue;
      }

      break;
    }

    // 로그인 비밀번호 입력
    while (true) {
      System.out.printf("로그인 비밀번호 : ");
      loginPw = Container.scanner.nextLine().trim();

      if(loginPw.length() == 0) {
        System.out.println("비밀번호를 입력해주세요.");
        continue;
      }

      boolean loginPwConfirmIsSame = true;

      while (true) {
        System.out.printf("로그인 비밀번호 확인 : ");
        loginPwConfirm = Container.scanner.nextLine().trim();

        if(loginPwConfirm.length() == 0) {
          System.out.println("비밀번호 확인을 입력해주세요.");
          continue;
        }

        if(loginPw.equals(loginPwConfirm) == false) {
          System.out.println("로그인 비밀번호가 일치하지 않습니다.");
          System.out.println("확인 후 다시 입력해주세요.");

          loginPwConfirmIsSame = false;
          break;
        }

        break;
      }

      // 로그인 비번화 비번확인이 일치한다면 제대로 입력된 것으로 간주한다.
      if(loginPwConfirmIsSame) {
        break;
      }
    }

    // 이름 입력
    while (true) {
      System.out.printf("이름 : ");
      name = Container.scanner.nextLine().trim();

      if(name.length() == 0) {
        System.out.println("이름을 입력해주세요.");
        continue;
      }

      break;
    }

    SecSql sql = new SecSql();
    sql.append("INSERT INTO `member`");
    sql.append("SET regDate = NOW()");
    sql.append(", updateDate = NOW()");
    sql.append(", loginId = ?", loginId);
    sql.append(", loginPw = ?", loginPw);
    sql.append(", name = ?", name);

    MysqlUtil.insert(sql);

    System.out.printf("\"%s\"님 회원 가입되었습니다.\n", name);
  }
}
