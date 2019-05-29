<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<link href= "webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"></link>


<div class="container">

	<h1>Weekly Roster</h1>

	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th class="text-center">Monday</th>
						<th class="text-center">Tuesday </th>
						<th class="text-center">Wednesday </th>
						<th class="text-center">Thursday</th>
						<th class="text-center">Friday</th>
					</tr>
				</thead>
				<tbody>
	
					<c:forEach items="${shiftsList}" var="shifts">
						<tr>
							<td>${request.getId()}</td>
							<td>${request.getUser().getFirstName()}
								${request.getUser().getLastName()}</td>
							<td>${request.getDate()}</td>
							<td>${request.getNumDays()}</td>
							<td>${request.getReason()}</td>
							<td><a href="<c:url value='/accept/${request.id}'  />" class="btn btn-danger custom-width" path="status">Accept</a></td>
							<td><a href="<c:url value='/decline/${request.id}'  />" class="btn btn-danger custom-width" path="status">Decline</a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		
</div>
		</div>
	</div>









<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.2.1/js/bootstrap.min.js"></script>