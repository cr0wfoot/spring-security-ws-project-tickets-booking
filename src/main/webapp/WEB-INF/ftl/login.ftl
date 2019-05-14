<#import "/spring.ftl" as spring />
<html>
<head>
  <title>Authentication</title>
</head>
<body>
  <h1>Authentication</h1>
  <form method="POST" action="./login">
    <table>
      <tr>
        <td>Login (email)</td>
        <td><input type="edit" name="email" /></td>
      </tr>
      <tr>
        <td>Password</td>
        <td><input type="password" name="password" /></td>
      </tr>
    </table>
    <input type="submit" value="log in" />
  </form>
  <a href="<@spring.url '/user/registration'/>">registration</a>
  <br><br>
  <br>Registered accounts for demo:
  <table style="border:1px solid black">
  <tr><td><b>login</b></td><td><b>password</b></td><td>description</td></tr>
  <tr><td>user</td><td>user</td><td>simple user with booked tickets</td></tr>
  <tr><td>manager</td><td>manager</td><td>booking manager:</td></tr>
  <tr><td>admin</td><td>admin</td><td>admin:</td></tr>
  </table>
</body>
</html>
