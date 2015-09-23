<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="SD Plus">
	<jsp:attribute name="script">
<script>
	$(document)
			.ready(
					function() {
						$('#sdp').addClass("active");
						$('#dashboard').addClass("active");
						$("#sdpTable").DataTable({
							"paging" : true,
							"lengthChange" : false,
							"searching" : false,
							"ordering" : true,
							"info" : true,
							"autoWidth" : false,
							dom : 'T<"clear">lfrtip',
						});
						$('#daterange')
								.daterangepicker(
										{
											format : 'YYYY-MM-DD',
											startDate : moment().subtract(29,
													'days'),
											endDate : moment(),
											minDate : '2015-01-01',
											maxDate : '2016-12-31',
											dateLimit : {
												days : 60
											},
											showDropdowns : true,
											showWeekNumbers : true,
											timePicker : false,
											timePickerIncrement : 1,
											timePicker12Hour : true,
											ranges : {
												'Today' : [ moment(), moment() ],
												'Yesterday' : [
														moment().subtract(1,
																'days'),
														moment().subtract(1,
																'days') ],
												'Last 7 Days' : [
														moment().subtract(6,
																'days'),
														moment() ],
												'Last 30 Days' : [
														moment().subtract(29,
																'days'),
														moment() ],
												'This Month' : [
														moment().startOf(
																'month'),
														moment().endOf('month') ],
												'Last Month' : [
														moment()
																.subtract(1,
																		'month')
																.startOf(
																		'month'),
														moment().subtract(1,
																'month').endOf(
																'month') ]
											},
											opens : 'left',
											drops : 'down',
											buttonClasses : [ 'btn', 'btn-sm' ],
											applyClass : 'btn-primary',
											cancelClass : 'btn-default',
											separator : ':',
											locale : {
												applyLabel : 'Submit',
												cancelLabel : 'Cancel',
												fromLabel : 'From',
												toLabel : 'To',
												customRangeLabel : 'Custom',
												daysOfWeek : [ 'Su', 'Mo',
														'Tu', 'We', 'Th', 'Fr',
														'Sa' ],
												monthNames : [ 'January',
														'February', 'March',
														'April', 'May', 'June',
														'July', 'August',
														'September', 'October',
														'November', 'December' ],
												firstDay : 1
											}
										},
										function(start, end, label) {
											console.log(start.toISOString(),
													end.toISOString(), label);
											$('#reportrange span')
													.html(
															start
																	.format('MMMM D, YYYY')
																	+ ' - '
																	+ end
																			.format('MMMM D, YYYY'));
										});

					});
</script>

</jsp:attribute>
	<jsp:body>
			
 		<section class="content-header">
          <h1>
            SD+ Data
          </h1>
        </section>
          <div class="row">
            <div class="col-xs-14">
              <div class="box">
                 <div class="box-body">
                
            <div class="box-header with-border">
              <h3 class="box-title">Filters</h3>
              
            </div>
						<!-- /.box-header -->
           
               
            </div>
            <div class="box-body">
              <form name="filterform"
							action="<c:url value="/Dashboard/sdplus/filterData"/>">
                          <div class="row">
           
          
               
                <div class="col-md-2 col-md-offset-1">
                  
                     <div class="form-group">
                       <div class="row">
                    <label>Group by shipper group</label>
                </div>  <div class="row">
										<input type="checkbox" value="0" name="shippergroup"
												style="margin-left: 60px;">
                  </div>
									</div>
									<!-- /.form-group -->
                </div>
								<!-- /.col -->
                
                <div class="col-md-3"> 
                  <div class="form-group">
                    <label>Shipper</label>
                    <select name="shipper" class="form-control select2">
                      <option selected readonly value="">Select Shipper</option>
                     <c:forEach var="shipper" items="${shipper}">
                      <option value="${shipper.courier}">${shipper.courier}</option>
                     </c:forEach>
                    </select>
                  </div>
									<!-- /.form-group -->
                </div>
								<!-- /.col -->
                
                    <div class="col-md-3">
                     <div class="form-group">
                    <label>Mode</label>
                    <select name="mode" class="form-control select2">
                      <option selected readonly value="">Select Mode</option>
                     <c:forEach var="mode" items="${mode}">
                      <option value="${mode}">${mode}</option>
                      </c:forEach>
                    </select>
                  </div>
									<!-- /.form-group -->
              </div>  
              <div class="col-md-3">
                     <div class="form-group">
                    <label>Select Date</label>
                     <div class="input-group">
                      <div class="input-group-addon">
                        <i class="fa fa-calendar"></i>
                      </div>
                      <input type="text" class="form-control pull-right"
												name="daterange" id="daterange" required="required" />
                    </div>
                  </div>
									<!-- /.form-group -->
                </div>
						</div>		<!-- /.row -->
			
                
              <div align="center">
									<input class="btn btn-success" type="submit" value="Filter">
								</div>
              
						</form>
              
            </div>
					<!-- /.box-body -->
        <div class="box-footer"></div>
					<!-- /.box-header -->
                <div class="box-body">
                 <c:choose>
                    <c:when test="${empty filterData}">
                    <table id="sdpTable"
									class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>Shipper Group</th>
                     
                        <c:if test="${group_aggr > 5}">
                        <th>Shipper</th>
						</c:if>
                        <th>Mode</th>
                        <th>Center</th>
                        <th>Shipped Today</th>
                        <th>Not Shipped One Day</th>
                        <th>Not Shipped Two Days</th>
                        <th>Not Shipped Three Days</th>
                        <th>Not Shipped Four Days</th>
                        <th>Not Shipped More than Four Days</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${data}">
                    <tr>
                        <td>${item.shipperGroup}</td>
                        <c:if test="${group_aggr > 5 }">
                        <td>${item.shipper}</td>
                        </c:if>
                        <td>${item.mode}</td>
                        <td>${item.center}</td>
                        <td>${item.shippedToday}</td>
                        <td>${item.notshippedOneDay}</td>
                        <td>${item.notshippedTwoDays}</td>
                        <td>${item.notshippedThreeDays}</td>
                        <td>${item.notshippedFourDays}</td>
                        <td>${item.notshippedMoreFourDays}</td>
                      </tr>
                    </c:forEach>
                    </tbody>
                    </table>
                    </c:when>
                    <c:otherwise>
                     <table id="sdpTable"
									class="table table-bordered table-hover">
                    <thead>
                      <tr>
                        <th>Shipper Group</th>
                        <th>Shipper</th>
                        <th>Mode</th>
                        <th>Shipped Today</th>
                        <th>Not Shipped One Day</th>
                        <th>Not Shipped Two Days</th>
                        <th>Not Shipped Three Days</th>
                        <th>Not Shipped Four Days</th>
                        <th>Not Shipped More than Four Days</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${filterData}">
                    <tr>
                        <td>${item.shipperGroup}</td>
                        <td>${item.shipper}</td>
                        <td>${item.mode}</td>
                        <td>${item.shippedToday}</td>
                        <td>${item.notshippedOneDay}</td>
                        <td>${item.notshippedTwoDays}</td>
                        <td>${item.notshippedThreeDays}</td>
                        <td>${item.notshippedFourDays}</td>
                        <td>${item.notshippedMoreFourDays}</td>
                      </tr>
                    </c:forEach>
                    </tbody>
                    </table>
                    </c:otherwise>
                    </c:choose>
                  
        		</div>
				</div>
			</div>
		</div>

</jsp:body>
</tags:page>