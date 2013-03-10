<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>备货单查看</title>
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
		-->
		</style>
		<script language="JavaScript"> 
		<!--
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
					});
				}
				if($("#table1")){
					$("#table1 tr:even").addClass("treven");
					$("#table1 tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
				if('${ProMajIder[0]}'=='1'){
		        	$('#ProMajIder1').attr("checked",'true');
		        }
		        if('${ProMajIder[0]}'=='0'){
		        	$('#ProMajIder0').attr("checked",'true');
		        }
		        if('${AreaMajId[0]}'=='1'){
		        	$('#AreaMajId1').attr("checked",'true');
		        }
		        if('${AreaMajId[0]}'=='0'){
		        	$('#AreaMajId0').attr("checked",'true');
		        }
			});
		//-->
		</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt; 发货管理 &gt;&gt; 备货单查看</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
    <div class="div_tiao"> &nbsp;&nbsp;备货单信息 </div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01">预计发货日期</td>
            <td class="td_02" height="18px">${sge.sendRequestDate}&nbsp;</td>
            <td class="td_01"></td>
            <td class="td_02"></td>
          </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;销售合同信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" width="20%" height="18px">产品合同号</td>
            <td class="td_02" width="30%">${sge.productContractCode}&nbsp;</td>
            <td class="td_01" width="20%">公司合同号</td>
            <td class="td_02" width="30%">${sge.companyContarctCode}&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">客户名称</td>
            <td class="td_02">${sge.sendCustomerName}&nbsp;</td>
            <td class="td_01">要求发货日期</td>
            <td class="td_02">${sge.requestSendDate}&nbsp;</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">人员名称</td>
            <td class="td_02">${sge.userName}&nbsp;</td>
            <td class="td_01">人员区域</td>
            <td class="td_02">${sge.areaname}&nbsp;</td>
          </tr>
		  <tr>
            <td class="td_01" height="18px">项目名称</td>
            <td class="td_02">${sge.contractProName}&nbsp;</td>
            <td class="td_01">&nbsp;</td>
            <td class="td_02">&nbsp;</td>
          </tr>
      </table>
    <br />
      <div class="div_tiao"> &nbsp;&nbsp;付款信息 </div>
  	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
			<tr>
				<td class="td_01" width="20%" height="18px">付款方式</td>
				<td class="td_02" width="30%">
					<c:if test="${sge.paymentWay==0}">全部现结&nbsp;</c:if>
					<c:if test="${sge.paymentWay==1}">全部信用&nbsp;</c:if>
					<c:if test="${sge.paymentWay==2}">部分现结部分信用&nbsp;</c:if>
				</td>
				<c:if test="${sge.paymentWay==2}">
					<td class="td_01">现结金额</td>
					<td class="td_02"><fmt:formatNumber value="${sge.cashMoney}" pattern="#,##0.00#"/>&nbsp;元</td>
				</c:if>
				<c:if test="${sge.paymentWay!=2}">
					<td class="td_01">&nbsp;</td>
					<td class="td_02" >&nbsp;</td>
				</c:if>
				
			</tr>
			<c:if test="${sge.paymentWay==1||sge.paymentWay==2}">
				<tr>
					<td class="td_01" height="18px">分批方式</td>
					<td  class="td_02" >
						<c:if test="${sge.batchWay==0}">不分批&nbsp;
							</td><td class="td_01">&nbsp;</td><td class="td_02">&nbsp;
						</c:if>
						<c:if test="${sge.batchWay==1}">分批&nbsp;
							</td>
							<td class="td_01">单批最大金额</td>
							<td  class="td_02"><fmt:formatNumber value="${sge.batchMaxMoney}" pattern="#,##0.00#"/>&nbsp;元</td>
						</c:if>
					</td>
				</tr>
			</c:if>
		</table>
		<div style="width:100%; text-align:right">单位：元</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
				<tr>
					<th>信用类型名称</th>
					<th>项目名称</th>
					<th>帐期(天)</th>
					<th>信用额度</th>
					<th>可用额度</th>
					<th>回款预收金额</th>
					<th>在途预收金额</th>
				</tr>
				<tr>
					<td height="18px">${sge.creditname}&nbsp;</td>
					<td>${sge.sendProjectName}&nbsp;</td>
					<td style='text-align:right;padding-right:5px;'>${sge.arterm}</td>
					<td style='text-align:right;padding-right:5px;'><fmt:formatNumber value="${sge.climit}" pattern="#,##0.00#"/></td>
					<td style='text-align:right;padding-right:5px;'><fmt:formatNumber value="${sge.free}" pattern="#,##0.00#"/></td>
					<td style='text-align:right;padding-right:5px;'><fmt:formatNumber value="${sge.sendPrepayMoney}" pattern="#,##0.00#"/></td>
					<td style='text-align:right;padding-right:5px;'><fmt:formatNumber value="${sge.sendTransitMoney}" pattern="#,##0.00#"/></td>
				</tr>
			</table>
			<br/>
	 <div class="div_tiao"> &nbsp;&nbsp;产品明细 </div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td class="td_01" height="18px" width="20%">库房名称</td>
                <td class="td_02" width="30%">${sge.sroomName}&nbsp;</td>
                <td class="td_01" width="20%">产品分类名称</td>
                <td class="td_02" width="30%">${sge.ptName}&nbsp;</td>
            </tr>
   	  </table>
	  <div style="width:100%; text-align:right">单位：元</div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1" style="border-left:1px solid #FFFFFF;">
			<tr>
				<th nowrap="nowrap" width="40" style=" border-left:1px solid #c2c2c2;" height="18px">序号</th>
				<th nowrap="nowrap">产品编码</th>
				<th nowrap="nowrap">产品名称</th>
				<th nowrap="nowrap">规格型号</th>
				<th nowrap="nowrap">单位</th>
				<th nowrap="nowrap">销售数</th>
				<th nowrap="nowrap">销售单价</th>
				<th nowrap="nowrap">销售金额</th>
				<th nowrap="nowrap">备货数</th>
				<th nowrap="nowrap">其它库房备货数</th>
				<th nowrap="nowrap">本库房备货数</th>
				<th nowrap="nowrap">备货金额</th>
				<th nowrap="nowrap">退货数</th>
				<th nowrap="nowrap">备货可用数</th>
			</tr>
				<logic:present name="list">
				<logic:iterate id="ssp" name="list" indexId="index">
					<tr>
						<td nowrap="nowrap" style=" border-left:1px solid #c2c2c2;" height="18px">${index+1}</td>
						<td nowrap="nowrap" >${ssp.productcode}&nbsp;</td>
						<td nowrap="nowrap" >${ssp.productname}&nbsp;</td>
						<td nowrap="nowrap" >${ssp.producttype}&nbsp;</td>
						<td nowrap="nowrap" >${ssp.productunit}&nbsp;</td>
						<td nowrap="nowrap" width="130px" style="text-align:right">${ssp.count}&nbsp;</td>
						<td nowrap="nowrap" width="130px" style="text-align:right"><fmt:formatNumber value="${ssp.price}" pattern="#,##0.00#"/>&nbsp;</td>
						<td nowrap="nowrap" width="80px" style="text-align:right"><fmt:formatNumber value="${ssp.money}" pattern="#,##0.00#"/>&nbsp;</td>
						<td width="80px" nowrap="nowrap" style="text-align:right">${ssp.yfhnum}&nbsp;</td>
						<td width="80px" nowrap="nowrap" style="text-align:right">${ssp.qtkfnum}&nbsp;</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${ssp.bkfnum}</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${ssp.bfmoney}" pattern="#,##0.00#"/></td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${ssp.htthsnum}</td>
						<td nowrap="nowrap" width="80px" style="text-align:right">${ssp.fhkynum}&nbsp;</td>
					</tr>
				</logic:iterate>
	   		 </logic:present>
			<tr>
				<td colspan="2" style=" border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
				<td colspan="9" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 备货金额合计 </td>
				<td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">
					<fmt:formatNumber value="${sge.money}" pattern="#,##0.00#"/>
				</td>
				<td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF"><span style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">元</span></td>
			</tr>
		</table>
   	  <br/>
        <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
            <td class="td_03" width="20%">特殊说明</td>
            <td class="td_04" style="padding:5px 100px 5px 5px"${tf:replaceBr(sge.sendtext)}>&nbsp;</td>
        </tr>
    </table>
    <br/>
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
            <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
              <tr>
                <td height="20px" colspan="2" >区域总监评审意见：</td>
              </tr>
              <tr>
                <td width="320" nowrap="nowrap">&nbsp;</td>
                <td height="20px" width="140px" nowrap="nowrap" algin="right">
					<input type="checkbox" name="AreaMajId1" id="AreaMajId1" disabled="disabled" />同意&nbsp;&nbsp;
					<input type="checkbox" name="AreaMajId0" id="AreaMajId0" disabled="disabled" />不同意
                </td>
              </tr>
              <tr>
                <td colspan="2">
					<table width="438px" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td valign="top" width="65px" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                      <td style="padding:5px 0 5px 0;" >${tf:replaceBr(sge.sendAreaMajText)}&nbsp;</td>
                    </tr>
                </table></td>
              </tr>
              <tr>
                <td width="320" height="20px">签字：${sge.sendAreaMajName}</td>
				<td width="140">日期：${sge.sendAreaMajDate}</td>
             </tr>
             <tr>
                <td height="20px" colspan="2" >销售总监评审意见：</td>
              </tr>
              <tr>
                <td width="320" nowrap="nowrap">&nbsp;</td>
                <td height="20px" width="140px" nowrap="nowrap" algin="right">
					<input type="checkbox" name="ProMajIder1" id="ProMajIder1" disabled="disabled" />同意&nbsp;&nbsp;
					<input type="checkbox" name="ProMajIder0" id="ProMajIder0" disabled="disabled" />不同意
                </td>
              </tr>
              <tr>
                <td colspan="2">
					<table width="438px" border="0" cellpadding="0" cellspacing="0">
                    <tr>
                      <td valign="top" width="65px" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                      <td style="padding:5px 0 5px 0;" >${tf:replaceBr(sge.sendProMajText)}&nbsp;</td>
                    </tr>
                </table></td>
              </tr>
              <tr>
                <td width="320" height="20px">签字：${sge.sendProMajName}</td>
				<td width="140">日期：${sge.sendProMajDate}</td>
             </tr>
            </table>
                
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom"><a href="javascript:window.location ='${ctx}/getSendGoodsList.do'"><img src="${ctx}/images/btnBack.gif" /></a></td>
    <td></td>
  </tr>
</table>
<br />
</body>
</html>
