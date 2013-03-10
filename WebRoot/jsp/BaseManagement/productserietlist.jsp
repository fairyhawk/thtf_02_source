<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>产品列表</title>
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
					$("#xxxlist tr:odd").addClass("treven");
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
		<script type="text/javascript" src="${ctx}/js/base.js"></script>
		<script type="text/javascript" src="${ctx}/js/util.js"></script>
		<script type="text/javascript">
		var myArray=new Array();
		var mark1;
		var mark;
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
		function shan(){
		if(myArray.length==0){
				alert("请选择删除项！");
			}else if (confirm("是否确认删除？")) {
					var num = document.getElementsByName("productserie.num")[0].value;
					var name = document.getElementsByName("productserie.name")[0].value;
					var tname = document.getElementsByName("productserie.tname")[0].options[document.getElementsByName("productserie.tname")[0].selectedIndex].value;
					window.location = '${ctx}/productseriemanagement.do?method=deleteProductSerie&id='+ myArray.join(',')+'&num='+num+'&name='+name+'&tname='+tname;
			}else{
			}
		};
		function gotoPage(pageOffset, pageSize) {
			if (pageOffset < 0) {
				return;
			}
			var form = document.getElementsByTagName("Form")[1];
			form.elements["pager.offset"].value = pageOffset;
			form.elements["pageSize"].value = pageSize;
			form.submit();
		};
		
		var formId = 0;
		//控件名
		var checknNames = [ "productserie.tname", "productserie.num", "productserie.name"];
		//提示语
		var descriptions = ["产品分类","产品系列编码","产品系列名称"];
		//是否非空验证,如果非空验证填写notnull,如果不需要非空验证传空参
		var checkNulls = [ "null", "notspace", "notspace"];
		//判断类型验证，只填数字验证填"num",    只填字母验证“abc”,    email验证“email”，非零开头的数字和零 "0num",  邮箱验证 "zip" ,登录名验证  “loginname” ,电话 “phone”
		var checkTypes = ["", "", ""];
		//判断长度验证 如果判断是否等于  参数前面加0, 否则正常  不需要长度验证则传空参
		var checkLengths = ["", "", "24"];
		function form0submit() {
		if(checkForm()==false){
			 return ;
			  }
			var form = document.getElementsByTagName("Form")[0];
			if($("#productcode").val()!='' && $.trim($("#productcode").val()).length < 6){alert("产品系列编码不正确");return;}
			form.submit();
		};
	
	
		function checks(){
			var err = document.getElementById("err").value;
			if(err==null||err==""){
				
			} 
			else if(err!= null){
				alert("删除失败！");
				}
		};
		</script>
	</head>
	<body onload="checks()">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="ye_header_left">&nbsp;
					
				</td>
				<td class="ye_header_center">
					<img src="${ctx}/images/main_jt.jpg" />
					&nbsp;当前位置： 基础信息管理 &gt;&gt; 产品系列信息管理
				</td>
				<td class="ye_header_right">&nbsp;
					
				</td>
			</tr>
			<tr>
				<td>&nbsp;
					
				</td>
				<td align="center">
					<br />
					<div class="mo_wp">
						<div style="display: ; " class="mo_con" align="left">
						 <html:form action="/productseriemanagement">
	          					<input type="hidden" name="method" value="checkProductSerie">
							 <table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao3">
							 	<tr>
								
									<td class="td_01">
										产品分类名称
									</td>
									<td class="td_02">
										<label>
												<select name="productserie.tname" id="tname" style=" width:126px">
													<option value="0" selected="selected">
														--请选择--
													</option>
													<logic:present name="tlist">
														<logic:iterate id="tlist" name="tlist">
															<logic:equal value="${psinfo.tname}" name="tlist"
																property="no">
																<option value="${tlist.no}" selected="selected">
																	${tlist.name}
																</option>
															</logic:equal>
															<logic:notEqual value="${psinfo.tname}" name="tlist"
																property="no">
																<option value="${tlist.no}">
																	${tlist.name}
																</option>
															</logic:notEqual>
														</logic:iterate>
													</logic:present>
												</select>
											</label>
									</td>
									<td class="td_01">
										产品系列编码
									</td>
									<td class="td_02">
										<input type="text" name="productserie.num" value="${psinfo.num}" id = "productcode" style="width:120px;"/>
									</td>
								</tr>
								<tr>
									<td class="td_01">
										产品系列名称
									</td>
									<td class="td_02">
										<input type="text" name="productserie.name" value="${psinfo.name}" style="width:120px;" maxlength="12"/>
									</td>
									 <td class="td_01" width="20%">&nbsp;</td>
               						 <td class="td_02" width="30%">&nbsp;</td>
								</tr>
							
								<tr>
									<td colspan="4" align="center" height="30px"><a href="javascript:form0submit();">
										<img src="${ctx}/images/btn_JianSuo.gif" /></a>
									</td>
								</tr>
							</table>
							</html:form>
						</div>
						<div class="mo_title" onclick="fMainListToggle(this)">
							<div style="text-align:center">
								<img id="imgShang" src="${ctx}/images/shang_sj.png" />
							</div>
							
						</div>
						
					</div>
				</td>
				<td>&nbsp;
					
				</td>
			</tr>
			<tr>
				<td>&nbsp;
					
				</td>
				<td align="center">
					<br />
					<form action="productseriemanagement.do?method=productSerieAll"
						method="post">
						<input type="hidden" name="productserie.tname" value="${psinfo.tname}"/>
						<input type="hidden" name="productserie.num" value="${psinfo.num}"/>
						<input type="hidden" name="productserie.name" value="${psinfo.name}"/>
						<table width="100%" border="0" cellpadding="0" cellspacing="0" class="biao1" id="xxxlist">
							<tr>
								<th nowrap="nowrap" width="41px">
									选择
								</th>
								<th nowrap="nowrap" width="96px">
									产品系列编码
								</th>
								<th nowrap="nowrap">
									产品系列名称
								</th>
								<th nowrap="nowrap" width="96px">
									产品分类编号
								</th>
								<th nowrap="nowrap" width="96px">
									产品分类名称
								</th>
								<th nowrap="nowrap" width="96px">
									产品部门编号
								</th>
								<th nowrap="nowrap" width="96px">
									产品部门名称
								</th>
								<th nowrap="nowrap" width="40px">
									操作
									<input type="hidden" name="err" id ="err" value="${err}"/>
								</th>
							</tr>
							<tr>
								<logic:present name="productserielist">
									<logic:iterate id="productserieall" name="productserielist" property="records">
										<tr>
											<td>
												&nbsp;<input type="checkbox" name="ss"
													value="${productserieall.id}" onclick="javascript:removeBefore(this);">
											</td>
											<td>
												${productserieall.tno}.${productserieall.no}
											</td>
											<td> 
												${productserieall.name}
											</td>
											<td>
												${productserieall.tno}
											</td>
											<td>
												${productserieall.tname}&nbsp;
											</td>
											<td>
												${productserieall.dno}
											</td>
											<td>
												${productserieall.dname}&nbsp;
											</td>
											<td>
												<a href="javascript:window.location = '${ctx}/productseriemanagement.do?method=showProductSerieUpdate&id=${productserieall.id}';">修改</a>
											</td>
										</tr>
									</logic:iterate>
								</logic:present>
							</tr>
						</table>
			<br />
			<table border="0" cellpadding="0" cellspacing="0" width="100%" id="ec_table">
				<tr>
					<td width="30px" align="left">
					&nbsp;&nbsp;<input type="checkbox" name="zong" onclick="che(this);">
					</td>
					<td width="50px">
						全选

					</td>
					<td width="100px"><a href="javascript:shan();">
						<img src="${ctx}/images/btnDelete.gif" /></a>
					</td>
					<td width="100px">
						<a href="javascript:window.location = '${ctx}/productseriemanagement.do?method=showProductSerieAdd';"><img src="${ctx}/images/btnAdd.gif" />
						</a>
					</td>
					<td align="right">
					<%@ include file="/jsp/common/page.jsp"%>
                    </td>
				</tr>
			</table>
			<tr>
			<td>&nbsp;
				
			</td>
			</tr>
		</table>

	</body>
</html>
