<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<link href= "webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"></link>

<c:url var="employeeListURL" value="/employeeList" />
<c:url var="shiftsURL" value="/showUserShifts?id=" />

<div class="container">

<h1>Employee List</h1>

<div class="row">
	<div class="col-md-12">
		<table class="table table-striped table-hover table-bordered">
			<thead>
				<tr>
					<th class="text-center">First Name</th>
					<th class="text-center">Last Name</th>
					<th class="text-center">Email</th>
					<th class="text-center">See Shifts</th>
				</tr>
				</thead>
				<tbody>
					<c:forEach items="${usersList}" var="users">
						<tr>
							<td>${users.getFirstName()}</td>
							<td>${users.getLastName()}</td>
							<td>${users.getEmail()}</td>
							<td><a href="${shiftsURL}${users.id}"
								class="btn btn-sm btn-primary">See Shifts</a></td>
						</tr>
					</c:forEach>
				</tbody>	
		</table>
	</div>

</div>
</div>
<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.2.1/js/bootstrap.min.js"></script>