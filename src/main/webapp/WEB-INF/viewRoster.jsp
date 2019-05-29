<script src="resources/js/daypilot/daypilot-all.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/WEB-INF/include/navbar.jsp"%>
<link href= "webjars/bootstrap/4.2.1/css/bootstrap.min.css" rel="stylesheet"></link>

<div id="dp"></div>

<script>

	var dp = new DayPilot.Calendar("dp", {
		timeRangeSelectedHandling: "Disabled",
	    eventMoveHandling: "Disabled",
	    eventResizeHandling: "Disabled",
	    eventDeleteHandling: "Disabled",
	    eventClickHandling: "Disabled",
	    eventRightClickHandling: "Disabled"
	});
	dp.viewType = "Week";
	dp.init();
	
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
    	alert(args.e.text() +" : " + args.e.start()+ " - " + args.e.end());
    }

</script>