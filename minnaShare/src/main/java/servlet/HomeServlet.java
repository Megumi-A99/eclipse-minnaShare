package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Staff;


/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//ログイン成功の後にホーム画面に移る処理。
		//まずは不正ログイン防止のため、セッションスコープにユーザ情報が存在するか確認する。
		HttpSession session = request.getSession();
        Staff loginStaff = (Staff) session.getAttribute("loginStaff");
        
        //もしセッションスコープにインスタンスが無い(null)であればログイン画面へリダイレクト。
        if (loginStaff == null) {
            response.sendRedirect("/minnaShare/LoginServlet");
            return;
        }
      //セッションスコープからインスタンスが取得できていればホーム画面を出力するビューへ
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
        dispatcher.forward(request, response);
    }
}
