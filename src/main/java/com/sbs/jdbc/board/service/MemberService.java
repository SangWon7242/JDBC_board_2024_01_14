package com.sbs.jdbc.board.service;

import com.sbs.jdbc.board.cotainer.Container;
import com.sbs.jdbc.board.repository.MemberRepository;

public class MemberService {
  private MemberRepository memberRepository;
  public MemberService() {
    memberRepository = Container.memberRepository;
  }
  public boolean isLoginIdDup(String loginId) {
    return memberRepository.isLoginIdDup(loginId);
  }

  public void join(String loginId, String loginPw, String name) {
    memberRepository.join(loginId, loginPw, name);
  }
}
