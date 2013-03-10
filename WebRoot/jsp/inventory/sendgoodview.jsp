<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>发货单查看（库存管理）</title>
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
			});
		//-->
		$(function(){
			//打印
			$('#YuLanBtn').click(function(){
				window.open('getStockSendGoodsPrint.do?id=${sge.id}','newwindow');
			});
		});
		</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style></head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt 发货管理 &gt;&gt; 发货单查看</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;销售合同信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
            <td class="td_01" width="20%" height="18px">产品合同号</td>
            <td class="td_02" width="30%">${sge.productContractCode}</td>
            <td class="td_01" width="20%">公司合同号</td>
            <td class="td_02" width="30%">${sge.companyContarctCode}</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">客户名称</td>
            <td class="td_02">${sge.customerName}</td>
            <td class="td_01">要求发货日期</td>
            <td class="td_02">${sge.requestSendDate}</td>
          </tr>
          <tr>
            <td class="td_01" height="18px">人员名称</td>
            <td class="td_02">${sge.userName}</td>
            <td class="td_01">人员区域</td>
            <td class="td_02">${sge.areaname}</td>
          </tr>
      </table>
    <br />
<c:if test="${roleId!=12&&roleId!=13}">
    <div class="div_tiao"> &nbsp;&nbsp;付款信息 </div>
  	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
			<tr>
				<td class="td_01" width="20%" height="18px">付款方式</td>
				<td class="td_02" width="30%">
					<c:if test="${sge.paymentWay==0}">全部现结</c:if>
					<c:if test="${sge.paymentWay==1}">全部信用</c:if>
					<c:if test="${sge.paymentWay==2}">部分现结部分信用</c:if>
				</td>
				<c:if test="${sge.paymentWay==2}">
					<td class="td_01">现结金额</td>
					<td class="td_02" ><fmt:formatNumber value="${sge.cashMoney}" pattern="#,##0.00#"/></td>
				</c:if>
				<c:if test="${sge.paymentWay!=2}">
					<td class="td_01">&nbsp;</td>
					<td class="td_02">&nbsp;</td>
				</c:if>
				
			</tr>
			<c:if test="${sge.paymentWay==1||sge.paymentWay==2}">
				<tr>
					<td class="td_01" height="18px">分批方式</td>
					<td  class="td_02" >
						<c:if test="${sge.batchWay==0}">不分批
							</td><td  class="td_01"></td><td class="td_02">
						</c:if>
						<c:if test="${sge.batchWay==1}">分批
							</td>
							<td class="td_01">单批最大金额</td>
							<td  class="td_02" ><fmt:formatNumber value="${sge.batchMaxMoney}" pattern="#,##0.00#"/>
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
						<td style="text-align:right">${sge.arterm}&nbsp;</td>
						<td style="text-align:right"><fmt:formatNumber value="${sge.climit}" pattern="#,##0.00#"/>&nbsp;</td>
						<td style="text-align:right"><fmt:formatNumber value="${sge.free}" pattern="#,##0.00#"/>&nbsp;</td>
						<td style="text-align:right"><fmt:formatNumber value="${sge.sendPrepayMoney}" pattern="#,##0.00#"/>&nbsp;</td>
						<td style="text-align:right"><fmt:formatNumber value="${sge.sendTransitMoney}" pattern="#,##0.00#"/>&nbsp;</td>
					</tr>
				</table>
			</c:if>
	  
			<br/>
	 <div class="div_tiao"> &nbsp;&nbsp;产品明细 </div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td class="td_01" height="18px" width="20%">库房名称</td>
                <td class="td_02" width="30%">${sge.sroomName}</td>
                <td class="td_01">产品分类名称</td>
                <td class="td_02">${sge.ptName}</td>
            </tr>
   	  </table>
<div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1" style="border-left:1px solid #FFFFFF;">
			<tr>
				<th nowrap="nowrap" width="40" style="border-left:1px solid #c2c2c2;">序号</th>
				<th nowrap="nowrap">产品编码</th>
				<th nowrap="nowrap">产品名称</th>
				<th nowrap="nowrap">规格型号</th>
				<th nowrap="nowrap">单位</th>
				<th nowrap="nowrap">销售数</th>
				
				<c:if test="${roleId!=12&&roleId!=13}">
					<th nowrap="nowrap">销售单价</th>
					<th nowrap="nowrap">销售金额</th>
				</c:if>
				
				<th nowrap="nowrap">已发货数</th>
				<th nowrap="nowrap">其它库房备货数</th>
				<th nowrap="nowrap">本库房备货数</th>
				
				<c:if test="${roleId!=12&&roleId!=13}">
					<th nowrap="nowrap">回款指定金额</th>
					<th nowrap="nowrap">在途指定金额</th>
					<th nowrap="nowrap">信用金额</th>
					<th nowrap="nowrap">开票金额</th>
				</c:if>
				
				<th nowrap="nowrap">本发货单退货数</th>
				<th nowrap="nowrap">发货数</th>
				
				<c:if test="${roleId!=12&&roleId!=13}">
					<th nowrap="nowrap">发货金额</th>
				</c:if>
				
				<th nowrap="nowrap">发货可用数</th>
			</tr>
			<logic:present name="svsplist">
				<logic:iterate id="svsp" name="svsplist" indexId="index">
					<tr>
						<td nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18px">${index+1}</td>
						<td nowrap="nowrap" >${svsp.productcode}</td>
						<td nowrap="nowrap" >${svsp.productname}</td>
						<td nowrap="nowrap" >${svsp.producttype}</td>
						<td nowrap="nowrap" >${svsp.productunit}</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${svsp.count}</td>
						<c:if test="${roleId!=12&&roleId!=13}">
							<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${svsp.price}" pattern="#,##0.00#"/></td>
							<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${svsp.money}" pattern="#,##0.00#"/></td>
						</c:if>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${svsp.yfhnum}</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${svsp.qtkfnum}</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${svsp.bkfnum}</td>
						
						<c:if test="${roleId!=12&&roleId!=13}">
							<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${svsp.hkzdjemoney}" pattern="#,##0.00#"/></td>
							<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${svsp.ztzdjemoney}" pattern="#,##0.00#"/></td>
							<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${svsp.xyjemoney}" pattern="#,##0.00#"/></td>
							<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${svsp.kpjemoney}" pattern="#,##0.00#"/></td>
						</c:if>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${svsp.thsnum}</td>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${svsp.ffcount}</td>
						<c:if test="${roleId!=12&&roleId!=13}">
							<td nowrap="nowrap" style=" text-align:right; padding-right:5px;"><fmt:formatNumber value="${svsp.ffmoney}" pattern="#,##0.00#"/></td>
						</c:if>
						<td nowrap="nowrap" style=" text-align:right; padding-right:5px;">${svsp.fhkynum}</td>
					</tr>
				</logic:iterate>
	   		 </logic:present>
			<c:if test="${roleId!=12&&roleId!=13}">
			<tr>
				<td colspan="2" style=" border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
				<td colspan="15" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"> 发货金额合计 </td>
				<td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">
					<fmt:formatNumber value="${sge.money}" pattern="#,##0.00#"/>
				</td>
				<td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF"><span style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">元</span></td>
			</tr>
			</c:if>
		</table>
      <br />
        <div class="div_tiao"> &nbsp;&nbsp;货运信息 </div>
  	    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
			<tr>
				<td width="50%" class="td_01" height="18px">货物接收单位名称</td>
				<td colspan="3" class="td_02" >${sge.custname}</td>
			</tr>
			<tr>
				<td class="td_01" height="18px">发货地址</td>
				<td colspan="3" class="td_02" >${sge.address}</td>
			</tr>
			<tr>
				<td class="td_01" width="20%" height="18px">联系人</td>
				<td class="td_02" width="30%">${sge.linkman}</td>
				<td class="td_01" width="20%">邮编</td>
				<td class="td_02" width="30%">${sge.postcode}</td>
			</tr>
			<tr>
				<td class="td_01" height="18px">电话</td>
				<td class="td_02" >${sge.tel}</td>
				<td class="td_01">手机</td>
				<td class="td_02" >${sge.mobile}</td>
			</tr>
			<tr>
				<td class="td_01" height="18px">&nbsp;要求发货日期</td>
				<td class="td_02" >${sge.sendRequestDate}</td>
				<td class="td_01" >&nbsp;运货方式</td>
				<td class="td_02" >
				<c:if test="${sge.sendTransportWay==0}">无指定</c:if>
				<c:if test="${sge.sendTransportWay==1}">自提</c:if>
				<c:if test="${sge.sendTransportWay==2}">快递</c:if>
				<c:if test="${sge.sendTransportWay==3}">汽运</c:if>
				<c:if test="${sge.sendTransportWay==4}">铁运</c:if>
				<c:if test="${sge.sendTransportWay==5}">航空</c:if>
				<c:if test="${sge.sendTransportWay==6}">海运</c:if>
				<c:if test="${sge.sendTransportWay==7}">中铁</c:if>
				<c:if test="${sge.sendTransportWay==8}">市内送货</c:if>
				<c:if test="${sge.sendTransportWay==9}">市内快递</c:if>
				</td>
			</tr>
			<c:if test="${sge.sendTransportWay==1}">
			<tr>
				<td class="td_01" height="18px">提货人姓名</td>
				<td class="td_02" >${sge.sendTakeName}</td>
				<td class="td_01" >身份证号码</td>
				<td class="td_02">${sge.sendTakeNumber}</td>
			</tr>
			</c:if>
		</table>
  	    <br/>
        <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
            <td class="td_03" width="20%">特殊说明</td>
            <td class="td_04"  style="padding:5px 100px 5px 5px">&nbsp;${tf:replaceBr(sge.sendtext)}</td>
        </tr>
    </table>
    <br/>
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
            <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
			  <tr>
			  	<td height="20px" colspan="2">库房管理员意见：</td>
			  	</tr>
			  <tr>
			  	<td width="320" height="20px">1.产品规格是否符合</td>
			  	<td width="140" height="20px">
				  	<c:if test="${ideastr[0]==1}">
					  	<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" checked/>符合&nbsp;&nbsp;
						<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" />不符合
					</c:if>
					<c:if test="${ideastr[0]==0}">
					  	<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" />符合&nbsp;&nbsp;
						<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" checked/>不符合
					</c:if>
					<c:if test="${ideastr[0]==null}">
					  	<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" />符合&nbsp;&nbsp;
						<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" />不符合
					</c:if>
			  	</td>
			  </tr>
			  <tr>
			  	<td height="20px">2.产品质量是否符合</td>
			  	<td height="20px">
			  		<c:if test="${ideastr[1]==1}">
					  	<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" checked/>符合&nbsp;&nbsp;
						<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" />不符合
					</c:if>
					<c:if test="${ideastr[1]==0}">
					  	<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" />符合&nbsp;&nbsp;
						<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" checked/>不符合
					</c:if>
					<c:if test="${ideastr[1]==null}">
					  	<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" />符合&nbsp;&nbsp;
						<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" />不符合
					</c:if>
				</td>
			  </tr>
			  <tr>
			  	<td height="20px">3.产品数量是否符合</td>
			  	<td height="20px">
			  		<c:if test="${ideastr[2]==1}">
					  	<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" checked/>符合&nbsp;&nbsp;
						<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled"/>不符合
					</c:if>
					<c:if test="${ideastr[2]==0}">
					  	<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled"/>符合&nbsp;&nbsp;
						<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" checked />不符合
					</c:if>  	
					<c:if test="${ideastr[2]==null}">
					  	<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" />符合&nbsp;&nbsp;
						<input type="checkbox" name="checkbox5" id="checkbox5" disabled="disabled" />不符合
					</c:if>
				</td>
			  </tr>
			  <tr>
			  	<td colspan="2">
					<table width="438px" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td valign="top" width="65px" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                          <td style="padding:5px 0 5px 0;">${tf:replaceBr(sge.sendStkAdmText)}</td>
                        </tr>
                	</table>
                </td>
			  </tr>
			  <tr>
				<td height="20px">签字：${sge.sendStkAdmName}</td>
			  	<td>日期：${sge.sendStkAdmDate}</td>
		  	</tr>
      </table>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    <img id="YuLanBtn" src="${ctx}/images/btnYuLan.gif" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${ctx}/stockOrder.do?queryPara.init=true"><img src="${ctx}/images/btnBack.gif" /></a>
    </td>
    <td></td>
  </tr>
</table>
<br />
</body>
</html>
