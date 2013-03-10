<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>样品归还管理</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
		<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
		<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script> 
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>

<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
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
</script>

<script type="text/javascript" >
	function form0submit() {
	   $("#searchSampleInDto").submit();
	}
	
	$(document).ready(function(){
	    var msg = "${msg}";  //获取服务端信息
		if(msg!=""){
			alert(msg);
		}
	 });
 function  confirmDel(){
 
  if(confirm('确认删除该归还单？')==false)return false;
	  return true;  
 }
 
 
 //隐藏table的一列
//$(document).ready(function(){
//	if("${USERLOGIN.roleId}"==4 ||"${USERLOGIN.roleId}"==9){
      
//  	  $("#inmoneyth").hide();
//  	  $("td[name='inmoneytd']").hide();
//  	}
//});

</script>
</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt; 样品归还管理</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
          
          <form action="searchSampleIn.do" id="searchSampleInDto" >
          
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                    <td class="td_01">归还单号</td>
                    <td class="td_02" ><input type="text" name="searchSampleInDto.sampleInId" value="${sampleInSearchDto.sampleInId}" style="width:120px;" maxLength="20" /></td>
                    <td class="td_01">借出单号</td>
                    <td class="td_02" ><input type="text" name="searchSampleInDto.sampleOutId" value="${sampleInSearchDto.sampleOutId}" style="width:120px;" maxLength="20"/></td>
                  </tr>
                  <tr>
                    <td class="td_01">库房名称 </td>
                    <td class="td_02">
                    <html:select property="searchSampleInDto.inStockroomId" value="${sampleInSearchDto.inStockroomId}" style="width:264px;">
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="stockroomEntity" name="stockroomEntitiesList" >
							   <html:option value="${stockroomEntity.id}"> ${stockroomEntity.name}</html:option> 
							</logic:iterate>
					</html:select> 
                    
                    </td>
                    <td class="td_01">产品分类名称</td>
                    <td class="td_02"> 
                    	<html:select property="searchSampleInDto.productTypeId" value="${sampleInSearchDto.productTypeId}" style="width:126px">
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="productTypeEntity" name="productTypeEntitiesList" >
							   <html:option value="${productTypeEntity.id}"> ${productTypeEntity.name}</html:option> 
							</logic:iterate>	
						</html:select> 
                    
                    </td>
                  </tr>
                  <tr>
                  	<td class="td_01">借出单位</td>
                  	<td class="td_02"><input type="text" name="searchSampleInDto.companyName" value="${sampleInSearchDto.companyName}" style="width:240px;" maxLength="40"/></td>
                  	<td class="td_01">申请人</td>
                  	<td class="td_02"><input type="text" name="searchSampleInDto.userName"  value="${sampleInSearchDto.userName}" style="width:120px;" maxLength="20"/></td>
                  	</tr>
                  <tr>
                  	<td class="td_01">申请起始日期</td>
                  	<td class="td_02"><input type="text" name="searchSampleInDto.dateStart" value="${sampleInSearchDto.dateStart}" style="width:120px;" onfocus="calendar()"/></td>
                  	<td class="td_01">申请终止日期</td>
                  	<td class="td_02"><input type="text" name="searchSampleInDto.dateEnd"  value="${sampleInSearchDto.dateEnd}"  style=" width:120px" onfocus="calendar()"/></td>
                  	</tr>
                  <tr>
                  	<td class="td_01">预计归还起始日期</td>
                  	<td class="td_02"><input type="text" name="searchSampleInDto.inDateStart" value="${sampleInSearchDto.inDateStart}" style="width:120px;" onfocus="calendar()"/></td>
                  	<td class="td_01">预计归还终止日期</td>
                  	<td class="td_02"><input type="text" name="searchSampleInDto.inDateEnd" value="${sampleInSearchDto.inDateEnd}" style="width:120px;" onfocus="calendar()"/></td>
                  	</tr>
                  <tr>
                  	<td class="td_01">入库起始日期</td>
                  	<td class="td_02"><input type="text" name="searchSampleInDto.stkAdmDateStart" value="${sampleInSearchDto.stkAdmDateStart}" style="width:120px;" onfocus="calendar()"/></td>
                  	<td class="td_01">入库终止日期</td>
                  	<td class="td_02"><input type="text" name="searchSampleInDto.stkAdmDateEnd" value="${sampleInSearchDto.stkAdmDateEnd}" style="width:120px;" onfocus="calendar()"/></td>
                  	</tr>
                  <tr>
                  	<td class="td_01">归还单状态</td>
                  	<td class="td_02">
                  	<html:select property="searchSampleInDto.status"  value="${sampleInSearchDto.status}"   style=" width:126px">
						  <html:option value="">--请选择--</html:option>
                	 	  <html:option value="1">未提交</html:option> 
                  		  <html:option value="2">采购主管待评审</html:option>    
                  		  <html:option value="3">采购主管未通过</html:option>
                	 	  <html:option value="4">待核对</html:option> 
                  		  <html:option value="5">核对未成功</html:option>    
                  		  <html:option value="6">入库成功</html:option>
                  	 </html:select>	
                  	
                  	</td>
                  	<td class="td_01">&nbsp;</td>
                  	<td class="td_02">&nbsp;</td>
                  	</tr>
              <tr>
                <td colspan="4" align="center" style="height:30px;"><img src="${ctx}/images/btn_JianSuo.gif" onclick="form0submit()"/></td>
              </tr>
            </table>
            
             </form>  
          </div>
          <div class="mo_title" onclick="fMainListToggle(this)">
              <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
          </div>
        </div>
    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;&nbsp;</td>
    <td align="center">
    <div style="width:100%; text-align:right">单位：元</div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
      <tr>
        <th nowrap="nowrap">归还单号</th>
        <th nowrap="nowrap">借出单号</th>
        <th nowrap="nowrap">库房名称</th>
        <th nowrap="nowrap">&nbsp;产品分类名称&nbsp;</th>
        <th nowrap="nowrap">借出单位</th>
        <!-- <th nowrap="nowrap" id="inmoneyth">&nbsp;&nbsp;&nbsp;归还金额&nbsp;&nbsp;&nbsp;</th> -->
        <c:if test="${USERLOGIN.roleId!=4&&USERLOGIN.roleId!=9}"><th nowrap="nowrap">&nbsp;&nbsp;&nbsp;归还金额&nbsp;&nbsp;&nbsp;</th></c:if>
        <th nowrap="nowrap">&nbsp;&nbsp;申请日期&nbsp;&nbsp;</th>
        <th nowrap="nowrap">&nbsp;预计归还日期&nbsp;</th>
        <th nowrap="nowrap">&nbsp;&nbsp;入库日期&nbsp;&nbsp;</th>
        <th nowrap="nowrap">申请人</th>
        <th nowrap="nowrap">&nbsp;&nbsp;归还单状态&nbsp;&nbsp;</th>
        <th nowrap="nowrap">操作</th>
        </tr>
      
      <logic:iterate id="sampleInListDto" name="sampleInList" >
			<tr>
				<td nowrap="nowrap" width="100px" height="18">${sampleInListDto.sampleInId}&nbsp;</td>
				<td nowrap="nowrap" ><div class="ellipsis_div" style="width:100px;">${sampleInListDto.sampleOutId}&nbsp;</div></td>
				<td width="120px" nowrap="nowrap" title="${sampleInListDto.inStockroomName}"><div class="ellipsis_div" style="width:100px;">${sampleInListDto.inStockroomName}&nbsp;;</div></td>	
				<td width="72px" nowrap="nowrap">${sampleInListDto.productTypeName}&nbsp;</td>
				<td width="72px" nowrap="nowrap" title="${sampleInListDto.companyName}"><div class="ellipsis_div" style="width:180px;">${sampleInListDto.companyName}&nbsp;</div></td>				
				<c:if test="${USERLOGIN.roleId!=4&&USERLOGIN.roleId!=9}"><td width="93px" nowrap="nowrap" style="text-align:right"><fmt:formatNumber value="${sampleInListDto.moneyTax}" pattern="#,##0.00"/>&nbsp;</td></c:if>
				<td width="73px" nowrap="nowrap" >${sampleInListDto.date}&nbsp;</td>
				<td width="77px" nowrap="nowrap" >${sampleInListDto.inDate}&nbsp;</td>
				<td width="73px" nowrap="nowrap" >${sampleInListDto.stkAdmDate}&nbsp;</td>
				<td width="73px" nowrap="nowrap" >${sampleInListDto.userName}&nbsp;</td>				
				<td width="100px" nowrap="nowrap" >
				  <c:if test="${sampleInListDto.status==1}">未提交</c:if>
				  <c:if test="${sampleInListDto.status==2}">采购主管待评审</c:if>
				  <c:if test="${sampleInListDto.status==3}">采购主管未通过</c:if>
				  <c:if test="${sampleInListDto.status==4}">待核对</c:if>
				  <c:if test="${sampleInListDto.status==5}">核对未通过</c:if>
				  <c:if test="${sampleInListDto.status==6}">入库成功</c:if>				 			
				</td>				 
				<td  nowrap="nowrap">
					<a href="getShowSampleInAssess.do?id=${sampleInListDto.sampleInId}&type=view">查看</a>		
					<!-- 采购专员-->
					<c:if test="${USERLOGIN.roleId==8}">  
						<c:choose>
							<c:when test="${sampleInListDto.status==1||sampleInListDto.status==3||sampleInListDto.status==5}">
						 	   <a href="getSampleInModifyInfo.do?id=${sampleInListDto.sampleInId}">修改</a>
						 	   <a href="deleteSampleIn.do?id=${sampleInListDto.sampleInId}"  onclick=" return confirmDel()" >删除</a>
							</c:when>
							<c:otherwise>
						   		 修改  删除
							</c:otherwise> 
						</c:choose>	
					 </c:if> 					
								
					<!-- 销售经理  -->
					<c:if test="${USERLOGIN.roleId==4}">  
						<c:choose>
							<c:when test="${sampleInListDto.status==1||sampleInListDto.status==3||sampleInListDto.status==5}">
						 	  <a href="getSampleInModifyInfo.do?id=${sampleInListDto.sampleInId}">修改</a>
						 	  <a href="deleteSampleIn.do?id=${sampleInListDto.sampleInId}"  onclick=" return confirmDel()" >删除</a>
							</c:when>
							<c:otherwise>
						   		 修改  删除
							</c:otherwise> 
						</c:choose>	
					 </c:if> 
					 
					<!-- 采购主管  -->
					<c:if test="${USERLOGIN.roleId==11}">  
					    <c:if test="${sampleInListDto.status==2}">
						  <a href="getShowSampleInAssess.do?id=${sampleInListDto.sampleInId}&type=showAssess">评审</a>
						</c:if>
						<c:if test="${sampleInListDto.status!=2}">
						    评审
						</c:if> 
					</c:if>&nbsp;	
										
				</td>
			</tr>
		</logic:iterate>
      
      
    </table>
    <br />
  <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        	<tr>				
           	    <td align="right"><%@ include file="/jsp/common/newPage.jsp"%></td>
            </tr>
        </table>   
	 </td>
    <td >&nbsp;</td>
  </tr>

</table>
 
</body>
</html>
