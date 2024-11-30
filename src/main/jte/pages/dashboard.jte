@import edu.wgu.activitytracker.utils.CsrfHiddenInput
@import edu.wgu.activitytracker.entities.DistanceUnit
@import edu.wgu.activitytracker.entities.Activity
@import edu.wgu.activitytracker.dto.UserActivityDto
@import java.util.List

@param List<DistanceUnit> distanceUnits
@param List<Activity> activities
@param List<UserActivityDto> userActivities
@param UserActivityDto userActivity
@param CsrfHiddenInput csrfHiddenInput

@template.layout.default(
content = @`
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
    @for(UserActivityDto userActivityDto : userActivities)
      ${userActivityDto.toString()}
    @endfor
    <div class="card m-4">
      <div class="card-body">
        Activities
        <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addActivityModal">New Activity</button>
      </div>
    </div>
    <div class="modal fade" id="addActivityModal" tabindex="-1" aria-labelledby="addActivityModalLabel" aria-hidden="true">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h1 class="modal-title fs-5" id="exampleModalLabel">Add New Activity</h1>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form action="/dashboard/activity/new" method="post">
              ${csrfHiddenInput}
              <label for="activities">activity</label>
              <select name="activityId" id="activities" class="form-select">
                @for (Activity activity : activities)
                  <option value="${activity.getId()}">${activity.getName()}</option>
                @endfor
              </select>
              <label for="startDateTime" class="">
                Start:
              </label>
              <input type="datetime-local"
                     class="form-control mb-3"
                     name="startDateTime"
                     id="startDateTime"
                     value="${String.valueOf(userActivity.getStartDateTime())}"
              >
              <label for="endDateTime" class="">
                End:
              </label>
              <input type="datetime-local"
                     class="form-control mb-3"
                     name="endDateTime"
                     id="endDateTime"
                     onchange="validateEndDate()"
                     value="${String.valueOf(userActivity.getEndDateTime())}"
              >
              <label for="distance" class="">
                Distance:
              </label>
              <input type="number"
                     class="form-control mb-3"
                     name="distance"
                     id="distance"
                     value="${userActivity.getDistance()}"
              >
              <label for="distanceUnit" class="">
                Unit:
              </label>
              <select name="distanceUnitId" id="distanceUnit" class="form-select">
                @for (DistanceUnit unit : distanceUnits)
                  <option value="${unit.getId()}">${unit.getName()}</option>
                @endfor
              </select>
              <label for="note" class="">
                Note:
              </label>
              <input type="text"
                     class="form-control mb-3"
                     name="note"
                     placeholder="Note"
                     id="note"
                     value="${userActivity.getNote()}"
              >
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
              <button type="submit" class="btn btn-primary">Save</button>
            </form>
          </div>
        </div>
      </div>
    </div>
    <script>
        document.addEventListener('DOMContentLoaded', (event) => {
            const startDate = document.getElementById("startDateTime");
            const endDate = document.getElementById("endDateTime");

            function validateEndDate() {
                if (endDate.value && startDate.value && endDate.value < startDate.value) {
                    endDate.setCustomValidity("The end date must be after the start date");
                } else {
                    endDate.setCustomValidity("");
                }
            }

            startDate.addEventListener("change", validateEndDate);
            endDate.addEventListener("change", validateEndDate);
        });
    </script>
  </main>
`)