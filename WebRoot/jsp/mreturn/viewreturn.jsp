<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>回款查看</title>
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
			});
		//-->
		</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000
}
-->
</style>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 回款管理 &gt;&gt; 回款查看</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;回款信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">回款类型</td>
          <td class="td_02" width="30%" >
	          <c:if test="${mreturnDto.returnType==0}">回款</c:if>
	          <c:if test="${mreturnDto.returnType==1}">在途</c:if>
	          <c:if test="${mreturnDto.returnType==2}">到帐</c:if>&nbsp;
	      </td>
          <td class="td_01" width="20%">回款编号</td>
          <td class="td_02" width="30%" >${mreturnDto.no}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">回款方式</td>
          <td class="td_02" width="30%" >
	          <c:if test="${mreturnDto.returnWay==1}">现金</c:if>
	          <c:if test="${mreturnDto.returnWay==2}">支票</c:if>
	          <c:if test="${mreturnDto.returnWay==3}">网银</c:if>
	          <c:if test="${mreturnDto.returnWay==4}">电汇</c:if>
	          <c:if test="${mreturnDto.returnWay==5}">银行承兑</c:if>
	          <c:if test="${mreturnDto.returnWay==6}">承诺函</c:if>
	          <c:if test="${mreturnDto.returnWay==7}">其它</c:if>&nbsp;
          </td>
          <td class="td_01" width="20%">凭证号</td>
          <td class="td_02" width="30%" >${mreturnDto.number}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">产品分类名称</td>
          <td class="td_02" width="30%" >${mreturnDto.productName}&nbsp;</td>
          <td class="td_01" width="20%">客户名称</td>
          <td class="td_02" width="30%" >${mreturnDto.customerName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">录入日期</td>
          <td class="td_02" width="30%">${mreturnDto.date}&nbsp;</td>
          <td class="td_01" width="20%">回款日期</td>
          <td class="td_02" width="30%">${mreturnDto.returnDate}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">回款金额</td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${mreturnDto.money}" pattern="#,##0.00#"/>&nbsp;元</td>
          <td class="td_01" width="20%">退款金额</td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${mreturnDto.backMoney}" pattern="#,##0.00#"/>&nbsp;元</td>
        </tr>
      </table>
      <BR />
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <div style=" padding:4px 0px 0px 10px "></div>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
        <tr>
          <th nowrap="nowrap" style="border-left:1px solid #c2c2c2;">发货单号</th>
          <th nowrap="nowrap">产品合同号</th>
          <th nowrap="nowrap">公司合同号</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">销售单价</th>
          <th nowrap="nowrap">发货金额</th>
          <th nowrap="nowrap">已指定金额</th>
          <th nowrap="nowrap">开票金额</th>
          <th nowrap="nowrap">退货金额</th>
          <th nowrap="nowrap">指定金额</th>
          <th nowrap="nowrap">人员名称</th>
        </tr>
        <logic:present name="sendGoodsList">
      		<logic:iterate id="sgl" name="sendGoodsList" indexId="index">
		        <tr>
		          <td width="30" nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18px">${sgl.sendGoodsId}&nbsp;</td>
		          <td nowrap="nowrap" >${sgl.productContractCode}&nbsp;</td>
		          <td nowrap="nowrap" >${sgl.companyContractCode}&nbsp;</td>
		          <td nowrap="nowrap" >${sgl.productCode}&nbsp;</td>
		          <td nowrap="nowrap" >${sgl.productName}&nbsp;</td>
		          <td nowrap="nowrap" >${sgl.productType}&nbsp;</td>
		          <td nowrap="nowrap" >${sgl.productUnit}&nbsp;</td>
		          <td nowrap="nowrap" ><fmt:formatNumber value="${sgl.sellContractDetailPrice}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sgl.fhMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sgl.sendGoodsMoney - sgl.appointMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sgl.kpMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sgl.thMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sgl.appointMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" >${sgl.userName}&nbsp;</td>
		        </tr>
        	</logic:iterate>
	   	</logic:present>
        <tr>
          <td height="18" nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
          <td colspan="10" nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">指定金额合计</td>
          <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">
<fmt:formatNumber value="${productMoney}" pattern="#,##0.00#"/></td>
          <td nowrap="nowrap" style=" border:1px solid #FFFFFF; background-color:#FFFFFF">元</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;销售合同信息</div>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
        <tr>
          <th nowrap="nowrap" style="border-left:1px solid #c2c2c2;">产品合同号</th>
          <th nowrap="nowrap">公司合同号</th>
          <th nowrap="nowrap">合同金额</th>
          <th nowrap="nowrap">发货金额</th>
          <th nowrap="nowrap">备货金额</th>
          <th nowrap="nowrap">指定金额</th>
          <th nowrap="nowrap">已预收金额</th>
          <th nowrap="nowrap">开票金额</th>
          <th nowrap="nowrap">退货合同金额</th>
          <th nowrap="nowrap">退货金额</th>
          <th nowrap="nowrap">预收金额</th>
          <th nowrap="nowrap">人员名称</th>
        </tr>
        <logic:present name="sellList">
      		<logic:iterate id="sell" name="sellList" indexId="index">
		        <tr>
		          <td width="30" nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18px">${sell.productContractCode}&nbsp;</td>
		          <td nowrap="nowrap" >${sell.companyContractCode}&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sell.money}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sell.fhMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sell.bhMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sell.sendGoodsMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" style=" text-align:right; "><fmt:formatNumber value="${sell.sellMoney - sell.appointMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap"  width="96" style=" text-align:right;"><fmt:formatNumber value="${sell.kpMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap"  width="96" style=" text-align:right;"><fmt:formatNumber value="${sell.sbMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap"  width="96" style=" text-align:right;"><fmt:formatNumber value="${sell.thMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap"  width="96" style=" text-align:right;"><fmt:formatNumber value="${sell.appointMoney}" pattern="#,##0.00#"/>&nbsp;</td>
		          <td nowrap="nowrap" >${sell.userName}&nbsp;</td>
		        </tr>
        	</logic:iterate>
	   	</logic:present>
        <tr>
          <td height="18" nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
          <td colspan="8" nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">预收金额合计</td>
          <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF"><fmt:formatNumber value="${sellContractMoney}" pattern="#,##0.00#"/></td>
          <td  nowrap="nowrap" style=" text-align:left; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">元</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;产品分类信息</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="50%" height="18px">剩余额度</td>
          <td class="td_04" ><fmt:formatNumber value="${symoney}" pattern="#,##0.00#"/>&nbsp;元</td>
        </tr>
      </table>
    <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
            <td class="td_03" width="20%">特殊说明</td>
            <td class="td_04"  style="padding:5px 100px 5px 5px">${tf:replaceBr(mreturnDto.text)}</td>
        </tr>
    </table>
      </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom"><a href="javascript:window.location ='${ctx}/mreturn.do'"><img src="${ctx}/images/btnBack.gif" /></a></td>
    <td></td>
  </tr>
</table>
<br />
</body>
</html>
