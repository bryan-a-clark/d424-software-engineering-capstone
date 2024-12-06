@import edu.wgu.activitytracker.utils.CsrfHiddenInput
@import edu.wgu.activitytracker.dto.UserDto
@import java.util.List
@import org.springframework.validation.ObjectError

@param UserDto user
@param CsrfHiddenInput csrfHiddenInput
@param List<ObjectError> errors

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Activity Tracker</title>
    <link href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.js" defer></script>
  </head>
  <body>
    <nav class="navbar bg-primary-subtle navbar-expand-lg">
      <div class="container-fluid">
        <a class="navbar-brand" href="/login">Activity Tracker</a>
      </div>
    </nav>
    <main>
      <div class="mt-5">
        <div class="card p-3 mx-auto" style="width: 25rem;">
          <div class="text-center">
            <form action="/user/register" method="POST">
              ${csrfHiddenInput}
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
              <button type="submit" class="btn btn-primary">Register</button>
              <a href="/login" class="btn btn-primary">Cancel</a>
            </form>
            @if (errors != null)
              <div class="alert alert-danger mt-3" role="alert">
                @for (var e : errors)
                  <span>${e.getDefaultMessage()}</span><br>
                @endfor
              </div>
            @endif
          </div>
        </div>
      </div>
    </main>
  </body>
</html>