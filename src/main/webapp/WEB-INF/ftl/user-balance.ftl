<#import "/spring.ftl" as spring />
<html>
<head>
  <title>Balance</title>
</head>
<body>
  <#include "header.ftl">
  <h1>Your balance</h1>
  Balance: ${user.userAccount.balance} <br>
  <form method="POST" action="<@spring.url '/user/balance/refill'/>">
    <input type="edit" name="amount"/>
    <input type="submit" value="accept"/>
  </form>
  <br>
  <a href="<@spring.url '/event/all'/>">main page</a>
</body>
</html>
