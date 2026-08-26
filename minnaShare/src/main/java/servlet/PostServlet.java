package servlet; //新規投稿画面を要求するgetリクエストと
				//投稿するpostリクエストの受付をするコントローラー

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Article;
import model.PostLogic;
import model.Staff;


/**
 * Servlet implementation class Post
 */
@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// home.jspから「新規投稿をする」を押したあと、投稿画面にフォワードをする
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/post.jsp");
		dispatcher.forward(request, response);
	}
	
	//ここから投稿画面から投稿内容をARTICLEテーブルに保存する処理を受け付ける
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1. リクエストパラメータのエンコーディング指定
        request.setCharacterEncoding("UTF-8");
        
     // 隠しパラメータ「action」を取得して、処理を分岐させる!
        String action = request.getParameter("action");
        /*---【確認画面】入力画面から来た場合---*/
        //1. リクエストパラメーターのactionの値チェック
        if("confirm".equals(action)){

        // 2. フォームから入力値を取得（post.jspのname属性と一致させます）
        String category = request.getParameter("category");
        String importance = request.getParameter("importance");
        String title = request.getParameter("title");
        String content = request.getParameter("content");

        // 3. セッションスコープから「投稿者のID」を取得
        HttpSession session = request.getSession();
        Staff loginStaff = (Staff) session.getAttribute("loginStaff");
        
        // （安全対策）万が一セッションが切れていた場合はログイン画面へ戻す
        if (loginStaff == null) {
            response.sendRedirect("/minnaShare/LoginServlet");
            return;
        }
        
        String userId = loginStaff.getUserId();

        // 4. 現在の日付を取得 (java.sql.Date を使用)
        long currentTimeMillis = System.currentTimeMillis();
        java.sql.Date postDate = new java.sql.Date(currentTimeMillis);

        // 5. 取得したすべての情報を Article インスタンスにまとめる
        Article newArticle = new Article(postDate, userId, category, title, importance, content);
        
        //(ここからは防御準備)バックエンド処理において 本当に正しい情報が送られてきたのか確認する
        //まだうまくエラー文の表示などができないので、post.jspとともに次回修正する。
        //1. フォームからのデータ受け取り
        String title2 = request.getParameter("title");
        String content2 = request.getParameter("content");

        // 2. エラーメッセージを格納するリストを準備
        List<String> errorMessages = new ArrayList<>();

        // 3. タイトルのチェック（未入力、および20文字以内か）
        if (title2 == null || title2.isEmpty()) {
            errorMessages.add("タイトルを入力してください。");
        } else if (title2.length() > 20) {
            errorMessages.add("タイトルは20文字以内で入力してください。");
        }

        // 4. 本文のチェック（未入力か）
        if (content2 == null || content2.isEmpty()) {
            errorMessages.add("本文を入力してください。");
        }

        // 5. エラーの有無で分岐
        if (errorMessages.size() > 0) {
            // 【エラーがある場合】
            // エラーメッセージをリクエストスコープに保存して、元の投稿画面(post.jsp)に戻す
            request.setAttribute("errorMessages", errorMessages);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/post.jsp");
            dispatcher.forward(request, response);
            return; // ここで処理を終了させる
        }

        // 【エラーがない場合】
        // これまで通り、セッションスコープに保存して確認画面(postConfirm.jsp)へ進む処理を書く
        
        //ここで確認画面に移る前にsessionスコープに投稿内容を一時保存
        session.setAttribute("newArticle", newArticle);
        //【確認画面】へフォワード(postConfirm.jspへ)
        jakarta.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/postConfirm.jsp");
        dispatcher.forward(request, response);
        }
        /*---ここからは【実行処理】確認画面から来たらこっちの処理へ！---*/
	        else if("execute".equals(action)) {
	        //1. sessionスコープから一時保存していた投稿内容を取り出す。
	        	HttpSession session = request.getSession();
	        	Article article = (Article)session.getAttribute("newArticle");
	        	
	        //2. ロジッククラスを使ってDBへ登録
	        	if (article != null) {
	        PostLogic logic = new PostLogic();
	        boolean result = logic.execute(article);
	
	        // 3. 処理結果に応じた画面遷移
		        if (result) {
		            // 成功時：sessionスコープに一時保存したnewArticleを削除。
		        		//その後ホーム画面へリダイレクト
		        		session.removeAttribute("newArticle");
		            response.sendRedirect("/minnaShare/HomeServlet");
		        } else {
		            // 失敗時：エラーメッセージをリクエストスコープに保存して投稿画面へフォワード。
		            request.setAttribute("errorMsg", "投稿の保存に失敗しました。");
		            jakarta.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/post.jsp");
		            dispatcher.forward(request, response);
		        }
		    }
	
		
		
	 }
	}
}
