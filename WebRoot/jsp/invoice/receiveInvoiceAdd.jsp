<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购发票录入</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
        <script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/contractUtil.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
<style type="text/css">
<!--
.STYLE1 {color: #FF0000}
-->
</style>
<script type="text/javascript">
	$(document).ready(function(){
	  jQuery.validator.addMethod("valMoney", function(value, element) {
        	var mon=/^[0-9]{1,8}(.[0-9]{0,5})?$/; 
        	return mon.test(value);
        });
      jQuery.validator.addMethod("taxCheck",function(value,element){
      		if($("#invoiceType")[0].selectedIndex==2){
	   			if($("#taxRate").val()==""){
	   			return false;
	   			}else{
	   				return true;
	   			}
	   		}else{
	   			return true;
	   		}
      })  
      
		$("#frmInvoice").validate({
				rules: {
						"prodTypeId": "required",					
						"receiveDate": "required",
						"invoiceType": "required",	
						"invoiceNo":"required",
						"receiveMoney": {required:true,number:true,min:1,valMoney:true,max:99999999.99999},			
						"taxRate": {number:true,min:1,taxCheck:true}					
					},
				messages: {
						"receiveDate": {required:"必填字段"},
						"invoiceNo": {required:"必填字段"},
						"receiveMoney": {required:"必填字段",number:"",valMoney:"最多只能五位小数",max:"输入的值不能大于99999999.99999"},			
						"taxRate": {taxCheck:"必填字段"}	
				}
		});	
      

		var msg = "${msg}";  //获取服务端信息
		
		if(msg!=""){
			if($("#invoiceType")[0].selectedIndex==1){
			$("#sl_tr").hide();
			$("#taxRate").val("");
		}else{
			$("#sl_tr").show();
		}
			alert(msg);
		}
	$("#invoiceType").change(function(){
		if($("#invoiceType")[0].selectedIndex==1){
			$("#sl_tr").hide();
			$("#taxRate").val("");
		}else{
			$("#sl_tr").show();
		}
	});	
    });
			
	//进入供应商页面
	function selectSuppliers(){
		window.open('${ctx}/getSuppliersList.do?','newwindow', popWindows(500));
	};
	
	//提交
	function onSubmit(){

	    if($("#frmInvoice").validate().form()==false){
	    	return;
	    }

		if($("#supplierId").val()==""){
			alert("请选择供货商");
			return;
		}

		if(checkTextAreaLen(200) == false){
			return ;
		}
	   
        $('#frmInvoice').submit();	    
	}

</script>
</head>
<html:form action="addReceiveInvoice.do"  method="post" styleId="frmInvoice">
<body> 
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" /> &nbsp;当前位置： 采购管理 &gt;&gt; 采购发票管理 &gt;&gt; 采购发票录入</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                <td class="td_01" width="20%"><span class="STYLE1">*</span>&nbsp;产品分类名称</td>
                <td class="td_02" width="30%">
					<html:select property="prodTypeId" style=" width:126px" value="${invoiceForm.prodTypeId}">
						<html:option value="">--请选择--</html:option> 
						<html:options collection="productTypeList" property="id" labelProperty="name"/>
					</html:select>
                </td>
				<input type="hidden" id="supplierId" name="supplierId" value="${invoiceForm.supplierId}"/>
				<input type="hidden" id="supplierName" name="supplierName" value="${invoiceForm.supplierName}"/>
                <td class="td_01" width="20%"><a href="#" onclick="selectSuppliers();"><img src="${ctx}/images/btnGHSXZ.gif" width="89" height="20" /></a></td>
                <td class="td_02" width="30%" id="td_supplierName">${invoiceForm.supplierName}&nbsp;</td>
              </tr>
              <tr>
                <td class="td_01"><span class="STYLE1">*</span>&nbsp;收票日期</td>
                <td class="td_02">
                    <input type="text" name="receiveDate" id="receiveDate" style="width:126px;" onfocus="calendar();" readonly="readonly" value="${invoiceForm.receiveDate}"/>
                </td>
                <td class="td_01"><span class="STYLE1">*</span>&nbsp;发票金额（元）</td>
                <td class="td_02"><input type="text" name="receiveMoney" id="receiveMoney" maxLength="16" style="width:120px;"value="${invoiceForm.receiveMoney}" /></td>
              </tr>
              <tr>
                <td class="td_01"><span class="STYLE1">*</span>&nbsp;发票类型</td>
                <td class="td_02">
					<html:select property="invoiceType" styleId="invoiceType" style=" width:126px" value="${invoiceForm.invoiceType}">
						<html:option value="">--请选择--</html:option>  
						<html:option value="0">普通</html:option>
						<html:option value="1">增值税</html:option> 
					</html:select>				
				</td>
                <td class="td_01"><span class="STYLE1">*</span>&nbsp;发票号</td>
                <td class="td_02"><input type="text" name="invoiceNo" id="invoiceNo" maxLength="20" style="width:120px;" value="${invoiceForm.invoiceNo}"/></td>
              </tr>
              <tr id="sl_tr">
                <td class="td_01"><span class="STYLE1">*</span>&nbsp;税率</td>
                <td class="td_02"><input type="text" name="taxRate" maxLength="2" id="taxRate" style="width:120px;" value="${invoiceForm.taxRate}"/>
                  %</td>
                <td class="td_01">&nbsp;</td>
                <td class="td_02">&nbsp;</td>
              </tr>
              <tr>
              	<td class="td_01">特殊说明</td>
              	<td colspan="3" class="td_02"><textarea name="text" id="text" cols="100" rows="4">${invoiceForm.text}</textarea></td>
              	</tr>
     </table>		</td>
    <td>&nbsp;</td>
  </tr>
   <tr>
   	<td >&nbsp;</td>
   	<td  height="40px" align="center" valign="bottom">
	<img src="${ctx}/images/btnSubmit.gif" onclick="onSubmit();"  width="65" height="20" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="${ctx}/getReceiveInvoiceList.do"><img src="${ctx}/images/btnBack.gif" width="65" height="20" /></a></td>
   	<td>&nbsp;</td>
   	</tr>
</table>
</html:form> 
</body>
</html>
