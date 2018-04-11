<%@page pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/common/admin_header.jsp" %>

<div class="widget-box position-relative">
	<div class="widget-header">
		<h5 class="widget-title bigger lighter">
			<i class="ace-icon fa fa-table"></i>
			模块列表
		</h5>

		<div class="widget-toolbar no-border">
			<button class="btn btn-xs btn-success bigger event-dialog" data-url="${ctx}/module/admin/add">
				<i class="ace-icon fa fa-plus"></i>
				新增模块
			</button>
		</div>

	</div>
	<div class="widget-body">
		<div class="widget-toolbox padding-8 clearfix">
			<form class="form-inline">
				<input type="text" class="form-control" name="moduleName" placeholder="模块名称">
				<input type="text" class="form-control" name="channelName" placeholder="通道名称">
				<app:propertySelect parentPath="business.module.useable" placeholder="--是否可用--" name="useable"/>

				<button type="button" class="btn btn-info btn-sm event-search" 
					data-url="${ctx}/module/admin/page/list" data-tpl="tpl-module">
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
						<th>模块名称</th>
						<th>通道名称</th>
						<th>对接地址</th>
						<th>对接密钥</th>
						<th>是否可用</th>
						<th>扩展码</th>
						<th>备注</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
</div>

<jsp:include page="/WEB-INF/views/common/admin_footer.jsp" flush="true">
	<jsp:param value="menu-module-list" name="menu"/>
</jsp:include>

<script id="tpl-module" type="text/html">
	{{# for(var i = 0; i < d.length; i++){ }}
      <tr>
        <td>{{=d[i].moduleName}}</td>
        <td>{{=d[i].channelName}}</td>
        <td>{{=d[i].moduleUrl}}</td>
        <td>{{=d[i].moduleKey}}</td>
        <td>
			{{# if(d[i].useable == 0) { }}
				不可用
			{{# } else if(d[i].useable == 1) { }}
				可用
			{{# } }}
		</td>
        <td>{{=d[i].extNumber}}</td>
        <td>{{=d[i].memo}}</td>
        <td>
			<shiro:hasPermission name="module_edit">
            <button type="button" class="btn btn-xs btn-yellow event-dialog" data-url="${ctx}/module/admin/edit/{{=d[i].moduleId}}">
              <i class="ace-icon fa fa-pencil"></i>修改
            </button>
			</shiro:hasPermission>
        </td>
      </tr>
    {{# } }}
</script>
