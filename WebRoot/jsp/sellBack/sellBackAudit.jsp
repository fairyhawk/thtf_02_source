<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tf" uri="localhost" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>退款评审</title>
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
		<!--
			$(document).ready(function(){
				if($("#table")){
					$("#table tr:odd").addClass("treven");
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
		//-->
		</script>
		<script type="text/javascript">
		       
		       function sub(assessResult){
		             
		              $("#assessResult").attr("value",assessResult);
		                 //根据当前用户的权限用不同的复选框
		                if(${USERLOGIN.roleId=="5"}){
		                  var checkBoxObj = document.getElementsByName('sellBackAssess.sellMajIdea');
		                } else if(${USERLOGIN.roleId=="17"}){
		                  var checkBoxObj = document.getElementsByName('sellBackAssess.opeMajIdea');
		                }else{
		                  alert("非权限范围内提交!");
		                  return false; 
		                }
		               //点了通过时。必须选中同意复选框 
		              if(assessResult=="pass"){
		                if(checkBoxObj[0].checked){
		                     $("#sellBackAssess").submit();
		                  }else{
		                     alert("要通过评审，评审意见必须选择同意!");
		                  }
		                } 
		                 //点了不通过时
		                if(assessResult=="nopass"){
		                  
		                   if((checkBoxObj[0].checked)||(checkBoxObj[1].checked)){
		                      var textarea ="";
		                      if(${USERLOGIN.roleId=="5"}){
		                          textarea = document.getElementById('sellBackAssess.sellMajText').value;
			                    }
			                  if(${USERLOGIN.roleId=="17"}){
			                      textarea = document.getElementById('sellBackAssess.opeMajText').value;       
			                    }
			                    textarea=$.trim(textarea);   
			                  if(textarea == null || textarea ==""){
			                       alert("补充说明不可为空！");
									return false;			                       									
								}
								
							$("#sellBackAssess").submit();
		                   }else{
		                       alert("请选择评审意见!");
		                   }
						
		                   
		                }
		       }
		
		       //销售总监复选框操作   
		       function sellMajCheck(flag){
		       
		          var checkBoxObj = document.getElementsByName('sellBackAssess.sellMajIdea');
		          //点同意
		          if(flag=="1"){
		             checkBoxObj[1].checked=false;
		          }
		          //点不同意
		          if(flag=="0"){
		             checkBoxObj[0].checked=false;
		          }
		          
		       }
		       
		       //运营总监复选框操作  
		      function opeMajCheck(flag){
		       
		          var checkBoxObj = document.getElementsByName('sellBackAssess.opeMajIdea');
		          //点同意
		          if(flag=="1"){
		             checkBoxObj[1].checked=false;
		          }
		          //点不同意
		          if(flag=="0"){
		             checkBoxObj[0].checked=false;
		          }
		          
		       }
		       
		        //画面初始化时。可用状态设置
		      $(document).ready(		      
		        function(){
		           var sellcheckBoxObj = document.getElementsByName('sellBackAssess.sellMajIdea'); 
		           var opecheckBoxObj = document.getElementsByName('sellBackAssess.opeMajIdea');
		        
		       		 //销售总监评审时，运营总监不能评审
		       		 if(${USERLOGIN.roleId=="5"}){
		        	 	  opecheckBoxObj[0].disabled="disabled";
		          		  opecheckBoxObj[1].disabled="disabled";
		        	      document.getElementById('sellBackAssess.opeMajText').disabled="disabled";
		                   $('#opeMajName').text('签字：');
		        	 }
		      		 //运营总监评审时，销售总监不能评审
		        	 if(${USERLOGIN.roleId=="17"}){
		          		  sellcheckBoxObj[0].disabled="disabled";
		          		  sellcheckBoxObj[1].disabled="disabled";
		          		  document.getElementById('sellBackAssess.sellMajText').disabled="disabled";	
		       		  }		        
		      	 }		      
		      );
		      
		      $(document).ready(function(){
				      var msg = "${msg}";  //获取服务端信息
					if(msg!=""){
					alert(msg);
					}
				 });		
		</script>

</head>
<body>
<html:form action="getSellBackAssess.do" styleId="sellBackAssess">
<input type="hidden" id="assessResult" value="${assessResult}" name = "assessResult"/>
<input type="hidden" id="sellBackAssess.id" value="${assessDto.id}" name="sellBackAssess.id"/>
<input type="hidden" id="sellBackAssess.opeMajId" value="${assessDto.opeMajId}" name="sellBackAssess.opeMajId"/>
<input type="hidden" id="sellBackAssess.confirmId" value="${assessDto.confirmId}" name="sellBackAssess.confirmId"/>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left">&nbsp;</td>
    <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" />&nbsp; 当前位置： 销售管理 &gt;&gt 销售退款管理 &gt;&gt; 退款评审 </td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td><br />
      <div class="div_tiao"> &nbsp;&nbsp;客户信息  </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">客户名称</td>
          <td class="td_02" width="30%">${assessDto.customerName}&nbsp;</td>
          <td class="td_01" width="20%">联系人</td>
          <td class="td_02" width="30%">${assessDto.name}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">省份</td>
          <td class="td_02" width="30%">${assessDto.province}&nbsp;</td>
          <td class="td_01" width="20%">电话</td>
          <td class="td_02" width="30%">${assessDto.tel}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">城市</td>
          <td class="td_02" width="30%">${assessDto.city}&nbsp;</td>
          <td class="td_01" width="20%">传真</td>
          <td class="td_02" width="30%">${assessDto.fax}&nbsp;</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">汇款银行名称</td>
          <td class="td_02" width="30%">${assessDto.remitBankName}&nbsp;</td>
          <td class="td_01" width="20%">汇款银行帐号</td>
          <td class="td_02" width="30%">${assessDto.remitBankAccount}&nbsp;</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;回款信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">回款金额</td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${sellBackDto.mreturnMoney}" pattern="#,##0.00"/>&nbsp;元</td>
          <td class="td_01" width="20%">指定金额</td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${sellBackDto.detailMoney}" pattern="#,##0.00"/>&nbsp;元</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">合同预收金额</td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${sellBackDto.contMoney}" pattern="#,##0.00"/>&nbsp;元</td>
          <td class="td_01" width="20%">产品分类预收金额</td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${sellBackDto.prodMoney}" pattern="#,##0.00"/>&nbsp;元</td>
        </tr>
        <tr>
          <td class="td_01" width="20%" height="18px">已退款金额</td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${sellBackDto.backSuccessmoney}" pattern="#,##0.00"/>&nbsp;元</td>
          <td class="td_01" width="20%">回款可退款金额</td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${sellBackDto.mreturnCanBackMoney}" pattern="#,##0.00"/>&nbsp;元</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;产品分类信用信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">产品分类预收金额合计</td>
          
          <td class="td_02" width="30%"><fmt:formatNumber value="${sellBackDto.prodMoneySum}" pattern="#,##0.00"/>&nbsp;元</td>
          <td class="td_01" width="20%">产品分类可退款金额</td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${sellBackDto.proCanBackMoney}" pattern="#,##0.00"/>&nbsp;元</td>
        </tr>
      </table>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
        <tr>
          <th nowrap="nowrap" >产品分类名称</th>
          <th nowrap="nowrap" >信用类型名称</th>
          <th nowrap="nowrap">项目名称</th>
          <th nowrap="nowrap">帐期</th>
          <th nowrap="nowrap">信用额度</th>
          <th nowrap="nowrap">已用金额</th>
        </tr>
        <logic:iterate id="customerProInfoListDto" name="customerProInfoListDtos" >
        <tr>
          <td nowrap="nowrap" height="18px">${customerProInfoListDto.productName}&nbsp;</td>
          <td nowrap="nowrap" >${customerProInfoListDto.creditTypeName}&nbsp;</td>
          <td nowrap="nowrap" >${customerProInfoListDto.projectName}&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right;">${customerProInfoListDto.arterm}&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${customerProInfoListDto.climit}" pattern="#,##0.00"/>&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${customerProInfoListDto.canUseLimit}" pattern="#,##0.00"/>&nbsp;</td>
        </tr>
        </logic:iterate>
        
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;客户信用信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">&nbsp;</td>
          <td class="td_02" width="30%">&nbsp;</td>
          <td class="td_01" width="20%">客户可退款金额</td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${customerCanBackMoney}" pattern="#,##0.00"/>&nbsp;元</td>
        </tr>
      </table>
      <div style="width:100%; text-align:right">单位：元</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table">
        <tr>
          <th nowrap="nowrap" >产品分类名称</th>
          <th nowrap="nowrap" >产品分类预收金额</th>
          <th nowrap="nowrap" >已用信用</th>
        </tr>
        <logic:iterate id="customerCreditDto" name="customerCreditList" >
        <tr>
          <td nowrap="nowrap" height="18px">${customerCreditDto.customerName}&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${customerCreditDto.prodMoney}" pattern="#,##0.00"/>&nbsp;</td>
          <td nowrap="nowrap" style="text-align:right;"><fmt:formatNumber value="${customerCreditDto.mreturnCanBackMoney}" pattern="#,##0.00"/>&nbsp;</td>
        </tr>
        </logic:iterate>
      </table>
      
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;退款信息 </div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_01" width="20%" height="18px">退款方式</td>
          <td class="td_02" width="30%">
                    <logic:equal value="7" property="backWay" name="assessDto">
            		            其他
            	    </logic:equal>
				    <logic:equal value="2" property="backWay" name="assessDto">
            		            支票
            	    </logic:equal>
            	    <logic:equal value="3" property="backWay" name="assessDto">
            		           网银
            	    </logic:equal>
            	    <logic:equal value="4" property="backWay" name="assessDto">
            		           电汇
            	    </logic:equal>
            	    <logic:equal value="5" property="backWay" name="assessDto">
            		           银行承兑
            	    </logic:equal>
          &nbsp;
          </td>
          <td class="td_01" width="20%">退款金额</td>
          <td class="td_02" width="30%"><fmt:formatNumber value="${assessDto.money}" pattern="#,##0.00"/>&nbsp;元</td>
        </tr>
      </table>
      <br/>
      <div class="div_tiao"> &nbsp;&nbsp;特殊说明</div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        <tr>
          <td class="td_03" width="20%">特殊说明</td>
          <td class="td_04" style="padding:5px 100px 5px 5px">
          ${tf:replaceBr(assessDto.text)}          
          &nbsp;</td>
        </tr>
      </table>
      <br />
      <div class="div_tiao"> &nbsp;&nbsp;评审意见</div>
      <table border="0"  cellpadding="0" cellspacing="0" align="center" width="460">
        <tr>
          <td height="18px" colspan="2" >销售总监评审意见：</td>
        </tr>
        <tr>
          <td width="320">&nbsp;</td>
          <td width="150" height="20px"><input type="checkbox" name="sellBackAssess.sellMajIdea" id="sellBackAssess.sellMajIdea" value="1" onclick="sellMajCheck(1)"
              <c:if test='${assessDto.sellMajIdea == "1"}'>checked="checked"</c:if>  
          />同意
            &nbsp;&nbsp;
              <input type="checkbox" name="sellBackAssess.sellMajIdea" id="sellBackAssess.sellMajIdea" value="0" onclick="sellMajCheck(0)"
              <c:if test='${assessDto.sellMajIdea == "0"}'>checked="checked"</c:if>  
            />不同意
            </td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                <textarea name="sellBackAssess.sellMajText" id="sellBackAssess.sellMajText" cols="60" rows="3">${assessDto.sellMajText}</textarea>
                </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px">签字：${assessDto.sellMajName}</td>
          <input type="hidden" name="sellBackAssess.sellMajName"  value="${assessDto.sellMajName}"/>
          <td>日期：${assessDto.sellMajDate}</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">&nbsp;</td>
        </tr>
        <tr>
          <td height="20px" colspan="2">运营总监评审意见：</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td height="20px"><input type="checkbox" name="sellBackAssess.opeMajIdea" id="sellBackAssess.opeMajIdea" value="1" onclick="opeMajCheck(1)"
              <c:if test='${assessDto.opeMajIdea == "1"}'>checked="checked"</c:if>  
            />同意
            &nbsp;&nbsp;
            <input type="checkbox" name="sellBackAssess.opeMajIdea" id="sellBackAssess.opeMajIdea" value="0" onclick="opeMajCheck(0)"
              <c:if test='${assessDto.opeMajIdea == "0"}'>checked="checked"</c:if> 
            />不同意
            </td>
        </tr>
        <tr>
          <td colspan="2" valign="top"><table width="98%" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="60px" valign="top" style="padding:5px 0 5px 0;">补充说明：</td>
                <td style="padding:5px 0 5px 0;">
                  <textarea name="sellBackAssess.opeMajText" id="sellBackAssess.opeMajText" cols="60" rows="3" >${assessDto.opeMajText}</textarea>
                </td>
              </tr>
            </table></td>
        </tr>
        <tr>
          <td height="20px" id="opeMajName">签字：${assessDto.opeMajName}</td>
          <input type="hidden" name="sellBackAssess.opeMajName" id="sellBackAssess.opeMajName"  value="${assessDto.opeMajName}"/>
          <td>日期：${assessDto.opeMajDate}</td>
        </tr>
      </table></td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td></td>
    <td height="50px" align="center" valign="bottom">
    <img src="${ctx}/images/btnTG.gif" width="65" height="20" onclick="sub('pass')"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <img src="${ctx}/images/btnWTG.gif" width="65" height="20" onclick="sub('nopass')"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
    <img src="${ctx}/images/btnBack.gif" onclick="javascript:window.location='${ctx}/getSellBackList.do'"/></td>
    <td></td>
  </tr>
</table>
</html:form>
</body>
</html>
