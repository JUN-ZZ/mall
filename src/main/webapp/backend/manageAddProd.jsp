<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<style type="text/css">
			h1{
				text-align: center;
			}
		</style>
	</head>
	<body>
		<h1>EasyMall_添加商品</h1>
		<hr>
			<form action="${app }/AddProdServlet" method="POST" enctype="multipart/form-data">
				<table align="center" border="1" cellspacing="0px" cellpadding="5px">
					<tr>
						<td colspan="2" style="text-align:center;color:red" >${errMsg}</td>
					</tr>
					<tr>
						<td>商品名称</td>
						<td><input type="text" name="name"/></td>
					</tr>
					<tr>
						<td>商品单价</td>
						<td><input type="text" name="price"/></td>
					</tr>
					<tr>
						<td>商品种类</td>
						<td><input type="text" name="cname"/></td>
					</tr>
					<tr>
						<td>库存数量</td>
						<td><input type="text" name="pnum"/></td>
					</tr>
					<tr>
						<td>商品图片</td>
						<td><input type="file" name="fx"/></td>
					</tr>
					<tr>
						<td>描述信息</td>
						<td>
							<textarea rows="5" cols="30" name="description"></textarea>
						</td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="添加商品"/></td>
					</tr>
				</table>
			</form>
		<hr>
	</body>
</html>
