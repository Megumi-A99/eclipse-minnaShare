package servlet;
/*ここではhome.jspから送られてきたcategoryを受け取り、
 * DAOを利用し投稿内容のリスト取得、JSPへフォワードする。*/

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Article;
import model.ArticleListLogic;

@WebServlet("/ArticleListServlet")
public class ArticleListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. パラメーター（カテゴリー名）を取得
        String category = request.getParameter("category");
        
        // 2. DAOを使って該当するカテゴリーの記事リストを取得
        ArticleListLogic articleListLogic = new ArticleListLogic();
        List<Article> articleList = articleListLogic.execute(category);
        
        // 3. 取得したリストをリクエストスコープに保存
        request.setAttribute("articleList", articleList);
        
        // （おまけ）画面表示用にカテゴリーの日本語名も保存しておくと便利です
        //?の部分については三項演算子という。条件式 ? trueの場合の値 : falseの場合の値。if文に近い意味がある。
        String categoryName = "procedure".equals(category) ? "手続きに関する情報" : "業務知識に関する情報";
        request.setAttribute("categoryName", categoryName);
        
        // 4. 一覧画面（articleList.jsp）へフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/articleList.jsp");
        dispatcher.forward(request, response);
    }
}