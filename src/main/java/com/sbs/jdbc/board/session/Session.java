package com.sbs.jdbc.board.session;

import com.sbs.jdbc.board.dto.Member;

public class Session {
  public int loginedMemberId;
  public Member loginedMemeber;

  public Session() {
    loginedMemberId = -1;
  }
}
