<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>图书管理CRUD-DEMO</title>
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


			<h3 id="add-book-head">查看作者信息</h3>
			<div id="inputForm">
				<s:form theme="simple" action="commitUpdatedAuthor" id="bookAdded">
					<label class="addBookLbl" for="authorAdded_newAuthor_authorID">编号：</label>
					<s:textfield name="newAuthor.authorID" readonly="true" />
					<br />
					<br />

					<label class="addBookLbl" for="authorAdded_newAuthor_name">姓名：</label>
					<s:textfield name="newAuthor.name" readonly="true" />
					<s:fielderror>
						<s:param>newAuthor.name</s:param>
					</s:fielderror>
					<br />
					<br />

					<label class="addBookLbl" for="authorAdded_newAuthor_age">年龄：</label>
					<s:textfield name="newAuthor.age" readonly="true" />
					<s:fielderror>
						<s:param>newAuthor.age</s:param>
					</s:fielderror>
					<br />
					<br />

					<label class="addBookLbl" for="authorAdded_newAuthor_country">国家：</label>
					<s:textfield name="newAuthor.country" readonly="true" />
					<s:fielderror>
						<s:param>newAuthor.country</s:param>
					</s:fielderror>
					<br />
					<br />

					<a id="back2" href="#" onclick="JavaScript:history.back(-1);">返回</a>

				</s:form>

			</div>
		</div>
		<div id="footer">哈工大软件工程Lab2</div>
	</div>
</body>
</html>