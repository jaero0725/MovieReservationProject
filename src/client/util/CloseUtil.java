package client.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CloseUtil {
   public static Connection getConnection() {
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "mytest", "mytest");
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }

   public static void close(PreparedStatement pstmt, Connection conn) {
      if (pstmt != null) {
         try {
            if (!pstmt.isClosed())
               pstmt.close();
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            pstmt = null;
         }
      }
      if (conn != null) {
         try {
            if (!conn.isClosed())
               conn.close();
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            conn = null;
         }
      }
   }

   public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
      if (rs != null) {
         try {
            if (!rs.isClosed())
               rs.close();
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            rs = null;
         }
      }
      if (pstmt != null) {
         try {
            if (!pstmt.isClosed())
               pstmt.close();
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            pstmt = null;
         }
      }
      if (conn != null) {
         try {
            if (!conn.isClosed())
               conn.close();
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            conn = null;
         }
      }
   }
}
