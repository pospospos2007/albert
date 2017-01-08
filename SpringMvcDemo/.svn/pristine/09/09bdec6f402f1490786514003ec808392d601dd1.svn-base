<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
    <title>user_main</title>
    

  </head>
  
  <body>

		<table border="1">
			<tbody>
				<tr>
					<th>id</th>
					<th>Name</th>
				</tr>
				<c:if test="${!empty users }">
					<c:forEach items="${users}" var="user">
						<tr>
							<td>${user.userId }</td>
							<td>${user.userName }</td>
						</tr>				
					</c:forEach>
				</c:if>
			</tbody>
		</table>
	
  </body>
</html>
