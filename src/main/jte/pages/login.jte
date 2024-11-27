@import edu.wgu.activitytracker.utils.CsrfHiddenInput
@import edu.wgu.activitytracker.dto.UserDto

@param UserDto user
@param CsrfHiddenInput csrfHiddenInput
@param String error = null

@template.layout.default(
content = @`
  <main>
    <div class="mt-5">
      <div class="card p-3 mx-auto" style="width: 25rem;">
        <div class="text-center">
          <form action="/login" method="post">
            ${csrfHiddenInput}
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
              <button type="submit" class="btn btn-primary">Log in</button>
              <a href="/register" class="btn btn-primary">Register</a>
          </form>
        </div>
      </div>
    </div>
    @if(error != null)
      <div class="text-center text-danger">
        ${error}
      </div>
    @endif
  </main>
`)