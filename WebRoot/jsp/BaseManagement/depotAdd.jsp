<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>库房添加</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../..js/tad_bs.js"></script>
<script type="text/javascript" src="${ctx}/js/util.js"></script>
<script type="text/javascript">
		function document.onkeydown(){                 
		     if(event.keyCode==13){
		         event.keyCode=0;  
		         return false;                               
		    }
		} 
		var formId = 0;
		//控件名	
		var checknNames = ["depot.name","depot.productDeptId"];
		//提示语
		var descriptions = [ "库房名称","产品部门"];
		//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
		var checkNulls = [ "notnull","notnull"];
		//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
		var checkTypes = [ "",""];
		//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
		var checkLengths = [ "40",""];
		function check(){
		var name = document.getElementById("name").value;
		var type = document.getElementById("depot.type").value;
		var productDeptId = document.getElementById("productDeptId").value;
		if(productDeptId==null||productDeptId==""){
			alert("请选择产品部门名称！");
			return;
		}
		if((name==null||name=="")&&(type==1||type==2)){
			alert("库房名称不可以为空！");
			return ;
		}
		if(type==1&&checkForm()==false){
			return ;
			}
			document.forms[0].submit();
		};
		
		function checks(){
			var err = document.getElementById("err").value;
			if(err==null||err==""){
				
			} 
			else if(err!= null){
				alert(err);
				}
		}
		//根据选择的库房类型，显示区域和库房名的显示情况
	function selectType(objVal){
	   if(objVal==0){
	     document.getElementById("selectTypeId0").style.display="none";
	     document.getElementById("selectTypeId1").style.display="none";
	   }else if(objVal==1){
	      document.getElementById("selectTypeId1").style.display="block";
	      document.getElementById("selectTypeId0").style.display="none";
	   }else{
	   	  document.getElementById("selectTypeId1").style.display="block";
	      document.getElementById("selectTypeId0").style.display="none";
	   }
	}
	
	function selectDept(name)
        { 
         var typeId = document.getElementById("depot.type").value;
         if(typeId==0){
         var deptName=name.options[name.selectedIndex].text; 
         document.getElementById("deptAndName").innerHTML=deptName+"虚拟库";
         }
        }
	</script>
</head>
<body onload="checks();">
<html:form action="/depot" method="post">
  <input type="hidden" name="method" value="addDepot" />
  <input type="hidden" name="err" value="${err}" id="err">
  </input>
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
      <td class="ye_header_left">&nbsp;</td>
      <td class="ye_header_center"><img src="${ctx}/images/main_jt.jpg" /> &nbsp;当前位置： 基础信息管理 &gt;&gt; 库房信息管理 &gt;&gt; 库房添加 </td>
      <td class="ye_header_right">&nbsp;</td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td align="center"><br />
        <table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="biao3">
          <tr>
            <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;库房类型 </td>
            <td class="td_02" width="30%"><select name="depot.type" id="depot.type" style=" width:126px"
										onchange="selectType(this.value);">
                <option value="0"> 虚拟库 </option>
                <option value="1" selected="selected"> 正常库 </option>
                <option value="2"> 索赔库 </option>
              </select>
            </td>
            <td class="td_01" width="20%"><span style="color:#FF0000">*</span>&nbsp;产品部门名称</td>
            <td class="td_02" width="30%"><select name="depot.productDeptId" id="productDeptId"
										style=" width:126px" onchange="selectDept(this);">
                <option value="" selected="selected"> --请选择-- </option>
                <logic:present name="productDeptList">
                  <logic:iterate id="productDept" name="productDeptList">
                    <option value="${productDept.id}"> ${productDept.name} </option>
                  </logic:iterate>
                </logic:present>
              </select>
            </td>
          </tr>
          <tr style="display:block;" id="selectTypeId1">
            <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;库房名称 </td>
            <td class="td_02"><input type="text" name="depot.name" id="name" maxlength="20"
										style="width:240px;">
              </input>
            </td>
            <td class="td_01"></td>
            <td class="td_02"></td>
          </tr>
          <tr style="display:none;" id="selectTypeId0">
            <td class="td_01"><span style="color:#FF0000">*</span>&nbsp;库房名称 </td>
            <td class="td_02" id="deptAndName"></td>
            <td class="td_01"></td>
            <td class="td_02"></td>
          </tr>
        </table>
        <table align="center">
          <tr>
            <td height="50px" valign="bottom"><a href="javascript:check();"><img src="${ctx}/images/btnAdd.gif" /></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="javascript:window.location='${ctx}/depot.do?method=depotAll';"> <img src="${ctx}/images/btnBack.gif" width="65" height="20" /></a> </td>
          </tr>
        </table></td>
      <td>&nbsp;</td>
    </tr>
  </table>
</html:form>
</body>
</html>
