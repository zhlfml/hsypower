<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="../../common/taglibs.jsp"%>
<head>
	<%@ include file="../common/header.jsp"%>
	<script src="${ctx }/widget/kindeditor/kindeditor-min.js" type="text/javascript"></script>
   	<script src="${ctx }/widget/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
	<script src="${ctx }/js/fileupload-xhr.js" type="text/javascript"></script>
   	<script type="text/javascript">
   	$(document).ready(function() {
   		var options = {
   			cssPath : '${ctx }/widget/kindeditor/plugins/code/prettify.css',
   			uploadJson : '${ctx }/fileupload'
   		};
   		var editor_introduce = KindEditor.create('#editor_introduce', options);

		var $preview = $("#preview");
   		$("#file").change(function(){
            var $file = $(this);
            var files = $file[0].files;
            [].forEach.call(files, function(file) {
                uploadFile("${ctx }/fileupload", file, function(json) {
                    console.debug(this);
					$preview.attr("src", json.url);
				});
            })
   		});
   	});
    </script>
</head>

<body>
	<center>
	<form:form commandName="product" enctype="multipart/form-data">
	    <form:hidden path="id" />
	    <form:hidden path="icon" />
		<form:errors path="*" cssStyle="color:red"></form:errors><br/>
        <div id="target"></div>
        <progress id="progress" class="form-control" value="0" max="100"></progress>
		<table class="pn-ftable" width="100%" cellspacing="1" cellpadding="2" border="0">
			<tbody>
				<tr>
					<td class="pn-flabel pn-flabel-h" width="10%">产品名称:</td>
					<td class="pn-fcontent" width="90%" colspan="3"><form:input path="name" /></td>
				</tr>
				<tr>
					<td class="pn-flabel pn-flabel-h" width="10%">产品分类:</td>
					<td class="pn-fcontent" width="90%" colspan="3">
						<form:select path="productCategory.id">
							<c:forEach items="${productCategories }" var="productCategory">
								<form:option value="${productCategory.id }">${productCategory.name }</form:option>
							</c:forEach>
						</form:select>
					</td>
				</tr>
				<tr style="height: 110px">
					<td class="pn-flabel pn-flabel-h" width="10%">产品图片:</td>
					<td class="pn-fcontent" width="25%"><form:input path="file" type="file" /></td>
					<td class="pn-fcontent" width="65%" colspan="2"><img id="preview" src="${ctx }${product.icon }" height="100px" /></td>
				</tr>
				<tr>
					<td class="pn-flabel pn-flabel-h" width="10%">产品介绍:</td>
					<td class="pn-fcontent" width="90%" colspan="3"><form:textarea id="editor_introduce" path="introduce" /></td>
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