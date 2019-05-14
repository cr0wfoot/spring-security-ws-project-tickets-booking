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
      <td>Date</td>
      <td>Seats</td>
      <td>Price</td>
    </tr>
  <#list tickets as ticket>
    <tr>
      <td>${ticket.event.name}</td>
      <td>${ticket.dateTime}</td>
      <td>${ticket.seats}</td>
      <td>${ticket.price}</td>
    </tr>
  </#list>
  </table>
</body>
</html>
