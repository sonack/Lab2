<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>图书管理CRUD-DEMO</title>
<style>
#intro {
	line-height: 200px;
	text-align: center;
}

#content {
	background-color: #fff;
}
</style>
<script type="text/javascript">
	window.onload = function() {
		var cb = document.getElementById("searchBooks_searchAuthorName");
		cb.onclick = function() {
			cb.value = "";
		}
	}
</script>
</head>
<body>

	<h2 id="headline">
		<a href="index.jsp">图书管理CRUD-DEMO</a>
	</h2>
	<div id="main">
		<div id="header">
			<ul>
				<li><a href="<s:url action='list' />">浏览全部图书</a></li>
				<li><a href="<s:url action='listAuthors' />">浏览全部作者</a></li>
				<li><a href="<s:url action='searchInput' />">查询指定作者</a></li>
				<li><a href="<s:url action='addBookForm' />">添加图书信息</a></li>
			</ul>
		</div>
		<div id="content">
			<h3 id="search-head">查询指定作者的全部书目</h3>
			<s:form action="searchBooks" theme="simple">
				<label id="searchLbl" for="searchBooks_searchAuthorName">作者名称：</label>
				<s:textfield name="searchAuthorName" value="如马龙..." />
				<br />
				<s:submit value="查询" />
			</s:form>
		</div>
		<div id="footer">哈工大软件工程Lab2</div>
	</div>
</body>
</html>