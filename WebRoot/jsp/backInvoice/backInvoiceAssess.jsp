<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;
charset=utf-8"%> 
<%@taglib prefix="tf" uri="localhost" %>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>通知开票</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<style type="text/css"> 
		<!--
		.treven {
			background-color: #f3fbff;
		}
		.over {
			background-color: #ECECEC;
		}
		-->
		</style>
		<script language="JavaScript"> 
		 
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
					});
				}
				
				//禁用回车事件，防止提交
				$("body").bind('keyup',function(event) {   
				if(event.keyCode==13){    
				return false;   
				}      
				});  
          //禁用回车事件，防止提交
				
			});
		 
		</script>
		
<script type="text/javascript">




         function sub(assessResult){
         	if($("#status").val()==1 || $("#status").val()==3 || $("#status").val()==5 || $("#status").val()==7 || $("#status").val()==6){
         		alert("此状态不能评审！");
         		return;
         	}else if($("#status").val()==4){
         		if(${USERLOGIN.roleId!="17"}){alert("此状态不能评审！");return;}
         	}
             $("#assessResult").attr("value", assessResult); 
           		//根据当前用户的权限用不同的复选框
	           if (${USERLOGIN.roleId=="5"}){ 
	             var checkBoxObj = document.getElementsByName('backInvoiceAssess.sellMajIdea');
	           } else if (${USERLOGIN.roleId=="17"} ){ 
	              var checkBoxObj = document.getElementsByName('backInvoiceAssess.opeMajIdea');    
	           }else {
	             alert("非权限范围内提交！");
	             return false;
	           }
	          //点了通过时。必须选中同意复选框 
             if (assessResult=="pass"){ 
               if (checkBoxObj[0].checked){
                 $("#backInvoiceAssess").submit();
               }else{
                 alert("通过评审，评审意见必须选择同意！");
               }              
             } 
             //点了不通过时
             if (assessResult=="nopass"){ 
             	if ((checkBoxObj[0].checked)||(checkBoxObj[1].checked)) {
             	    var textarea ="";
                  if(${USERLOGIN.roleId=="5"}){
                      textarea = document.getElementById('backInvoiceAssess.sellMajText').value;
                    }
                  if(${USERLOGIN.roleId=="17"}){
                      textarea = document.getElementById('backInvoiceAssess.opeMajText').value;       
                    }
                    textarea=$.trim(textarea);   
                  if(textarea == null || textarea ==""){
                       alert("补充说明不可为空！");
						return false;			                       									
					}
                	$("#backInvoiceAssess").submit();
                }else{
                   alert("请选择评审意见！");
                }               
             }  
             
         }
         
   //销售总监复选框操作      
   function sellMajCheck(ideaFlag){
       var checkBoxObj = document.getElementsByName('backInvoiceAssess.sellMajIdea');
       //点同意
       if (ideaFlag=="0"){  
          checkBoxObj[1].checked=false;
       } 
       //点不同意
       if (ideaFlag=="1"){ 
            checkBoxObj[0].checked=false;  
          }
       } 
  
    //运营总监复选框操作  
   function opeMajCheck(ideaFlag){
       var checkBoxObj = document.getElementsByName('backInvoiceAssess.opeMajIdea');
       
       //点同意
       if (ideaFlag=="0"){  
          checkBoxObj[1].checked=false;
       } 
       //点不同意
       if (ideaFlag=="1"){
          checkBoxObj[0].checked=false;
       } 
   }
   //画面初始化时。可用状态设置
 	$(document).ready(
 	
	  function(){
	     var sellcheckBoxObj = document.getElementsByName('backInvoiceAssess.sellMajIdea');
	     var opecheckBoxObj  = document.getElementsByName('backInvoiceAssess.opeMajIdea'); 
	       
		 //登录用户是销售总监时，运营总监部分不可用
		 if ( ${USERLOGIN.roleId=="5"}){ 
		 	opecheckBoxObj[0].disabled="disabled";
		 	opecheckBoxObj[1].disabled="disabled"; 
		    document.getElementById('backInvoiceAssess.opeMajText').disabled="disabled";
		    $('#opeMajName').text('签字：');
		 }
		//登录用户是运营总监时，销售总监部分不可用 
		 if ( ${USERLOGIN.roleId=="17"}){
		 	sellcheckBoxObj[0].disabled="disabled";
		 	sellcheckBoxObj[1].disabled="disabled"; 
		 	document.getElementById('backInvoiceAssess.sellMajText').disabled="disabled";
		 }  
	  }
	  
	);
	
	
	$(function(){
	
		$("#totalMoney").html(formatNum(fmoney(${totalMakeInvoicePrice},2))+'元');
	
	});
	
//数字每隔三位加上逗号
function   formatNum(num)
{   
    if(!/^(\+|-)?(\d+)(\.\d+)?$/.test(num)){alert("金额格式化错误!");   return   num;}   
    var   a   =   RegExp.$1,   b   =   RegExp.$2,   c   =   RegExp.$3;   
    var   re   =   new   RegExp().compile("(\\d)(\\d{3})(,|$)");   
    while(re.test(b))   b   =   b.replace(re,   "$1,$2$3");   
    return   a   +""+   b   +""+   c;   
}

//金额合计的格式化s为要格式化的参数（浮点型），n为小数点后保留的位数	
	function fmoney(s,n){
		n = n>0 && n<=20 ? n : 2;
		s = parseFloat((s+"").replace(/[^\d\.-]/g,"")).toFixed(n)+"";
		var l = s.split(".")[0].split("").reverse(), 
		r = s.split(".")[1]; 
		t = "";
		for(i = 0;i<l.length;i++){
			t+=l[i]+((i+1)%3==0 && (i+1) != l.length ? "" : ""); 
		}
		return t.split("").reverse().join("")+"."+r;
	}	

 $(document).ready(function(){
	      var msg = "${msg}";  //获取服务端信息
		if(msg!=""){
		alert(msg);
		}
	 });
		  
</script>
</head>
<body>
<html:form action="backInvoiceJudge.do"  styleId="backInvoiceAssess">
<input type="hidden" id="assessResult" value="${assessResult}" name = "assessResult"/>
<input type="hidden" id="backInvoiceAssess.id" value="${assessDto.id}" name = "backInvoiceAssess.id"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 销售发票管理 &gt;&gt; 退票评审</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;发票信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
          <tr>
				<td class="td_01" width="20%" height="18px">开票日期</td>
          		<td class="td_02" width="30%">${assessDto.date}&nbsp;</td>
          		<td class="td_01" width="20%">发票号</td>
          		<td class="td_02" width="30%">${assessDto.number}&nbsp;</td>
          </tr>
          <tr>
				<td class="td_01" height="18px">发票金额</td>
          		<td class="td_02">${assessDto.money}&nbsp;</td>
          		<td class="td_01">&nbsp;</td>
          		<td class="td_02">&nbsp;</td>
          </tr>
      </table>
   <br/>
	 <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
            <tr>
              <td height="18" class="td_03" width="50%">产品分类名称</td>
                <td class="td_04">${assessDto.productTypeName}&nbsp;</td>
            </tr>
   	  </table>
      <div style="width:100%; text-align:right">单位：元</div>
   	  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" style="border-left:1px solid #FFFFFF;">
			<tr>
				<th nowrap="nowrap" width="35" style="border-left:1px solid #c2c2c2;">序号</th>
				<th nowrap="nowrap">发货单号</th>
				<th nowrap="nowrap">产品合同号</th>
				<th nowrap="nowrap">公司合同号</th>
				<th nowrap="nowrap">产品编码</th>
				<th nowrap="nowrap">产品名称</th>
				<th nowrap="nowrap">规格型号</th>
				<th nowrap="nowrap">单位</th>
				<th nowrap="nowrap">销售单价</th>
				<th nowrap="nowrap">开票数</th>
				<th nowrap="nowrap">开票金额</th>
			</tr>
			<logic:present name="sellInvoiceDetailList">
				<logic:iterate id="sellInvoiceDetail" name="sellInvoiceDetailList" indexId="indexId">
			<tr>
				<td nowrap="nowrap" style="border-left:1px solid #c2c2c2;" height="18px">${indexId+1}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.sendGoodsId}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.productContractCode}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.companyContractCode}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.productCode}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.productName}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.productType}&nbsp;</td>
				<td nowrap="nowrap" >${sellInvoiceDetail.productUnit}&nbsp;</td>
				<td nowrap="nowrap" style="text-align:right; width:93px;"><fmt:formatNumber value="${sellInvoiceDetail.price}" pattern="#,##0.00"/>&nbsp;</td>
				<td nowrap="nowrap" style="text-align:right; width:93px;">${sellInvoiceDetail.count}&nbsp;</td>
				<td nowrap="nowrap" style="text-align:right; padding-right:12px; width:93px;"><fmt:formatNumber value="${sellInvoiceDetail.makeInvoicePrice}" pattern="#,##0.00"/></td>				
			</tr>		
				</logic:iterate>
			</logic:present>
		</table>
		<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
			<tr>
			<td style=" border:1px solid #FFFFFF; background-color:#FFFFFF">&nbsp;</td>
			<td colspan="10" nowrap="nowrap" style=" text-align:right; border:1px solid #FFFFFF; padding-right:5px; background-color:#FFFFFF">&nbsp;</td>
			<td  nowrap="nowrap" style=" text-align:right;width:96px;padding-right:12px">退票金额合计</td>
			<td  nowrap="nowrap" style=" text-align:right;width:100px;padding-top:5px;" id="totalMoney"></td>
			</tr>
		</table>
		
		<div class="div_tiao"> &nbsp;&nbsp;备注信息</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">备注信息</td>
					<td class="td_04" style="padding:5px 100px 5px 5px">${tf:replaceBr(assessDto.remark)}&nbsp;</td>
				</tr>
			</table>
			<br/>
			
			<div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
				<tr>
					<td class="td_03" width="20%">特殊说明</td>
					<td class="td_04" style="padding:5px 100px 5px 5px">${tf:replaceBr(assessDto.text)}&nbsp;</td>
				</tr>
			</table>
			<br/>
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
            <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
              <tr>
			  	<td height="20px" colspan="2">销售总监评审意见：</td>
			  	</tr>
			  <tr>
			  	<td height="20px">&nbsp;</td>
			  	<td height="20px"><input type="checkbox" name="backInvoiceAssess.sellMajIdea" id="backInvoiceAssess.sellMajIdea" value="1" onclick="sellMajCheck(0)"
		  	             <c:if test='${assessDto.sellMajIdea == "1"}'>checked="checked"</c:if>  
		  	            />同意&nbsp;&nbsp;
				       <input type="checkbox" name="backInvoiceAssess.sellMajIdea" id="backInvoiceAssess.sellMajIdea" value="0" onclick="sellMajCheck(1)"
					     <c:if test='${assessDto.sellMajIdea == "0"}'>checked="checked"</c:if> 
					    />不同意
			  	</td>
			  </tr>
			  <tr>
			  	<td height="20px" colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                          <td style="padding:5px 0 5px 0;">
                          	<textarea name="backInvoiceAssess.sellMajText" id="backInvoiceAssess.sellMajText" cols="60" rows="3">${assessDto.sellMajText}</textarea>
                          </td>
                        </tr>
                	</table>				</td>
			  	</tr>
              <tr>
                <td width="320" height="20px">签字：&nbsp; ${assessDto.sellMajName} </td>
                <input  type="hidden" name="backInvoiceAssess.sellMajName" value="${assessDto.sellMajName}">
                <td width="140">日期：&nbsp; ${assessDto.sellMajDate} </td>
              </tr>
			  <tr>
			  	<td height="20px" colspan="2">&nbsp;</td>
			  	</tr>
			  <tr>
			  	<td height="20px" colspan="2">运营总监评审意见：</td>
			  	</tr>
			  <tr>
			  	<td height="20px">&nbsp;</td>
			  	<td height="20px"><input type="checkbox" name="backInvoiceAssess.opeMajIdea" id="backInvoiceAssess.opeMajIdea" value="1" onclick="opeMajCheck(0)"
			  	             <c:if test='${assessDto.opeMajIdea == "1"}'>checked="checked"</c:if> 
			  	            />
			  		同意
			  		&nbsp;&nbsp;
						<input type="checkbox" name="backInvoiceAssess.opeMajIdea" id="backInvoiceAssess.opeMajIdea" value="0" onclick="opeMajCheck(1)" 
						 <c:if test='${assessDto.opeMajIdea == "0"}'>checked="checked"</c:if> 
						 
						/>
			  		不同意</td>
			  </tr>
			  <tr>
			  	<td height="20px" colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <tr>
                          <td valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                          <td style="padding:5px 0 5px 0;">
                          <textarea name="backInvoiceAssess.opeMajText" id="backInvoiceAssess.opeMajText" cols="60" rows="3" >${assessDto.opeMajText}</textarea>
                          </td>
                        </tr>
                	</table>				</td>
			  	</tr>
			  <tr>
					<td height="20px" id="opeMajName">签字：&nbsp; ${assessDto.opeMajName}</td>
					<input type="hidden" name="backInvoiceAssess.opeMajName" value="${assessDto.opeMajName}">
			  	<td>日期：&nbsp; ${assessDto.opeMajDate}</td>
		  	</tr>
      </table>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    <img src="${ctx}/images/btnTG.gif" width="65" height="20" onclick="sub('pass')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="${ctx}/images/btnWTG.gif" width="65" height="20" onclick="sub('nopass')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    <img src="${ctx}/images/btnBack.gif" onclick="javascript:window.location='${ctx}/getBackInvoiceByUserId.do'"/>
    </td>
    <td></td>
  </tr>
</table>
<input type="hidden" id="status" value="${assessDto.status }"/>
</html:form>
</body>
</html>
