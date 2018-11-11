
package todo.dao;

import java.sql.*;
import java.util.*;
import todo.dto.Todo;

/**
 *
 * @author banba
 */
public class TodoDAO extends DAO {
    public List<Todo> todoList()  throws Exception {
        List<Todo> returnList = new ArrayList<Todo>();
        
        String sql = "SELECT td.id, td.title, td.task, td.limitdate, td.lastupdate, td.userid, stts.label, td.status, td.filename "
                + "FROM todo_list td LEFT JOIN status_list stts ON stts.status = td.status";
        
        //プリペアドステートメントを取得し、実行SQLを渡す
        PreparedStatement statement = getPreparedStatement(sql);
        
        //SQLを実行してその結果を取得する
        ResultSet rs = statement.executeQuery();
        
        //検索結果の行数分フェッチを行い、取得結果をTodoインスタンスへ格納する
        while (rs.next()) {
            Todo dto = new Todo();
            
            //クエリ結果をVOへ格納（あらかじめクエリ結果とdtoの変数名は一致させてある）
            dto.setId(rs.getInt("id"));
            dto.setTitle(rs.getString("title"));
            dto.setTask(rs.getString("task"));
            dto.setLimitdate(rs.getTimestamp("limitdate"));
            dto.setLastupdate(rs.getTimestamp("lastupdate"));
            dto.setUserid(rs.getString("userid"));
            dto.setLabel(rs.getString("label"));
            dto.setFilename(rs.getString("filename"));
            
            returnList.add(dto);
        }
        
        return returnList;
    }
}
