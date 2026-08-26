<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン成功 - minnaShare</title>
<jsp:include page = "common_head.jsp" /><%--ここでは共通のCSSや設定をインクルード --%>
</head>
<body>
    <h1>ログインが成功しました</h1>
    
    <%-- セッションスコープからスタッフ名を取得して表示 --%>
    <p>ようこそ、${loginStaff.staffName} さん！</p>
    
    <%-- ホーム画面（HomeServlet）へ遷移するボタン --%>
    <form action="/minnaShare/HomeServlet" method="get">
        <input type="submit" value="ホーム画面へ進む">
    </form>
</body>
</html>