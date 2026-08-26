package dao;//投稿内容をARTICLEテーブルにINSERT文で保存する

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Article;

public class ArticleDAO {
    // データベース接続情報
    private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/minnaShare";
    private final String DB_USER = "sa";
    private final String DB_PASS = "";

    //投稿内容の保存を行うメソッド
    public boolean insert(Article article) {
        // Tomcat用：JDBCドライバの読み込み
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
            // INSERT文の準備 (article_id は AUTO_INCREMENT なので指定しません)
            String sql = "INSERT INTO article (post_date, user_id, category, title, importance, content) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pStmt = conn.prepareStatement(sql);

            // Articleインスタンスから値を取り出し、? にセットする
            pStmt.setDate(1, article.getPostDate());//投稿日付
            pStmt.setString(2, article.getUserId());//ユーザID
            pStmt.setString(3, article.getCategory());//投稿カテゴリ
            pStmt.setString(4, article.getTitle());//タイトル
            pStmt.setString(5, article.getImportance());//重要度
            pStmt.setString(6, article.getContent());//本文

            // INSERT文を実行（executeUpdate は変更された行数を返します）
            int result = pStmt.executeUpdate();
            
            // 1行追加されていれば成功(true)、そうでなければ失敗(false)を返す
            return result == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
 // ArticleDAOからDBに接続して条件に一致した投稿内容をList型で返すメソッド
    public List<Article> findByCategory(String category) {
        List<Article> articleList = new ArrayList<>();
        
        // POST_DATEの降順（新しい順）で取得するSQL
        String sql = "SELECT * FROM Article WHERE CATEGORY = ? ORDER BY POST_DATE DESC";
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("JDBCドライバを読み込めませんでした");
        }
        
        /* データベース接続処理 */
        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)){
        		 
        		PreparedStatement pStmt = conn.prepareStatement(sql);
            pStmt.setString(1, category);//ここはsql文のプレースホルダ(？の部分)に実引数として渡されるcategoryが格納される。
            ResultSet rs = pStmt.executeQuery();

            while (rs.next()) {
                Article article = new Article();
                // ※ご自身のArticle.javaのフィールド名に合わせて大文字小文字を調整してください
                article.setArticleId(rs.getInt("ARTICLE_ID"));
                article.setPostDate(rs.getDate("POST_DATE"));
                article.setUserId(rs.getString("USER_ID"));
                article.setCategory(rs.getString("CATEGORY"));
                article.setTitle(rs.getString("TITLE"));
                article.setImportance(rs.getString("IMPORTANCE"));
                article.setContent(rs.getString("CONTENT"));
                
                articleList.add(article);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return articleList;
    }
    
 //(8/20) 新しいメソッドを追加。投稿されたコメントのタイトルを押し、詳細文を取得して返すためのメソッド
    public Article findById(int articleId) {
        Article article = null; // 見つからなかった場合はnullを返すように初期化
        
        // IDを条件に1件だけ取得するSQL
        String sql = "SELECT * FROM Article WHERE ARTICLE_ID = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)/* データベース接続処理 */;
             PreparedStatement pStmt = conn.prepareStatement(sql)) {

            pStmt.setInt(1, articleId); // ?にIDをセット
            ResultSet rs = pStmt.executeQuery();

            // 1件だけ取得できれば良いので while ではなく if を使用
            if (rs.next()) {
                article = new Article();
                article.setArticleId(rs.getInt("ARTICLE_ID"));
                article.setPostDate(rs.getDate("POST_DATE"));
                article.setUserId(rs.getString("USER_ID"));
                article.setCategory(rs.getString("CATEGORY"));
                article.setTitle(rs.getString("TITLE"));
                article.setImportance(rs.getString("IMPORTANCE"));
                article.setContent(rs.getString("CONTENT"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return article;
    }


}