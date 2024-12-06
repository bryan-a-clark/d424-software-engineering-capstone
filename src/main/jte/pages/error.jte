@import java.util.Map;

@param Map<String, Object> errorDetails

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
    <main class="m-4">
      <div>
        <h1 class="h3">An unrecoverable error has occurred.</h1>
      </div>
      <div class="alert alert-danger" role="alert">
        ${errorDetails.get("message").toString()}
      </div>
      <a class="btn btn-primary" href="/login">Return to login</a>
    </main>
  </body>
</html>