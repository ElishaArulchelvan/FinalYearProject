<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<link href= "webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"></link>

<c:url var="editInfoURL" value="/editInfo" />

<body>

<div class="d-flex justify-content-center " >

	

<div class="row">
	
	 <div class="form-group">
	 <dl class="row">
		<dt class="col-sm-3">First Name: </dt>
		<dd class="col-sm-8"> ${user.getFirstName()}</dd>
		<dt class="col-sm-3">Surname: </dt>
		<dd class="col-sm-8"> ${user.getLastName()}</dd>
		<dt class="col-sm-3">Type: </dt>
		<dd class="col-sm-8"> ${user.getType()}</dd>
	</dl>
	 <div class="card p-5">
        <form action="${editInfoURL}" method="post" role="form" class="form-horizontal">

            <input type="hidden" value="${user.id}" name="id"/>
           

            <div class="form-group row">
                <label class="control-label col-sm-10" for="email">Email:</label>
                <div class="col-sm-10">
                    <input value="${user.email}" name="email" type="text" id="email" class="form-control" placeholder="Email" autofocus>
                </div>
               
            </div>

            <div class="form-group row">
                <label class="control-label col-sm-10" for="password">New password:</label>
                <div class="col-sm-10">
                    <input value="${user.password}" name="password" type="text" id="password" class="form-control" placeholder="password">
                </div>
            </div>
            
            <div class="form-group row">
                <label class="control-label col-sm-10" for="confirmPassword">Confirm Password:</label>
                <div class="col-sm-10">
                    <input name="confirmPassword" type="text" id="confirmPassword" class="form-control" placeholder="Confirm password">
                </div>
            </div>
            
            <div class="form-group row">
                <div class="col-sm-offset-2 col-sm-6">
                    <button type="submit" class="btn btn-primary">Save</button>
                    <a href="#" class="btn btn-danger">Cancel</a>
                </div>
            </div>

        </form>
       </div> </div>
        </div>
 </div>
</body>