package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.LoginLogic;
import model.Staff;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // GETリクエスト（ログイン画面への遷移）
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }

    // POSTリクエスト（ログイン処理）
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userId = request.getParameter("userId");
        String pass = request.getParameter("password");

        // DAOを使ってデータベース照合。直接接続せずOBであるLoginLogicからDAOに繋げる。
        LoginLogic bo = new LoginLogic();
        Staff loginStaff = bo.execute(userId, pass);

        if (loginStaff != null) {
            // ログイン成功：セッションスコープにスタッフ情報を保存
            HttpSession session = request.getSession();
            session.setAttribute("loginStaff", loginStaff);
            
            // ログインに成功したらログイン成功画面にフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/loginSuccess.jsp");
            dispatcher.forward(request, response);
            //もともとはそのままホーム画面に遷移する予定だったけど無し　response.sendRedirect("/(あなたのプロジェクト名)/HomeServlet");
        } else {
            // ログイン失敗：エラーメッセージをリクエストスコープに保存して元の画面へ
            request.setAttribute("errorMsg", "IDまたはパスワードが間違っています");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
