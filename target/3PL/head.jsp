<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ taglib uri="http://www.springframework.org/security/tags"	prefix="security"%>
<%@ page import="com.snapdeal.entity.User"%>
<%
	User sessionUser = (User) SecurityContextHolder.getContext() 
 			.getAuthentication().getPrincipal(); 

 	pageContext.setAttribute("name", sessionUser.getUserName()); 
 	pageContext.setAttribute("id", sessionUser.getId()); 
%>  

     <a href="<c:url value="/home"/>" class="logo">
          <!-- mini logo for sidebar mini 50x50 pixels -->
          <span class="logo-mini"><b>3</b>PL</span>
          <!-- logo for regular state and mobile devices -->
          <span class="logo-lg"><b>3PL </b>Dashboard</span>
        </a>		
<nav class="navbar navbar-static-top" role="navigation">
     
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
                      
              <!-- User Account: style can be found in dropdown.less -->
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                  <img src="<c:url value="/img/avatar.png"/>" class="user-image" alt="User Image" />
                  <span class="hidden-xs">${name}</span>
                </a>
                <ul class="dropdown-menu">
                    <li>
                   	<a href="<c:url value="/User/changePassword/${id}"/>">Change Password</a>
                    
                    </li>
                    <li >
                      <a href="<c:url value="/logout"/>" class="btn btn-default btn-flat">Sign out</a>
                    
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </nav>
