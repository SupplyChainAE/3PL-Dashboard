<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="Cancellation">
<jsp:attribute name="script">
<script>
$(document).ready(function(){
$('#cancel').addClass("active");
$('#daterange').daterangepicker({
	format: 'YYYY-MM-DD',
	separator: ':'
});
// $.fn.dataTable.Buttons.swfPath = '//cdn.datatables.net/buttons/1.0.0/swf/flashExport.swf';

$("#cancTable").DataTable({
		"paging": true,
	    "lengthChange": true,
	    "searching": true,
	    "ordering": true,
	    "info": true,
	    "autoWidth": false,
	    
	    });
});

function save()
{
	var data ="${data}";
	$.ajax({
		url : "/3PL/Dashboard/cancellation/saveToFile",
		method : "GET",
		contentType : "application/json",
		dataType  : "Json",
		data : { data:data },
		success: function(data){}
	});
}
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
                  <div class="box-body">
              <form  name="filterform" action="<c:url value="/Dashboard/cancellation"/>">
             
               <div class="row">
             <div class="col-md-4">
                     <div class="form-group">
                    <label>Select Date</label>
                     <div class="input-group">
                      <div class="input-group-addon">
                        <i class="fa fa-calendar"></i>
                      </div>
                      <input type="text" class="form-control pull-right" name="daterange" id="daterange" />
                    </div>
                  </div><!-- /.form-group -->
                </div>
             </div> 
              <div align="center"><input class="btn btn-success" type="submit" value="Filter"></div>
             
              </form>
              
            </div><!-- /.box-body -->
        <div class="box-footer"></div>
          			<div class="box-body">
          			<br>
          			<c:if test="${ not empty data}">
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
                    <div align="center">
                    	<button class="btn btn-app" onclick="save();">
                    	<i 	class="fa fa-save"></i>Save to File
                    	</button>
                    </div>
                    </c:if>
                    </div>
                    
        		</div>
				</div>
			</div>
		

</jsp:body>
</tags:page>