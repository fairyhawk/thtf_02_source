<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>产品添加</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript" src="${ctx}/js/ajax.js"></script>
		<script type="text/javascript">
	var currentSort;
//下拉列表项改变时的操作
	function proChange(objVal){
	 createXMLHttpRequest();
	 if(objVal==""){
	 document.getElementById("ssno1").innerText = ""; 
	 document.getElementById("sxf").innerHTML="";
	 }
	 document.getElementById("sname").length=1;
	 XMLHttpReq.onreadystatechange=getAjaxStatus;
	 var url = "${ctx}/product.do?method=querySerieByTypeId&typeId="+objVal;
	 XMLHttpReq.open("GET",url,true);
	 XMLHttpReq.send(null);
	}
//解析XML信息，添加系列
	function parseXML(xmlDoc){
	var seireName = xmlDoc.getElementsByTagName("serieName");
	var citySel = document.getElementById("sname");
	for(var i=0;i<seireName.length;i++){
	var opt = document.createElement("OPTION");
	opt.text = xmlDoc.getElementsByTagName("serieName")[i].firstChild.data;
	opt.value=xmlDoc.getElementsByTagName("serieIdNo")[i].firstChild.data;
	citySel.add(opt);
	}
	}
	//选择系列时，获取产品最大no
	function proChange1(){
	 createXMLHttpRequest();
	 var tno = document.getElementById("tname").value;
	 var ssno = document.getElementById("sname").value;
	 XMLHttpReq.onreadystatechange=getAjaxStatus1;
	 var url = "${ctx}/product.do?method=getMaxProductNoByPSId&tidno="+tno+"&sidno="+ssno;
	 XMLHttpReq.open("GET",url,true);
	 XMLHttpReq.send(null);
	}
//解析XML信息，添加系列
	function parseXML1(xmlDoc){
	 var sno = document.getElementById("sname").value;
	 if(sno!=""){
	 var ss = xmlDoc.getElementsByTagName("maxno")[0].firstChild.data;
	 document.getElementById("ssno1").innerText = ss; 
	 document.getElementById("ssno").value=ss;
	 }else{
	 document.getElementById("ssno1").innerText = ""; 
	 document.getElementById("ssno").value="";
	 }
	changeSerie();
	}
	function changeSerie(){
	   var tno = document.getElementById("tname").value;
	   var sno = document.getElementById("sname").value;
	   var ssno = document.getElementById("ssno").value;
	   if(sno!=""){
	   var sss;
		 var tidno = tno.split(",");
		 var sidno = sno.split(",");
		 sss=tidno[1]+"."+sidno[1]+"."+ssno;
		 document.getElementById("sxf").innerHTML=sss;
	   }else{
	   document.getElementById("sxf").innerHTML="";
	   }
		 
	}
	
	var formId = 0;
	//控件名
	var checknNames = [ "name1", "type", "unit","product.sname1","product.tname1","limitPrice"];
	//提示语
	var descriptions = [ "产品名称", "规格型号", "单位", "产品系列","产品分类","限价"];
	//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
	var checkNulls = [ "notnull", "notnull", "notnull", "notnull" ,"notnull","notnull"];
	//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
	var checkTypes = [ "", "", "", "","","num."];
	//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
	var checkLengths = [ "80", "80", "8", "" ,"","11"];
	
	function check(){
			if(checkForm()==false){
			return ;
			}
			document.forms[0].submit();
		
		}
		
		function checks(){
			var err = document.getElementById("err").value;
			if(err==null||err==""){
				
			} 
			else if(err!= null){
				alert("添加失败！");
				}
		};
		//要验证的表单是第n个表单
	
	</script>
	</head>
	<body onload="checks();">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="ye_header_left">&nbsp;
					
				</td>
				<td class="ye_header_center">
					<img src="${ctx}/images/main_jt.jpg" />
					&nbsp;当前位置： 基础信息管理 &gt;&gt; 产品信息管理 &gt;&gt; 产品添加
				</td>
				<td class="ye_header_right">&nbsp;
					
				</td>
			</tr>
			<tr>
				<td>&nbsp;
					
				</td>
				<td align="center">
					<br />
					<html:form action="/product" method="post">
						<input type="hidden" name="method" value="productAdd" />
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;产品分类名称
								</td>
								<td width="30%" class="td_02">
									<label>
										<select name="product.tname1" id="tname"
											onchange="proChange(this.value);" style=" width:126px">
											<option value="" selected="selected">
												--请选择--
											</option>
											<logic:present name="tlist">
												<logic:iterate id="tlist" name="tlist">
													<option value="${tlist.idNo}">
														${tlist.name}
													</option>
												</logic:iterate>
											</logic:present>
										</select>
									</label>
								</td>
								<td class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;产品系列名称
								</td>
								<td width="30%" class="td_02">
									<label>
										<select name="product.sname1" id="sname"
											onchange="proChange1();" style=" width:132px">
											<option value="" selected="selected">
												--请选择--
											</option>
										</select>
									</label>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;产品名称
								</td>
								<td class="td_02">
									<input type="text" name="name1" id="name" maxlength="40"
										style="width:320px;" />
									<input type="hidden" name="ssno" id="ssno" />
									<input type="hidden" name="err" id="err" value="${err}" />
								</td>
								<td nowrap="nowrap" class="td_01">
									&nbsp;
									<span style="color:#FF0000">*</span>&nbsp;规格型号
								</td>
								<td class="td_02">
									<input type="text" name="type" id="type" maxlength="40"
										style="width:240px;" />
								</td>
							</tr>
							<tr>
								<td class="td_01" nowrap="nowrap" height="18px">
									&nbsp;产品编号
								</td>
								<td class="td_02" id="ssno1">
								</td>
								<td class="td_01" nowrap="nowrap">
									产品编码
								</td>
								<td class="td_02" id="sxf">&nbsp;
									
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;单位
								</td>
								<td class="td_02">
									<input type="text" name="unit" id="unit" style="width:120px;" maxlength="4"/>
								</td>
								<td nowrap="nowrap" class="td_01"><span style="color:#FF0000">*</span>&nbsp;限价
								</td>
								<td class="td_02">
									<input type="text" name="limitPrice" id="limitPrice" style="width:120px;" maxlength="11"/>
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="400px"
							id="ec_table">
							<tr>
								<td height="50px" valign="bottom" align="center">
									<a href="javascript:check();"><img src="${ctx}/images/btnAdd.gif" /></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="javascript:window.location = '${ctx}/product.do?method=productAll';"><img src="${ctx}/images/btnBack.gif" width="65" height="20" /></a>
								</td>
							</tr>
						</table>
					</html:form>
				</td>
				<td>&nbsp;
					
				</td>
			</tr>
		</table>
	</body>
</html>
