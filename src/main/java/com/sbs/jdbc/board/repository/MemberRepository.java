package com.sbs.jdbc.board.repository;

import com.sbs.jdbc.board.dto.Article;
import com.sbs.jdbc.board.dto.Member;
import com.sbs.jdbc.board.util.MysqlUtil;
import com.sbs.jdbc.board.util.SecSql;

import java.util.Map;

public class MemberRepository {
  public boolean isLoginIdDup(String loginId) {
    SecSql sql = new SecSql();
    sql.append("SELECT COUNT(*) > 0");
    sql.append("FROM `member`");
    sql.append("WHERE loginId = ?", loginId);

    return MysqlUtil.selectRowBooleanValue(sql);
  }

  public void join(String loginId, String loginPw, String name) {
    SecSql sql = new SecSql();
    sql.append("INSERT INTO `member`");
    sql.append("SET regDate = NOW()");
    sql.append(", updateDate = NOW()");
    sql.append(", loginId = ?", loginId);
    sql.append(", loginPw = ?", loginPw);
    sql.append(", name = ?", name);

    MysqlUtil.insert(sql);
  }

  public Member getMemberByLoginId(String loginId) {
    SecSql sql = new SecSql();
    sql.append("SELECT *");
    sql.append("FROM `member`");
    sql.append("WHERE loginId = ?", loginId);

    Map<String, Object> selectRow = MysqlUtil.selectRow(sql);

    if(selectRow.isEmpty()) {
      return null;
    }

    return new Member(selectRow);
  }

  public Member getMemberByEmail(String email) {
    SecSql sql = new SecSql();
    sql.append("SELECT *");
    sql.append("FROM `member`");
    sql.append("WHERE email = ?", email);

    Map<String, Object> selectRow = MysqlUtil.selectRow(sql);

    if(selectRow.isEmpty()) {
      return null;
    }

    return new Member(selectRow);
  }

  public void changeLoginPw(String loginId, String loginPw) {
    SecSql sql = new SecSql();
    sql.append("UPDATE `member`");
    sql.append("SET loginPw = ?", loginPw);
    sql.append("WHERE loginId = ?", loginId);

    MysqlUtil.update(sql);
  }

  public boolean checkLoginIdAndEmailMatch(String loginId, String email) {
    SecSql sql = new SecSql();
    sql.append("SELECT COUNT(*) > 0");
    sql.append("FROM `member`");
    sql.append("WHERE loginId = ?", loginId);
    sql.append("AND email = ?", email);

    return MysqlUtil.selectRowBooleanValue(sql);
  }
}
