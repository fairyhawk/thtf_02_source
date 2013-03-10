<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>123</title>
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
				if($("#xxxlist")){
					$("#xxxlist tr:even").addClass("treven");
					$("#xxxlist tr").each(function(i){
						$(this).mouseover(function(){
							$(this).addClass("over");
						});
						$(this).mouseout(function(){
							$(this).removeClass("over");
						});
					});
				}
			});
		//-->
		</script>
	<script type="text/javascript" src="${ctx}/js/jquery-1.3.min.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.bgiframe.pack.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.jmesa.js"></script>
	<script type="text/javascript" src="${ctx}/js/jmesa.js"></script>
	<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/input.css" rel="stylesheet" type="text/css" />
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
				alert("删除失败！");
				return false;
			}
		}
	</script>
  </head>
  
  <body>
  <input type="hidden" name="err" value="${err}" id="err"></input>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td class="ye_header_left">&nbsp;</td>
		    <td class="ye_header_center">
		    	<img src="${ctx}/images/main_jt.jpg" />
		    	&nbsp;当前位置： 基础信息管理 &gt;&gt; 用户
		    </td>
	    <td class="ye_header_right">&nbsp;</td>
	  </tr>
	   <tr>
	    <td >&nbsp;</td>
	    <td align="center">
	    <br />
	    <div class="mo_wp">
	          <div style="display: ; " class="mo_con" >
	          <form action="searchUser.do">
	          <input type="hidden" name="method" value="searchUser">
	    	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
	        	<tr>
	            	<td class="td_01">登录名</td>
	                <td class="td_02" width="25%">
	                	<input type="text" name="username" value="${username}" maxlength="20">
	                </td>
	                <td class="td_01">用户名</td>
	                <td class="td_02" width="30%">
	                	<input type="text" name="name" value="${name}" maxlength="4">
	                </td>
	            </tr>
	            <tr>
	            	<td class="td_01">职务</td>
	                <td class="td_02">
	                	<select name="duty">
	                		<option value="">无</option>
	                		<logic:iterate id="li" name="dutySearch">
	                			<c:if test="${userSearchInfo.duty==li.name}">
	                				<option value="${li.name}" selected="selected">${li.name}</option>
	                			</c:if>
	                			<c:if test="${userSearchInfo.duty!=li.name}">
	                				<option value="${li.name}">${li.name}</option>
	                			</c:if>
	                		</logic:iterate>
	                	</select>
	                </td>
	                <td class="td_01">人员区域</td>
	                <td class="td_02">
	                	<select name="regional">
	                		<option value="">无</option>
	                		<logic:iterate id="li" name="areaSearch">
	                			<c:if test="${userSearchInfo.regional==li.area_name}">
	                				<option value="${li.area_name}" selected="selected">${li.area_name}</option>
	                			</c:if>
	                			<c:if test="${userSearchInfo.regional!=li.area_name}">
	                				<option value="${li.area_name}">${li.area_name}</option>
	                			</c:if>
	                		</logic:iterate>
	                	</select>
	                </td>
	            </tr>
	            <tr>
	            	<td class="td_01">人员部门</td>
	                <td class="td_02">
	                	<select name="department">
	                		<option value="">无</option>
	                		<logic:iterate id="li" name="deptSearch">
	                			<c:if test="${userSearchInfo.department==li.dept_name}">
	                				<option value="${li.dept_name}" selected="selected">${li.dept_name}</option>
	                			</c:if>
	                			<c:if test="${userSearchInfo.department!=li.dept_name}">
	                				<option value="${li.dept_name}">${li.dept_name}</option>
	                			</c:if>
	                		</logic:iterate>
	                	</select>
	                </td>
	                <td class="td_01">使用状态</td>
	                <td class="td_02">
	                	<select name="enable">
	                		<option value="">无</option>
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
	                	</select>
	                </td>
	            </tr>
	            <tr>
	            	<td class="td_01">在线状态</td>
	                <td class="td_02">
	                	<select name="online">
	                		<option value="">无</option>
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
	                	</select>
	                </td>
	                <td class="td_01">&nbsp;</td>
	                <td>&nbsp;</td>
	            </tr>
	            <tr><td colspan="4" align="center" height="30px"><a href="javascript:form0submit();"><img src="${ctx}/images/btn_JianSuo.gif" width="65" height="20" /></a></td>
	            </tr>
	        </table>
	        </form>
	         </div>
	          <div class="mo_title" onClick="fMainListToggle(this)">
	              <div  style="text-align:center"><img id="imgShang" src="${ctx}/images/shang_sj.png" /></div>
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
	            	<th nowrap="nowrap" width="50px">选择</th>
	                <th nowrap="nowrap">登录名</th>
	                <th nowrap="nowrap">用户名</th>
	                <th nowrap="nowrap">电话</th>
	                <th nowrap="nowrap">手机</th>
	                <th nowrap="nowrap">职务</th>
	                <th nowrap="nowrap">人员部门</th>
	                <th nowrap="nowrap">人员区域</th>
	                <th nowrap="nowrap">使用状态</th>
	                <th nowrap="nowrap">在线状态</th>
	                <th nowrap="nowrap" width="80px">&nbsp;操作&nbsp;</th>
	            </tr>
	            <logic:iterate id="listRange" name="listRange" property="records">
		            <tr>
		            	<td><input name="cc" type="checkbox" value="${listRange.id}" onClick="javascript:removeBefore(this);"></td>
		                <td>${listRange.id}</td>
		                <td>${listRange.name}</td>
		                <td>${listRange.tel}</td>
		                <td>${listRange.mobile}</td>
		                <td>${listRange.role_name}</td>
		                <td>${listRange.dept_name}</td>
		                <td>${listRange.area_name}</td>
		                <td>
		                	<c:if test="${listRange.enable==1}">
		                		使用
		                	</c:if>
		                	<c:if test="${listRange.enable==0}">
		                		停用
		                	</c:if>
		                </td>
		                <td>
		                	<c:if test="${listRange.online==1}">
		                		在线
		                	</c:if>
		                	<c:if test="${listRange.online==0}">
		                		离线
		                	</c:if>
		                </td>
		                <td>
		                <c:if test=""></c:if>
		                <a href="${ctx}/searchUser.do?method=viewUser&id=${listRange.id}&viewid=<c:if test="${listRange.role_id==1||listRange.role_id==6||listRange.role_id==7||listRange.role_id==11||listRange.role_id==15||listRange.role_id==17||listRange.role_id==18||listRange.role_id==2||listRange.role_id==13||listRange.role_id==16}">1</c:if><c:if test="${listRange.role_id==5||listRange.role_id==8||listRange.role_id==10}">2</c:if><c:if test="${listRange.role_id==9||listRange.role_id==12}">3</c:if><c:if test="${listRange.role_id==4}">4</c:if><c:if test="${listRange.role_id==14}">5</c:if><c:if test="${listRange.role_id==3}">6</c:if>">查看</a>&nbsp;<a href="${ctx}/searchUser.do?method=changeBeforeUser&id=${listRange.id}&viewid=<c:if test="${listRange.role_id==1||listRange.role_id==7||listRange.role_id==15||listRange.role_id==17||listRange.role_id==18||listRange.role_id==2||listRange.role_id==13||listRange.role_id==16}">1</c:if><c:if test="${listRange.role_id==5||listRange.role_id==8||listRange.role_id==10||listRange.role_id==6||listRange.role_id==11}">2</c:if><c:if test="${listRange.role_id==9||listRange.role_id==12}">3</c:if><c:if test="${listRange.role_id==4}">4</c:if><c:if test="${listRange.role_id==14}">5</c:if><c:if test="${listRange.role_id==3}">6</c:if>">修改</a>
		                </td>
		            </tr>
	            </logic:iterate>
	        </table>
	        
	<br>
	        <table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
	        	<tr>
	            	<td width="50px" align="center"><input name="ccc" type="checkbox" onClick="javascript:che(this);"/></td>
	                <td width="50px">全选</td>
	                <td width="100px"><a href="javascript:remove();"><img src="${ctx}/images/btnDelete.gif"/></a></td>
	                <td width="100px"><a href="javascript:window.location = '${ctx}/searchUser.do?method=adduserBefore';"><img src="${ctx}/images/btnAdd.gif" /></a></td>
					<td align="right">
					<%@ include file="/jsp/common/page.jsp"%>
                    </td>
	            </tr>
	        </table>
	        </form>
	    </td>
	    <td >&nbsp;</td>
	  </tr>
	</table>
  </body>
</html>
