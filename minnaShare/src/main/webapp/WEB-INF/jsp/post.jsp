<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規投稿 - minnaShare</title>
<jsp:include page = "common_head.jsp" /><%--ここでは共通のCSSや設定をインクルード --%>
</head>
<body>
    <h1>ナレッジの新規投稿</h1>
    
    <%-- セッションから取得したスタッフ名を表示 --%>
    <p>投稿者：${loginStaff.staffName} さん</p>
    
	<%-- 入力内容に以上があってPostServletからエラーメッセージがある場合のみ表示 --%>
	<c:if test="${not empty errorMessages}">
    		<ul style="color: red;">
        		<c:forEach var="error" items="${errorMessages}">
            		<li><c:out value="${error}" /></li>
    			</c:forEach>
    		</ul>
	</c:if>
	
    <form action="/minnaShare/PostServlet" method="post">
    <input type="hidden" name="action" value="confirm"><%-- サーブレットに「確認処理」であることを伝える目印。あとで投稿前の確認画面を作るため--%>
        
        <p>カテゴリー：<br>
            <input type="radio" name="category" value="procedure" required> 手続きに関すること
            <input type="radio" name="category" value="knowledge"> 業務知識に関すること
        </p>

        <p>重要度（基準：高=必須知識, 中=知っていると楽, 低=時間がある時にみてほしい）：<br>
            <input type="radio" name="importance" value="高" required> 高
            <input type="radio" name="importance" value="中"> 中
            <input type="radio" name="importance" value="低"> 低
        </p>

        <p>タイトル (100文字以内)：<br>
            <input type="text" name="title" maxlength="100" style="width: 400px;" required>
        </p>

        <p>本文 (2000文字以内)：<br>
            <textarea name="content" maxlength="2000" rows="10" cols="60" required></textarea>
        </p>

        <input type="submit" value="投稿する">
    </form>
    
    <br>
    <a href="/minnaShare/HomeServlet">ホームへ戻る</a>
</body>
</html>