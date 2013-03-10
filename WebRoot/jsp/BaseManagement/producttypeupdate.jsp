<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>产品分类修改</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript">
		function document.onkeydown(){                 
		     if(event.keyCode==13){
		         event.keyCode=0;  
		         return false;                               
		    }
		}
			var formId = 0;
			//控件名
			var checknNames = ["producttype.name", "producttype.product_dept_id"];
			//提示语
			var descriptions = ["分类名称", "部门名称"];
			//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
			var checkNulls = ["notnull", "notnull"];
			//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
			var checkTypes = ["", ""];
			//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
			var checkLengths = ["24", ""];
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
					&nbsp;当前位置： 基础信息管理 &gt;&gt; 产品分类信息管理 &gt;&gt; 产品分类修改
				</td>
				<td class="ye_header_right">&nbsp;
					
				</td>
			</tr>
			<tr>
				<td>&nbsp;
					
				</td>
				<td align="center">
					<br />
					<html:form action="/producttypemanagement" method="post">
						<input type="hidden" name="method" value="productTypeUpdate" />
						<table width="100%" border="0" cellpadding="0" cellspacing="0"  class="biao3">
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%">产品分类编号</td>
								<td class="td_02" width="30%">
										${producttypemsg.no}
									<input type="hidden" name="producttype.id"
										value="${producttypemsg.id}" />
									<input type="hidden" name="err" value="${err}" id="err"></input>
								</td>
								<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;产品分类名称</td>
								<td class="td_02" width="30%">
									<input type="text" name="producttype.name" id="name"
										value="${producttypemsg.name}" maxlength="12" style="width:120px;"/>
								</td>
							</tr>
							<tr>
								<td nowrap="nowrap" class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;产品部门名称</td>
								<td class="td_02">
									<label>
										<select name="producttype.product_dept_id" id="se" style=" width:126px">
											<logic:present name="pdeptname">
												<logic:iterate id="pdeptnames" name="pdeptname">
													<logic:equal value="${pdeptnames.id}"
														property="product_dept_id" name="producttypemsg">
														<option value="${pdeptnames.id}" selected="selected">
															${pdeptnames.name}
														</option>
													</logic:equal>
													<logic:notEqual value="${pdeptnames.id}"
														property="product_dept_id" name="producttypemsg">
														<option value="${pdeptnames.id}">
															${pdeptnames.name}
														</option>
													</logic:notEqual>
												</logic:iterate>
											</logic:present>
										</select>
									</label>
								</td>
								<td class="td_01">&nbsp;
									
								</td>
								<td class="td_02">&nbsp;
									
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="400px"
							id="ec_table">
							<tr>
								<td align="center" height="50px" valign="bottom">
									<a href="javascript:check();"><img src="${ctx}/images/btnUpdate.gif" width="65" height="20" /></a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="javascript:window.location='${ctx}/producttypemanagement.do?method=productTypeAll'">
										<img src="${ctx}/images/btnBack.gif" width="65" height="20" />
									</a>
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
