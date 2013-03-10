<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>销售合同管理</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
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
			$(document).ready(function(){
				if($("#table")){
					$("#table tr:even").addClass("treven");
					$("#table tr").each(function(i){
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
		    	var msg = "${msg}";  //获取服务端信息
				
				if(msg!=""){
			    	alert(msg);
				}
 
       		 });	
       		 		
      		 function querySubmit() {
      		 	trimText();
				$("#frmConList").submit();
			 }
			 
			 function delSalesContract(){
			 	if(!confirm('确实要删除吗？')){
			 		return false;
				}
			 }
		function trimText(){
	        var fom = document.forms[0];
	        for(var i=0;i<fom.elements.length;i++){
	            if(fom.elements[i].type == 'text'){
	               fom.elements[i].value = $.trim(fom.elements[i].value);
	            }
	        }
	    }
		</script>
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="ye_header_left" width="16px" ><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
				<td class="ye_header_center" style="font-size:12px">
					<img src="${ctx}/images/main_jt.jpg" />
					&nbsp;当前位置： 销售管理 &gt;&gt; 销售合同管理
				</td>
				<td class="ye_header_right">
					&nbsp;
				</td>
			</tr> 
			<%-- 检索条件区域 --%>
			<tr>
				<td>
					&nbsp;
				</td>
				<td align="center">
					<form action="getSalesContractsList.do" id="frmConList"> 
						<br />
						<div class="mo_wp">
							<div style="display: ; " class="mo_con">
								<table width="100%" border="0" cellpadding="0" cellspacing="0"
									class="biao3"> 
									<tr>
										<td class="td_01" width="20%">
											产品分类名称
										</td>
										<td class="td_02" width="30%"> 
											<html:select property="productTypeId" value="${queryPara.productTypeId}" style=" width:126px">
												<html:option value="">--请选择--</html:option> 
												<html:options collection="productTypeList" property="id" labelProperty="name"/>
											</html:select>
										</td>
										<td class="td_01" width="20%">
											产品合同号
										</td>
										<td class="td_02" width="30%">
											<input type="text" name="productContractCode"
												style="width:120px;" value="${queryPara.productContractCode}">
										</td> 
									</tr> 
									<tr>
										<td class="td_01" width="20%">
											公司合同号
										</td>
										<td class="td_02" width="30%">
											<input type="text" name="companyContarctCode"
												style="width:120px;" value="${queryPara.companyContractCode}">
										</td> 
										<td class="td_01" width="20%">
											客户名称
										</td>
										<td class="td_02" width="30%">
											<input type="text" name="customerName" style="width:240px;" value="${queryPara.customerName}"/>
										</td>
									</tr> 
									<tr> 
										<td class="td_01">
											模版类型
										</td>
										<td class="td_02"> 
											<html:select property="templateType" value="${queryPara.templateType}" style=" width:126px">
												<html:option value="">--请选择--</html:option> 
												<html:option value="0">标准模板</html:option> 
												<html:option value="1">自定义模板</html:option>
												<html:option value="2">非模板</html:option>
											</html:select>
										</td>
										<td class="td_01" width="20%">
											付款方式
										</td>
										<td class="td_02" width="30%"> 
											<html:select property="paymentWay" value="${queryPara.paymentWay}" style=" width:126px">
												<html:option value="">--请选择--</html:option> 
												<html:option value="0">全部现结</html:option> 
												<html:option value="1">全部信用</html:option>
												<html:option value="2">现结信用</html:option> 
											</html:select> 
										</td>
									</tr> 
									<tr>
										<td class="td_01">
											合同签订起始日期
										</td>
										<td class="td_02">
											<input type="text" name="starttime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.starttime}"/>
										</td>
										<td class="td_01">
											合同签订终止日期
										</td>
										<td class="td_02">
											<input type="text" name="endtime" maxlength="12" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryPara.endtime}"/>
										</td>
									</tr> 
									<tr>  
										<td class="td_01" width="20%">
											人员名称
										</td>
										<td class="td_02" width="30%"> 
											<input type="text" name="userName" style="width:120px;" value="${queryPara.userName}">
										</td>  
										<td class="td_01" width="20%">
											合同状态
										</td>
										<td class="td_02" width="30%"> 
											<html:select property="status" value="${queryPara.status}" style=" width:126px">
												<html:option value="">--请选择--</html:option>  
												<html:option value="1">未提交</html:option>
												<html:option value="2">产品总监待评审</html:option> 
												<html:option value="3">产品总监未通过</html:option> 
												<html:option value="4">法务专员待评审</html:option>
												<html:option value="5">法务专员未通过</html:option> 
												<html:option value="16">区域总监待评审</html:option>
												<html:option value="17">区域总监未通过</html:option>
												<html:option value="6">销售总监待评审</html:option> 
												<html:option value="7">销售总监未通过</html:option>
												<html:option value="8">运营助理待评审</html:option> 
												<html:option value="9">运营助理未通过</html:option> 
												<html:option value="10">运营总监待评审</html:option>
												<html:option value="11">运营总监未通过</html:option> 
												<html:option value="12">待打印</html:option> 
												<html:option value="13">待确认</html:option> 
												<html:option value="14">合同生效</html:option> 
												<html:option value="15">合同取消</html:option> 
											</html:select> 
										</td> 
									</tr>
									<tr>
										<td class="td_01" width="20%" align="left">
											项目名称
										</td>
										<td class="td_02" width="30%"> 
											<input type="text" name="contractProName" style="width:120px;" value="${queryPara.contractProName}" maxlength="50">
										</td>
										<td class="td_01" width="20%">
											发货完成状态
										</td>
										<td class="td_02" width="30%">
											<html:select property="unfinished" value="${queryPara.unfinished}" style=" width:126px">
												<html:option value="">--请选择--</html:option>
												<html:option value="1">发货未完成</html:option>
												<html:option value="2">发货完成</html:option>
											</html:select> 
										</td>  
									</tr>
									
									<tr>
										<td colspan="4" align="center" style="height:30px;">
											<a href="javascript:querySubmit();"><img
													src="${ctx}/images/btn_JianSuo.gif" /> </a>
										</td>
									</tr>
								</table>
							</div>
							<div class="mo_title" onclick="fMainListToggle(this)">
								<div style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
							</div>
						</div>
					</form>
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
			<%-- 检索结果显示区域 --%>
			<tr>
				<td>
					&nbsp;
				</td>
				<td align="center">
				<div style="width:100%; text-align:right;font-size:12px">单位：元</div>
					<form action="salesContractList.do">
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
							<tr>
								<th nowrap="nowrap">
									合同流水号
								</th>
								<th nowrap="nowrap">
									产品分类名称
								</th>
								<th nowrap="nowrap">
									产品合同号
								</th>
								<th nowrap="nowrap">
									公司合同号
								</th>
								<th nowrap="nowrap">
									客户名称
								</th>
								<th nowrap="nowrap">
									项目名称
								</th>
								<th nowrap="nowrap">
									模版类型
								</th>
								<th nowrap="nowrap">
									付款方式
								</th>
								<th nowrap="nowrap">
									签订日期
								</th>
								<th nowrap="nowrap">
									人员名称
								</th>
								<th nowrap="nowrap">
									合同状态
								</th>
								<th nowrap="nowrap">
									合同总额
								</th>
								<th nowrap="nowrap">
									发货金额
								</th>
								<th nowrap="nowrap">
									备货金额
								</th>
								<th nowrap="nowrap" title="发货成功且不包含在途">
									指定金额
								</th>
								<th nowrap="nowrap">
									预收金额
								</th>
								<th nowrap="nowrap">
									开票金额
								</th>
								<th nowrap="nowrap">
									退货合同金额
								</th>
								<th nowrap="nowrap">
									退货金额
								</th>
								<th nowrap="nowrap">
									操作
								</th>
							</tr>
							 
							<logic:iterate id="sellContract" name="sellContractList" >
								<tr>
									<td nowrap="nowrap" width="96px" height="18px">
										${sellContract.sellContractId}&nbsp;
									</td>
									<td nowrap="nowrap" width="72px">
										${sellContract.productTypeName}&nbsp;
									</td>
									<td nowrap="nowrap" width="96px">
										${sellContract.productContractCode}&nbsp;
									</td>
									<td nowrap="nowrap" width="110px">
										${sellContract.companyContractCode}&nbsp;
									</td>
									<td nowrap="nowrap" width="85px" title="${sellContract.customerName}"><div class=ellipsis_div style="width:80px;">
										${sellContract.customerName}</div>
									</td>
									<td nowrap="nowrap" width="85px" title="${sellContract.contractProName}"><div class=ellipsis_div style="width:80px;">
										${sellContract.contractProName}</div>
									</td>
									<td nowrap="nowrap">
										<c:if test="${sellContract.sellContractTemplateType==0}">标准模板</c:if>
										<c:if test="${sellContract.sellContractTemplateType==1}">自定义模板</c:if>
										<c:if test="${sellContract.sellContractTemplateType==2}">非模板</c:if>&nbsp;
									</td> 
									<td nowrap="nowrap" width="70px">
										<c:if test="${sellContract.sellContractPaymentWay==0}">全部现结</c:if>
										<c:if test="${sellContract.sellContractPaymentWay==1}">全部信用</c:if>
										<c:if test="${sellContract.sellContractPaymentWay==2}">现结信用</c:if>&nbsp;</td>
									<td nowrap="nowrap" width="72px">
										${sellContract.sellContractDate}&nbsp;
									</td>
									<td nowrap="nowrap" width="72px">
										${sellContract.sellContractUserName}&nbsp;
									</td>
									<td nowrap="nowrap" ><div class=ellipsis_div style="width:95px;">
										<c:if test="${sellContract.sellContractStatus==1}">未提交</c:if>
										<c:if test="${sellContract.sellContractStatus==2}">产品总监待评审</c:if>
										<c:if test="${sellContract.sellContractStatus==3}">产品总监未通过</c:if>
										<c:if test="${sellContract.sellContractStatus==4}">法务专员待评审</c:if>
										<c:if test="${sellContract.sellContractStatus==5}">法务专员未通过</c:if>
										<c:if test="${sellContract.sellContractStatus==16}">区域总监待评审</c:if>
										<c:if test="${sellContract.sellContractStatus==17}">区域总监未通过</c:if>
										<c:if test="${sellContract.sellContractStatus==6}">销售总监待评审</c:if>
										<c:if test="${sellContract.sellContractStatus==7}">销售总监未通过</c:if>
										<c:if test="${sellContract.sellContractStatus==8}">运营助理待评审</c:if>
										<c:if test="${sellContract.sellContractStatus==9}">运营助理未通过</c:if>
										<c:if test="${sellContract.sellContractStatus==10}">运营总监待评审</c:if>
										<c:if test="${sellContract.sellContractStatus==11}">运营总监未通过</c:if>
										<c:if test="${sellContract.sellContractStatus==12}">待打印</c:if>
										<c:if test="${sellContract.sellContractStatus==13}">待确认</c:if>
										<c:if test="${sellContract.sellContractStatus==14}">合同生效</c:if>
										<c:if test="${sellContract.sellContractStatus==15}">合同取消</c:if></div>
									</td>
									<td nowrap="nowrap" width="90px" style=" text-align:right; padding-right:6px;" >
									<fmt:formatNumber value="${sellContract.sellContractMoney}" type="number" pattern="#,##0.00" />
									</td>
									<td nowrap="nowrap" width="90px" style=" text-align:right;padding-right:6px;" >
									<fmt:formatNumber value="${sellContract.deliveryAmountDeliveryAmountMoney}" type="number" pattern="#,##0.00" />
									</td>  
									<td nowrap="nowrap" width="90px" style=" text-align:right;padding-right:6px;" >
									<fmt:formatNumber value="${sellContract.reserveAmountReserveAmountMoney}" type="number" pattern="#,##0.00" />
									</td>
									<td nowrap="nowrap" width="90px" style=" text-align:right;padding-right:6px;" >
									<fmt:formatNumber value="${sellContract.specifyAmountSpecifyAmountMoney}" type="number" pattern="#,##0.00" />
									</td>
									<td nowrap="nowrap" width="90px" style=" text-align:right;padding-right:6px;" >
									<fmt:formatNumber value="${sellContract.advanceAmountAdvanceAmountMoney + sellContract.freezeAmounFreezeAmountMoney}" type="number" pattern="#,##0.00" />
									</td>
									<td nowrap="nowrap" width="90px" style=" text-align:right;padding-right:6px;" >
									<fmt:formatNumber value="${sellContract.makeAmountMakeAmountMoney}" type="number" pattern="#,##0.00" />
									</td>
									<td nowrap="nowrap" width="90px" style=" text-align:right;padding-right:6px;" >
									<fmt:formatNumber value="${sellContract.backAmountBackAmountMoney}" type="number" pattern="#,##0.00" />
									</td>
									<td nowrap="nowrap" width="90px" style=" text-align:right;padding-right:6px;" >
									<fmt:formatNumber value="${sellContract.sellbackAmountSellbackAmountMoney}" type="number" pattern="#,##0.00" />
									</td>
									<td nowrap="nowrap">
									<c:if		  test="${user.roleId==3||user.roleId==4||user.roleId==5||user.roleId==6||user.roleId==7||user.roleId==9||user.roleId==10||user.roleId==11||user.roleId==15||user.roleId==16||user.roleId==17||user.roleId==18||user.roleId==19||user.roleId==20}"> 
											<a href="javascript:window.location='${ctx}/salesContractPreview.do?salesContractId=${sellContract.sellContractId}';">查看</a>
											<c:if test="${user.roleId==4}">
												<c:choose>
													<c:when
														test="${sellContract.sellContractStatus==1||sellContract.sellContractStatus==3||sellContract.sellContractStatus==5||sellContract.sellContractStatus==7||sellContract.sellContractStatus==9||sellContract.sellContractStatus==11||sellContract.sellContractStatus==15||sellContract.sellContractStatus==17}">
														<a href="javascript:window.location='${ctx}/showSalesContract.do?contractId=${sellContract.sellContractId}';">修改</a>														
														<a href="${ctx}/deleteSalesContract.do?contractId=${sellContract.sellContractId}"  onClick="return delSalesContract()">删除</a>
													</c:when>
													<c:otherwise>
														修改
														删除
													</c:otherwise>
												</c:choose>
												<c:choose>
														<c:when
															test="${sellContract.sellContractStatus==14}">
															 <a href="${ctx}/addCreateSendGoodsInit.do?sellContractId=${sellContract.sellContractId}">发货</a>
															 <a href="${ctx}/addCreateReserveGoodsInit.do?sellContractId=${sellContract.sellContractId}">备货</a>
															 <html:link action="addSalesBackContractOfShow.do" paramId="id" paramName="sellContract" paramProperty="sellContractId">退货合同</html:link>
														</c:when>
														<c:otherwise>
															发货
															备货
															退货合同
														</c:otherwise>
												</c:choose>
											</c:if> 
									</c:if>
									<c:if
										test="${user.roleId==9||user.roleId==5||user.roleId==10||user.roleId==15||user.roleId==16||user.roleId==17}">
										 
											
											<a href="javascript:void(0);" onclick="javascript:window.open ('${ctx}/auditPreview.do?salesContractId=${sellContract.sellContractId}','newwindow', 'toolbar=no,scrollbars=yes,resizable=yes,top=200,left=370, width=700,height=400');">查看评审表</a>
											<c:choose>
												<c:when
													test="${user.roleId==9&&sellContract.sellContractStatus==16||user.roleId==5&&sellContract.sellContractStatus==6||user.roleId==10&&sellContract.sellContractStatus==2||user.roleId==15&&sellContract.sellContractStatus==4||user.roleId==16&&sellContract.sellContractStatus==8||user.roleId==17&&sellContract.sellContractStatus==10}"> 
													<a
														href="javascript:window.location = '${ctx}/showSalesContractForAssess.do?contractId=${sellContract.sellContractId}';">评审</a>
												</c:when>
												<c:otherwise> 
									        		评审
								        		</c:otherwise>
											</c:choose>
										 
									</c:if>
									<c:if test="${user.roleId==18||user.roleId==20}">
										 
											<a href="javascript:void(0);" onclick="javascript:window.open ('${ctx}/auditPreview.do?salesContractId=${sellContract.sellContractId}','newwindow', 'toolbar=no,scrollbars=yes,resizable=yes,top=200,left=370, width=700,height=400');">查看评审表</a>
										 
									</c:if>
									<c:if test="${user.roleId==3}"> 
											<a href="javascript:void(0)" onclick="javascript:window.open ('${ctx}/auditPreview.do?salesContractId=${sellContract.sellContractId}&printAudit=true','newwindow', 'toolbar=no,scrollbars=yes,resizable=yes,top=200,left=370, width=700,height=400');">查看评审表</a> 
											<c:choose>
											<c:when test="${sellContract.sellContractStatus==13}">
												<a href="javascript:window.location='${ctx}/salesContractPreview.do?salesContractId=${sellContract.sellContractId}&command=effective';">确认</a>
											</c:when>
											<c:otherwise> 
												确认
								        	</c:otherwise>
											</c:choose>
										
									</c:if>
									</td>
								</tr>
							</logic:iterate>
						</table>
						<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table" style="padding-top:10px">
							<tr>
								<td width="100px">
									<c:if test="${user.roleId==4}">
										<a href="${ctx}/salesContractCreate.do?"><img src="${ctx}/images/btnXinJian.gif" width="112" height="20" /></a>
									</c:if> 
								</td>
								<td align="right" height="25" style="font-size:12px">
									<%@ include file="/jsp/common/newPage.jsp"%>
								</td>
							</tr>
						</table> 
					</form>
				</td>
				<td>
					&nbsp;
				</td>
			</tr>
		</table>

	</body>
</html>
