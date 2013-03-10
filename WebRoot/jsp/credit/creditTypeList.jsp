<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>信用类型管理</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
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
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="ye_header_left">&nbsp;
					
				</td>
				<td class="ye_header_center">
					<img src="${ctx}/images/main_jt.jpg" />
					&nbsp;当前位置： 信用管理 &gt;&gt; 信用类型管理
				</td>
				<td class="ye_header_right">&nbsp;
					
				</td>
			</tr>
			<tr>
				<td>&nbsp;
					
				</td>
				<td align="center">
					<br />
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="biao1" id="xxxlist">
						<tr>
							<th nowrap="nowrap" >
								信用类型名称
							</th>
							<c:if test="${roleId==7}">
								<th nowrap="nowrap" width="40px">
									操作
								</th>
							</c:if>
						</tr>
						<tr>
							<logic:present name="credittypelist">
								<logic:iterate id="credittypeAll" name="credittypelist">
									<tr>
										<td height="18px">
											${credittypeAll.name}
										</td>
										<c:if test="${roleId==7}">
											<td>
												<a href="${ctx}/findCreditType.do?id=${credittypeAll.id}">修改</a>
											</td>
										</c:if>
									</tr>
								</logic:iterate>
							</logic:present>
						</tr>
					</table>
					<br />
					<table width="100%" border="0px">
						<tr>
							<td align="right"><%@ include file="/jsp/common/newPage.jsp"%></td>
						</tr>
					</table>
				</td>
				<td>&nbsp;
					
				</td>
			</tr>
		</table>
	</body>
</html>
