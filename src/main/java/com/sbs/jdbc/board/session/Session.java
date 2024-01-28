package com.sbs.jdbc.board.session;

import com.sbs.jdbc.board.dto.Member;

public class Session {
  public int loginedMemberId;
  public Member loginedMemeber;

  public Session() {
    loginedMemberId = -1;
  }

  public boolean isLogined() {
    return loginedMemberId != -1;
  }

  public void login(Member member) {
    loginedMemberId = member.getId();
    loginedMemeber = member;
  }

  public boolean isLogout() {
    return loginedMemberId == -1;
  }

  public void logout() {
    loginedMemberId = -1;
    loginedMemeber = null;
  }
}
