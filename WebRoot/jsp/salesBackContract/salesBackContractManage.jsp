<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售退货合同管理</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<style type="text/css"> 
		<!--
		.treven {
			background-color: #f3fbff;
		}
		.over {
			background-color: #ECECEC;
		}
		.rowselected {
  			background-color: #868686;
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
					$(this).click( function(){
					  if( $(this).hasClass("rowselected") ){
						$(this).removeClass("rowselected");
					  }else{
						$(this).addClass("rowselected");
					  }
					});
				});
			}
		});
	//-->
	function lookCommentClick(id,istrue){
		window.open('${ctx}/lookCommentTable.do?print='+istrue+'&id='+id+'','newwindow', "toolbar=no,scrollbars=yes,resizable=yes,top=0,left=170, width=650,height=680");
	}
</script>		

<script type="text/javascript">

	$(function(){		
		
		var msg = "${msg}"
		
		if (msg != "") {
			alert(msg);
		}
	
	});	
	function delSellBackContract(){
		if(confirm('确认删除该条信息及此信息的所有明细？')){return true;}else{return false;}
	}
</script>

</head>
<body>
<c:if test="${param.roleError}">
	<script>alert("你无此权限操作！");</script>
</c:if>
<c:if test="${param.delError}">
	<script>alert("删除失败！");</script>
</c:if>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 销售退货合同管理</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
      <div class="mo_wp">
        <div style="display: ; " class="mo_con" >
        <html:form action="salesBackContractManage" method="get" styleId="salesBackQueryForm">
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td class="td_01" width="20%">产品分类名称</td>
              <td class="td_02" width="30%">
				<html:select property="productTypeId" value="${queryCondition.productTypeId}" style=" width:126px">
					<html:option value="">--请选择--</html:option> 
					<html:options collection="productTypeList" property="id" labelProperty="name"/>
				</html:select>
              </td>
              <td class="td_01" width="20%">产品合同号</td>
              <td class="td_02" width="30%">
              	<input type="text" name="productContractCode" id="productContractCode" value="${queryCondition.productContractCode}" style="width:120px;" />
              </td>
            </tr>
            <tr>
              <td class="td_01">公司合同号</td>
              <td class="td_02">
              	<input type="text" name="companyContractCode" id="companyContractCode"  value="${queryCondition.companyContractCode}" style="width:120px;" />
              	</td>
              <td class="td_01">客户名称</td>
              <td class="td_02">
              	<input type="text" name="customerName" id="customerName" value="${queryCondition.customerName}" style="width:240px;" />
              	</td>
            </tr>
            <tr>
              <td class="td_01">模版类型</td>
              <td class="td_02">
				<html:select property="templateType" value="${queryCondition.templateType}" style=" width:126px">
					<html:option value="">--请选择--</html:option> 
					<html:option value="0">标准模板</html:option> 
					<html:option value="1">非模板</html:option>
			    </html:select>
              </td>
              <td class="td_01">人员名称</td>
              <td class="td_02"><input type="text" name="userName" id="userName" style="width:120px;" value="${queryCondition.userName}" /></td>
            </tr>
            <tr>
              <td class="td_01">合同签订起始日期</td>
              <td class="td_02"><input type="text" name="startTime" id="startTime" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryCondition.startTime}"/></td>
              <td class="td_01">合同签订终止日期</td>
              <td class="td_02"><input type="text" name="endTime" id="endTime" style="width:120px;" onfocus="calendar()" readonly="readonly" value="${queryCondition.endTime}"/></td>
            </tr>
            <tr>
              <td class="td_01">合同状态</td>
              <td class="td_02">
				<html:select property="status" value="${queryCondition.status}" style=" width:126px">
					<html:option value="">--请选择--</html:option>  
					<html:option value="1">未提交</html:option>								
					<html:option value="2">法务专员待评审</html:option>
					<html:option value="3">法务专员未通过</html:option> 
					<html:option value="12">区域总监待评审</html:option> 
					<html:option value="13">区域总监未通过</html:option>
					<html:option value="4">销售总监待评审</html:option> 
					<html:option value="5">销售总监未通过</html:option>
					<html:option value="6">运营总监待评审</html:option>
					<html:option value="7">运营总监未通过</html:option> 
					<html:option value="8">待打印</html:option> 
					<html:option value="9">待确认</html:option> 
					<html:option value="11">合同生效</html:option> 
					<html:option value="10">合同取消</html:option> 
				</html:select> 
              </td>
              <td class="td_01">&nbsp;</td>
              <td class="td_02">&nbsp;</td>
            </tr>
            <tr>
              <td colspan="4" align="center" style="height:30px;">
              	<img src="${ctx}/images/btn_JianSuo.gif" onclick="$('#salesBackQueryForm').submit();"/>
              </td>
            </tr>
          </table>
          </html:form>
        </div>
        <div class="mo_title" onclick="fMainListToggle(this)">
          <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
        </div>
      </div></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;&nbsp;</td>
    <td align="center">
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
        <tr>
          <th nowrap="nowrap">合同流水号</th>
          <th nowrap="nowrap">产品分类名称</th>
          <th nowrap="nowrap">产品合同号</th>
          <th nowrap="nowrap">公司合同号</th>
          <th nowrap="nowrap" >客户名称</th>
          <th nowrap="nowrap">退货合同金额</th>
          <th nowrap="nowrap">模版类型</th>
          <th nowrap="nowrap">签订日期</th>
          <th nowrap="nowrap">人员名称</th>
          <th nowrap="nowrap">合同状态</th>
          <th nowrap="nowrap" >&nbsp;操作&nbsp;</th>
        </tr>

       <c:forEach var="salesBackContract" items="${salesBackContractList}">
        <tr>
          <td nowrap="nowrap" height="18px"><div class="ellipsis_div" style="width:86px;">${salesBackContract.id}&nbsp;</div></td>
          <td nowrap="nowrap" width="84px">${salesBackContract.productTypeName}&nbsp;</td>
          <td nowrap="nowrap" width="120px">${salesBackContract.productContractCode}&nbsp;</td>
          <td nowrap="nowrap" width="96px">${salesBackContract.companyContractCode}&nbsp;</td>
          <td nowrap="nowrap" width="240px">${salesBackContract.customerName}&nbsp;</td>
          <td nowrap="nowrap" width="90px" style=" text-align:right;">
          	<fmt:formatNumber value="${salesBackContract.money}" pattern="#,##0.00"/>&nbsp;
       	  </td>
       	  
          <td nowrap="nowrap">
          	<c:if test="${salesBackContract.templateType==0}">标准模板</c:if>
			<c:if test="${salesBackContract.templateType==1}">非模板</c:if>&nbsp;&nbsp;
          </td>
          <td nowrap="nowrap" >${salesBackContract.date}&nbsp;</td>
          <td nowrap="nowrap">${salesBackContract.userName}&nbsp;</td>
          <td nowrap="nowrap"><div class=ellipsis_div style="width:97px;">
            <c:if test="${salesBackContract.status==1}">未提交</c:if>
			<c:if test="${salesBackContract.status==2}">法务专员待评审</c:if>
			<c:if test="${salesBackContract.status==3}">法务专员未通过</c:if>
			<c:if test="${salesBackContract.status==12}">区域总监待评审</c:if>
			<c:if test="${salesBackContract.status==13}">区域总监未通过</c:if>
			<c:if test="${salesBackContract.status==4}">销售总监待评审</c:if>
			<c:if test="${salesBackContract.status==5}">销售总监未通过</c:if>
			<c:if test="${salesBackContract.status==6}">运营总监待评审</c:if>
			<c:if test="${salesBackContract.status==7}">运营总监未通过</c:if>
			<c:if test="${salesBackContract.status==8}">待打印</c:if>
			<c:if test="${salesBackContract.status==9}">待确认</c:if>
			<c:if test="${salesBackContract.status==11}">合同生效</c:if>
			<c:if test="${salesBackContract.status==10}">合同取消</c:if> 
          &nbsp;</div>

          </td>
          <td nowrap >
			<html:link action="commentSalesBackContractOfShow.do?preview=true" paramId="id" paramName="salesBackContract" paramProperty="id">查看</html:link>
			<c:if test="${action=='viewA'}">
				<a href="#" id="lookComment" onclick="lookCommentClick('${salesBackContract.id}',true)">查看评审表</a>
			</c:if>
			<c:if test="${action == 'UD'}">
				<c:choose>
					<c:when test="${salesBackContract.status==1||salesBackContract.status==3||salesBackContract.status==5||salesBackContract.status==7||salesBackContract.status==10||salesBackContract.status==13}"> 
					    <html:link action="modifySalesBackContractOfShow" paramId="id" paramName="salesBackContract" paramProperty="id">修改</html:link>
					    <html:link action="deleteSalesBackContract" paramId="id" paramName="salesBackContract" paramProperty="id" onclick="return delSellBackContract()">删除</html:link>
					</c:when>
					<c:otherwise> 
						修改
						删除
					</c:otherwise>
				</c:choose>
			</c:if>

			<c:if test="${action == 'assess'}">
				<a href="#" id="lookComment" onclick="lookCommentClick('${salesBackContract.id}',true)">查看评审表</a>
				<c:choose>
					<c:when test="${(salesBackContract.status==12 && requestScope.user.roleId==9)||(salesBackContract.status==2 && requestScope.user.roleId==15) || (salesBackContract.status==4 && requestScope.user.roleId==5) || (salesBackContract.status==6 && requestScope.user.roleId==17)}"> 
				        <html:link action="commentSalesBackContractOfShow" paramId="id" paramName="salesBackContract" paramProperty="id">评审</html:link>	
					</c:when>
					<c:otherwise> 
                        评审
					</c:otherwise>
				</c:choose>
			</c:if>

			<c:if test="${action == 'printE'}">
				<a href="#" id="lookComment" onclick="lookCommentClick('${salesBackContract.id}',true)">查看评审表</a>
				<c:choose>
					<c:when test="${salesBackContract.status==9}"> 
				        <html:link action="commentSalesBackContractOfShow.do?decide=true" paramId="id" paramName="salesBackContract" paramProperty="id">确认</html:link>
					</c:when>
					<c:otherwise> 
                        确认
					</c:otherwise>
				</c:choose>
			</c:if>
          </td>
        </tr>
       </c:forEach>
      </table>
      <br />
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
          <td align="right"><%@ include file="/jsp/common/newPage.jsp"%></td>
        </tr>
      </table></td>
    <td >&nbsp;</td>
  </tr>
</table>
</body>
</html>
