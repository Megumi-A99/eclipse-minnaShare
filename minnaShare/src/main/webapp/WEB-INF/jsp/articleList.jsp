<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${categoryName}一覧 - minnaShare</title>
<jsp:include page = "common_head.jsp" /><%--ここでは共通のCSSや設定をインクルード --%>
<style>
    /* 長方形の枠のデザイン（CSS） */
    .article-card {
        border: 2px solid #ccc; /* 枠線 */
        border-radius: 8px;     /* 角を少し丸くする */
        padding: 15px;          /* 枠の内側の余白 */
        margin-bottom: 20px;    /* 下の枠との間隔 */
        width: 80%;             /* 画面幅に対する割合 */
        max-width: 600px;       /* 最大幅 */
        background-color: #f9f9f9; /* 背景色を少しグレーに */
    }
    
    .article-title {
        font-size: 1.2em;       /* タイトルの文字を少し大きく */
        font-weight: bold;
        margin-bottom: 10px;
    }
    
    .article-info {
        color: #555;            /* タイトル以外は少し落ち着いた色に */
        margin-bottom: 5px;
    }
</style>
</head>
<body>
    <h1><c:out value="${categoryName}" />一覧</h1>
    
    <%-- リストが空（0件）の場合の表示 --%>
    <c:if test="${empty articleList}">
        <p>現在、このカテゴリーの投稿はありません。</p>
    </c:if>

    <%-- 取得した投稿リストをループで表示 --%>
    <c:forEach var="article" items="${articleList}">
        <div class="article-card">
            <%-- ① タイトル（DetailServletへIDを渡すリンクにする） --%>
            <div class="article-title">
                <a href="/minnaShare/DetailServlet?id=${article.articleId}">
                    <c:out value="${article.title}" />
                </a>
            </div>
            
            <%-- ② 重要度 --%>
            <div class="article-info">
                重要度：<c:out value="${article.importance}" />
            </div>
            
            <%-- ③ ユーザーID --%>
            <div class="article-info">
                投稿者ID：<c:out value="${article.userId}" />
            </div>
            
            <%-- ④ 投稿日付 --%>
            <div class="article-info">
                投稿日：<c:out value="${article.postDate}" />
            </div>
        </div>
    </c:forEach>
    
    <br>
    <a href="/minnaShare/HomeServlet">ホームに戻る</a>

</body>
</html>
