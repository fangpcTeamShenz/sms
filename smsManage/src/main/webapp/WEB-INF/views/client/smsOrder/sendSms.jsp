<%@page pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/common/admin_header.jsp" %>
<style>
	.divLeft {
		margin-left: 10%;
	}
</style>
<div class="row">
	<div class="col-sm-5" style="width: 75%; ">
		<div class="widget-box">
			<div class="widget-header" style="text-align: center;">
				<h4 class="widget-title">发送短信</h4>
			</div>

			<div class="widget-body">
				<div class="widget-main no-padding">
					<form>
						<!-- <legend>Form</legend> -->
						<fieldset>
							<div class="divLeft">
								<label for="form-field-9">号码 ：</label> 
								<input type="text" id="phone" placeholder="手机号码多个以逗号隔开“,”" style="width: 75%;" />
								<button class="btn btn-info btn-sm event-dialog" style="margin-top: -5px;"
									title="导入手机号码" data-url="${ctx}/clientOrderSms/client/import">
									<i class="ace-icon glyphicon glyphicon-upload bigger-110"></i>导入号码
								</button>
							</div>
							<br>
							<div class="divLeft">
								<label for="form-field-9">签名 ：</label> 
								<select name="smsSignatureId" style="width:200px;" id=smsSignatureId>
								    <option value="">--签名--</option>
									<c:forEach items="${signatures}" var="obj">
										<option value="${obj.smsSignatureId}">【${obj.signContent}】</option>
									</c:forEach>
								</select>
							</div>
							<br>
							<%-- <div class="divLeft">
								<label for="form-field-9">模板 ：</label> 
								<select name="smsTemplateId" style="width:200px;" id="smsTemplateId" >
									<option value="">--模板--</option>
									<c:forEach items="${templates}" var="obj">
										<option value="${obj.smsTemplateId}" data-content="${obj.templateContent }">${obj.templateName}</option>
									</c:forEach>
								</select>
							</div> 
							<br>--%>
							<div class="divLeft">
								<label for="form-field-9">短信内容：</label>
								<textarea rows="4" class="form-control limited" style="width: 80%;" id="templateContent"
									maxlength="50"></textarea>
								<br>
								<label style="color: red;">短信内容长度为：<span id="number">0</span> 个字</label>
							</div>

						</fieldset>

						<div class="form-actions center">
							<button type="button" class="btn btn-sm btn-success" onclick="ajaxSubmit();">
								发送 <i
									class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
							</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<div class="col-sm-5" style="width: 25%; ">
		<div class="widget-box">
			<div class="widget-header" style="text-align: center;">
				<h4 class="widget-title">注意事项</h4>
			</div>

			<div class="widget-body">
				<div class="widget-main no-padding">
					<span style="color: red; padding-left: 5px; ">
						<p>&nbsp;&nbsp;1、单次提交最好不超过20万个号码。 </p>
						<p>&nbsp;&nbsp;2、手动输入号码请用英文逗号或换行分隔。 </p>
						<p>&nbsp;&nbsp;3、内容编辑完成请先检查屏蔽词再行发送。 </p>
						<p>&nbsp;&nbsp;4、号码文件只支持TXT、EXCEL文件格式。</p>
						<p>&nbsp;&nbsp;5、请使用CTRL+V粘贴短信内容。</p>
						<p>&nbsp;&nbsp;6、汉字、数字、英文和标点符号都表示1个长度。</p>
						<p>&nbsp;&nbsp;7、发手机一条为70个字。长短信为每条67个字。</p>
						<p>&nbsp;&nbsp;8、小灵通一条为60个字，长短信在每60个字的基础上每加54个字算加一条。</p>
						<p>&nbsp;&nbsp;9、短信内容实际长度=短信签名+短信内容。</p>
					</span>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="widget-box position-relative">
		<div class="widget-header">
			<h5 class="widget-title bigger lighter">
				<i class="ace-icon fa fa-table"></i> 短信记录
			</h5>

		</div>
		<div class="widget-body">
			<div class="widget-toolbox padding-8 clearfix">
				<form class="form-inline">
					<input type="hidden" class="form-control" name="pageSize" value="5">
					<button type="button" class="btn btn-info btn-sm event-search"
						style="display: none;"
						data-url="${ctx}/clientOrderSms/client/page/list"
						data-tpl="tpl-clientSendSms">
						<i class="ace-icon fa fa-search bigger-110"></i> 搜索
					</button>
					<button type="reset" class="btn btn-sm" style="display: none;">
						<i class="ace-icon fa fa-undo bigger-110"></i> 清空
					</button>
					</p>
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
		<jsp:param value="menu-clientSendSms-list" name="menu" />
	</jsp:include>

	<script id="tpl-clientSendSms" type="text/html">
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
//获取短信内容长度
$("#templateContent").bind("input propertychange", function () {
	var txtval = $.trim($("#templateContent").val());
	$("#number").html(txtval.length);
});

//异步发送短信
var ajaxSubmit = function(){
	console.log(111);
	var phones = $("#phone").val();
	var smsSignatureId = $("#smsSignatureId").val();
	//var smsTemplateId = $("#smsTemplateId").val();
	var templateContent = $("#templateContent").val();
	if(phones == ''){
		app.alert("手机号码不能为空！");
	}else if(smsSignatureId == ''){
		app.alert("签名不能为空！");
	}else if(templateContent == ''){
		app.alert("模板内容不能为空！");
	}else{
		var data = {"phones":phones, "smsSignatureId":smsSignatureId, "templateContent":templateContent};
		//发送
		$.ajax({
			url: '${ctx}/clientOrderSms/client/send',
			type: 'post',
			data: data,
			dataType: 'json',
			success: function(data) {
				app.alert(data.data);
			},
			error: function(msg) {
				app.alert('系统异常，请稍后再试');
			}
		});
	}
};

//设置手机号码的值
var setPhonesVal = function(val){
	$("#phone").val(val);
};

//设置短信内容
$("#smsTemplateId").change(function(){
	var templateContent = $("#smsTemplateId").find("option:selected").attr("data-content");
	if(templateContent != ''){
		$("#templateContent").val(templateContent);
	}
});
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

</script>