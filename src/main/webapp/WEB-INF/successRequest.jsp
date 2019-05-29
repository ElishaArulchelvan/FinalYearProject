<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<link href= "webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"></link>


<div class="d-flex justify-content-center " >
  <div class="card p-5">
  <h2>Request Sent</h2>

    <dl class="row">
	<dt class="col-xs-6 col-sm-3" style="background-color:lavenderblush;">Request Date: </dt>
 		<dd class="col-sm-8" style="background-color:lavenderblush;"> ${request.date}</dd>
	
	<dt class="col-xs-6 col-sm-3" style="background-color:lightcyan;">Request Days: </dt>
 		<dd class="col-sm-8" style="background-color:lightcyan;"> ${request.numDays}</dd>
 		
 	<dt class="col-xs-6 col-sm-3" style="background-color:lavender;">Request Reason: </dt> 
 	<dd class="col-sm-8" style="background-color:lavender;"> ${request.reason}</dd>
 		
 	<dt class="col-xs-6 col-sm-3" style="background-color:lavenderblush;">Employee Name: </dt>
 		<dd class="col-sm-8" style="background-color:lavenderblush;">${request.getUser().getFirstName()}</dd>
	</dl>
   
</div>
</div>
 



<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.2.1/js/bootstrap.min.js"></script>
