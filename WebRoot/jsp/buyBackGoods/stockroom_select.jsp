<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>入库明细选择</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
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
	  	
	//将小页面被选中的入库明细信息带到大页面
	var myArrayInStock=new Array();
	function selectProductList() 
	{
		var productChecked =document.getElementsByName("inStockCheck");
		// 定义是否有产品被选中
		var notSelect = true;
		// 把被选中的入库明细传入数组
		for(var i=0;i<productChecked.length;i++){
			if(productChecked[i].checked==true){
				toParentsValue(productChecked[i]);
				notSelect = false ;
			}
		}
		// 没有入库明细被选择
		if(notSelect){
			alert("请选择入库明细");
			return;
		}  
		opener.newWindow(myArrayInStock);
		 
		window.close();
    }
	    
    // 把选中产品的一条记录传到父页面
    function toParentsValue(obj) 
    {
		var records = obj.value;
		var instockmsg = new Array();
		instockmsg = records.split("#");
		var productid = instockmsg[0];
		var productname = instockmsg[1];
		var producttype = instockmsg[2];
		var productunit = instockmsg[3];
		var price = instockmsg[4];
		var inStockMoney = instockmsg[5];
		var paymentMoney = instockmsg[6];
		var receiveMoney = instockmsg[7];
		var alreadyBackCount = instockmsg[8];
		var backMoney = instockmsg[9];
		var stockNum  = instockmsg[10]; 
	 	var productcode  = instockmsg[11];
		if(obj.checked==true) {
			myArrayInStock.push([productid,productname,producttype,productunit,price,inStockMoney,paymentMoney,receiveMoney,alreadyBackCount,backMoney,stockNum,productcode]);
		}
	}
	 
	
	
	
	</script>
	
	
</head>
<body>
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td align="center">
    <br/>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
      <tr>
          <th nowrap="nowrap">选择</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">采购单价</th>
          <th nowrap="nowrap">入库金额</th>
          <th nowrap="nowrap">指定金额</th>
          <th nowrap="nowrap">收票金额</th>
          <th nowrap="nowrap">已返厂数</th>
          <th nowrap="nowrap">返厂金额</th>
          <th nowrap="nowrap">库存可用数</th>
        </tr>
        
      <logic:iterate id="GoogsCreateInfoDto" name="backGoogsCreateInfoList" >
      <tr>
		  <td width="30" nowrap="nowrap" ><input type="checkbox" id="inStockCheck" 
		  
		  value="${GoogsCreateInfoDto.productId}#${GoogsCreateInfoDto.productName}#${GoogsCreateInfoDto.productType}#
		  ${GoogsCreateInfoDto.productUnit}#<fmt:formatNumber value="${GoogsCreateInfoDto.price}" pattern="#,##0.00000"/>#<fmt:formatNumber value="${GoogsCreateInfoDto.inStockMoney}" pattern="#,##0.00000"/>#
		  <fmt:formatNumber value="${GoogsCreateInfoDto.paymentMoney}" pattern="#,##0.00000"/>#<fmt:formatNumber value="${GoogsCreateInfoDto.receiveMoney}" pattern="#,##0.00000"/>#
		  ${GoogsCreateInfoDto.alreadyBackCount}#<fmt:formatNumber value="${GoogsCreateInfoDto.backMoney}" pattern="#,##0.00000"/>#
		  ${GoogsCreateInfoDto.stockNum}#${GoogsCreateInfoDto.code}
		  "
		  >&nbsp;</td>
            
          <td nowrap="nowrap" >${GoogsCreateInfoDto.code}&nbsp;</td>
          <td nowrap="nowrap" >${GoogsCreateInfoDto.productName}&nbsp;</td>
          <td nowrap="nowrap" width="64">${GoogsCreateInfoDto.productType}&nbsp;</td>
          <td nowrap="nowrap" >${GoogsCreateInfoDto.productUnit}&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${GoogsCreateInfoDto.price}" pattern="#,##0.00000"/>&nbsp;</td>  
          <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${GoogsCreateInfoDto.inStockMoney}" pattern="#,##0.00000"/>&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${GoogsCreateInfoDto.paymentMoney}" pattern="#,##0.00000"/>&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${GoogsCreateInfoDto.receiveMoney}" pattern="#,##0.00000"/>&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right;">${GoogsCreateInfoDto.alreadyBackCount}&nbsp;</td>
        
          <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${GoogsCreateInfoDto.backMoney}" pattern="#,##0.00000"/>&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right;">${GoogsCreateInfoDto.stockNum}&nbsp;</td>        
      </tr> 
        </logic:iterate>
    </table>
    <br />
        <table border="0" cellpadding="0" cellspacing="0"  width="100%">
        	<tr>
           	  	<td align="right" >
           	  	    <%@ include file="/jsp/common/newPage.jsp"%>
           	  	</td>
          </tr>
        </table>
        <table align="center">
            <tr>
              <td height="45px" colspan="2" align="center" valign="bottom"><img src="${ctx}/images/btnChoice.gif" width="65" height="20" onclick="selectProductList()"/></td>
            </tr>
        </table>    
    </td>
  </tr>
</table>
     
</body>
</html>
