<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder" %>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="css/login.css"/>
		<title>EasyMall欢迎您登陆</title>
	</head>
	<body>
		<%
		/*拥有自动登录cookie直接重定向到登录验证处  */
		Cookie[] cs = request.getCookies();
		Cookie autologin = null;
		Cookie remname = null;
		String username = "";
		String password = "";
		
		if(cs!=null){
			for(Cookie c:cs){
				if("autologin".equals(c.getName())){
					/* 这里不是重定向, */
					autologin = c;
					String s = URLDecoder.decode(c.getValue(), "utf-8");
					username = s.split(";")[0];
					password = s.split(";")[1];
					break;
				}else if("remname".equals(c.getName())){
					remname = c;
					username = URLDecoder.decode(c.getValue(), "utf-8");
				}
			}
		}
		
		%>
	
		<h1>欢迎登陆EasyMall</h1>
		<form action="${ applicationScope.app }/LoginServlet" method="POST">
			<table>
				<tr>
					<td colspan="2" style="text-algin:center;color:red">
						<%=request.getAttribute("error")==null?"":request.getAttribute("error") %>
					</td>
				</tr>
				<tr>
					<td class="tdx">用户名：</td>
					<td><input type="text" name="username" value="<%=request.getParameter("username")==null?"":request.getParameter("username")  %><%=username %>" /></td>
				</tr>
				<tr>
					<td class="tdx">密&nbsp;&nbsp; 码：</td>
					<td><input type="password" name="password" value="<%=request.getParameter("password")==null?"":request.getParameter("password")  %><%=password %> "/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox" name="remname" checked='<%=remname==null?"":"checked" %>' value="true"/>记住用户名
						<input type="checkbox" name="autologin" checked='<%=autologin==null?"":"checked" %>' value="true"/>30天内自动登陆
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
						<input type="submit" value="登 陆"/>
					</td>
				</tr>
			</table>
		</form>		
	</body>
</html>
