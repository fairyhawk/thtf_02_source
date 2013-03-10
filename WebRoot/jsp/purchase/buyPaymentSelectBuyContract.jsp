<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购合同选择</title>
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
-->
</style>
<script language="JavaScript"> 
		<!--
			$(document).ready(function(){
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
				//选择提交
				$('#submit').click(function(){   
				 	var pcArray = new Array();
				 	$('#buyContract input:checked').each(function(){
					 	pcArray.push($(this).val());
				 	});  
				 	//验证
				 	if(pcArray.length>0){
						$('#buyContract :checkbox').attr("checked", false);
						opener.addProductC(pcArray); 
						//选择完毕关闭小页面
						window.close();
				 	}else{ 
						alert("未选择任何采购合同");
					}
				});
				//检索提交
				$('#btnJianSuo').click(function(){
					document.forms[0].submit();
				});
				
				$('input:checkbox').click(function(){   
					if($(this).attr('checked')){ 
						if(opener.checkbuyC($(this).val())){
							alert("此合同已存在");
							$(this).attr('checked',false);
						} 
					}
				});  
			});
		//-->
		</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
</head>
<body>
<table width="96%" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
    <td align="center"><br />
      <div class="mo_wp">
        <div style="display: ; " class="mo_con" >
        <form action="buyPaymentSelectBuyContract.do" name="buyPaymentSelectBuyContract" id="buyPaymentSelectBuyContract" method="post">
        	<input type="hidden" id="supplierId" name="supplierId" value="${supplierId}">
        	<input type="hidden" id="productTypeId" name="productTypeId" value="${productTypeId}">
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td class="td_01" width="20%">公司合同号</td>
              <td class="td_02" width="30%"><input type="text" name="companyContractCode" id="companyContractCode" value="${formBuy.companyContractCode}" style="width:120px;" /></td>
              <td class="td_01" width="20%">产品合同号</td>
              <td class="td_02" width="30%"><input type="text" name="productContractCode" id="productContractCode" value="${formBuy.productContractCode}" style="width:120px;" /></td>
            </tr>
            <tr>
              <td colspan="4" align="center" style="height:30px;"><img href="#" id="btnJianSuo" src="${ctx}/images/btn_JianSuo.gif" /></td>
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
          <th nowrap="nowrap">产品合同号</th>
          <th nowrap="nowrap">公司合同号</th>
          <th nowrap="nowrap">合同金额</th>
          <th nowrap="nowrap">入库金额</th>
          <th nowrap="nowrap">指定金额</th>
          <th nowrap="nowrap">预付金额</th>
          <th width="84" nowrap="nowrap">收票金额</th>
          <th width="84" nowrap="nowrap">退货合同金额</th>
          <th width="84" nowrap="nowrap">返厂金额</th>
        </tr>
        <tbody id="buyContract">
        <logic:iterate id="bc" name="list" >
	        <tr>
	          <td width="30" nowrap="nowrap"><input type="checkbox" name="buyContract" id="buyContract" value="${bc.id}\||//${bc.productContractCode}\||//${bc.companyContractCode}\||//${bc.contractMoney}\||//${bc.instockMoney}\||//${bc.appointMoney}\||//${bc.advanceMoney}\||//${bc.invoiceMoney}\||//${bc.backContractMoney}\||//${bc.backGoodsMoney}" /></td>
	          <td nowrap="nowrap" >${bc.productContractCode}&nbsp;</td>
	          <td nowrap="nowrap" >${bc.companyContractCode}&nbsp;</td>
	          <td nowrap="nowrap" style=" text-align:right;" ><fmt:formatNumber value="${bc.contractMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
	          <td nowrap="nowrap" style=" text-align:right;" ><fmt:formatNumber value="${bc.instockMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
	          <td nowrap="nowrap" style=" text-align:right;" ><fmt:formatNumber value="${bc.appointMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
	          <td nowrap="nowrap" style=" text-align:right;" ><fmt:formatNumber value="${bc.advanceMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
	          <td nowrap="nowrap" style=" text-align:right;" ><fmt:formatNumber value="${bc.invoiceMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
	          <td nowrap="nowrap" style=" text-align:right;" ><fmt:formatNumber value="${bc.backContractMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
	          <td nowrap="nowrap" style=" text-align:right;" ><fmt:formatNumber value="${bc.backGoodsMoney}" pattern="#,##0.00000#"/>&nbsp;</td>
	        </tr>
     	</logic:iterate>
        <tbody>
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
</body>
</html>
