<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>入库明细选择</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
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
<script type="text/javascript">
<!--			
	$(document).ready(function(){
		//产品分类名称
		$('#productTypeName').text(opener.getProductTypeName());
		//隔行换色
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
		
		//检索提交
		$('#btnJianSuo').click(function(){
			document.forms[0].submit();
		});
		
		//选择提交
		$('#submit').click(function(){   
		 	var insArray = new Array();
		 	$('#inStockD input:checked').each(function(){
			 	insArray.push($(this).val());
		 	});  
		 	//验证
		 	if(insArray.length>0){
				$('#inStockD :checkbox').attr("checked", false);
				opener.addInStockD(insArray); 
				//选择完毕关闭小页面
				window.close();
		 	}else{ 
				alert("未选择任何入库明细");
			}
		});
		$('input:checkbox').click(function(){   
			if($(this).attr('checked')){ 
				if(opener.checkInStockD($(this).val())){
					alert("此入库明细已存在");
					$(this).attr('checked',false);
				} 
			}
		});  
	});
//-->
</script>
</head>
<body>
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td align="center"><br />
      <div class="mo_wp">
        <div style="display: ; " class="mo_con" >
        <form action="buyPaymentSelectInStockDetail.do" name="buyPaymentSelectInStockDetail" id="buyPaymentSelectInStockDetail" method="post">
    	  <input type="hidden" id="supplierId" name="supplierId" value="${supplierId}">
    	  <input type="hidden" id="productTypeId" name="productTypeId" value="${productTypeId}">
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td class="td_01" width="20%">产品分类名称</td>
              <td class="td_02" width="30%" id="productTypeName">${productTypeName}&nbsp;</td>
              <td class="td_01" width="20%">入库单号</td>
              <td class="td_02" width="30%"><input type="text" name="inStockId" id="inStockId" value="${formInStock.inStockId}" style="width:120px;" /></td>
            </tr>
            <tr>
              <td class="td_01">产品合同号</td>
              <td class="td_02"><input type="text" name="productContractCode" id="productContractCode" value="${formInStock.productContractCode}" style="width:120px;" /></td>
              <td class="td_01">公司合同号</td>
              <td class="td_02"><input type="text" name="companyContractCode" id="companyContractCode" value="${formInStock.companyContractCode}" style="width:120px;" /></td>
            </tr>
            <tr>
              <td class="td_01">产品编码</td>
              <td class="td_02"><input type="text" name="productNo" id="productNo" value="${formInStock.productNo}" style="width:120px;" /></td>
              <td class="td_01">产品名称</td>
              <td class="td_02"><input type="text" name="productName" id="productName" value="${formInStock.productName}" style="width:120px;" /></td>
            </tr>
            <tr>
				<td class="td_01">要求付款起始日期</td>
				<td class="td_02">
					<input type="text" name="startRequestAccountDate" id="startRequestAccountDate" style="width:120px;" onfocus="calendar()"
								readonly="readonly" value="${formInStock.startRequestAccountDate}" />
				</td>
				<td class="td_01">要求付款终止日期</td>
				<td class="td_02">
					<input type="text" name="endRequestAccountDate" id="endRequestAccountDate" style="width:120px;" onfocus="calendar()"
								readonly="readonly" value="${formInStock.endRequestAccountDate}" />
				</td>
			</tr>
            <tr>
              <td class="td_01" width="20%">规格型号</td>
              <td class="td_02" width="30%"><input type="text" name="productType" id="productType" value="${formInStock.productType}" style="width:240px;" /></td>
              <td class="td_01" width="20%">&nbsp;</td>
              <td class="td_02" width="30%">&nbsp;</td>
            </tr>
            <tr>
              <td colspan="4" align="center" style="height:30px;"><a href="#" id="btnJianSuo"><img src="${ctx}/images/btn_JianSuo.gif" /></a></td>
            </tr>
          </table>
          </form>
        </div>
        <div class="mo_title" onclick="fMainListToggle(this)">
          <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
        </div>
      </div></td>
  </tr>
  <tr>
    <td align="center"><div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
        <tr>
          <th nowrap="nowrap">选择</th>
          <th nowrap="nowrap">入库单号</th>
          <th nowrap="nowrap">要求付款日期</th>
          <th nowrap="nowrap">产品合同号</th>
          <th nowrap="nowrap">公司合同号</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
		  <th width="84" nowrap="nowrap">数量</th>
          <th nowrap="nowrap">采购单价</th>
          <th nowrap="nowrap">入库金额</th>
          <th nowrap="nowrap">指定金额</th>
          <th width="84" nowrap="nowrap">收票金额</th>
          <th width="84" nowrap="nowrap">返厂金额</th>
        </tr>
        <tbody id="inStockD">
        <logic:iterate id="product" name="list" indexId="index">
        <tr>
			<td width="30" nowrap="nowrap"><input type="checkbox" name="inStockD" id="inStockD" value="${product.inStockId}\||//${product.requestAccountDate}\||//${product.productContractCode}\||//${product.companyContractCode}\||//${product.productCode}\||//${product.productName}\||//${product.productType}\||//${product.productUnit}\||//${product.buyContractPrice}\||//${product.inStockMoney}\||//${product.yzdMoney}\||//${product.spMoney}\||//${product.fcMoney}\||//${product.productId}\||//${product.buyContractId}" /></td>
			<td width="72" nowrap="nowrap">${product.inStockId}&nbsp;</td>
			<td nowrap="nowrap" >${product.requestAccountDate}&nbsp;</td>
			<td nowrap="nowrap" >${product.productContractCode}&nbsp;</td>
			<td nowrap="nowrap" >${product.companyContractCode}&nbsp;</td>
			<td nowrap="nowrap" >${product.productCode}&nbsp;</td>
			<td nowrap="nowrap" >${product.productName}&nbsp;</td>
			<td nowrap="nowrap" >${product.productType}&nbsp;</td>
			<td nowrap="nowrap"	>${product.productUnit}&nbsp;</td>
			<td nowrap="nowrap" style=" text-align:right;">${product.inStockCount}&nbsp;</td> 
			<td nowrap="nowrap" ><fmt:formatNumber value="${product.buyContractPrice}" pattern="#,##0.00000#"/>&nbsp;</td>
			<td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${product.inStockMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
			<td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${product.yzdMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
			<td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${product.spMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
			<td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${product.fcMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
        </tr>
        </logic:iterate>
      	</tbody>
      </table>
      <br />
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
          <td align="right"><%@ include file="/jsp/common/newPage.jsp"%></td>
        </tr>
      </table>
      <table align="center">
        <tr>
          <td height="45px" colspan="2" align="center" valign="bottom"><img id="submit" src="${ctx}/images/btnChoice.gif" width="65" height="20" /></td>
        </tr>
      </table></td>
  </tr>
</table>
<br/>
</body>
</html>
