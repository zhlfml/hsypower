<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<%@ page contentType="text/html;charset=UTF-8"%>
	<%@ include file="../common/taglibs.jsp"%>
<head>
	<%@ include file="../common/header.jsp"%>
	<script type="text/javascript">
	var imgSrc = "";
	var iconList = new Array();
	var mapList	 = new Array();
	
	<c:forEach items="${qualificationFileCategory.qualificationFiles }" var="qualificationFile" varStatus="status">
	iconList[${status.index }] = "${ctx }${qualificationFile.filePath }";
	mapList[${status.index }] = "${ctx }${qualificationFile.filePath }";
	</c:forEach>
	</script>
</head>

<body class="festival">
	<%@ include file="../common/navigate.jsp"%>
	<div class="mgrT15"></div>
	<div id="main">
        <!--图片放大相册-->
        <div id="main_Con">
			<h1>
				<table border="0" cellpadding="0" align="right">
					<tr>
						<td width="300" align="center"><img src="${qualificationFileCategory.filePath }" width="210" height="46" /></td>
						<td width="260" align="center" valign="middle"><a href="${ctx }/qualification/${nextQualificationFileCategory.id }"><span style="font-size:20px; font-weight:bold;">${nextQualificationFileCategory.name }</span></a></td>
						<td width="300" align="left" valign="middle"><a href="${ctx }/qualification/${next2QualificationFileCategory.id }"><span style="font-size:20px; font-weight:bold;">${next2QualificationFileCategory.name }</span></a></td>
					</tr>
				</table>
			</h1>
			<div class="content">
				<div class="contLeft">
				    <div class="contLeftBtn" onclick="MarqueeUpMap('main_rightCon')"></div>
				</div>
				<div class="contCon">
				    <div id="contLeftBtn" onclick="MarqueeUpMap('main_rightCon')"></div>
				    <div id="contRight" onclick="MarqueeDownMap('main_rightCon')"></div>
					<div id="gameOver">
						<div id="gameOverClose"><img src="${ctx }/images/news_11.gif" width="16" height="16" /></div>
						<div id="gameOverContent">您已经浏览完所有图片</div>
						<div id="gameOverBtn">
						    <div class="gameOverBtnL"><a href="javascript:chongxinbf()"><img src="${ctx }/images/news_24.gif" width="83" height="24" /></a></div>
						    <div class="gameOverBtnL"><a href="${ctx }/qualification/${nextQualificationFileCategory.id }"><img src="${ctx }/images/news_21.gif" width="108" height="24" /></a></div>
						</div>
					</div>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="center" valign="middle"><img src=""/></td>
						</tr>
					</table>
				</div>
				<div class="contRight">
					<div class="contRightBtn" onclick="MarqueeDownMap('main_rightCon')"></div>
				</div>
				<div id="label" align="center"><em>标签：</em> ${qualificationFileCategory.name }</div>
			</div>
		</div>
  
		<div id="main_right">
			<div id="lastMapMuster"><a href="${ctx }/qualification/${qualificationFileCategory.id }"><img src="${qualificationFileCategory.filePath }" /><span>${qualificationFileCategory.name }</span></a></div>
			<div id="lastMap" onclick="MarqueeUpMap('main_rightCon')"><img src="${ctx }/images/news_07.gif" width="43" height="13" /></div>
			<div id="main_rightCon">
				<ul>
				<c:forEach items="${qualificationFileCategory.qualificationFiles }" var="qualificationFile" varStatus="status">
					<li id='${status.index }'><div><img src="${ctx }${qualificationFile.filePath }" /></div><span>${status.index }</span></li>
				</c:forEach>
				</ul>
			</div>
			<div id="nextMap" onclick="MarqueeDownMap('main_rightCon')"><img src="${ctx }/images/news_41.gif" width="43" height="13" /></div>
			<div id="nextMapMuster"><a href="${ctx }/qualification/${nextQualificationFileCategory.id }"><img src="${nextQualificationFileCategory.filePath }" /><span>${nextQualificationFileCategory.name }</span></a></div>
		</div>
        <!--图片放大相册END-->
	</div>
	<script type="text/javascript" src="${ctx }/js/qualification.js"></script>
	<%@ include file="../common/footer.jsp"%>
</body>
</html>
