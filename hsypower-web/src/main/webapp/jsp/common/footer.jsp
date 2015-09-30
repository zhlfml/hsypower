<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>

<div class="wwwbottom">
	<p>
		<c:forEach items="${bottom }" var="channel" varStatus="status" >
			<c:if test="${status.index > 0 }"> | </c:if>
			<a href="${ctx }${channel.path}">${channel.name }</a>
		</c:forEach>
	</p>

    <p><span> <a href="${url }">${company.name }</a> </span>版权所有</p>
</div>