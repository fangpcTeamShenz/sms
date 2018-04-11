<%@page pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/common/admin_header.jsp" %>

<div class="widget-box position-relative">
	<div class="widget-header">
		<h5 class="widget-title bigger lighter">
			<i class="ace-icon fa fa-table"></i>
			短信模板
		</h5>
		<shiro:hasPermission name="clientTemplate_insert">
		<div class="widget-toolbar no-border">
			<button class="btn btn-xs btn-success bigger event-dialog" data-url="${ctx}/clientTemplate/client/add">
				<i class="ace-icon fa fa-plus"></i>
				申请模板
			</button>
		</div>
		</shiro:hasPermission>
	</div>
	<div class="widget-body">
		<div class="widget-toolbox padding-8 clearfix">
			<form class="form-inline">
				<input type="text" class="form-control" name="templateName" placeholder="模板名称">
				<input type="text" class="form-control" name="templateContent" placeholder="模板内容">
				<app:propertySelect parentPath="business.smsTemplate.productType" placeholder="--模板类型--" name="productType"/>
				<app:propertySelect parentPath="business.smsTemplate.status" placeholder="--状态--" name="status"/>
				<input type="hidden" name="userId" value="${userId}"/>
				<button type="button" class="btn btn-info btn-sm event-search" 
					data-url="${ctx}/clientTemplate/client/page/list" data-tpl="tpl-clientTemplate">
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
						<th>模板名称</th>
						<th>模板内容</th>
						<th>模板类型</th>
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
	<jsp:param value="menu-clientTemplate-list" name="menu"/>
</jsp:include>

<script id="tpl-clientTemplate" type="text/html">
	{{# for(var i = 0; i < d.length; i++){ }}
      <tr>
        <td>{{=d[i].templateName}}</td>
        <td>{{=d[i].templateContent}}</td>
        <td>
			{{# if(d[i].productType == 1) { }}
				通知
			{{# } else if(d[i].productType == 2) { }}
				营销
			{{# } }}
		</td>
        <td>
			{{# if(d[i].status == 0) { }}
				审核中
			{{# } else if(d[i].status == 1) { }}
				审核通过
			{{# }else if(d[i].status == 2) { }}
				审核不通过
			{{# } }}
		</td>
        <td>{{=d[i].reason}}</td>
        <td>{{=d[i].memo}}</td>
        <td>{{=d[i].createTime}}</td>
        <td>
			<shiro:hasPermission name="clientTemplate_delete">
			<button type="button" class="btn btn-xs btn-inverse event-ajax" data-tips="你确定要删除吗？" data-url="${ctx}/smsTemplate/admin/delete/{{=d[i].smsTemplateId}}">
              <i class="ace-icon glyphicon glyphicon-remove"></i>删除
            </button>
			</shiro:hasPermission>
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
	});
</script>