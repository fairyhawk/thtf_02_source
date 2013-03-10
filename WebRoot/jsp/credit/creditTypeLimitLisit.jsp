<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>产品分类信用类型额度管理</title>
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
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$(".delete").bind("click",function(){
					var productTypeIdValue = $(this).prev().val();
					var creditTypeIdValue = $(this).next().val();
					
					var productTypeName = $(this).parent().parent().children(".productTypeName").text();
					var creditTypeName = $(this).parent().parent().children(".creditTypeName").text();
					if(confirm("是否确认删除？")){
						window.location = 'deleteProdAssortCreditTypeLimit.do?creditTypeId='+creditTypeIdValue+'&productTypeId='+productTypeIdValue+'&productTypeName='+productTypeName+'&creditTypeName='+creditTypeName;
					}else{
					
					}
				});
			});
		</script>
		 <script type="text/javascript">
		$(document).ready(function(){
		    var msg = "${msg}";  //获取服务端信息
			if(msg!=""){
			    alert(msg);
			}
        });		

		</script>
		
	</head>

	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="ye_header_left">&nbsp;
					
				</td>
				<td class="ye_header_center">
					<img src="${ctx}/images/main_jt.jpg" />
					&nbsp;当前位置： 信用管理 &gt;&gt; 产品分类信用类型额度管理
				</td>
				<td class="ye_header_right">&nbsp;
					
				</td>
			</tr>
			<tr>
				<td>&nbsp;
					
				</td>
				<td align="center">
				<br />
					<div style="width:100%; text-align:right">
						单位：元
					</div>
					<html:form action="creditTypeLimit.do?" styleId="formModify"
						method="get">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao1" id="xxxlist">
							<tr>
								<th nowrap="nowrap">
									产品分类名称
								</th>
								<th nowrap="nowrap">
									信用类型
								</th>
								<th nowrap="nowrap">
									信用额度
								</th>
								<th nowrap="nowrap">
									已分配额度
								</th>
								<th nowrap="nowrap">
									未分配额度
								</th>
								<th nowrap="nowrap">
									已用额度
								</th>
								<th nowrap="nowrap">
									冻结额度
								</th>
								<th nowrap="nowrap">
									可用额度
								</th>
								
								<c:if test="${roleId == 7}">
								<th nowrap="nowrap" width="70px">
									操作
								</th>
								</c:if>
							</tr>
							<tr>
								<logic:present name="creditlimit">
									<logic:iterate id="creditlimitall" name="creditlimit">
										<tr>
											<td class="productTypeName" height="18px">
												${creditlimitall.prodName}
												<input type="hidden" name="creditlimit.id"
													value="${creditlimitall.crdttpId}" />
												<input type="hidden" name="err" value="${err}" id="err"></input>
											</td>
											<td width="11%" class="creditTypeName">
												${creditlimitall.crdttpName}
											</td>
											<td width="11%"
												style=" text-align:right;">
												<fmt:formatNumber value="${creditlimitall.totalLimit}"
													type="number" pattern="#,##0.00" />&nbsp;
											</td>
											<td width="11%" style="text-align:right;">
												<fmt:formatNumber value="${creditlimitall.assignedLimit}"
													type="number" pattern="#,##0.00" />&nbsp;
											</td>
											<td width="11%" style="text-align:right;">
												<fmt:formatNumber value="${creditlimitall.unassignedLimit}"
													type="number" pattern="#,##0.00" />&nbsp;
											</td>
											<td width="11%" style="text-align:right;">
												<fmt:formatNumber value="${creditlimitall.usedLimit}"
													type="number" pattern="#,##0.00" />&nbsp;
											</td>
											<td width="11%" style="text-align:right;">
												<fmt:formatNumber value="${creditlimitall.lockedLimit}"
													type="number" pattern="#,##0.00" />&nbsp;
											</td>
											<td width="11%" style="text-align:right;">
												<fmt:formatNumber value="${creditlimitall.freeLimit}"
													type="number" pattern="#,##0.00" />&nbsp;
											</td>
											<input type="hidden" name="productTypeId" value="${creditlimitall.prodId}" />
											<input type="hidden" name="creditTypeId" value="${creditlimitall.crdttpId}" />
											<c:if test="${roleId == 7}">
											<td>
												<a href="findProdAssortCreditLimit.do?creditTypeId=${creditlimitall.crdttpId}&productTypeId=${creditlimitall.prodId}">修改</a>
												<input type="hidden" name="productTypeId" value="${creditlimitall.prodId}" />
												<a href="#" class="delete">删除</a>
												<input type="hidden" name="creditTypeId" value="${creditlimitall.crdttpId}" />
											</td>
											</c:if>
										</tr>
									</logic:iterate>
								</logic:present>
							</tr>
						</table>
						<br />
						<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
							<tr>
								<td align="left">
								<c:if test="${roleId == 7}">
								<a href="findAllProdType.do">
										<img src="${ctx}/images/btnAdd.gif" /> 
									</a>
								</c:if>	
								</td>
								<td align="right">
									<%@ include file="/jsp/common/newPage.jsp"%>
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
