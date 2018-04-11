<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<form class="form-horizontal" action="${ctx}/user/admin/insert" method="post">
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">用户名</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<input type="text" name="nickname" autocomplete="off" class="width-100" data-rule-rangelength="[2,20]" data-rule-english required />
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">邮箱地址</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="email" autocomplete="off" class="width-100" data-rule-email required />
			</span>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">登陆密码</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="password" id="password" name="password" autocomplete="off" class="width-100" required />
			</span>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">确认密码</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="password" name="confirm_password" autocomplete="off" class="width-100" data-rule-equalTo="#password" required />
			</span>
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
