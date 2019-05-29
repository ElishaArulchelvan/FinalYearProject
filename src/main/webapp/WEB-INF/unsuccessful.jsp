<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<link href= "webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"></link>

<c:url value="/request" var="requestURL"/>

<p class="bg-danger text-white" align="center">Too many people have asked for that day off</p>
<div class="d-flex justify-content-center " >
<div class="form-group"> 
	 <div class="card p-5">
	 <h3> Request Day Off</h3>
        <form id="form" action="${createRequestURL}" method="post" role="form" class="form-horizontal"  >

            <input type="hidden" value="${request.id}" name="id"/>

            <div class="form-group row">
                <label class="control-label col-sm-10" for="date">Date:</label>
                <div class="col-sm-10">
                    <input value="${request.date}" name="date" type="date" id="date" class="form-control" placeholder="Enter Request Date" autofocus>
                </div>
            </div>

            <div class="form-group row">
                <label class="control-label col-sm-10" for="numDays">Number Days Off:</label>
                <div class="col-sm-10">
                    <input value="${request.numDays}" name="numDays" type="text" id="numDays" class="form-control" placeholder="Enter number of days off">
                </div>
            </div>

            <div class="form-group row">
                <label class="control-label col-sm-10" for="email">Reason:</label>
                <div class="col-sm-10">
                    <input value="${request.reason}" name="reason" type="text" id="reason" class="form-control" placeholder="Enter reason">
                </div>
            </div>

            <div class="form-group row">
                <div class="col-sm-offset-2 col-sm-6">
                    <button type="submit" class="btn btn-primary">Save</button>
                    <a href="#" class="btn btn-danger">Cancel</a>
                </div>
            </div>

        </form>
        </div>
        </div>
        </div>

<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.2.1/js/bootstrap.min.js"></script>