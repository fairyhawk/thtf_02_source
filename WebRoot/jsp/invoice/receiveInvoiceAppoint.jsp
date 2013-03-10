<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<c:if test="${param.isInvoice==false}">
	<script>alert("指定失败")</script>
</c:if>

<c:if test="${param.invoiceMoneyCheck==false}">
	<script>alert("收票金额合计必须等于发票金额")</script>
</c:if>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购发票指定</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
        <script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/contractUtil.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
<style type="text/css">
<!--
.STYLE1 {
	color: #FF0000
}
.treven {
	background-color: #f3fbff;
}
.over {
	background-color: #ECECEC;
}
-->
</style>
<script language="JavaScript"> 
  
var countRow=0;
var lastDeleteIndex=-1;
var indexRow=0;
function init(){
var tableObj = document.getElementById("prodtbody");
 countRow = tableObj.rows.length-1;
 indexRow=tableObj.rows.length-1;

}

	$(document).ready(function(){
		updateClass();//重新设置行样式
	});

	//进入选择产品页面
	function selectProduct(){
		var prodTypeId   = $("#prodTypeId").val();
		var prodTypeName = "${dto.prodTypeName}";
		var supplierId=$("#supplierId").val();
		window.open('${ctx}/getInStockProdList.do?prodTypeId='+prodTypeId+'&prodTypeName='+encodeURI(prodTypeName,"UTF-8")+'&supplierId='+supplierId,'newwindow', "toolbar=no,scrollbars=yes,resizable=no,top=200,left=200, width=1000,height=400");
	};

	//去除重复选择产品的项
	function newWindow(myArrayProduct){
		var productids = document.getElementsByName("productids");//获得产品Id
		 var inStockId=document.getElementsByName("inStockId");
		for(var i=0;i<productids.length;i++){
			for(var j=0;j<myArrayProduct.length;j++){
				var id = myArrayProduct[j];
				if(productids[i].value==id[3]&&inStockId[i].value==id[0]){
					myArrayProduct.splice(j,1);
				}
			}
		}
		insertCell(myArrayProduct);
	};
	//从产品小页面返回的信息,显示
	var receiveTotalMoeny=0;
	function insertCell(myArray){
		var averagePrice = 0;
		
		for(var i=0;i<myArray.length;i++){
			var arr=myArray[i];
			var tables = document.getElementById("prodtbody");
			//添加一行
	        var newTr = tables.insertRow();
	       
	        countRow++;
	        indexRow++;
	        receiveTotalMoeny+=parseFloat(arr[12]);//計算总金额
	        //添加十列
	        var newTd0 = newTr.insertCell();
	        
	        var newTd1 = newTr.insertCell();
	        var newTd2 = newTr.insertCell();
	        var newTd3 = newTr.insertCell();
	        var newTd4 = newTr.insertCell();
	        var newTd5 = newTr.insertCell();
	        var newTd6 = newTr.insertCell();
	        var newTd7 = newTr.insertCell();
	        var newTd8 = newTr.insertCell();
	        var newTd9 = newTr.insertCell();
	        var newTd10= newTr.insertCell();
	        var newTd11= newTr.insertCell();
	        var newTd12= newTr.insertCell();
	        var newTd13= newTr.insertCell();
	        var newTd14= newTr.insertCell();
	        //设置列内容和属性
	        newTd0.innerHTML = '<input type="hidden" name="taxRate" value="'+arr[14]+'"/><input type="hidden" name="prodMoney" value="'+arr[15]+'"/><input type="hidden" name="productids" value="'+arr[3]+'"><input type="hidden" name="prodid" id="prodid" value="'+arr[3]+'"/><input type="checkbox" name="productCheckbox" id="sel'+countRow+'" value="'+arr[0]+'"/>';
	        newTd0.style.borderLeft="1px solid #c2c2c2";
	        newTd1.innerHTML = '<input type="hidden" name="inStockId" value="'+arr[0]+'">'+arr[0];
	        newTd2.innerText = ''+arr[1];
	        newTd3.innerText = ''+arr[2];
	        newTd4.innerText = ''+arr[4];
	        newTd5.innerText = ''+arr[5];
	        newTd6.innerHTML = ''+arr[6];
	        newTd7.innerHTML = ''+arr[7];
	        newTd8.innerHTML = '<input type="hidden" name="price" value="'+arr[8]+'"/>'+arr[8]+'&nbsp;';
	         newTd8.style.textAlign="right";
	        newTd9.innerHTML = ''+arr[9]+'&nbsp;';
	         newTd9.style.textAlign="right";
			newTd10.innerHTML = ''+arr[10]+'&nbsp;';
			 newTd10.style.textAlign="right";
	        newTd11.innerHTML = ''+arr[11]+'&nbsp;';
	        newTd11.style.textAlign="right";
	        newTd12.innerHTML = '<input type="text" style="text-align:left" name="receiveInvoiceCount" onfocus="remClass(this)" onblur="receiveInvoiceCountCheck(this,'+arr[9]+','+arr[11]+','+arr[8]+',\'receiveInvoiceMoney'+arr[0]+arr[3]+'\')" id="receiveInvoiceCount" value="0"/>';
	        newTd13.innerHTML = '<input type="hidden" name="receiveInvoiceMoney" id="receiveInvoiceMoney'+arr[0]+arr[3]+'" value="0.00000"/><div id="DIVreceiveInvoiceMoney'+arr[0]+arr[3]+'">0.00000</div>';
	         newTd13.style.textAlign="right";
	         newTd13.style.paddingRight="5px";
	        newTd14.innerHTML = ''+arr[13]+'&nbsp;';
	         newTd14.style.textAlign="right";
		}
		$("#tableLength").attr("value", countRow);
		totleMoneyMethod();
		updateClass();//重新设置行样式
	}
	//收票数录入键盘弹起事件
	function receiveInvoiceCountCheck(receiveCount,inStockCount,alreadyReceiveCount,buyUnitPrice,rIMoney){
		var x="1"+receiveCount.value;
		var y=parseInt(receiveCount.value,10)+"";
		 if(receiveCount.value!=""&&(!isNaN(receiveCount.value))&&receiveCount.value!=parseInt(receiveCount.value,10)){
				alert("收票数只能为正整数！");
				receiveCount.focus();
		}else if(isNaN(receiveCount.value)){
			alert("收票数只能为正整数！");
			receiveCount.select();
		}else if((parseInt(receiveCount.value,10)+parseInt(alreadyReceiveCount,10))>inStockCount){
			alert("收票数应小于等于入库数减已收票数")
			//receiveCount.value=(inStockCount-alreadyReceiveCount);
			//document.getElementById(rIMoney).value=receiveCount.value*buyUnitPrice;
			//document.getElementById("DIV"+rIMoney).innerHTML=formatMoney(receiveCount.value*buyUnitPrice,2);
			//totleMoneyMethod();
			receiveCount.select();
		}else if(receiveCount.value<0){
			alert("收票数应大于零")	
			//receiveCount.value="0";
			receiveCount.select();
		}else if(x.length-1!=y.length){
			alert("收票数只能为正整数");
			receiveCount.select();
		}else{
			document.getElementById(rIMoney).value=receiveCount.value*buyUnitPrice; 
			document.getElementById("DIV"+rIMoney).innerHTML=formatMoney(FloatMul(buyUnitPrice,receiveCount.value),5);
			totleMoneyMethod();
		}
		//receiveCount.focus();//让当前文本框获取焦点
	}
	//转换money的格式为（###,###.##） s表示需要转换的money，n表示有多少位小数
	function formatMoney(s, n)
		{
		   n = n > 0 && n <= 20 ? n : 2;
		   s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
		   var l = s.split(".")[0].split("").reverse(),
		   r = s.split(".")[1];
		   t = "";
		   for(i = 0; i < l.length; i ++ )
		   {
			  t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? "," : "");
		   }
		   return t.split("").reverse().join("") + "." + r;
		}
	// 刪除時的全选
	function checkAll(record){
   		var allCheck = document.getElementsByName("productCheckbox");
    		 if(record.checked==true){
       			 for(i=0;i<allCheck.length;i++){
            		allCheck[i].checked=true;
       		 }
   			 }else if(record.checked==false){
        		for(i=0;i<allCheck.length;i++){
            		allCheck[i].checked=false;
        		}
   	 		 }
	};
	// 刪除所选择的产品的相关操作
	function removeProduct(){
		judgeRemovePro();
		// 金额计算
		totleMoneyMethod();
		$("#tableLength").attr("value", countRow);
	}
	
	// 刪除所选择的产品
	function judgeRemovePro(){
		var productAll = document.getElementsByName("productCheckbox");
		if(productAll.length==0){
			alert("没有可删除的产品");
		}
		var table = document.getElementById("prodtbody");
		//删除行
		var oldCount = countRow+1;
		var bol=false;
		for(var i=countRow;i>0;i--){
			var checkId = document.getElementById("sel"+i);
			if(checkId.checked==true){
				bol=true;
				table.deleteRow(i);
				countRow--;
				lastDeleteIndex=i-1;
				indexRow--;
			}
		}
		//重新设置checkbox的自增ID
		if(bol){
				for(i=lastDeleteIndex+2;i<oldCount;i++){
					var box = document.getElementById("sel"+i);
					if(box!=null){
					box.id="sel"+(++lastDeleteIndex);
					}
				}
				//changeIndex();
				if(countRow==0){
	   				$("#chckbxSelectAllProduct").attr("checked", false);
				}
				updateClass();//重新设置行样式
		}
	};
	//自动添加序号
	    function changeIndex(){
	    	var contractDetail = $("#prodtbody").get(0);
	    	var detailCount = contractDetail.rows.length;
	    	for(var i=1; i<detailCount; i++){
	    		contractDetail.rows[i].cells[1].innerHTML = i;
	    	}
	    }	
	     //更新样式
	 function updateClass(){
	 	if($("#prodtbody")){
					$("#prodtbody tr:even").addClass("treven");//偶数行添加样式
					$("#prodtbody tr:odd").removeClass("treven");//奇数行移除样式
				}
	 }
	function rmoney(s){ 
		return parseFloat(s.replace(/[^\d\.-]/g,""));  
	} 
	//计算总金额
	function totleMoneyMethod(){
		//得到所有产品信息
		var tableObj = document.getElementById("prodtbody");
		var tlength = tableObj.rows.length;
		vtotle=0;
		for(var i=1;i<tlength;i++){
		    var price = rmoney(tableObj.rows[i].cells[13].innerText);; //收票金额
			vtotle = FloatAdd(vtotle,parseFloat(price));
		} 
			$("#receiveTotalMoenyHidden").val(vtotle);//给总收票金额隐藏域赋值
	document.getElementById("receiveTotalMoeny").innerHTML=""+formatMoney(vtotle,5);//给总收票金额隐藏域赋值
	}
	//指定提交
	function subAppoint(){
		//得到收票总金额
		var totalReceiveInvoiceMoney=$("#receiveTotalMoenyHidden").val();
		//得到发票金额
		var invoiceMoney=$("#invoiceMoney").val();
		//得到所有产品信息
		var tableObj = document.getElementById("prodtbody");
		var tlength = tableObj.rows.length;
		var ts=true;
		if(tlength<=1){//判断长度大于1而不大于0是因为在prodtbody中存在一个空行
			alert("当前没有产品信息，无法指定");
			return;
		}else{
		////////////
		for(var i=1;i<tlength;i++){
		    var warehousingCount = parseInt($(tableObj.rows[i].cells[10]).text()); //入库数
		    var _ticketCount=$(tableObj.rows[i].cells[13]).children();//收票数
			var _yTicketCount=parseInt($(tableObj.rows[i].cells[12]).text());//已收票数
		    if(_ticketCount.val()==""){
		   		 _ticketCount.addClass("invalid");
		    	//alert("收票数不能为空");
		    	ts=false;
		    }else if(isNaN(_ticketCount.val())){//如果输入非整数
		    	_ticketCount.addClass("invalid");
		    	//alert("收票数必须为正整数");
		    	ts=false;
		    	//return;
		    }
			
		    var ticketCount=parseInt(_ticketCount.val(),10);
		    if(ticketCount<=0||ticketCount>(warehousingCount-_yTicketCount)){
		    	$(tableObj.rows[i].cells[12]).children().addClass("invalid");
		    	//alert("收票数大于零且小于等于入库数减已收票数");
		    	ts=false;
		    	//return;
		    }
		}
		
		/////////////
			if(ts){//如果收票数录入格式有效
				if(parseFloat(totalReceiveInvoiceMoney)!=parseFloat(invoiceMoney)){
					alert("收票金额合计必须等于发票金额");
					return;
				}else{
					$("#appointForm").submit();
				}
			}else{
				alert("收票数有误");
			}
		}
	}
	function remClass(obj){
		$(obj).removeClass("invalid");
	}
</script>

</head>
<body onload="init()">
<form action="${ctx}/addReceiveInvoiceAppoint.do?receiveType=${dto.invoiceType}&receiveRate=${dto.taxRate}"  method="post" id="appointForm">
<input type="hidden" id="tableLength" value="0"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" /> &nbsp;当前位置： 采购管理 &gt;&gt; 采购发票管理 &gt;&gt; 采购发票指定</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;采购发票信息 </div>
	  <input type="hidden" id="prodTypeId" name="prodTypeId" value="${dto.prodTypeId}"/>
	  <input type="hidden" id="supplierId" name="supplierId" value="${dto.supplierId}"/>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18">产品分类名称</td>
          <td class="td_02" width="30%" id="prodTypeName">${dto.prodTypeName}&nbsp;</td>
          <td class="td_01" width="20%">供货商名称</td>
          <td class="td_02" width="30%">${dto.supplierName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18">收票日期</td>
          <td class="td_02">${dto.receiveDate}&nbsp;</td>
          <td class="td_01">发票金额</td>
          <td class="td_02" >
		  <input type="hidden" name="invoiceMoney" id="invoiceMoney" value="${dto.invoiceMoney}"/>
		  <fmt:formatNumber value="${dto.invoiceMoney}" pattern="#,##0.00000"/>
		  &nbsp;元</td>
        </tr>
        <tr>
          <td class="td_01" height="18">发票类型</td>
		  <c:if test="${dto.invoiceType==0}">
          <td class="td_02">普通&nbsp;</td>
		  </c:if>
		  <c:if test="${dto.invoiceType==1}">
          <td class="td_02">增值税&nbsp;</td>
		  </c:if>
          <td class="td_01">发票号</td>
          <td class="td_02">${dto.invoiceNo}&nbsp;</td>
        </tr>
       <c:if test="${dto.invoiceType==1}">
        <tr>
          <td class="td_01" height="18">税率</td>
          <td colspan="3" class="td_02">${dto.taxRate}%</td>
        </tr>
         </c:if>
        <tr>
          <td class="td_01" height="18">特殊说明</td>
          <td colspan="3" class="td_02">${tf:replaceBr(dto.text)}&nbsp;</td>
        </tr>
      </table>
      <br/>
      <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <div style="width:100%; text-align:right">单位：元&nbsp;</div>
     
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
        <thead>
          <tr>
            <th nowrap="nowrap" style="border-left:1px solid #c2c2c2;">选择</th>
            <th nowrap="nowrap">入库单号</th>
            <th nowrap="nowrap">产品合同号</th>
            <th nowrap="nowrap">公司合同号</th>
            <th nowrap="nowrap">产品编码</th>
            <th nowrap="nowrap">产品名称</th>
            <th nowrap="nowrap">规格型号</th>
            <th nowrap="nowrap">单位</th>
            <th nowrap="nowrap">采购单价 </th>
            <th nowrap="nowrap">入库数</th>
            <th nowrap="nowrap">指定金额</th>
            <th nowrap="nowrap">已收票数</th>
            <th nowrap="nowrap">收票数</th>
            <th nowrap="nowrap">收票金额</th>
            <th nowrap="nowrap">返厂金额</th>
          </tr>
        </thead>
        <tbody id="prodtbody">
        <tr>
        </tr>
          <bean:define id="total" value="0" type="java.lang.String"/>
        <logic:iterate id="list" name="list" indexId="index">
        <bean:define id="total" value="${list.receiveInvoiceMoney+total}" type="java.lang.String"/>
			  <tr>
			  <td nowrap="nowrap" style="border-left:1px solid #c2c2c2"><input type="hidden" name="taxRate" value="${list.taxRate}"/><input type="hidden" name="prodMoney" value="${list.money}"/><input type="hidden" name="productids" value="${list.prodId}"><input type="hidden" name="prodid" id="prodid" value="${list.prodId}"/><input type="checkbox" name="productCheckbox" id="sel${index+1}" value="${list.inStockId}"/></td>
				<td nowrap="nowrap" ><input type="hidden" name="inStockId" value="${list.inStockId}">${list.inStockId}&nbsp;</td>
				<td nowrap="nowrap" >${list.prodContractCode}&nbsp;</td>
				<td nowrap="nowrap" >${list.compContractCode}&nbsp;</td>
				<td nowrap="nowrap" >${list.prodCode}&nbsp;</td>
				<td nowrap="nowrap" >${list.prodName}&nbsp;</td>
				<td nowrap="nowrap" >${list.prodType}&nbsp;</td>
				<td nowrap="nowrap" >${list.prodUnit}&nbsp;</td>
				<td nowrap="nowrap" style="text-align:right;"><input type="hidden" name="price" value="${list.buyUnitPrice}"/>
				<fmt:formatNumber value="${list.buyUnitPrice}" pattern="#,##0.00000"/>&nbsp;</td>
				<td nowrap="nowrap" style="text-align:right;">${list.inStockCount}&nbsp;</td>
				<td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${list.appointMoney}" pattern="#,##0.00000"/>&nbsp;</td>
				<td nowrap="nowrap" style="text-align:right;">${list.receivedInvoiceCount}&nbsp;</td>
				<td nowrap="nowrap" ><input type="text" style="text-align:left" name="receiveInvoiceCount" onfocus="remClass(this)" onblur="receiveInvoiceCountCheck(this,${list.inStockCount},${list.receivedInvoiceCount},${list.buyUnitPrice},'receiveInvoiceMoney${list.inStockId}${list.prodId}')" id="receiveInvoiceCount" value="${list.receiveInvoiceCount}"/></td>
				<td nowrap="nowrap" style="text-align:right;padding-right:5px"><input type="hidden" name="receiveInvoiceMoney" id="receiveInvoiceMoney${list.inStockId}${list.prodId}" value="${list.receiveInvoiceMoney}"/><div id="DIVreceiveInvoiceMoney${list.inStockId}${list.prodId}">
				<fmt:formatNumber value="${list.receiveInvoiceMoney}" pattern="#,##0.00000"/>
				</div></td>
				<td nowrap="nowrap" style="text-align:right;">
				<fmt:formatNumber value="${list.backGoodsMoney}" pattern="#,##0.00000"/>
				&nbsp;</td>
			  </tr>
		  </logic:iterate>
        </tbody>
        <tfoot>
          <tr>
            <td style=" border:1px solid #FFFFFF; background-color:#FFFFFF">
              <input type="checkbox" name="checkbox3" id="chckbxSelectAllProduct" onclick="checkAll(this);" /><input type="hidden" name="receiveInvoiceId" id="receiveInvoiceId" value="${dto.receiveInvoiceId}"/>
              </span></td>
            <td colspan="2" valign="middle"  nowrap="nowrap" style="  border:1px solid #FFFFFF;  background-color:#FFFFFF">全选&nbsp;&nbsp; <img onclick="removeProduct();" src="${ctx}/images/btnDelete.gif" width="65" height="20" align="absmiddle" />&nbsp;&nbsp; <img src="${ctx}/images/btnAdd.gif" align="absmiddle" onClick="selectProduct();"/> </td>
            </td>
            <td colspan="9"  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; ">&nbsp;</td>
            <td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right">收票金额合计</td>
            <td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:right" id="receiveTotalMoeny"><fmt:formatNumber value="${total}" pattern="#,##0.00000"/></td>
            <td  nowrap="nowrap" style=" border:1px solid #FFFFFF;  background-color:#FFFFFF; text-align:left"><input type="hidden" id="receiveTotalMoenyHidden" value="${total}"/>元</td>
          </tr>
        </tfoot>
      </table>
      
      </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom"><img onclick="subAppoint();" src="${ctx}/images/btnZD.gif" width="65" height="20" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="${ctx}/getReceiveInvoiceList.do"><img src="${ctx}/images/btnBack.gif" /></a> </td>
    <td></td>
  </tr>
</table>
</form>
</body>
</html>
