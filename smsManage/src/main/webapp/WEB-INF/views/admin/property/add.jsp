<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<%@ taglib uri="/WEB-INF/lib/app.tld" prefix="app"%>

<form class="form-horizontal" action="${ctx}/property/admin/insert" method="post">
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">父路径</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<input type="text" name="parentPath" autocomplete="off" class="width-100" value="${criteria.parentPath}" readonly="readonly"/>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right"><span class="orange">* </span>字典名称</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<input type="text" name="propertyName" autocomplete="off" class="width-100" data-rule-maxlength="128" data-rule-english required />
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right"><span class="orange">* </span>值</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<input type="text" name="propertyKey" autocomplete="off" class="width-100" data-rule-maxlength="128" placeholder="保存数据库中的值" required />
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right"><span class="orange">* </span>显示名称</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<input type="text" name="propertyValue" autocomplete="off" class="width-100" data-rule-maxlength="128" placeholder="WEB页面显示的名称" required />
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right"><span class="orange">* </span>显示顺序</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<input type="text" name="propertyIndex" autocomplete="off" class="width-100" placeholder="页面显示的顺序" data-rule-digits required />
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right"><span class="orange">* </span>是否显示</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<app:propertySelect parentPath="business.property.disable" placeholder="是否显示" name="disable" required="true"/>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">备注</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<input type="text" name="memo" autocomplete="off" class="width-100" />
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
