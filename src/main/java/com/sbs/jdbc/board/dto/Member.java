package com.sbs.jdbc.board.dto;

import java.util.Map;

public class Member {
  public int id;
  public String regDate;
  public String updateDate;

  public String loginId;
  public String loginPw;
  public String name;

  public Member(Map<String, Object> selectRow) {
    this.id = (int) selectRow.get("id");
    this.regDate = (String) selectRow.get("regDate");
    this.updateDate = (String) selectRow.get("updateDate");
    this.loginId = (String) selectRow.get("loginId");
    this.loginPw = (String) selectRow.get("loginPw");
    this.name = (String) selectRow.get("name");
  }

  @Override
  public String toString() {
    return "Member{" +
        "id=" + id +
        ", regDate='" + regDate + '\'' +
        ", updateDate='" + updateDate + '\'' +
        ", loginId='" + loginId + '\'' +
        ", loginPw='" + loginPw + '\'' +
        ", name='" + name + '\'' +
        '}';
  }
}
