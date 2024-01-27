package com.sbs.jdbc.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Member {
  private int id;
  private String regDate;
  private String updateDate;
  private String loginId;
  private String loginPw;
  private String name;

  public Member(Map<String, Object> selectRow) {
    this.id = (int) selectRow.get("id");
    this.regDate = (String) selectRow.get("regDate");
    this.updateDate = (String) selectRow.get("updateDate");
    this.loginId = (String) selectRow.get("loginId");
    this.loginPw = (String) selectRow.get("loginPw");
    this.name = (String) selectRow.get("name");
  }

}
