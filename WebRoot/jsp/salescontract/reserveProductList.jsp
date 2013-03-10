<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>产品选择</title>
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
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
		
		<script language="JavaScript"> 
		<!--
			 
			$(function(){ 
				//各行换色
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
			
				 $("#submit").click(function(){   
					 var seleProArr = new Array();

					 $('#productTable input:checked').each(function(){  
							 seleProArr.push($(this).val()); 
					 });  
					
					 if(seleProArr.length>0){
						$("#productTable :checkbox").attr("checked", false);
						opener.addProduct(seleProArr); 
						//选择完毕关闭小页面
						window.close();
					 }else{ 
						alert("未选择任务产品.");
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
	<body><br/>
		 <table id="productTable" width="96%" border="0" cellpadding="0" cellspacing="0" align="center" class="biao1">
			<tr>
				<th>选择</th>
				<th>产品编码</th>
				<th>产品名称</th>
				<th>规格型号</th>
				<th>单位</th>

				<th>销售数</th>
				<th>销售单价</th>
				<th>销售金额</th>
				<th>已发货数</th>
				<th>退货数</th>
				<th>备货可用数</th>
			</tr>
			<logic:iterate id="product" name="productList" indexId="num">
				<c:if test="${product.ybfNum<(product.count-product.yfhNum+product.thsNum)}">
				<tr>
					<td>
						<input type="checkbox" id="prodCh${num}" value="${product.productId}&${product.productCode}&${product.productName}&${product.productType}&${product.productUnit}&${product.count}&${product.price}&${product.money}&${product.yfhNum}&${product.qtkfNum}&${product.thsNum}&${product.fhkyNum+product.bkfNum}">
					</td>
					<td>${product.productCode}</td>
					<td>${product.productName}</td>
					<td>${product.productType}</td>
					<td>${product.productUnit}</td>

					<td style=" text-align:right;padding-right:5px;">${product.count}</td> 
					<td style=" text-align:right;padding-right:5px;">${product.price}</td>
					<td style=" text-align:right; padding-right:5px;">${product.money}</td>
					<td style=" text-align:right;padding-right:5px;">${product.yfhNum}</td>
					<td  style=" text-align:right;padding-right:5px;">${product.thsNum}</td>
					<td style=" text-align:right;padding-right:5px;">${product.fhkyNum+product.bkfNum}</td>
				</tr> 
				</c:if>
			</logic:iterate>
		 </table>
		 <table align="center">
				<tr>
					<td height="45px" colspan="2" align="center" valign="bottom">
						<img src="${ctx}/images/btnChoice.gif" width="65" id="submit" height="20" />
					</td>
				</tr>
			</table>
	</body>
</html>
