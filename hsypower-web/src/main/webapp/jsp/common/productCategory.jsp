<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>

<div class="comm_theme_m">
	<div class="adv_pcimg"><img src="${ctx }/images/co_pic/prod_logo.jpg" width="231" height="243"/></div>
	<div class="mgrT15"></div>
	<ul class="prod_theme_l">
		<c:forEach items="${productCategories }" var="productCategory">
			<li <c:if test="${currentCategory == productCategory.id }">class="selected"</c:if>>
				<a href="${ctx }/product/${productCategory.id }">${productCategory.name }</a>
			</li>
         </c:forEach>
	</ul>
</div>