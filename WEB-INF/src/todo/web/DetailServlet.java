
package todo.web;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import todo.dao.TodoDAO;
import todo.dto.Todo;

/**
 *
 * @author OmOm-muron
 */
@WebServlet("/todo/detail")
public class DetailServlet extends HttpServlet {
    private static final long seriaiVersionUID = 1L;
    
    protected void doGet (HttpServletRequest req, HttpServletResponse rsp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //リクエストパラメータから選択したタスクIDを取得する
        String paramId = req.getParameter("id");
        
        //Stringからintへ変換し、daoで処理を行う 更新対象のタスクを1件取得する
        Todo dto;
        try (TodoDAO dao = new TodoDAO()) {
            //intへ変換 ※NumberFormatExceptionの可能性あり
            int id = Integer.parseInt(paramId);
            
            //タスク詳細結果を取得
            dto = dao.detail(id);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        
        //タスク1件のvoをリクエスト属性へバインド
        req.setAttribute("dto", dto);
        
        //画面を返す
        //検索一覧を表示する
        RequestDispatcher rd = req.getRequestDispatcher("/jsp/detail.jsp");
        rd.forward(req, rsp);
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse rsp)
            throws ServletException, IOException {
        doGet(req,rsp);
    }
}
