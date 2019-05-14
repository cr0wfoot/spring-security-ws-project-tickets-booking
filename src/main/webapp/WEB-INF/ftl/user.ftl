<#import "/spring.ftl" as spring />
<html>
<head>
  <title>Account</title>
</head>
<body>
  <h1>Your Account</h1>
  <ul>
    <li>Login: ${user.login}</li>
    <li>Name: ${user.fullName}</li>
    <li>Email: ${user.email}</li>
    <li><a href="<@spring.url '/user/tickets?userId=${user.id}'/>">booked tickets</a></li>
    <li><a href="<@spring.url '/user/tickets/pdf?userId=${user.id}'/>">tickets in PDF</a></li>
  </ul>
  <br>
  <a href="<@spring.url '/event/all'/>">main page</a>
</body>
</html>
