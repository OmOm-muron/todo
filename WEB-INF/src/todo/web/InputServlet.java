
package todo.web;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import todo.dto.Todo;

/**
 *
 * @author OmOm-muron
 */
@WebServlet("/todo/input")
public class InputServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest req, HttpServletResponse rsp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //voの作成
        Todo dto = new Todo();
        
        //新規作成であることを判別するためid=0
        dto.setId(0);
        
        //DTOをリクエスト属性へバインド
        req.setAttribute("dto",dto);
        
        //詳細画面を表示する
        RequestDispatcher rd = req.getRequestDispatcher("/jsp/detail.jsp");
        rd.forward(req,rsp);
    }
    
    protected void doPost(HttpServletRequest req, HttpServletResponse rsp)
            throws ServletException, IOException {
        doGet(req,rsp);
    }
}
