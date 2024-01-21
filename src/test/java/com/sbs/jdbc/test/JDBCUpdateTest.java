package com.sbs.jdbc.test;

import java.sql.*;

public class JDBCUpdateTest {
  public static void main(String[] args) {
    // JDBC 드라이버 클래스 이름
    String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    // 데이터베이스 연결 정보
    String url = "jdbc:mysql://127.0.0.1:3306/text_board?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
    String username = "sbsst";
    String password = "sbs123414";

    Connection conn = null;
    PreparedStatement pstat = null;

    try {
      // JDBC 드라이버 로드
      Class.forName(jdbcDriver);

      // 데이터베이스에 연결
      conn = DriverManager.getConnection(url, username, password);

      String sql = "UPDATE article";
      sql += " SET updateDate = NOW()";
      sql += ", title = '수정제목'";
      sql += ", `body` = '수정내용'";
      sql += " WHERE id = 1;";

      pstat = conn.prepareStatement(sql);
      pstat.executeUpdate();

      System.out.println("sql : " + sql);

    } catch (ClassNotFoundException e) {
      System.out.println("드라이버 로딩 실패");
    } catch (SQLException e) {
      System.out.println("에러 : " + e);
    } finally {
      try {
        if (pstat != null && !pstat.isClosed()) {
          pstat.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        if (conn != null && !conn.isClosed()) {
          // 연결 닫기
          conn.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
