<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SSM-CRUD</title>
<script type="text/javascript" src="${APP_PATH}/static/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${APP_PATH}/static/js/list.js"></script>
<script type="text/javascript" src="${APP_PATH}/static/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${APP_PATH}/static/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH}/static/css/list.css">
</head>
<body>
	<input type="hidden" id="basePath" value="${APP_PATH}">
	<!-- 新增员工弹出模态框 -->
	<div class="modal fade" id="employeeAddModal" tabindex="-1" role="dialog" aria-labelledby="employeeAddModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="employeeAddModalLabel">员工新增</h4>
	      </div>
	      
	      <div class="modal-body">
	      <form class="form-horizontal" id="addEmp">
			  <div class="form-group">
			    <label for="empName" class="col-sm-2 control-label">empName</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="empName" id="empName" placeholder="empName">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="email" class="col-sm-2 control-label">email</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" name="email" id="email" placeholder="xxx@yyy.com">
			      <span class="help-block"></span>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">gender</label>
			    <div class="col-sm-10">
					<label class="radio-inline">
					  <input type="radio" name="gender" id="gender1" value="M" checked> 男
					</label>
					<label class="radio-inline">
					  <input type="radio" name="gender" id="gender2" value="F"> 女
					</label>
					<label class="radio-inline">
					  <input type="radio" name="gender" id="gender3" value="S"> 未知
					</label>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">dept</label>
			    <div class="col-sm-4">
					<select class="form-control" name="dId" id="addDepts">
					</select>
			    </div>
			  </div>
			</form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="addSaveBtn">保存</button>
	      </div>
	    </div>
	  </div>
	</div>
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-6">
				<h1>SSM-CRUD</h1>
			</div>
		</div>
		<!-- 操作按钮 -->
		<div class="row">
			<div class="col-md-8">
				<!-- 				部门 -->
				<div class="col-md-2">
					<div class="dropdown">
					  <button class="btn btn-default dropdown-toggle" type="button" id="deptMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					    <span id="deptName">部门</span>	
					    <span class="caret"></span>
					  </button>
					  <ul id="dept" dept="" class="dropdown-menu" aria-labelledby="deptMenu">
					    <li><a href="javascript:changeDept(0,'请选择')">请选择</a></li>
					  </ul>
					</div>
				</div>
				<!-- 				性別 -->
				<div class="col-md-2">
					<div class="dropdown">
					  <button class="btn btn-default dropdown-toggle" type="button" id="genderMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					    <span id="genderName">性别</span>	
					    <span class="caret"></span>
					  </button>
					  <ul id="gender" gender="" class="dropdown-menu" aria-labelledby="genderMenu">
					    <li><a href="javascript:changeGender('','请选择')">请选择</a></li>
				    	<li><a href="javascript:changeGender('M','男')">男</a></li>
				    	<li><a href="javascript:changeGender('F','女')">女</a></li>
				    	<li><a href="javascript:changeGender('S','未知')">未知</a></li>
					  </ul>
					</div>
				</div>
				<!-- 				每页显示数据条数 -->
				<div class="col-md-2">
					<div class="dropdown">
					  <button class="btn btn-default dropdown-toggle" type="button" id="pdnMenu" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
					    <span id="pdnName">5</span>	
					    <span class="caret"></span>
					  </button>
					  <ul id="pdn" pdn="" class="dropdown-menu" aria-labelledby="pdnMenu">
					    <li><a href="javascript:changePdn(5)">5</a></li>
				    	<li><a href="javascript:changePdn(10)">10</a></li>
				    	<li><a href="javascript:changePdn(50)">50</a></li>
				    	<li><a href="javascript:changePdn(100)">100</a></li>
					  </ul>
			  		</div>
				</div>
				<!-- 				模糊查询 -->
				<div class="col-md-4">
					<div class="input-group">
					  	<input type="text" id="queryCondition" class="form-control" placeholder="查询条件，支持模糊查询" aria-describedby="searchBtn">
					  	<span class="input-group-addon" id="searchBtn" onclick="search(1)">
					  		<span class="glyphicon glyphicon-search" aria-hidden="true" ></span>
						</span>
					</div>
				</div>
			</div>
			<!-- 			批量操作 -->
			<div class="col-md-4">
				<button class="btn btn-primary" id="employeeAddBtn">新增</button>
				<button class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 表格数据 -->
		<div class="row data">
			<table class="table table-hover" id="dataArea">
				<thead>
					<tr>
						<th>#</th>
						<th>name</th>
						<th>gender</th>
						<th>email</th>
						<th>deptName</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
		<!-- 分页信息 -->
		<div class="row">
			<div class="col-md-6" id="pageInfoArea">
			</div>
			<div class="col-md-6" id="pageNavArea">
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			getDepts();
			search(1);
			$("#employeeAddBtn").click(bindModal);
			$("#addSaveBtn").click(saveEmp);
			$("#empName").change(validateEmpName);
			$("#email").change(validateEmail);
		})
	</script>
</body>
</html>