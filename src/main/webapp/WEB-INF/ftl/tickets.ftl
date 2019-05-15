<#import "/spring.ftl" as spring />
<html>
<head>
  <title>Tickets</title>
</head>
  <#include "header.ftl">
  <h1>Tickets:</h1>
  <table>
    <tr>
      <td>Event name</td>
      <td>Seats</td>
      <td>Price</td>
    </tr>
  <#list tickets as ticket>
    <tr>
      <td>${ticket.event.name}</td>
      <td>${ticket.bookedSeats}</td>
      <td>${ticket.totalPrice}</td>
    </tr>
  </#list>
  </table>
</body>
</html>
