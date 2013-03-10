<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>供货商选择</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/math.js"></script>
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
				$('#btnJianSuo').click(function(){
					document.forms[0].submit();
				});
				
				//注入提交click事件
				$('#btnChoice').click(
					function(){
						var result = false;
						$('input[name=supplier]').each(function(i){
						 	if ($(this).attr("checked") == true) {
						          result = true;
						          opener.addSupplier($(this).val()); 
								  //选择完毕关闭小页面
								  window.close();
						    }
						});
						if(!result){
						alert('请选择客户！');
						return false;
					}
					}
				);
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
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                <td class="td_03" width="50%">供货商名称</td>
                <td class="td_04">
                <form action="buyPaymentSelectSupplier.do" name="buyPaymentSelectSupplier" id="buyPaymentSelectSupplier"> 
                  <input type="text" name="supplierName" id="supplierName" value="${supplierName}" style="width:240px;" />
                </form>
                </td>
              </tr>
              <tr>
                <td colspan="4" align="center" style="height:30px;"><img id="btnJianSuo" src="${ctx}/images/btn_JianSuo.gif" /></td>
              </tr>
            </table>
          </div>
          <div class="mo_title" onclick="fMainListToggle(this)">
              <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
          </div>
        </div>    </td>
  </tr>
  <tr>
    <td align="center">
    <br/>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
      <tr>
        <th nowrap="nowrap">选择</th>
        <th nowrap="nowrap">供货商名称</th>
        </tr>
		<logic:iterate id="su" name="list">
			<tr>
	        	<td  width="50px" nowrap="nowrap"><input type="radio" name="supplier" id="supplier" value="${su.id}\||//${su.name}\||//${su.province}\||//${su.city}\||//${su.invoiceType}\||//${su.taxRate}\||//${su.remitBankName}\||//${su.remitBankAccount}\||//${su.contractType}" /></td>
		        <td nowrap="nowrap">${su.name}&nbsp;</td>
			</tr>
		</logic:iterate>
    </table>
    <br />
        <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        	<tr>
           	  	<td align="right"><%@ include file="/jsp/common/newPage.jsp"%></td>
          </tr>
        </table>
        <table align="center">
            <tr>
              <td height="45px" colspan="2" align="center" valign="bottom"><img id="btnChoice" src="${ctx}/images/btnChoice.gif" width="65" height="20" /></td>
            </tr>
        </table>    
    </td>
  </tr>
</table>
 
</body>
</html>
