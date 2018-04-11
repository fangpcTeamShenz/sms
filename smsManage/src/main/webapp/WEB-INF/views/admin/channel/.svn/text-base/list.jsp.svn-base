<%@page pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/common/admin_header.jsp" %>

<div class="widget-box position-relative">
	<div class="widget-header">
		<h5 class="widget-title bigger lighter">
			<i class="ace-icon fa fa-table"></i>
			通道列表
		</h5>

		<div class="widget-toolbar no-border">
			<button class="btn btn-xs btn-success bigger event-dialog" data-url="${ctx}/channel/admin/add">
				<i class="ace-icon fa fa-plus"></i>
				新增通道
			</button>
		</div>

	</div>
	<div class="widget-body">
		<div class="widget-toolbox padding-8 clearfix">
			<form class="form-inline">
				<input type="text" class="form-control" name="channelName" placeholder="通道名称">
				<app:propertySelect parentPath="business.channel.useable" placeholder="--是否可用--" name="useable"/>
				<app:propertySelect parentPath="business.channel.existCallback" placeholder="--是否有回调--" name="existCallback"/>
				<app:propertySelect parentPath="business.channel.existUpper" placeholder="--是否有上行--" name="existUpper"/>

				<button type="button" class="btn btn-info btn-sm event-search" 
					data-url="${ctx}/channel/admin/page/list" data-tpl="tpl-channel">
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
						<th>通道名称</th>
						<th>是否可用</th>
						<th>是否有回调</th>
						<th>是否有上行</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/admin_footer.jsp" flush="true">
	<jsp:param value="menu-channel-list" name="menu"/>
</jsp:include>

<script id="tpl-channel" type="text/html">
	{{# for(var i = 0; i < d.length; i++){ }}
      <tr>
        <td>{{=d[i].channelName}}</td>
        <td>
			{{# if(d[i].useable == 0) { }}
				不可用
			{{# } else if(d[i].useable == 1) { }}
				可用
			{{# } }}
		</td>
        <td>
			{{# if(d[i].existCallback == 0) { }}
				无
			{{# } else if(d[i].existCallback == 1) { }}
				有
			{{# } }}
		</td>
        <td>
			{{# if(d[i].existUpper == 0) { }}
				无
			{{# } else if(d[i].existUpper == 1) { }}
				有
			{{# } }}
		</td>
        <td>
			<shiro:hasPermission name="channel_edit">
            <button type="button" class="btn btn-xs btn-yellow event-dialog" data-url="${ctx}/channel/admin/edit/{{=d[i].channelId}}">
              <i class="ace-icon fa fa-pencil"></i>修改
            </button>
			</shiro:hasPermission>
        </td>
      </tr>
    {{# } }}
</script>
