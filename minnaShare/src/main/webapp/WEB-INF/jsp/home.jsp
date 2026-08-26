<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホーム　-minnaShare</title>
<jsp:include page = "common_head.jsp" /><%--ここでは共通のCSSや設定をインクルード --%>
</head>
<body>
	<h1>ナレッジ共有ホーム</h1>
	<p>${loginStaff.staffName }さん、お疲れ様です！</p>
	
	<hr>
	<h2>閲覧するカテゴリーを選ぶ</h2>
	<ul>
		<li><a href="/minnaShare/ArticleListServlet?category=procedure">手続きに関する情報</a></li>
		<li><a href="/minnaShare/ArticleListServlet?category=knowledge">業務知識に関する情報</a></li>
	</ul>
	</hr>
	
	<h2>メニュー</h2>
	<ul>
		<li><a href="PostServlet">新規投稿をする</a></li>
	</ul>
	
	<br>
	<%--ログアウト処理を行うサーブレットへ遷移 --%>
	<form action = "/minnaShare/LogoutServlet" method = "get">
		<input type = "submit" value = "ログアウトする">
	</form>
	

</body>
</html>