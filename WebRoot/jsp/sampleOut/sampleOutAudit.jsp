<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>样品借出单评审</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/js/messages_cn.js"></script>
        <script type="text/javascript" src="${ctx}/js/common/common.js"></script>
<style type="text/css">
<!--
.treven {
	background-color: #f3fbff;
}
.over {
	background-color: #ECECEC;
}
.STYLE1 {
	color: #FF0000
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
					});
				}
				
				
			//禁用回车事件，防止提交
				$("body").bind('keyup',function(event) {   
				if(event.keyCode==13){    
				return false;   
				}      
				});  
				//禁用回车事件，防止提交
 

       //通过
		$('#pass').click(function(){
			var textArea ;
			var CheckCount=0; 
			var checkbox = $(":checkbox[value='1']:checked:enabled").length;
			var nocheckbox = $(":checkbox[value='0']:checked:enabled").length;
      		if("${USERLOGIN.roleId}"==11){
				textArea = $('#buyManText').val();
				$(":checkbox:enabled").each(function(){
					if($(this).attr("checked")){
						CheckCount++; 
					} 
				});
				if(CheckCount != 1){
					alert("评审意见不可为空！");
					return false;
				}
				if(nocheckbox==0){
					$("#assessResult").val("pass");
				    sampleOutAssess.submit();
				}else{
					alert("评审意见必须为同意！");
					return false;
				}
			}
			if("${USERLOGIN.roleId}"==5){
				textArea = $('#sellMajText').val();
				$(":checkbox:enabled").each(function(){
					if($(this).attr("checked")){
						CheckCount++; 
					} 
				});
				if(CheckCount != 1){
					alert("评审意见不可为空！");
					return false;
				}
				if(nocheckbox==0){
					$("#assessResult").val("pass");
				    sampleOutAssess.submit();
				}else{
					alert("评审意见必须为同意！");
					return false;
				}
			}
			
		});

        //未通过
		$('#nopass').click(function(){
			var result = submitCheck();
			var textarea ="";
			if(result == false){
				return false;
			}
	
          if(${USERLOGIN.roleId=="11"}){
              textarea = document.getElementById('sampleOutAssess.buyManText').value;
            } 
          if(${USERLOGIN.roleId=="5"}){
             textarea = document.getElementById('sampleOutAssess.sellMajText').value;
            } 
            textarea=$.trim(textarea);    
          if(textarea == null || textarea ==""){
               alert("补充说明不可为空！");
				return false;			                       									
			}
			
			
			
			$("#assessResult").val("nopass");
			sampleOutAssess.submit();
		});
		
		//checkbox 单选
		$(":checkbox:enabled").each(function(i,assessItem){
			$(assessItem).bind("click", function() {
					if (assessItem.value == "1" ) {						
						$(":checkbox[name='" + assessItem.name + "'][value='0']").attr("checked", false);
					} else {						
						$(":checkbox[name='" + assessItem.name + "'][value='1']").attr("checked", false);
					}
			});//bind			
		});//each
		
		if("${USERLOGIN.roleId}"==11){
		       if('${assessDto.buyManIdea}'=='1'){
	        	$('#buyManIdea1').attr("checked",'true');
	        }
	        if('${assessDto.buyManIdea}'=='0'){
	        	$('#buyManIdea0').attr("checked",'true');
	        }
		}
		if("${USERLOGIN.roleId}"==5){
		       if('${assessDto.sellMajIdea}'=='1'){
	        	$('#sellMajIdea1').attr("checked",'true');
	        }
	        if('${assessDto.sellMajIdea}'=='0'){
	        	$('#sellMajIdea0').attr("checked",'true');
	        }
		}

});

		function submitCheck(){
			var result = true;
		    var textArea ;
		    var CheckCount=0; 
		
		   	if("${USERLOGIN.roleId}"==11){
				textArea = $('#buyManText').val();
				$(":checkbox:enabled").each(function(){
					if($(this).attr("checked")){
						CheckCount++; 
					} 
				});
				if(CheckCount != 1){
					alert("评审意见不可为空！");
					result = false;
				}
			}
			if("${USERLOGIN.roleId}"==5){
				textArea = $('#sellMajText').val();
				$(":checkbox:enabled").each(function(){
					if($(this).attr("checked")){
						CheckCount++; 
					} 
				});
				if(CheckCount != 1){
					alert("评审意见不可为空！");
					result = false;
				}
			}
		    return result ;
		}
		
		
		
		//计算归还金额合计
	function totleMoneyMethod(){
		var tableObj = document.getElementById("table");
		var tlength = tableObj.rows.length;
		var vtotle = 0;
		var tmptotle=0; 
		for(var i=1;i<tlength;i++){
		    var countid = "reCount"+i;
		    var priceid = "priceTax"+i;
		    var countobj= document.getElementById(countid);		  
		    var backcount =countobj.value;		    
		    var num =  $("#"+countid).val() ; //借出数量
		    
		    if(num==""){num =0};
		    var priceTax =   $("#"+priceid).val() ; //库存单价
		    tmptotle=FloatMul(num,priceTax);
		    vtotle = FloatAdd(vtotle,tmptotle);
		 } 
		$("#totalmoney").html(fmoney(vtotle,2)+'&nbsp;');
		$("#money").val(vtotle);
	}
	
	//金额合计的格式化s为要格式化的参数（浮点型），n为小数点后保留的位数	
	function fmoney(s,n){
		n = n>0 && n<=20 ? n : 2;
		s = parseFloat((s+"").replace(/[^\d\.-]/g,"")).toFixed(n)+"";
		var l = s.split(".")[0].split("").reverse(), 
		r = s.split(".")[1]; 
		t = "";
		for(i = 0;i<l.length;i++){
			t+=l[i]+((i+1)%3==0 && (i+1) != l.length ? "," : ""); 
		}
		return t.split("").reverse().join("")+"."+r;
	}	


$(document).ready(function(){
 	 totleMoneyMethod();
 	 } );
 	 
 	  $(function(){ 
		  //隐藏姓名和身份证号码 
		 if(${assessDto.transportWay== 1} && ${assessDto.companyType !=1}){ 
						  $("#nameAndId").show();  
					}else{
						$("#nameAndId").hide(); 
		} 
		//隐藏归还日期 	 
		if(${assessDto.type == 0}){
		  $("#tdindate").html('&nbsp');  
		$("#inDate").html('&nbsp'); 
		}  
		
	 });
	 
	   $(document).ready(function(){
	      var msg = "${msg}";  //获取服务端信息
		if(msg!=""){
		alert(msg);
		}
	 });
</script>
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
</head>
<body>
<html:form action="getSampleOutAssess.do" styleId="sampleOutAssess" >
<input type="hidden" id="assessResult" value="" name = "assessResult"/>
<input type="hidden" id="sampleOutAssess.id" value="${assessDto.id}" name="sampleOutAssess.id"/>
<input type="hidden" id="sampleOutAssess.outStockroomId" value="${assessDto.outStockroomId}" name="sampleOutAssess.outStockroomId"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 库存管理 &gt;&gt; 样品借出管理 &gt;&gt; 样品借出单评审</td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;借出单信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">借出单类型</td>
          <td class="td_02" width="30%">
                    <logic:equal value="0" property="type" name="assessDto">
            		          不归还
            	    </logic:equal>
                    <logic:equal value="1" property="type" name="assessDto">
            		          归还
            	    </logic:equal>
          &nbsp;</td>
          <td class="td_01" width="20%" id="tdindate">预计归还日期</td>
          <td class="td_02" width="30%" id="inDate">${assessDto.inDate}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">借出单位类型</td>
          <td  class="td_02" >
                    <logic:equal value="1" property="companyType" name="assessDto">
            		          公司
            	    </logic:equal>
                    <logic:equal value="2" property="companyType" name="assessDto">
            		          客户
            	    </logic:equal>
                    <logic:equal value="3" property="companyType" name="assessDto">
            		         供货商
            	    </logic:equal>
          &nbsp;</td>
          <td class="td_01">保管人</td>
          <td class="td_02" >${assessDto.custosName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">借出单位</td>
          <td class="td_02" >${assessDto.companyName}&nbsp;</td>
          <td class="td_01" height="18px">产品分类名称</td>
          <td class="td_02" >${assessDto.productTypeName}&nbsp;</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;产品信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%" height="18px">库房名称</td>
          <td class="td_04" >${assessDto.stockRoomName}&nbsp</td>
        </tr>
      </table>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table" >
        <tr>
          <th nowrap="nowrap">产品编码</th>
          <th nowrap="nowrap">产品名称</th>
          <th nowrap="nowrap">规格型号</th>
          <th nowrap="nowrap">单位</th>
          <th nowrap="nowrap">库存单价</th>
          <th width="98" nowrap="nowrap">借出数</th>
          <th nowrap="nowrap" width="96">&nbsp;&nbsp;&nbsp;借出金额&nbsp;&nbsp;&nbsp;</th>
          <th nowrap="nowrap" width="80">&nbsp;借出可用数&nbsp;</th>
        </tr>
        <logic:iterate id="sampleOutProductInfoDto" name="list" indexId="indexId">
        <tr>
          <td nowrap="nowrap" height="18px">${sampleOutProductInfoDto.code}&nbsp;</td>
          <td nowrap="nowrap">${sampleOutProductInfoDto.productName}&nbsp;</td>
          <td nowrap="nowrap" >${sampleOutProductInfoDto.type}&nbsp;</td>
          <td nowrap="nowrap" >${sampleOutProductInfoDto.unit}&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${sampleOutProductInfoDto.priceTax}" pattern="#,##0.00"/>&nbsp;</td>
          <td  width="98" nowrap="nowrap" style="text-align:right;">${sampleOutProductInfoDto.count}&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right;width:87px"><fmt:formatNumber value="${sampleOutProductInfoDto.money}" pattern="#,##0.00"/>&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right;width:72px">${sampleOutProductInfoDto.outUseCount}&nbsp;</td>
          <input type="hidden" id="reCount${indexId+1}" value="${sampleOutProductInfoDto.count}" name="counts"/>
          <input type="hidden"  id="priceTax${indexId+1}" value="${sampleOutProductInfoDto.priceTax}" name="priceTaxs"/>
          <input type="hidden" id="productIds" value="${sampleOutProductInfoDto.id}" name="productIds"/>
        </tr>
        </logic:iterate>
      </table>
      <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        <tr>
          <td height="25">&nbsp;</td>
          <td style=" text-align:right;">借出金额合计</td>
          <td style=" text-align:right;width:94px" id="totalmoney">&nbsp;</td>
          <td style="width:80px">&nbsp;元</td>
        </tr>
      </table>
      <div class="div_tiao"> &nbsp;&nbsp;发货信息</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" height="18px">货物接收单位名称</td>
          <td colspan="3" class="td_02" >${addressDto.goodsReceiveUnitName}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">发货地址</td>
          <td colspan="3"  class="td_02" >${addressDto.address}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">联系人</td>
          <td class="td_02" >${addressDto.linkman}&nbsp;</td>
          <td class="td_01">邮编</td>
          <td class="td_02" >${addressDto.postcode}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">电话</td>
          <td class="td_02" width="30%">${addressDto.tel}&nbsp;</td>
          <td class="td_01" width="20%">手机</td>
          <td class="td_02" width="30%">${addressDto.mobile}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" height="18px">要求发货日期</td>
          <td class="td_02" >${assessDto.requestDate}&nbsp;</td>
          <td class="td_01">货运方式</td>
          <td class="td_02" >
                    <logic:equal value="1" property="transportWay" name="assessDto">
            		          自提
            	    </logic:equal>
				    <logic:equal value="2" property="transportWay" name="assessDto">
            		           快递
            	    </logic:equal>
            	    <logic:equal value="3" property="transportWay" name="assessDto">
            		           汽运
            	    </logic:equal>
            	    <logic:equal value="4" property="transportWay" name="assessDto">
            		           铁运
            	    </logic:equal>
            	    <logic:equal value="5" property="transportWay" name="assessDto">
            		           航空
            	    </logic:equal>
                    <logic:equal value="6" property="transportWay" name="assessDto">
            		           海运
            	    </logic:equal>
            	    <logic:equal value="7" property="transportWay" name="assessDto">
            		           中铁
            	    </logic:equal>
            	    <logic:equal value="8" property="transportWay" name="assessDto">
            		           市内送货
            	    </logic:equal>
            	    <logic:equal value="9" property="transportWay" name="assessDto">
            		           市内快递
            	    </logic:equal>
          &nbsp;</td>
        </tr>
        <tr id="nameAndId">
          <td class="td_01" height="18px">提货人姓名</td>
          <td class="td_02" >${assessDto.takeName}&nbsp;</td>
          <td class="td_01">身份证号码</td>
          <td class="td_02" >${assessDto.takeNumber}&nbsp;</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%">特殊说明</td>
          <td class="td_04" style="padding:5px 100px 5px 5px">${tf:replaceBr(assessDto.text)}&nbsp;</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
      <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
      
      
      
      <c:if test="${USERLOGIN.roleId == 11}">
        <tr>
          <td height="20px" colspan="2" >采购主管评审意见：</td>
        </tr>
        <tr>
          <td width="320" nowrap="nowrap">&nbsp;</td>
          <td height="20px" width="150" nowrap="nowrap"><input type="checkbox" name="sampleOutAssess.buyManIdea" id="buyManIdea1" value="1" 
                    <c:if test='${assessDto.buyManIdea == "1"}'>checked="checked"</c:if> 
             />同意
            &nbsp;&nbsp;
            <input type="checkbox" name="sampleOutAssess.buyManIdea" id="buyManIdea0" value="0" 
                    <c:if test='${assessDto.buyManIdea == "0"}'>checked="checked"</c:if> 
            />不同意
          </td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                <textarea name="sampleOutAssess.buyManText" id="sampleOutAssess.buyManText" cols="60" rows="3">${assessDto.buyManText}</textarea>
                </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：</td>
          <td>日期：${assessDto.buyManDate}</td>
          <input type="hidden" name="sampleOutAssess.buyManDate"  value="${assessDto.buyManDate}"/>
        </tr>
        <tr>
          <td height="20px" colspan="2">&nbsp;</td>
        </tr> 
        </c:if> 
        <c:if test="${USERLOGIN.roleId == 5}">
        <tr>
          <td height="20px" colspan="2">销售总监评审意见：</td>
        </tr>
        <tr>
           <td width="320" nowrap="nowrap">&nbsp;</td>
          <td height="20px"><input type="checkbox" name="sampleOutAssess.sellMajIdea" id="sellMajIdea1" value="1" 
                  <c:if test='${assessDto.sellMajIdea == "1"}'>checked="checked"</c:if>
            /> 同意
            &nbsp;&nbsp;
            <input type="checkbox" name="sampleOutAssess.sellMajIdea" id="sellMajIdea0" value="0" 
                  <c:if test='${assessDto.sellMajIdea == "0"}'>checked="checked"</c:if> 
            /> 不同意
          </td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="62px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                <textarea name="sampleOutAssess.sellMajText" id="sampleOutAssess.sellMajText" cols="60" rows="3">${assessDto.sellMajText}</textarea>
                </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：</td>
          <td>日期：${assessDto.sellMajDate}</td>
          <input type="hidden" name="sampleOutAssess.sellMajDate"  value="${assessDto.sellMajDate}"/>
        </tr>
        
        <tr>
          <td height="20px" colspan="2">&nbsp;</td>
        </tr>
      </c:if>
             <c:set var="disabled" value="disabled" scope="page"></c:set>
		     <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,0,1)==0}">
             <c:set value="checked" var="productSpecUnpass" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,0,1)==1}">
             <c:set value="checked" var="productSpecPass" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,1,2)==0}">
             <c:set value="checked" var="productQualityUnpass" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,1,2)==1}">
             <c:set value="checked" var="productQualityPass" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,2,3)==0}">
             <c:set value="checked" var="productSumUnpass" scope="page"></c:set>
             </c:if>
             <c:if test="${assessDto.stkAdmIdea!=null && fn:substring(assessDto.stkAdmIdea,2,3)==1}">
             <c:set value="checked" var="productSumPass" scope="page"></c:set>
             </c:if> 
        <tr>
          <td height="20px" colspan="2">库房管理员核对意见：</td>
        </tr>
        <tr>
          <td>1.产品规格是否符合</td>
          <td height="20px">
          <input type="checkbox" id="productSpec1" value="1" name="productSpec" ${disabled } ${productSpecPass}/>符合&nbsp;&nbsp;
          <input type="checkbox" id="productSpec0" value="0" ${disabled } name="productSpec" ${productSpecUnpass}/>不符合
          </td>
        </tr>
        <tr>
          <td>2.产品质量是否符合</td>
          <td height="20px">
          <input type="checkbox" ${disabled } name="productQuality" id="productQuality1" value="1" ${productQualityPass}/>符合&nbsp;&nbsp;
          <input type="checkbox" name="productQuality" id="productQuality0" value="0" ${disabled } ${productQualityUnpass}/>不符合
          </td>
        </tr>
        <tr>
          <td>3.产品数量是否符合</td>
          <td height="20px">
           <input type="checkbox" ${disabled } value="1" id="productSum1" name="productSum" ${productSumPass}/>符合&nbsp;&nbsp;
           <input type="checkbox" ${disabled } value="0" id="productSum0" name="productSum" ${productSumUnpass}/>不符合
           </td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="62px" valign="top" style="padding:5px 0 5px 0;" nowrap="nowrap">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
               ${tf:replaceBr(assessDto.stkAdmText)}
                &nbsp;</td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${assessDto.stkAdmName}</td>
          <td>日期：${assessDto.stkAdmDate}</td>
        </tr>
    </table>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
     <td height="50px" align="center" valign="bottom">
        <img src="${ctx}/images/btnTG.gif" width="65" height="20" id="pass"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
        <img src="${ctx}/images/btnWTG.gif" width="65" height="20" id="nopass"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="${ctx}/getSampleOutList.do"><img src="${ctx}/images/btnBack.gif" /></a>    </td>
    <td></td>
  </tr>
</table>
<br />
</html:form>
</body>
</html>
