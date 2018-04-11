<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<%@ taglib uri="/WEB-INF/lib/app.tld" prefix="app"%>

<form class="form-horizontal" action="${ctx}/clientSignature/client/insert" method="post">
	<input type="hidden" name="status" value="0"/>
	<input type="hidden" name="userId" value="${user.userId}"/>
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">签名类型</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<app:propertySelect parentPath="business.smsSignature.signatureType" placeholder="--签名类型--" name="signatureType" required="true"/>
			</span>
		</div>
	</div> 
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">签名内容</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" id="signContent" name="signContent" autocomplete="off" class="width-100" required />
			</span>
			<span style="color: red;">例:签名为【沛璟】，请输入 沛璟 。</span>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">备注</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="memo" autocomplete="off" class="width-100"/>
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
