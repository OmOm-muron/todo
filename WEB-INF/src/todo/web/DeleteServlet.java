
package todo.web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import todo.dao.TodoDAO;

/**
 *
 * @author OmOm-muron
 * 削除を行う
 */
@WebServlet("/todo/delete")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doPost(HttpServletRequest req, HttpServletResponse rsp) 
            throws ServletException, IOException {
        String paramId = req.getParameter("id");
        
        try (TodoDAO dao = new TodoDAO()) {
            //intへ変換 ※NumberFormatExceptionの可能性あり
            int id = Integer.parseInt(paramId);
            
            //daoで処理
            int result = dao.delete(id);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        
        setMessage(req,"タスク[ " + paramId + " ]の削除処理が完了しました。");
        
        //画面を返す
        RequestDispatcher rd = req.getRequestDispatcher("/todo/search");
        rd.forward(req,rsp);
    }
    
    /**
     * JSPで表示するメッセージを設定
     * 
     * @param req
     * @param message
     */
    protected void setMessage(HttpServletRequest req, String message) {
        req.setAttribute("message", message);
    }
}
