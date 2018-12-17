<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>_left</title>
		<meta charset="utf-8"/>
		<style type="text/css">
			/* css样式 */
			body{
				background-color: #32323A;
				font-family: "微软雅黑";
				font-size: 14px;
			}
			body,div{
				margin: 0px;
				padding:0px;
			}
			div#menu_bar{
				font-size: 20px;
				color:#FFFFFF;
			}
			div#menu_bar div{
				border-top: 1px solid #cccccc;
				padding: 5px 0;
				text-indent: 15px;
				letter-spacing: 3px;
			}
			div#menu_bar div:last-child{
				border-bottom: 1px solid #cccccc;
			}
			div#menu_bar div:hover{
				background-color: #797981;
			}
			div#menu_bar div a{
				font-size: 20px;
				color:#FFFFFF;
				text-decoration: none;
			}
		</style>
	</head>
	<body margin="0">
		<div id="menu_bar">
		
			<div><a href="${app }/backend/manageAddProd.jsp" target="rightFrame">> 商品添加</a></div>
			<div><a href="${app }/backend/ListProdServlet" target="rightFrame">> 商品修改</a></div>
			<div><a href="#" >> 权限管理</a></div>
			<div><a href="#" >> 订单管理</a></div>
			<div><a href="#" >> 销售榜单</a></div>
		</div>		
	</body>
</html>
