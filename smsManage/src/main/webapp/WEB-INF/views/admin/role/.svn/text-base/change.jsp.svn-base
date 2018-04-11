<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<form class="form-horizontal" action="${ctx}/role/self/updateinfo" method="post">
	
	<input type="hidden" name="id" value="${item.id}">
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">角色名</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<input type="text" name="roleName" autocomplete="off" class="width-100" value="${item.roleName}" disabled="disabled" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">角色说明</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<input type="text" name="description" autocomplete="off" class="width-100" value="${item.description}" disabled="disabled" />
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
			<button type="reset" class="btn btn-sm" style="display: none;">
				<i class="ace-icon fa fa-undo bigger-110"></i> 重置
			</button>
		</div>
	</div>
</form>
<input type="hidden" name="id" id="adroles" value="${adroles}">
<script>

var adroles=document.getElementById("adroles").value;
var adrolesItem=adroles.split(",");
for (var i=1 ; i< adrolesItem.length ; i++)
{

//获得id
var id="permission_"+adrolesItem[i];

//绑定权限到复选框
document.getElementById(id).checked=true;

}

</script>