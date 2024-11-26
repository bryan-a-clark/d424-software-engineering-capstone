<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Activity Tracker</title>
  <link href="/webjars/bootstrap/5.3.3/css/bootstrap.min.css" rel="stylesheet">
  <script src="/webjars/bootstrap/5.3.3/js/bootstrap.bundle.js" defer></script>
</head>
<body>
  <nav class="navbar bg-primary-subtle navbar-expand-lg">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Activity Tracker</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">Features</a>
          </li>
        </ul>
      </div>
      <div>
        <button class="btn btn-outline-primary">Logout</button>
      </div>
    </div>
  </nav>
  <main>
    <div class="card m-4">
      <div class="card-body">
        Activities
        <button class="btn btn-primary">New Activity</button>
      </div>
    </div>
  </main>
</body>
</html>