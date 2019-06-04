<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>

<c:url value="/roster" var="rosterURL" />
<c:url value="/register" var="registerURL" />
<c:url value="/request" var="requestURL" />
<c:url var="viewRequestsURL" value="/viewRequests" />
<c:url var="assignShiftURL" value="/assignShift" />
<c:url var="googleURL" value="/google" />
<c:url var="createAnnouncementURL" value="/createAnnouncement" />
<c:url value="/deleteAnnouncement" var="deleteAnnouncementURL" />

 <link href= "webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"></link>
 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="container">
	
	<div class="container" style="margin-top:30px">
  <div class="row">
    <div class="col-sm-4">
      <img src="${pageContext.request.contextPath}/resources/images/logo.png" class="img-rounded" alt="Cinque Terre">
      <h2>Welcome</h2>
      <h5>${user.getFirstName()} </h5>
      <div> ${user.getEmail()} </div>
      <div> ${user.getType()}</div>
     
      <ul class="nav nav-pills flex-column">
        <li class="nav-item">
          <a class="nav-link active">You've worked "${totalTime}" hours this year </a>
        </li>
        <li class="nav-item">
          <a class="nav-link">Total hours worked this month "${totalTimeMonth}"</a>
        </li>
        <li class="nav-item">
          <a class="nav-link"">Average weekly hours "${averageWeek}"</a>
        </li>
        <li class="nav-item">
          <a class="nav-link">You are entitled to "${holidayHours}" paid holiday hours </a>
        </li>
      </ul>
      <hr class="d-sm-none">
    </div>
	<div class="col-sm-8">
      <h2>Announcements</h2>
      <c:forEach items="${announcements}" var="announcements">
      <ul>
      	<li>${announcements.getMessage()}</li><br />
      	<br />
      	<li><p>Posted on: ${announcements.getDatePosted()}</p></li>
      	
      	
      	<security:authorize access="hasRole('ADMIN')">
			<a href= "${deleteAnnouncementURL}/${announcements.id}" class= "btn-outline-danger btn-sm pull-right">Delete</a> 
		</security:authorize>
	  </ul>
      </c:forEach>
      
      <security:authorize access="hasRole('ADMIN')">
      <form id="message" action="${createAnnouncementURL}" method="post" role="form">
      	<input type="hidden" value="${announcement.id}" name="id" />
      	
      	<div class="form-group row">
                <label class="control-label col-sm-10" for="date">Announcement:</label>
                <div class="col-sm-10">
                    <input value="${announcement.message}" name="message" type="text" id="message" class="form-control" placeholder="Enter Message" autofocus>
                </div>
        </div>
        <div class="form-group row">
                <div class="col-sm-offset-2 col-sm-6">
                    <button type="submit" class="btn btn-primary">Save</button>
                    <a href="#" class="btn btn-danger">Cancel</a>
                </div>
            </div>
      </form>
      </security:authorize>
    </div>
  </div>


<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.2.1/js/bootstrap.min.js"></script>