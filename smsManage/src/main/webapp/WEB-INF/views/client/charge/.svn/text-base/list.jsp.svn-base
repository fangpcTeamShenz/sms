<%@page pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/common/admin_header.jsp" %>

<div class="widget-box position-relative">
	<div class="widget-header">
		<h5 class="widget-title bigger lighter">
			<i class="ace-icon fa fa-table"></i>
			充值列表
		</h5>
	</div>
	<div class="widget-body">
		<div class="widget-toolbox padding-8 clearfix">
			<form class="form-inline">
				<input type="text" class="form-control" name="tradeNumber" placeholder="交易流水号">
				<app:propertySelect parentPath="business.charge.status" placeholder="--状态--" name="status"/>
				<input type="hidden" name="userId" value="${userId}" />
				<button type="button" class="btn btn-info btn-sm event-search" 
					data-url="${ctx}/clientCharge/client/page/list" data-tpl="tpl-clientCharge">
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
						<th>交易流水号</th>
						<th>充值条数</th>
						<th>赠送条数</th>
						<th>状态</th>
						<th>充值金额</th>
						<th>备注</th>
						<th>创建时间</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/admin_footer.jsp" flush="true">
	<jsp:param value="menu-clientCharge-list" name="menu"/>
</jsp:include>

<script id="tpl-clientCharge" type="text/html">
	{{# for(var i = 0; i < d.length; i++){ }}
      <tr>
        <td>{{=d[i].tradeNumber}}</td>
        <td>{{=d[i].chargeAmount}}</td>
        <td>{{=d[i].giftAmount}}</td>
        <td>
			{{# if(d[i].status == 0) { }}
				等待支付
			{{# } else if(d[i].status == 1) { }}
				支付成功
			{{# }else if(d[i].status == 2) { }}
				充值成功
			{{# } }}
		</td>
        <td>{{=d[i].amout}}</td>
        <td>{{=d[i].memo}}</td>
        <td>{{=d[i].createTime}}</td>
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
