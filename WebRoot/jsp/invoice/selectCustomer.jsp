<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
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
	if($("input:checked").val()==null){alert("请选择客户");return;}//提示没有选择提示信息
	//var newJson=$("#customerInfoAll").text().replace(new RegExp("'","gm"),'‘');
	var customerInfo=${requestScope.customerInfo};
	for(var i=0;i<customerInfo.resultCount;i++){//循环找到所选客户的信息
		if($("input:checked").val()==customerInfo.rows[i].id){
			window.close();//关闭子窗口
			opener.getOpenerVal(customerInfo.rows[i]);//调父窗口的类，参数所选人的信息 类型为Object
		}
	}
});
});

</script>
<body>
<html:form action="findCustomerList.do" method="get">
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
            <th width="8%">选择</th>
            <th>客户名称</th>
          </tr>
         
          <logic:iterate id="customerList" name="customerList">
            <tr>
              <td width="50px" style=" text-align:left; padding-left:4px"><html:radio property="id" value="${customerList.id}" styleId="customerId"></html:radio>
              </td>
              <td width="700px">${customerList.name}</td>
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
            <td height="45px" colspan="2" align="center" valign="bottom"><a href="#" id="changeValue"><img src="${ctx}/images/btnChoice.gif" width="65" height="20" /></a> </td>
          </tr>
        </table></td>
    </tr>
    </c:if>
  </table>

  <div id="customerInfoAll" style="display:none"> </div>
</html:form>
</body>
</html>
