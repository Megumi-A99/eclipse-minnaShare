<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>minnaShare - ログイン</title>
<jsp:include page = "/WEB-INF/jsp/common_head.jsp" /><%--ここでは共通のCSSや設定をインクルード --%>
</head>
<body>
    <h1>業務ナレッジ共有 minnaShare</h1>
    
    <%-- エラーメッセージがある場合は表示 --%><%--p357にJSTLのCoreタグライブラリの使い方あるからたまに見てね --%>
    <c:if test="${not empty errorMsg}">
        <p style="color: red; font-weight: bold;">${errorMsg}</p>
    </c:if>

    <form action="LoginServlet" method="post">
        <p>ユーザーID (半角英数字20文字以内):<br>
        <input type="text" name="userId" maxlength="20" pattern="^[a-zA-Z0-9]+$" required></p>
        
        <p>パスワード (半角英数字20文字以内):<br>
        <input type="password" name="password" minlength="8" maxlength="20" pattern="^[a-zA-Z0-9]{8,20}$" required></p>
        
        <input type="submit" value="ログイン">
    </form>
</body>
</html>