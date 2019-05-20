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
      <td>Price</td>
      <td>Date</td>
      <td>Auditorium</td>
      <td></td>
    </tr>
  <#list events as event>
    <tr>
      <td>${event.name}</td>
      <td>${event.seatPrice}</td>
      <td>${event.date}</td>
      <td><a href="#">${event.auditorium.name}</a></td>
      <td><a href="<@spring.url '/event/id/${event.id}'/>">details</a></td>
    </tr>
  </#list>
  </table>
  <form method="POST" enctype="multipart/form-data" action="./upload">
  	<table>
  	  <tr><td>File with users to upload:</td><td><input type="file" name="file" /></td></tr>
  	  <tr><td></td><td><input type="submit" value="upload" /></td></tr>
  	</table>
  </form>
</body>
</html>
