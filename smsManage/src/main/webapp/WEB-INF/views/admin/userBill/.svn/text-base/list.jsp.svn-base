<%@page pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/common/admin_header.jsp" %>

<div class="widget-box position-relative">
	<div class="widget-header">
		<h5 class="widget-title bigger lighter">
			<i class="ace-icon fa fa-table"></i>
			充值消费记录列表
		</h5>

		<div class="widget-toolbar no-border">
			<button class="btn btn-xs btn-success bigger event-dialog" data-url="${ctx}/userBill/admin/add">
				<i class="ace-icon fa fa-plus"></i>
				新增记录
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
				<app:propertySelect parentPath="business.userBill.feeType" placeholder="--费用类型--" name="feeType"/>

				<button type="button" class="btn btn-info btn-sm event-search" 
					data-url="${ctx}/userBill/admin/page/list" data-tpl="tpl-userBill">
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
						<th>发生条数</th>
						<th>费用类型</th>
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
	<jsp:param value="menu-userBill-list" name="menu"/>
</jsp:include>

<script id="tpl-userBill" type="text/html">
	{{# for(var i = 0; i < d.length; i++){ }}
      <tr>
        <td>{{=d[i].nickname}}</td>
        <td>{{=d[i].afterAmount}}</td>
        <td>
			{{# if(d[i].feeType == 0) { }}
				消费
			{{# } else if(d[i].feeType == 1) { }}
				退款
			{{# }else if(d[i].feeType == 2) { }}
				充值
			{{# } }}
		</td>
        <td>{{=d[i].memo}}</td>
        <td>{{=d[i].createTime}}</td>
        <td>
			<shiro:hasPermission name="userBill_edit">
            <button type="button" class="btn btn-xs btn-yellow event-dialog" data-url="${ctx}/userBill/admin/edit/{{=d[i].billId}}">
              <i class="ace-icon fa fa-pencil"></i>修改
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
		$('#userIds').change(function(){
			var _userId =  $(this).val();
			$('#userId').val(_userId);
		});
		
	});
</script>