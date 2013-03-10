<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/jsp/common/taglibs.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<title>客户选择</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery-1.4.2.min.js"></script>
<style>
.treven {
	background-color: #f3fbff;
}
.over {
	background-color: #ECECEC;
</style>
</head>
<script type="text/javascript">
/*
检索人名提交

*/
function selectCustomer_submit(){
	if($("#name").val()==null){alert("请输入客户名字");return false;}
	document.forms[0].submit();
	return true;
}

$(document).ready(function(){
if($("#table1")){
			$("#table1 tr:even").addClass("treven");
			$("#table1 tr").each(function(i){
				$(this).mouseover(function(){
					$(this).addClass("over");
				});
				$(this).mouseout(function(){
					$(this).removeClass("over");
				});
			});
		}
		$("#changeValue").click(function(){//点击选择客户
			//提示没有选择提示信息
			if($("input:checked").val()==null){alert("请选择客户");return;}
			
			//给父页面的值赋值,父页面要有companyId
			var arrAddress = new Array();
			arrAddress  = $("input:checked").val().split("#");
			var companyId =arrAddress[0];
			var companyName=arrAddress[1];
			window.opener.document.getElementById("companyId").value = companyId;
			 
			$("#disPlayName",window.opener.document).html(companyName);
			//关闭子窗口
			window.close();
			 
		});
		});

</script>

<body>
<html:form action="getSampleOutCoustomerSelectByName.do" method="post">
  <table width="90%" border="0" cellpadding="0" cellspacing="0" align="center">
    <tr>
      <td align="center"><br />
        <div class="mo_wp">
          <div style="display: ; " class="mo_con" >
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
              <tr>
                <td class="td_03" width="50%" align="right">客户名称： </td>
                <td class="td_04" ><html:text property="name" styleId="name"></html:text>
                </td>
              </tr>
              <tr>
                <td colspan="2" align="center" style="height:30px;"><a href="#" onClick="return selectCustomer_submit()"><img src="${ctx}/images/btn_JianSuo.gif" /></a></td>
              </tr>
            </table>
          </div>
          <div class="mo_title" onClick="fMainListToggle(this)">
            <div  style="text-align:center"><img src="${ctx}/images/shang_sj.png" /></div>
          </div>
        </div></td>
    </tr>
    <c:if test="${requestScope.customerSize!='0'}">
    <tr>
      <td align="center"><br/>
       
        <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="table1">
          <tr>
            <th>选择</th>
            <th>客户名称</th>
          </tr>
         
          <logic:iterate id="customerList" name="customerList" indexId="indexId">
            <tr>
              <td width="24px" style=" text-align:left; padding-left:4px">
              	<input type="radio" name="id" id="customerId" value="${customerList.id}#${customerList.name}">
              </td>
              <td width=>${customerList.name}&nbsp;</td>
            </tr>
          </logic:iterate>
        </table>
          
        </td>
    </tr>
    <tr>
      <td id="div1" align="right" height="30px">
          <%@ include file="/jsp/common/newPage.jsp"%>
      </td>
    </tr>
    <tr>
      <td><table align="center">
          <tr>
            <td height="30px" colspan="2" align="center" valign="bottom"><a href="#" id="changeValue"><img src="${ctx}/images/btnChoice.gif" width="65" height="20" /></a> </td>
          </tr>
        </table></td>
    </tr>
    </c:if>
  </table>
<br />
  <div id="customerInfoAll" style="display:none"> ${requestScope.customerInfo} </div>
</html:form>
</body>
</html>
