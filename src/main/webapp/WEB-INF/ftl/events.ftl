<#import "/spring.ftl" as spring />
<html>
<head>
  <title>Events</title>
</head>
<body>
  <#include "header.ftl">
  <h1>Events:</h1>
  <table>
    <tr>
      <td>Name</td>
      <td>Rate</td>
      <td>Price</td>
      <td>Date</td>
      <td>Auditorium</td>
      <td></td>
    </tr>
  <#list events as event>
    <tr>
      <td>${event.name}</td>
      <td>${event.rate}</td>
      <td>${event.basePrice}</td>
      <td>${event.dateTime}</td>
      <td><a href="#">${event.auditorium.name}</a></td>
      <td><a href="<@spring.url '/event/id/${event.id}'/>">details</a></td>
    </tr>
  </#list>
  </table>
</body>
</html>
