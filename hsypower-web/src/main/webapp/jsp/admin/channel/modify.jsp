<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="../../common/taglibs.jsp"%>
<head>
	<%@ include file="../common/header.jsp"%>
	<script type="text/javascript">
	$(document).ready(function() {
		$('input.return').click(function() {
			location.href = '${ctx }/admin/channel/list';
		});
	});
	</script>
</head>

<body>
	<form:form commandName="channel">
		<form:hidden path="id" />
		<table class="pn-ftable" width="100%" cellspacing="1" cellpadding="2" border="0">
			<tbody>
				<tr>
					<td class="pn-flabel pn-flabel-h" width="10%">栏目名称:</td>
					<td class="pn-fcontent" width="90%" colspan="3"><form:input path="name" /></td>
				</tr>
				<tr>
					<td class="pn-flabel pn-flabel-h" width="10%">访问路径:</td>
					<td class="pn-fcontent" width="90%" colspan="3"><form:input path="path" readonly="true" /></td>
				</tr>
				<tr>
					<td class="pn-flabel pn-flabel-h" width="10%">排序:</td>
					<td class="pn-fcontent" width="90%" colspan="3"><form:input path="sort" /></td>
				</tr>
				<tr>
					<td class="pn-flabel pn-flabel-h" width="10%">是否显示:</td>
					<td class="pn-fcontent" width="90%" colspan="3">
						<form:radiobutton path="display" value="true" label="是" />
						<form:radiobutton path="display" value="false" label="否" />
					</td>
				</tr>
				<tr>
					<td class="pn-flabel pn-flabel-h" width="10%">导航栏:</td>
					<td class="pn-fcontent" width="90%" colspan="3">
						<form:radiobutton path="navigate" value="true" label="是" />
						<form:radiobutton path="navigate" value="false" label="否" />
					</td>
				</tr>
				<tr>
					<td class="pn-flabel pn-flabel-h" width="10%">左侧栏:</td>
					<td class="pn-fcontent" width="90%" colspan="3">
						<form:radiobutton path="leftSide" value="true" label="是" />
						<form:radiobutton path="leftSide" value="false" label="否" />
					</td>
				</tr>
				<tr>
					<td class="pn-flabel pn-flabel-h" width="10%">底部栏:</td>
					<td class="pn-fcontent" width="90%" colspan="3">
						<form:radiobutton path="bottom" value="true" label="是" />
						<form:radiobutton path="bottom" value="false" label="否" />
					</td>
				</tr>
			</tbody>
		</table>
		<div align="center" style="margin-top:15px;">
			<input type="submit" value="保存" />
			<input type="button" class="return" value="返回" />
		</div>
	</form:form>
</body>
</html>