<html>
<head>
  <title>Registration</title>
</head>
<body>
  <h1>Registration</h1>
  <form method="POST" action="./register">
    <table>
      <tr>
        <td>Name</td>
        <td><input type="edit" name="name" /></td>
      </tr>
      <tr>
        <td>Email (login)</td>
        <td><input type="edit" name="email" /></td>
      </tr>
      <tr>
        <td>Password</td>
        <td><input type="password" name="password" /></td>
      </tr>
      <tr>
        <td>Birthday</td>
        <td><input type="edit" name="birthday" placeholder="1990-10-27" /></td>
      </tr>
    </table>
    <input type="submit" value="register" />
  </form>
</body>
</html>
