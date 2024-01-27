package com.sbs.jdbc.board.controller;

import com.sbs.jdbc.board.cotainer.Container;
import com.sbs.jdbc.board.dto.Member;
import com.sbs.jdbc.board.service.MemberService;

public class MemberController {
  private MemberService memberService;
  public MemberController() {
    memberService = Container.memberService;
  }
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

      boolean isLoginIdDup = memberService.isLoginIdDup(loginId);

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

    memberService.join(loginId, loginPw, name);

    System.out.printf("\"%s\"님 회원 가입되었습니다.\n", name);
  }

  public void login() {
    String loginId;
    String loginPw;
    Member member = null;

    System.out.println("== 로그인 ==");

    int loginIdTryMaxCount = 3;
    int loginIdTryCount = 0;

    while (true) {
      if(loginIdTryCount == loginIdTryMaxCount) {
        System.out.println("로그인 아이디를 확인 후 다음에 다시 입력해주세요.");
        return;
      }

      System.out.printf("로그인 아이디 : ");
      loginId = Container.scanner.nextLine().trim();

      if (loginId.length() == 0) {
        System.out.println("로그인 아이디를 입력해주세요.");
        loginIdTryCount++;
        continue;
      }

      member = memberService.getMemberByLoginId(loginId);

      if (member == null) {
        System.out.println("입력하신 로그인 아이디는 존재하지 않습니다.");
        return;
      }

      break;
    }


    int loginPwTryMaxCount = 3;
    int loginPwTryCount = 0;

    while (true) {
      if(loginPwTryCount == loginPwTryMaxCount) {
        System.out.println("비밀번호 확인 후 다음에 다시 입력해주세요.");
        break;
      }

      System.out.printf("로그인 비밀번호 : ");
      loginPw = Container.scanner.nextLine().trim();

      if (loginPw.length() == 0) {
        System.out.println("로그인 비밀번호를 입력해주세요.");
        return;
      }

      if(member.getLoginPw().equals(loginPw) == false) {
        System.out.println("비밀번호가 일치하지 않습니다.");
        loginPwTryCount++;
        continue;
      }

      System.out.printf("\"%s\"님 로그인 되었습니다.\n", member.getName());
      Container.session.loginedMemberId = member.getId();
      Container.session.loginedMemeber = member;
      break;
    }
  }

  public void whoami() {
    String loginId;

    if(Container.session.loginedMemberId == -1) {
      System.out.println("로그인 상태가 아닙니다.");
    } else {
      loginId = Container.session.loginedMemeber.getLoginId();
      System.out.printf("현재 로그인한 회원은 \"%s\" 입니다.\n", loginId);
    }
  }
}
