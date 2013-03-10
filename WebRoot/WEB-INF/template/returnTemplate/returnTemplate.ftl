<#import "common/common.ftl" as c> 
<html>
<head>
<META CONTENT="text/html;charset=utf-8">
<title></title>
<style type="text/css"> 
.div_tiao{width:100%;text-align:left; background-image:url(../images/bt1_bg.gif); line-height:23px; font-weight:bold; color:#43667a; padding-bottom:5px;}
body{ margin:0px; padding:0px; font-size:12px; font-family:"宋体"; background-color:#ffffff; background-repeat:repeat-y;}
table.biao3{border-left:1px solid #c2c2c2;font-size:12px; border-top:1px solid #c2c2c2;}
table.biao3 th{border-right:1px solid #c2c2c2;color:#43667a;nowrap:nowrap;background-image: url(../images/bt1_bg.gif); line-height:23px; font-size:12px;}
table.biao3 td{border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000; }
.td_01{background-color:#f2f2f2; text-align:right;padding:2px 9px 2px 0px; width:20%;}
.td_02{ text-align:left;padding:2px 0px 2px 9px; width:30%;}
</style>
</head>
<body> 
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr><td colspan="2">有一条付款信息,请您查看</td></tr>
  <tr><td colspan="2">&nbsp;</td></tr>
  <tr><td colspan="2"><div class="div_tiao"> &nbsp;&nbsp;回款信息</div></td></tr>
  <tr>
    <td>&nbsp;</td>
    <td> 
	<table border="0" style="font-size:12px;" width="100%" class="biao3"> 
	<tr> 
		<td width="20%" class="td_01" height="20px">
			回款类型
		</td>
	    <td width="30%" class="td_02">
			<#if mreturn.returnType=="0">
				回款
			<#elseif mreturn.returnType=="2">
				到账
			<#else>
				在途款  
			</#if>
	  	</td> 
		<td width="20%" class="td_01" height="20px">
			回款编号</td><td width="30%" class="td_02">
	  		<#if mreturn.no?? >
				${mreturn.no}
			<#else>  
			</#if>
			&nbsp;
		</td>
	</tr>
	<tr>
	  	<td width="20%" class="td_01" height="20px">
			回款方式</td><td width="30%" class="td_02">
	  		<#if mreturn.returnWay==2>
				支票  
			<#elseif mreturn.returnWay==3>
				网银
			<#elseif mreturn.returnWay==4>
				电汇  
			<#elseif mreturn.returnWay==5>
				银行承兑  
			<#elseif mreturn.returnWay==6>
				信用证 
			<#else>
				其它
			</#if>
		</td>  
		<td width="20%" class="td_01" height="20px">
			凭证号</td><td width="30%" class="td_02">
	  		<#if mreturn.number?? >
				${mreturn.number}
			<#else> 
			</#if> 
			&nbsp;
		</td>
	</tr>
	<tr>
	  	<td width="20%" class="td_01" height="20px">
			产品分类名称</td><td width="30%" class="td_02">
	  		${mreturn.productName}
		</td>  
		<td width="20%" class="td_01" height="20px">
			客户名称</td><td width="30%" class="td_02">
	  		${mreturn.customerName}
		</td>
	</tr>
	<tr>
	  	<td width="20%" class="td_01" height="20px">
			回款日期</td><td width="30%" class="td_02">
	  		${mreturn.returnDate}
		</td>  
		<td width="20%" class="td_01" height="20px">
			回款金额</td><td width="30%" class="td_02">
	  		${mreturn.money}
		</td>
	</tr>
	<tr>
	  	<td width="20%" class="td_01" height="20px">
			特殊说明</td><td class="td_02" width="80%" colspan="3">
			<@c.replaceBr str=mreturn.text/>&nbsp;
		</td> 
	</tr>
	</table> 
    </td>
	<td >&nbsp;</td>
  </tr>
</table>
</body>
</html>