<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>

<c:url value="/roster" var="rosterURL" />
<c:url value="/register" var="registerURL" />
<c:url value="/request" var="requestURL" />
<c:url var="viewRequestsURL" value="/viewRequests" />
<c:url var="employeeListURL" value="/employeeList" />
<c:url value="/logout" var="logoutURL" />
<c:url value="/myShifts" var="myShiftsURL" />
<c:url value="/viewRoster" var="viewRosterURL" />
<c:url value="/google" var="googleURL" />
<c:url value="/editInfo" var="editInfoURL" />
<c:url value="/holidays" var="holidaysURL" />
<c:url value="/" var="mainURL" />
<c:url value="/myRequests" var="myRequestsURL" />

<html>
<head>
<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #85BFEC;
}

li {
  float: left;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover:not(.active) {
  background-color: #111;
}

.main {
  background-color: #333;
}
</style>
</head>
<body>

<ul>
  <li><a class ="main" href="${mainURL}">Main</a></li>
  <sec:authorize access="hasRole('ADMIN')">
	<li>
	<ul class="nav nav-container">
		<li><a href="${registerURL}">Register New Employee</a></li>
		<li><a href="${employeeListURL}">Show all Employees</a></li>
		<li><a href="${viewRequestsURL}">View Requests</a></li>
		<li><a href="${googleURL}">Create Weekly Roster</a></li>
	</ul></li>
	</sec:authorize>
	
	<li>
	<sec:authorize access="hasRole('USER')">
		<li><a href="${requestURL}">Send Request</a></li>					
		<li role="separator" class="divider"></li>
		<li><a href="${myShiftsURL}">My Shifts</a></li>					
		<li role="separator" class="divider"></li>
		<li><a href="${viewRosterURL}">Roster</a></li>	
		<li><a href="${editInfoURL}">Edit info</a></li>			
		<li role="separator" class="divider"></li>
		<li><a href="${myRequestsURL}">Request Status</a></li>
		</sec:authorize>
		<li><a href="${logoutURL}">Logout</a></li>
	</ul></li>

</ul>

</ul>

</body>
</html>