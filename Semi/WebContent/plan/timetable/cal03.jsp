<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- fullcalendar -->
<link rel='stylesheet' href='/resources/timetable/fullcalendar/fullcalendar.css' />
<link href='/resources/timetable/fullcalendar/fullcalendar.print.css' rel='stylesheet' media='print' />

<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src='/resources/timetable/jquery-ui.min.js'></script>
<script type="text/javascript" src='/resources/timetable/moment.min.js'></script>
<script type="text/javascript" src="/resources/timetable/fullcalendar/fullcalendar.js"></script>
<script type="text/javascript" src="/resources/timetable/fullcalendar/scheduler.min.js"></script>
<script src='/resources/timetable/fullcalendar/locale-all.js'></script>

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
	      height:'auto',
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
	      resources: [
	        { id: 'a', title: 'Room A' },
	        { id: 'b', title: 'Room B', eventColor: 'green' },
	        { id: 'c', title: 'Room C', eventColor: 'orange' },
	        { id: 'd', title: 'Room D', eventColor: 'red' }
	      ],
	      events: [
// 	        { id: '1', resourceId: 'a', start: '2018-04-06', end: '2018-04-08', title: 'event 1' },
	        { id: '2', resourceId: 'a', start: '2018-04-07T10:00:00', end: '2018-04-07T14:00:00', title: 'event 2' },
	        { id: '3', resourceId: 'b', start: '2018-04-07T14:00:00', end: '2018-04-08T06:00:00', title: 'event 3' },
	        { id: '4', resourceId: 'c', start: '2018-04-07T07:30:00', end: '2018-04-07T09:30:00', title: 'event 4' },
	        { id: '5', resourceId: 'd', start: '2018-04-08T10:00:00', end: '2018-04-08T15:00:00', title: 'event 5' }
	      ],
	      eventOverlap: false,
	      eventMouseover: function(event, jsEvent, view) {
	        console.log(
	          'mouserover',
	          event.start.format()
	        );
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
	          console.log(intervalStart.format("YYYY-MM-DD"));
	          console.log(intervalEnd.format("YYYY-MM-DD"));
	          
	          if(intervalStart.format("YYYY-MM-DD") == '2018-04-07'){
	          	$(".fc-prev-button").hide();
	          }else{
	        	  $(".fc-prev-button").show();
	          }
	      },
	    });
	 $('#calendar').fullCalendar('option', 'locale', 'ko');
	 
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