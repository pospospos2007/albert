<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
 <link rel="shortcut icon" href="<%=path%>/images/favicon.ico">
	<meta charset="utf-8" />
	<meta name="author" content="Tencent.AlloyTeam.Svenzeng" />
	<meta name="copyright" content="Tencent.AlloyTeam" />
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
	<title>街头霸王 Street Fighter </title>
	<script src="js/StreetFighter/config.js"></script>
	<script src="js/StreetFighter/util.js"></script>
	<script src="js/StreetFighter/interface.js"></script>
	<script src="js/StreetFighter/ai.js"></script>
	<script src="js/StreetFighter/class.js"></script>
	<script src="js/StreetFighter/timer.js"></script>
	<script src="js/StreetFighter/map.js"></script>
	<script src="js/StreetFighter/main.js"></script>
	<script src="js/StreetFighter/game.js"></script>
</head>
<body>
<!--Google Analytics-->
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-23019343-9']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
</body>


