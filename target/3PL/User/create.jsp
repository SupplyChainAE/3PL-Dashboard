<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="User">
	<jsp:attribute name="script">
<script>
	$(document).ready(function() {
		$('#usercreate').addClass("active");
		$(".select2").select2();
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
        	
        		<form method="post" name="user" id="user" action="<c:url value="/User/save"/>" role="form">
        		  <div class="row">
                  <div class="col-xs-6">
                  	<div class="box-body">
                    
                    <div class="form-group">
                      <label for="userName">User Name</label>
                      <input class="form-control" name="userName" id="userName" placeholder="Enter Username">
                    </div>
                    
                    <div class="form-group">
                      <label for="password">Password</label>
                      <input type="password" class="form-control"
									name="password"	id="password" placeholder="Password">
                    </div>
                    
                    <div class="form-group ">
                    <div class="select2-container select2-container-multi">
                    <label>Roles</label>
                    <select name="role" multiple="multiple" class="form-control select2">
                      <c:forEach var="roles" items="${roles}">
                      <option value="${roles.id}">${roles.role}</option>
                      </c:forEach>
                    </select>
                    </div>
                  </div>
                  
                  <div class="form-group ">
                    <div class="select2-container select2-container-multi">
                    <label>Shippers</label>
                    <select name="shipper" multiple="multiple" class="form-control select2" >
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
