<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<link href= "webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"></link>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<c:url var="shiftsURL" value="/showUserShifts" />
<c:url value="/delete" var="deleteShiftURL" />

<div class="container">

	<h1>Shifts this week</h1>

	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th class="text-center">Date</th>
						<th class="text-center">Time</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${shiftList}" var="shifts">
						<tr>
							<td>${shifts.getStart().toLocalDate().getDayOfMonth()} - ${shifts.getStart().toLocalDate().getMonth()} - ${shifts.getStart().toLocalDate().getYear()}</td>
							<td>${shifts.getStart().toLocalTime()} - ${shifts.getEnd().toLocalTime()}</td>
						<security:authorize access="hasRole('ADMIN')">
								<td><a href="<c:url value='/delete/${shifts.id}'  />" class="btn btn-danger custom-width">Delete</a></td>
						</security:authorize>
						</tr>
						
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>

<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.2.1/js/bootstrap.min.js"></script>