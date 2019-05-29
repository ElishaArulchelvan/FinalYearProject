<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<link href= "webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"></link>

<c:url var="viewRequestsURL" value="/viewRequests" />
<c:url value="/delete" var="deleteRequestURL" />


<div class="container">

	<h1>Requests</h1>
	<div class="row">
		<div class="col-md-12">
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<th class="text-center">Employee name </th>
						<th class="text-center">Date </th>
						<th class="text-center">Number of days</th>
						<th class="text-center">Reason</th>
					</tr>
				</thead>
				<tbody>
	
					<c:forEach items="${requestList}" var="request">
						<tr>
							<td>${request.getUser().getFirstName()}
								${request.getUser().getLastName()}</td>
							<td>${request.getDate()}</td>
							<td>${request.getNumDays()}</td>
							<td>${request.getReason()}</td>
							<td><a href="<c:url value='/accept/${request.id}'  />" class="btn btn-success custom-width" path="status">Accept</a></td>
							<td><a href="<c:url value='/decline/${request.id}'  />" class="btn btn-danger custom-width" path="status">Decline</a></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</div>
</div>
	<!-- Trigger the modal with a button -->
  <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal" >Set</button>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <form role="form">
            <div class="form-group row">
              	<label for="monday" class="col-sm-2 col-form-label">Monday</label>
             <div class="col-sm-10">
             	<div class="md-form mt-0">
              	<input type="text" class="form-control" id="monday" placeholder="Number of staff">
            	</div>
            </div>
            </div>
            <div class="form-group row">
              	<label for="tuesday" class="col-sm-2 col-form-label">Tuesday</label>
             <div class="col-sm-10">
             	<div class="md-form mt-0">
              	<input type="text" class="form-control" id="tuesday" placeholder="Number of staff">
            	</div>
            </div>
            </div>
            <div class="form-group row">
              	<label for="wednesday" class="col-sm-2 col-form-label">Wednesday</label>
             <div class="col-sm-10">
             	<div class="md-form mt-0">
              	<input type="text" class="form-control" id="wednesday" placeholder="Number of staff">
            	</div>
            </div>
            </div>
            <div class="form-group row">
              	<label for="thursday" class="col-sm-2 col-form-label">Thursday</label>
             <div class="col-sm-10">
             	<div class="md-form mt-0">
              	<input type="text" class="form-control" id="thursday" placeholder="Number of staff">
            	</div>
            </div>
            </div>
            <div class="form-group row">
              	<label for="friday" class="col-sm-2 col-form-label">Friday</label>
             <div class="col-sm-10">
             	<div class="md-form mt-0">
              	<input type="text" class="form-control" id="friday" placeholder="Number of staff">
            	</div>
            </div>
            </div>
            <div class="form-group row">
    		<div class="col-sm-10">
     			 <button type="submit" class="btn btn-primary btn-md">Set</button>
    		</div>
  		</div>
            </form>
      </div>
      </div></div>

<script>
	$(".delete-button").click(function(){
    	$.ajax({
       	 url : 'declineRequest',
       	 method : 'POST',
       	 async : false,
       	 complete : function(data) {
        	    console.log(data.responseText);
       	 }
    	});

	});
</script>
<script src="webjars/jquery/3.3.1-1/jquery.min.js"></script>
<script src="webjars/bootstrap/4.2.1/js/bootstrap.min.js"></script>
