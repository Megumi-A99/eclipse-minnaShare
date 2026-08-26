<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- ここでは投稿する前の【確認画面にあたるページ】 -->
<html>
<head>
<meta charset="UTF-8">
<title>投稿内容の確認 - minnaShare</title>
<jsp:include page = "common_head.jsp" /><%--ここでは共通のCSSや設定をインクルード --%>
</head>
<body>
    <h1>以下の内容で投稿しますか？</h1>
    
    <%-- セッションから「newArticle」を取り出して表示 --%>
    <table border="1">
        <tr>
            <th>カテゴリー</th>
            <td>
            <%-- この下にはJSTLのCタグの<c:choose>タグで分岐を作ってます--%>
                <c:choose>
                    <c:when test="${newArticle.category == 'procedure'}">手続きに関すること</c:when>
                    <c:otherwise>業務知識に関すること</c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <th>重要度</th>
            <td>${newArticle.importance}</td>
        </tr>
        <tr>
            <th>タイトル</th>
            <td><c:out value="${newArticle.title}" /></td>
        </tr>
        <tr>
            <th>本文</th>
            <%-- 改行をそのまま表示するために preタグ を使用 --%>
            <td><pre><c:out value="${newArticle.content}" /></pre></td>
        </tr>
    </table>

    <br>

    <%-- 修正ボタン（ブラウザの「戻る」機能を使うと入力内容が消えずに戻れます） --%>
    <button type="button" onclick="history.back()">修正する</button>

    <%-- 投稿ボタン（action=execute を送る） --%>
    <form action="/minnaShare/PostServlet" method="post" style="display:inline;">
        <input type="hidden" name="action" value="execute">
        <input type="submit" value="この内容で投稿する">
    </form>

</body>
</html>