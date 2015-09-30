// JavaScript Document


var sUserAgent = navigator.userAgent;
var isIE = sUserAgent.indexOf("compatible") > -1 && sUserAgent.indexOf("MSIE") > -1 ;
var isMinIE6_7 = isMinIE8 = false;


if(isIE){
	var reIE = new RegExp("MSIE (\\d+\\.\\d+)");
	reIE.test(sUserAgent);
	var fIEVersion = parseFloat(RegExp["$1"]);
	
	isMinIE6_7 = fIEVersion < 8.0;
	isMinIE8 = fIEVersion >= 8.0;
	
}

function Validate(id){
	try{document.execCommand("BackgroundImageCache", false, true);}catch(e){};
    container = document.getElementById(id),
    original = container.getElementsByTagName("dt")[0],
    clone = container.getElementsByTagName("dd")[0],
    speed = arguments[1] || 10;
    clone.innerHTML=original.innerHTML;
}



//热门图集	
	
	var container;
	var timer ; 
	var count=0;
	
var rolling = function(){
		if (count>=24) {
			clearInterval(timer);
			count=0;
			return false;
		}else{
				 if(container.scrollLeft >= clone.offsetLeft ){
					 if(isMinIE6_7){
						container.scrollLeft = 25;
					}else{
						container.scrollLeft = 65;
					}
				  }else{
					container.scrollLeft=container.scrollLeft + 25;
				  }
			}
     count++;
    }
	
var rollingRight = function(){
		if (count >= 24) {
			clearInterval(timer);
			count=0;
			return false;
		}else{
				if(container.scrollLeft == 0){
					if(isMinIE6_7){
						container.scrollLeft = clone.offsetLeft - 30;
					}else{
						container.scrollLeft = clone.offsetLeft - 65;
					}
				}else{
					container.scrollLeft=container.scrollLeft-25;
		}
     	count++;
	}
}
	
//向左移动
function Marquee(id){
    Validate(id);
	clearInterval(timer);
	timer = setInterval(rolling,5);
}

//向右移动
function MarqueeRight(id){
	   Validate(id);
	   clearInterval(timer);
		timer = setInterval(rollingRight,5);
  }

  
//热门图集END






//图片展示功能
var runT = runD = true ;
var mouseOn ;


var mapNum = mapList.length;
	
	$('#main_rightCon ul li span').each(function(index){
		$("#main_rightCon ul li:eq(" + 0 + ")").addClass("mouseOn");
		$('#main_rightCon ul li span:eq(' + index + ")").text(index+1);
		$('#main_rightCon ul li span:eq(' + 0 + ")").text((1)+"/"+mapNum);
	});
	
	
	//显示大图片及下载图片
	var showBigMap = function(index){
		$('#main_Con .contCon table img').attr("src",imgSrc+mapList[index]+"");
		
		if($('#main_Con .contCon table img').width() >= 650){
			$('#main_Con .contCon table img').width("650px");
		}else{
			$('#main_Con .contCon table img').width("auto");
		}
		
		$('#main_Con .contConBtn a.quanpin').attr("href",imgSrc+mapList[index]+"");
		$('#main_Con .contConBtn a.downLoad').attr("href",imgSrc+mapList[index]+"");
		{
			$('#main_rightCon ul li span').each(function(index){
				$('#main_rightCon ul li span:eq(' + index + ")").text(index+1);
			 });
		}
		$('#main_rightCon ul li span:eq(' + index + ")").text((parseInt(index)+1)+"/"+mapNum);
		
		//alert($('#main_Con .contCon table img').width());
		
		
	}

	$('#main_rightCon ul li').each(function(index){
			$(this).click(function(){	
				var liNode = $(this);
				$('#gameOver').hide();
				$("#main_rightCon ul li").removeClass("mouseOn");
				liNode.addClass("mouseOn");	
				runT = runD = true;
				//显示大图片及下载图片
				showBigMap(index);
				
		});		
			
	});


var containerUp = $('#main_rightCon ul');
	
//列表向上移动	
var rollingUpList = function(){		
		
		if (count >= 21 || parseInt(containerUp.css('top')) >= 0) {
			clearInterval(timer);
			count=0;
			return false;
		}else{
			var i =parseInt(containerUp.css('top'))+20+"px";
			containerUp.css('top',i);
		}
     	count++;
}

//列表向下移动
var rollingDownList = function(){
		
		if (count >= 21 || parseInt(containerUp.css('top')) + (84*mapNum)  <= 420) {
			clearInterval(timer);
			count=0;
			return false;
		}else{
			var i =parseInt(containerUp.css('top'))-20+"px";
			containerUp.css('top',i);
		}
     	count++;
}

function MarqueeUp(id){
	clearInterval(timer);
	timer = setInterval(rollingUpList,5);
}

function MarqueeDown(id){
	 clearInterval(timer);
	timer = setInterval(rollingDownList,5);
}
  


//单张向上移动	
var rollingUp = function(){		
		
		if (count >= 21 || parseInt(containerUp.css('top')) >= 0) {
			clearInterval(timer);
			count=0;
			return false;
		}else{
			var i =parseInt(containerUp.css('top'))+4+"px";
			containerUp.css('top',i);
		}
     	count++;
}

//单张向下移动
var rollingDown = function(){		
		
		if (count >= 21 || parseInt(containerUp.css('top')) + (84*mapNum)  <= 420) {
			clearInterval(timer);
			count=0;
			
			
			return false;
		}else{
			var i =parseInt(containerUp.css('top'))-4+"px";
			containerUp.css('top',i);
		}
     	count++;
}
  

//向下移动
function MarqueeDownMap(id){
	
	mouseOn = $("#main_rightCon ul li.mouseOn");
	if(mouseOn.attr('id') == mapNum-1){
			runT = false;
		}
	if(runT){
		runT = runD = true;
	   	clearInterval(timer);
		timer = setInterval(rollingDown,5);
		showBigMap(mouseOn.next('li').attr('id'));		//显示图片
		mouseOn.removeClass("mouseOn");
		mouseOn.next('li').addClass("mouseOn");
		
	}else{
		$('#gameOver').show();
	}
}

//向上移动
function MarqueeUpMap(id){
	
	mouseOn = $("#main_rightCon ul li.mouseOn");
	if(mouseOn.attr('id') == 0){
			runD = false;
		}else{
		    $('#gameOver').hide();
	    }
	if(runD && mouseOn.attr('id') != 0){
		runT = runD = true;
		clearInterval(timer);
		timer = setInterval(rollingUp,5);
		mouseOn.removeClass("mouseOn");
		mouseOn.prev('li').addClass("mouseOn");
		showBigMap(mouseOn.prev('li').attr('id')); 	//显示图片

		
	}else{
		//$('#gameOver').show();
	}
} 

$(document).ready(function(){
	$('#gameOverClose').click(function(){
		$('#gameOver').hide();
	});
	showBigMap(0);
})

//重新播放
var chongxinbf = function(){
	location.reload();
}

//图片展示功能END