<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="../common/taglibs.jsp"%>
<head>
	<%@ include file="../common/header.jsp"%>
</head>

<body class="festival">
	<%@ include file="../common/navigate.jsp"%>
	<div class="wrap">
	    <!--主要产品、主要业绩-->
		<div class="backgro mgrT10">
			<div class="box_jj">  
				<%@ include file="../common/productCategory.jsp"%>
				
				<div class="comm_theme_r">
					<div class="comm_top_t"></div>
					<div class="mgrT10"></div>
					<div class="comm_top_k">
						<form action="${ctx }/product/${currentCategory }" method="post">
							<table width="650" cellspacing="2" cellpadding="0" border="0" align="center">
								<tr>
								<c:forEach items="${page.content }" var="product" varStatus="status">
									<td align="center">
										<a href="${ctx }/product_detail/${product.id}" target="_blank"><img src="${ctx }${product.icon }" width="240px" style="border: 1px solid #cccccc;" /></a>
										<br />
										${product.name }
										<br /><br />
									</td>
									<c:if test="${(status.index + 1) % 2 == 0 }"><c:out value="</tr><tr>" escapeXml="false" /></c:if>
								</c:forEach>
								</tr>
							</table>
							<%@ include file="../common/paging.jsp"%>
						</form>
					</div>
				</div>
	        </div> 
		</div>
		<!-- 结束-->    
	</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>
