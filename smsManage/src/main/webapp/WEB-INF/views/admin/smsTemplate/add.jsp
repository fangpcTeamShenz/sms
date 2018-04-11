<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
 <%@ taglib uri="/WEB-INF/lib/app.tld" prefix="app"%>

<form class="form-horizontal" action="${ctx}/smsTemplate/admin/insert" method="post">
	
	<input type="hidden" name="status" value="1"/>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right"> 用户名
		</label>
		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<select name="userId" style="width:359px;" id="userId" class="dept_select" required="true">
					<c:forEach items="${users}" var="obj">
						<option value="${obj.userId}">${obj.nickname}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">模板名称</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="templateName" autocomplete="off" class="width-100" required />
			</span>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">模板内容</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<textarea rows="5" cols="47" name="templateContent" id="templateContent" required="required"></textarea>
			</span>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">模板类型</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<app:propertySelect parentPath="business.smsTemplate.productType" placeholder="--模板类型--" name="productType" required="true"/>
			</span>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">备注</label>

		<div class="col-xs-12 col-sm-5 no-padding-right" style="height: 45px;">
			<span class="block input-icon input-icon-right">
				<input type="text" id="memo" name="memo" autocomplete="off" class="width-100" />
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

<script type="text/javascript">
$(function(){
	$('.dept_select').chosen({
		no_results_text:"没有找到",
	    allow_single_deselect:true,
	    placeholder_text_single:"没有选项",
	    width:"100%"
	});
});
</script>
