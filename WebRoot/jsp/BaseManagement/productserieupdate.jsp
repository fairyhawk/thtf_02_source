<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>产品系列-修改</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript">
		var formId = 0;
		//控件名
		var checknNames = ["productserie.name1"];
		//提示语
		var descriptions = ["系列名称"];
		//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
		var checkNulls = ["notnull"];
		//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
		var checkTypes = [""];
		//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
		var checkLengths = ["24"];
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
		//控制回车提交
		function document.onkeydown(){                 
		     if(event.keyCode==13){
		         event.keyCode=0;  
		         return false;                               
		    }
		}
		</script>
	</head>
	<body onload="checks();">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="ye_header_left">&nbsp;
					
				</td>
				<td class="ye_header_center">
					<img src="${ctx}/images/main_jt.jpg" />
					&nbsp;当前位置： 基础信息管理 &gt;&gt; 产品系列信息管理 &gt;&gt; 产品系列修改
				</td>
				<td class="ye_header_right">&nbsp;
					
				</td>
			</tr>
			<tr>
				<td>&nbsp;
					
				</td>
				<td align="center">
					<br />
					<html:form action="/productseriemanagement" method="post">
						<input type="hidden" name="method" value="updateProductSerie" />
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%">
									产品分类名称
								</td>
								<td class="td_02" width="30%">
									${productMsg.tname }
								</td>
								<td class="td_01" width="20%">
									<span style="color:#FF0000">*</span>&nbsp;产品系列名称
								</td>
								<td class="td_02" width="30%">
									<input type="text" name="productserie.name1"
										value="${productMsg.name}" maxlength="12" id="name"
										style="width:120px;" />
									<input type="hidden" name="err" value="${err}" id="err"></input>
									<input type="hidden" name="productserie.id"
										value="${productMsg.id}"></input>
									<input type="hidden" name="productserie.product_type_id"
										value="${productMsg.product_type_id}"></input>
								</td>
							</tr>
							<tr >
								<td nowrap="nowrap" class="td_01">
									产品系列编号
								</td>
								<td class="td_02" height="18">
									<input type="hidden" name="productserie.no1" id="maxno"
										value="${productMsg.no}" />
									${productMsg.no}
								</td>
								<td class="td_01">
									产品系列编码
								</td>
								<td class="td_02" id="sxf">
									${productMsg.num}
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="300px"
							id="ec_table">
							<tr>
								<td height="50px" valign="bottom" align="center">
									<a href="javascript:check();"><img src="${ctx}/images/btnUpdate.gif" width="65" height="20" /></a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="javascript:window.location = '${ctx}/productseriemanagement.do?method=productSerieAll';"><img
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
