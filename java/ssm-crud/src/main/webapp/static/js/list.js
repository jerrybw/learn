/**
 * @author 向博文
 */
var curWwwPath = window.document.location.href;
var pathName =  window.document.location.pathname;
var pos = curWwwPath.indexOf(pathName);
var localhostPaht = curWwwPath.substring(0,pos);
var projectName = pathName.substring(0,pathName.substr(1).indexOf('/')+1);
window.basePath =localhostPaht + projectName;

//更改部门
function changeDept(deptId,deptName){
	$("#dept").attr("dept",deptId);
	$("#deptName").html(deptName);
}

//更改性别
function changeGender(gender,genderName){
	$("#gender").attr("gender",gender);
	$("#genderName").html(genderName);
}

//更改每页展示数据条数
function changePdn(pdn){
	$("#pdn").attr("pdn",pdn);
	$("#pdnName").html(pdn);
}

//获取所有部门信息
function getDepts(){
	$.ajax({
		url:basePath+"/depts",
		type:"GET",
		success:function(data){
			if(data.code == 1){
				$.each(data.extend.depts,function(index,item){
					$("#dept").append("<li><a href='javascript:changeDept("+item.deptId+",\""+item.deptName+"\")'>"+item.deptName+"</a></li>");
					$("#addDepts").append("<option value="+item.deptId+">"+item.deptName+"</option>");
				})
			}
		}
	})
}

//获取员工数据（根据查询条件）
function search(pn){
	var dept = $("#dept").attr("dept");
	var gender = $("#gender").attr("gender");
	var queryCondition = $("#queryCondition").val();
	var dName = $("#deptName").html();
	var pdn = $("#pdn").attr("pdn");
	var url = basePath+"/emps";
	$.ajax({
		url:url,
		type:"GET",
		data:"dId="+dept+"&dName="+dName+"&gender="+gender+"&queryCondition="+queryCondition+"&pn="+pn+"&pdn="+pdn,
		success:function(data){
			if(data.code == 1){
				//渲染数据
				renderData(data);
				//渲染分页数据
				renderPageInfoArea(data);
				//渲染分页导航栏
				renderPageNavArea(data);
			}
			
		}
	})
}

//渲染分页数据
function renderPageInfoArea(data){
	$("#pageInfoArea").html("当前页"+data.extend.pageInfo.pageNum+"：,总记录数：<span id='totalEmp'>"+data.extend.pageInfo.total+"</span>,总页数："+data.extend.pageInfo.pages)
}

//渲染分页导航栏
function renderPageNavArea(data){
	var html = "<nav aria-label='Page navigation'><ul class='pagination'>";
	if(!data.extend.pageInfo.hasPreviousPage){
		html += "<li class='disabled'><a href='#'>首页</a></li>";
		html += "<li class='disabled'><a href='#' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>"
	}else{
		html += "<li ><a href='javascript:search(1)'>首页</a></li>";
		html += "<li ><a href='javascript:search("+(data.extend.pageInfo.pageNum - 1)+")' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>"
	}
	$.each(data.extend.pageInfo.navigatepageNums,function(index,item){
		if(data.extend.pageInfo.pageNum == item){
			html += '<li class="active"><a href="#">'+item+'</a></li>';
		}else{
			html += '<li><a href="javascript:search('+item+')">'+item+'</a></li>';
		}
	})
	if(!data.extend.pageInfo.hasNextPage){
		html += "<li class='disabled'><a href='#' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>"
		html += "<li class='disabled'><a href='javascript:search("+data.extend.pageInfo.pages+")'>末页</a></li>";
	}else{
		html += "<li ><a href='javascript:search("+(data.extend.pageInfo.pageNum + 1)+")' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>"
		html += "<li ><a href='javascript:search("+data.extend.pageInfo.pages+")'>首页</a></li>";
	}
	html += "</ul></nav>";
	$("#pageNavArea").html(html);
}

//渲染表格数据
function renderData(data){
	var html = "";
	$.each(data.extend.pageInfo.list,function(index,item){
		html += '<tr>'+
		'<td>'+item.empId+'</td>'+
		'<td>'+item.empName+'</td>'+
		'<td>'+(item.gender=="M"?"男":(item.gender=="F"?"女":"未知"))+'</td>'+
		'<td>'+(item.email==null?"":item.email)+'</td>'+
		'<td>'+(item.department==null?"无":item.department.deptName)+'</td>'+
		'<td>'+
		'<button class="btn btn-primary btn-sm">'+
		'<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>'+
		'修改'+
		'</button>'+
		'<button class="btn btn-danger btn-sm">'+
		'<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>'+
		'删除'+
		'</button>'+
		'</td>'+
		'</tr>';
	})
	$("#dataArea tbody").html(html);
}

//绑定模态框
function bindModal(){
	resetForm();
	$("#employeeAddModal").modal({
		backdrop:"static"
	})
}

//保存员工
function saveEmp(){
	if(validateEmpData()){
		$.ajax({
			url:basePath+"/emp",
			type:"POST",
			data:$("#addEmp").serialize(),
			success:function(data){
				if(data.code == 1){
					$('#employeeAddModal').modal('hide');
					search($("#totalEmp").text());
				}else{
					$.each(data.extend.errors,function(){
						renderValidateResult($("#"+this.key),false,this.value);
					})
				}
			}
		})
	}
}

//数据校验
function validateEmpData(){
	var regEmpName = validateEmpName();
	var regEmail = validateEmail();
	return regEmpName && regEmail;
}

//员工姓名校验
function validateEmpName(){
	var regEmpName = /(^[a-zA-Z0-9_]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
	var empName = $("#empName").val();
	var flag = renderValidateResult($("#empName"),regEmpName.test(empName),"员工姓名必须由6-16位大小写字母数字以及下划线或者2-5位中文组成");
	if(flag){
		return validateEmpNameIsAble(empName);
	}else{
		return false;
	}
}

//验证与员工名是否可用
function validateEmpNameIsAble(empName){
	var flag = true;
	$.ajax({
		url:basePath+"/checkUserName",
		data:{
			"empName":empName
		},
		type:"POST",
		success:function(data){
			if(data.code != 1){
				renderValidateResult($("#empName"),false,data.extend.validateRes);
				flag = false
			}
		}
	})
	return flag;
}


//邮箱校验
function validateEmail(){
	var regEmail = 	/^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;
	var email = $("#email").val();
	return renderValidateResult($("#email"),regEmail.test(email),"邮箱格式不正确");
}

//渲染校验结果
function renderValidateResult(ele,isSuccess,msg){
	ele.parent().removeClass("has-error has-success");
	ele.next("span").text("");
	if(isSuccess){
		ele.parent().addClass("has-success");
		return true;
	}else{
		ele.parent().addClass("has-error");
		ele.next("span").text(msg);
		return false;
	}
}


//重置表单
function resetForm(){
	$("#addEmp").find("*").removeClass("has-error has-success");
	$("#addEmp")[0].reset();
}