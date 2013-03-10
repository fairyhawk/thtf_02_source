<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售退款打印</title>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
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
.STYLE2 {font-size: 14px}
-->
</style>

<script type="text/javascript">
	
		function printPreview(){    
			// 打印页面预览    
			wb.execwb(7,1);       
		}    
		function printSetup(){    
			// 打印页面设置    
			wb.execwb(8,1);    
		}

		function  printIt(img){
		if (confirm('确定打印吗？')) { 
			//打印后按钮变灰 不可用
			img.style.filter=" Alpha(opacity=50);-moz-opacity:.1;opacity:0.1";
			
  			img.disabled="true";
		
			var html=$.ajax({
				url:'printSellBack.do?sellBackId='+$("#id").val()+'&status='+$("#status").val(),
				success:function(html){
					window.opener.location.href='getSellBackList.do';  
					wb.execwb(6,6); 
				},
				error: function() {
					wb.execwb(6,6);
            		alert('错误：未能修改退款状态！');
       			 }
			});
		}
		} 
		
		
		
		$(document).ready(function(){

				$(":checkbox").click(function(){
					return false;
				})
		});
	
	
	
</script>

</head>

<body><br/>
<OBJECT id="wb" height=0 width=0 classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 name="wb"></OBJECT>
<input type="hidden" id="id" value="${assessDto.id}">
<input type="hidden" id="status" value="${assessDto.status}">
<table border="0" cellpadding="0" cellspacing="0" align="center" width="98%" class="tad"><tr><td colspan="2" align="center" height="30"><table border="0" cellpadding="0" cellspacing="0" align="center" width="98%" class="tad">
	<tr>
		<td height="40" colspan="2" valign="top"><table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center" class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;同方股份有限公司</td>
				<td width="100">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center" class="STYLE2" height="25px">资金支付审批表（产品）</td>
			</tr>
		</table></td>
	</tr>
    <tr>
		<td colspan="2"><table width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<td width="13%" align="left">部门名称：${productDeptName}&nbsp;</td>
				<td width="13%" align="left">申请人：${assessDto.userName}&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="left">申请时间：${assessDto.date}&nbsp; </td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table width="100%" cellpadding="0" cellspacing="0" class="tad_1">
			<tr>
			  <td colspan="4" align="left" bgcolor="#E0E0E0">客户信息</td>
		    </tr>
			<tr>
				<td width="13%" align="left">客户名称</td>
				<td width="37%" align="left">${assessDto.customerName}&nbsp;</td>
				<td width="13%" align="left">联系人</td>
				<td width="37%" align="left">${assessDto.name}&nbsp;</td>
			</tr>
			<tr>
				<td align="left">省份</td>
				<td align="left">${assessDto.province}&nbsp;</td>
				<td align="left">电话</td>
				<td align="left">${assessDto.tel}&nbsp;</td>
			</tr>
			<tr>
				<td align="left">城市</td>
				<td align="left">${assessDto.city}&nbsp;</td>
				<td align="left">传真</td>
				<td align="left">${assessDto.fax}&nbsp;</td>
			</tr>
			<tr>
				<td align="left">汇款银行名称</td>
				<td align="left">${assessDto.remitBankName}&nbsp;</td>
				<td align="left">汇款银行账号</td>
				<td align="left">${assessDto.remitBankAccount}&nbsp;</td>
			</tr>
		</table>
		  <table width="100%" cellpadding="0" cellspacing="0" class="tad_1">
            <tr>
              <td colspan="4" align="left" bgcolor="#E0E0E0">回款信息</td>
            </tr>
            <tr>
              <td width="13%" align="left">回款金额</td>
              <td width="37%" align="left"><div style="float:left"><fmt:formatNumber value="${sellBackDto.mreturnMoney}" pattern="#,##0.00"/>&nbsp;</div><div >元&nbsp;</div></td>
              <td width="13%" align="left">指定金额</td>
              <td width="37%" align="left"><div style="float:left"><fmt:formatNumber value="${sellBackDto.detailMoney}" pattern="#,##0.00"/>&nbsp;</div><div>元&nbsp;</div></td>
            </tr>
            <tr>
              <td align="left">合同预收金额</td>
              <td align="left"><div style="float:left"><fmt:formatNumber value="${sellBackDto.contMoney}" pattern="#,##0.00"/>&nbsp;</div><div >元&nbsp;</div></td>
              <td align="left">产品分类预收金额</td>
              <td align="left"><div style="float:left"><fmt:formatNumber value="${sellBackDto.prodMoney}" pattern="#,##0.00"/>&nbsp;</div><div >元&nbsp;</div></td>
            </tr>
            <tr>
              <td align="left">已退款金额</td>
              <td align="left"><div style="float:left"><fmt:formatNumber value="${sellBackDto.backSuccessmoney}" pattern="#,##0.00"/>&nbsp;</div><div >元&nbsp;</div></td>
              <td align="left">回款可退款金额</td>
              <td align="left"><div style="float:left"><fmt:formatNumber value="${sellBackDto.mreturnCanBackMoney}" pattern="#,##0.00"/>&nbsp;</div><div >元&nbsp;</div></td>
            </tr>
          </table>
		  <table width="100%" cellpadding="0" cellspacing="0" class="tad_1">
            <tr>
              <td colspan="4" align="left" bgcolor="#E0E0E0">产品分类信用信息</td>
            </tr>
            <tr>
              <td width="13%" align="left">产品分类预收金额合计</td>
              <td width="37%" align="left"><div style="float:left"><fmt:formatNumber value="${sellBackDto.prodMoneySum}" pattern="#,##0.00"/>&nbsp;</div><div >元&nbsp;</div></td>
              <td width="13%" align="left">产品分类可退款金额</td>
              <td width="37%" align="left"><div style="float:left"><fmt:formatNumber value="${sellBackDto.proCanBackMoney}" pattern="#,##0.00"/>&nbsp;</div><div >元&nbsp;</div></td>
            </tr>
          </table>
		  <table width="100%" cellpadding="0" cellspacing="0" class="tad_1">
            <tr>
              <td colspan="4" align="left" bgcolor="#E0E0E0">退款信息</td>
            </tr>
            <tr>
              <td width="13%" align="left">退款方式</td>
              <td width="37%" align="left">
              		<logic:equal value="7" property="backWay" name="assessDto">
            		            其他&nbsp;
            	    </logic:equal>
				    <logic:equal value="2" property="backWay" name="assessDto">
            		            支票&nbsp;
            	    </logic:equal>
            	    <logic:equal value="3" property="backWay" name="assessDto">
            		           网银&nbsp;
            	    </logic:equal>
            	    <logic:equal value="4" property="backWay" name="assessDto">
            		           电汇&nbsp;
            	    </logic:equal>
            	    <logic:equal value="5" property="backWay" name="assessDto">
            		           银行承兑&nbsp;
            	    </logic:equal>
              
              </td>
              <td width="13%" align="left">退款金额</td>
              <td width="37%" align="left"><div style="float:left"><fmt:formatNumber value="${assessDto.money}" pattern="#,##0.00"/>&nbsp;</div><div >元&nbsp;</div></td>
            </tr>
          </table>
		  <table width="100%" cellpadding="0" cellspacing="0" class="tad_1">
            <tr>
              <td colspan="4" align="left" bgcolor="#E0E0E0">特殊说明</td>
            </tr>

            <tr>
              <td width="100%" align="left">
             ${tf:replaceBr(assessDto.text)}
             &nbsp;</td>
            </tr>
          </table></td>
	</tr>
	<tr>
		<td colspan="2">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="2">
        <table width="100%" cellpadding="0" cellspacing="0" class="tad_1">
                <tr>
                  <td colspan="4" align="left" bgcolor="#E0E0E0">评审意见</td>
                </tr>
        </table>
        </td>
	</tr>
	<tr>
		<td colspan="2">
        <table cellpadding="0" cellspacing="0" width="100%" class="tad_2">
			<tr>
				<td colspan="3" style="line-height:18px" align="left">销售总监评审意见</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">&nbsp;</td>
				<td colspan="2" align="left">
									<input type="checkbox" name="sellBack.sellMajIdea"
										id="checkbox" 
										<c:if test="${assessDto.sellMajIdea == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="sellBack.sellMajIdea"
										id="checkbox" 
										<c:if test="${assessDto.sellMajIdea == 0}">checked="checked"</c:if> />
									不符合
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px" align="left"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="70" valign="top">补充说明：</td>
						<td align="left">
						${tf:replaceBr(assessDto.sellMajText)}
						</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="line-height:22px" width="50%">&nbsp;</td>
				<td style="line-height:22px" width="25%" align="left">签字：${assessDto.sellMajName}</td>
				<td style="line-height:22px" width="25%" align="left">日期：${assessDto.sellMajDate}</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%" class="tad_2">
			<tr>
				<td colspan="3" style="line-height:22px" align="left">运营总监评审意见</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">&nbsp;</td>
		 		 <td colspan="2" align="left">
		  							<input type="checkbox" name="sellBack.opeMajIdea"
										id="checkbox" 
										<c:if test="${assessDto.opeMajIdea == 1}">checked="checked"</c:if> />
									符合 &nbsp;&nbsp;
									<input type="checkbox" name="sellBack.opeMajIdea"
										id="checkbox" 
										<c:if test="${assessDto.opeMajIdea == 0}">checked="checked"</c:if> />
									不符合
			</tr>
			<tr>
				<td colspan="3" style="line-height:22px" align="left"><table cellpadding="0" cellspacing="0" width="100%">
					<tr>
						<td width="70" valign="top">补充说明：</td>
						<td align="left">
						 ${tf:replaceBr(assessDto.opeMajText)}
						</td>
					</tr>
				</table></td>
			</tr>
			<tr>
				<td style="line-height:22px" width="50%">&nbsp;</td>
				<td style="line-height:22px" width="25%" align="left">签字：${assessDto.opeMajName}</td>
				<td style="line-height:22px" width="25%" align="left">日期：${assessDto.opeMajDate}</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2"><table cellpadding="0" cellspacing="0" width="100%" class="tad_2">
			<tr>
				<td colspan="3" style="line-height:22px" align="left">公司经营管理部授权审核人意见：</td>
			</tr>
			<tr>
				<td style="line-height:22px" align="left">&nbsp;</td>
				<td colspan="2" align="left"><input type="checkbox" name="checkbox6" id="checkbox5" />
					符合
					&nbsp;&nbsp;
											<input type="checkbox" name="checkbox6" id="checkbox5" />
					不符合</td>
			</tr>
			<tr>
				<td style="line-height:22px" width="50%">&nbsp;</td>
				<td style="line-height:22px" width="25%" align="left">签字：</td>
				<td style="line-height:22px" width="25%" align="left">日期：</td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td colspan="2" align="center" height="50px">
			<input id="print" type="hidden" value="${param.printAudit}">
			<img src="${ctx}/images/btnPrint.gif" width="65" height="20" class="buttonNoPrint" onClick="javascript:printIt(this);"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="${ctx}/images/btnPrintSZ.gif" width="88" height="20" class="buttonNoPrint" onClick="javascript:printSetup();"/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<img src="${ctx}/images/btnPrintYL.gif" class="buttonNoPrint" onClick="javascript:printPreview();" />
			
			
		</td>
	</tr>
</table>
</body>
</html>
