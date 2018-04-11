<%@page pageEncoding="utf-8" %>
<%@include file="/WEB-INF/views/common/admin_header.jsp" %>

<div class="widget-box position-relative">
	<div class="widget-header">
		<h5 class="widget-title bigger lighter">
			<i class="ace-icon fa fa-table"></i>
			产品列表
		</h5>

		<div class="widget-toolbar no-border">
			<button class="btn btn-xs btn-success bigger event-dialog" data-url="${ctx}/product/admin/add">
				<i class="ace-icon fa fa-plus"></i>
				新增产品
			</button>
		</div>

	</div>
	<div class="widget-body">
		<div class="widget-toolbox padding-8 clearfix">
			<form class="form-inline">
				<input type="text" class="form-control" name="productName" placeholder="产品名称">
				<app:propertySelect parentPath="business.product.productType" placeholder="--产品类型--" name="productType"/>
				<app:propertySelect parentPath="business.product.ispCode" placeholder="--运营商--" name="ispCode"/>

				<button type="button" class="btn btn-info btn-sm event-search" 
					data-url="${ctx}/product/admin/page/list" data-tpl="tpl-product">
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
						<th>产品名称</th>
						<th>产品类型</th>
						<th>运营商</th>
						<!-- <th>归属地</th> -->
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
	<jsp:param value="menu-product-list" name="menu"/>
</jsp:include>

<script id="tpl-product" type="text/html">
	{{# for(var i = 0; i < d.length; i++){ }}
      <tr>
        <td>{{=d[i].productName}}</td>
        <td>
			{{# if(d[i].productType == 1) { }}
				通知类
			{{# } else if(d[i].productType == 2) { }}
				营销类
			{{# } }}
		</td>
        <td>
			{{# if(d[i].ispCode == 1) { }}
				中国移动
			{{# } else if(d[i].ispCode == 2) { }}
				中国联通
			{{# } else if(d[i].ispCode == 3) { }}
				中国电信
			{{# } }}
		</td>
        <!--<td>{{=d[i].province}}</td>-->
        <td>{{=d[i].memo}}</td>
        <td>
			<shiro:hasPermission name="product_edit">
            <button type="button" class="btn btn-xs btn-yellow event-dialog" data-url="${ctx}/product/admin/edit/{{=d[i].productId}}">
              <i class="ace-icon fa fa-pencil"></i>修改
            </button>
			</shiro:hasPermission>
        </td>
      </tr>
    {{# } }}
</script>
