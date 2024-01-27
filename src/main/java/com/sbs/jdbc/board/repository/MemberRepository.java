package com.sbs.jdbc.board.repository;

import com.sbs.jdbc.board.util.MysqlUtil;
import com.sbs.jdbc.board.util.SecSql;

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
}
