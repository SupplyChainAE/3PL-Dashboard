<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="User">
	<jsp:attribute name="script">
<script>
	$(document).ready(function() {
		$('#useredit').addClass("active");
	});
</script>

</jsp:attribute>
	<jsp:body>
  
        <section class="content-header">
          <h1>
            Edit User
          </h1>
        </section>
     
         <div class="row">
            <div class="col-xs-14">
              <div class="box">
        	
        		<form method="post" name="user" id="user"
						action="<c:url value="/User/save"/>" role="form">
        		  <div class="row">
                  <div class="col-xs-6">
                  	<div class="box-body">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="hidden" name="password"
										value="${user.password}">
                    <div class="form-group">
                      <label for="userName">User Name</label>
                      <input class="form-control" name="userName"
											id="userName" placeholder="Enter Username"
											value="${user.userName}" required="required">
                    </div>
          
                                
                    
                    <div class="form-group ">
                    <label>Roles</label>
                                        <div
											class="chosen-container-multi chosen-container">
                    
                    <select name="role" multiple="multiple"
												class="form-control " required="required" data-rel="chosen">
                      <c:forEach var="roles" items="${roles}">
                      			<c:set var="found" value="false" />
                      			<c:forEach var="savedRole"
														items="${user.userRoles}">
									<c:if test="${roles.id eq savedRole.id}">
										<option value="${roles.id}" selected="selected">${roles.role}</option>
									<c:set var="found" value="true" />
									</c:if>
								</c:forEach>
			                      <c:if test="${not found}">
									<option value="${roles.id}">${roles.role}</option>
								  </c:if>
                      </c:forEach>
                    </select>
                    </div>
                  </div>
                  
                  <div class="form-group ">
                    
                    <label>Shippers</label>
                    <div class="chosen-container-multi chosen-container">
                    
                    <select name="shipper" multiple="multiple"
												class="form-control " required="required" data-rel="chosen">
                     <c:forEach var="shipper" items="${shippers}">
							<c:forEach var="savedShipper" items="${user.shippers}">
									<c:set var="found" value="false" />
									<c:if test="${shipper.id eq savedShipper.id}">
										<option value="${shipper.id}" selected="selected">${shipper.courier}</option>
									<c:set var="found" value="true" />
									</c:if>
								</c:forEach>
			                      <c:if test="${not found}">
									<option value="${shipper.id}">${shipper.courier}</option>
								  </c:if>
                      </c:forEach>
                    </select>
                  	</div>
             	 </div>
             	 
             	  <div class="form-group">
                    <input type="submit" class="btn btn-success">
             	 </div>
              		</div>
              	</div>
              	</div>
              </form>
              </div>
            </div>
         </div>

	</jsp:body>
</tags:page>
