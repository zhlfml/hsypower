<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="../common/taglibs.jsp"%>
<head>
	<%@ include file="../common/header.jsp"%>
</head>

<body class="festival">
	<%@ include file="../common/navigate.jsp"%>
	<div class="le">
		<div class="left">
			<div class="part1">
				<dl>
				  <dt><span><a href="${ctx }/introduce">更多‖</a></span></dt>
				  <dd><table width="100%" cellspacing="0" cellpadding="0" border="0"><tbody><tr><td>${company.introduce }</td></tr></tbody></table></dd>
				 </dl>
			</div>
			<div class="part2">
				<dl>
					<dt><span><a href="${ctx }/product">更多‖</a></span></dt>
					<dd>
						<div class="products_marquee">
							<c:forEach items="${products }" var="product">
								<a href="${ctx }/product_detail/${product.id }" target="_blank"><img src="${ctx }${product.icon }" alt="${product.name }" height="120px" /></a>
							</c:forEach>
					  	</div>
					</dd>
				</dl>
			</div>
		</div>
		<div class="right">
			<div class="part3">
				<dl>
	  				<dt></dt>
	  				<dd>
	  					<img src="${ctx }/images/lxwm.jpg" />
	  					<table width="100%" cellspacing="0" cellpadding="0" border="0">
	  						<tbody>
	  							<tr>
	  								<td>
	  									<p style="line-height: 25px">${company.name } <br>
	  										地址：${company.address } <br>
	  										电话：${company.telephone }<br>
	  										传真：${company.tax }<br>
	  										邮箱：<a href="mailto:${company.email }">${company.email }</a>
	  									</p>
	  									<p>
	  										<img border="0" src="${ctx }/images/position.jpg" />
	  									</p>
									</td>
								</tr>
							</tbody>
						</table>
					</dd>
	 			</dl>
			</div>
		</div>
	</div>
	<%@ include file="../common/footer.jsp"%>
	<script src="${ctx }/js/jquery.marquee.js" type="text/javascript"></script>
	<script type="text/javascript">
	$(function() {
		$('.products_marquee').marquee({
			speed: 12000,
			gap: 50,
			delayBeforeStart: 0,
			direction: 'left',
			duplicated: true,
			pauseOnHover: true
		});
	});
	</script>
</body>
</html>
