@param edu.wgu.activitytracker.dto.UserDto user
@param org.springframework.security.web.csrf.CsrfToken csrfToken
@param String error = null
@param String message = null

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Register User</title>
  <link href="/css/bootstrap.min.css" rel="stylesheet">
  <script src="/js/bootstrap.min.js" defer></script>
</head>
<body>
  <div class="mt-5">
    <div class="card p-3 mx-auto" style="width: 25rem;">
      <div class="text-center">
        <form action="/register" method="post">
          <h1 class="h3 mb-3 font-weight-normal">Register New User</h1>
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
                 required
                 >
          <label for="password" class="visually-hidden">
            Confirm Password
          </label>
          <input type="password"
                 class="form-control mb-3"
                 name="confirmPassword"
                 placeholder="Confirm Password"
                 id="confirmPassword"
                 onchange="validatePasswords()"
                 required>
          <input type="hidden" name="${csrfToken.getParameterName()}" value="${csrfToken.getToken()}">
          <button type="submit" class="btn btn-primary">Register</button>
          <a href="/login" class="btn btn-primary">Cancel</a>
        </form>
      </div>
    </div>
  </div>
  @if(error != null)
    <div class="text-center text-danger">
      ${error}
    </div>
  @endif
  @if(message != null)
    <div class="text-center text-success">
      ${message}
    </div>
  @endif
  <script>
      const password = document.getElementById("password")
      const confirmPassword = document.getElementById("confirmPassword")

      function validatePasswords() {
          confirmPassword.setCustomValidity("")
          if (!confirmPassword.validity.valid) {
              return;
          }

          if (confirmPassword.value !== password.value) {
              confirmPassword.setCustomValidity("The passwords don't match")
          }
      }

      confirmPassword.addEventListener("input", function() {
          confirmPassword.setCustomValidity("");
      });
  </script>
</body>
</html>