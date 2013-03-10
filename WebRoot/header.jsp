<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<title>header</title>
		<style type="text/css">
		<!--
			body{margin:0px; padding:0px; font-size:12px; background-image:url(images/header_bg.jpg); background-repeat:repeat-x;}
			.header_01{ color:#FFFFFF; font-family:"宋体"; font-size:13px; }
			.header_02{ color:#1c567e; font-family:"宋体"; font-size:13px; background-image:url(images/header_03.jpg); background-repeat:no-repeat; width:470px; line-height:26px}
			a {color:#1c567e; font-family:"宋体"; font-size:13px;}
			a:link {text-decoration:none;color:#1c567e; font-family:"宋体"; font-size:13px;}
			a:visited{color:#1c567e; font-family:"宋体"; font-size:13px;text-decoration:none;}
			a:hover{color:#fd6802; font-family:"宋体"; font-size:13px;text-decoration:none;}
			img{border:0;}
			ul,li{margin:0;padding:0;list-style:none;text-overflow:ellipsis;}
			#scrollDiv{height:25px;line-height:25px;overflow:hidden;width:280px;text-overflow:ellipsis;}
			#scrollDiv li{height:25px;padding-left:10px;}
		-->
		</style>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {  
        getCnt();
        window.setInterval(getCnt,10000);
		var uri = "${reportLoginURI}";
		if( uri != "" ){ 
			$.ajax({type: "POST",  url: uri,success: function(msg){ 
				getScrollMsg();
		    	setInterval('getScrollMsg()',1000*60*30);     
			}}); 
		}
		window.setInterval(keep,540000); 
    });
    function getCnt(){
        var time = new Date();
        $.getJSON("${ctx}/toworkcount.do?"+time, function(json){
            $('#userCount').text(json.count);
        });
    }
	function keep(){
		var time = new Date();
		$.getJSON("${reportKeepURI}"+"&"+time);
    }
    function logout(){
    	$.getJSON("${reportLogoutURI}");
    	window.parent.location.href='${ctx}/logout.do';
    }
    
    
    
    	function AutoScroll(obj){
    		if($(obj).find("li").length<2)return;
	    	$(obj).find("ul:first").animate({
	    	marginTop:"-25px"
	    	},500,function(){
	    	$(this).css({marginTop:"0px"}).find("li:first").appendTo(this);
	    	});
    	}
    	var scrollMsg ;
    	var scrollIndex = 0;
    	var scroll=true;
    	function setScroll(length){
    		$("#scrollDiv li").bind("mousemove",function(){
    			$(this).css({"cursor":"pointer","textDecoration":"underline"});
    			if(scrollMsg)clearInterval(scrollMsg);
    		}).bind("mouseout",function(){
    			$(this).css({"cursor":"","textDecoration":"none"});
    			if(length!=0)scrollMsg = setInterval('AutoScroll("#scrollDiv")',2500);
    		});
    		$("#scrollDiv li").click(function(){
    			if(length<2 && !scroll){scroll=true;scrollMsg = setInterval('AutoScroll("#scrollDiv")',2500)}
    			window.open('${ctx}/go2rpt.do?url=view/base/ViewSiteInformation.action?showSiteInformationList=@@msgId='+$(this).attr("id")+'','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=150,left=270, width=930,height=410');
    			
    		});
    	}
    	 
    	var reloadByScroll="";
    	function reload(){
    		window.location.reload(true);
    	}
        	function getScrollMsg(){
        		if(scrollIndex>3){return;}
        		var time = new Date();
        		$.ajax({
        			url:"${ctx}/go2rpt.do?url=view/base/ViewSiteInformation.action?getSubject=&"+time,
        			type:"get",
        			dataType:"json",
        			success:function(v){
        				if(reloadByScroll){clearInterval(reloadByScroll);}
        				//var v = eval('('+r+')');
        				$("#scrollDiv ul").empty();
        				var msg = "";
        				if(v.msgs.length==0){
        					msg = "<li>查看邮件信息</li>";
        					scroll=false;
        				}
        				$.each(v.msgs,function(i){
        					var subMsg =v.msgs[i].length>15?(v.msgs[i].substring(0,18)+"..."):v.msgs[i];
        					msg+="<li id='"+v.id[i]+"' title='"+v.msgs[i]+"'>"+subMsg+"</li>"
        				});
        				$("#scrollDiv ul").append(msg);
        				//if(scrollMsg){clearInterval(scrollMsg);}
        				if(!scrollMsg){
        					scroll=true;
        					scrollMsg = setInterval('AutoScroll("#scrollDiv")',2500);
        				}
        				setScroll(v.msgs.length);
        			},
        			error:function(){scrollIndex+=1;reloadByScroll=setInterval('getScrollMsg()',1000);}
        		});
        	}
</script>
	</head>

	<body>
		<table width="100%" height="73px" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td rowspan="3" align="left" valign="top" ><img src="images/header_01.jpg" height="73" width="400"/></td>
				<td class="header_01" align="right" valign="top" height="40px">
				<div style="padding-top:5px">
					今天是：
					<script language=JavaScript>
						today=new Date();
						function initArray(){this.length=initArray.arguments.length;
						for(var i=0;i<this.length;i++) this[i+1]=initArray.arguments[i]};
						var d=new initArray("星期日","星期一","星期二", "星期三","星期四","星期五","星期六");
						document.write("<font class=date>",today.getYear(),"年",today.getMonth()+1,"月",today.getDate(),"日 ", d[today.getDay()+1],"</font>");
					</script>
					&nbsp;&nbsp;</div>
				</td>
			</tr>
			<tr>
				<td class="header_02">
					<div style="position:absolute;left:450px;color:#fff; font-family:'宋体'; font-size:13px;" id="scrollDiv">
						<ul></ul>
					</div>
					
					<table width="470px" border="0" cellpadding="0px" cellspacing="0px">
						<tr>
							<td width="140px" align="center">&nbsp;&nbsp;当前用户:&nbsp;${USERLOGIN.name}</td>
							<td width="145px" align="center">
								职务:<c:if test="${USERLOGIN.roleId==1}">管理员</c:if>
								<c:if test="${USERLOGIN.roleId==2}">信息管理员</c:if>
								<c:if test="${USERLOGIN.roleId==3}">销售助理</c:if>
								<c:if test="${USERLOGIN.roleId==4}">销售经理</c:if>
								<c:if test="${USERLOGIN.roleId==5}">销售总监</c:if>
								<c:if test="${USERLOGIN.roleId==6}">信用专员</c:if>
								<c:if test="${USERLOGIN.roleId==7}">信用主管</c:if>
								<c:if test="${USERLOGIN.roleId==8}">采购专员</c:if>
								<c:if test="${USERLOGIN.roleId==9}">区域总监</c:if>
								<c:if test="${USERLOGIN.roleId==10}">产品总监</c:if>
								<c:if test="${USERLOGIN.roleId==11}">采购主管</c:if>
								<c:if test="${USERLOGIN.roleId==12}">库房管理员</c:if>
								<c:if test="${USERLOGIN.roleId==13}">库房主管</c:if>
								<c:if test="${USERLOGIN.roleId==14}">物流管理员</c:if>
								<c:if test="${USERLOGIN.roleId==15}">法务专员</c:if>
								<c:if test="${USERLOGIN.roleId==16}">运营总监助理</c:if>
								<c:if test="${USERLOGIN.roleId==17}">运营总监</c:if>
								<c:if test="${USERLOGIN.roleId==18}">总经理</c:if>
								<c:if test="${USERLOGIN.roleId==19}">区域经理</c:if>
								<c:if test="${USERLOGIN.roleId==20}">大区经理</c:if>
								<c:if test="${USERLOGIN.roleId==21}">开票专员</c:if>
							</td>
							<td width="85px" align="center"><a href="${ctx}/main.jsp" target="body">待办事项(<span id="userCount"></span>)</a></td>
							<td width="60" align="center"><a href="javascript:void(0)" onclick="logout();">退出系统</a></td>
							<td><a href="${ctx}/help.html" target="_blank" align="center">帮助</a></td>
						</tr>
					</table>
				</td>
			 </tr>
			 <tr>
		    	<td >&nbsp;</td>
		    </tr>
		</table>
	</body>
</html>