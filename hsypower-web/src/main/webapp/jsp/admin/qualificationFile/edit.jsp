<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="../../common/taglibs.jsp"%>
<head>
	<%@ include file="../common/header.jsp"%>
   	<script type="text/javascript">
   	$(document).ready(function() {
   		$("#file").change(function(){
   			previewImg(this, '#preview');
   		});
   	});
    </script>
</head>

<body>
	<center>
	<form:form commandName="qualificationFile" enctype="multipart/form-data">
	    <form:hidden path="id" />
	    <form:hidden path="filePath" />
		<form:errors path="*" cssStyle="color:red"></form:errors><br/>
		<table class="pn-ftable" width="100%" cellspacing="1" cellpadding="2" border="0">
			<tbody>
				<tr>
					<td class="pn-flabel pn-flabel-h" width="10%">资质文件名称:</td>
					<td class="pn-fcontent" width="90%" colspan="3"><form:input path="name" /></td>
				</tr>
				<tr>
					<td class="pn-flabel pn-flabel-h" width="10%">资质文件类名:</td>
					<td class="pn-fcontent" width="90%" colspan="3">
						<form:select path="qualificationFileCategory.id">
							<c:forEach items="${qualificationFileCategories }" var="qualificationFileCategory">
								<form:option value="${qualificationFileCategory.id }">${qualificationFileCategory.name }</form:option>
							</c:forEach>
						</form:select>
					</td>
				</tr>
				<tr style="height: 110px">
					<td class="pn-flabel pn-flabel-h" width="10%">资质文件图片:</td>
					<td class="pn-fcontent" width="25%"><form:input path="file" type="file" /></td>
					<td class="pn-fcontent" width="65%" colspan="2"><img id="preview" src="${ctx }${qualificationFile.filePath }" height="100px" /></td>
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