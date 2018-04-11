<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<form class="form-horizontal" action="${ctx}/smsTemplate/admin/editAudit" method="post">
	
	<input type="hidden" name="smsTemplateId" value="${item.smsTemplateId}">
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">用户名</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" id="nickname" name="nickname" autocomplete="off" class="width-100" value="${user.nickname }" disabled="disabled" />
			</span>
		</div>
	</div>
	
	<%-- <div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">模板状态</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<app:propertySelect parentPath="business.smsTemplate.status" placeholder="--模板状态--" propertyKey="${item.status }" name="status" required="true"/>
			</span>
		</div>
	</div> --%>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">模板类型</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<select name="status" style="width:359px;" id="status" class="dept_select" required="true">
					<option value="">--模板状态--</option>
					<option value="0" <c:if test="${item.status == 0 }">selected="selected"</c:if> >审核中</option>
					<option value="1" <c:if test="${item.status == 1 }">selected="selected"</c:if> >审核成功</option>
					<option value="2" <c:if test="${item.status == 2 }">selected="selected"</c:if> >审核失败</option>
				</select>
			</span>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">失败原因</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="reason" autocomplete="off" value="${item.reason }" class="width-100"/>
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
