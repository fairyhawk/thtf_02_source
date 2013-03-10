<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购合同附件打印</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<style type="text/css"> 
.treven {
			background-color: #f3fbff;
		}
.over {
	background-color: #ECECEC;
}
table tr{
	height:18px;
}
.STYLE1 {
	color: #FF0000
}	
</style>
</head>
<script>
function printPreview(){ 
	 $("#DivPrint").hide();   
		// 打印页面预览    
		wb.execwb(7,1);
		$("#DivPrint").show();       
	}    
	function printSetup(){    
		// 打印页面设置    
		wb.execwb(8,1);    
	}
	$(document).ready(function(){
		$("#print").click(function(){
			$("#DivPrint").hide();   
			wb.execwb(6,6);
			$("#DivPrint").show();
		});
		  
		var jsonDate=${requestScope.jsonDate};
		for(var i=0;i<jsonDate.resultSum;i++){
			var infoDate=jsonDate.rows[i];
			var tableId="receiveAddressOfTable"+infoDate.companyType+infoDate.addressId+infoDate.companyId;

			$("#info").append("<div style='line-height:20px;font-size:14px'><strong>发货地址"+(i+1)+":</strong></div><table width='100%' border='0' cellpadding='0' cellspacing='0' class='biao3' align='center' id='"+tableId+"'></table>");
			$("#"+tableId).append("<tr><td class='td_01'>名称</td><td colspan='3' class='td_02'>"+jsonDate.rows[i].receiveName+"&nbsp;&nbsp;</td></tr>");
			$("#"+tableId).append("<tr><td class='td_01'>货物接收单位名称</td><td colspan='3' class='td_02'>"+jsonDate.rows[i].goodsName+"&nbsp;</td></tr>");
			$("#"+tableId).append("<tr><td class='td_01'>发货地址</td><td colspan='3' class='td_02'>"+jsonDate.rows[i].address+"&nbsp;</td></tr>");
			$("#"+tableId).append("<tr><td class='td_01' >联系人</td><td class='td_02'>"+jsonDate.rows[i].linkman+"&nbsp;</td><td class='td_01'>邮编</td><td class='td_02'>"+jsonDate.rows[i].postcode+"&nbsp;</td></tr>");
			$("#"+tableId).append("<tr><td class='td_01'>电话</td><td class='td_02'>"+jsonDate.rows[i].tel+"&nbsp;</td><td class='td_01'>手机</td><td class='td_02'>"+jsonDate.rows[i].mobile+"&nbsp;</td></tr>");
  		$("#info").append("<div style='text-align:right;width:90%'></div><div id='div"+tableId+"list'><table width='100%' border='0' cellpadding='0' cellspacing='0' class='biao1' id='"+tableId+"list'><tr><th nowrap='nowrap'>序号</th><th nowrap='nowrap'>产品编码</th><th nowrap='nowrap'>产品名称</th><th nowrap='nowrap'>规格型号</th><th nowrap='nowrap'>单位</th><th nowrap='nowrap'>采购数</th><th nowrap='nowrap'>收货数</th></tr></table></div><br/><br/>");
  		for(var j=0;j<jsonDate.rows[i].resultSum;j++){
  			$("#"+tableId+"list").append("<tr><td style='padding-right:5px;text-align:right'>"+(j+1)+"</td><td>"+jsonDate.rows[i].rows[j].prodCode+"&nbsp;</td><td>"+jsonDate.rows[i].rows[j].prodName+"&nbsp;</td><td>"+jsonDate.rows[i].rows[j].prodType+"&nbsp;</td><td>"+jsonDate.rows[i].rows[j].prodUnit+"&nbsp;</td><td style='padding-right:5px;text-align:right'>"+jsonDate.rows[i].rows[j].buyCount+"&nbsp;</td><td style='padding-right:5px;text-align:right'>"+jsonDate.rows[i].rows[j].receiveCount+"&nbsp;</td></tr>");
  		}
  		if($("#"+tableId+"list")){
				$("#"+tableId+"list tr:even").addClass("treven");
				$("#"+tableId+"list tr").each(function(i){
					$(this).mouseover(function(){
						$(this).addClass("over");
					});
					$(this).mouseout(function(){
						$(this).removeClass("over");
					});
				});
			}
		}
		$("#receiveAddressOfa").click(function(){
			window.open("${ctx}/goReceiveDetailAddSub.do?buyContractId="+$("#buyContractId").val()+"",'newprodwindow', 'toolbar=no,scrollbars=yes,resizable=no,top=200,left=370, width=700,height=500');
		});
		$("#backOfA").click(function(){
			$("#backOfA").attr("href","${ctx}/modifyBuyContractOfShow.do?id="+$("#buyContractId").val()+"");
		})
	});

</script>
<body>

<OBJECT id="wb" height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name="wb"></OBJECT>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<tr>
    <td colspan="2" style="font-size:18px;padding-left:12px;padding-top:10px" align="left"><strong>采购合同附件:</strong></td>
    
  </tr>

  <tr>
    <td colspan="2" style="font-size:12px;padding-left:14px;padding-top:3px"><div style="float:left;width:350px;">产品合同编号：${productContractCode }</div><div>公司合同编号：${companyContractCode}</div></td>
    
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td>
      
      <div id="info"></div>
      <span id="lastDom"></span>
	</td>
    <td >&nbsp;</td>
  </tr>
  <tr id="DivPrint">
    <td></td>
    <td height="50px" align="center" valign="bottom">
    		
		    <a href="#" id="print"><img src="/cms/images/btnPrint.gif" width="65" height="20" class="buttonNoPrint" /></a>&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="${ctx}/images/btnPrintSZ.gif" width="88" height="20" class="buttonNoPrint" onClick="javascript:printSetup();"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!-- <img src="${ctx}/images/btnPrintYL.gif" class="buttonNoPrint" onClick="javascript:printPreview();" /> -->
			 <img src="${ctx}/images/btnBack.gif" class="buttonNoPrint" onClick="history.back()" />
    	</td>
    <td></td>
  </tr>
</table>
<br />
</body>

</html>