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

	$('#calendar').fullCalendar({
	      defaultView: 'agendaDay',
	      defaultDate: '2018-04-07',
	      editable: true,
	      height: 'auto',
	      selectable: true,
	      eventLimit: true, // allow "more" link when too many events
	      header: {
	        left: 'prev,next today',
	        center: 'title',
	        right: 'agendaDay,agendaTwoDay,agendaWeek,month'
	      },
	      views: {
	        agendaTwoDay: {
	          type: 'agenda',
	          duration: { days: 2 },

	          // views that are more than a day will NOT do this behavior by default
	          // so, we need to explicitly enable it
	          groupByResource: true

	          //// uncomment this line to group by day FIRST with resources underneath
	          //groupByDateAndResource: true
	        }
	      },

	      //// uncomment this line to hide the all-day slot
	      //allDaySlot: false,

	      resources: [
	        { id: 'a', title: 'Room A' },
	        { id: 'b', title: 'Room B', eventColor: 'green' },
	        { id: 'c', title: 'Room C', eventColor: 'orange' },
	        { id: 'd', title: 'Room D', eventColor: 'red' }
	      ],
	      events: [
	        { id: '1', resourceId: 'a', start: '2018-04-06', end: '2018-04-08', title: 'event 1' },
	        { id: '2', resourceId: 'a', start: '2018-04-07T09:00:00', end: '2018-04-07T14:00:00', title: 'event 2' },
	        { id: '3', resourceId: 'b', start: '2018-04-07T12:00:00', end: '2018-04-08T06:00:00', title: 'event 3' },
	        { id: '4', resourceId: 'c', start: '2018-04-07T07:30:00', end: '2018-04-07T09:30:00', title: 'event 4' },
	        { id: '5', resourceId: 'd', start: '2018-04-07T10:00:00', end: '2018-04-07T15:00:00', title: 'event 5' }
	      ],

	      select: function(start, end, jsEvent, view, resource) {
	        console.log(
	          'select',
	          start.format(),
	          end.format(),
	          resource ? resource.id : '(no resource)'
	        );
	      },
	      dayClick: function(date, jsEvent, view, resource) {
	        console.log(
	          'dayClick',
	          date.format(),
	          resource ? resource.id : '(no resource)'
	        );
	      }
	    });
	  
	  });

	</script>
	<style>

	  body {
	    margin: 0;
	    padding: 0;
	    font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
	    font-size: 14px;
	  }

	  #calendar {
	    max-width: 900px;
	    margin: 50px auto;
	  }

	</style>
	</head>
	<body>

	  <div id='calendar'></div>

	</body>
	</html>