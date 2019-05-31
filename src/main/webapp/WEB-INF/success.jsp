<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<link href= "webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"></link>


<p class="bg-success text-white" align="center">Success</p>
<div class="d-flex justify-content-center " >
	

<div class="row">
	<div class="card p-5">
	 <dl class="row">
		<dt class="col-sm-3">First Name: </dt>
		<dd class="col-sm-8"> ${user.getFirstName()}</dd>
		<dt class="col-sm-3">Surname: </dt>
		<dd class="col-sm-8"> ${user.getLastName()}</dd>
		<dt class="col-sm-3">Type: </dt>
		<dd class="col-sm-8"> ${user.getType()}</dd>
		<dt class="col-sm-3">Email: </dt>
		<dd class="col-sm-8"> ${user.getEmail()}</dd>
	</dl>
</div>

</div>
</div>
<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.2.1/js/bootstrap.min.js"></script>