<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>人员区域管理-添加</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
		    var msg = "${msg}";  //获取服务端信息
            
			$("#area").validate({
				rules: {
					"area.name": "required"
				},
				messages: {
					"area.name": "请输入正确的人员区域名称"
				}
			});
			if(msg!=""){
			    alert(msg);
			}
			
        });		
		
//		function document.onkeydown(){                 
//		     if(event.keyCode==13){
//		         event.keyCode=0;  
//		         return false;                               
//		    }
//		} 
//		function check(){
//			var name = document.getElementById("name").value;
//			if(name == ""){
//				alert("必填项不能为空！");
//		}else if (name.indexOf(" ")==0||name.lastIndexOf(" ")==(name.length-1)
//				||name.indexOf("　")==0||name.lastIndexOf("　")==(name.length-1)) {
//					alert('人员区域名称' + "\u4e0d\u80fd\u4ee5\u7a7a\u683c\u5f00\u5934\u6216\u7ed3\u5c3e!");
//			}else{
//				document.forms[0].submit();
//			}
//		}
		function checks(){
			var err = document.getElementById("err").value;
			if(err==null||err==""){
				
			} 
			else if(err!= null){
				alert("添加失败！");
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
					<img src="${ctx}/images/main_jt.jpg" />&nbsp;&nbsp;当前位置： 基础信息管理 &gt;&gt; 人员区域信息管理 &gt;&gt; 人员区域添加
				</td>
				<td class="ye_header_right">&nbsp;
					
				</td>
			</tr>
			<tr>
				<td>&nbsp;
					
				</td>
				<td align="center">
					<br />

					<html:form action="/areamanagement" method="post" styleId="area">
						<input type="hidden" name="method" value="addArea" />
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
							<tr>
								<td nowrap="nowrap" class="td_03" width="50%">
									<span style="color:#FF0000">*</span>&nbsp;人员区域名称
								</td>
								<td class="td_04">
									<input type="text" name="area.name" id="name" maxlength="4"></input>
									<input type="hidden" name="err" value="${err}" id="err"></input>
								</td>
							</tr>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="300px" id="ec_table">
							<tr>
								<td  align="center" height="50px" valign="bottom">
									<a href="#" onclick="$('#area').submit();"><img src="${ctx}/images/btnAdd.gif" /></a>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a
										href="javascript:window.location='${ctx}/areamanagement.do?method=areaAll';"><img
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
