<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<%@ taglib uri="/WEB-INF/lib/app.tld" prefix="app"%>

<form class="form-horizontal" action="${ctx}/routeForce/admin/update" method="post">
	
	<input type="hidden" name="rotueId" value="${item.rotueId}">
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">路由名称</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="routeName" value="${item.routeName }" autocomplete="off" class="width-100" required="required"/>
			</span>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right"> 产品名称
		</label>
		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<select name="productId" style="width:359px;" id="productId" class="dept_select" required="true">
					<c:forEach items="${products}" var="obj">
						<option value="${obj.productId}" <c:if test="${obj.productId == item.productId}">selected="selected"</c:if> >${obj.productName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">通道名称
		</label>
		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<select name="channelId" style="width:359px;" id="channelId" class="dept_select" required="true">
					<c:forEach items="${channels}" var="obj">
						<option value="${obj.channelId}" <c:if test="${obj.channelId == item.channelId}">selected="selected"</c:if>>${obj.channelName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">优先级</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="number" id="priority" name="priority" value="${item.priority }" autocomplete="off" class="width-100" />
			</span>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">是否可用</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<app:propertySelect parentPath="business.routeForce.useable" placeholder="--是否可用--" propertyKey="${item.useable }"  name="useable" required="true"/>
			</span>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">占比</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="number" id="rate" name="rate" value="${item.rate }" autocomplete="off" class="width-100" />
			</span>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right"> 用户名
		</label>
		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<select name="userId" style="width:359px;" id="userId" class="dept_select">
					<option value="" >--无--</option>
					<c:forEach items="${users}" var="obj">
						<option value="${obj.userId}" <c:if test="${obj.userId == item.userId}">selected="selected"</c:if>>${obj.nickname}</option>
					</c:forEach>
				</select>
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
