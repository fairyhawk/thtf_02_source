<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>产品修改</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript">

		var formId = 0;
	//控件名
	var checknNames = [ "product.name1", "product.type1", "product.unit1","limitPrice"];
	//提示语
	var descriptions = [ "产品名称", "规格型号", "单位","限价"];
	//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
	var checkNulls = [ "notnull", "notnull", "notnull","notnull"];
	//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
	var checkTypes = [ "", "", "","num."];
	//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
	var checkLengths = [ "80", "80", "8","11"];
		function check(){
			if(checkForm()==false){
			return ;
			}
			document.forms[0].submit();
		};
		function checks(){
			var err = document.getElementById("err").value;
			if(err==null||err==""){
				
			} 
			else if(err!= null){
				alert("修改失败！");
				}
		};
		</script>
	</head>
	<body onload="checks();">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="ye_header_left">&nbsp;
					
				</td>
				<td class="ye_header_center">
					<img src="${ctx}/images/main_jt.jpg" />
					&nbsp;当前位置： 基础信息管理 &gt;&gt; 产品信息管理 &gt;&gt; 产品修改
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
						<input type="hidden" name="method" value="saveProduct" />
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%">
									产品分类名称
								</td>
								<td width="30%" class="td_02" height="18px">
									${pinfoMsg.tname}
								</td>
								<td class="td_01" width="20%">
									产品系列名称
								</td>
								<td width="30%" class="td_02">
									${pinfoMsg.sname}
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;产品名称
								</td>
								<td class="td_02">
									<input type="text" name="product.name1"
										value="${pinfoMsg.name}" maxlength="40" id="name"
										style="width:320px;" />
									<input type="hidden" name="product.ids" value="${pinfoMsg.id}" />
									<input type="hidden" name="product.id" value="${pinfoMsg.id}" />
									<input type="hidden" name="product.product_serie_id1"
										value="${pinfoMsg.sid}" />
									<input type="hidden" name="product.product_type_id1"
										value="${pinfoMsg.tid}" />
									<input type="hidden" name="tno"
										value="${pinfoMsg.tno}" />
									<input type="hidden" name="sno"
										value="${pinfoMsg.sno}" />
									<input type="hidden" name="err" id="err" value="${err}" />
								</td>
								<td nowrap="nowrap" class="td_01">
									&nbsp;
									<span style="color:#FF0000">*</span>&nbsp;规格型号
								</td>
								<td class="td_02">
									<input type="text" name="product.type1"
										value="${pinfoMsg.type}" maxlength="40" id="type"
										style="width:240px;" />
								</td>
							</tr>

							<tr>
								<td class="td_01" nowrap="nowrap" height="18px">
									&nbsp;产品编号
								</td>
								<td class="td_02">
									<input type="hidden" name="ssno" value="${pinfoMsg.no}"
										id="ssno" />
									${pinfoMsg.no}
								</td>
								<td class="td_01" nowrap="nowrap">
									产品编码
								</td>
								<td id="sxf" class="td_02">
									${pinfoMsg.tno}.${pinfoMsg.sno}.${pinfoMsg.no}
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01">
									<span style="color:#FF0000">*</span>&nbsp;单位
								</td>
								<td class="td_02">
									<input type="text" name="product.unit1"
										value="${pinfoMsg.unit}" maxlength="4" id="unit"
										style="width:120px;" />
								</td>
								<td nowrap="nowrap" class="td_01"><span style="color:#FF0000">*</span>&nbsp;限价
									
								</td>
								<td class="td_02">
									<input type="text" name="limitPrice" value="${pinfoMsg.limitPrice}" id="limitPrice" style="width:120px;" maxlength="11"/>
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="400px"
							id="ec_table">
							<tr>
								<td height="50px" valign="bottom" align="center">
									<a href="javascript:check();"><img src="${ctx}/images/btnUpdate.gif" width="65" height="20" /></a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="javascript:window.location = '${ctx}/product.do?method=productAll';"><img
											src="${ctx}/images/btnBack.gif" width="65" height="20" /> </a>
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
