<%@page pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/common/admin_header.jsp" %>

<div class="widget-box position-relative">
	<div class="widget-header">
		<h5 class="widget-title bigger lighter">
			<i class="ace-icon fa fa-table"></i>
			模板列表
		</h5>

		<div class="widget-toolbar no-border">
			<button class="btn btn-xs btn-success bigger event-dialog" data-url="${ctx}/smsTemplate/admin/add">
				<i class="ace-icon fa fa-plus"></i>
				新增模板
			</button>
		</div>

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
				<input type="text" class="form-control" name="templateName" placeholder="模板名称">
				<input type="text" class="form-control" name="templateContent" placeholder="模板内容">
				<app:propertySelect parentPath="business.smsTemplate.productType" placeholder="--模板类型--" name="productType"/>
				<app:propertySelect parentPath="business.smsTemplate.status" placeholder="--状态--" name="status"/>

				<button type="button" class="btn btn-info btn-sm event-search" 
					data-url="${ctx}/smsTemplate/admin/page/list" data-tpl="tpl-smsTemplate">
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
	<jsp:param value="menu-smsTemplate-list" name="menu"/>
</jsp:include>

<script id="tpl-smsTemplate" type="text/html">
	{{# for(var i = 0; i < d.length; i++){ }}
      <tr>
        <td>{{=d[i].nickname}}</td>
        <td>{{=d[i].templateName}}</td>
        <td>
			{{# if(d[i].templateContent.length >= 30){ }}
				<div class="action-buttons">
					<span title="{{=d[i].templateContent}}">
						{{=d[i].templateContent.substring(0,25)}}***
						{{=d[i].templateContent.substring(d[i].templateContent.length - 5,d[i].templateContent.length)}}
					</span>
					<a href="javascript:void(0)" class="green bigger-140 show-details-btn" title="Show Details" onclick="showTemp('{{=d[i].smsTemplateId}}');">
						<i id="ic{{=d[i].smsTemplateId}}" class="ace-icon fa fa-angle-double-down"></i>
						<span class="sr-only">Details</span>
					</a>
				</div>

			{{# } else { }}
				<span>{{=d[i].templateContent}}</span>
			{{# } }}
		</td>
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
			<shiro:hasPermission name="smsTemplate_edit">
            <button type="button" class="btn btn-xs btn-yellow event-dialog" data-url="${ctx}/smsTemplate/admin/edit/{{=d[i].smsTemplateId}}">
              <i class="ace-icon fa fa-pencil"></i>编辑
            </button>
			</shiro:hasPermission>
			<shiro:hasPermission name="smsTemplate_delete">
			<button type="button" class="btn btn-xs btn-inverse event-ajax" data-tips="你确定要删除吗？" data-url="${ctx}/smsTemplate/admin/delete/{{=d[i].smsTemplateId}}">
              <i class="ace-icon glyphicon glyphicon-remove"></i>删除
            </button>
			</shiro:hasPermission>
			{{# if(d[i].status == 0) { }}
			<shiro:hasPermission name="smsTemplate_audit">
            <button type="button" class="btn btn-xs btn-yellow event-dialog" data-url="${ctx}/smsTemplate/admin/audit/{{=d[i].smsTemplateId}}">
              <i class="ace-icon fa fa-key"></i>审核
            </button>
			</shiro:hasPermission>
			{{# } }}
        </td>
      </tr>
	  <tr id="{{=d[i].smsTemplateId}}" class="detail-row" style="display:none">
		<td colspan="8">
			<div class="table-detail">
				<span>{{=d[i].templateContent}}</span>
			</div>
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
	
	var showTemp = function(temp){
		var display =$('#' + temp).css('display');
		if(display == 'none'){
		    $("#" + temp).show();
		    $("#ic" + temp).toggleClass('fa-angle-double-down').toggleClass('fa-angle-double-up');
		}else{
			$("#" + temp).hide();
			$("#ic" + temp).toggleClass('fa-angle-double-up').toggleClass('fa-angle-double-down');
		}
	};
</script>