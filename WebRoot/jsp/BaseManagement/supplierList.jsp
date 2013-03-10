<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>供货商信息管理</title>
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
					$("#xxxlist tr:even").addClass("treven");
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
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/ajax.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript">
	var currentSort;
//下拉列表项改变时的操作
	function proChange(objVal){
	 createXMLHttpRequest();
	 document.getElementById("supplier.city").length=1;
	 XMLHttpReq.onreadystatechange=getAjaxStatus;
	 var url = "${ctx}/logistics.do?method=getCitysByProvinceId&provinceId="+encodeURI(objVal,"UTF-8");
	 XMLHttpReq.open("GET",url,true);
	 XMLHttpReq.send(null);
	}
//解析XML信息，添加城市
	function parseXML(xmlDoc){
	var cityName = xmlDoc.getElementsByTagName("cityName");
	var areaName = "";
	var citySel = document.getElementById("supplier.city");
	for(var i=0;i<cityName.length;i++){
	var opt = document.createElement("OPTION");
	opt.text = xmlDoc.getElementsByTagName("cityName")[i].firstChild.data;
	opt.value=xmlDoc.getElementsByTagName("cityName")[i].firstChild.data;
	citySel.add(opt);
	areaName = xmlDoc.getElementsByTagName("areaName")[0].firstChild.data;
	document.getElementById("supplier.area").value=areaName;
	}
	}
		
		var myArray=new Array();
		var mark1;
		var mark;
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
		//删除供货商
		function shan(){
		 if(myArray.length==0){
				alert("请选择删除项！");
			}else if (confirm("是否确认删除？")) {
				window.location = '${ctx}/supplier.do?method=deleteSupplier&id='+myArray.join(',');
				//document.forms[0].action='${ctx}/supplier.do?method=deleteSupplier&id='+myArray.join(',');
				//document.forms[0].submit();
			}
		};
		
			var formId = 0;
			//控件名
			var checknNames = [ "supplier.name", "supplier.area", "supplier.province","supplier.city"];
			//提示语
			var descriptions = ["供货商名称","区域","省/直辖市","市"];
			//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
			var checkNulls = [ "notspace", "null", "null", "null"];
			//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
			var checkTypes = ["", "", "", ""];
			//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
			var checkLengths = ["80", "", "", ""];
		//检索供货商
		function checkSupplier() {
			if(checkForm()==false){
			 return ;
			  }
			var form = document.getElementsByTagName("Form")[0];
			form.submit();
		};
		
		//控制回车提交
		function document.onkeydown(){                 
		     if(event.keyCode==13){
		         event.keyCode=0;  
		         return false;                               
		    }
		}
</script>
	</head>

	<body>
		<form action="supplier.do">
		<input type="hidden" name="method" value="supplierAll">
			      <input type="hidden" name="method" value="searchUser">
	      	<input type="hidden" id="oldPageBySupplier" value="cutPage" />
  			<input type="hidden" id="mappingName" value="${supplier.name}"/>
			<input type="hidden" id="mappingArea" value="${supplier.area }"/>
			<input type="hidden" id="mappingProvince" value="${supplier.province }"/>
			<input type="hidden" id="mappingCity" value="${supplier.city }"/>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="ye_header_left">&nbsp;
						
					</td>
					<td class="ye_header_center">
						<img src="${ctx}/images/main_jt.jpg" />
						&nbsp;当前位置： 基础信息管理 &gt;&gt; 供货商信息管理
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
							<div style="display: ; " class="mo_con">

								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="biao3">
									<tr>
										<td class="td_01" width="20%">
											供货商名称
										</td>
										<td class="td_02" width="30%">
											<input type="text" name="supplier.name" id="supplier.name"
												style="width:240px;" value="${supplier.name}" maxlength="40"/>
										</td>
										<td class="td_01" width="20%">
											区域
										</td>
										<td class="td_02" width="30%">
											<select name="supplier.area" id="supplier.area"
												style="width:132px">
												<option value="">
													--请选择--
												</option>
												<logic:present name="areaList">
													<logic:iterate id="area" name="areaList">
														<logic:equal value="${supplier.area }" name="area"
															property="name">
															<option value="${area.name}" selected="selected">
																${area.name}
															</option>
														</logic:equal>
														<logic:notEqual value="${supplier.area }" name="area"
															property="name">
															<option value="${area.name}">
																${area.name}
															</option>
														</logic:notEqual>

													</logic:iterate>
												</logic:present>
											</select>
										</td>
									</tr>
									<tr>
										<td class="td_01" width="20%">
											省/直辖市
										</td>
										<td class="td_02" width="30%">
											<select name="supplier.province" id="supplier.province"
												style="width:132px" onchange="proChange(this.value);">
												<option value="">
													--请选择--
												</option>
												<logic:present name="provinceList">
													<logic:iterate id="province" name="provinceList">
														<logic:equal value="${supplier.province }" name="province"
															property="name">
															<option value="${province.name}" selected="selected">
																${province.name}
															</option>
														</logic:equal>
														<logic:notEqual value="${supplier.province }"
															name="province" property="name">
															<option value="${province.name}">
																${province.name}
															</option>
														</logic:notEqual>
													</logic:iterate>
												</logic:present>
											</select>
										</td>
										<td class="td_01" width="20%">
											市
										</td>
										<td class="td_02" width="30%">
											<select name="supplier.city" id="supplier.city"
												style="width:132px">
												<option value="">
													--请选择--
												</option>
												<logic:present name="cityList">
													<logic:iterate id="city" name="cityList">
														<logic:equal value="${supplier.city }" name="city"
															property="name">
															<option value="${city.name}" selected="selected">
																${city.name}
															</option>
														</logic:equal>
														<logic:notEqual value="${supplier.city }" name="city"
															property="name">
															<option value="${city.name}">
																${city.name}
															</option>
														</logic:notEqual>
													</logic:iterate>
												</logic:present>
											</select>
										</td>
									</tr>
									<tr>
										<td colspan="4" align="center" height="30px">
											<a href="#"><img src="${ctx}/images/btn_JianSuo.gif"
												onclick="checkSupplier();" /></a>
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

						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="xxxlist">
							<tr>
							<c:if test="${roleId==2}">
								<th nowrap="nowrap" width="41px">
									选择
								</th>
							</c:if>
								<th nowrap="nowrap">
									供货商名称
								</th>
								<th nowrap="nowrap">
									区域
								</th>
								<th nowrap="nowrap">
									省/直辖市
								</th>
								<th nowrap="nowrap">
									市/区
								</th>
								<th nowrap="nowrap" width="70px">
									操作
								</th>
							</tr>
							<logic:present name="supplierList">
								<logic:iterate id="supplier" name="supplierList"
									property="records">
									<tr>
									<c:if test="${roleId==2}">
										<td height="18px">
											&nbsp;<input name="ss" type="checkbox" value="${supplier.id }"
												onclick="javascript:removeBefore(this);" />
										</td>
										</c:if>
										<td height="18px">
											${supplier.name}
										</td>
										<td nowrap="nowrap" width="120px">
											${supplier.area}
										</td>
										<td nowrap="nowrap" width="120px">
											${supplier.province}
										</td>
										<td nowrap="nowrap" width="120px">
											${supplier.city}
										</td>
										<td nowrap="nowrap" >
											<a href="javascript:window.location = '${ctx}/supplier.do?method=supplierLook&id=${supplier.id}';">查看</a><c:if test="${roleId==2}">
											<a href="javascript:window.location = '${ctx}/supplier.do?method=showSupplierUpdate&id=${supplier.id}';">修改</a>
											</c:if>
										</td>
									</tr>
								</logic:iterate>
							</logic:present>
						</table>
						<br />
							<table border="0" cellpadding="0" cellspacing="0" width="100%"
								id="ec_table">
								<c:if test="${roleId==2}">
								<tr>
									<td width="30px" align="left">
										&nbsp;&nbsp;<input name="input2" type="checkbox" onclick="che(this);" />
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
												href="javascript:window.location = '${ctx}/supplier.do?method=showSupplierAdd';"><img
													src="${ctx}/images/btnAdd.gif" /> </a>
									</td>
									</c:if>
									<td align="right">
										&nbsp;&nbsp;
										<%@ include file="/jsp/common/page.jsp"%>
									</td>
								</tr>
							</table>
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
		</form>
	</body>
</html>
