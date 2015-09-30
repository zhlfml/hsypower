<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="../../common/taglibs.jsp"%>
<head>
	<%@ include file="../common/header.jsp"%>
</head>

<body>
	<center>
	<form:form commandName="productCategory" >
	    <form:hidden path="id" />
		<form:errors path="*" cssStyle="color:red"></form:errors><br/>
		<table class="pn-ftable" width="100%" cellspacing="1" cellpadding="2" border="0">
			<tbody>
				<tr>
					<td class="pn-flabel pn-flabel-h" width="10%">产品类名:</td>
					<td class="pn-fcontent" width="90%" colspan="3"><form:input path="name" /></td>
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