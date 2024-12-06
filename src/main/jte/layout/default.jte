@import edu.wgu.activitytracker.utils.CsrfHiddenInput
@param gg.jte.Content content

@param CsrfHiddenInput csrfHiddenInput

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
      <a class="navbar-brand" href="/dashboard">Activity Tracker</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div>
        <form action="/logout" method="POST">
          ${csrfHiddenInput}
          <button class="btn btn-outline-primary" type="submit">Logout</button>
        </form>
      </div>
    </div>
  </nav>
  <main>
    ${content}
  </main>
</body>
</html>