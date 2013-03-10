<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>样品借出管理</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
		<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
		<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script> 
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>

<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
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
</script>
		
<script type="text/javascript" >
	function form0submit() {
	   $("#searchSampleOutDto").submit();
	}
	
	$(document).ready(function(){
	    var msg = "${msg}";  //获取服务端信息
		if(msg!=""){
			alert(msg);
		}
	 });
 function  confirmDel(){
 
  if(confirm('确认删除该借出单？')==false)return false;
	  return true;  
 }

//隐藏table的一列

//$(document).ready(function(){
	//if("${USERLOGIN.roleId}"==4 ||"${USERLOGIN.roleId}"==9){
      
  	  //$("#outmoneyth").hide();
  	  //$("td[name='outmoneytd']").hide();
  	  //$("#inmoneyth").hide();
  	  //$("td[name='inmoneytd']").hide();
  	//}
//});



</script>

</head>

<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left" width="16px" title=""><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt; 样品借出管理</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
          
           <form action="searchSampleOut.do" id="searchSampleOutDto" >
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                    <td class="td_01">借出单号</td>
                    <td class="td_02" ><input type="text" name="searchSampleOutDto.id" value="${sampleOutSearchDto.id}" style="width:120px;" /></td>
                    <td class="td_01">库房名称</td>
                    <td class="td_02" >
                     <html:select property="searchSampleOutDto.outStockroomId" value="${sampleOutSearchDto.outStockroomId}" style=" width:264px">
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="stockroomEntity" name="stockroomEntitiesList" >
							   <html:option value="${stockroomEntity.id}"> ${stockroomEntity.name}</html:option> 
							</logic:iterate>
					</html:select> 
                    
                    </td>
                  </tr>
                  <tr>
                    <td class="td_01">产品分类名称 </td>
                    <td class="td_02">
                    <html:select property="searchSampleOutDto.productTypeId" value="${sampleOutSearchDto.productTypeId}" style="width:126px">
							<html:option value="">--请选择--</html:option>
							<logic:iterate id="productTypeEntity" name="productTypeEntitiesList" >
							   <html:option value="${productTypeEntity.id}"> ${productTypeEntity.name}</html:option> 
							</logic:iterate>	
					</html:select> 
					</td>
                    <td class="td_01">借出单位类型</td>
                    <td class="td_02">
                    <html:select property="searchSampleOutDto.companyType"  value="${sampleOutSearchDto.companyType}"   style=" width:126px">
                  		  <html:option value="">--请选择--</html:option>  
                	 	  <html:option value="1">公司</html:option> 
                  		  <html:option value="2">客户</html:option>  
                  		  <html:option value="3">供货商</html:option>                  		              		    		   
                     </html:select>	                    
                    </td>
                  </tr>
                  <tr>
                  	<td class="td_01">借出单位</td>
                  	<td class="td_02"><input type="text" name="searchSampleOutDto.companyName" value="${sampleOutSearchDto.companyName}" style="width:240px;" /></td>
                  	<td class="td_01">&nbsp;</td>
                  	<td class="td_02">&nbsp;</td>
                  	</tr>
                  <tr>
                  	<td class="td_01">申请起始日期</td>
                  	<td class="td_02"><input type="text" name="searchSampleOutDto.dateStart" value="${sampleOutSearchDto.dateStart}" style="width:120px;" onfocus="calendar()"/></td>
                  	<td class="td_01">申请终止日期</td>
                  	<td class="td_02"><input type="text" name="searchSampleOutDto.dateEnd"  value="${sampleOutSearchDto.dateEnd}"  style=" width:120px" onfocus="calendar()"/></td>
                  	</tr>
                  <tr>
                  	<td class="td_01">要求发货起始日期</td>
                  	<td class="td_02"><input type="text" name="searchSampleOutDto.requestDateStart" value="${sampleOutSearchDto.requestDateStart}" style="width:120px;" onfocus="calendar()"/></td>
                  	<td class="td_01">要求发货终止日期</td>
                  	<td class="td_02"><input type="text" name="searchSampleOutDto.requestDateEnd" value="${sampleOutSearchDto.requestDateEnd}" style="width:120px;" onfocus="calendar()"/></td>
                  	</tr>
                  <tr>
                  	<td class="td_01">预计归还起始日期</td>
                  	<td class="td_02"><input type="text" name="searchSampleOutDto.inDateStart" value="${sampleOutSearchDto.inDateStart}" style="width:120px;" onfocus="calendar()"/></td>
                  	<td class="td_01">预计归还终止日期</td>
                  	<td class="td_02"><input type="text" name="searchSampleOutDto.inDateEnd" value="${sampleOutSearchDto.inDateEnd}" style="width:120px;" onfocus="calendar()"/></td>
                  	</tr>
                  <tr>
                  	<td class="td_01">发货起始日期</td>
                  	<td class="td_02"><input type="text" name="searchSampleOutDto.sendDateStart" value="${sampleOutSearchDto.sendDateStart}" style="width:120px;" onfocus="calendar()"/></td>
                  	<td class="td_01">发货终止日期 </td>
                  	<td class="td_02"><input type="text" name="searchSampleOutDto.sendDateEnd" value="${sampleOutSearchDto.sendDateEnd}" style="width:120px;" onfocus="calendar()"/></td>
                  	</tr>
                  <tr>
                  	<td class="td_01">申请人</td>
                  	<td class="td_02"><input type="text" name="searchSampleOutDto.userName"  value="${sampleOutSearchDto.userName}" style="width:120px;" /></td>
                  	<td class="td_01">保管人</td>
                  	<td class="td_02"><input type="text" name="searchSampleOutDto.custosName"  value="${sampleOutSearchDto.custosName}" style="width:120px;" /></td>
                  	</tr>
                  <tr>
                  	<td class="td_01">借出单类型</td>
                  	<td class="td_02"> 
                  	<html:select property="searchSampleOutDto.type"  value="${sampleOutSearchDto.type}"   style=" width:126px">
                  		  <html:option value="">--请选择--</html:option>
                	 	  <html:option value="0">不归还</html:option> 
                  		  <html:option value="1">归还</html:option>                  		              		    		   
                     </html:select>		
                  	</td>
                  	<td class="td_01">借出单状态</td>
                  	<td class="td_02">
                  	<html:select property="searchSampleOutDto.sampleOutStatus"  value="${sampleOutSearchDto.sampleOutStatus}"   style=" width:126px">
						  <html:option value="">--请选择--</html:option>
                	 	  <html:option value="1">未提交</html:option> 
                  		  <html:option value="2">销售总监待评审</html:option>    
                  		  <html:option value="3">销售总监未通过</html:option>
                	 	  <html:option value="4">采购主管待评审</html:option> 
                  		  <html:option value="5">采购主管未通过</html:option>    
                  		  <html:option value="6">待发货</html:option>
                	 	  <html:option value="7">发货中</html:option> 
                  		  <html:option value="8">发货异常</html:option>    
                  		  <html:option value="9">发货成功</html:option>
                  	 </html:select>		
                  	</td>
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
    <td >&nbsp;</td>
    <td align="center">
    <br/>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
      <tr>
        <th nowrap="nowrap">借出单号</th>
        <th nowrap="nowrap">库房名称</th>
        <th nowrap="nowrap">&nbsp;产品分类名称&nbsp;</th>
        <th nowrap="nowrap">&nbsp;借出单位类型&nbsp;</th>
        <th nowrap="nowrap">&nbsp;借出单位&nbsp;</th>
        <c:if test="${ USERLOGIN.roleId!=4 && USERLOGIN.roleId!=9}">
        <th nowrap="nowrap" id="outmoneyth">&nbsp;&nbsp;&nbsp;借出金额&nbsp;&nbsp;&nbsp;</th>
        <th nowrap="nowrap" id="inmoneyth">&nbsp;&nbsp;&nbsp;归还金额&nbsp;&nbsp;&nbsp;</th>
        </c:if>
        <th nowrap="nowrap">&nbsp;申请日期&nbsp;</th>
        <th nowrap="nowrap">&nbsp;要求发货日期&nbsp;</th>
        <th nowrap="nowrap">&nbsp;预计归还日期&nbsp;</th>
        <th nowrap="nowrap">&nbsp;发货日期&nbsp;</th>
        <th nowrap="nowrap">&nbsp;申请人&nbsp;</th>
        <th nowrap="nowrap">&nbsp;保管人&nbsp;</th>
        <th nowrap="nowrap">&nbsp;借出单类型&nbsp;</th>
        <th nowrap="nowrap">&nbsp;&nbsp;借出单状态&nbsp;&nbsp;</th>
        <th nowrap="nowrap">&nbsp;操作&nbsp;</th>
        </tr>
        
         <logic:iterate id="sampleOutListDto" name="sampleOutList" >
			<tr>
				<td nowrap="nowrap" width="100px" height="18">${sampleOutListDto.id}&nbsp;</td>
				<td  nowrap="nowrap" title="${sampleOutListDto.outStockroomName}"><div class="ellipsis_div" style="width:90px;">${sampleOutListDto.outStockroomName}&nbsp;</div></td>
				<td width="72px" nowrap="nowrap">${sampleOutListDto.productTypeName}&nbsp;</td>				
				<td><div class="ellipsis_div" style="width:80px;">		
				  <c:if test="${sampleOutListDto.companyType==1}">公司</c:if>
				  <c:if test="${sampleOutListDto.companyType==2}">客户</c:if>
				  <c:if test="${sampleOutListDto.companyType==3}">供货商</c:if>				  
				</div></td>				
				<td  title="${sampleOutListDto.companyName}"><div class="ellipsis_div" style="width:120px;">${sampleOutListDto.companyName}&nbsp;</td>				
				<c:if test="${ USERLOGIN.roleId!=4 && USERLOGIN.roleId!=9}">
				 
				<td width="90px" nowrap="nowrap" style=" text-align:right;" name="outmoneytd"><fmt:formatNumber value="${sampleOutListDto.sampleOutMoneyTax}" pattern="#,##0.00"/>&nbsp;</td>
				<td width="90px" nowrap="nowrap" style=" text-align:right;" name="inmoneytd"><fmt:formatNumber value="${sampleOutListDto.sampleInSumMoneyTax}" pattern="#,##0.00"/>&nbsp;</td>
				</c:if>
				
				<td width="73px" nowrap="nowrap" >${sampleOutListDto.date}&nbsp;</td>
				<td width="84px" nowrap="nowrap" >${sampleOutListDto.requestDate}&nbsp;</td>
				<td width="84px" nowrap="nowrap" >${sampleOutListDto.inDate}&nbsp;</td>
				<td width="73px" nowrap="nowrap" >${sampleOutListDto.sendDate}&nbsp;</td>
				<td width="73px" nowrap="nowrap" >${sampleOutListDto.userName}&nbsp;</td>
				<td width="73px" nowrap="nowrap" >${sampleOutListDto.custosName}&nbsp;</td>				
				<td width="73px" nowrap="nowrap" >
				  <c:if test="${sampleOutListDto.type==0}">不归还</c:if>
				  <c:if test="${sampleOutListDto.type==1}">归还</c:if>				  
				</td>				
				<td width="96px" nowrap="nowrap" >
				  <c:if test="${sampleOutListDto.sampleOutStatus==1}">未提交</c:if>
				  <c:if test="${sampleOutListDto.sampleOutStatus==2}">销售总监待评审</c:if>
				  <c:if test="${sampleOutListDto.sampleOutStatus==3}">销售总监未通过</c:if>
				  <c:if test="${sampleOutListDto.sampleOutStatus==4}">采购主管待评审</c:if>
				  <c:if test="${sampleOutListDto.sampleOutStatus==5}">采购主管未通过</c:if>
				  <c:if test="${sampleOutListDto.sampleOutStatus==6}">待发货</c:if>
				  <c:if test="${sampleOutListDto.sampleOutStatus==7}">发货中</c:if>
				  <c:if test="${sampleOutListDto.sampleOutStatus==8}">发货异常</c:if>	
				  <c:if test="${sampleOutListDto.sampleOutStatus==9}">发货成功</c:if>				
				 </td>
				 
				<td  nowrap="nowrap">
					<a href="getShowSampleOutAssess.do?outId=${sampleOutListDto.id}&type=1">查看</a>		
					<!-- 采购专员-->
					<c:if test="${USERLOGIN.roleId==8}">  
						<c:choose>
							<c:when test="${sampleOutListDto.sampleOutStatus==1||sampleOutListDto.sampleOutStatus==3||sampleOutListDto.sampleOutStatus==5||sampleOutListDto.sampleOutStatus==8}">
						 	   <a href="getSampleOutModifyInfo.do?sampleOutId=${sampleOutListDto.id}">修改</a>
						 	    <a href="deleteSampleOut.do?id=${sampleOutListDto.id}"  onclick=" return confirmDel()" >删除</a>
							</c:when>
							<c:otherwise>
						   		 修改&nbsp;删除
							</c:otherwise> 
						</c:choose>
						 
						<c:choose>
							<c:when test="${sampleOutListDto.sampleOutStatus==9&&sampleOutListDto.recount>0&&sampleOutListDto.type==1}">
						 	   <a href="createSampleInInfo.do?sampleOutId=${sampleOutListDto.id}">归还</a> 
							</c:when>
							<c:otherwise>
						   		 归还
							</c:otherwise> 
						</c:choose>
							
					 </c:if> 					
								
					<!-- 销售经理  -->
					<c:if test="${USERLOGIN.roleId==4}">  
						<c:choose>
							<c:when test="${sampleOutListDto.sampleOutStatus==1||sampleOutListDto.sampleOutStatus==3||sampleOutListDto.sampleOutStatus==5||sampleOutListDto.sampleOutStatus==8}">
						 	  <a href="getSampleOutModifyInfo.do?sampleOutId=${sampleOutListDto.id}">修改</a>
						 	  <a href="deleteSampleOut.do?id=${sampleOutListDto.id}"  onclick=" return confirmDel()" >删除</a>
							</c:when>
							<c:otherwise>
						   		 修改  删除
							</c:otherwise> 
						</c:choose>	
						
						<c:choose>
							<c:when test="${sampleOutListDto.sampleOutStatus==9&&sampleOutListDto.recount>0&&sampleOutListDto.type==1}">
						 	   <a href="createSampleInInfo.do?sampleOutId=${sampleOutListDto.id}">归还</a> 
							</c:when>
							<c:otherwise>
						   		 归还
							</c:otherwise> 
						</c:choose>
					 </c:if> 
					 
					<!-- 采购主管  -->
					<c:if test="${USERLOGIN.roleId==11}">  
					    <c:if test="${sampleOutListDto.sampleOutStatus==4}">
						  <a href="getShowSampleOutAssess.do?outId=${sampleOutListDto.id}&type=2">评审</a>
						</c:if>
						<c:if test="${sampleOutListDto.sampleOutStatus!=4}">
						    评审
						</c:if> 
					</c:if>	
					<!-- 销售总监  -->
					<c:if test="${USERLOGIN.roleId==5}">  
					    <c:if test="${sampleOutListDto.sampleOutStatus==2}">
						  <a href="getShowSampleOutAssess.do?outId=${sampleOutListDto.id}&type=2">评审</a>
						</c:if>
						<c:if test="${sampleOutListDto.sampleOutStatus!=2}">
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
           	  <c:if test="${USERLOGIN.roleId==8||USERLOGIN.roleId==4}">  
				<td align="left" width="150px"><a href="createSampleOutInfo.do"><img src="${ctx}/images/btnYPJCSQ.jpg" width="100" height="20" /></a></td>
			  </c:if>
           	    <td align="right"><%@ include file="/jsp/common/newPage.jsp"%></td>
            </tr>
        </table>   
	 </td>
    <td >&nbsp;</td>
  </tr>

</table>
 
</body>
</html>
