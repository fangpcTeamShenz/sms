<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<%@ taglib uri="/WEB-INF/lib/app.tld" prefix="app"%>

<form class="form-horizontal" action="${ctx}/channel/admin/insert" method="post">
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">通道名称</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="channelName" autocomplete="off" class="width-100" required="required"/>
			</span>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">是否可用</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<app:propertySelect parentPath="business.channel.useable" placeholder="--是否可用--" name="useable" required="true"/>
			</span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">是否有回调</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<app:propertySelect parentPath="business.channel.existCallback" placeholder="--是否有回调--" name="existCallback" required="true"/>
			</span>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">是否有上行</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<app:propertySelect parentPath="business.channel.existUpper" placeholder="--是否有上行--" name="existUpper" required="true"/>
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
