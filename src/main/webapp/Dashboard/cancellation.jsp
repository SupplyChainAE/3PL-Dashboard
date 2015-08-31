<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="Cancellation">
<jsp:attribute name="script">
<script>
$(document).ready(function(){
$('#cancel').addClass("active");
$("#cancTable").DataTable({
		"paging": true,
	    "lengthChange": true,
	    "searching": false,
	    "ordering": true,
	    "info": true,
	    "autoWidth": false
	    });
});
</script>

</jsp:attribute>
	<jsp:body>
  
        <section class="content-header">
          <h1>
            Cancellation Data
          </h1>
        </section>
          <div class="row">
            <div class="col-xs-14">
              <div class="box">
                	<!-- /.box-header -->
            
          
                    <table id="cancTable" class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>Shipper Group</th>
                        <th>Shipper</th>
                        <th>Mode</th>
                        <th>Center</th>
                        <th>AWB Number</th>
                        <th>Suborder Code</th>
                        <th>Order Date</th>
                        <th>SOI Status code</th>
                        <th>Seller Code</th>
                        <th>Seller Name</th>
                        <th>Seller City</th>
                        <th>Seller Pincode</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${data}">
                    <tr>
                        <td>${item.shipperGroup}</td>
                        <td>${item.shipper}</td>
                        <td>${item.mode}</td>
                        <td>${item.center}</td>
                        <td>${item.awbNumber}</td>
                        <td>${item.suborderCode}</td>
                        <td>${item.orderDate}</td>
                        <td>${item.soiStatusCode}</td>
                        <td>${item.sellerCode}</td>
                        <td>${item.sellerName}</td>
                        <td>${item.sellerCity}</td>
                        <td>${item.sellerPinCode}</td>
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