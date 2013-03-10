<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>客户信用管理</title>
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
				if($("#table1")){
					$("#table1 tr:even").addClass("treven");
					$("#table1 tr").each(function(i){
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
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
		
		<script type="text/javascript">
			
			$(document).ready(function(){
		    	var msg = "${msg}";  //获取服务端信息          
		
				if(msg!=""){
				    alert(msg);
				}
				
				$("#cstmrCrdtForm").validate({
						rules: {								
							"creditLimit": {number:true,min:0},									
							"arterm": {number:true,min:0}					
						}
							
			   });	
			
        	});
        	
        	function sureDelete() {
        	
        		return confirm("确认删除 ?");        	
        		
        	};
	
		</script>
	</head>

	<body>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
					<td class="ye_header_center">
						<img src="${ctx}/images/main_jt.jpg" />
						&nbsp;当前位置： 信用管理 &gt;&gt; 客户信用管理
					</td>
					<td class="ye_header_right">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="center">
                    <form action="${ctx}/customerCreditManage.do" method="get" id="cstmrCrdtForm">
						<div class="mo_wp">
							<div style="display: ; " class="mo_con">
							<br />
								<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3"> 
									<tr>
										<td class="td_01" width="20%">
											客户名称
										</td>
										<td class="td_02" width="30%">
											<input type="text" name="customerName"
												value="${customerCredit.customerName}" style="width:240px;" maxlength="40"/>
										</td>
										<td class="td_01" width="20%">
											产品分类名称
										</td>
										<td class="td_02" width="30%">
											<select name="productTypeId" style=" width:126px"
										id="productTypeId"  >
										<option value="" selected="selected">
											--请选择--
										</option>
										<c:forEach var="productType" items="${productTypeList}">
											<option value="${productType.id}"
												<c:if test="${productType.id == customerCredit.productTypeId}">
													selected = "selected"
												</c:if>
											>
												${productType.name}
											</option>
										</c:forEach>	
									</select>
										</td>
									</tr>
									<tr>
										<td class="td_01" width="20%">
											信用类型
										</td>
										<td class="td_02" width="30%">
											<select name="creditTypeId" style=" width:126px" >
										<option value="" selected="selected">
											--请选择--
										</option>
										
										<c:forEach var="creditType" items="${creditTypeList}">
											<option value="${creditType.id}"
												<c:if test="${creditType.id == customerCredit.creditTypeId}">
													selected = "selected"
												</c:if>
											>
												${creditType.name}
											</option>												
										</c:forEach>
									</select>
										</td>
										<td class="td_01" width="20%">
											信用额度
										</td>
										<td class="td_02" width="30%">
											<input type="text" name="creditLimit"  id="creditLimit" style="width:120px;" value="${customerCredit.creditLimit}" maxlength="11"/>
											元
										</td>
									</tr>
									<tr>
										<td class="td_01" width="20%">
											账期
										</td>
										<td class="td_02" width="30%">
											<input type="text" name="arterm" style="width:120px;" value="${customerCredit.arterm}" maxlength="3"/>
											天
										</td>
										<td class="td_01" width="20%">
											状态
										</td>
										<td class="td_02" width="30%">
											<select name="enable" style=" width:126px">
												
												<option value="" selected="selected">
													--请选择--
												</option>
												
												<option value="1" 
													<c:if test='${customerCredit.enable == "1"}'>
														selected="selected"
													</c:if>	
												>
													启用
												</option>
											
												<option value="0" 
													<c:if test='${customerCredit.enable == "0"}'>
														selected="selected"
													</c:if>
												>
													停用
												</option>
												
											</select>
										</td>
									</tr>
									<tr>
										<td colspan="4" align="center" height="30px">
											<a href="#" onclick="$('#cstmrCrdtForm').submit();"> <img src="${ctx}/images/btn_JianSuo.gif" /> </a>
										</td>
									</tr>
								</table>
							</div>
							<div class="mo_title" onclick="fMainListToggle(this)">
								<div style="text-align:center;"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
							</div>
						</div>
						</form>
					</td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td align="center">
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1">
							<div style="width:100%; text-align:right">单位：元</div>
							<tr>
								<th nowrap="nowrap">
									客户名称
								</th>
								<th nowrap="nowrap" width="96px">
									产品分类名称
								</th>
								<th nowrap="nowrap" width="96px">
									信用类型
								</th>
								<th nowrap="nowrap" width="120px">
									项目名称
								</th>
								<th nowrap="nowrap" width="70px">
									&nbsp;账期(天)&nbsp;
								</th>
								<th nowrap="nowrap" width="84px">
									信用额度
								</th>
								<th nowrap="nowrap" width="84px">
									已用额度
								</th>
								<th nowrap="nowrap" width="84px">
									冻结额度
								</th>
								<th nowrap="nowrap" width="84px">
									可用额度
								</th>
								<th nowrap="nowrap" with="90px">
									在途款指定金额
								</th>
								<th nowrap="nowrap" width="84px">
									超期欠款金额
								</th>
								<th nowrap="nowrap" width="84px">
									最大超期天数
								</th>
								<th nowrap="nowrap" width="45px">
									&nbsp;状态&nbsp;
								</th>
								<th nowrap="nowrap" width="60px">
									更新人
								</th>
								<th nowrap="nowrap" width="60px">
									更新时间
								</th>
								<th nowrap="nowrap">
									&nbsp;操作&nbsp;
								</th>
							</tr>
							
								<c:forEach var="customerCredit" items="${customerCreditList}">
									
										<tr>
											
											<td nowrap="nowrap" height="18px" title="${customerCredit.customerName}">
											<div class="ellipsis_div" style="width:180px;">${customerCredit.customerName}&nbsp;</div></td>
											<td nowrap="nowrap">
												${customerCredit.productTypeName}&nbsp;
											</td>
											<td nowrap="nowrap">
												${customerCredit.creditTypeName}&nbsp;
											</td>
											<td nowrap="nowrap">
												${customerCredit.projectName}&nbsp;
											</td>
											<td nowrap="nowrap"
												style=" text-align:right; padding-right:5px;">
												${customerCredit.arterm}
											</td>
											<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">
												<fmt:formatNumber value="${customerCredit.creditLimit}" type="number"
													pattern="#,##0.00" />
											</td>
											<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">
												
												<fmt:formatNumber value="${customerCredit.usedCredit}" type="number"
													pattern="#,##0.00" />
											</td>
											<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">
												
												<fmt:formatNumber value="${customerCredit.lockCredit}" type="number"
													pattern="#,##0.00" />
											</td>
											
											<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">
												
												<fmt:formatNumber value="${customerCredit.canUseCredit}" type="number" pattern="#,##0.00" />
											</td>
											<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">
												
												<fmt:formatNumber value="${customerCredit.inTransitMoney}" type="number"
													pattern="#,##0.00" />
											</td>
											<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">
													&nbsp;<fmt:formatNumber value="${customerCredit.exceedOweMoney}" type="number" pattern="#,##0.00" />
											</td>
											<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">
												&nbsp;
											${customerCredit.maxExceedDays}
											</td>
											<td nowrap="nowrap">
									
													<c:if test='${customerCredit.enable == "1"}'>
														启用
													</c:if>	
													<c:if test='${customerCredit.enable == "0"}'>
														停用
													</c:if>
											</td>
											<td nowrap="nowrap">
												${customerCredit.userName}&nbsp;
											</td>
											<td nowrap="nowrap">
												${customerCredit.datetime}&nbsp;
											</td>
											<td nowrap="nowrap">
											    <c:if test="${authority == 'check'}">
													<a href="${ctx}/viewCustomerCredit.do?customerCreditId=${customerCredit.id}">查看</a>
												</c:if>
												
												<c:if test="${authority == 'modify'}">
													<a href="${ctx}/showCustomerCredit.do?customerCreditId=${customerCredit.id}&customerId=${customerCredit.customerId}">修改</a>
													
													<a href="${ctx}/removeCustomerCredit.do?customerCreditId=${customerCredit.id}
															&stamp=${customerCredit.timeStamp.time}
															&pid=${customerCredit.productTypeId}
															&cid=${customerCredit.creditTypeId}" onclick="return sureDelete();">
													删除</a>
												</c:if>
											</td>
										</tr>
									</c:forEach>
						</table>
						<br />
						<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
							<tr>
								<c:if test="${authority == 'modify'}">
									<td align="left">
										<a href="${ctx}/forwardNewCustomerCreditPage.do"><img src="${ctx}/images/btnAdd.gif" /></a>	
									</td>
								</c:if>
								<td align="right">
									<%@ include file="/jsp/common/newPage.jsp"%>
								</td>
							</tr>
						</table>
					</td>
					<td>&nbsp;</td>
				</tr>
			</table>
	</body>
</html>
