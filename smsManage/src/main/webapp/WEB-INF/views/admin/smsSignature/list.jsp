<%@page pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/common/admin_header.jsp" %>

<div class="widget-box position-relative">
	<div class="widget-header">
		<h5 class="widget-title bigger lighter">
			<i class="ace-icon fa fa-table"></i>
			签名列表
		</h5>
		<shiro:hasPermission name="smsSignature_insert">
		<div class="widget-toolbar no-border">
			<button class="btn btn-xs btn-success bigger event-dialog" data-url="${ctx}/smsSignature/admin/add">
				<i class="ace-icon fa fa-plus"></i>
				新增签名
			</button>
		</div>
		</shiro:hasPermission>
	</div>
	<div class="widget-body">
		<div class="widget-toolbox padding-8 clearfix">
			<form class="form-inline">
				<div class="input-group">
					<input type="hidden" class="form-control" name="userId" id="userId">
					<select class="dept_select form-control" multiple="multiple" id="userIds"
							 data-placeholder="--用户名--" style="height: 60px;width: 350px;" >
						<c:forEach items="${users}" var="item">
							<option value="${item.userId}">${item.nickname}</option>
						</c:forEach>
					</select>
				</div>
				<app:propertySelect parentPath="business.smsSignature.signatureType" placeholder="--签名类型--" name="signatureType"/>
				<input type="text" class="form-control" name="signContent" placeholder="签名内容">
				<app:propertySelect parentPath="business.smsSignature.status" placeholder="状态" name="status"/>

				<button type="button" class="btn btn-info btn-sm event-search" 
					data-url="${ctx}/smsSignature/admin/page/list" data-tpl="tpl-smsSignature">
					<i class="ace-icon fa fa-search bigger-110"></i> 搜索
				</button>
				<button type="reset" class="btn btn-sm">
					<i class="ace-icon fa fa-undo bigger-110"></i> 清空
				</button>
			</form>
		</div>
		<div class="widget-main no-padding">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th>用户名</th>
						<th>签名类型</th>
						<th>签名内容</th>
						<th>状态</th>
						<th>失败原因</th>
						<th>备注</th>
						<th>创建时间</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/admin_footer.jsp" flush="true">
	<jsp:param value="menu-smsSignature-list" name="menu"/>
</jsp:include>

<script id="tpl-smsSignature" type="text/html">
	{{# for(var i = 0; i < d.length; i++){ }}
      <tr>
        <td>{{=d[i].nickname}}</td>
        <td>{{=d[i].signatureType}}</td>
        <td>{{=d[i].signContent}}</td>
		<td>{{=d[i].status}}</td>
		<td>{{=d[i].reason}}</td>
		<td>{{=d[i].memo}}</td>
		<td>{{=d[i].createTime}}</td>
        <td>
			<shiro:hasPermission name="smsSignature_edit">
            <button type="button" class="btn btn-xs btn-yellow event-dialog" data-url="${ctx}/smsSignature/admin/edit/{{=d[i].smsSignatureId}}">
              <i class="ace-icon fa fa-pencil"></i>修改
            </button>
			</shiro:hasPermission>
			<shiro:hasPermission name="smsSignature_delete">
			<button type="button" class="btn btn-xs btn-inverse event-ajax" data-tips="你确定要删除吗？" data-url="${ctx}/smsSignature/admin/delete/{{=d[i].smsSignatureId}}">
              <i class="ace-icon glyphicon glyphicon-remove"></i>删除
            </button>
			</shiro:hasPermission>
			<shiro:hasPermission name="smsSignature_audit">
			{{# if (d[i].status == '审核中') { }}
				<button type="button" class="btn btn-xs btn-yellow event-dialog" data-url="${ctx}/smsSignature/admin/audit/{{=d[i].smsSignatureId}}?userId={{=d[i].userId}}">
              		<i class="ace-icon fa fa-key"></i>审核
            	</button>
			</shiro:hasPermission>
			{{# } }}
        </td>
      </tr>
    {{# } }}
</script>
<script type="text/javascript">
	$(function(){
		//监听回车事件
		document.onkeydown = function(e){ 
		    var ev = document.all ? window.event : e;
		    if(ev.keyCode==13) {
		       $('.event-search').click();//处理事件
		    }
		}
		$('.date-picker').datepicker({
			autoclose: true,
			todayHighlight: true
		})
		$('.dept_select').chosen({
			no_results_text:"没有找到",
			allow_single_deselect:true,
			placeholder_text_single:"没有选项",
			max_selected_options:1,
			width:"200px;line-height:2.5"
		});
		$('#userIds').change(function(){
			var _userId =  $(this).val();
			$('#userId').val(_userId);
		});
		
	});
</script>
