<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>产品选择</title>
	<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
	<style type="text/css"> 
		.treven {
			background-color: #f3fbff;
		}
		.over {
			background-color: #ECECEC;
		}
		 
		.STYLE1 {
			color: #FF0000
		} 
	</style>
	<script language="JavaScript">  
		$(document).ready(function(){
			if($("#productTable")){
				$("#productTable tr:even").addClass("treven");
				$("#productTable tr").each(function(i){
					$(this).mouseover(function(){
						$(this).addClass("over");
					});
					$(this).mouseout(function(){
						$(this).removeClass("over");
					});
				});
			}

			$("#add").click(function(){   
				 var productArr = new Array();

				 $('#productTable input:checked').each(function(){  
						 productArr.push($(this).val()); 
				 });  
				
				 if(productArr.length>0){
					$("#productTable :checkbox").attr("checked", false);
					opener.addProduct(productArr);

					//选择完毕关闭小页面
					window.close();
				 }else{ 
					alert("未选产品！");
				 }
			 }); 

			 $("input:checkbox").click(function(){   
				if($(this).attr("checked")){ 
					if(opener.checkProduct($(this).val())){
						alert("此产品已存在");
						$(this).attr("checked",false);
					} 
				}
			 });  
		}); 
	</script>

</head>

<body>
<html:form method="post" action="getProductList" styleId="ascForm">
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td align="center">
    <br/>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="productTable">
      <tr>
        <th nowrap="nowrap">选择</th>
        <th nowrap="nowrap">产品编码</th>
        <th nowrap="nowrap">产品名称</th>
        <th nowrap="nowrap">规格型号</th>
        <th nowrap="nowrap">单位</th>
        <th nowrap="nowrap">应入库数</th>
        <th nowrap="nowrap">采购单价</th>
        <th nowrap="nowrap">采购金额</th>
        <th nowrap="nowrap">入库数</th>
        <th nowrap="nowrap">返厂数</th>
      </tr>
	  <logic:iterate id="product" name="resultList">
      <tr>
        <td  width="29px" nowrap="nowrap">
			<input type="checkbox" name="checkbox" id="checkbox" value="${product.productId}&${product.productCode}&${product.productName}&${product.productType}&${product.productUnit}&${product.receiveGoodsDetailCount}&${product.buyContractDetailPrice}&${product.money}&${product.inStockCount}&${product.receiveInvoiceCount}&${product.buyBackGoodsCount}"/>
		</td>
        <td nowrap="nowrap">${product.productCode}&nbsp;</td>
        <td nowrap="nowrap">${product.productName}&nbsp;</td>
        <td nowrap="nowrap">${product.productType}&nbsp;</td>
        <td nowrap="nowrap">${product.productUnit}&nbsp;</td>
        <td nowrap="nowrap" style="text-align:right">${product.receiveGoodsDetailCount}&nbsp;</td>
        <td nowrap="nowrap" width="90" style="text-align:right"><fmt:formatNumber value="${product.buyContractDetailPrice}" pattern="#,##0.00000#"/>&nbsp;</td>
        <td nowrap="nowrap" width="90" style="text-align:right"><fmt:formatNumber value="${product.money}" pattern="#,##0.00000#"/>&nbsp;</td>
        <td nowrap="nowrap" width="70px" style="text-align:right">${product.inStockCount}&nbsp;</td>
        <td nowrap="nowrap" width="70px" style="text-align:right">${product.buyBackGoodsCount}&nbsp;</td>
       </tr>
	   </logic:iterate>
    </table>
    <br /> 
        <table align="center">
            <tr>
              <td height="45px" colspan="2" align="center" valign="bottom">
				<img src="${ctx}/images/btnChoice.gif" id="add" width="65" height="20" />
			  </td>
            </tr>
        </table>    
    </td>
  </tr>
</table>
</html:form>
</body>
</html>
