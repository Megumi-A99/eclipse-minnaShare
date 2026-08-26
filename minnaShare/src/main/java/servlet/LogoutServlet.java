package servlet;

import java.io.IOException;

// ▼すべて jakarta に変更しています
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //ここではログアウトを行う処理。まずはユーザ情報が入っているセッションスコープを破棄する。    
    	HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();//セッションスコープを破棄するメソッド
        }

        //ログアウト完了画面を表示するビューにフォワードする。
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/logout.jsp");
        dispatcher.forward(request, response);
    }
}