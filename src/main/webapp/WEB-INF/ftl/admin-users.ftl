<#import "/spring.ftl" as spring />
<html>
<head>
  <title>Users</title>
</head>
<body>
  <h1>Users</h1>
  <#if users?has_content>
  <table>
    <tr>
      <td>Name</td>
      <td>Email</td>
      <td>Birthday</td>
      <td></td>
    </tr>
  <#list users as user>
    <tr>
      <td>${user.name}</td>
      <td><a href="<@spring.url '/user/id/${user.id}'/>">${user.email}</a></td>
      <td>${user.birthday}</td>
      <td>
        <form method="POST" enctype="multipart/form-data" action="./remove">
          <input type="hidden" name="id" value="${user.id}"/>
          <input type="submit" value="delete" />
        </form>
      </td>
    </tr>
  </#list>
  </table>
  </#if>
  <form method="POST" enctype="multipart/form-data" action="./upload">
  	<table>
  	  <tr><td>File with users to upload:</td><td><input type="file" name="file" /></td></tr>
  	  <tr><td></td><td><input type="submit" value="upload" /></td></tr>
  	</table>
  </form>
</body>
</html>
