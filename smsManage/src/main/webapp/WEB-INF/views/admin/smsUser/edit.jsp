<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<form class="form-horizontal" action="${ctx}/smsUser/admin/update" method="post">
	
	<input type="hidden" name="userId" value="${item.userId}">
	
	<input type="hidden" name="userType" value="${item.userType}"/>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">用户名</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="nickname" autocomplete="off" class="width-100" value="${item.nickname }" disabled="disabled" />
			</span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">联系人</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="linkman" autocomplete="off" class="width-100" value="${item.linkman }"/>
			</span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">联系电话</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="phone" autocomplete="off" class="width-100" value="${item.phone }"/>
			</span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">邮箱</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="email" autocomplete="off" class="width-100" value="${item.email }"/>
			</span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">信用条数</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="number" name="creditAmount" autocomplete="off" value="${item.creditAmount }" class="width-100" />
			</span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">预警号码</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="alertPhone" autocomplete="off" value="${item.alertPhone }" class="width-100" />
			</span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">预警条数</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="number" name="alertBalance" autocomplete="off" value="${item.alertBalance }" class="width-100" />
			</span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">上行回调地址</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="upstreamUrl" autocomplete="off" value="${item.upstreamUrl }" class="width-100" />
			</span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">用户级扩展码</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="extNumber" autocomplete="off" value="${item.extNumber }" class="width-100" />
			</span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">回调通知地址</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="notifyUrl" autocomplete="off" value="${item.notifyUrl }" class="width-100" />
			</span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">cmpp账号</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="cmppSpid" autocomplete="off" value="${item.cmppSpid }" class="width-100" />
			</span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">cmpp密码</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="cmppPwd" autocomplete="off" value="${item.cmppPwd }" class="width-100" />
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
