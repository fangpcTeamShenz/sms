<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<%@ taglib uri="/WEB-INF/lib/app.tld" prefix="app"%>

<form class="form-horizontal" action="${ctx}/userBill/admin/update" method="post">
	
	<input type="hidden" name="billId" value="${item.billId}">
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right"> 用户名
		</label>
		<div class="col-xs-12 col-sm-5 no-padding-right">
			<div class="block input-icon input-icon-right">
				<select name="userId" style="width:359px;" id="userId" class="dept_select">
					<c:forEach items="${users}" var="obj">
						<option value="${obj.userId}" <c:if test="${obj.userId == item.userId}">selected="selected"</c:if> >${obj.nickname}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">发生条数</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="number" name="afterAmount" autocomplete="off" value="${item.afterAmount }" class="width-100" required="required"/>
			</span>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">费用类型</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<app:propertySelect parentPath="business.userBill.feeType" placeholder="--费用类型--" propertyKey="${item.feeType }" name="feeType" required="true"/>
			</span>
		</div>
	</div>
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right">备注</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<span class="block input-icon input-icon-right">
				<input type="text" name="memo" autocomplete="off" value="${item.memo }" class="width-100" />
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
