<#import "/spring.ftl" as spring />
<html>
<head>
  <title>Account</title>
</head>
<body>
  <h1>Your Account</h1>
  <ul>
    <li>Name: ${user.name}</li>
    <li>Email: ${user.email}</li>
    <li>Birthday: ${user.birthday}</li>
    <li>Balance: ${user.userAccount.balance} (<a href="<@spring.url '/user/balance'/>">refill</a>)</li>
    <li><a href="<@spring.url '/user/tickets?userId=${user.id}'/>">booked tickets</a></li>
    <li><a href="<@spring.url '/user/tickets/pdf?userId=${user.id}'/>">tickets in PDF</a></li>
  </ul>
  <br>
  <a href="<@spring.url '/event/all'/>">main page</a>
</body>
</html>
