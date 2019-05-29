<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<link href= "webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"></link>

<c:url var="assignShiftURL" value="/assignShift" />

<h1>Create Shifts</h1>
<div class="container">

	 <div class="form-group">
        <form action="${assignShiftURL}" method="post" role="form" class="form-horizontal">
        	<div class="form-group">
                <label class="control-label col-sm-2" for="Shift">Shift:</label>
                <div class="col-sm-6">
                    <select name ="Shift" id="Shift" class="form-control"   >
                    	<c:forEach items="${shiftsList}" var="shift" >
                    	<option value="${shift.getId()}">${shift.getStart()} - ${shift.getEnd()} </option>
                    	</c:forEach>
                    </select>
                </div>
            </div>
           
         
            <div class="form-group">
                <label class="control-label col-sm-2" for="User">Employee:</label>
                <div class="col-sm-6">
                    <select name ="User" id="User" class="form-control">
                    	<c:forEach items="${usersList}" var="user">
                    	<option value="${user.getId()}">${user.getFirstName()}</option>
                    	</c:forEach>
                    </select>
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

