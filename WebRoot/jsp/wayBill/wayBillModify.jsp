<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>运单修改</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" /> 

<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script> 
<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
<script type="text/javascript" src="${ctx}/js/Calendar1.js"></script> 		
		
<script language="JavaScript">
	  $(document).ready(function(){
	  //禁用回车事件，防止提交
				$("body").bind('keyup',function(event) {   
				if(event.keyCode==13){    
				return false;   
				}      
				});  
				//禁用回车事件，防止提交
				
	      var msg = "${msg}";  //获取服务端信息
		if(msg!=""){
		alert(msg);
		}
	 });
	 
    jQuery.validator.addMethod("dateChk",
    function(value, element) {
        if(value == ""){
            return true;
        }
        var arrival_date = $('#arrival_date').val();
        return new Date(Date.parse(arrival_date.replace("-", "/"))).getTime() <= new Date(Date.parse(value.replace("-", "/"))).getTime();
    },
    "签收日期必须大于到货日期!");
    //add by liu
    jQuery.validator.addMethod("arrDateRequired",
    function(value, element) {  
          return $('#arrival_date').val().length>0; 
    },
        
    "请填写到货日期!");
    //add by liu
    $(document).ready(function() {

        $("#wayBillModifyForm").validate({
			rules:{  
				"arrival_date": {
                    required: {
                        depends: function(element) {
                            return $('#signoff_date').val().length != 0;
                        }
                    },
                   arrDateRequired:{
                        depends: function(element) {
                            return $('#oldArrival_date').val().length != 0;
                        }
                    } 
                    
                },
				"signoff_date": {
                    dateChk:{
                        depends: function(element) {
                            return $('#arrival_date').val().length != 0;
                        }
                    }
                }
            },
            messages:{
                "arrival_date":{required:"已填写签收日期，请填写到货日期!" ,arrDateRequired:"请填写到货日期!"}
                
            }
		});
    });
		 
</script>
		
<script type="text/javascript">
  
function form0submit() { 
   if ($('#signoff_date').val().length>0){
      if ($('#signoff_name').val().length==0){
        alert('请填写签收人！');
        return false;
      }
    }
    
 
	if ($('#box_info').val().length>200){
	 alert('在途信息长度过长！'); 
	}else{ 
	  $("#wayBillModifyForm").submit();
	}
	
}
</script>

 
	
</head>
<body>

<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 物流管理 &gt;&gt; 运单管理 &gt;&gt; 运单修改</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
    <div class="div_tiao"> &nbsp;&nbsp;发货信息 </div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
          	<td class="td_01" width="20%" height="18px">装箱单号</td>
          	<td class="td_02" id="box_id" width="30%">${wayBillModifyDto.box_id}&nbsp;</td>
          	
          	<td class="td_01" width="20%">发货日期</td>
          	<td class="td_02" width="30%">${wayBillModifyDto.box_date}&nbsp;</td>
          	</tr>
          <tr>
          	<td class="td_01" height="18px">客户名称</td>
          	<td class="td_02" >${wayBillModifyDto.customer_name}&nbsp;</td>
          	<td class="td_01">货物接收单位名称</td>
          	<td class="td_02" >${wayBillModifyDto.name}&nbsp;</td>
          	</tr>
          <tr>
          	<td class="td_01" height="18px">发货地址</td>
          	<td colspan="3" class="td_02" >${wayBillModifyDto.customer_address}&nbsp;</td>
          	</tr>
          <tr>
          	<td class="td_01" height="18px">联系人</td>
          	<td class="td_02" >${wayBillModifyDto.linkman}&nbsp;</td>
          	<td class="td_01">邮编</td>
          	<td class="td_02" >${wayBillModifyDto.customer_postcode}&nbsp;</td>
          	</tr>
          <tr>
          	<td class="td_01" height="18px">电话</td>
          	<td class="td_02" >${wayBillModifyDto.customer_tel}&nbsp;</td>
          	<td class="td_01">手机</td>
          	<td class="td_02" >${wayBillModifyDto.customer_mobile}&nbsp;</td>
          	</tr>
          <tr>
            <td class="td_01" width="20%" height="18px">装箱件数</td>
            <td class="td_02" width="30%">
              ${wayBillModifyDto.box_count}&nbsp;件</td>
              <td class="td_01" width="20%">要求货运方式</td>
              <td class="td_02" width="30%">
              	<c:if test="${wayBillModifyDto.request_way==0}"> 无指定 </c:if>
             	<c:if test="${wayBillModifyDto.request_way==1}"> 自提 </c:if>
		    	<c:if test="${wayBillModifyDto.request_way==2}"> 快递 </c:if>
		    	<c:if test="${wayBillModifyDto.request_way==3}"> 汽运 </c:if>
		    	<c:if test="${wayBillModifyDto.request_way==4}"> 铁运 </c:if>
		    	<c:if test="${wayBillModifyDto.request_way==5}"> 航空 </c:if>
		    	<c:if test="${wayBillModifyDto.request_way==6}"> 海运 </c:if>
		    	<c:if test="${wayBillModifyDto.request_way==7}"> 中铁 </c:if>
		    	<c:if test="${wayBillModifyDto.request_way==8}"> 市内送货 </c:if>
		    	<c:if test="${wayBillModifyDto.request_way==9}"> 市内快递 </c:if> 
              
              &nbsp;</td>
          </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;运单信息</div>
          
      <html:form action="wayBillModify.do"   styleId="wayBillModifyForm">   
      <table width="100%" border="0" cellpadding="0" cellspacing="0"  class="biao3">
        
        <input type="hidden" value="${wayBillModifyDto.box_id}" name="box_id">
       
        <input type="hidden" value="${wayBillModifyDto.box_date}" name="box_date">
        <input type="hidden" value="${wayBillModifyDto.customer_name}" name="customer_name">
        <input type="hidden" value="${wayBillModifyDto.name}" name="name">
        <input type="hidden" value="${wayBillModifyDto.customer_address}" name="customer_address">
        <input type="hidden" value="${wayBillModifyDto.linkman}" name="linkman">
        <input type="hidden" value="${wayBillModifyDto.customer_postcode}" name="customer_postcode">
        <input type="hidden" value="${wayBillModifyDto.customer_tel}" name="customer_tel">
        <input type="hidden" value="${wayBillModifyDto.customer_mobile}" name="customer_mobile">
        <input type="hidden" value="${wayBillModifyDto.box_count}" name="box_count">
        <input type="hidden" value="${wayBillModifyDto.request_way}" name="request_way">
 
          <tr>
            <td class="td_01" width="20%">运单号</td>
            <td class="td_02" width="30%">
              <input type="text" name="no" id="no" style="width:120px;" value="${wayBillModifyDto.no}" maxlength="20" />
            </td>
            <td class="td_01" width="20%">实际货运方式</td>
            <td class="td_02" width="30%">   
            <html:select property="reality_way"  value="${wayBillModifyDto.reality_way}" style=" width:126px">
				<html:option value="">--请选择--</html:option> 
	      		<html:option value="1"> 自提 </html:option> 
			    <html:option value="2"> 快递 </html:option> 
			    <html:option value="3"> 汽运 </html:option> 
			    <html:option value="4"> 铁运 </html:option> 
			    <html:option value="5"> 航空 </html:option> 
			    <html:option value="6"> 海运 </html:option> 
			    <html:option value="7"> 中铁 </html:option> 
			    <html:option value="8"> 市内送货 </html:option> 
			    <html:option value="9"> 市内快递 </html:option>  
			</html:select>   
            </td>
          </tr>
          <tr>
            <td class="td_01">预计到货日期</td>
            <td class="td_02"> 
            	<input onfocus="calendar()"  readonly="readonly" type="text" name="estimate_date" id="estimate_date" style="width:120px;" value="${wayBillModifyDto.estimate_date}" maxlength="20"/>
            </td>
            <td class="td_01">联系电话</td>
            <td class="td_02">
              <input type="text" name="box_tel" id="box_tel" style="width:120px;" value="${wayBillModifyDto.box_tel}" maxlength="20"/>
              </td>
          </tr>
          <tr>
          	<td class="td_01" >在途信息</td>
          	<td colspan="3" class="td_02">
          		<textarea name="box_info" id="box_info" cols="100" rows="4"  >${wayBillModifyDto.box_info}</textarea>
          	</td>
          	</tr> 
          <tr>
          	<td class="td_01">到货日期</td>
          	<td class="td_02">
          	  <input onfocus="calendar()" readonly="readonly" type="text" name="arrival_date" id="arrival_date" style="width:120px;" value="${wayBillModifyDto.arrival_date}" maxlength="20"/>
          	  <input type="hidden" name="oldArrival_date" id="oldArrival_date"  value="${wayBillModifyDto.arrival_date}" />
          	 </td>
          	<td class="td_01">签收日期</td>
          	<td class="td_02">
          	   <input    type="text"  readonly="readonly" name="signoff_date" id="signoff_date" style="width:120px;" value="${wayBillModifyDto.signoff_date}" maxlength="20" onfocus="calendar()" /></td>
          	</tr>
          <tr>
            <td class="td_01">签收人</td>
            <td class="td_02">
               <input type="text" name="signoff_name" id="signoff_name" style="width:120px;" value="${wayBillModifyDto.signoff_name}" maxlength="4"/>
             </td>
            <td class="td_01">&nbsp;</td>
            <td class="td_02">&nbsp;</td>
          </tr>
      </table>
     
    </html:form>   
  </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom"><a href="#"><img src="${ctx}/images/btnSubmit.gif" width="65" height="20" onclick=" return form0submit()" /></a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${ctx}/wayBillAll.do"><img src="${ctx}/images/btnBack.gif" /></a></td>
    <td></td>
  </tr>
</table>
<br />

</body>
</html>
