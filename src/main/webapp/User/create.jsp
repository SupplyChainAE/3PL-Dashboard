<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="User">
	<jsp:attribute name="script">
<script>
	$(document).ready(function() {
		$('#usercreate').addClass("active");
	});
</script>

</jsp:attribute>
	<jsp:body>
  
        <section class="content-header">
          <h1>
            Create User
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
                    
                    <div class="form-group">
                      <label for="userName">User Name</label>
                      <input class="form-control" name="userName"
											id="userName" placeholder="Enter Username"
											required="required">
                    </div>
                    
                    <div class="form-group">
                      <label for="password">Password</label>
                      <input type="password" class="form-control"
											name="password" id="password" placeholder="Password"
											required="required">
                    </div>
                    
                    <div class="form-group ">
                    
                    <label>Roles</label>
                    <div class="chosen-container-multi chosen-container">
                    <select name="role" multiple="multiple"
												class="form-control" required="required">
                      <c:forEach var="roles" items="${roles}">
                      <option value="${roles.id}">${roles.role}</option>
                      </c:forEach>
                    </select>
                    </div>
                  </div>
                  
                  <div class="form-group ">
                    <label>Shippers</label>
                                        <div
											class="chosen-container-multi chosen-container">
                    
                    <select name="shipper" multiple="multiple"
												class="form-control " required="required">
                     <c:forEach var="shipper" items="${shippers}">
                      <option value="${shipper.id}">${shipper.courier}</option>
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
