<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<title>用户信息管理</title>
<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
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
				if($("#xxxlist")){
					$("#xxxlist tr:even").addClass("treven");
					$("#xxxlist tr").each(function(i){
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
<script type="text/javascript" src="${ctx}/js/tad_bs2.js"></script>
<script type="text/javascript">
		var myArray=new Array();
		var mark1;
		var mark;
		
		function gotoPage(pageOffset, pageSize) {
			if (pageOffset < 0) {
				return;
			}
			var form = document.getElementsByTagName("Form")[1];
			form.elements["pager.offset"].value = pageOffset;
			form.elements["pageSize"].value = pageSize;
			form.submit();
		};
		function form0submit() {
			trimText();
			var form = document.getElementsByTagName("Form")[0];
			form.submit();
		};
		function che(a) {
   			var all=document.getElementsByName("cc");
    		if(a.checked==true){
       			 for(i=0;i<all.length;i++){
            		all[i].checked=true;
            		myArray.push(all[i].value);
       		 }
   			 }else if(a.checked==false){
        		for(i=0;i<all.length;i++){
            		all[i].checked=false;
        		}
        		myArray=new Array();
   	 		 }
		};
		function removeBefore(a) {
			for(var i=0;i<myArray.length;i++){
				if(myArray[i]==a.value){
					mark=0;
					mark1=i;
				}
			}
			if(mark!=0){
				myArray.push(a.value);
			}
			if(mark==0){
				myArray.splice(mark1,1);
			}
			var mark1=0;
			var mark=0;
		};
		function removeall() {
			
		}
		function remove() {
			//alert(myArray.length);
			if(myArray.length==0){
				alert("请选择删除项！");
			} else{
				if (confirm("是否确认删除？")) {
				
					var username = document.getElementsByName("username")[0].value;
					var name = document.getElementsByName("name")[0].value;
					var duty = document.getElementsByName("duty")[0].options[document.getElementsByName("duty")[0].selectedIndex].value;
					var regional = document.getElementsByName("regional")[0].options[document.getElementsByName("regional")[0].selectedIndex].value;
					var department = document.getElementsByName("department")[0].options[document.getElementsByName("department")[0].selectedIndex].value;
					var enable = document.getElementsByName("enable")[0].options[document.getElementsByName("enable")[0].selectedIndex].value;
					var online = document.getElementsByName("online")[0].options[document.getElementsByName("online")[0].selectedIndex].value;
					window.location = '${ctx}/searchUser.do?method=removeUser&id='+ myArray.join(',')+'&username='+username+'&name='+name+'&duty='+duty+'&regional='+regional+'&department='+department+'&enable='+enable+'&online='+online;
				}
			}
		}
		
		function checks(){
			var err = document.getElementById("err").value;
			if(err==null||err==""){
				
			} 
			else if(err!= null){
				alert(err);
				return false;
			}
		}
		function trimText(){
	        var fom = document.forms[0];
	        for(var i=0;i<fom.elements.length;i++){
	            if(fom.elements[i].type == 'text'){
	               fom.elements[i].value = $.trim(fom.elements[i].value);
	            }
	        }
	    }
</script>
</head>
<body style="overflow:auto" onload="checks();">
<input type="hidden" name="err" value="${err}" id="err"></input>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="ye_header_left" width="16px" ><div class=ellipsis_div style="width:16px;">&nbsp;</div></td>
    <td class="ye_header_center" style="font-size:12px">
    	<img src="${ctx}/images/main_jt.jpg" />&nbsp;&nbsp;当前位置： 基础信息管理 &gt;&gt; 用户信息管理
    </td>
    <td class="ye_header_right">&nbsp;</td>
  </tr>
   <tr>
    <td >&nbsp;</td>
    <td align="center">
    <div class="mo_wp" style="margin-top:12px;">
          <div style="display: ; " class="mo_con" >
          <form action="searchUser.do">
	      <input type="hidden" name="method" value="searchUser">
    	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
        	<tr>
            	<td class="td_01" width="20%">登录名</td>
                <td class="td_02" width="30%"><input type="text" name="username" value="${username}" style="width:120px;"  maxlength="20" /></td>
                <td class="td_01" width="20%">用户名</td>
                <td class="td_02" width="30%"><input type="text" name="name" value="${name}" style="width:120px;"  maxlength="4"/></td>
            </tr>
            <tr>
            	<td class="td_01">职务</td>
                <td class="td_02"><select name="duty" style=" width:126px">
                  <option value="">--请选择--</option>
	           		<logic:iterate id="li" name="dutySearch">
	           			<c:if test="${userSearchInfo.duty==li.name}">
	           				<option value="${li.name}" selected="selected">${li.name}</option>
	           			</c:if>
	           			<c:if test="${userSearchInfo.duty!=li.name}">
	           				<option value="${li.id}">${li.name}</option>
	           			</c:if>
	           		</logic:iterate>
                </select></td>
                <td class="td_01">人员区域</td>
                <td class="td_02"><select name="regional" style=" width:126px">
                  <option value="">--请选择--</option>
	                		<logic:iterate id="li" name="areaSearch">
	                			<c:if test="${userSearchInfo.regional==li.area_name}">
	                				<option value="${li.area_id}" selected="selected">${li.area_name}</option>
	                			</c:if>
	                			<c:if test="${userSearchInfo.regional!=li.area_name}">
	                				<option value="${li.area_id}">${li.area_name}</option>
	                			</c:if>
	                		</logic:iterate>
                </select></td>
            </tr>
            <tr>
            	<td class="td_01">人员部门</td>
                <td class="td_02"><select name="department" style=" width:126px">
                  <option value="">--请选择--</option>
	                		<logic:iterate id="li" name="deptSearch">
	                			<c:if test="${userSearchInfo.department==li.dept_name}">
	                				<option value="${li.dept_id}" selected="selected">${li.dept_name}</option>
	                			</c:if>
	                			<c:if test="${userSearchInfo.department!=li.dept_name}">
	                				<option value="${li.dept_id}">${li.dept_name}</option>
	                			</c:if>
	                		</logic:iterate>
                </select></td>
                <td class="td_01">使用状态</td>
                <td class="td_02"><select name="enable" style=" width:126px">
                  <option value="">--请选择--</option>
	                		<c:if test="${userSearchInfo.enable==1}">
	                			<option value="1" selected="selected">使用</option>
	                		</c:if>
	                		<c:if test="${userSearchInfo.enable!=1}">
	                			<option value="1">使用</option>
	                		</c:if>
	                		<c:if test="${userSearchInfo.enable==0}">
	                			<option value="0" selected="selected">停用</option>
	                		</c:if>
	                		<c:if test="${userSearchInfo.enable!=0}">
	                			<option value="0">停用</option>
	                		</c:if>
                </select></td>
            </tr>
            <tr>
            	<td class="td_01">在线状态</td>
                <td class="td_02"><select name="online" style=" width:126px">
                  <option value="">--请选择--</option>
	                		<c:if test="${userSearchInfo.online==1}">
		                		<option value="1" selected="selected">在线</option>
	                		</c:if>
	                		<c:if test="${userSearchInfo.online!=1}">
		                		<option value="1">在线</option>
	                		</c:if>
	                		<c:if test="${userSearchInfo.online==0}">
	                			<option value="0" selected="selected">离线</option>
	                		</c:if>
	                		<c:if test="${userSearchInfo.online!=0}">
	                			<option value="0">离线</option>
	                		</c:if>
                </select></td>
                <td class="td_01">&nbsp;</td>
                <td>&nbsp;</td>
            </tr>
            <tr><td colspan="4" align="center" height="30px"><a href="javascript:form0submit();"><img src="${ctx}/images/btn_JianSuo.gif" width="65" height="20" /></a></td>
            </tr>
        </table>
        </form>
         </div>
          <div class="mo_title" onclick="fMainListToggle(this)">
              <div style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
          </div>
        </div>
    </td>
    <td >&nbsp;</td>
  </tr>
  <tr>
    <td >&nbsp;</td>
    <td align="center">
    <br/>
    <form action="searchUser.do">
	    	<input type="hidden" name="method" value="searchUser">
	    	<input type="hidden" name="username" value="${userSearchInfo.username}">
	    	<input type="hidden" name="name" value="${userSearchInfo.name}">
	    	<input type="hidden" name="duty" value="${userSearchInfo.duty}">
	    	<input type="hidden" name="regional" value="${userSearchInfo.regional}">
	    	<input type="hidden" name="department" value="${userSearchInfo.department}">
	    	<input type="hidden" name="enable" value="${userSearchInfo.enable}">
	    	<input type="hidden" name="online" value="${userSearchInfo.online}">
    	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="xxxlist">
        	<tr>
        		<c:if test="${roleId==2}">	
            	<th nowrap="nowrap" width="41px">选择</th>
            	 </c:if>	
                <th nowrap="nowrap">登录名</th>
                <th nowrap="nowrap">用户名</th>
                <th nowrap="nowrap">职务</th>
                <th nowrap="nowrap">电话</th>
                <th nowrap="nowrap">手机</th>
                <th nowrap="nowrap">Email</th>
                <th nowrap="nowrap">MSN</th>
                <th nowrap="nowrap">QQ</th>
                <th nowrap="nowrap">人员部门</th>
                <th nowrap="nowrap">人员区域</th>
                <th nowrap="nowrap">使用状态</th>
                <th nowrap="nowrap">在线状态</th>
                <c:if test="${roleId==2}">
                <th nowrap="nowrap" width="70px">&nbsp;操作&nbsp;</th>
                </c:if><c:if test="${roleId==3||roleId==4||roleId==5||roleId==6||roleId==7||roleId==8||roleId==9||roleId==10||roleId==11||roleId==12||roleId==13||roleId==14||roleId==15||roleId==16||roleId==17||roleId==18||roleId==19||roleId==20}">	
                <th nowrap="nowrap" width="40px">&nbsp;操作&nbsp;</th>
                </c:if>	
            </tr>
            <logic:iterate id="listRange" name="listRange" property="records">
            <tr>
            	<c:if test="${roleId==2}">	
            	<td height="18px">&nbsp;<input name="cc" type="checkbox" value="${listRange.id}" onclick="javascript:removeBefore(this);"></td>
            	</c:if>	

                <td width="120px" nowrap="nowrap" height="18px">${listRange.id}&nbsp;</td>
                <td width="48px" nowrap="nowrap">${listRange.name}&nbsp;</td>
                <td width="96px" nowrap="nowrap">${listRange.role_name}&nbsp;</td>
                <td width="120px" nowrap="nowrap">${listRange.tel}&nbsp;</td>
                <td width="72px" nowrap="nowrap">${listRange.mobile}&nbsp;</td>
                <td width="80px" title="${listRange.mail}"><div class=ellipsis_div style="width:80px;">${listRange.mail}</div></td>
                <td width="80px" title="${listRange.msn}"><div class=ellipsis_div style="width:80px;">${listRange.msn}</div></td>
                <td width="72px" nowrap="nowrap">${listRange.qq}&nbsp;</td>
                <td width="96px" nowrap="nowrap">${listRange.dept_name}&nbsp;</td>
                <td width="96px" nowrap="nowrap">${listRange.area_name}&nbsp;</td>
                <td width="48px" nowrap="nowrap">

                			<c:if test="${listRange.enable==1}">
		                		使用
		                	</c:if>
		                	<c:if test="${listRange.enable==0}">
		                		停用
		                	</c:if>&nbsp;
		        </td>
                <td width="48px">
                			<c:if test="${listRange.online==1}">
		                		在线
		                	</c:if>
		                	<c:if test="${listRange.online==0}">
		                		离线
		                	</c:if>&nbsp;
		        </td>
		        <c:if test="${roleId==2}">
		        <td>
 
		        <a href="${ctx}/searchUser.do?method=viewUser&id=${listRange.id}&viewid=<c:if test="${listRange.role_id==1||listRange.role_id==7||listRange.role_id==15||listRange.role_id==17||listRange.role_id==18||listRange.role_id==2||listRange.role_id==13||listRange.role_id==16 || listRange.role_id==21}">1</c:if><c:if test="${listRange.role_id==5||listRange.role_id==6||listRange.role_id==8||listRange.role_id==10||listRange.role_id==11}">2</c:if><c:if test="${listRange.role_id==9}">3</c:if><c:if test="${listRange.role_id==4}">4</c:if><c:if test="${listRange.role_id==14}">5</c:if><c:if test="${listRange.role_id==3}">6</c:if><c:if test="${listRange.role_id==12}">7</c:if><c:if test="${listRange.role_id==19}">19</c:if><c:if test="${listRange.role_id==20}">20</c:if>">查看</a>
				<a href="${ctx}/searchUser.do?method=changeBeforeUser&id=${listRange.id}&viewid=<c:if test="${listRange.role_id==1||listRange.role_id==7||listRange.role_id==15||listRange.role_id==17||listRange.role_id==18||listRange.role_id==2||listRange.role_id==13||listRange.role_id==16 || listRange.role_id==21 }">1</c:if><c:if test="${listRange.role_id==5||listRange.role_id==8||listRange.role_id==10||listRange.role_id==6||listRange.role_id==11}">2</c:if><c:if test="${listRange.role_id==9}">3</c:if><c:if test="${listRange.role_id==4}">4</c:if><c:if test="${listRange.role_id==14}">5</c:if><c:if test="${listRange.role_id==3}">6</c:if><c:if test="${listRange.role_id==12}">7</c:if><c:if test="${listRange.role_id==19}">19</c:if><c:if test="${listRange.role_id==20}">20</c:if>">修改</a>
 
				</td>
		        </c:if>
		        <c:if test="${roleId==3||roleId==4||roleId==5||roleId==6||roleId==7||roleId==8||roleId==9||roleId==10||roleId==11||roleId==12||roleId==13||roleId==14||roleId==15||roleId==16||roleId==17||roleId==18||roleId==19||roleId==20}">
		        <td>
 
               	<a href="${ctx}/searchUser.do?method=viewUser&id=${listRange.id}&viewid=<c:if test="${listRange.role_id==1||listRange.role_id==7||listRange.role_id==15||listRange.role_id==17||listRange.role_id==18||listRange.role_id==2||listRange.role_id==13||listRange.role_id==16}">1</c:if><c:if test="${listRange.role_id==5||listRange.role_id==6||listRange.role_id==8||listRange.role_id==10||listRange.role_id==11}">2</c:if><c:if test="${listRange.role_id==9}">3</c:if><c:if test="${listRange.role_id==4}">4</c:if><c:if test="${listRange.role_id==14}">5</c:if><c:if test="${listRange.role_id==3}">6</c:if><c:if test="${listRange.role_id==12}">7</c:if><c:if test="${listRange.role_id==19}">19</c:if><c:if test="${listRange.role_id==20}">20</c:if>">查看</a>
 
                </td>
                </c:if>  
             </tr>
                 </tr>
           </logic:iterate>
        </table>
<br/>
		
        <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
        	<tr>
        	<c:if test="${roleId==2}">	
            	<td width="30px" align="left">&nbsp;&nbsp;<input name="ccc" type="checkbox" onclick="javascript:che(this);"></td>
                <td width="50px" style="font-size: 12px; text-align:center;">全选</td>
                <td width="100px"><a href="javascript:remove();"><img src="${ctx}/images/btnDelete.gif" /></a></td>
                <td width="100px"><a href="javascript:window.location = '${ctx}/searchUser.do?method=adduserBefore';"><img src="${ctx}/images/btnAdd.gif" /></a></td>
             </c:if>
                <td align="right" style="font-size:12px"><%@ include file="/jsp/common/page.jsp"%></td>
            </tr>
        </table>
        </form>
    </td>
    <td >&nbsp;</td>
  </tr>
</table>
 
</body>
</html>
