<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<tags:page title="View">
	<jsp:attribute name="script">
	<script>
	var dataTable1;	
	$(document).ready(function() {
			$("#view").addClass('active');
// 			var code = $("#whselect").val(); 
			$("#Table").dataTable({});
// 				 serverSide: true,
// 				  ajax: {
// 				        url: '/SFM/viewAsr',
// 				        type: 'GET',
// 				        data :{ "code" : code}
// 				    	},
// 				 deferRender:true
// 	 			   } );
	
			
		});

		function getAsr() {
			
			var code = $("#whselect").val();

			$.ajax({

				url : "/SFM/viewAsr",
				type : "GET",
				dataType : "JSON",
				contentType : 'application/json',
				data : "code=" + code,
				success : function(data) {
					$("#maintable").empty();
// 					dataTable1.fnClearTable();
					for(var i=0;i<data.length;i++){
// 						var arr=[];
						
						$("#maintable").append("<tr>");
						$("#maintable").append("<td class='a'>"+data[i].asrNumber+"</td>");
						$("#maintable").append("<td>"+data[i].status+"</td>");
						$("#maintable").append("<td>"+data[i].warehouse.name+"</td>");
						$("#maintable").append("<td><a href=\"#myModal\" role=\"button\"\
							class=\"btn \" data-toggle=\"modal\"\
							onclick=\"asrDetails("+data[i].id+");asrHistory("+data[i].id+");\">View</a></td>");
						$("#maintable").append("</tr>");

// 					arr.push(data[i].asrNumber);
// 					arr.push(data[i].status);
// 					arr.push(data[i].warehouse.name);
// 					arr.push("<a href=\"#myModal\" role=\"button\"\
//  							class=\"btn \" data-toggle=\"modal\"\
//  							onclick=\"asrDetails("+data[i].id+");asrHistory("+data[i].id+");\">View</a>");
// 					dataTable1.fnAddData(arr);
					}
				
			
			}
			});
			
			
		}
		
		function asrDetails(id) {
			
			$.ajax({

				url : "/SFM/asrDetails",
				type : "GET",
				dataType : "JSON",
				contentType : 'application/json',
				data : "id=" +id,
				success : function(data) {

					$("#tablebody").empty();
					var receivedate=new Date(parseInt(data.asrReceived));
					var pickupdate=new Date(parseInt(data.pickupAlignment));
						
						$("#tablebody").append("<tr>");
						$("#tablebody").append("<td><b>ASR Number</td>");
						$("#tablebody").append("<td>"+data.asrNumber+"</td>");
						$("#tablebody").append("</tr>");
						
						$("#tablebody").append("<tr>");
						$("#tablebody").append("<td><b>Status</td>");
						$("#tablebody").append("<td>"+data.status+"</td>");
						$("#tablebody").append("</tr>");
																
						$("#tablebody").append("<tr>");
						$("#tablebody").append("<td><b>ASR Recieved Date</td>");
						$("#tablebody").append("<td>"+receivedate+"</td>");
						$("#tablebody").append("</tr>");
						
						$("#tablebody").append("<tr>");
						$("#tablebody").append("<td><b>Pickup Alignment Date</td>");
						$("#tablebody").append("<td>"+pickupdate+"</td>");
						$("#tablebody").append("</tr>");
						
						$("#tablebody").append("<tr>");
						$("#tablebody").append("<td><b>Order Quantity</td>");
						$("#tablebody").append("<td>"+data.orderQuantity+"</td>");
						$("#tablebody").append("</tr>");
						
						$("#tablebody").append("<tr>");
						$("#tablebody").append("<td><b>GRN Quantity</td>");
						$("#tablebody").append("<td>"+data.grnQuantity+"</td>");
						$("#tablebody").append("</tr>");
						
						$("#tablebody").append("<tr>");
						$("#tablebody").append("<td><b>Pickup Quantity</td>");
						$("#tablebody").append("<td>"+data.pickupQuantity+"</td>");
						$("#tablebody").append("</tr>");
						
						$("#tablebody").append("<tr>");
						$("#tablebody").append("<td><b>Warehouse Name</td>");
						$("#tablebody").append("<td>"+data.warehouse.name+"</td>");
						$("#tablebody").append("</tr>");
						
						$("#tablebody").append("<tr>");
						$("#tablebody").append("<td><b>Seller Code</td>");
						$("#tablebody").append("<td>"+data.seller.sellerCode+"</td>");
						$("#tablebody").append("</tr>");
						
						$("#tablebody").append("<tr>");
						$("#tablebody").append("<td><b>Seller Name</td>");
						$("#tablebody").append("<td>"+data.seller.sellerName+"</td>");
						$("#tablebody").append("</tr>");
						
						$("#tablebody").append("<tr>");
						$("#tablebody").append("<td><b>Super Category</td>");
						$("#tablebody").append("<td>"+data.superCategory+"</td>");
						$("#tablebody").append("</tr>");
						
						$("#tablebody").append("<tr>");
						$("#tablebody").append("<td><b>Transporter</td>");
						$("#tablebody").append("<td>"+data.transporter+"</td>");
						$("#tablebody").append("</tr>");
			}	
				});
					
							
		}
		
		
function asrHistory(id) {

			
			$.ajax({
	
				url : "/SFM/asrHistory",
				type : "GET",
				dataType : "JSON",
				contentType : 'application/json',
				data : "asrid=" + id,
				success : function(data) {
					$("#statusbody").empty();
					for(var i=0;i<data.length;i++){
						var date=new Date(parseInt(data[i].lastUpdated));
						$("#statusbody").append("<tr>");
						$("#statusbody").append("<td>"+data[i].status+"</td>");
						$("#statusbody").append("<td>"+date+"</td>");
						$("#statusbody").append("</tr>");
					}
				}
			});
		}
</script>
</jsp:attribute>
<jsp:body>
	<div class="main">
	<div class="main-inner">
		<div class="container">
			<div class="row">
				<div class="span12">
						<div class="widget widget-table action-table">
            			<div class="widget-header"><i class="icon-list-alt"></i>
              			 <h3>ASRs</h3>
              			 <div
										style="float: right; padding-top: 4px; padding-right: 10px"> 
						<input type="text" id="daterange" />	
						   <select id="whselect" onclick="getAsr()";>
						   <option value="" selected disabled>Select Warehouse </option>
						   <c:forEach var="warehouse" items="${warehouse}">
						   <option value="${warehouse.code}">${warehouse.name}</option>
						   </c:forEach>
						   </select>
									</div>
		   					</div>
           				 	
<!--            				 /widget-header -->
						<div class="widget-content" id="tablediv">

              <table  id="Table" cellspacing="0" width="100%" class="table table-striped table-bordered">
                <thead>
                  <tr>
                    <th> ASR Number</th>	 
                    <th> Status</th>
                    <th> Warehouse</th>
                    <th class="td-actions"> </th>
                  </tr>
                </thead>
                <tbody id="maintable">
                      <c:forEach var="asr" items="${asr}">
									<tr>
										<td>${asr.asrNumber}</td>
										<td>${asr.status}</td>
										<td>${asr.warehouse.name}</td>
										<td><a href="#myModal" role="button" class="btn "
														data-toggle="modal"
														onclick="asrDetails(${asr.id});asrHistory(${asr.id});">View</a>
											</td>
									</tr>
								</c:forEach>           
                </tbody>
              </table>
              <div id="nav"></div>
              <!-- Modal -->
                 <div id="myModal" class="modal hide fade" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true" style="width:800px;">
                    <div class="modal-header">
                         <button type="button" class="close"
												data-dismiss="modal" aria-hidden="true">×</button>
                           <h4 id="myModalLabel">ASR details</h4>
                   </div>
                    <div class="modal-body">
                         <table  class="table table-striped table-bordered">
                <thead>
                  <tr>
                  <th>ASR Details </th>
                  <th></th>

                  </tr>
                </thead>
                <tbody id="tablebody">
                </tbody>
                </table>
                <br><br><br>
                <table class="table table-striped table-bordered">
                <thead>
                  <tr>
                    <th>Status</th>
                    <th>Updated On</th>
                  </tr>
                </thead>
                <tbody id="statusbody">
                </tbody>
                </table>
                           </div>
                           <div class="modal-footer">
                              <button class="btn" data-dismiss="modal"
												aria-hidden="true">Close</button>         
                           </div>
                    </div>
            </div>
	</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</jsp:body>
</tags:page>
	