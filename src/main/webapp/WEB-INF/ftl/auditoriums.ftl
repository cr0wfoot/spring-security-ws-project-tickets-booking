<html>
<head>
  <title>Auditoriums</title>
</head>
  <#include "header.ftl">
  <h1>Auditoriums:</h1>
  <table>
    <tr>
      <td>Name</td>
      <td>Number of seats</td>
      <td>VIP seats</td>
    </tr>
  <#list auditoriums as auditorium>
    <tr>
      <td>${auditorium.name}</td>
      <td>${auditorium.seatsQuantity}</td>
      <td>${auditorium.vipSeats}</td>
    </tr>
  </#list>
  </table>
</body>
</html>
