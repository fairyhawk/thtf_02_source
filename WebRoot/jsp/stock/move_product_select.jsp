<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>产品选择</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
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
	var myArrayMoveStock=new Array();
	function selectProductList() 
	{
		var productChecked =document.getElementsByName("checkbox");
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
			alert("请选择产品");
			return;
		}  
		opener.newWindow(myArrayMoveStock);
		 
		window.close();
    }
	 // 把选中产品的一条记录传到父页面
    function toParentsValue(obj) 
    {
		var records = obj.value;
		var instockmsg = new Array();
		instockmsg = records.split("#");
		var productid = instockmsg[0];
		var productcode = instockmsg[1];
		var productname = instockmsg[2];
		var producttype = instockmsg[3];
		var unit = instockmsg[4];
		var price = instockmsg[5];
		var useCount = instockmsg[6];
		var timeStamp = instockmsg[7];
		   
		if(obj.checked==true) {
			myArrayMoveStock.push([productid,productcode,productname,producttype,unit,price,useCount,timeStamp]);
		}
		
	} 
			 
</script>
</head>
<body>
<html:form action="getMoveProductSelect.do" styleId="getMoveProductSelectForm" >
<input type="hidden" name="outStockroomId" value="${outStockroomId}" >
<input type="hidden" name="productTypeId" value="${productTypeId}" >
<input type="hidden" name="productTypeName" value="${productTypeName}" >
 	
	
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td align="center"><br />
      <div class="mo_wp">
        <div style="display: ; " class="mo_con" >
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td class="td_01" width="20%">产品分类名称</td>
              <td class="td_02" width="30%">
              ${productTypeName}
              	&nbsp;
              </td>
              <td class="td_01" width="20%">产品系列名称</td>
              <td width="30%" class="td_02"> 
               	<html:select property ="proSerieId"  value="${moveStockProductDto.proSerieId}" style=" width:126px" styleId="proSerieId">
		          	<html:option value="">--请选择--</html:option>
		          	<logic:iterate id="serieEntry" name="serieList">
		          	  <html:option value="${serieEntry.proSerieId}">${serieEntry.proSerieName}</html:option> 
		          	</logic:iterate>
          		</html:select>
             
                
              </td>
            </tr>
            <tr>
              <td class="td_01">产品编码</td>
              <td class="td_02"><input type="text" name="productCode" id="productCode" style="width:120px;" value="${moveStockProductDto.productCode}"/></td>
              <td class="td_01">产品名称</td>
              <td class="td_02"><input type="text" name="productName" id="productName" style="width:120px;" value="${moveStockProductDto.productName}"/></td>
            </tr>
            <tr>
              <td class="td_01">规格型号</td>
              <td class="td_02"><input type="text" name="productType" id="productType" style="width:240px;" value="${moveStockProductDto.productType}" /></td>
              <td class="td_01">  &nbsp;</td>
              <td class="td_02"> &nbsp;</td>
            </tr>
            <tr>
              <td colspan="4" align="center" style="height:30px;"><img src="${ctx}/images/btn_JianSuo.gif" onclick="submit()"/></td>
            </tr>
          </table>
        </div>
        <div class="mo_title" onclick="fMainListToggle(this)">
          <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
        </div>
      </div></td>
  </tr>
  <tr>
    <td align="center">
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
        <tr>
          <th nowrap="nowrap" width="40" style="border-left:1px solid #c2c2c2;">选择</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">库存单价</th>
          <th nowrap="nowrap">移库可用数</th>
        </tr>
        <logic:iterate id="productDto" name="productlist" indexId="index">        	   
		          	
        <tr>
          <td nowrap="nowrap" style="border-left:1px solid #c2c2c2;"><input type="checkbox" name="checkbox" id="checkbox" 
          value="${productDto.id}#${productDto.code}#${productDto.productName}#${productDto.type}#${productDto.unit}#<fmt:formatNumber value="${productDto.priceTax}" pattern="#,##0.00"/>#${productDto.useCount}#${productDto.timeStamp}"
          
          
          /></td>
          <td nowrap="nowrap" >${productDto.code}&nbsp;</td>
          <td nowrap="nowrap" >${productDto.productName}&nbsp;</td>
          <td nowrap="nowrap" >${productDto.type}&nbsp;</td>
          <td nowrap="nowrap" >${productDto.unit}&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right;"><fmt:formatNumber value="${productDto.priceTax}" pattern="#,##0.00"/>&nbsp;</td>
          <td nowrap="nowrap" style=" text-align:right;">${productDto.useCount}&nbsp;</td>
        </tr> 
        </logic:iterate>
      </table>
      <br />
      <table align="center" width="100%" cellpadding="0" cellspacing="0">
        <tr>
          <td  align="right" valign="bottom"><%@ include file="/jsp/common/newPage.jsp"%></td>
        </tr>
        <tr>
          <td height="45px"  align="center" valign="bottom"><img src="${ctx}/images/btnChoice.gif" width="65" height="20" onclick="selectProductList()"/></td>
        </tr>
      </table></td>
  </tr>
</table>
</html:form>
</body>
</html>
