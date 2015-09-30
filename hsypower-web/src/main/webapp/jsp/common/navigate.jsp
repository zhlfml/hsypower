<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../common/taglibs.jsp"%>

<%
	int numOfNavi = (Integer) request.getAttribute("numOfNavi");
	int width = 960 / numOfNavi;
%>
<div class="mgrT10" style="position:relative; text-align:center">
	<div class="wrap">
		<div class="idx_logo_top" align="left">
	  		<img src="${ctx}/images/gjdw.jpg" border="0" />
	  		<img src="${ctx}/images/logo.jpg" border="0" />
	  		<img src="${ctx}/images/name.jpg" border="0" />
	  	</div>

      	<div class="clear"></div>
      	<div style="height:5px;overflow:hidden"></div>
		<!--首页导航-->
      	<ul class="index_navbar linkWhite fntwryh">
			<c:forEach items="${navigate }" var="channel" >
			<li style="width: <%=width %>px">
				<a href="${ctx }${channel.path}">${channel.name }</a>
			</li>
			</c:forEach>
      	</ul>
		<!--首页导航结束-->      
	</div>
</div>
