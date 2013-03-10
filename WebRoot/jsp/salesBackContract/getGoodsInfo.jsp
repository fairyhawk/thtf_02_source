<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html>
<head>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
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
</head>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<title>产品信息</title>
</head>
<script type="text/javascript">
$(document).ready(function(){
$("#changeCheckdValue").click(function(){//点击
if($("#valueList input:checked[name='productId']").length==0){alert("请选择产品！");}
if($("#saleBackId").val()!=$("#sellContractId",window.opener.document).val()){
	alert("此信息已过期，请重新添加产品信息");
	window.close();
	return;
}
$("#valueList input:checked[name='productId']").each(function(){//循环 chenkbox选中 
opener.getOpenerValOfGoodsInfo($("#tr"+$(this).attr("id")).html(),$(this).attr("id"));//调用父窗口方法 参数：tr里面的信息
window.close();
});
	
});
if($("#valueList")){
		$("#valueList tr:even").addClass("treven");
		$("#valueList tr").each(function(i){
			$(this).mouseover(function(){
				$(this).addClass("over");
			});
			$(this).mouseout(function(){
				$(this).removeClass("over");
			});
		});
	}
});
</script>
<body>
 <div style="width:100%; text-align:right;font-size:12px">单位：元&nbsp;</div>
    <table id="valueList" widht="96%"  border="0" cellpadding="0" cellspacing="0" class="biao1" align="center" style="border-left:1px solid #ffffff;">
		<tr>
          <th nowrap="nowrap" width="30" style="border-left:1px solid #c2c2c2;">选择</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">销售数</th>
          <th nowrap="nowrap">销售单价</th>
          <th nowrap="nowrap">销售金额</th>
          <th nowrap="nowrap">发货金额</th>
          <th nowrap="nowrap">备货金额</th>
          <th nowrap="nowrap">指定金额</th>
          <th nowrap="nowrap">开票金额</th>
          <th nowrap="nowrap">已退货合同数</th>
          <th nowrap="nowrap">退货数</th>
        </tr>
        <logic:present name="goodsInfo">
       <logic:iterate id="goodsInfo" name="goodsInfo" indexId="index">
        <tr id="tr${goodsInfo.productId }">
       	  <td nowrap="nowrap"  height="18px" id="first" style="border-left:1px solid #c2c2c2;">
       	  	<html:checkbox property="productId" name="goodsInfo" styleId="${goodsInfo.productId }"></html:checkbox>
       	  </td>
          <td nowrap="nowrap" width="72px">${tf:replaceHTML(goodsInfo.productCode)}&nbsp;</td>
          <td nowrap="nowrap" ><div class="ellipsis_div" style="width:60px;">${tf:replaceHTML(goodsInfo.productName)}&nbsp;</div></td>
          <td nowrap="nowrap" ><div class="ellipsis_div" style="width:60px;">${tf:replaceHTML(goodsInfo.specModel)}&nbsp;</div></td>
          <td nowrap="nowrap" width="28px">${tf:replaceHTML(goodsInfo.unit)}&nbsp;</td>
          <td nowrap="nowrap" width="42px" style=" text-align:right;padding-right:5px;">${goodsInfo.sellCount }&nbsp;</td>
          <td nowrap="nowrap" width="80px" style=" text-align:right;padding-right:5px;"><input type="hidden" name="sellPrice" id="sellPrice${goodsInfo.productId }" value="${goodsInfo.sellPrice }"/><fmt:formatNumber value="${goodsInfo.sellPrice }" type="number" pattern="#,##0.00" />&nbsp;</td>
          <td nowrap="nowrap" width="80px" style=" text-align:right;padding-right:5px;"><fmt:formatNumber value="${goodsInfo.sellMoney }" type="number" pattern="#,##0.00" /></td>
          <td nowrap="nowrap" width="80px" style=" text-align:right;padding-right:5px;"><fmt:formatNumber value="${goodsInfo.sendGoodsMoney }" type="number" pattern="#,##0.00" /></td>
          <td nowrap="nowrap" width="80px" style=" text-align:right;padding-right:5px;"><fmt:formatNumber value="${goodsInfo.preGoodsMoney }" type="number" pattern="#,##0.00" /></td>
          <td nowrap="nowrap" width="80px" style=" text-align:right;padding-right:5px;"><fmt:formatNumber value="${goodsInfo.appointMoney }" type="number" pattern="#,##0.00" /></td>
          <td nowrap="nowrap" width="80px" style=" text-align:right;padding-right:5px;"><fmt:formatNumber value="${goodsInfo.makeInvoiceMoney }" type="number" pattern="#,##0.00" /></td>
          <td nowrap="nowrap"  width="78px" style=" text-align:right;padding-right:5px;">${goodsInfo.alreadyReturnCount }&nbsp;</td>
          <td nowrap="nowrap" id="tdReturnCount" style=" text-align:right;padding-right:5px;">${goodsInfo.returnCount }&nbsp;</td>
          <td nowrap="nowrap" id="tdBackContractGoodsMoney" style="display:none;text-align:right;padding-right:12px"></td>
          <td nowrap="nowrap" style="display:none;text-align:right;padding-right:12px">${goodsInfo.sellPrice }</td>
          <td nowrap="nowrap" style="display:none;text-align:right;padding-right:12px">0</td>
        </tr>
        </logic:iterate>
		</logic:present>
	</table>
	<br />
    <table align="center" width="99%" cellpadding="0" cellspacing="0">
			<tr>
              <td  align="right" valign="bottom" style="font-size:12px">
              <div id="fenye"></div><%@ include file="/jsp/common/newPage.jsp"%></td>
            </tr>
            <tr>
              <td height="45px"  align="center" valign="bottom">
              <a href="#" id="changeCheckdValue"><img src="${ctx}/images/btnChoice.gif" width="65" height="20" /></a></td>
            </tr>
        </table>   
        <input type="hidden" id="saleBackId" value="${requestScope.saleBackId }"/> 
</body>
</html>