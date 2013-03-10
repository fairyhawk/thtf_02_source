<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/taglibs.jsp"%>
<html>
	<head>

		<title>My JSP 'arealist1.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>
	<body>
		<table>
			<tr>
				<td>
					<a href="${ctx}/areamanagement.do?method=areaAll">人员区域信息管理</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="${ctx}/userdeptmanagement.do?method=userDeptAll">人员部门信息管理</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="${ctx}/productdeptmanagement.do?method=productDeptAll">产品部门信息管理</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="${ctx}/producttypemanagement.do?method=productTypeAll">产品分类信息管理</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="${ctx}/productseriemanagement.do?method=productSerieAll">产品系列信息管理</a>
				</td>
			</tr>

			<tr>
			<tr>
				<td>
					<a href="${ctx}/product.do?method=productAll">产品信息信息管理</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="${ctx}/depot.do?method=depotAll">库房信息管理</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="${ctx}/logistics.do?method=logisticsAll">物流公司信息管理</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="${ctx}/supplier.do?method=supplierAll">供货商信息管理</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="${ctx}/customer.do?method=supplierAll">客户信息管理</a>
				</td>
			</tr>
			<tr>
				<td>
					<a href="${ctx}/companyadress.do?method=companyAll">公司信息管理</a>
				</td>
			</tr>
		</table>
	</body>
</html>
