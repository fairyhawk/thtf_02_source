<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;
charset=utf-8"%> 
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>退票申请</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
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
				
				//禁用回车事件，防止提交
				$("body").bind('keyup',function(event) {   
				if(event.keyCode==13){    
				return false;   
				}      
				});  
				//禁用回车事件，防止提交				
			});
		//-->
		</script>
		
<script type="text/javascript">
		function sub(method){	
			
			if ($.trim($("#textarea").val()).length==0){
		 	  alert("请输入特殊说明");
			  return false;
			}
			
			if ($("#textarea").val().length>200){
		 	  alert("特殊说明长度过长");
			  return false;
			}
		
        	$("#method").attr("value", method);
        	
        	if ((method=="save") || (method="submit")){
		   		 $("#backInvoiceForm").submit();
			}else{
			    alert("非法提交！");
			}	
					
	    }

$(function(){


		$("#totalMoney").html(formatNum(fmoney(${totalMakeInvoicePrice},2))+'元');
	
	});

function   formatNum(num)
{   
    if(!/^(\+|-)?(\d+)(\.\d+)?$/.test(num)){alert("金额格式化错误!");   return   num;}   
    var   a   =   RegExp.$1,   b   =   RegExp.$2,   c   =   RegExp.$3;   
    var   re   =   new   RegExp().compile("(\\d)(\\d{3})(,|$)");   
    while(re.test(b))   b   =   b.replace(re,   "$1,$2$3");   
    return   a   +""+   b   +""+   c;   
}

 $(document).ready(function(){
	      var msg = "${msg}";  //获取服务端信息
		if(msg!=""){
		alert(msg);
		}
	 });

//金额合计的格式化s为要格式化的参数（浮点型），n为小数点后保留的位数	
	function fmoney(s,n){
		n = n>0 && n<=20 ? n : 2;
		s = parseFloat((s+"").replace(/[^\d\.-]/g,"")).toFixed(n)+"";
		var l = s.split(".")[0].split("").reverse(), 
		r = s.split(".")[1]; 
		t = "";
		for(i = 0;i<l.length;i++){
			t+=l[i]+((i+1)%3==0 && (i+1) != l.length ? "" : ""); 
		}
		return t.split("").reverse().join("")+"."+r;
	}

</script>
</head>
<body>
<html:form action="modifyBackInvoice.do?sellInvoiceId=${sellInvoiceDto.id}" styleId="backInvoiceForm">
<input type="hidden" id="method" value="" name = "method"/>
<input type="hidden" value="${param.flag}" name="flag" id="flag">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 销售发票管理 &gt;&gt; 退票申请</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;发票信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
				<td class="td_01" width="20%" height="18">开票日期</td>
          		<td class="td_02" width="30%">${sellInvoiceDto.date}&nbsp;</td>
          		<td class="td_01" width="20%">发票号</td>
          		<td class="td_02" width="30%">${sellInvoiceDto.number}&nbsp;</td>
          </tr>
          <tr>
				<td class="td_01" height="18">发票金额</td>
          		<td class="td_02"><fmt:formatNumber value="${sellInvoiceDto.money}" type="number" pattern="#,##0.00" />&nbsp;</td>
          		<td class="td_01">&nbsp;</td>
          		<td class="td_02">&nbsp;</td>
          </tr>
      </table>
   <br/>
	 <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td height="18" class="td_03" width="50%">产品分类名称</td>
                <td class="td_04">${sellInvoiceDto.productTypeName}</td>
            </tr>
   	  </table>
      <div style="width:100%; text-align:right">单位：元</div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
			<tr>
				<th nowrap="nowrap" width="35" style="border-left:1px solid #c2c2c2;">序号</th>
				<th nowrap="nowrap">发货单号</th>
				<th nowrap="nowrap">产品合同号</th>
				<th nowrap="nowrap">公司合同号</th>
				<th nowrap="nowrap">产品编码</th>
				<th nowrap="nowrap">产品名称</th>
				<th nowrap="nowrap">规格型号</th>
				<th nowrap="nowrap">单位</th>
				<th nowrap="nowrap">销售单价</th>
				<th nowrap="nowrap">开票数</th>
				<th nowrap="nowrap">开票金额</th>
				
			</tr>
			<logic:present name="sellInvoiceDetailList">
				<logic:iterate id="sellInvoiceDetail" name="sellInvoiceDetailList" indexId="indexId">
			<tr>
				<td nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18px">&nbsp;${indexId+1}</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.sendGoodsId}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.productContractCode}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.companyContractCode}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.productCode}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.productName}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.productType}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.productUnit}&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; width:93px;"><fmt:formatNumber value="${sellInvoiceDetail.price}" type="number" pattern="#,##0.00" />&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; width:93px;" >${sellInvoiceDetail.count}&nbsp;</td>
				<td nowrap="nowrap" style=" text-align:right; padding-right:12px; width:93px;"><fmt:formatNumber value="${sellInvoiceDetail.makeInvoicePrice}" type="number" pattern="#,##0.00" />&nbsp;</td>				
			</tr>		
				</logic:iterate>
			</logic:present>
		</table>
		<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">			
			<tr>
			<td align="center" >&nbsp;</td>
         	<td style=" text-align:right;width:96px;padding-top:5px;">退票金额合计</td>
        	<td style=" text-align:right;width:100px;padding-top:5px;" id="totalMoney"></td>
			</tr>
		</table>
   	 <br/>
			<div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">特殊说明</td>
					<td class="td_04" ><textarea name="textarea" id="textarea"  cols="100" rows="4">${sellInvoiceDto.text}</textarea>
					</td>
				</tr>
			</table>
			<br/>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    <img src="${ctx}/images/btnSave.gif" width="65" height="20" onclick="sub('save')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="${ctx}/images/btnSubmit.gif" width="65" height="20" onclick="sub('submit')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    <img src="${ctx}/images/btnBack.gif" onclick="javascript:window.location='${ctx}/getBackInvoiceByUserId.do' "/>
    </td>
    <td></td>
  </tr>
</table>
</html:form>
</body>
</html>
