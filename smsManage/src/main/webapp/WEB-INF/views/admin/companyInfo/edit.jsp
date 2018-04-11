<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<%@ taglib uri="/WEB-INF/lib/app.tld" prefix="app"%>

<form class="form-horizontal" action="${ctx}/companyInfo/admin/update" method="post">
	
	<input type="hidden" name="id" value="${item.id}">
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">用户名</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="nickname" autocomplete="off" class="width-100" value="${users.nickname }" disabled="disabled"/>
			</span>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">公司名称</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="companyName" value="${item.companyName }" autocomplete="off" class="width-100" required="required"/>
			</span>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">机构代码</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="pictureids" value="${item.pictureids }" autocomplete="off" class="width-100"/>
			</span>
		</div>
	</div>
	
	<%-- <div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">审核状态</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<app:propertySelect parentPath="business.companyInfo.status" propertyKey="${item.status }" placeholder="--审核状态--"  name="status" required="true"/>
			</span>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">审核失败原因</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="reason" value="${item.reason }" autocomplete="off" class="width-100"/>
			</span>
		</div>
	</div> --%>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">备注</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="memo" value="${item.memo }" autocomplete="off" class="width-100"/>
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
