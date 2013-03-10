<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>装箱单打印</title>
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
@media   print   {   
  		.buttonNoPrint {display:none;}  
  		th{border-top:1px solid #C2C2C2; border-bottom:1px solid #C2C2C2;}
  		}
.STYLE1 {color: #FF0000}

-->
.instockTitle{font-size:20px;width:210px; text-align:center; float:left;}
#nextCaption{font-weight:700;position:relative; margin-left:230px;}
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
function printPreview(){   
	// 打印页面预览    
	wb.execwb(7,1);
		  
}    
function printSetup(){    
	// 打印页面设置    
	wb.execwb(8,1);    
}

function  printIt(){ 
	wb.execwb(6,6); 
} 

function closeWindow(){
	wb.execwb(45,1); 
}
</script>

</head>
<body>
<OBJECT id="wb" height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name="wb"></OBJECT>
<div id="nextCaption">
<div class="instockTitle"> 
	出库单
</div>
</div>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td colspan="2" align="center">&nbsp;</td></tr>
  <tr>
    <td>&nbsp;</td>
    <td>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3"> 
        <tr>
          <td class="td_03" height="18px">装箱单号</td>
          <td class="td_04">${box.id}&nbsp;</td>
		  <td class="td_03" height="18px"">产品分类名称</td>
          <td class="td_04">${box.productTypeName}&nbsp;</td>
        </tr>
		<tr>
          <td width="20%" class="td_01" height="18px">库房名称</td>
          <td colspan="3" class="td_02">${box.stockroomName}&nbsp;</td> 
        </tr> 
		<tr>
          <td width="20%" class="td_01" height="18px">货物接收单位名称</td>
          <td colspan="3" class="td_02">${box.customerAddressName}&nbsp;</td> 
        </tr>
		<tr>
          <td width="20%" class="td_01" height="18px">发货地址</td>
          <td colspan="3" class="td_02">${box.customerAddress}&nbsp;</td> 
        </tr>
		<tr>
          <td class="td_03" height="18px">联系人</td>
          <td class="td_04">${box.linkman}&nbsp;</td>
		  <td class="td_03" height="18px"">电话</td>
          <td class="td_04">${box.tel}&nbsp;</td>
        </tr>
		<tr>
          <td class="td_03" height="18px">手机</td>
          <td class="td_04">${box.mobile}&nbsp;</td>
		  <td class="td_03" height="18px"">打印日期</td>
		  <jsp:useBean id="now" class="java.util.Date" />
          <td class="td_04"><fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />&nbsp;</td>
        </tr>
		<tr>
          <td class="td_03" height="18px">提货人姓名</td>
          <td class="td_04">${box.takeName}&nbsp;</td>
		  <td class="td_03" height="18px"">身份证号码</td>
		  <td class="td_04">${box.takeNumber}&nbsp;</td>
        </tr> 
      </table>
	  <c:if test="${orderTextSize!=0}">
	   <br />
	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1">
		<thead>
			<tr>
			  <th nowrap="nowrap" width="20%">发货单号</th>
			  <th nowrap="nowrap">特殊说明</th> 
			</tr>
		</thead>
		<tbody id="orderTable"> 
			<logic:iterate id="order" name="orderText">
				<tr> 
					<td nowrap="nowrap" height="18px">${order.orderId}</td> 
					<td nowrap="nowrap">${order.text}</td> 
				</tr>
			</logic:iterate> 
		</tbody>
      </table> 
	  </c:if> 
      <br />
     <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1">
		<thead>
			<tr>
			  <th nowrap="nowrap">序号</th>
			  <th nowrap="nowrap">发货单号</th>
			  <th nowrap="nowrap">库房名称</th>
			  <th nowrap="nowrap">产品编码</th>
			  <th nowrap="nowrap">产品名称</th>

			  <th nowrap="nowrap">规格型号</th>
			  <th nowrap="nowrap">单位</th>
			  <th nowrap="nowrap">发货数</th>
			  <th nowrap="nowrap" width="90">配货数</th>
			  <th nowrap="nowrap">库存数</th>
			  <th nowrap="nowrap">配送方式</th>
			</tr>
		</thead>
		<tbody id="orderTable">
			<c:if test="${boxProductList!=null}">
			<logic:iterate id="product" name="boxProductList" indexId="index">
				<tr>
					 
					<td height="18px" >${index+1}</td> 
					<td nowrap="nowrap" >${product.orderId}</td>
					<td nowrap="nowrap" class='productCode'>${product.stockroomName}</td>
					<td nowrap="nowrap" >${product.productCode}</td>
					<td nowrap="nowrap" >${product.productName}</td>

					<td nowrap="nowrap" >${product.productType}</td>
					<td nowrap="nowrap" >${product.productUnit}</td>
					<td nowrap="nowrap" style="text-align:right;">${product.count}</td>
					<td nowrap="nowrap" ></td>
					<td nowrap="nowrap" style="text-align:right;">${product.num}</td>
					<td nowrap="nowrap" style="text-align:right;">
						<c:if test="${product.transportWay==0}">无指定</c:if>
						<c:if test="${product.transportWay==1}">自提</c:if>
						<c:if test="${product.transportWay==2}">快递</c:if>
						<c:if test="${product.transportWay==3}">汽运</c:if>
						<c:if test="${product.transportWay==4}">铁运</c:if>

						<c:if test="${product.transportWay==5}">航空</c:if>
						<c:if test="${product.transportWay==6}">海运</c:if>
						<c:if test="${product.transportWay==7}">中铁</c:if> 
						<c:if test="${bproductox.transportWay==8}">市内送货</c:if>
						<c:if test="${product.transportWay==9}">市内快递</c:if> 
					</td>
				</tr>
			</logic:iterate>
			</c:if> 
		</tbody>
      </table> 
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
   <td colspan="2" align="center" height="50px" valign="bottom">
	<a href=#><img src="${ctx}/images/btnPrint.gif" width="65" height="20" class="buttonNoPrint" onClick="javascript:printIt();"/></a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href=#><img src="${ctx}/images/btnPrintSZ.gif" width="88" height="20" class="buttonNoPrint" onClick="javascript:printSetup();"/></a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href=#><img src="${ctx}/images/btnPrintYL.gif" class="buttonNoPrint" onClick="javascript:printPreview();" /></a>
	</td>
  </tr>
</table>
</body>
</html>
