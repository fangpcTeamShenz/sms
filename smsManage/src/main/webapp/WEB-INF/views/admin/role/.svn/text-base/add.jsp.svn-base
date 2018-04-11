<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<form class="form-horizontal" action="${ctx}/role/admin/roleadd" method="post">
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">角色名</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<input type="text" name="roleName" autocomplete="off" class="width-100" required />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">角色说明</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<input type="text" name="description" autocomplete="off" class="width-100"  required/>
			</div>
		</div>
		
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">权限</label>
		</div>
		<div class="form-group">
	<c:forEach items="${permissions}" var="permission">
	<div class="checkbox">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right"></label>
	<label>
	<input name="permission" type="checkbox" class="ace" value="${permission.id}" id="permission_${permission.id}">
	<span class="lbl">${permission.description }</span>
	</label>
	</div>
	</c:forEach>
	</div>
	
	<div class="form-group">
		<div class="col-xs-12 col-sm-4 col-sm-offset-3">
			<button type="submit" class="btn btn-success btn-sm">
				<i class="ace-icon fa fa-save bigger-110"></i> 保存
			</button>
			<button type="reset" class="btn btn-sm" >
				<i class="ace-icon fa fa-undo bigger-110"></i> 重置
			</button>
		</div>
	</div>
</form>
