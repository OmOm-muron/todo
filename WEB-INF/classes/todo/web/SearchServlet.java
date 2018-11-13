
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
 * Search Function. 
 * This servlet searches tasks, and forward to result list.
 */

@WebServlet(urlPatterns={"/todo/search"})
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse rsp) throws ServletException,IOException {
        req.setCharacterEncoding("UTF-8");

        //DAO取得
        try (TodoDAO dao = new TodoDAO()) {
            //タスクのリストを一覧で取得し、リクエスト属性へ格納する
            List<Todo> list = dao.todoList();
            req.setAttribute("todoList", list);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        
        //検索一覧を表示する
        RequestDispatcher rd = req.getRequestDispatcher("/jsp/search.jsp");
        rd.forward(req, rsp);
    }
        
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse rsp) throws ServletException,IOException {
        doGet(req,rsp);
    }
}
