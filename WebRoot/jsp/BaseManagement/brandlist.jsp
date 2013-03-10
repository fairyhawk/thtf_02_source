<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>品牌管理</title>
		<link href="${ctx}/css/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="../..js/tad_bs.js"></script>
		<script type="text/javascript" src="${ctx}/js/base.js"></script>
		<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
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
				window.location = '${ctx}/brand.do?method=deleteBrand&id='+ myArray.join(',');
			}else{
			}
		};
		</script>
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="ye_header_left">&nbsp;
					
				</td>
				<td class="ye_header_center">
					<img src="${ctx}/images/main_jt.jpg" />
					&nbsp;当前位置： 基础信息 &gt;&gt; 品牌管理
				</td>
				<td class="ye_header_right">&nbsp;
					
				</td>
			</tr>
			<tr>
				<td>&nbsp;
					
				</td>
				<td align="center">
					<br />
					<form action="brand.do?method=brandAll" method="post">
						<table width="800px" border="0" cellpadding="0" cellspacing="0"
							class="biao1">
							<tr>
								<th nowrap="nowrap" width="50px">
									选择
								</th>
								<th nowrap="nowrap">
									品牌名称
								</th>
								<th nowrap="nowrap" width="80px">
									操作
								</th>
							</tr>
							<tr>
								<logic:present name="brandlist">
									<logic:iterate id="brandall" name="brandlist" property="records">
										<tr>
											<td>
												<input type="checkbox" name="ss" value="${brandall.id}" id = "box" onclick="javascript:removeBefore(this);"/>
											</td>
											<td>
												${brandall.name}
											</td>
											<td>
												<input type="hidden" name="brand.id" value="${brandall.id}" />
												<a
													href="${ctx}/brand.do?method=showBrandUpdate&id=${brandall.id}">修改</a>
											</td>
										</tr>

									</logic:iterate>
								</logic:present>
							</tr>
						</table>
						<br />
						<table border="0" cellpadding="0" cellspacing="0" width="800px"
							id="ec_table">
							<tr>
								<td width="50px" align="center">
									<input type="checkbox" name="zong" onclick="che(this);" />
								</td>
								<td width="50px">
									全选

								</td>
								<td width="100px">
									<a href="javascript:shan();"><img
											src="${ctx}/images/btnDelete.gif" /> </a>
								</td>
								<td width="100px">
									<a href="javascript:window.location = '${ctx}/brand.do?method=showBrandAdd';"><img
											src="${ctx}/images/btnAdd.gif" /> </a>
								</td>
								<td align="right">
								<%@ include file="/jsp/common/page.jsp"%>
                                </td>
							</tr>

						</table>
					</form>
				</td>
				<td>&nbsp;
					
				</td>
			</tr>
		</table>
	</body>
</html>
