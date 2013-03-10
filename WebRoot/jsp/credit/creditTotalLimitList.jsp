<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>产品分类信用总额管理</title>
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
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="ye_header_left">&nbsp;
				</td>
				<td class="ye_header_center">
					<img src="${ctx}/images/main_jt.jpg" />
					&nbsp;当前位置： 信用管理 &gt;&gt; 产品分类信用总额管理
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
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="biao1" id="xxxlist">
						<tr>
							<th nowrap="nowrap">
								产品分类名称
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
							<c:if test="${roleId==7}">
								<th nowrap="nowrap" width="40px">
									操作
								</th>
							</c:if>
						</tr>
						<logic:iterate id="p_clist" name="p_clist">
							<tr>
								<td height="18px">
									${p_clist.prodName}
								</td>
								<td width="13%" style=" text-align:right; padding-right:5px;">
									&nbsp;
									<fmt:formatNumber value="${p_clist.totalLimit}" type="number"
										pattern="#,##0.00" />
										<input name="prodId" value="${pcli.prodId}" type="hidden" />
								</td>
								<td width="13%" style=" text-align:right; padding-right:5px;">
									&nbsp;
									<fmt:formatNumber value="${p_clist.assignedLimit}"
										type="number" pattern="#,##0.00" />
								</td>
								<td width="13%" style=" text-align:right; padding-right:5px;">
									&nbsp;
									<fmt:formatNumber value="${p_clist.unassignedLimit}"
										type="number" pattern="#,##0.00" />
								</td>
								<td width="13%" style=" text-align:right; padding-right:5px;">
									&nbsp;
									<fmt:formatNumber value="${p_clist.usedLimit}" type="number"
										pattern="#,##0.00" />
								</td>
								<td width="13%" style=" text-align:right; padding-right:5px;">
									&nbsp;
									<fmt:formatNumber value="${p_clist.lockedLimit}" type="number"
										pattern="#,##0.00" />
								</td>
								<td width="13%" style=" text-align:right; padding-right:5px;">
									&nbsp;
									<fmt:formatNumber value="${p_clist.freeLimit}" type="number"
										pattern="#,##0.00" />
								</td>
								<c:if test="${roleId==7}">
									<td>
										<a
											href="javascript:window.location = '${ctx}/findProdCreditLimitById.do?prodId=${p_clist.prodId}'">修改</a>
									</td>
								</c:if>

							</tr>
						</logic:iterate>
					</table>
				</td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<td align="right">
					<br />
					<%@ include file="/jsp/common/newPage.jsp"%>
				</td>
				<td></td>
			</tr>
		</table>
	</body>
</html>
