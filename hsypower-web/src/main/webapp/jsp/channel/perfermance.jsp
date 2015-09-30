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
				<%@ include file="../common/left.jsp"%>	
				
				<div class="comm_theme_r">
					<div class="comm_top_t"><img src="${ctx}/images/co_pic${current }_tt.jpg" /></div>
					<div class="mgrT10"></div>
					<div class="comm_top_k">
						${company.perfermance }
					</div>
				</div>
	        </div> 
		</div>
		<!-- 结束-->    
	</div>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>