<%@page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>

<form class="form-horizontal" method="post" id="form1">
	
	<div class="form-group">
		<label class="control-label col-xs-12 col-sm-3 col-md-3 no-padding-right"><span class="orange">* </span> 导入订单文件</label>

		<div class="col-xs-12 col-sm-5 no-padding-right">
			<input type="file" class="app-simple-file" id="uploadExcelFile" name="uploadExcelFile" required="required">
			<span class="orange">* 必须以模板格式为准,给我一个<a href="${ctx}/assets/tempFiles/phoneTemplate.xlsx" >phoneTemplate.xlsx</a>,
					<a href="${ctx}/assets/tempFiles/phoneTemplate.txt" download="phoneTemplate.txt">phoneTemplate.txt</a>模板吧!</span>
		</div>
	</div>

	<div class="form-group">
		<div class="col-xs-12 col-sm-4 col-sm-offset-3">
			<button type="button" class="btn btn-success btn-sm" onclick="ajaxSubmit1();">
				<i class="ace-icon fa fa-save bigger-110"></i> 保存
			</button>
			<button type="reset" class="btn btn-sm">
				<i class="ace-icon fa fa-undo bigger-110"></i> 重置
			</button>
		</div>
	</div>
</form>
<script type="text/javascript">
jQuery(function($) {
	$('.app-simple-file').ace_file_input({
		no_file:'空',
		btn_choose:'选择文件',
		btn_change:'重新选择',
		droppable:false,
		onchange:null,
		thumbnail:false, //| true | large
		allowExt:["xlsx", "xls", "txt"]
	});
});

function ajaxSubmit1(){
	//异步提交表单
	
     var options = {
         url: "${ctx}/clientOrderSms/client/importPhone",
         type: "post",
         dataType: "json",
         success: function (data) {
             if (data.status == "SUCCESS") {
            	 setPhonesVal(data.data);
            	 bootbox.hideAll();
             } else {
            	 app.alert(data.message);
             }
         }
     };
     $("#form1").ajaxSubmit(options);
	
	/* var data = $("#form1").serialize();
	$.ajax({
		url: '${ctx}/clientOrderSms/client/importPhone',
		type: 'post',
		data: data,
		async:false,
		dataType: 'json',
		success: function(data) {
			if(data.status == "SUCCESS"){
				setPhonesVal(data.data);
			}else{
				app.alert(data.message);
			}
		},
		error: function(msg) {
			app.alert("网络异常，请稍后再试！");
			console.log(msg);
		}
	});	 */
};
</script>
