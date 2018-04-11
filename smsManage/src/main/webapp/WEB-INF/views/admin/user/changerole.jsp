<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<form class="form-horizontal" action="${ctx}/user/admin/roleupdate" method="post">
	
	<input type="hidden" name="userId" value="${item.userId}">
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">用户名</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<input type="text" name="nickname" autocomplete="off" class="width-100" value="${item.nickname}" disabled="disabled" />
			</div>
		</div>
	</div>

	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">角色权限组</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<select name="roleid" class="form-control" required >
				<option value="" id="role_0">请选择</option>
				<c:forEach items="${roles}" var="role">
					<option value="${role.id}" id="role_${role.id}">${role.roleName}：${role.description}</option>
				</c:forEach>
			</select>
		</div>
	</div>

	<div class="form-group">
		<div class="col-xs-12 col-sm-4 col-sm-offset-3">
			<button type="submit" class="btn btn-success btn-sm">
				<i class="ace-icon fa fa-save bigger-110"></i> 保存
			</button>
			<button type="reset" class="btn btn-sm">
				<i class="ace-icon fa fa-undo bigger-110"></i> 重置
			</button>
		</div>
	</div>
</form>
<input type="hidden" name="id" id="adroles" value="${userrole}">
<script>

	var adroles=document.getElementById("adroles").value;
	
	//获得id
	var id="role_"+adroles;
	
	//绑定权限到复选框
	document.getElementById(id).selected=true;

</script>
