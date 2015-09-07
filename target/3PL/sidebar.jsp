<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<aside class="main-sidebar"> <!-- sidebar: style can be found in sidebar.less -->
<section class="sidebar"> <!-- sidebar menu: : style can be found in sidebar.less -->
<ul class="sidebar-menu">

	<li id ="dashboard" class=" treeview"><a href="<c:url value="/home"/>">
			<i class="fa fa-dashboard"></i> <span>Dashboard</span> <i
			class="fa fa-angle-left pull-right"></i> </a>
		<ul class="treeview-menu">
			<li id="sdp">
			<a href="<c:url value="/Dashboard/sdplus"/>"><i	class="fa fa-circle-o"></i>SD+ and Oneship</a>
			</li>
			<li id="drop">
			<a href="<c:url value="/Dashboard/dropship"/>">
			<i class="fa fa-circle-o"></i>Dropship</a>
			</li>
			<li id="cancel">
			<a href="<c:url value="/Dashboard/cancellation"/>"><i	class="fa fa-circle-o"></i>Cancellations</a>
			</li>
		</ul></li>
		<li id ="dashboard" class=" treeview"><a href="<c:url value="/User"/>">
			<i class="fa fa-user"></i> <span>User</span> <i
			class="fa fa-angle-left pull-right"></i> </a>
		<ul class="treeview-menu">
			<li id="usercreate">
			<a href="<c:url value="/User/create"/>"><i	class="fa fa-circle-o"></i>Create</a>
			</li>
			<li id="useredit">
			<a href="<c:url value="/User/view"/>">
			<i class="fa fa-circle-o"></i>View/Edit</a>
			</li>	
		</ul></li>

</ul>
</section> <!-- /.sidebar --> </aside>
