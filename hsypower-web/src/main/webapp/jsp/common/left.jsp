<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>

<div class="comm_theme_m">
	<div class="adv_pcimg"><img src="${ctx}/images/co_pic${current }_logo.jpg" width="231" height="243"/></div>
	<div class="mgrT15"></div>
	<ul class="comm_theme_l">
		<c:forEach items="${leftSide }" var="channel" >
		<li>
			<c:choose>
				<c:when test="${current == channel.path }">
					<div class="NavMouseOn"><a href="${ctx }${channel.path}"><font color="#FFF">${channel.name }</font></a></div>
				</c:when>
				<c:otherwise>
					<a href="${ctx }${channel.path}">${channel.name }</a>
				</c:otherwise>
			</c:choose>
		</li>
		</c:forEach>
	</ul>
</div>