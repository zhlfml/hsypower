<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="../../common/taglibs.jsp"%>
<head>
	<%@ include file="../common/header.jsp"%>
</head>

<body>
	<form action="${ctx }/admin/channel/saveSort" method="post">
		<table class="pn-ltable" width="100%" cellspacing="1" cellpadding="0" border="0" align="center">
			<thead class="pn-lthead">
				<tr>
					<th>ID</th>
					<th>栏目名称</th>
					<th>访问路径</th>
					<th>排序</th>
					<th>是否显示</th>
					<th>导航栏</th>
					<th>左侧栏</th>
					<th>底部栏</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody class="pn-ltbody">
			<c:forEach items="${channels }" var="channel">
				<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'" onmouseover="this.bgColor='#eeeeee'" align="center">
					<td>${channel.id }<input type="hidden" value="${channel.id }" name="id" /></td>
					<td>${channel.name }</td>
					<td>${channel.path }</td>
					<td><input type="text" name="sort" value="${channel.sort }" style="width:40px; border:1px solid #7e9db9"" /></td>
					<td>${channel.display ? "是" : "否"}</td>
					<td>${channel.navigate ? "是" : "否" }</td>
					<td>${channel.leftSide ? "是" : "否" }</td>
					<td>${channel.bottom ? "是" : "否" }</td>
					<td>
						<a href="${ctx }/admin/channel/modify?channelId=${channel.id }">修改</a>
						<a href="${ctx }${channel.path }" target="_blank">访问</a>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<div align="center" style="margin-top:15px;">
			<input type="submit" value="保存排列顺序" />
		</div>
	</form>
</body>
</html>