<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="tf" uri="localhost" %>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>客户信用查看</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../../js/tad_bs.js"></script>
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
<script type="text/javascript">
       
        $(function(){		   
		    
		    var creditTypeId = $("#creditTypeId").val();			
			
			if (creditTypeId == "5" )
			{
			
				$("td [name=tdProjectName]").show();				
				$("td [name=tdProjectNameFill]").hide();	
				$("td [name=tdagreementCode]").hide();			
				
			}else if(creditTypeId == "4"){
			$("td [name=tdagreementCode]").show();				
				$("td [name=tdProjectNameFill]").hide();
				$("td [name=tdProjectName]").hide();	
				
			} 
			else
			{
				$("td [name=tdProjectName]").hide();
				$("td [name=tdagreementCode]").hide();	
				$("td [name=tdProjectNameFill]").show();
			} 
			
		});
        </script>
</head>
<body >
<input type="hidden" name="creditTypeId" id="creditTypeId" value="${customerCredit.creditTypeId}"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" /> &nbsp;当前位置： 信用管理 &gt;&gt; 客户信用管理 &gt;&gt; 客户信用查看 </td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="center"><br />
      <table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
        <tr>
          <td nowrap="nowrap" class="td_01" width="20%" height="18px">客户名称 </td>
          <td class="td_02" width="30%"> ${customerCredit.customerName} </td>
          <td nowrap="nowrap" class="td_01" width="20%">&nbsp;</td>
          <td class="td_02" width="30%">&nbsp;</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="td_01" width="20%" height="18px">产品分类名称 </td>
          <td class="td_02" width="30%"> ${customerCredit.productTypeName} </td>
          <td nowrap="nowrap" class="td_01" width="20%">信用类型 </td>
          <td class="td_02" width="30%"> ${customerCredit.creditTypeName} </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="td_01" width="20%" height="18px"> 可分配额度 </td>
          <td class="td_02" width="30%" height="18" id="canDistributeLimit"><fmt:formatNumber value="${customerCredit.distributeCredit}" pattern="#,##0.00#"/>&nbsp;元
          </td>
          <td nowrap="nowrap" class="td_01" width="20%" name="tdProjectName">项目名称 </td>
          <td nowrap="nowrap" class="td_01" width="20%" name="tdagreementCode">协议合同编号 </td>
          <td class="td_02" width="30%" name="tdProjectName"> ${customerCredit.projectName} </td>
          <td class="td_02" width="30%" name="tdagreementCode"> ${customerCredit.agreementCode}&nbsp; </td>
          <td nowrap="nowrap" class="td_01" width="20%" name="tdProjectNameFill"><span style="color:#FF0000"></span>&nbsp; </td>
          <td class="td_02" width="30%" name="tdProjectNameFill">&nbsp;</td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="td_01" width="20%" height="18px">信用额度 </td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${customerCredit.creditLimit}" pattern="#,##0.00#"/>
            元 </td>
          <td nowrap="nowrap" class="td_01" width="20%">账期 </td>
          <td class="td_02" width="30%"> ${customerCredit.arterm}
            天 </td>
        </tr>
        <tr>
          <td nowrap="nowrap" class="td_01" width="20%">已用冻结额度 </td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${customerCredit.lockCredit + customerCredit.usedCredit}" pattern="#,##0.00#"/>
            元 </td>
          <td nowrap="nowrap" class="td_01" width="20%" height="18px"> 状态 </td>
          <td class="td_02" width="30%"><c:if test="${customerCredit.enable == '1'}"> 启用 </c:if>
            <c:if test="${customerCredit.enable != '1'}"> 停用 </c:if>
          </td>
        </tr>
         <tr>
          <td nowrap="nowrap" class="td_01" width="20%">特殊说明 </td>
          <td class="td_02" colspan="3" width="80%">${tf:replaceBr(customerCredit.text)}&nbsp;</td>
        </tr>
      </table>
      <br />
      <table border="0" cellpadding="0" cellspacing="0" width="300px"
							id="ec_table">
        <tr>
          <td width="100px" height="50" align="center"><a href="${ctx}/customerCreditManage.do"><img
											src="${ctx}/images/btnBack.gif" width="65" height="20" /> </a> </td>
        </tr>
      </table></td>
    <td>&nbsp;</td>
  </tr>
</table>
</body>
</html>
