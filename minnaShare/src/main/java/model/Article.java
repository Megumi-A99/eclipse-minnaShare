package model; //投稿内容を表すJavabeans。p418のエンティティーに当たる

import java.io.Serializable;
import java.sql.Date;

public class Article implements Serializable {
    private int articleId; //
    private Date postDate; //投稿日付
    private String userId; //投稿した人を判別するためユーザーID
    private String category; //「手続き」「業務知識」のどちらに属するか
    private String title; //投稿内容のタイトル
    private String importance; //投稿内容の重要レベル「高」「中」「低」
    private String content; //本文

    public Article() {}

    // 新規投稿時に使うコンストラクタ（articleIdは自動採番のため不要）
    public Article(Date postDate, String userId, String category, String title, String importance, String content) {
        this.postDate = postDate;
        this.userId = userId;
        this.category = category;
        this.title = title;
        this.importance = importance;
        this.content = content;
    }

    // DBから取得する時に使うコンストラクタ（全項目あり）
    public Article(int articleId, Date postDate, String userId, String category, String title, String importance, String content) {
        this.articleId = articleId;
        this.postDate = postDate;
        this.userId = userId;
        this.category = category;
        this.title = title;
        this.importance = importance;
        this.content = content;
    }

    // --- 以下、各フィールドのgetter/setterを作成してください ---
    public int getArticleId() { return articleId; }
    public void setArticleId(int articleId) { this.articleId = articleId; }
    public Date getPostDate() { return postDate; }
    public void setPostDate(Date postDate) { this.postDate = postDate; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getImportance() { return importance; }
    public void setImportance(String importance) { this.importance = importance; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
}