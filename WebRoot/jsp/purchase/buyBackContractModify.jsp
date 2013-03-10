<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购退货合同修改</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/common/contractUtil.js"></script>
		<script type="text/javascript" src="${ctx}/js/math.js"></script>
		<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
        <script type="text/javascript" src="${ctx}/js/util.js"></script>
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

</head>
<body>
<c:if test="${param.roleError}">
  <script>alert("越权操作！");</script>
</c:if>
<c:if test="${param.place}">
	<script>alert("签订地点不能为空！");</script>
</c:if>
<c:if test="${param.buyContractId}">
	<script>alert("对应的采购合同信息出错！");</script>
</c:if>
<c:if test="${param.prodTypeId}">
	<script>alert("产品类型不存在！");</script>
</c:if>
<c:if test="${param.principal}">
	<script>alert("采购主管不存在！");</script>
</c:if>
<c:if test="${param.compliance}">
	<script>alert("法务专员不存在！");</script>
</c:if>
<c:if test="${param.money}">
	<script>alert("退货总金额有误！");</script>
</c:if>
<c:if test="${param.prods}">
	<script>alert("没有选择退购的产品！");</script>
</c:if>
<c:if test="${param.fileExistError}">
	<script>alert("请选择文件！");</script>
</c:if>
<c:if test="${param.fileError}">
	<script>alert("文件上传失败:文件格式不正确。");</script>
</c:if>
<c:if test="${param.isInsert}">
	<script>alert("退货合同添加失败！");</script>
</c:if>
<c:if test="${param.isTokenValid}">
	<script>alert("不能重复提交！");</script>
</c:if>
<c:if test="${param.statusError}">
	<script>alert("执行失败，此状态不能操作");</script>
</c:if>
<c:if test="${param.fileMaxError}">
	<script>alert("${param.msg}");</script>
</c:if>
<c:if test="${buyBackContract.status!=1&&buyBackContract.status!=3&&buyBackContract.status!=5&&buyBackContract.status!=7&&buyBackContract.status!=10}">
	<script>
	location="${ctx}/buyBackContractList.do";
	</script>
</c:if>
<html:form action="modifyBuyBackContract" method="post" styleId="buyBackForm" enctype="multipart/form-data">
<input type="hidden" id="tableLength" value="0"/>
<input type="hidden" id="buyState" name="bbce.status"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 采购管理 &gt;&gt; 采购退货合同管理 &gt;&gt; 采购退货合同修改</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;采购合同信息 </div>
	  <input type="hidden" id="buyContractId" name="bbce.buyContractId" value="${buyBackContract.buyContractId}"/>
	  <input type="hidden" id="prodTypeId" name="bbce.productTypeId" value="${contractDto.prodTypeId}"/>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" height="18px">产品合同号</td>
          <td class="td_02">${contractDto.prodContractCode}&nbsp;</td>
          <td class="td_01">公司合同号</td>
          <td class="td_02">${contractDto.compContractCode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">签订日期</td>
          <td class="td_02" width="30%">${contractDto.signDate}&nbsp;</td>
          <td class="td_01" width="20%">产品分类名称</td>
          <td class="td_02" width="30%">${contractDto.prodTypeName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">合同金额（元）</td>
          <td class="td_02"><fmt:formatNumber value="${contractDto.contractMoney}" pattern="#,##0.00000"/>&nbsp;</td>
          <td class="td_01">预付金额（元）</td>
          <td class="td_02"><fmt:formatNumber value="${contractDto.prepayMoney}" pattern="#,##0.00000"/>&nbsp;</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;供货商信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">供货商名称</td>
          <td class="td_02" width="30%">${contractDto.supplierName}&nbsp;</td>
          <td class="td_01" width="20%">联系人</td>
          <td class="td_02" width="30%">${contractDto.linkman}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">省份</td>
          <td class="td_02">${contractDto.province}&nbsp;</td>
          <td class="td_01">电话</td>
          <td class="td_02">${contractDto.tel}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">城市</td>
          <td class="td_02">${contractDto.city}&nbsp;</td>
          <td class="td_01">传真</td>
          <td class="td_02">${contractDto.fax}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">开户行</td>
          <td class="td_02">${contractDto.remitBankName}&nbsp;</td>
          <td class="td_01">帐号</td>
          <td class="td_02">${contractDto.remitBankAccount}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">发票类型</td>
		  <td>&nbsp;
			<c:if test="${contractDto.invoiceType==0}">普通</c:if>
			<c:if test="${contractDto.invoiceType==1}">增值税</c:if>
          </td>
          <td class="td_01">增值税税率</td>
          <td class="td_02">${contractDto.taxRate} %</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;退货合同信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%">合同类型</td>
          <td class="td_02" width="30%">
          	<c:if test="${contractDto.contractType==0}">
          		国内
          	</c:if>
          	<c:if test="${contractDto.contractType==1}">
          		国外
          	</c:if>
          </td>
          <td class="td_01" width="20%"><span class="STYLE1">*</span> 模版类型</td>
                 <td class="td_02" width="30%" >
				 <input id="templement1" name="bbce.templateType" type="radio" value="0"
				  <c:if test="${buyBackContract.templateType==0}"> 
				  checked="checked" </c:if>
				  onclick="radM(this)" />
            模版
            <input type="radio"  name="bbce.templateType"
            <c:if test="${buyBackContract.templateType==1}">
             checked="checked" 
             </c:if> 
             id="templement2" value="1" onclick="radM(this)" />
            非模版</td>
        </tr>
        
		<c:if test="${buyBackContract.templateType==0}">
        <tr id="tr_MT">
          <td class="td_01" height="18px">签订地点</td>
          <td colspan="3" class="td_02" >${contractDto.place}
			<input type="hidden" name="bbce.place" value="${contractDto.place}"/>
		  </td>
        </tr>
        <tr id="tr_MF" style="display:none">
        	<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp选择文件</td>
        	<td class="td_02" colspan="3" width="80%">
			<div style="float:left"><html:file property="file" styleId="templementFile" value="${buyBackContract.file}" onchange="focusRemoveCls(this)"></html:file></div>
			<div id="fileError_div" style="float:left;margin-left:10px"></div>
			<input type="hidden" name="fileHidden" id="fileHidden" value="${buyBackContract.file}"/>
			</td>
        </tr>
		</c:if>
		<c:if test="${buyBackContract.templateType==1}">
		<tr id="tr_MT" style="display:none">
          <td class="td_01" height="18px">签订地点</td>
          <td colspan="3" class="td_02" >${contractDto.place}
			<input type="hidden" name="bbce.place" value="${contractDto.place}"/>
		  </td>
        </tr>
        <tr id="tr_MF">
        	<td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp选择文件</td>
        	<td class="td_02" colspan="3" width="80%">
			<div style="float:left"><html:file property="file" styleId="templementFile" value="${buyBackContract.file}" onchange="focusRemoveCls(this)"></html:file></div>
			<div id="fileError_div" style="float:left;margin-left:10px"></div>
			<input type="hidden" name="fileHidden" id="fileHidden" value="${buyBackContract.file}"/>
			</td>
        </tr>
		</c:if>
      </table>
      <br/>
      <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
      <thead>
        <tr>
          <th nowrap="nowrap" style="border-left:1px solid #c2c2c2;">选择</th>
          <th nowrap="nowrap" >序号</th>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">采购数</th>
          <th nowrap="nowrap">采购单价</th>
          <th nowrap="nowrap">采购金额</th>
          <th nowrap="nowrap">入库金额</th>
          <th nowrap="nowrap">指定金额</th>
          <th nowrap="nowrap">收票金额</th>
          <th nowrap="nowrap">已退货合同数</th>
          <th nowrap="nowrap" width="90">返厂金额</th>
          <th nowrap="nowrap" width="90">退货合同数</th>
          <th nowrap="nowrap">退货合同金额</th>
          <th nowrap="nowrap">库存总数</th>
        </tr>
        </thead>
        <tbody id="prodtbody">
        <tr>
        </tr>
		<bean:define id="total" value="0" type="java.lang.String" />
		<logic:iterate id="prod" name="prods" indexId="prodindex">
		<bean:define id="total" value="${total+(prod.buyPrice*prod.buyBackContractCount)}" type="java.lang.String" />
			<tr>
				<td style="border-left:1px solid #c2c2c2;"><input type="hidden" name="prodid" id="productids" value="${prod.prodId}"><input type="checkbox" name="productCheckbox" id="sel${prodindex+1}" value="${prod.prodId}"/></td>
				<td><input type="hidden" name="inStockId" value="${prod.prodId}">${prodindex+1}&nbsp;</td>
				<td>${prod.prodCode}&nbsp;</td>
				<td>${prod.prodName}&nbsp;</td>
				<td>${prod.prodType}&nbsp;</td>
				<td>${prod.prodUnit}&nbsp;</td>
				<td style="text-align:right;">${prod.buyCount}&nbsp;</td>
				<td style="text-align:right;"><input type="hidden" name="price" id="price" value="${prod.buyPrice}"/><fmt:formatNumber value="${prod.buyPrice}" pattern="#,##0.00000"/>&nbsp;</td>
				<td style="text-align:right;"><fmt:formatNumber value="${prod.buyMoney}" pattern="#,##0.00000"/>&nbsp;</td>
				<td style="text-align:right;"><fmt:formatNumber value="${prod.instockMoney}" pattern="#,##0.00000"/>&nbsp;</td>
				<td style="text-align:right;"><fmt:formatNumber value="${prod.appointMoney}" pattern="#,##0.00000"/>&nbsp;</td>
				<td style="text-align:right;"><fmt:formatNumber value="${prod.receiveInvoiceMoney}" pattern="#,##0.00000"/>&nbsp;</td>
				<td style="text-align:right;">${prod.backContractCount}&nbsp;</td>
				<td style="text-align:right;"><fmt:formatNumber value="${prod.returnMoney}" pattern="#,##0.00000"/>&nbsp;</td>
				<td><input type="text"  style="width:80px;" name="count" id="buyBackCount" onblur="buyBackCountCheck(this,${prod.buyCount},${prod.backContractCount},${prod.buyPrice},'buyBackMoney${prod.prodId}')" id="buyBackCount" value="${prod.buyBackContractCount}"/>&nbsp;</td>
				<td style="text-align:right;padding-right:5px"><input type="hidden" name="buyBackMoney" id="buyBackMoney${prod.prodId}" value="0.00000"/><div id="DIVbuyBackMoney${prod.prodId}"><fmt:formatNumber value="${prod.buyPrice*prod.buyBackContractCount}" pattern="#,##0.00000"/>&nbsp;</div></td>
				<td style="text-align:right;">${prod.stockTotalNum}&nbsp;</td>
			</tr>
		</logic:iterate>
        </tbody>
        <tfoot>
        <tr>
          <td nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF">
            <input type="checkbox" name="checkbox6" id="chckbxSelectAllProduct" onclick="checkAll(this);" />
          </td>
          <td nowrap="nowrap" style="border:1px solid #FFFFFF; background-color:#FFFFFF" height="18">全选</td>
          <td nowrap="nowrap" colspan="2" style="border:1px solid #FFFFFF; background-color:#FFFFFF"><img onclick="removeProduct();" src="${ctx}/images/btnDelete.gif" width="65" height="20" />&nbsp;&nbsp; <img src="${ctx}/images/btnAdd.gif" onClick="selectProduct();" /> </td>
          <td colspan="11" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">退货合同金额合计</td>
          <td nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF" id="receiveTotalMoeny">
				<fmt:formatNumber value="${total}" pattern="#,##0.00000"/>
		</td>
          <td nowrap="nowrap" style=" border:1px solid #FFFFFF; background-color:#FFFFFF"><input type="hidden" id="receiveTotalMoenyHidden" value="${total}" name="bbce.money"/> 元</td>
        </tr>
        </tfoot>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;退货退款信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td width="50%" rowspan="4" class="td_03"><span class="STYLE1">*</span> 退货退款方式选择</td>
          <td class="td_04" >&nbsp;已供货已付款&nbsp;&nbsp;&nbsp;
            <input type="radio" name="bbce.backWay" <c:if test="${buyBackContract.backWay==1}">checked="checked"</c:if> id="radio3" value="1" />
            先退货后退款&nbsp;&nbsp;
            <input type="radio" name="bbce.backWay" <c:if test="${buyBackContract.backWay==2}">checked="checked"</c:if> id="radio4" value="2" />
            先退款后退货</td>
        </tr>
        <tr>
          <td class="td_04" >
			<input type="radio" name="bbce.backWay" <c:if test="${buyBackContract.backWay==3}">checked="checked"</c:if> id="radio5" value="3" />
            已供货未付款</td>
        </tr>
        <tr>
          <td class="td_04" >
			 <input type="radio" name="bbce.backWay" <c:if test="${buyBackContract.backWay==4}">checked="checked"</c:if> id="radio7" value="4" />
            未供货已付款</td>
        </tr>
        <tr>
          <td class="td_04" >
			<input type="radio" name="bbce.backWay" <c:if test="${buyBackContract.backWay==5}">checked="checked"</c:if> id="radio6" value="5" />
            未供货未付款</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;发货信息</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
          <td width="62%">&nbsp;&nbsp;<a href="#" onclick="javascript:window.open('${ctx}/getSupplierAddress.do?buyContractId=${buyBackContract.buyContractId}','newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=900,height=400');"><img src="${ctx}/images/btnFHDZ.gif" /></a></td>
          <td align="left"><span style="color:#FF0000">*</span>&nbsp;要求发货日期&nbsp;
            <input type="text" name="bbce.requestSendDate"
			id="deliveryDate" style="width:120px;"   value="${buyBackContract.requestSendDate}" onfocus="focusRemoveCls(this),calendarMinTomorrow()" readonly="readonly" /></td>
        </tr>
      </table>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18">货物接收单位名称</td>
          <td colspan="3" class="td_02" ><input type="hidden" id="supplierAddressId" name="bbce.supplierAddressId" value="${supplierAddress.id}"/><input type="hidden" id="supplierId" value="${supplierAddress.supplierId}"/>
          	<label id="name">${supplierAddress.name}</label></td>
        </tr>
        <tr>
          <td class="td_01" height="18">发货地址</td>
          <td colspan="3" class="td_02" ><label id="address">${supplierAddress.address}</label></td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18">联系人</td>
          <td class="td_02" width="30%"><label id="linkman">${supplierAddress.linkman}</label></td>
          <td class="td_01" width="20%">邮编</td>
          <td class="td_02" width="30%"><label id="postcode">${supplierAddress.postcode}</label></td>
        </tr>
        <tr>
          <td class="td_01" height="18">电话</td>
          <td class="td_02" ><label id="tel">${supplierAddress.tel}</label></td>
          <td class="td_01">手机</td>
          <td class="td_02" ><label id="mobile">${supplierAddress.mobile}</label></td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;退货原因说明</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="30%"><span class="STYLE1">*</span> 退货原因说明</td>
          <td class="td_04" ><textarea name="bbce.text" onfocus="focusRemoveCls(this)" id="textArea" cols="100" rows="4">${buyBackContract.text}</textarea>
          </td>
        </tr>
      </table></td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom"><a href="javascript:subBuyBackContract(0);"><img src="${ctx}/images/btnSave.gif" width="65" height="20"/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:subBuyBackContract(1);"><img src="${ctx}/images/btnSubmit.gif" width="65" height="20"/></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="${ctx}/buyBackContractList.do?init=true"><img src="${ctx}/images/btnBack.gif" /></a></td>
    <td></td>
  </tr>
</table>
<br />
<input type="hidden" name="bbce.id" value="${buyBackContract.buyBackContractId}"/>
</html:form>
</body>
<script language="JavaScript"> 
//得到所有产品信息
var tableObj = document.getElementById("prodtbody");
var countRow=tableObj.rows.length-1;
var lastDeleteIndex=tableObj.rows.length-2;
var indexRow=tableObj.rows.length-1;
	$(document).ready(function(){
		if($("#table")){
			$("#table tr:odd").addClass("treven");
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
function rmoney(s){ 
	return parseFloat(s.replace(/[^\d\.-]/g,""));  
} 
function radM(rad){
	if(rad.value==0){
		document.getElementById("tr_MT").style.display="block";
		document.getElementById("tr_MF").style.display="none";
	}else{
		document.getElementById("tr_MT").style.display="none";
		document.getElementById("tr_MF").style.display="block";
	}
}
//////////////////////产品信息///////////////////
	//进入选择产品页面
	function selectProduct(){
		var buyContractId = $("#buyContractId").val();
		window.open('${ctx}/getProdInfoById.do?buyContractId='+buyContractId,'newwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=900,height=400');
	};
	//去除重复选择产品的项
	function newWindow(myArrayProduct){
		var productids = document.getElementsByName("prodid");//获得产品Id
		for(var i=0;i<productids.length;i++){
			for(var j=0;j<myArrayProduct.length;j++){
				var id = myArrayProduct[j];
				if(productids[i].value==id[0]){
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
			var newTd15= newTr.insertCell();
			var newTd16= newTr.insertCell();
	        //设置列内容和属性
	        newTd0.innerHTML = '<input type="hidden" name="prodid" id="productids" value="'+arr[0]+'"><input type="checkbox" name="productCheckbox" id="sel'+countRow+'" value="'+arr[0]+'"/>';
	        newTd0.style.borderLeft  = '1px';
	        newTd0.style.borderLeftColor = '#c2c2c2';
	        newTd0.style.borderLeftStyle = 'solid';
	        newTd1.innerHTML = '<input type="hidden" name="inStockId" value="'+arr[0]+'">'+countRow;
	        newTd2.innerText = ''+arr[1];//产品编码
	        newTd3.innerText = ''+arr[2];//产品名称
	        newTd4.innerText = ''+arr[3];//规格型号
	        newTd5.innerText = ''+arr[4];//单位
	        newTd6.innerHTML = ''+arr[5]+'&nbsp;';//采购数
	        newTd6.style.textAlign='right';
	        newTd7.innerHTML = '<input type="hidden" name="price" id="price" value="'+rmoney(formatMoney(arr[6],5)+"")+'"/>'+formatMoney(arr[6],5)+'&nbsp;';//采购单价
	        newTd7.style.textAlign='right';
	        newTd8.innerHTML = ''+formatMoney(arr[7],5)+'&nbsp;';//采购金额
	        newTd8.style.textAlign='right';
	        newTd9.innerHTML = ''+formatMoney(arr[8],5)+'&nbsp;';//入库金额
	        newTd9.style.textAlign='right';
			newTd10.innerHTML = ''+formatMoney(arr[9],5)+'&nbsp;';//指定金额
			newTd10.style.textAlign='right';
	        newTd11.innerHTML = ''+formatMoney(arr[10],5)+'&nbsp;';//收票金额
	        newTd11.style.textAlign='right';
			newTd12.innerHTML=''+arr[11]+'&nbsp;';//已退货合同数
			newTd12.style.textAlign='right';
			newTd13.innerHTML=''+formatMoney(arr[12],5)+'&nbsp;';//返厂金额
			newTd13.style.textAlign='right';
	        newTd14.innerHTML = '<input type="text"  style="width:80px;" name="count" id="buyBackCount"  onblur="buyBackCountCheck(this,'+arr[5]+','+arr[11]+',\''+formatMoney(arr[6],5)+'\',\'buyBackMoney'+arr[0]+'\')" id="buyBackCount" value="0"/>&nbsp;';
	        newTd15.innerHTML = '<input type="hidden" name="buyBackMoney" id="buyBackMoney'+arr[0]+'" value="0.00"/><div id="DIVbuyBackMoney'+arr[0]+'">0.00&nbsp;</div>';
	        newTd15.style.textAlign='right';
	        newTd15.style.paddingRight="5px";
	        newTd16.innerHTML = ''+arr[13]+'&nbsp;';//库存总数
	        newTd16.style.textAlign='right';
		}
		$("#tableLength").attr("value", countRow);
		if($("#table")){
					$("#table tr:odd").addClass("treven");
					$("#table tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
	}
	//收票数录入键盘弹起事件
	function buyBackCountCheck(receiveCount,inStockCount,alreadyReceiveCount,buyUnitPricen,rIMoney){
		var buyUnitPrice=rmoney(buyUnitPricen+"");
		var s=parseInt(receiveCount.value,10);
		 var m=""+s;
		 if(receiveCount.value==""){
		 	alert("退货合同数不能为空");
		 	receiveCount.focus();
		 }else if((!isNaN(receiveCount.value))&&(receiveCount.value!=parseInt(receiveCount.value,10)||receiveCount.value.length!=m.length)){
				alert("退货合同数只能为正整数！");
				receiveCount.select();
		}else if(isNaN(receiveCount.value)){
			alert("退货合同数只能为正整数！");
			receiveCount.select();
		}else if((parseInt(receiveCount.value,10)+parseInt(alreadyReceiveCount,10))>inStockCount){
			alert("退货合同数应小于等于采购数减已退货合同数")
			receiveCount.select();
			//receiveCount.value=(inStockCount-alreadyReceiveCount);
			//document.getElementById(rIMoney).value=receiveCount.value*buyUnitPrice;
			//document.getElementById("DIV"+rIMoney).innerHTML=formatMoney(receiveCount.value*buyUnitPrice,2);
			//totleMoneyMethod();
		}else if(receiveCount.value<0){
			alert("退货合同数应大于0")	
			receiveCount.select();
			//receiveCount.value="0";
		}else{
			document.getElementById(rIMoney).value=receiveCount.value*buyUnitPrice;
			document.getElementById("DIV"+rIMoney).innerHTML=formatMoney(receiveCount.value*buyUnitPrice,5);
			totleMoneyMethod();
			focusRemoveCls(receiveCount);
			//receiveCount.focus();//让当前文本框获取焦点
		}
		
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
				changeIndex();
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
		
	
	//计算总金额
	function totleMoneyMethod(){
		//得到所有产品信息
		var tableObj = document.getElementById("prodtbody");
		var tlength = tableObj.rows.length;
		vtotle=0;
		for(var i=1;i<tlength;i++){
		    var price = rmoney(tableObj.rows[i].cells[15].innerText);; //收票金额
			  vtotle=FloatAdd(vtotle,price)
		//	vtotle += parseFloat(price);
		}
		vtotle=vtotle.toFixed(5);
			$("#receiveTotalMoenyHidden").val(vtotle);//给总收票金额隐藏域赋值
	document.getElementById("receiveTotalMoeny").innerHTML=""+formatMoney(vtotle,5);//给总收票金额隐藏域赋值
	}
	//退货合同信息验证
	function buyBackInfoCheck(){
		var tem=document.getElementsByName("bbce.templateType");
		var fileText=document.getElementById("templementFile").value;
		var fileHidden=document.getElementById("fileHidden").value;
		for(var i=0;i<tem.length;i++){
			if(tem[i].checked==true){
				if(tem[i].value=="1"&&fileText==""&&fileHidden==""){
				$("#templementFile").addClass("invalid");
					$("#templementFile")[0].focus();
					alert("请选择文件");
					return false;
				}else if(tem[i].value=="1"&&fileText!=""){
					var ar=["/^+.txt$/","/^+.pdf$/","/^+.xml$/","/^+.rtf$/","/^+.doc$/","/^+.docx$/","/^+.xls$/","/^+.xlsx$/"];
					var b=false;
					uploadFileName=fileText.substring(fileText.lastIndexOf(".")+1,fileText.length).toLowerCase();
					var prec=["txt","pdf","xml","rtf","doc","docx","xls","xlsx"];
					for(var j=0;j<prec.length;j++){
						if(uploadFileName==prec[j]){
							b=true;
							break;
						}
					}
					//for(var j=0;j<ar.length;i++){
					//	alert(ar[j]+"||"+fileText);
						//if(test(ar[j],fileText)){
						//	b=true;
						//	break;
						//}
					//}
					if(!b){
						$("#templementFile").addClass("invalid");
						$("#templementFile")[0].focus();
						$("#fileError_div").html("<font color='red' size='-1'>格式只能为txt、pdf、xml、rtf、doc、docx、xls、xlsx类型！</font>");
						
						return false;
					}else{
						$("#fileError_div").html("");
					}
				}else{
					if(fileText==""&&fileHidden!=""){
						document.getElementById("templementFile").value=fileHidden;
					}
				}
				$("#templementFile").removeClass("invalid");
			}
		}
		return productCheck();
	}
	//产品信息验证
	function productCheck(){
		var tableObj = document.getElementById("prodtbody");
		var tlength = tableObj.rows.length;
		if(tlength<=1){
			alert("请添加产品信息");
			return false;
		}
		for(var i=1;i<tlength;i++){
		    var buyCount = parseInt($(tableObj.rows[i].cells[6]).text()); //采购数
		    var _buyBack=$(tableObj.rows[i].cells[14]).children();//退货合同数
			var backContractCount=parseInt($(tableObj.rows[i].cells[12]).text());//已退货合同数
		    if(_buyBack.val()==""){
		   		 _buyBack.addClass("invalid");
		    	alert("退货合同数不能为空");
		    	return false;
		    }else if(isNaN(_buyBack.val())){//如果输入非整数
		    	_buyBack.addClass("invalid");
		    	alert("退货合同数必须为正整数");
		    	return false;
		    }
			
		    var buyBack=parseInt(_buyBack.val(),10);
		    if(buyBack<=0||buyBack>buyCount){
		    	$(tableObj.rows[i].cells[14]).children().addClass("invalid");
		    	alert("退货合同数大于零且小于等于采购数减已退货合同数");
		    	return false;
		    }
			if(buyCount<buyBack+backContractCount){
				$(tableObj.rows[i].cells[14]).children().addClass("invalid");
				alert("退货合同数不能大于采购数减已退货合同数");
				return false;
			}
		}
		return  backMoneyCheck()
	}
	//退货退款信息验证
	function backMoneyCheck(){
		var bool=false;
		var backMoney=document.getElementsByName("bbce.backWay");
		for(var i=0;i<backMoney.length;i++){
			if(backMoney[i].checked==true){
				bool=true;
			}
		}
		if(!bool){
			alert("请选择退货退款方式");
			return false;
		}
		return deliveryCheck();
	}
	//发货信息验证
	function deliveryCheck(){
		var supplierAddressId=document.getElementById("supplierAddressId");
		var deliveryDate=document.getElementById("deliveryDate");
		
		if(supplierAddressId.value==""){
			alert("请选择发货信息");
			return false;
		}
		if(deliveryDate.value==""){
			alert("请选择发货日期");
			$("#deliveryDate").addClass("invalid");
			return false;
		}else{
			var _year=deliveryDate.value.substring(0,4);
			var _month=deliveryDate.value.substring(5,7);
			var _date=deliveryDate.value.substring(8,10);
			var date=new Date();
			if(_year<date.getFullYear())
			{
				$("#deliveryDate").addClass("invalid");
				alert("日期必须大于今日日期");
				return false;
			}
			if(_month<date.getMonth()+1){
				$("#deliveryDate").addClass("invalid");
				alert("日期必须大于今日日期");
				return false;
			}
			if(_date<=date.getDate()){
				$("#deliveryDate").addClass("invalid");
				alert("日期必须大于今日日期");
				return false;
			}
			}
		$("#deliveryDate").removeClass("invalid");
		return backDescriptionCheck();
	}
	//退货原因说明验证
	function backDescriptionCheck(){
		var description=document.getElementById("textArea");
		var text=$.trim($("#textArea").val());
		if(text==""){
			alert("退货原因说明不能为空!");
			$(description).addClass("invalid");
			return false;
		}
		//用于判读补充说明是否输入过长
		if(checkTextAreaLen(200) == false){
			return ;
		}
		$(description).removeClass("invalid");
		return true;
	}
	//退货合同提交
	function subBuyBackContract(state){
		var buyState=document.getElementById("buyState");
			if(buyBackInfoCheck()){
				buyState.value=state==0?0:1;
				$("#buyBackForm").submit();
				//alert("验证全部通过");
			}
	}
	function focusRemoveCls(obj){
		$(obj).removeClass("invalid");
	}
</script>
</html>
