<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="../../common/taglibs.jsp"%>
<head>
	<%@ include file="../common/header.jsp"%>
</head>

<body>
	<form action="${ctx }/admin/news/modify" method="post">
		<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0" align="center">
			<thead class="pn-lthead">
				<tr>
					<th>ID</th>
					<th>标题</th>
					<th>是否发布</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="pn-ltbody">
			<c:forEach items="${newses }" var="news">
				<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'" align="center">
					<td>${news.id }<input type="hidden" value="${news.id }" name="id" /></td>
					<td>${news.name }</td>
					<td>${news.publish ? "是" : "否"}</td>
					<td>
						<c:choose>
							<c:when test="${news.publish }">
								<a href="${ctx }/admin/news/modify/${news.id }?publish=false">下架</a>
							</c:when>
							<c:otherwise>
								<a href="${ctx }/admin/news/modify/${news.id }?publish=true">发布</a>
							</c:otherwise>
						</c:choose>
						<a href="${ctx }/admin/news/modify?newsId=${news.id }">编辑</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</form>
</body>
</html>