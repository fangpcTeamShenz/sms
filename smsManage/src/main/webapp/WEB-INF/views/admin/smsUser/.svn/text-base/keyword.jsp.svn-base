<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<form class="form-horizontal" action="${ctx}/smsUser/admin/updateKeyWord" method="post">

	<input type="hidden" name="userId" value="${item.userId }"/>

	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">敏感字白名单</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<textarea name="keywordWhite" rows="5" cols="47">${item.keywordWhite }</textarea>
			</div>
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
