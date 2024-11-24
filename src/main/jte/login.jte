@param edu.wgu.activitytracker.dto.UserDto user
@param org.springframework.security.web.csrf.CsrfToken csrfToken
@param String error = null

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Please Login</title>
</head>
<body>
<h1>Please Log In</h1>

  @if(error != null)
    <div>
      ${error}
    </div>
  @endif
  <div>
    <form action="/login" method="post">
      <div>
        <label for="username">
          Username
        </label>
        <input type="text"
               name="username"
               placeholder="Username"
               id="username"
               value="${user.getUsername()}">
      </div>
      <div>
        <label for="password">
          Password
        </label>
        <input type="password"
               name="password"
               placeholder="Password"
               id="password"
               value="${user.getPassword()}">
      </div>
      <div>
        <input type="hidden" name="${csrfToken.getParameterName()}" value="${csrfToken.getToken()}">
      </div>
      <div>
        <button type="submit">log in</button>
      </div>
    </form>
  </div>
</body>
</html>