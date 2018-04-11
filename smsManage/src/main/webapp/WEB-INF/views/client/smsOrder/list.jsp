<%@page pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/common/admin_header.jsp" %>

<div class="widget-box position-relative">
	<div class="widget-header">
		<h5 class="widget-title bigger lighter">
			<i class="ace-icon fa fa-table"></i>
			短信记录
		</h5>

	</div>
	<div class="widget-body">
		<div class="widget-toolbox padding-8 clearfix">
			<form class="form-inline">
				<input type="text" class="form-control" name="phone" placeholder="手机号码">
				<input type="text" class="form-control" name="orderSystemUniqueId" placeholder="订单号">
				<app:propertySelect parentPath="business.orderSms.status" placeholder="状态" name="status"/>
				<div class="input-daterange input-group">
					<input type="text" class="form-control" placeholder="发送开始日期" name="sendTimeBegin"/>
					<span class="input-group-addon">
						<i class="fa fa-exchange"></i>
					</span>
					<input type="text" class="form-control" placeholder="发送截止日期" name="sendTimeEnd"/>
				</div>
				<button type="button" class="btn btn-info btn-sm event-search" 
					data-url="${ctx}/clientOrderSms/client/page/list" data-tpl="tpl-clientOrderSms">
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
						<th>订单号</th>
						<th>号码</th>
						<th>发送内容</th>
						<th>状态</th>
						<th>失败原因</th>
						<th>发送时间</th>
						<th>完成时间</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/admin_footer.jsp" flush="true">
	<jsp:param value="menu-clientOrderSms-list" name="menu"/>
</jsp:include>

<script id="tpl-clientOrderSms" type="text/html">
	{{# for(var i = 0; i < d.length; i++){ }}
      <tr>
        <td>{{=d[i].order_system_unique_id}}</td>
        <td>{{=d[i].phone}}</td>
        <td>
			{{# if(d[i].smsContent.length >= 25){ }}
				<div class="action-buttons">
					<span title="{{=d[i].smsContent}}">
						{{=d[i].smsContent.substring(0,20)}}***
						{{=d[i].smsContent.substring(d[i].smsContent.length - 5,d[i].smsContent.length)}}
					</span>
					<a href="javascript:void(0)" class="green bigger-140 show-details-btn" title="Show Details" onclick="showTemp('{{=d[i].order_sms_id}}');">
						<i id="ic{{=d[i].smsContent}}" class="ace-icon fa fa-angle-double-down"></i>
						<span class="sr-only">Details</span>
					</a>
				</div>

			{{# } else { }}
				<span>{{=d[i].smsContent}}</span>
			{{# } }}
		</td>
        <td>
			{{# if(d[i].status == 0) { }}
				等待发送
			{{# } else if(d[i].status == 1) { }}
				发送中
			{{# }else if(d[i].status == -1) { }}
				发送失败
			{{# }else if(d[i].status == -2) { }}
				订单暂停
			{{# }else if(d[i].status == 100) { }}
				发送成功
			{{# }else { }}
				未知
			{{# } }}
		</td>
        <td>{{=d[i].errorMsg}}</td>
        <td>{{=d[i].send_time}}</td>
        <td>{{=d[i].complete_time}}</td>
      </tr>
	  <tr id="{{=d[i].order_sms_id}}" class="detail-row" style="display:none">
		<td colspan="8">
			<div class="table-detail">
				<span>{{=d[i].smsContent}}</span>
			</div>
		</td>
	  </tr>
    {{# } }}
</script>

<script type="text/javascript">
$(function(){
	$('.input-daterange').datepicker({
		language: 'zh-CN',
		format : 'yyyy-mm-dd',
		autoclose: true,
		todayHighlight: true		
	});
	
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
