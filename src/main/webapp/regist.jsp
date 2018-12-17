<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>欢迎注册EasyMall</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="css/regist.css"/>
		<script type="text/javascript" src="js/jquery-1.4.2.js"></script>
		<script type="text/javascript">
		//用jQuery进行表单验证
		var formObj = {
			"checkNull":function(name,msg){//非空验证
				var value = $("input[name='"+name+"']").val();
				if($.trim(value)==""){
					this.setMsg(name,msg);
					return false;
				}else{
					this.setMsg(name,"");
					return true;
				}
			},
			"setMsg":function(name,msg){
				//用来设置消息
				$("input[name='"+name+"']").nextAll("span").html(msg).css("color","red");
			},
			"checkEmail":function(msg){//邮箱格式验证
				var reg = /^\w+@\w+(\.\w+)+/;
				var value = $("input[name='email']").val();
				if($.trim(value)!=""&&value.match(reg)){//格式正确
					this.setMsg("email","");
					return true;
				}else if($.trim(value)!=""&&!value.match(reg)){//格式不正确
					this.setMsg("email",msg);
					return false;
				}else{
					return false;
				}
			},
			"checkPassword":function(name,msg){//两次密码确认
				var pw1 = $("input[name='password']").val();
				var pw2 = $("input[name='password2']").val();
				if($.trim(pw1)!=""&&$.trim(pw2)&&pw1==pw2){//非空的时候才验证两次密码
					this.setMsg(name,"");
					return true;
				}else if($.trim(pw1)!=""&&$.trim(pw2)&&pw1!=pw2){
					this.setMsg(name,msg);
					return false;
				}else{
					return false;
				}
			},
			"checkHasUsername":function(){
				$.ajax({
					   type: "POST",
					   url: "/mall/UsernameAjax",
					   data: "username="+$("input[name='username']").val(),
					   success: function(msg){
							$("input[name='username']").nextAll("span").html(msg).css("color","red");
					   }
					});
			},
			"checkForm":function(){
				var flag = true;
				flag = this.checkNull("password","密码不能为空")&&flag;
				flag =this.checkNull("password","密码不能为空")&&flag;
				flag =this.checkNull("password2","用户名不能为空")&&flag;
				flag =this.checkNull("nickname","昵称不能为空")&&flag;
				flag =this.checkNull("email","邮箱不能为空")&&flag;
				flag =this.checkNull("valistr","验证码不能为空")&&flag;
				flag =this.checkPassword("password2","确认密码不正确")&&flag;
				flag =this.checkEmail("邮箱格式不正确")&&flag;
				var text = $("input[name='username']").nextAll("span").text();
				if(text=="用户名已存在"||text.length>0){
					flag=false;
				}
				return flag;
			}
			
		}

		$(function(){//添加焦点事件
			$("input[name='username']").blur(function(){
				var b = formObj.checkNull("username","用户名不能为空");
			  	if(b){
					formObj.checkHasUsername();
				} 
			 	
			});
			$("input[name='password']").blur(function(){
				formObj.checkNull("password","密码不能为空");
				formObj.checkPassword("password2","确认密码不正确");
			});
			$("input[name='password2']").blur(function(){
				formObj.checkNull("password2","确认密码不能为空");
				formObj.checkPassword("password2","确认密码不正确");
			});
			$("input[name='nickname']").blur(function(){
				formObj.checkNull("nickname","昵称不能为空");
			});
			$("input[name='email']").blur(function(){
				formObj.checkNull("email","邮箱不能为空");
				formObj.checkEmail("邮箱格式不正确");
			});
			$("input[name='valistr']").blur(function(){
				formObj.checkNull("valistr","验证码不能为空");
			});
			$(".valistrImg").click(function(){
				var date = new Date();
				$(".valistrImg").attr("src","/mall/VerifyCodeServlet?"+date.getTime());
				
			});
			
		});
		
		
		
		</script>
		
	</head>
	<body>
		<form action="/RegistServlet" method="POST" onsubmit="return formObj.checkForm()">
			<h1>欢迎注册EasyMall</h1>
			<table>
				<tr>
					<td colspan="2" style="text-algin:center;color:red">
						<%=request.getAttribute("errMsg")==null?"":request.getAttribute("errMsg") %>
					</td>
				</tr>
				<tr>
					<td class="tds">用户名：</td>
					<td>
						<input type="text" name="username" value="<%=request.getParameter("username")==null?"":request.getParameter("username")  %>"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">密码：</td>
					<td>
						<input type="password" name="password" value="<%=request.getParameter("password")==null?"":request.getParameter("password")  %>"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">确认密码：</td>
					<td>
						<input type="password" name="password2" value="<%=request.getParameter("password2")==null?"":request.getParameter("password2")  %>"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">昵称：</td>
					<td>
						<input type="text" name="nickname" value="<%=request.getParameter("nickname")==null?"":request.getParameter("nickname")  %>"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">邮箱：</td>
					<td>
						<input type="text" name="email" value="<%=request.getParameter("email")==null?"":request.getParameter("email")  %>"/>
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">验证码：</td>
					<td>
						<input type="text" name="valistr" value="<%=request.getParameter("valistr")==null?"":request.getParameter("valistr")  %>"/>
						<img class="valistrImg" src="${app }/VerifyCodeServlet" width="" height="" alt="" />
						<span></span>
					</td>
				</tr>
				<tr>
					<td class="sub_td" colspan="2" class="tds">
						<input type="submit" value="注册用户"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
