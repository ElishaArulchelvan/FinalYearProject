<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<link href= "webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"></link>

<c:url value="/register" var="registerURL"/>
<c:url value="/login" var="loginURL"/>


<div class="d-flex justify-content-center " >
	<div class="form-group">
	<h3> Register new employee</h3>
	<div class="card p-5">
	
        <form action="${registerURL}" method="post" modelAttribute="userForm">
        	<div class="form-group row" >
        		<label for="inputEmail" class="control-label col-sm-4">Email</label>
        		<div class="col-sm-8">
            		<input name="email" type="email" id="inputEmail" class="form-control" placeholder="Email address" required>
            	</div>
            </div>
            <div class="form-group row">
            	<label for="inputFirstName" class="control-label col-sm-4">First Name</label>
            	<div class="col-sm-8">
            		<input name="firstName" type="text" id="inputFirstName" class="form-control" placeholder="First name" required>
            	</div>
            </div>
            <div class="form-group row">
            	<label for="inputLastName" class="control-label col-sm-4">Surname</label>
            	<div class="col-sm-8">
            		<input name="lastName" type="text" id="inputLastName" class="form-control" placeholder="Last name" required> 
            	</div>  
            </div>
            <div class="form-group row">
            <label for="inputType" class="control-label col-sm-4">Type</label>
            <div class="col-sm-8">
           		<select name="type" id= "type" class="form-control" placeholder="Type" required>
           				<option>Part time</option>
           				<option>Full time</option>   
      			 </select>
      			</div>
      		 </div>
      		 <div class="form-group row">
      		 	<label for="inputPassword" class="control-label col-sm-4">Password</label>
   				<div class="col-sm-8">
            		<input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
            	</div>
            </div>
            <div class="form-group row">
            	<label for="inputConFirmPassword" class="control-label col-sm-4">Confirm Password</label>
            	<div class="col-sm-8">
            		<input type="password" id="inputRepeatedPassword" class="form-control" placeholder="Repeat password" required>
				</div>
			</div>
			<div class="form-group">
			<div class="col-sm-offset-2 col-sm-6">
            <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Create</button>
         	</div>
         	</div>
       
        </form>
        </div>
     </div>

</div>

<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.2.1/js/bootstrap.min.js"></script>