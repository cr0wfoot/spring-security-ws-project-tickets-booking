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
</body>
</html>
