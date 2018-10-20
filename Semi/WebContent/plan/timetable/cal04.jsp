<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href='/Cal/fullcalendar/fullcalendar.css' />
<link href='/Cal/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />
<script type="text/javascript" src='/Cal/lib/jquery.min.js'></script>
<script type="text/javascript" src='/Cal/lib/jquery-ui.min.js'></script>
<script type="text/javascript" src='/Cal/lib/moment.min.js'></script>
<script type="text/javascript" src="/Cal/fullcalendar/fullcalendar.js"></script>
<script type="text/javascript" src="/Cal/fullcalendar/scheduler.min.js"></script>
<script src='/Cal/fullcalendar/locale-all.js'></script>

<script type="text/javascript">
$(document).ready(function(){

    /* initialize the external events
    -----------------------------------------------------------------*/

    $('#external-events .fc-event').each(function() {

      // store data so the calendar knows to render an event upon drop
      $(this).data('event', {
        title: $.trim($(this).text()), // use the element's text as the event title
        stick: true // maintain when user navigates (see docs on the renderEvent method)
      });

      // make the event draggable using jQuery UI
      $(this).draggable({
        zIndex: 999,
        revert: true,      // will cause the event to go back to its
        revertDuration: 0  //  original position after the drag
      });

    });

    
	 $('#calendar').fullCalendar({
		  schedulerLicenseKey: 'GPL-My-Project-Is-Open-Source',
 		  defaultView: 'titetable',
	      defaultDate: '2018-04-07',
	      locale: 'ko',
	      editable: true,
	      droppable: true, // this allows things to be dropped onto the calendar
 	      selectable: true,
	      eventLimit: true, // allow "more" link when too many events
	      header: {
        	left: 'prev',
	        //center: 'title',
	        right: 'next'//, agendaDay,agendaTwoDay,agendaWeek,month'
	      },
	      views: {
    	    titetable: {
    	      type: 'agenda',
    	      visibleRange: function(currentDate) {
  	    	    return {
  	    	      start: currentDate.clone().subtract(0, 'days'),
  	    	      end: currentDate.clone().add(3, 'days')
  	    	    };
  	    	  }
    	    },
    	  },
	      //// uncomment this line to hide the all-day slot
	      allDaySlot: false,
	      events: [
// 	        { id: '1', start: '2018-04-06', end: '2018-04-08', title: 'event 1' },
	        { id: '2', start: '2018-04-07T10:00:00', end: '2018-04-07T14:00:00', title: 'event 2' },
	        { id: '3', start: '2018-04-09T14:00:00', end: '2018-04-09T17:30:00', title: 'event 3' },
	        { id: '4', start: '2018-04-07T07:30:00', end: '2018-04-07T09:30:00', title: 'event 4' },
	        { id: '5', start: '2018-04-08T10:00:00', end: '2018-04-08T15:00:00', title: 'event 5' }
	      ],
// 	      eventConstraint: {
// 	    	    start: '00:00',
// 	    	    end: '24:00'
// 	      },
	      eventOverlap: false,
	      eventMouseover: function(event, jsEvent, view) {
// 	        console.log(
// 	          'mouserover',
// 	          event.start.format()
// 	        );
	      },
	      dayClick: function(date, jsEvent, view, resource) {
	        console.log(
	          'dayClick',
	          date.format(),
	          resource ? resource.id : '(no resource)'
	        );
	      },
	      viewRender: function (view, element)
	      {
	          intervalStart = view.intervalStart;
	          intervalEnd = view.intervalEnd;
// 	          console.log(intervalStart.format("YYYY-MM-DD"));
// 	          console.log(intervalEnd.format("YYYY-MM-DD"));
	          
	          if(intervalStart.format("YYYY-MM-DD") == '2018-04-07'){
	          	$(".fc-prev-button").hide();
	          }else{
	        	  $(".fc-prev-button").show();
	          }
	          console.log(view.selectConstraint );
	          console.log(element.selectConstraint );
	      },
	      eventRender: function(event, element, view) {
	    	  if(event.start.format("YYYY-MM-DD") == '2018-04-07'){
	    	  	element.css("background-color", "green");
	    	  }else if(event.start.format("YYYY-MM-DD") == '2018-04-08'){
	    	  	element.css("background-color", "orange");
	    	  }
	    	}
	    });
	 $('#calendar').fullCalendar('option', 'locale', 'ko');
 	 $('#calendar').fullCalendar('option', 'eventConstraint',{start:"00:00", end:"24:00"})
});
</script>
<style>

  body {
    margin-top: 40px;
    text-align: center;
    font-size: 14px;
    font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
  }

  #wrap {
    width: 1100px;
    margin: 0 auto;
  }

  #external-events {
    float: left;
    width: 150px;
    padding: 0 10px;
    border: 1px solid #ccc;
    background: #eee;
    text-align: left;
  }

  #external-events h4 {
    font-size: 16px;
    margin-top: 0;
    padding-top: 1em;
  }

  #external-events .fc-event {
    margin: 10px 0;
    cursor: pointer;
  }

  #external-events p {
    margin: 1.5em 0;
    font-size: 11px;
    color: #666;
  }

  #external-events p input {
    margin: 0;
    vertical-align: middle;
  }

  #calendar {
    float: right;
    width: 900px;
  }

</style>
</head>
<body>
  <div id='wrap'>

    <div id='external-events'>
      <h4>Draggable Events</h4>
      <div class='fc-event'>My Event 1</div>
      <div class='fc-event'>My Event 2</div>
      <div class='fc-event'>My Event 3</div>
      <div class='fc-event'>My Event 4</div>
      <div class='fc-event'>My Event 5</div>
    </div>

    <div id='calendar'></div>

    <div style='clear:both'></div>

  </div>
</body>
</html>