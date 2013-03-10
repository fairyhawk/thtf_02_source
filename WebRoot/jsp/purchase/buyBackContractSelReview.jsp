<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<c:if test="${requestScope.print}">
<OBJECT id="wb" height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name="wb"></OBJECT>
</c:if>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>查看采购退货合同评审表</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>

	    <style type="text/css"> 
		<!--
			body{font-family:"宋体"; font-size:13px; font-weight:bold;}
		.tad_1{border-left:1px solid #000000; border-bottom:1px solid #000000; }
		.tad_1 td{border-right:1px solid #000000;border-top:1px solid #000000;color:#000000;white-space:nowrap; padding:3px 0px 3px 0px;}
		.tad td{padding-bottom:5px;}
		.tad_2{ border-bottom:1px solid #000000; }
		.STYLE1 {
			font-size: 24px;
			font-weight: bold;
		} 
  		@media   print   {   
  		.buttonNoPrint {display:none;}   
  		}   
		-->
		</style>
		<style type="text/css">
		<!--
			.STYLE1 {color: #FF0000}
		-->
		</style>
	</head>
<script type="text/javascript">
function reviewPrint(userRoleId,buyBackContractId,img){
	//打印后按钮变灰 不可用
	img.style.filter=" Alpha(opacity=50);-moz-opacity:.1;opacity:0.1";
			
  	img.disabled="true";
	var status=document.getElementById("status").value;
		if(userRoleId==8&&status==8){//如果当前用户的角色为8(采购专员)并且和他状态为8(待打印)
			$.getJSON("${ctx}/upBuyBackContractStatus.do?status="+9+"&buyBackContractId="+buyBackContractId, function(data){
				if(data==false){
			  	  	alert("更新状态失败");
			  	  	
				}else{
					document.getElementById("status").value=9;
					window.opener.opener=null;
					window.opener.location.reload();//刷新父窗体 
					$("#DivPrint").hide();
					wb.execwb(6,6);  
					$("#DivPrint").show();
					
				}
				});
		}else{
			$("#DivPrint").hide();
			wb.execwb(6,6);  
			$("#DivPrint").show();
		}
		}
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
</script>
<body><br/>
<table border="0" cellpadding="0" cellspacing="0" align="center" width="98%" class="tad"><tr><td colspan="2" align="center" height="30"><table border="0" cellpadding="0" cellspacing="0" align="center" width="98%" class="tad">
	<tr>
		<td height="40" colspan="2" valign="top"><table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center" class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;同方股份有限公司</td>
				<td width="100">JL-CP-001</td>
			</tr>
			<tr>
				<td colspan="2" align="center" class="STYLE2" height="25px">采购退货合同评审表</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table width="100%" cellpadding="0" cellspacing="0" class="tad_1">
			<tr>
				<td width="10%" align="center">原采购产品合同号</td>
				<td width="40%" align="left">${reviewDto.buyProdContractCode}</td>
				<td width="10%" align="center">原采购合同金额（元）</td>
				<td width="40%" align="left">
					<fmt:formatNumber value="${reviewDto.buyContractMoney}" pattern="#,##0.00000"/>
				</td>
			</tr>
			<tr>
				<td align="center">部门名称</td>
				<td align="left">${reviewDto.deptName}</td>
				<td align="center">产品合同号</td>
				<td align="left">${reviewDto.buyBackProdContractCode}</td>
			</tr>
			<tr>
				<td align="center">供货商名称</td>
				<td align="left">${reviewDto.supplierName}</td>
				<td align="center">公司合同号</td>
				<td align="left">${reviewDto.compContractCode}</td>
			</tr>
			<tr>
				<td align="center">合同类型</td>
				<td align="left">
					<c:if test="${reviewDto.contractType==0}">
						国内
					</c:if>	
					<c:if test="${reviewDto.contractType==1}">
						国外
					</c:if>
				</td>
				<td align="center">模板类型</td>
				<td align="left">
					<c:if test="${reviewDto.templateType==0}">
						模板
					</c:if>
					<c:if test="${reviewDto.templateType==1}">
						非模板
					</c:if>
				</td>
			</tr>
			<tr>
				<td align="center">产品分类</td>
				<td align="left">${reviewDto.prodTypeName}</td>
				<td align="center">产品序列号</td>
				<td align="left">${reviewDto.prodSerialNumber}</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td width="50%">&nbsp;</td>
		<td align="right">单位：元</td>
	</tr>
	<tr>
		<td colspan="2"><table width="100%" cellpadding="0" cellspacing="0" class="tad_1">
			<tr>
				<td align="center"><strong>序号</strong></td>
				<td align="center">产品名称</td>
				<td align="center"><strong>规格型号</strong></td>
				<td align="center" width="40"><strong>单位</strong></td>
				<td align="center" width="40"><strong>数量</strong></td>
				<td align="center" width="96">采购单价</td>
				<td align="center" width="96">退货金额</td>
				<td align="center" width="96"><strong>原采购数量</strong></td>
				<td align="center" width="96"><strong>库存总数</strong></td>
			</tr>
			<bean:define id="total" value="0" type="java.lang.String" />
		<logic:iterate id="prod" name="prods" indexId="prodindex">
		<bean:define id="total" value="${total+prod.money}" type="java.lang.String" />
			<tr>
				<td style="border-left:1px solid #c2c2c2;">${prodindex+1}</td>
				<td align="left">&nbsp;${prod.prodName}</td>
				<td align="left">&nbsp;${prod.prodType}</td>
				<td align="left">&nbsp;${prod.prodUnit}</td>
				<td align="right">${prod.backContractProdCount}&nbsp;</td>
				<td align="right"><fmt:formatNumber value="${prod.buyContractPrice}" pattern="#,##0.00000"/>&nbsp;</td>
				<td align="right"><fmt:formatNumber value="${prod.money}" pattern="#,##0.00000"/>&nbsp;</td>
				<td align="right">${prod.buyContractCount}&nbsp;</td>
				<td align="right">${prod.prodCount}&nbsp;</td>
			</tr>
		</logic:iterate>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
				<td align="right">&nbsp;</td>
				<td align="center">总计</td>
				<td align="right"><fmt:formatNumber value="${total}" pattern="#,##0.00000"/>&nbsp;</td>
				<td align="center">&nbsp;</td>
				<td align="right">&nbsp;</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%" class="tad_2">
			<tr>
				<td colspan="3" style="line-height:22px"  align="left">部门送审人送审说明</td>
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px" align="left">发票类型：
				<c:if test="${reviewDto.invoiceType==0}">
					普通
				</c:if>
				<c:if test="${reviewDto.invoiceType==1}">
					增值税
				</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px" align="left">付款方式：
					<c:if test="${reviewDto.backWay==1}">先退货后退款</c:if> 
		            <c:if test="${reviewDto.backWay==2}">先退款后退货</c:if> 
					<c:if test="${reviewDto.backWay==3}">已供货未付款</c:if>
				    <c:if test="${reviewDto.backWay==4}">未供货已付款</c:if>
					<c:if test="${reviewDto.backWay==5}">未供货未付款</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px" align="left">要求发货日期：${reviewDto.requestSendDate}</td>
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="100" valign="top">重要事项说明：</td>
						<td align="left">${tf:replaceBr(reviewDto.text)}</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="line-height:22px" width="50%" align="left">&nbsp;</td>
				<td style="line-height:22px" width="25%" align="left">送审人签字：${reviewDto.userName}</td>
				<td style="line-height:22px" width="25%" align="left">日期：${reviewDto.dateTime}</td>
			</tr>
		</table></td>
	</tr>
	<c:if test="${reviewDto.templateType==1}">
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%" class="tad_2">
			<tr>
				<td colspan="3" style="line-height:22px" align="left">法务专员评审意见</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">法律法规是否符合</td>
				<td colspan="2" align="left"><input type="checkbox" onclick="return false"  <c:if test="${reviewDto.legalIdea==1}" >checked="checked"</c:if>  name="checkbox5" id="checkbox4" />
					符合
					&nbsp;&nbsp;
											<input type="checkbox" onclick="return false"  <c:if test="${reviewDto.legalIdea==0}" >checked="checked"</c:if>  name="checkbox5" id="checkbox4" />
					不符合</td>
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="70" valign="top">补充说明：</td>
						<td align="left">${tf:replaceBr(reviewDto.legalText)}</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="line-height:22px" width="50%" align="left">&nbsp;</td>
				<td style="line-height:22px" width="25%" align="left">签字：${reviewDto.legalName}</td>
				<td style="line-height:22px" width="25%" align="left">日期：${reviewDto.legalDate}</td>
			</tr>
		</table></td>
	</tr>
	</c:if>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%" class="tad_2">
			<tr>
				<td colspan="3" style="line-height:22px" align="left">采购主管评审意见</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">1.退货数量是否符合</td>
				<td colspan="2" align="left"><input type="checkbox" onclick="return false"  <c:if test="${reviewDto.buyManProductIdea==1}" >checked="checked"</c:if>  name="checkbox6" id="checkbox5" />
					符合
					&nbsp;&nbsp;
											<input type="checkbox" onclick="return false"  <c:if test="${reviewDto.buyManProductIdea==0}" >checked="checked"</c:if>  name="checkbox6" id="checkbox5" />
					不符合</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">2.退货退款方式是否符合</td>
				<td colspan="2" align="left"><input type="checkbox" onclick="return false"  <c:if test="${reviewDto.buyManPaymentTypeIdea==1}" >checked="checked"</c:if>  name="checkbox6" id="checkbox6" />
					符合
					&nbsp;&nbsp;
											<input type="checkbox" onclick="return false"  <c:if test="${reviewDto.buyManPaymentTypeIdea==0}" >checked="checked"</c:if>  name="checkbox6" id="checkbox6" />
					不符合</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">3.退货地址是否符合</td>
				<td colspan="2" align="left"><input type="checkbox" onclick="return false"  <c:if test="${reviewDto.buyManBeforeserviceIdea==1}" >checked="checked"</c:if>  name="checkbox7" id="checkbox8" />
					符合
					&nbsp;&nbsp;
											<input type="checkbox" onclick="return false"  <c:if test="${reviewDto.buyManBeforeserviceIdea==0}" >checked="checked"</c:if>  name="checkbox7" id="checkbox8" />
					不符合</td>
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="70" valign="top"  align="left">补充说明：</td>
						<td  align="left">${tf:replaceBr(reviewDto.buyManText)}</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="line-height:22px" width="50%">&nbsp;</td>
				<td style="line-height:22px" width="25%" align="left">签字：${reviewDto.buyManName}</td>
				<td style="line-height:22px" width="25%" align="left">日期：${reviewDto.buyManDate}</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%" class="tad_2">
			<tr>
				<td colspan="3" style="line-height:22px" align="left">运营总监评审意见</td>
			</tr>
			<tr>
				<td style="line-height:22px">&nbsp;</td>
				<td colspan="2" align="left"><input type="checkbox" <c:if test="${reviewDto.opeMajIdea==1}" >checked="checked"</c:if>  onclick="return false"  name="checkbox8" id="checkbox9" />
					同意
					&nbsp;&nbsp;
											<input type="checkbox" <c:if test="${reviewDto.opeMajIdea==0}" >checked="checked"</c:if> onclick="return false" name="checkbox8" id="checkbox9" />
					不同意</td>
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="70" valign="top" align="left">补充说明：</td>
						<td align="left">${tf:replaceBr(reviewDto.opeMajText)}</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="line-height:22px" width="50%" align="left">&nbsp;</td>
				<td style="line-height:22px" width="25%" align="left">签字：${reviewDto.opeMajName}</td>
				<td style="line-height:22px" width="25%" align="left">日期：${reviewDto.opeMajDate}</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%" class="tad_2">
			<tr>
				<td colspan="3" style="line-height:22px" align="left">公司授权领导最终审批意见</td>
			</tr>
			<tr>
				<td style="line-height:22px">&nbsp;</td>
				<td colspan="2" align="left"><input type="checkbox"  onclick="return false"  name="checkbox9" id="checkbox10" />
					同意
					&nbsp;&nbsp;
											<input type="checkbox"  onclick="return false"   name="checkbox9" id="checkbox10" />
					不同意</td>
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="70" valign="top" align="left">补充说明：</td>
						<td align="left">&nbsp;</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="line-height:22px" width="50%" align="left">&nbsp;</td>
				<td style="line-height:22px" width="25%" align="left">签字：</td>
				<td style="line-height:22px" width="25%" align="left">日期：</td>
			</tr>
		</table></td>
	</tr>
</table>
<c:if test="${requestScope.print}">
		<div style="text-align:center;" id="DivPrint">
			<input type="hidden" id="status" value="${reviewDto.status}"/>
		 <!--  <a href="javascript:reviewPrint('${USERLOGIN.roleId}','${reviewDto.buyBackContractId}')">  <img src="/cms/images/btnPrint.gif" width="65" height="20"/></a> -->
		  <img src="/cms/images/btnPrint.gif" width="65" height="20" onClick="reviewPrint('${USERLOGIN.roleId}','${reviewDto.buyBackContractId}',this)"/>
		 &nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="${ctx}/images/btnPrintSZ.gif" width="88" height="20" class="buttonNoPrint" onClick="javascript:printSetup();"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="${ctx}/images/btnPrintYL.gif" class="buttonNoPrint" onClick="javascript:printPreview();" />
		</div>
</c:if>
</body>

</html>
