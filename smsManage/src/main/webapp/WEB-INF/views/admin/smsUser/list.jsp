<%@page pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/common/admin_header.jsp" %>

<div class="widget-box position-relative">
	<div class="widget-header">
		<h5 class="widget-title bigger lighter">
			<i class="ace-icon fa fa-table"></i>
			短信用户列表
		</h5>

		<div class="widget-toolbar no-border">
			<button class="btn btn-xs btn-success bigger event-dialog" data-url="${ctx}/smsUser/admin/add">
				<i class="ace-icon fa fa-plus"></i>
				新增短信用户
			</button>
		</div>

	</div>
	<div class="widget-body">
		<div class="widget-toolbox padding-8 clearfix">
			<form class="form-inline">
				<input type="text" class="form-control" name="nickname" placeholder="用户名">
				<input type="text" class="form-control" name="linkman" placeholder="联系人">
				<input type="text" class="form-control" name="phone" placeholder="联系电话">
				<input type="hidden" name="userType" value="0"/>

				<button type="button" class="btn btn-info btn-sm event-search" 
					data-url="${ctx}/smsUser/admin/showList" data-tpl="tpl-smsUser">
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
						<th>联系人</th>
						<th>联系电话</th>
						<th>邮箱</th>
						<th>余额（条）</th>
						<th>信用条数</th>
						<th>余额预警条数</th>
						<th>预警号码</th>
						<th>预警次数</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/admin_footer.jsp" flush="true">
	<jsp:param value="menu-smsUser-list" name="menu"/>
</jsp:include>

<script id="tpl-smsUser" type="text/html">
	{{# for(var i = 0; i < d.length; i++){ }}
      <tr>
        <td>{{=d[i].nickname}}</td>
        <td>{{=d[i].linkman}}</td>
        <td>{{=d[i].phone}}</td>
        <td>{{=d[i].email}}</td>
        <td>{{=d[i].balance}}</td>
        <td>{{=d[i].creditAmount}}</td>
        <td>{{=d[i].alertBalance}}</td>
        <td>{{=d[i].alertPhone}}</td>
        <td>{{=d[i].notifySend}}</td>
        <td>
			<shiro:hasPermission name="smsUser_edit">
            <button type="button" class="btn btn-xs btn-yellow event-dialog" data-url="${ctx}/smsUser/admin/edit/{{=d[i].id}}">
              <i class="ace-icon fa fa-pencil"></i>修改
            </button>
			<button type="button" class="btn btn-xs btn-yellow event-dialog" data-url="${ctx}/smsUser/admin/editKeyword/{{=d[i].id}}">
              <i class="ace-icon fa fa-pencil"></i>敏感字
            </button>
			</shiro:hasPermission>
        </td>
      </tr>
    {{# } }}
</script>
