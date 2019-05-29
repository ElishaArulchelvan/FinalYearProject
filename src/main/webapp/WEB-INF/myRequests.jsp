<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<link href= "webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"></link>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
	

<div class="container">

	<h1>Upcoming Requests</h1>

	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th class="text-center">Request Date</th>
						<th class="text-center">Request Number of Days</th>
						<th class="text-center bg-danger text-white">Request Status</th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${requests}" var="requests">
						<tr>
							<td>${requests.getDate()}</td>
							<td>${requests.getNumDays()}</td>
							<td>${requests.getStatus()}</td>
						</tr>
						
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>