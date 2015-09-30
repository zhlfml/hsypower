<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="../../common/taglibs.jsp"%>
<head>
	<%@ include file="../common/header.jsp"%>
	<script src="${ctx }/widget/kindeditor/kindeditor-min.js" type="text/javascript"></script>
	<script src="${ctx }/widget/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function() {
		var options = {
			cssPath : '${ctx }/widget/kindeditor/plugins/code/prettify.css',
			uploadJson : '${ctx }/fileupload'
		};
		var editor_salesNetwork = KindEditor.create('#editor_salesNetwork', options);
	});
	</script>
</head>

<body>
	<center>
	<form:form commandName="company">
		<form:hidden path="id" />
		<input type="hidden" name="part" value="${param.part }" />
		<table class="pn-ftable" width="100%" cellspacing="1" cellpadding="2" border="0">
			<tbody>
				<tr class="hidden">
					<td class="pn-flabel pn-flabel-h" width="10%">公司名:</td>
					<td class="pn-fcontent" width="40%"><form:input path="name" /></td>
					<td class="pn-flabel pn-flabel-h" width="10%">网址:</td>
					<td class="pn-fcontent" width="40%"><form:input path="website" /></td>
				</tr>
				<tr class="hidden">
					<td class="pn-flabel pn-flabel-h" width="10%">电话:</td>
					<td class="pn-fcontent" width="40%"><form:input path="telephone" /></td>
					<td class="pn-flabel pn-flabel-h" width="10%">传真:</td>
					<td class="pn-fcontent" width="40%"><form:input path="tax" /></td>
				</tr>
				<tr class="hidden">
					<td class="pn-flabel pn-flabel-h" width="10%">公司地址:</td>
					<td class="pn-fcontent" width="40%"><form:input path="address" /></td>
					<td class="pn-flabel pn-flabel-h" width="10%">邮编:</td>
					<td class="pn-fcontent" width="40%"><form:input path="zipcode" /></td>
				</tr>
				<tr class="hidden">
					<td class="pn-flabel pn-flabel-h" width="10%">销售热线:</td>
					<td class="pn-fcontent" width="40%"><form:input path="saleTel" /></td>
					<td class="pn-flabel pn-flabel-h" width="10%">服务热线:</td>
					<td class="pn-fcontent" width="40%"><form:input path="techServiceTel" /></td>
				</tr>
				<tr class="hidden">
					<td class="pn-flabel pn-flabel-h" width="10%">邮箱:</td>
					<td class="pn-fcontent" width="40%"><form:input path="email" /></td>
					<td class="pn-flabel pn-flabel-h" width="10%">meta关键字:</td>
					<td class="pn-fcontent" width="40%"><form:input path="keywords" /></td>
				</tr>
				<tr class="hidden">
					<td class="pn-flabel pn-flabel-h" width="10%">meta描述:</td>
					<td class="pn-fcontent" width="90%" colspan="3"><form:textarea path="description" cssClass="meta" /></td>
				</tr>
				<tr class="hidden">
					<td class="pn-flabel pn-flabel-h" width="10%">公司简介:</td>
					<td class="pn-fcontent" width="90%" colspan="3"><form:textarea id="editor_introduce" path="introduce" /></td>
				</tr>
				<tr class="hidden">
					<td class="pn-flabel pn-flabel-h" width="10%">主要业绩:</td>
					<td class="pn-fcontent" width="90%" colspan="3"><form:textarea id="editor_perfermance" path="perfermance" /></td>
				</tr>
				<tr class="hidden">
					<td class="pn-flabel pn-flabel-h" width="10%">客户服务:</td>
					<td class="pn-fcontent" width="90%" colspan="3"><form:textarea id="editor_customerService" path="customerService" /></td>
				</tr>
				<tr>
					<td class="pn-flabel pn-flabel-h" width="10%">销售网络:</td>
					<td class="pn-fcontent" width="90%" colspan="3"><form:textarea id="editor_salesNetwork" path="salesNetwork" /></td>
				</tr>
			</tbody>
		</table>
		<div align="center" style="margin-top:15px;">
			<input type="submit" value="保存" />
			<input type="reset" value="重置" />
		</div>
	</form:form>
	</center>
</body>
</html>