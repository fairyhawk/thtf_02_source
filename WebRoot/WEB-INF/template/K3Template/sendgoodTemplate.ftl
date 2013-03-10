<#import "common/common.ftl" as c> 
<html>
<head>
	<META CONTENT="text/html;charset=utf-8">
  <title></title>
  <style type="text/css"> 
		body{ margin:0px; padding:0px; font-size:12px; font-family:"宋体"; background-color:#ffffff; background-repeat:repeat-y;}
	</style>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>&nbsp;</td>
    <td>
	<table border="0" style="font-size:12px;" width="100%" cellpadding="0" cellspacing="0" >
	<tr> 
	<td colspan="2">  
		<table width="100%" border="0" cellpadding="0" cellspacing="0" style="border-left:1px solid #c2c2c2;font-size:12px;">
			<tr>  
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">部门名称</th> 
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">出库日期</th>
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">客户名称</th> 
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">产品合同号</th>
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">出库单号</th>
				
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">库房名称</th> 
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">产品分类</th>
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">产品编码</th> 
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">产品名称</th>
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">规格型号</th>

				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">单位</th> 
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">数量</th>
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">单价（含税）</th> 
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">总价（含税）</th>
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">经办人</th>

				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">发货人</th>
			</tr>
			<#list sendGoodsList as sendGoods> 
			<tr>  
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${sendGoods.productDeptName}</td> 
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${sendGoods.sendDate}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${sendGoods.customerName}</td> 
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${sendGoods.productContractCode}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${sendGoods.id}</td>
				
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${sendGoods.stockroomname}</td> 
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${sendGoods.producttypename}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${sendGoods.productCode}</td> 
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${sendGoods.productName}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${sendGoods.productType}</td>

				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${sendGoods.unit}</td> 
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${sendGoods.count}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;"><#assign x=sendGoods.price/>#{x;M5}</td> 
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;"><#assign y=sendGoods.money/>#{y;M5}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${sendGoods.userName}</td>

				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${sendGoods.stkAdmName}&nbsp;</td>
			</tr>
			</#list>
		</table>
	</td>
	</tr> 
	</table> 
    </td>
	<td >&nbsp;</td>
  </tr>
</table>
</body>
</html>