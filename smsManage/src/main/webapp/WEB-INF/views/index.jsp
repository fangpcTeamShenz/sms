<%@page pageEncoding="utf-8" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request"/>
<!DOCTYPE html>
<html>
	<head>
	</head>
		
	<body>
		<button type="submit" id="test"> 提交  </button>
		<input type="text" id="123">
	</body>
</html>
	<script src="${ctx}/smsManage/assets/js/jquery.min.js"></script>
	<script src="${ctx}/smsManage/assets/js/sha256.js"></script>
<script type="text/javascript">
jQuery(function($) {
	$(document).on("click","#test",function(){
		/* alert(sha256_digest('123456'));
		$("#123").val(sha256_digest('123456')); */
		$.post(
			'${ctx}/smsManage/api/userSmsContent/admin/page/list',
			{
			    "userId": 1000
			},
			function(res){
				alert(1);
			}
		);
	});
});
</script>