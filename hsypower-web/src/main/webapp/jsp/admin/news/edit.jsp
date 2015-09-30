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
   		var editor_content = KindEditor.create('#editor_content', options);
   	});
    </script>
</head>

<body>
	<center>
	<form:form commandName="news">
	    <form:hidden path="id" />
		<form:errors path="*" cssStyle="color:red"></form:errors><br/>
		<table class="pn-ftable" width="100%" cellspacing="1" cellpadding="2" border="0">
			<tbody>
				<tr>
					<td class="pn-flabel pn-flabel-h" width="10%">标题:</td>
					<td class="pn-fcontent" width="90%" colspan="3"><form:input path="name" /></td>
				</tr>
				<tr>
					<td class="pn-flabel pn-flabel-h" width="10%">内容:</td>
					<td class="pn-fcontent" width="90%" colspan="3"><form:textarea id="editor_content" path="content" /></td>
				</tr>
				<tr>
					<td class="pn-flabel pn-flabel-h" width="10%">是否发布:</td>
					<td class="pn-fcontent" width="90%" colspan="3">
						<form:radiobutton path="publish" value="true" label="是" />
						<form:radiobutton path="publish" value="false" label="否" />
					</td>
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