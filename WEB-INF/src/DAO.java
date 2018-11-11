
package todo.dao;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

/**
 *
 * @author banba
 * データベースとの接続を取得する。
 * 取得済みだった場合は既存の接続を利用し、未取得だった場合は新規にコンテナから取得。
 */
public class DAO implements AutoCloseable {
    private Connection connection = null;
    
    public DAO() {
        
    }
    
    /**
      * データベースとの接続を取得する。
      * 取得済みだった場合は既存の接続を利用し、
      * 未取得だった場合は新規にコンテナから取得。
      * @return
      * @throws Exception
      */
    public Connection getConnection() throws Exception {
        //NamingException, SQLExceptionがスローされる
        try {
            if (connection == null || connection.isClosed()) {
                InitialContext initCtx = new InitialContext();
                DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/mydb");
                
                //データベース接続を取得する
                connection = ds.getConnection();
            }
        } catch (NamingException|SQLException e) {
            //もし例外が発生した場合はconnection=nullにし、
            //例外はそのまま送出する
            e.printStackTrace();
            connection = null;
            throw e;
        }
        
        return connection;
    }
    
    /**
     * 接続を閉じる。確実に接続を解放するため、
     * finallyでconnection=nullを行う。
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection = null;
        }
    }
    
    /**
     * PreparedStatementを返す
     * 
     * @param sql
     * @return
     * @throws Exception
     */
    public PreparedStatement getPreparedStatement(String sql) throws Exception {
        return getConnection().prepareStatement(sql);
    }
    
    /**
     * トランザクションのコミットを行う
     * 
     * @throws SQLException
     */
    public void commit() throws SQLException {
        connection.commit();
    }
    
    /**
     * トランザクションのロールバックを行う
     * 
     * @throws SQLException
     */
    public void rollback() throws SQLException {
        connection.rollback();
    }
    
    /**
     * 接続を閉じる
     */
    public void close() {
        System.out.println("close connection----------------------->");
        
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection = null;
        }
    }
}
