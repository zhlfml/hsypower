<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="../common/taglibs.jsp"%>

<c:if test="${not empty page.content}">
<script type="text/javascript">
function pageSelected(pageNo){
	var pageForm = document.getElementById('page');
	document.getElementById('pageNo').value = pageNo;
	(pageForm || document.forms[0]).submit();
}
</script>
<input type="hidden" name="pageNo" value="1" id="pageNo">
<%-- <div class="left">共有<b> ${page.totalCount } </b>条记录，当前第 <b>${page.currentPageNo }</b> 页，共 <b>${page.totalPageCount }</b> 页</div> --%>
<div class="right" align="right">
	<c:choose>
		<c:when test="${page.firstPage}"><span>首页</span></c:when>
		<c:otherwise><a href="javascript:pageSelected(0)">首页</a></c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${hasPreviousPage }"><a href="javascript:pageSelected('${page.number - 1}')">上一页</a></c:when>
		<c:otherwise><span>上一页</span></c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${hasNextPage }"><a href="javascript:pageSelected('${page.number + 1 }')">下一页</a></c:when>
		<c:otherwise><span>下一页</span></c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${page.lastPage}"><span>尾页</span></c:when>
		<c:otherwise><a href="javascript:pageSelected('${page.totalPages - 1 }')">尾页</a></c:otherwise>
	</c:choose>
</div>
</c:if>