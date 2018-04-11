<%@page pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/common/admin_header.jsp" %>

<div class="row">
	<div class="col-xs-12">
		<!-- PAGE CONTENT BEGINS -->
		<div>
			<div id="user-profile-1" class="user-profile row">
				<div class="col-xs-12 col-sm-3 center">
					<div>
						<span class="profile-picture"> <img id="avatar"
							class="editable img-responsive editable-click editable-empty"
							alt="Alex的头像" src="../../assets/avatars/profile-pic.jpg">
						</span>

						<div class="space-4"></div>

						<div
							class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
							<div class="inline position-relative">
								<a href="#" class="user-title-label dropdown-toggle"
									data-toggle="dropdown"> <i
									class="ace-icon fa fa-circle light-green"></i> &nbsp; <span
									class="white"><font style="vertical-align: inherit;"><font
											style="vertical-align: inherit;">${item.nickname }</font></font></span>
								</a>
							</div>
						</div>
					</div>

					<div class="space-6"></div>

					<div class="hr hr12 dotted"></div>

					<div class="clearfix">
						<div class="grid2">
							<span class="bigger-175 blue"><font
								style="vertical-align: inherit;"><font
									style="vertical-align: inherit;">${item.balance} 条</font></font></span> <br>
							<font style="vertical-align: inherit;"><font
								style="vertical-align: inherit;"> 剩余条数 </font></font>
						</div>

						<div class="grid2">
							<span class="bigger-175 blue"><font
								style="vertical-align: inherit;"><font
									style="vertical-align: inherit;">${billSize} 条</font></font></span> <br>
							<font style="vertical-align: inherit;"><font
								style="vertical-align: inherit;"> 已用条数 </font></font>
						</div>
					</div>

					<div class="hr hr16 dotted"></div>
				</div>

				<div class="col-xs-12 col-sm-9">

					<div class="space-12"></div>

					<div class="profile-user-info profile-user-info-striped">
						<div class="profile-info-row">
							<div class="profile-info-name">
								<font style="vertical-align: inherit;"><font
									style="vertical-align: inherit;"> 用户名 </font></font>
							</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id="nickname"><font
										style="vertical-align: inherit;">${item.nickname}<c:if test="${item.nickname == ''}">无</c:if></font></span>
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">
								<font style="vertical-align: inherit;"><font
									style="vertical-align: inherit;"> 联系人 </font></font>
							</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id="linkman"><font
									style="vertical-align: inherit;"><font
										style="vertical-align: inherit;">${item.linkman }<c:if test="${item.linkman == ''}">无</c:if></font></font></span>
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">
								<font style="vertical-align: inherit;"><font
									style="vertical-align: inherit;"> 联系电话 </font></font>
							</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id="phone"><font
									style="vertical-align: inherit;"><font
										style="vertical-align: inherit;">${item.phone }<c:if test="${item.phone == ''}">无</c:if></font></font></span>
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">
								<font style="vertical-align: inherit;"><font
									style="vertical-align: inherit;"> 邮箱 </font></font>
							</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id="email"><font
									style="vertical-align: inherit;"><font
										style="vertical-align: inherit;">${item.email }<c:if test="${item.email == ''}">无</c:if></font></font></span>
							</div>
						</div>

						<div class="profile-info-row">
							<div class="profile-info-name">
								<font style="vertical-align: inherit;"><font
									style="vertical-align: inherit;"> 余额预警条数 </font></font>
							</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id="alertBalance"><font
									style="vertical-align: inherit;"><font
										style="vertical-align: inherit;">${item.alertBalance }<c:if test="${item.alertBalance == ''}">无</c:if></font></font></span>
							</div>
						</div>
						
						<div class="profile-info-row">
							<div class="profile-info-name">
								<font style="vertical-align: inherit;"><font
									style="vertical-align: inherit;"> 预警号码 </font></font>
							</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id="alertPhone"><font
									style="vertical-align: inherit;"><font
										style="vertical-align: inherit;">${item.alertPhone }<c:if test="${item.alertPhone == ''}">无</c:if></font></font></span>
							</div>
						</div>
						
						<div class="profile-info-row">
							<div class="profile-info-name">
								<font style="vertical-align: inherit;"><font
									style="vertical-align: inherit;"> 状态回调地址 </font></font>
							</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id="notifyUrl"><font
									style="vertical-align: inherit;"><font
										style="vertical-align: inherit;">${item.notifyUrl }<c:if test="${item.notifyUrl == '' || item.notifyUrl == null}">无</c:if></font></font></span>
							</div>
						</div>
						
						<div class="profile-info-row">
							<div class="profile-info-name">
								<font style="vertical-align: inherit;"><font
									style="vertical-align: inherit;"> 上行回调地址 </font></font>
							</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id=upstreamUrl><font
									style="vertical-align: inherit;"><font
										style="vertical-align: inherit;">${item.upstreamUrl }<c:if test="${item.upstreamUrl == '' || item.upstreamUrl == null}">无</c:if></font></font></span>
							</div>
						</div>
						
						<div class="profile-info-row">
							<div class="profile-info-name">
								<font style="vertical-align: inherit;"><font
									style="vertical-align: inherit;"> accessKey</font></font>
							</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id="username"><font
									style="vertical-align: inherit;"><font
										style="vertical-align: inherit;">${item.username }</font></font></span>
							</div>
						</div>
						
						<div class="profile-info-row">
							<div class="profile-info-name">
								<font style="vertical-align: inherit;"><font
									style="vertical-align: inherit;"> appSecret</font></font>
							</div>

							<div class="profile-info-value">
								<span class="editable editable-click" id="appsecret"><font
									style="vertical-align: inherit;"><font
										style="vertical-align: inherit;">${item.appsecret }<c:if test="${item.appsecret == '' || item.appsecret == null}">无</c:if></font></font></span>
							</div>
						</div>
					</div>

					<div class="space-20"></div>

					<div class="hr hr2 hr-double"></div>

					<div class="space-6"></div>

				</div>
			</div>
		</div>

		
					</div>
				</div>
			</div>
		</div>

		<!-- PAGE CONTENT ENDS -->
	</div>
	<!-- /.col -->
</div>

<jsp:include page="/WEB-INF/views/common/admin_footer.jsp" flush="true">
	<jsp:param value="menu-clientUser-list" name="menu"/>
</jsp:include>

<script type="text/javascript">

	jQuery(function($) {

		$('#linkman').editable({
            type: "text",             //编辑框的类型。支持text|textarea|select|date|checklist等
            title: "联系人",              //编辑框的标题
            disabled: false,             //是否禁用编辑
            emptytext: "空文本",          //空值的默认文本
            mode: "popup", 			//编辑框的模式：支持popup和inline两种模式，默认是popup
            name:"linkman",
            validate: function (value) { //字段验证
                if (!$.trim(value)) {
                    return '不能为空';
                }else{
                	//发送请求
                	var data = {"userId":"${item.userId}","linkman":value};
                	ajaxUpdate(data);
                }
            }
        });
		
		$('#phone').editable({
            type: "text",             //编辑框的类型。支持text|textarea|select|date|checklist等
            title: "联系电话",              //编辑框的标题
            disabled: false,             //是否禁用编辑
            emptytext: "空文本",          //空值的默认文本
            mode: "popup", 			//编辑框的模式：支持popup和inline两种模式，默认是popup
            name:"phone",
            validate: function (value) { //字段验证
                if (!$.trim(value)) {
                    return '不能为空';
                }else{
                	//发送请求
                	var data = {"userId":"${item.userId}","phone":value};
                	ajaxUpdate(data);
                }
            }
        });
		
		$('#email').editable({
            type: "text",             //编辑框的类型。支持text|textarea|select|date|checklist等
            title: "邮箱",              //编辑框的标题
            disabled: false,             //是否禁用编辑
            emptytext: "空文本",          //空值的默认文本
            mode: "popup", 			//编辑框的模式：支持popup和inline两种模式，默认是popup
            name:"email",
            validate: function (value) { //字段验证
                if (!$.trim(value)) {
                    return '不能为空';
                }else{
                	//发送请求
                	var data = {"userId":"${item.userId}","email":value};
                	ajaxUpdate(data);
                }
            }
        });
		
		$('#alertBalance').editable({
            type: "text",             //编辑框的类型。支持text|textarea|select|date|checklist等
            title: "余额预警条数",              //编辑框的标题
            disabled: false,             //是否禁用编辑
            emptytext: "空文本",          //空值的默认文本
            mode: "popup", 			//编辑框的模式：支持popup和inline两种模式，默认是popup
            name:"alertBalance",
            validate: function (value) { //字段验证
                if (!$.trim(value)) {
                    return '不能为空';
                }else{
                	//发送请求
                	var data = {"userId":"${item.userId}","alertBalance":value};
                	ajaxUpdate(data);
                }
            }
        });
		
		$('#alertPhone').editable({
            type: "text",             //编辑框的类型。支持text|textarea|select|date|checklist等
            title: "预警号码",              //编辑框的标题
            disabled: false,             //是否禁用编辑
            emptytext: "空文本",          //空值的默认文本
            mode: "popup", 			//编辑框的模式：支持popup和inline两种模式，默认是popup
            name:"alertPhone",
            validate: function (value) { //字段验证
                if (!$.trim(value)) {
                    return '不能为空';
                }else{
                	//发送请求
                	var data = {"userId":"${item.userId}","alertPhone":value};
                	ajaxUpdate(data);
                }
            }
        });
		
		$('#notifyUrl').editable({
            type: "text",             //编辑框的类型。支持text|textarea|select|date|checklist等
            title: "状态回调地址",              //编辑框的标题
            disabled: false,             //是否禁用编辑
            emptytext: "空文本",          //空值的默认文本
            mode: "popup", 			//编辑框的模式：支持popup和inline两种模式，默认是popup
            name:"notifyUrl",
            validate: function (value) { //字段验证
                if (!$.trim(value)) {
                    return '不能为空';
                }else{
                	//发送请求
                	var data = {"userId":"${item.userId}","notifyUrl":value};
                	ajaxUpdate(data);
                }
            }
        });
		
		$('#upstreamUrl').editable({
            type: "text",             //编辑框的类型。支持text|textarea|select|date|checklist等
            title: "上行回调地址",              //编辑框的标题
            disabled: false,             //是否禁用编辑
            emptytext: "空文本",          //空值的默认文本
            mode: "popup", 			//编辑框的模式：支持popup和inline两种模式，默认是popup
            name:"upstreamUrl",
            validate: function (value) { //字段验证
                if (!$.trim(value)) {
                    return '不能为空';
                }else{
                	//发送请求
                	var data = {"userId":"${item.userId}","upstreamUrl":value};
                	ajaxUpdate(data);
                }
            }
        });
		
		/* 异步修改用户信息 */
		var ajaxUpdate = function(data){
			$.ajax({
				url: '${ctx}/clientUser/client/update',
				type: 'post',
				data: data,
				dataType: 'json',
				success: function(data) {
					var status = data.status;
					//app.alert(status);
					console.log(status);
				},
				error: function(msg) {
					app.alert('系统异常，请稍后再试');
				}
			});
		};

	});
</script>

</script>
