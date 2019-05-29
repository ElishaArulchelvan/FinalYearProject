<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<link href= "webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"></link>

<c:url var="rosterURL" value="/roster" />

<h1>Create Shifts</h1>
<div class="container">
	 <div class="form-group">
        <form action="${rosterURL}" method="post" role="form" class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-sm-2" for="date">Date:</label>
                <div class="col-sm-6">
                    <input value="${shift.Date}" name="date" type="text" id="date" class="form-control" placeholder="Date">
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="start">Start Time:</label>
                <div class="col-sm-6">
                    <input value="${shift.start}" name="start" type="text" id="start" class="form-control" placeholder="StartTime">
                </div>
            </div>
            
            <div class="form-group">
                <label class="control-label col-sm-2" for="end">End Time:</label>
                <div class="col-sm-6">
                    <input value="${shift.end}" name="end" type="text" id="end" class="form-control" placeholder="End Time">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-6">
                    <button type="submit" class="btn btn-primary">Save</button>
                    <a href="#" class="btn btn-danger">Cancel</a>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.2.1/js/bootstrap.min.js"></script>