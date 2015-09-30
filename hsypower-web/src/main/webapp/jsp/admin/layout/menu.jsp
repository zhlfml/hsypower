<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="../../common/taglibs.jsp"%>
<head>
	<%@ include file="../common/header.jsp"%>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#accordion").accordion({
			heightStyle: "content"
		});
	});
	</script>
</head>

<body class="lbody">
	<div id="accordion" style="width: 250px">
		<h3>公司管理</h3>
		<div>
			<ul>
				<li><a href="${ctx }/admin/company/edit?part=basic" target="rightFrame">基本信息</a></li>
				<li><a href="${ctx }/admin/company/edit?part=introduce" target="rightFrame">公司简介</a></li>
				<li><a href="${ctx }/admin/company/edit?part=perfermance" target="rightFrame">主要业绩</a></li>
				<li><a href="${ctx }/admin/company/edit?part=service" target="rightFrame">客户服务</a></li>
				<li><a href="${ctx }/admin/company/edit?part=sales" target="rightFrame">销售网络</a></li>
			</ul>
		</div>
		<h3>栏目管理</h3>
		<div>
			<ul>
				<li><a href="${ctx }/admin/channel/list" target="rightFrame">栏目列表</a></li>
			</ul>
		</div>
		<h3>用户管理</h3>
		<div>
			<ul>
				<li><a href="${ctx }/admin/user/list" target="rightFrame">用户列表</a></li>
				<li><a href="${ctx }/admin/user/add" target="rightFrame">新增用户</a></li>
				<li><a href="${ctx }/admin/user/changePwd" target="rightFrame">修改密码</a></li>
			</ul>
		</div>
		<h3>新闻管理</h3>
		<div>
			<ul>
				<li><a href="${ctx }/admin/news/list" target="rightFrame">新闻列表</a></li>
				<li><a href="${ctx }/admin/news/add" target="rightFrame">发布新闻</a></li>
			</ul>
		</div>
		<h3>产品管理</h3>
		<div>
			<ul>
				<li><a href="${ctx }/admin/product/list" target="rightFrame">产品列表</a></li>
				<li><a href="${ctx }/admin/product/add" target="rightFrame">新增产品</a></li>
				<li><a href="${ctx }/admin/productCategory/list" target="rightFrame">产品分类列表</a></li>
				<li><a href="${ctx }/admin/productCategory/add" target="rightFrame">新增产品分类</a></li>
			</ul>
		</div>
		<h3>资质文件管理</h3>
		<div>
			<ul>
				<li><a href="${ctx }/admin/qualificationFile/list" target="rightFrame">资质文件列表</a></li>
				<li><a href="${ctx }/admin/qualificationFile/add" target="rightFrame">新增资质文件</a></li>
				<li><a href="${ctx }/admin/qualificationFileCategory/list" target="rightFrame">资质文件分类列表</a></li>
				<li><a href="${ctx }/admin/qualificationFileCategory/add" target="rightFrame">新增资质文件分类</a></li>
			</ul>
		</div>
	</div>
</body>
</html>