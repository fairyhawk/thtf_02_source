<#import "common/common.ftl" as c> 
<html>
<head>
	<META CONTENT="text/html;charset=utf-8">
	<style type="text/css"> 
		body{ margin:0px; padding:0px; font-size:12px; font-family:"宋体"; background-color:#ffffff; background-repeat:repeat-y;}
	</style>
  <title></title>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div style="width:100%;text-align:left;line-height:23px; font-size:12px;font-weight:bold; color:#43667a; padding-bottom:5px;"> &nbsp;&nbsp;开票申请信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" style="border-left:1px solid #c2c2c2;font-size:12px; border-top:1px solid #c2c2c2;">
          <tr>
                        <td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;background-color:#f2f2f2; text-align:right;padding:4px 9px 4px 0px; width:20%;">申请单号</td>
          		<td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;text-align:left; width:30%;">&nbsp;${invoice.id}</td>
          		<td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;background-color:#f2f2f2; text-align:right;padding:4px 9px 4px 0px; width:20%;">发票类型</td>
          		<td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;text-align:left;width:30%;">&nbsp;<#if invoice.invoiceType == 0 >普通<#else>增值税 </#if></td>
          </tr>
          <tr>
                        <td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;background-color:#f2f2f2; text-align:right;padding:4px 9px 4px 0px; width:20%;">客户名称</td>
          		<td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;text-align:left; width:30%;">&nbsp;${invoice.customerName}</td>
          		<td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;background-color:#f2f2f2; text-align:right;padding:4px 9px 4px 0px; width:20%;">税号</td>
          		<td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;text-align:left; width:30%;">&nbsp;${invoice.taxNumber}</td>
          </tr>

          <tr>
                        <td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;background-color:#f2f2f2; text-align:right;padding:4px 9px 4px 0px; width:20%;">地址</td>
          		<td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;text-align:left; width:30%;">&nbsp;${invoice.invoiceBankAddress}</td>
          		<td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;background-color:#f2f2f2; text-align:right;padding:4px 9px 4px 0px; width:20%;">电话</td>
          		<td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;text-align:left;width:30%;">&nbsp;${invoice.invoiceBankTel}</td>
          </tr>
          <tr>
                        <td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;background-color:#f2f2f2; text-align:right;padding:4px 9px 4px 0px; width:20%;">开户行</td>
          		<td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;text-align:left; width:30%;">&nbsp;${invoice.invoiceBankName}</td>
          		<td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;background-color:#f2f2f2; text-align:right;padding:4px 9px 4px 0px; width:20%;">帐号</td>
          		<td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;text-align:left; width:30%;">&nbsp;${invoice.invoiceBankAccount}</td>
          </tr>
          <tr>
                        <td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;background-color:#f2f2f2; text-align:right;padding:4px 9px 4px 0px; width:20%;">申请人</td>
          		<td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;text-align:left; width:30%;">&nbsp;${invoice.userName}</td>
          		<td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;background-color:#f2f2f2; text-align:right;padding:4px 9px 4px 0px; width:20%;">申请日期</td>
          		<td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;text-align:left; width:30%;">&nbsp;${invoice.date}</td>
          </tr>
          <tr>
                        <td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;background-color:#f2f2f2; text-align:right;padding:4px 9px 4px 0px; width:20%;">通知人</td>
          		<td rowspan="3" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;text-align:left; width:30%;">&nbsp;${invoice.notifyName}</td>
          </tr>
      </table>
   <br/>
	 <div style="width:100%;text-align:left;line-height:23px; font-size:12px;font-weight:bold; color:#43667a; padding-bottom:5px;"> &nbsp;&nbsp;产品信息 </div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" style="border-left:1px solid #c2c2c2;font-size:12px; border-top:1px solid #c2c2c2;">
            <tr>
              <td  style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;background-color:#f2f2f2; text-align:right;padding:4px 9px 4px 0px; width:50%;">产品分类名称</td>
                <td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;text-align:left; width:50%;">&nbsp;${invoice.productTypeName}</td>
            </tr>
   	  </table>
      <div style="width:100%; text-align:right;font-size:12px;">单位：元&nbsp;&nbsp;&nbsp;</div>
  <table border="0" class="biao2" width="100%" cellpadding="0" cellspacing="0" >
  	<tr> 
  	<td colspan="2">	  
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0"  style="border-left:1px solid #c2c2c2;font-size:12px;">
			<tr>
				<th nowrap="nowrap" width="35" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">序号</th>
								<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">发货单号</th>
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">产品合同号</th>
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">产品编码</th>
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">产品名称</th>
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">规格型号</th>
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">单位</th>
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">数量</th>
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">销售单价</th>
				<th nowrap="nowrap" style="border-right:1px solid #c2c2c2;border-top:1px solid #c2c2c2;border-bottom:1px solid #c2c2c2;color:#43667a;line-height:26px; font-size:13px;white-space:nowrap; background-color: #D9ECFF;">开票金额</th>
			</tr>
		    <#list product as prod>
		    <tr>
			    <td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${prod_index+1}</td>
			    				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${prod.getSendGoodsId()}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${prod.getProductContractCode()}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${prod.getProductCode()}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${prod.getProductName()}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${prod.getProductType()}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:left;font-size:12px;white-space:nowrap;">${prod.getProductUnit()}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:right;font-size:12px;white-space:nowrap;">${prod.getCount()}</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;padding:4px 0px 4px 5px; text-align:right;font-size:12px;white-space:nowrap;">
				    ${prod.getPrice()?string("#,##0.00")}	
				</td>
				<td nowrap="nowrap" style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;font-size:12px;white-space:nowrap;text-align:right;padding-right:12px;width:96px;">
				    ${prod.getMoney()?string("#,##0.00")}
				</td>			    
		    </tr>
		    </#list>
		</table>
		</td>
		</tr>
		<tr>
			<td colspan="2">
	            	<table cellpadding="0" cellspacing="0" border="0" width="100%" style="font-size:12px; padding-top:4px">
	                	<tr>
	                    	<td style="background-color:#FFFFFF;" align="right">开票金额合计</td>				
	                        <td width="120px" nowrap="nowrap" style=" background-color:#FFFFFF; text-align:right;">
	                        ${invoice.money?string("#,##0.00")}
	                        </td>
	                    </tr>
                	</table>
            </td>
		</tr>
	</table>
		<div style="width:100%;text-align:left;line-height:23px; font-size:12px;font-weight:bold; color:#43667a; padding-bottom:5px;"> &nbsp;&nbsp;特殊说明</div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" style="border-left:1px solid #c2c2c2;font-size:12px; border-top:1px solid #c2c2c2;">
			<tr>
				<td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;background-color:#f2f2f2; text-align:right;padding:4px 9px 4px 0px; width:30%;">特殊说明</td>
				<td style="border-bottom:1px solid #c2c2c2;border-right:1px solid #c2c2c2;color:#000000;text-align:left;padding:4px 100px 4px 9px;">
				<@c.replaceBr str=invoice.text/>&nbsp; 
				</td>
			</tr>
		</table>
    </td>
    <td >&nbsp;</td>
  </tr>
</table>
</body>
</html>