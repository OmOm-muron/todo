
package todo.web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import todo.dto.Todo;
import todo.dao.TodoDAO;

/**
 *
 * @author OmOm-muron
 * 登録処理を行う
 */
@WebServlet("/todo/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest req, HttpServletResponse rsp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //リクエストパラメータを受け取り、DTOに格納する準備をする
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String task = req.getParameter("task");
        String inputLimitdate = req.getParameter("limitdate");
        String userid = req.getParameter("userid");
        int status = Integer.parseInt(req.getParameter("status"));
            
        //DTOへ格納する
        Todo dto = new Todo();
        dto.setId(id);
        dto.setTitle(title);
        dto.setTask(task);
        dto.setInputLimitdate(inputLimitdate);
        dto.setUserid(userid);
        dto.setStatus(status);
        
        String message = "";
        try (TodoDAO dao = new TodoDAO()) {
            //更新または登録処理を行う
            //id=0のときは新規登録、id>0のときは更新
            if(id == 0) {
                dao.registerInsert(dto);
                message = "タスクの新規登録処理が完了しました。";
            } else {
                dao.registerUpdate(dto);
                message = "タスク[ " + id + " ]の更新が完了しました。";
            }
            setMessage(req,message);
        } catch (Exception e) {
            throw new ServletException(e);
        }
            
        //登録完了→一覧画面を表示
        RequestDispatcher rd = req.getRequestDispatcher("/search");
        rd.forward(req, rsp);
    }
        
    /**
     * setMessageを実装
     * （JSPで表示するメッセージを設定するメソッド）
     * 
     * @param req
     *     サーブレットリクエスト
     * @param message
     *     メッセージ文字列
     */
    protected void setMessage(HttpServletRequest req,String message) {
        req.setAttribute("message", message);
    }
}
