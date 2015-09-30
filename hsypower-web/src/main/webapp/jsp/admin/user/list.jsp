<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="../../common/taglibs.jsp"%>
<head>
	<%@ include file="../common/header.jsp"%>
</head>

<body>
	<form action="${ctx }/admin/user/modify" method="post">
		<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0" align="center">
			<thead class="pn-lthead">
				<tr>
					<th>ID</th>
					<th>用户名</th>
					<th>是否启用</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="pn-ltbody">
			<c:forEach items="${users }" var="user">
				<c:if test="${user.id != sessionScope.user.id }">
				<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'" align="center">
					<td>${user.id }<input type="hidden" value="${user.id }" name="id" /></td>
					<td>${user.name }</td>
					<td>${user.activity ? "是" : "否"}</td>
					<td>
						<c:choose>
							<c:when test="${user.activity }">
								<a href="${ctx }/admin/user/modify?userId=${user.id }&activity=false">禁用</a>
							</c:when>
							<c:otherwise>
								<a href="${ctx }/admin/user/modify?userId=${user.id }&activity=true">启用</a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				</c:if>
			</c:forEach>
			</tbody>
		</table>
	</form>
</body>
</html>