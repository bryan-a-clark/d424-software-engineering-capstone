@param edu.wgu.activitytracker.dto.UserDto user
@param org.springframework.security.web.csrf.CsrfToken csrfToken
@param String error = null

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Please Login</title>
  <link href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
  <script src="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.js" defer></script>
</head>
<body>
  <div class="mt-5">
    <div class="card p-3 mx-auto" style="width: 25rem;">
      <div class="text-center">
        <form action="/login" method="post">
          <h1 class="h3 mb-3 font-weight-normal">Please Log In</h1>
          <label for="username" class="visually-hidden">
            Username
          </label>
          <input type="text"
                 class="form-control mb-3"
                 name="username"
                 placeholder="Username"
                 id="username"
                 value="${user.getUsername()}"
                 required
                 autofocus>
            <label for="password" class="visually-hidden">
              Password
            </label>
            <input type="password"
                   class="form-control mb-3"
                   name="password"
                   placeholder="Password"
                   id="password"
                   value="${user.getPassword()}"
                   required>
            <input type="hidden" name="${csrfToken.getParameterName()}" value="${csrfToken.getToken()}">
            <button type="submit" class="btn btn-primary">log in</button>
        </form>
      </div>
    </div>
  </div>
  @if(error != null)
    <div class="text-center text-danger">
      ${error}
    </div>
  @endif
</body>
</html>