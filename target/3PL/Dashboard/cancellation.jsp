<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="Cancellation">
<jsp:attribute name="script">
<script>
$(document).ready(function(){
$('#cancel').addClass("active");

$('#daterange').daterangepicker({
    format: 'DD-MM-YYYY',
    startDate: moment().subtract(29, 'days'),
    endDate: moment(),
    minDate: '01-01-2015',
    maxDate: '31-12-2016',
    dateLimit: { days: 60 },
    showDropdowns: true,
    showWeekNumbers: true,
    timePicker: false,
    timePickerIncrement: 1,
    timePicker12Hour: true,
    ranges: {
       'Today': [moment(), moment()],
       'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
       'Last 7 Days': [moment().subtract(6, 'days'), moment()],
       'Last 30 Days': [moment().subtract(29, 'days'), moment()],
       'This Month': [moment().startOf('month'), moment().endOf('month')],
       'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
    },
    opens: 'left',
    drops: 'down',
    buttonClasses: ['btn', 'btn-sm'],
    applyClass: 'btn-primary',
    cancelClass: 'btn-default',
    separator: ':',
    locale: {
        applyLabel: 'Submit',
        cancelLabel: 'Cancel',
        fromLabel: 'From',
        toLabel: 'To',
        customRangeLabel: 'Custom',
        daysOfWeek: ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr','Sa'],
        monthNames: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
        firstDay: 1
    }
}, function(start, end, label) {
    console.log(start.toISOString(), end.toISOString(), label);
    $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
});


$("#cancTable").DataTable({
		"paging": true,
	    "lengthChange": true,
	    "searching": true,
	    "ordering": true,
	    "info": true,
	    "autoWidth": false,
	    dom: 'T<"clear">lfrtip',
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
                  <div class="box-body">
              <form  name="filterform" action="<c:url value="/Dashboard/cancellation"/>">
             
               <div class="row">
             <div class="col-md-6">
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
          			
                    <table  id="cancTable" class="table table-bordered table-hover">
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