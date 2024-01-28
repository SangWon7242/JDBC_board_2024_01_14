package com.sbs.jdbc.board.service;

import com.sbs.jdbc.board.cotainer.Container;
import com.sbs.jdbc.board.dto.Member;
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

  public Member getMemberByLoginId(String loginId) {
    return memberRepository.getMemberByLoginId(loginId);
  }

  public Member getMemberByEmail(String email) {
    return memberRepository.getMemberByEmail(email);
  }

  public void changeLoginPw(String loginId, String loginPw) {
    memberRepository.changeLoginPw(loginId, loginPw);
  }
}
