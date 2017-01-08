<%@ page trimDirectiveWhitespaces="true" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page contentType="image/jpeg" import="java.io.OutputStream,java.io.InputStream,java.net.URL,java.net.URLConnection" language="java"%>
<%
// response.reset();
try{
OutputStream os = response.getOutputStream();
String picPath = request.getQueryString();
picPath = picPath.substring(4,picPath.length());
URLConnection u = new URL(picPath).openConnection();
InputStream in = u.getInputStream();
if (null != in) {
int len;
byte[] b = new byte[1024];
while ((len = in.read(b)) != -1) { // 循环读取
os.write(b, 0, len); // 写入到输出流
}
os.flush();
in.close();
}
os.close();
out.clear();
out = pageContext.pushBody();
}catch(Exception e){
e.printStackTrace();
}
%>