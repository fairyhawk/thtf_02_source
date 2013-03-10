<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>物流公司信息管理</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<style type="text/css"> 
		<!--
		.treven {
			background-color: #f3fbff;
		}
		.over {
			background-color: #ECECEC;
		}
		.rowselected {
		  	background-color: #868686;
		}
		-->
		</style>
		<script language="JavaScript"> 
		<!--
			$(document).ready(function(){
				if($("#xxxlist")){
					$("#xxxlist tr:odd").addClass("treven");
					$("#xxxlist tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
						$(this).click( function(){
				            if( $(this).hasClass("rowselected") ){
				                $(this).removeClass("rowselected");
				            }else{
				                $(this).addClass("rowselected");
				            }
			            });
					});
				}
			});
		//-->
		</script>
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/base.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript">
		var myArray=new Array();
		var mark1;
		var mark;
		
		//要验证的表单是第n个表单
		var formId = 0;
		//控件名
		var checknNames = [ "logisticsName"];
		//提示语
		var descriptions = [ "物流公司名称"];
		//是否非空验证,如果非空验证填写notnull,如果只验证 开头和结尾的空格填写 notspace，如果不需要非空验证传空参
		var checkNulls = [ "notspace"];
		//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,只能填字母和数字  “abcnum”，    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”,
		var checkTypes = [ ""];
		//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
		var checkLengths = [ ""];
		
		function removeBefore(a) {
			for(var i=0;i<myArray.length;i++){
				if(myArray[i]==a.value){
					mark=0;
					mark1=i;
				}
			}
			if(mark!=0){
				myArray.push(a.value);
			}
			if(mark==0){
				myArray.splice(mark1,1);
			}
			var mark1=0;
			var mark=0;
		};
		
		function shan(){
		 if(myArray.length==0){
				alert("请选择删除项！");
			}else if (confirm("是否确认删除？")) {
			    var name = document.getElementById("name").value;
				window.location ='${ctx}/logistics.do?method=deleteLogistics&id='+myArray.join(',')+'&name='+name;
			}else{
			}
		};
		function checkLogistics(){
				if(checkForm()==false){
					return ;
				}
		        var logisticsName=document.getElementById("name").value;
				window.location ='${ctx}/logistics.do?method=checkLogistics&logisticsName='+encodeURI(logisticsName,"UTF-8");
		};

		</script>
	</head>
	<body>
		<form action="logistics.do?method=logisticsAll" method="post">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="ye_header_left">&nbsp;
						
					</td>
					<td class="ye_header_center">
						<img src="${ctx}/images/main_jt.jpg" />
						&nbsp;当前位置： 基础信息管理 &gt;&gt; 物流公司信息管理
					</td>
					<td class="ye_header_right">&nbsp;
						
					</td>
				</tr>
				<tr>
					<td>&nbsp;
						
					</td>
					<td align="center">
						<br />
						<div class="mo_wp">
							<div style="display: ; " class="mo_con" align="left">
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="biao3">
									<tr>
										<td class="td_03" width="50%">
											物流公司名称
										</td>
										<td class="td_04">

											<input type="text" name="logisticsName" id="name"
												value="${logistics.name}" maxlength="40"
												style="width:240px;" />
										</td>
									<tr>
										<td colspan="2" align="center" height="30px">
											<a href="javascript:checkLogistics();"><img
													src="${ctx}/images/btn_JianSuo.gif" /> </a>
										</td>
									</tr>
								</table>
							</div>
							<div class="mo_title" onclick="fMainListToggle(this)">
								<div style="text-align:center">
									<img id="imgShang" src="${ctx}/images/shang_sj.png" />
								</div>
							</div>
						</div>
					</td>
					<td>&nbsp;
						
					</td>
				</tr>
				<tr>
					<td>&nbsp;
						
					</td>
					<td align="center">
						<br />
						<input type="hidden" name="logistics.name"
							value="${logistics.name}" />
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="xxxlist">
							<tr>
								<c:if test="${roleId==2}">	
								<th nowrap="nowrap" width="41px">
									选择
								</th>
								</c:if>
								<th nowrap="nowrap">
									物流公司名称
								</th>
								<th nowrap="nowrap">
									省/直辖市
								</th>
								<th nowrap="nowrap">
									市/区
								</th>
								<th nowrap="nowrap">
									联系人
								</th>
								<th nowrap="nowrap">
									电话
								</th>
								<th nowrap="nowrap">
									手机
								</th>
								<th nowrap="nowrap">
									MSN
								</th>
								<th nowrap="nowrap">
									QQ
								</th>
								<th nowrap="nowrap">
									合作开始日期
								</th>
								<c:if test="${roleId==2||roleId==5||roleId==6||roleId==7||roleId==15||roleId==16||roleId==17||roleId==18}">	
								<th nowrap="nowrap" width="70px">
									操作
								</th>
								</c:if>	
							</tr>
							<tr>
								<logic:present name="logisticsList">
									<logic:iterate id="logistics" name="logisticsList">
										<tr>
											<c:if test="${roleId==2}">	
											<td>
												&nbsp;<input type="checkbox" name="ss" value="${logistics.id}"
													id="box" onclick="javascript:removeBefore(this);">
											</td>
											</c:if>	
											<td nowrap="nowrap" height="18px">
												${logistics.name}
											</td>
											<td nowrap="nowrap" width="96px">
												${logistics.province}
											</td>
											<td nowrap="nowrap" width="96px">
												${logistics.city}
											</td>
											<td nowrap="nowrap" width="48px">
												${logistics.linkman}
											</td>
											<td nowrap="nowrap" width="120px">
												${logistics.tel}&nbsp;
											</td>
											<td nowrap="nowrap" width="72px">
												${logistics.mobile}&nbsp;
											</td>
											<td nowrap="nowrap" width="180px" title="${logistics.msn}">
												<div class="ellipsis_div" style="width:120px;">
													${logistics.msn}&nbsp;
												</div>
											</td>
											<td nowrap="nowrap" width="72px">
												${logistics.qq}&nbsp;
											</td>
											<td nowrap="nowrap" width="72px">
												${logistics.date}
											</td>
											<c:if test="${roleId==2}">	
											<td nowrap="nowrap">
												<a href="javascript:window.location = '${ctx}/logistics.do?method=logisticsLooks&id=${logistics.id}';">查看</a>
												<a href="javascript:window.location = '${ctx}/logistics.do?method=logisticsUpdate&id=${logistics.id}';">修改</a>
											</td>
											</c:if>
											 <c:if test="${roleId==5||roleId==6||roleId==7||roleId==15||roleId==16||roleId==17||roleId==18}">
											 <td nowrap="nowrap">
												<a href="javascript:window.location = '${ctx}/logistics.do?method=logisticsLooks&id=${logistics.id}';">查看</a>
											</td>
											 </c:if>		
										</tr>
									</logic:iterate>
								</logic:present>
							</tr>
						</table>
						<br />
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							id="ec_table">
							<tr>
								<c:if test="${roleId==2}">	
								<td width="30px" align="left">
									&nbsp;&nbsp;<input type="checkbox" name="zong" onclick="che(this);">
								</td>
								<td width="50px">
									全选

								</td>
								<td width="100px">
									<a href="javascript:shan();"> <img
											src="${ctx}/images/btnDelete.gif" /> </a>
								</td>
								<td width="100px">
									<a
										href="javascript:window.location = '${ctx}/logistics.do?method=showLogisticsAdd';"><img
											src="${ctx}/images/btnAdd.gif" /> </a>
								</td>
								</c:if>	
								<td align="right" style="font-size:12px">
									<%@ include file="/jsp/common/newPage.jsp"%>
								</td>
							</tr>
						</table>
				<tr>
					<td>&nbsp;
						
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
