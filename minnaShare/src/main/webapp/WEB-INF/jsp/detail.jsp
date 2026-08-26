<%-- WEB-INF/jsp/detail.jsp の新規作成 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html><%--http://java.sun.com/jsp/jstl/core(Apache9あたり) jakarta.tags.core(Apache10以降)--%>
<html>
<head>
<meta charset="UTF-8">
<title>記事詳細 - minnaShare</title>
<jsp:include page = "common_head.jsp" /><%--ここでは共通のCSSや設定をインクルード --%>
<style>
    /* 詳細画面を見やすくするための簡単なCSS */
    .detail-container {
        width: 80%;
        max-width: 800px;
        margin: 20px auto; /* 画面の中央に配置 */
        padding: 20px;
        border: 1px solid #ccc;
        border-radius: 8px;
        background-color: #fff;
    }
    .detail-title {
        font-size: 1.5em;
        font-weight: bold;
        border-bottom: 2px solid #333;
        padding-bottom: 10px;
        margin-bottom: 20px;
    }
    .detail-info {
        background-color: #f9f9f9;
        padding: 10px;
        margin-bottom: 20px;
        border-radius: 4px;
        color: #555;
    }
    .detail-content {
        line-height: 1.6;
        white-space: pre-wrap; /* 改行をそのまま表示する魔法のCSSプロパティ */
    }
</style>
</head>
<body>
    <div class="detail-container">
        <%-- articleが空（URLに直接アクセスされた等）の場合はエラーメッセージを表示 --%>
        <c:choose>
            <c:when test="${empty article}">
                <h2>記事が見つかりませんでした。</h2>
                <p>指定された記事は存在しないか、削除された可能性があります。</p>
            </c:when>
            
            <%-- articleが取得できている場合は詳細を表示 --%>
            <c:otherwise>
                <div class="detail-title">
                    <c:out value="${article.title}" />
                </div>
                
                <div class="detail-info">
                    <%-- カテゴリー名（英字）を日本語に変換して表示 --%>
                    カテゴリー：
                    <c:choose>
                        <c:when test="${article.category == 'procedure'}">手続きに関する情報</c:when>
                        <c:when test="${article.category == 'knowledge'}">業務知識に関する情報</c:when>
                        <c:otherwise><c:out value="${article.category}" /></c:otherwise>
                    </c:choose>
                    <br>
                    重要度：<c:out value="${article.importance}" /><br>
                    投稿者ID：<c:out value="${article.userId}" /><br>
                    投稿日時：<c:out value="${article.postDate}" />
                </div>
                
                <div class="detail-content"><c:out value="${article.content}" /></div>
            </c:otherwise>
        </c:choose>
        
        <br><br>
        <%-- JavaScriptを使って「前の画面（一覧）」に戻るボタン --%>
        <button type="button" onclick="history.back()">一覧に戻る</button>
        
    </div>
    <%-- 念のためホームへ戻るリンクも設置 --%>
    <a href="/minnaShare/HomeServlet">ホームに戻る</a>
</body>
</html>

