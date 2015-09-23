<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<tags:resources title="Error Page">
	<div class="container">
	<div class="row">	
		<div class="span12">
			
			<div class="error-container">
				<h2>Bad request</h1>
				
				<h4>Invalid Parameters Passed. Please correct and proceed.</h2>
				
<!-- 				<div class="error-details"> -->
<!-- 					Sorry, an error has occured! Why not try going back to the <a href="index.html">home page</a> or perhaps try following! -->
					
<!-- 				</div> /error-details -->
				<br>
				<div class="error-actions">
					<a href="<c:url value="/home"/>" class="btn btn-large btn-primary">
						<i class="icon-chevron-left"></i>
						&nbsp;
						Back to Dashboard						
					</a>
					
					
					
				</div> <!-- /error-actions -->
							
			</div> <!-- /error-container -->			
			</div>
			</div></div>
</tags:resources>