<%@ tag description="Page Components" language="java"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="title" required="true"%>
<%@ attribute name="script" fragment="true"%>
<html>
<head>
<meta charset="utf-8">
<title>${title}</title>
<jsp:include page="/resources.jsp" />
<jsp:invoke fragment="script" />
</head>
<body class="skin-blue-light sidebar-mini">
	<div class="wrapper">
		<header class="main-header"> <jsp:include page="/head.jsp" />
		</header>
		<jsp:include page="/sidebar.jsp" />
		<div class="content-wrapper">

			<!-- Main content -->
			<section class="content"> 
				<jsp:doBody /> 
			</section>

			
		</div>
		<footer class="main-footer"> 
				<strong>Copyright &copy; Snapdeal.com </strong> 
			</footer>
	</div>
</body>
</html>
