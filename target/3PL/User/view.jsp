<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="User">
	<jsp:attribute name="script">
<script>
$(document).ready(function(){
$('#useredit').addClass("active");
$("#userTable").DataTable({
	"paging": true,
    "lengthChange": false,
    "searching": false,
    "ordering": true,
    "info": true,
    "autoWidth": false
});
});
function toggleUser(id,enabled)
{
    window.location = '<c:url value="/User/enableDisable?id='+id+'&enabled='+enabled+'" />' ;
}

</script>

</jsp:attribute>
	<jsp:body>
			
 		<section class="content-header">
          <h1>
           Users
          </h1>
        </section>
          <div class="row">
            <div class="col-xs-14">
              <div class="box">
            <div class="box-body">
              
            </div>
					<!-- /.box-body -->
        <div class="box-footer"></div>
					<!-- /.box-header -->
                <div class="box-body">
                    <table id="userTable"
							class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>UserName</th>
                        <th>Role</th>
                        <th>Shippers</th>
                        <th>Actions</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${users}">
                    <tr>
                        <td>${item.userName}</td>
                        <td><c:forEach var="roles"
												items="${item.userRoles}">
                        	${roles.role}<br>
                        	</c:forEach></td>
                        <td>
                        <c:forEach var="shipper"
												items="${item.shippers}">
                        	${shipper.courier}<br>
                        </c:forEach>
                        </td>
                        <td>
                        <c:choose>
                        	<c:when test="${item.enabled == true }">
                        		<button class="btn btn-danger"
														onclick="toggleUser(${item.id},${item.enabled})">Disable</button>
                       		</c:when>
                        	<c:otherwise>
                        		<button class="btn btn-success"
														onclick="toggleUser(${item.id},${item.enabled})">Enable</button>
                        	</c:otherwise>
                        </c:choose>&nbsp;&nbsp;
                        <a href=<c:url value="/User/edit/${item.id}"/>
											class="btn btn-default">Edit</a>&nbsp;&nbsp;
                        <a
											href=<c:url value="/User/changePassword/${item.id}"/>>changepassword</a>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                    </table>
				</div>
		</div>
		</div>
		</div>
</jsp:body>
</tags:page>