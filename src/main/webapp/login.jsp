<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/resources.jsp"></jsp:include>
<body class="login-page">
	<div class="login-box">
		<div class="login-logo">
			<a href="../../index2.html"><b>3PL</b> Dashboard</a>
		</div>
		<!-- /.login-logo -->
		<div class="login-box-body">
			<p class="login-box-msg">Sign in to start your session</p>
			<form action="<c:url value="/j_spring_security_check"/>" method="post">
				<div class="form-group has-feedback">
					<input id="username" name="username" type="text" class="form-control" placeholder="Email"/><span
						class="glyphicon glyphicon-envelope form-control-feedback"></span>
				</div>
				<div class="form-group has-feedback">
					<input type="password" id="password" name="password" class="form-control" placeholder="Password" />
					<span class="glyphicon glyphicon-lock form-control-feedback"></span>
				</div>
				<div class="row">
					
					<!-- /.col -->
					<div class="col-xs-4">
						<button type="submit" class="btn btn-primary btn-block btn-flat">Sign
							In</button>
					</div>
					<!-- /.col -->
				</div>
			</form>			
		</div>
		<!-- /.login-box-body -->
	</div>
	<!-- /.login-box -->

</body>