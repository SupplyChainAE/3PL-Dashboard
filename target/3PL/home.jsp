<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page import="com.snapdeal.entity.User"%>
<%
    User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    
	pageContext.setAttribute("name", sessionUser.getUserName());
%>

<tags:page title="Home">
<jsp:attribute name="script">
<script>
$('#dashboard').addClass('active');
</script>
</jsp:attribute>
<jsp:body>
<div align ="center">
<h1>Welcome ${name} </h1>
</div>
</jsp:body>
</tags:page>



