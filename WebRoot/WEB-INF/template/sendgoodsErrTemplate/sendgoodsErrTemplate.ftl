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
  <tr><td colspan="2">有一条发货异常信息,发货单号：${sendGoods.id},请您查看</td></tr>
  <tr><td colspan="2">&nbsp;</td></tr>
  <tr><td colspan="2"><div class="div_tiao"> &nbsp;&nbsp;库房管理员评审意见</div></td></tr>
  <tr>
    <td>&nbsp;</td>
    <td> 
	<table border="0" style="font-size:12px;" width="50%" cellpadding="0" cellspacing="0" class="biao3"> 
	<tr> 
		<td width="20%" class="td_01" height="20px">
			1.产品规格是否符合</td><td width="30%" class="td_02">
			<#if sendGoods.stkAdmIdea[0..0]=="0">
				不符合
			<#else>
				  符合
			</#if>      
	  	</td>
	</tr>
	<tr>
		<td width="20%" class="td_01" height="20px">
			2.产品质量是否符合</td><td width="30%" class="td_02">
	  		  <#if sendGoods.stkAdmIdea[1..1]=="0">
				不符合
			<#else>
				  符合
			</#if> 
		</td>
	</tr>
	<tr>
	  	<td width="20%" class="td_01" height="20px">
			3.产品数量是否符合</td><td width="30%" class="td_02">
	  		<#if sendGoods.stkAdmIdea[2..2]=="0">
				不符合
			<#else>
				  符合
			</#if>  
		</td>
	</tr>
	<tr> 
		<td class="td_01" width="20%" valign="top" >补充说明</td>
		<td class="td_02" width="30%"><@c.replaceBr str=sendGoods.stkAdmText/></td> 
	</tr>
	<tr>
		<td width="20%" class="td_01" height="20px">签字：</td><td width="30%" class="td_02">${sendGoods.stkAdmName}</td>
	</tr>
	<tr>
		<td width="20%" class="td_01" height="20px">日期：</td><td width="30%" class="td_02">${sendGoods.stkAdmDate}</td>
	</tr>
	</table> 
    </td>
	<td >&nbsp;</td>
  </tr>
</table>
</body>
</html>