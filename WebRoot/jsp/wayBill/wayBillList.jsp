<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运单管理</title>

 	<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" /> 
	<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<style type="text/css"> 
		
		 
		.treven {
			background-color: #f3fbff;
		}
		.over {
			background-color: #ECECEC;
		
			
		}
		 .rowselected {
  			background-color: #868686;
		}
		</style>
		<script language="JavaScript"> 
		
			  $(document).ready(function(){
	      var msg = "${msg}";  //获取服务端信息
		if(msg!=""){
		alert(msg);
		}
	 });
	 
		 
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
						 //点击换色
						$(this).click( function(){
			              if( $(this).hasClass("rowselected") ){
			                $(this).removeClass("rowselected");
			              }else{
			                $(this).addClass("rowselected");
			              }
			            });
			            //点击换色
            
					});
				}
			});
		 
		</script>
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript">
 
function MM_jumpMenu(targ,selObj,restore){ //v3.0
  eval(targ+".location='"+selObj.options[selObj.selectedIndex].value+"'");
  if (restore) selObj.selectedIndex=0;
}

  function form0submit() {
	var form = document.getElementsByTagName("Form")[0];
	form.submit();
  };
		
</script>
</head>

<body><form action="searchWayBill.do" id="searchWayBill" >
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 物流管理 &gt;&gt; 运单管理</td>
    <td class="ye_header_right">&nbsp;&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center"><br />
    	<div class="mo_wp">
          <div style="display: ; " class="mo_con" >
          
          	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_01" width="20%">运单号</td>
					<td class="td_02" width="30%"><input type="text" name="searchWayBill.no" id="in_no" style="width:120px;" maxlength="20" value="${wayBillDto.no}"/>					</td>
					<td class="td_01" width="20%">装箱单号</td>
					<td class="td_02" width="30%"><input type="text" name="searchWayBill.boxId" id="boxid" style="width:120px;" maxlength="20" value="${wayBillDto.boxId}"/></td>
				</tr>
				<tr>
					<td class="td_01" width="20%">货物接收单位名称</td>
					<td class="td_02" width="30%"><input type="text" name="searchWayBill.company_name" id="in_company_name" style="width:240px;" maxlength="40" value="${wayBillDto.company_name}"/></td>
					<td class="td_01" width="20%">运单状态</td>
					<td class="td_02" width="30%">
					<html:select property="searchWayBill.status" value="${wayBillDto.status}" style=" width:126px">
						<html:option value="">--请选择--</html:option> 
						<html:option value="2">在途</html:option> 
						<html:option value="3">到达</html:option> 
						<html:option value="4">签收</html:option> 
						<html:option value="5">回执确认</html:option> 
						<html:option value="6">已付款</html:option> 
					</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="center" style="height:30px;"><a href="javascript:form0submit();"><img src="images/btn_JianSuo.gif" /></td>
				</tr>
			</table>
			
          </div>
          <div class="mo_title" onclick="fMainListToggle(this)">
              <div style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
          </div>
        </div>
    </td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;&nbsp;</td>
    <td align="center">
    <br/>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
      <tr>
      	<th nowrap="nowrap">运单号</th>
      	<th nowrap="nowrap">装箱单号</th>
      	<th nowrap="nowrap">物流公司名称</th>
      	<th nowrap="nowrap">货物接收单位名称</th>
      	<th nowrap="nowrap">发货地址</th>
      	<th nowrap="nowrap">装箱件数</th>
      	<th nowrap="nowrap">要求货运方式</th>
      	<th nowrap="nowrap">实际货运方式</th>
      	<th nowrap="nowrap">发货日期</th>
      	<th nowrap="nowrap">预计到货日期</th>
      	<th nowrap="nowrap">到货日期</th>
      	<th nowrap="nowrap">签收日期</th>
      	<th nowrap="nowrap">回执确认日期</th>
      	<th nowrap="nowrap">运单状态</th>
      	<th nowrap="nowrap">&nbsp;操作&nbsp;</th>
        </tr>
     
        <logic:iterate id="billAllDto" name="billAllDtoList" >
 <tr>
      	<td width="75px" nowrap="nowrap" height="18px" title="${billAllDto.no}"><div class=ellipsis_div style="width:70px;">${billAllDto.no}&nbsp;<div></td>
      	<td width="75px" nowrap="nowrap" title="${billAllDto.id}"><div class=ellipsis_div style="width:70px;">${billAllDto.id}&nbsp;</div></td>
      	<td width="85px" nowrap="nowrap" title="${billAllDto.logistics_name}"><div class=ellipsis_div style="width:80px;">${billAllDto.logistics_name}&nbsp;<div></td>
      	<td width="86px" nowrap="nowrap" title="${billAllDto.company_name}"><div class=ellipsis_div style="width:97px;">${billAllDto.company_name}&nbsp;</div></td>
      	<td width="103px" nowrap="nowrap" title="${billAllDto.address}"><div class=ellipsis_div style="width:80px;">${billAllDto.address}&nbsp;</div></td>
      	<td width="58px" nowrap="nowrap" style="text-align:right">${billAllDto.count}&nbsp;</td>
      	
      	<td width="63px" nowrap="nowrap" >
      		<c:if test="${billAllDto.request_way==1}"> 自提 </c:if>
		    <c:if test="${billAllDto.request_way==2}"> 快递 </c:if>
		    <c:if test="${billAllDto.request_way==3}"> 汽运 </c:if>
		    <c:if test="${billAllDto.request_way==4}"> 铁运 </c:if>
		    <c:if test="${billAllDto.request_way==5}"> 航空 </c:if>
		    <c:if test="${billAllDto.request_way==6}"> 海运 </c:if>
		    <c:if test="${billAllDto.request_way==7}"> 中铁 </c:if>
		    <c:if test="${billAllDto.request_way==8}"> 市内送货 </c:if>
		    <c:if test="${billAllDto.request_way==9}"> 市内快递 </c:if>  
      	    &nbsp;
      	</td> 
      	 <td width="85px" nowrap="nowrap"  >
      		<c:if test="${billAllDto.reality_way==1}"> 自提 </c:if>
		    <c:if test="${billAllDto.reality_way==2}"> 快递 </c:if>
		    <c:if test="${billAllDto.reality_way==3}"> 汽运 </c:if>
		    <c:if test="${billAllDto.reality_way==4}"> 铁运 </c:if>
		    <c:if test="${billAllDto.reality_way==5}"> 航空 </c:if>
		    <c:if test="${billAllDto.reality_way==6}"> 海运 </c:if>
		    <c:if test="${billAllDto.reality_way==7}"> 中铁 </c:if>
		    <c:if test="${billAllDto.reality_way==8}"> 市内送货 </c:if>
		    <c:if test="${billAllDto.reality_way==9}"> 市内快递 </c:if>  
      	    &nbsp;     	 
      	 
      	 </td>
      	 
      	<td width="73px" nowrap="nowrap" >${billAllDto.send_date}&nbsp;</td>
      	<td width="79px" nowrap="nowrap">${billAllDto.estimate_date}&nbsp;</td>
      	<td width="73px" nowrap="nowrap">${billAllDto.arrival_date}&nbsp;</td>
      	<td width="73px" nowrap="nowrap" >${billAllDto.signoff_date}&nbsp;</td>
      	<td width="79px" nowrap="nowrap">${billAllDto.confirm_date}&nbsp;</td>
      	 
      	<td width="58px" nowrap="nowrap">
      		<c:if test="${billAllDto.status==1}"> 在途 </c:if> 
      	 	<c:if test="${billAllDto.status==2}"> 在途 </c:if> 
      	 	<c:if test="${billAllDto.status==3}"> 到达 </c:if> 
      	 	<c:if test="${billAllDto.status==4}"> 签收 </c:if> 
      	 	<c:if test="${billAllDto.status==5}"> 回执确认 </c:if>
      	 	<c:if test="${billAllDto.status==6}"> 已付款 </c:if>
      		
      	</td>
      	 
      	<td nowrap="nowrap" width="70px">
      	<a href="wayBillView.do?boxId=${billAllDto.id}">查看</a>
      	<c:if test="${billAllDto.status==2  or billAllDto.status==3}">   
      	  <a href="wayBillModifyView.do?boxId=${billAllDto.id}&boxNo=${billAllDto.no}">修改</a> 
      	</c:if> 
      	<c:if test="${billAllDto.status==4  or billAllDto.status==5}">   
      	   修改  
      	</c:if> 
      	</td>
        </tr>
 </logic:iterate>
    </table>
<br />
  <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        	<tr>
       	  	  <td align="right" ><%@ include file="/jsp/common/newPage.jsp"%></td>
            </tr>
    </table>    </td>
    <td >&nbsp;</td>
  </tr>
</table>
 	</form>
</body>
</html>
