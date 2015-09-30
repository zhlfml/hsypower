<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="../../common/taglibs.jsp"%>
<head>
	<%@ include file="../common/header.jsp"%>
</head>

<body>
	<form action="${ctx }/admin/qualificationFile/modify" method="post">
		<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0" align="center">
			<thead class="pn-lthead">
				<tr>
					<th>ID</th>
					<th>资质文件名称</th>
					<th>资质文件类名</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="pn-ltbody">
			<c:forEach items="${qualificationFiles }" var="qualificationFile">
				<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'" align="center">
					<td>${qualificationFile.id }</td>
					<td>${qualificationFile.name }</td>
					<td>${qualificationFile.qualificationFileCategory.name }</td>
					<td><a href="${ctx }/admin/qualificationFile/modify?qualificationFileId=${qualificationFile.id }">编辑</a></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</form>
</body>
</html>