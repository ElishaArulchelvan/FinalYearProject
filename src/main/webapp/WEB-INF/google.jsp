<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<link href= "webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"></link>
<!-- DayPilot library -->
<script src="resources/js/daypilot/daypilot-all.min.js"></script>
<script src="resources/js/daypilot/daypilot-modal.min.js"></script>
<script src="resources/js/jquery/jquery-2.2.0.min.js"></script>

	
<div class="space">
    Week:
    <a href="javascript:dp.startDate = dp.startDate.addDays(-7); dp.update();">Previous</a>
    |
    <a href="javascript:dp.startDate = dp.startDate.addDays(7); dp.update();">Next</a>
</div>
<div class="form-group">
                <label class="control-label col-sm-2" for="User">Employee List</label>
                <div class="col-sm-6">
                    <select name ="User" id="User" class="form-control">
                    	<c:forEach items="${usersList}" var="user">
                    	<option value="${user.getId()}">${user.getFirstName()}</option>
                    	</c:forEach>
                    </select>
                </div>
            </div>
<div id="dp"></div>
	


<script>

	var dp = new DayPilot.Calendar("dp");
	dp.viewType = "Week";
	dp.eventDeleteHandling = "Update";
    dp.init();
    
    dp.onEventDeleted = function(args) {
    
    	console.log(args.e.id());
    	console.log(args.e.start());
        // AJAX call to the server, this example uses jQuery
        $.get("google/events/delete/" + args.e.id(), function(result) {
          window.alert("Shift deleted");
        });

      };
  
   	dp.onTimeRangeSelected = function (args) {
    	var modal = new DayPilot.Modal();
    	modal.height = 25;
    	DayPilot.Modal.confirm("Shift").then(function(modal)
    	{
    		var idElement = document.getElementById("User");
       		var selectedValue = idElement.options[idElement.selectedIndex].text;
       		modal = selectedValue;
       		console.log(selectedValue);
       		
       		/*var ids = 1000;
       		for(var i =0; i< ids; i++)
       		{
       			var uId = i;
       		}*/
       		
    		//var dp = args.control;
    		//dp.clearSelection();
    		//if(!modal.result){
    		//	return;
    		//}
    		var params = {
    	    		start: args.start.toString(),
    	    		end: args.end.toString(),
    	    		text: modal
    	    	 };
    		$.ajax({
    	    		type: 'POST',
    	    		url: '/Test/google/events/create',
    	    		data: JSON.stringify(params),
    	    		success: function(params){
    	    			dp.events.add(new DayPilot.Event(params));
    	    			window.alert("Success");
    	    		},
    	    		error: function(params){
    	    			window.alert("Employee requested day off");
    	    		},
    	    		contentType: "application/json",
    	    		dataType: 'json'
    	    	});
      	});
    }
   	
   	let result = [];
   	
   	fetch('/Test/google/events?to=2019-03-30T00:00&from=2019-03-30T00:00')
    .then(response => response.json())
    .then(data => {
    		var e = [];
    		e.push(data);
    		e.forEach(function(element){
    			console.log(element);
    			element.forEach(item => {
    				result.push({
    					"id": item.id,
    					"start": item.start,
    					"end": item.end,
    					"text": item.usersShifts[0].firstName + ""
    				})
    				console.log(result)
    				//var output = JSON.stringify(result)
    				//console.log(output)
    			});	
    			dp.events.list = result;
    			dp.update();
    		});
    	})
   
   	
    dp.onEventClicked = function(args)
    {
   		console.log(args.e.data);
    	alert(args.e.text() +" : " + args.e.start()+ " - " + args.e.end());
    }
    
 
</script>  	
