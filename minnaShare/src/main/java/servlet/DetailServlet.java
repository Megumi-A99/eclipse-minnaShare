// servlet/DetailServlet.java の新規作成
package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Article;
import model.ArticleDetailLogic; // 指定したarticleを取得するためのLogicクラス。

@WebServlet("/DetailServlet")
public class DetailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. パラメーター（記事ID）を取得
        // URLの "?id=1" のような部分から文字列として取得されるので、int型に変換します
        String idStr = request.getParameter("id");
        
        // エラーを防ぐため、もしidが空じゃなかったら処理をする
        if (idStr != null && !idStr.isEmpty()) {
            int articleId = Integer.parseInt(idStr);
            
            // 2. Logic（またはDAO）を使って該当する記事を取得
            ArticleDetailLogic logic = new ArticleDetailLogic();
            Article article = logic.execute(articleId);
            
            // 3. 取得した記事をリクエストスコープに保存
            // 見つかった場合のみ保存します
            if (article != null) {
                request.setAttribute("article", article);
            }
        }
        
        // 4. 詳細画面（detail.jsp）へフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detail.jsp");
        dispatcher.forward(request, response);
    }
}

