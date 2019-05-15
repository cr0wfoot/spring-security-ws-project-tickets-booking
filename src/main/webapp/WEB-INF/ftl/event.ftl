<#import "/spring.ftl" as spring />
<html>
<head>
  <title>Event</title>
</head>
<body>
  <#include "header.ftl">
  <h1>Event:</h1>
  <ul>
    <li>Name: ${event.name}</li>
    <li>Date: ${event.date}</li>
    <li>Price: ${event.seatPrice}</li>
    <li>Auditorium: <a href="<@spring.url '/auditorium/${event.auditorium.name}'/>">${event.auditorium.name}</a></li>
  </ul>
  <a href="<@spring.url '/event/tickets?eventId=${event.id}'/>">Booked tickets for event (for managers only)</a><br>
  <a href="<@spring.url '/event/tickets/pdf?eventId=${event.id}'/>">Booked tickets in PDF (for managers only)</a><br><br>
  <form method="POST" action="<@spring.url '/ticket/book'/>">
    <#list seats as seat>
    <#if seat_index % 20 == 0><br></#if>
      [${seat}<input type="checkbox" name="seat" value="${seat}"/>]
    </#list>
    <br>
    <input type="hidden" name="eventId" value="${event.id}"/>
    <input type="submit" value="book tickets"/>
  </form>
</body>
</html>
