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
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">入库日期</th>
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">供货商名称</th> 
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">产品合同号</th>
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">入库单号</th>
				
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

				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">入库人</th>
			</tr>
			<#list incomeList as incomeList> 
			<tr>  
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${incomeList.productDeptName}</td> 
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${incomeList.stkAdmDate}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${incomeList.supplierName}</td> 
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">
					<#if incomeList.productContractCode?? >
						${incomeList.productContractCode}
					</#if>
					&nbsp;
				</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${incomeList.incomeId}</td>
				
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${incomeList.stockroomName}</td> 
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${incomeList.productTypeName}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${incomeList.code}</td> 
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${incomeList.name}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${incomeList.type}</td>

				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${incomeList.unit}</td> 
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${incomeList.count}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;"><#assign x=incomeList.price/>#{x;M5}</td> 
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;"><#assign y=incomeList.sumValue/>#{y;M5}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${incomeList.userName}</td>

				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">
					<#if incomeList.stkAdmName??>
						${incomeList.stkAdmName}
					</#if>
					&nbsp;
				</td>
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